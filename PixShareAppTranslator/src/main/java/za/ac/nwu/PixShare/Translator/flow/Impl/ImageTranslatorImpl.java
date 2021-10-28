package za.ac.nwu.PixShare.Translator.flow.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Repo.persistence.ImageRepository;
import za.ac.nwu.PixShare.Translator.flow.ImageTranslator;

import java.io.File;
import java.io.IOException;

@Component
public class ImageTranslatorImpl implements ImageTranslator {

    private final AmazonS3 s3;
    private final ImageRepository imageRepository;

    @Autowired
    public ImageTranslatorImpl(AmazonS3 s3, ImageRepository imageRepository) {
        this.s3 = s3;
        this.imageRepository = imageRepository;
    }

    @Override
    public void uploadImage(String bucketName, String imgName, File image) throws IOException {
        s3.putObject(new PutObjectRequest(bucketName, imgName, image));
    }
//    Upload metadata

    @Override
    public void deleteImage(String bucketName, String imgName) {
        s3.deleteObject(bucketName, imgName);
    }
//    Delete metadata & user access

//    View image

    @Override
    public S3ObjectInputStream downloadImage(String imgName, String bucketName) throws IOException {
        S3Object image = s3.getObject(bucketName, imgName);
        S3ObjectInputStream s3inputStream = image.getObjectContent();
        return s3inputStream;
    }




}
