package za.ac.nwu.PixShare.Translator.flow;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public interface ImageTranslator {

    void uploadImage(String bucketName, String imgName, File image) throws IOException;

    void deleteImage(String bucketName, String imgName);

    S3ObjectInputStream downloadImage(String imgName, String bucketName) throws IOException;
}
