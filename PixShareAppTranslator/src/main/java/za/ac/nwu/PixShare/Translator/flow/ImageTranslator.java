package za.ac.nwu.PixShare.Translator.flow;

import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;

import java.io.File;

@Component
public interface ImageTranslator {

    void uploadImage(String bucketName, String filename, ImageDTO imageDTO, File image);
}
