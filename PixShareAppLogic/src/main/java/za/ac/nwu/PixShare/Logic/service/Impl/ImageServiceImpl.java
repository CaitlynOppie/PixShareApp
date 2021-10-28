package za.ac.nwu.PixShare.Logic.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.persistence.BucketName;
import za.ac.nwu.PixShare.Domain.persistence.Image;
import za.ac.nwu.PixShare.Logic.service.ImageService;
import za.ac.nwu.PixShare.Translator.flow.ImageTranslator;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Transactional
@Component("ImageService")
public class ImageServiceImpl implements ImageService {

    private String bucketName = BucketName.PROFILE_IMAGE.getBucketName();
    private AmazonS3 s3;
    private final ImageTranslator imageTranslator;

    @Autowired
    public ImageServiceImpl(AmazonS3 s3, ImageTranslator imageTranslator) {
        this.s3 = s3;
        this.imageTranslator = imageTranslator;
    }


//    public void uploadImage(ImageDTO imageDTO, MultipartFile file ) {
////        1. Check if image is not empty
//        isFileEmpty(file);
////        2. If file is an image
//        isImage(file);
////        3. The user exists in DB
////        userProfileDataAccessService
////                .getUserProfiles()
////                .stream()
////                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
////                .findFirst()
////                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
//
////        4. Grab some metadata from file if any
//        Map<String, String> metadata = extractMetadata(file);
//
////        5. Store the image in S3 and update DB (userProfileImageLink) with S3 image link
//
//        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
//        String filename = String.format("%s-%s", file.getName(), UUID.randomUUID());
//
//        try{
//            imageDTO.setLink(filename);
//            imageDTO.setName(file.getOriginalFilename());
//            imageDTO.setSize(file.getSize());
//            imageTranslator.uploadImage(path, filename, Optional.of(metadata), file.getInputStream(), imageDTO);
//        }catch(IOException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    private Map<String, String> extractMetadata(MultipartFile file) {
//        Map<String, String> metadata = new HashMap<>();
//        metadata.put("Content-Type", file.getContentType());
//        metadata.put("Content-Length", String.valueOf(file.getSize()));
//        return metadata;
//    }
//
//    private void isImage(MultipartFile file) {
//        if (!Arrays.asList(
//                IMAGE_JPEG.getMimeType(),
//                IMAGE_PNG.getMimeType(),
//                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
//            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
//        }
//    }
//
//    private void isFileEmpty(MultipartFile file) {
//        if (file.isEmpty()) {
//            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
//        }
//    }

//    Upload Image to aws
    @Override
    public String uploadImage(MultipartFile image, Integer userID) throws IOException {
        File newImage = convertMultiPartFile(image);
        String imgName = String.format("%s-%s", image.getOriginalFilename(), userID);
        s3.putObject(new PutObjectRequest(bucketName, imgName, newImage));
        newImage.delete();
        return "Image :" + imgName + " uploaded to AWS";
    }

//   upload metadata

    public String deleteImage(String imgName) {
        imageTranslator.deleteImage(bucketName,imgName);
        return imgName + " has been permanently deleted";
    }

//    delete metadata and remove user access (shared images)

    //        View image

    @Override
    public byte[] downloadImage(String imgName) throws IOException {
        S3ObjectInputStream s3inputStream = imageTranslator.downloadImage(imgName,bucketName);
        try {
            byte[] content = IOUtils.toByteArray(s3inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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




}
