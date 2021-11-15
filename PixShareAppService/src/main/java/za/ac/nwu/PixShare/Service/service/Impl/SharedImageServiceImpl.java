package za.ac.nwu.PixShare.Service.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.persistence.BucketName;
import za.ac.nwu.PixShare.Domain.persistence.Image;
import za.ac.nwu.PixShare.Domain.persistence.SharedImage;
import za.ac.nwu.PixShare.Repo.persistence.ImageRepository;
import za.ac.nwu.PixShare.Repo.persistence.SharedImageRepository;
import za.ac.nwu.PixShare.Service.service.SharedImageService;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Transactional
@Component("SharedImageService")
public class SharedImageServiceImpl implements SharedImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SharedImageServiceImpl.class);

    private final AmazonS3 s3;
    private String bucketName = BucketName.IMAGE.getBucketName();
    private final SharedImageRepository sharedImageRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public SharedImageServiceImpl(AmazonS3 s3, SharedImageRepository sharedImageRepository, ImageRepository imageRepository) {
        this.s3 = s3;
        this.sharedImageRepository = sharedImageRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public String shareImage(ImageDTO image, Integer sharedID) throws Exception {
        try{
            SharedImage sharedImage = new SharedImage(image.getName(), sharedID, image.getUserID(), image.getImageID());
            String fromBucket = bucketName + "/" + image.getUserID();
            String toBucket = bucketName + "/" + sharedID;
            String key=  image.getName();
            LOGGER.info("The image provided is {}, the userID of the user that the image is shared with is {}, from bucket: {}, to bucket: {}", image, sharedID, fromBucket, toBucket);
            image.setImageID(null);
            image.setUserID(sharedID);

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date today = Calendar.getInstance().getTime();
            image.setDate(dateFormat .format(today));
            image.setLink(bucketName + "/" + sharedID + "/" + image.getName());
            imageRepository.save(new Image(image));
            s3.copyObject(fromBucket, key, toBucket, key);
            sharedImageRepository.save(sharedImage);
            LOGGER.info("The output object is {}", sharedImage);
            return "Image shared";
        }catch (Exception e){
            throw new Exception("Image could not be shared", e);
        }

    }


    @Override
    public String deleteSharedImage(Integer sharedImgID, String imgName, Integer sharedID) throws Exception {
        try{
            LOGGER.info("The shared image ID provided is {}, the userID of the user that the image is shared with is {} and the name" +
                    " of the shared the image is {}", sharedImgID, sharedID, imgName);
            String toBucket = bucketName + "/" + sharedID;
            s3.deleteObject(toBucket, imgName);
            sharedImageRepository.deleteById(sharedImgID);
            return "Image deleted";
        }catch (Exception e){
            throw new Exception("Image could not be deleted", e);
        }


    }
}
