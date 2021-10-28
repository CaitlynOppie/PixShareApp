package za.ac.nwu.PixShare.Translator.flow.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Repo.persistence.ImageRepository;
import za.ac.nwu.PixShare.Translator.flow.ImageTranslator;

import java.io.File;

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
    public void uploadImage(String bucketName, String filename, ImageDTO imageDTO, File image) {
        try{
            s3.putObject(new PutObjectRequest(bucketName, filename, image));
            imageRepository.save(imageDTO.getImage());
        }catch (Exception e){
            throw new IllegalStateException("Failed to store image", e);
        }
    }


}
