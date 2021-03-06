package za.ac.nwu.PixShare.Service.service.Impl;


import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.persistence.BucketName;
import za.ac.nwu.PixShare.Domain.persistence.Image;
import za.ac.nwu.PixShare.Domain.persistence.User;
import za.ac.nwu.PixShare.Repo.persistence.ImageRepository;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.PixShare.Repo.persistence.SharedImageRepository;
import za.ac.nwu.PixShare.Service.service.ImageService;

import javax.transaction.Transactional;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Component("ImageService")
public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final AmazonS3 s3;
    private String bucketName = BucketName.IMAGE.getBucketName();
    private final ImageRepository imageRepository;
    private final SharedImageRepository sharedImageRepository;

    @Autowired
    public ImageServiceImpl(AmazonS3 s3, ImageRepository imageRepository, SharedImageRepository sharedImageRepository) {
        this.s3 = s3;
        this.imageRepository = imageRepository;
        this.sharedImageRepository = sharedImageRepository;
    }

    //    UPLOAD IMAGE TO AWS ---> get userID from logged in user
    @Override
    public String uploadImage(MultipartFile image, Integer userID) throws IOException {

        LOGGER.info("The input image is {} and the userID is {}", image, userID);
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", image.getContentType());
        metadata.put("Content-Length", String.valueOf(image.getSize()));
        ObjectMetadata objectMetadata = mapMetadata(Optional.of(metadata));

        String path = String.format("%s/%s", bucketName, userID);
        String imgLink = path + "/" + image.getOriginalFilename();
        String imgName = image.getOriginalFilename();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date today = Calendar.getInstance().getTime();
        String imgDate = dateFormat .format(today);
        double imgSize = Math.round(image.getSize()/100)/10.0;

        LOGGER.info("The image path is {} ", path);
        LOGGER.info("The image link is {} ", imgLink);
        LOGGER.info("The image name is {} ", imgName);
        LOGGER.info("The image date is {} ", imgDate);
        LOGGER.info("The image size is {} ", imgSize);
        try{
            s3.putObject(path, imgName, image.getInputStream(), objectMetadata);
            imageRepository.save(new Image(imgDate, imgLink, imgName, imgSize,  userID));
        }catch(IOException e) {
            throw new IllegalStateException(e);
        }
        return "Image :" + imgName + " uploaded to AWS";
    }

    @Override
    public String deleteImage(Integer userID, String imgName) throws Exception {
        try{
            LOGGER.info("The image name is {} and the userID is {} ", imgName, userID);
            String path = String.format("%s/%s", bucketName, userID);
            String imgLink = path + "/" + imgName;
            String imgKey = userID + "/" + imgName;
            LOGGER.info("The image path is {} ", path);
            LOGGER.info("The image link is {} ", imgLink);
            LOGGER.info("The image key is {} ", imgKey);
//            DELETE SHARED IMAGES ASWELL
            s3.deleteObject(bucketName, imgKey);
            imageRepository.deleteByLink(imgLink);
            return imgName + " has been permanently deleted";
        }catch (Exception e){
            throw new Exception("Image could not be deleted",e);
        }

    }

    //  DOWNLOAD IMAGE ---> add option for multi file download (array)
    @Override
    public ByteArrayOutputStream downloadImage(String imgName, Integer userID) throws IOException {
        try {
            LOGGER.info("The image name is {} and the userID is {} ", imgName, userID);
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
            throw new IOException(ioe.getMessage());
        } catch (AmazonServiceException ase) {
            throw new AmazonServiceException(ase.getMessage());
        } catch (AmazonClientException ace) {
            throw new AmazonClientException(ace.getMessage());
        }
    }

    @Override
    public List<String> listAllImages(Integer userID) throws Exception {
        try {
            LOGGER.info("The userID is {} ", userID);
            String prefix = userID + "/";
            List<String> names = new ArrayList<>();
            ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix(prefix).withDelimiter("/");
            ListObjectsV2Result listing = s3.listObjectsV2(req);
            for (S3ObjectSummary summary: listing.getObjectSummaries()) {
                names.add(summary.getKey());
            }
            LOGGER.info("The output is: {}", names);
            return names;

        } catch (Exception e) {
            throw new Exception("Could not list all images");
        }
    }

    @Override
    public List<ImageDTO> getAllUserImage(Integer userID) throws Exception {
        try {
            LOGGER.info("The userID is {} ", userID);
            LOGGER.info("The output is: {}",imageRepository.findAllByUserID(new User(userID)) );
            return imageRepository.findAllByUserID(new User(userID));
        } catch (Exception e) {
            throw new Exception("Could not get all user images");
        }
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
