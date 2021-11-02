package za.ac.nwu.PixShare.Service.service.Impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.persistence.BucketName;
import za.ac.nwu.PixShare.Domain.persistence.Image;
import za.ac.nwu.PixShare.Repo.persistence.ImageRepository;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.PixShare.Service.service.ImageService;

import javax.transaction.Transactional;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Component("ImageService")
public class ImageServiceImpl implements ImageService {
    private final AmazonS3 s3;
    private String bucketName = BucketName.IMAGE.getBucketName();
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(AmazonS3 s3, ImageRepository imageRepository) {
        this.s3 = s3;
        this.imageRepository = imageRepository;
    }

    //    UPLOAD IMAGE TO AWS ---> get userID from logged in user
    @Override
    public String uploadImage(MultipartFile image, Integer userID) throws IOException {

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", image.getContentType());
        metadata.put("Content-Length", String.valueOf(image.getSize()));
        ObjectMetadata objectMetadata = mapMetadata(Optional.of(metadata));

        File newImage = convertMultiPartFile(image);
        String path = String.format("%s/%s", bucketName, userID);
        String imgLink = path + "/" + image.getOriginalFilename();
        String imgName = image.getOriginalFilename();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        String imgDate = dateFormat .format(today);
        double imgSize = Math.round(image.getSize()/100)/10.0; //Divide by 1000 to get KB size

        try{
            s3.putObject(path, imgName, image.getInputStream(), objectMetadata);
            imageRepository.save(new Image(imgLink, imgName, imgSize, imgDate, userID));
        }catch(IOException e) {
            throw new IllegalStateException(e);
        }

//        s3.putObject(new PutObjectRequest(bucketName, imgLink, newImage));

        newImage.delete();
        return "Image :" + imgName + " uploaded to AWS";
    }

//   upload metadata ---> still to implement

    @Override
    public String deleteImage(String imgName, Integer userID) {
        String path = String.format("%s/%s", bucketName, userID);
        String imgLink = path + "/" + imgName;
        String imgKey = userID + "/" + imgName;
        s3.deleteObject(bucketName, imgKey);
        imageRepository.deleteById(imgLink);
        return imgName + " has been permanently deleted";
    }

//    delete metadata ---> still to implement

//    remove user access (shared images)

//    View image

    //  DOWNLOAD IMAGE ---> add option for multi file download (array)
    @Override
    public ByteArrayOutputStream downloadImage(String imgName, Integer userID) throws IOException {
        try {
//            String path = String.format("%s/%s", bucketName, userID);
            String imgKey = userID + "/" + imgName;
            S3Object image = s3.getObject(new GetObjectRequest(bucketName, imgKey));
            InputStream inputStream = image.getObjectContent();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int length;
            byte[] buffer = new byte[4096];
            while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream;
        } catch (IOException ioe) {
//            logger.error("IOException: " + ioe.getMessage());
            throw new IOException(ioe.getMessage());
        } catch (AmazonServiceException ase) {
//            logger.info("sCaught an AmazonServiceException from GET requests, rejected reasons:");
//            logger.info("Error Message:    " + ase.getMessage());
//            logger.info("HTTP Status Code: " + ase.getStatusCode());
//            logger.info("AWS Error Code:   " + ase.getErrorCode());
//            logger.info("Error Type:       " + ase.getErrorType());
//            logger.info("Request ID:       " + ase.getRequestId());
//            throw ase;
            throw new AmazonServiceException(ase.getMessage());
        } catch (AmazonClientException ace) {
//            logger.info("Caught an AmazonClientException: ");
//            logger.info("Error Message: " + ace.getMessage());
//            throw ace;
            throw new AmazonClientException(ace.getMessage());
        }
    }

    private File convertMultiPartFile(MultipartFile file) throws IOException {
        File newFile = new File(file.getOriginalFilename());
        try (FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (IOException e) {
            throw new IOException("Error converting multipart file to file", e);
        }
        return newFile;
    }

    private ObjectMetadata mapMetadata(Optional<Map<String, String>> optionalMetadata){
        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if(!map.isEmpty()){
                map.forEach(metadata::addUserMetadata);
            }
        });
        return metadata;
    }
}
