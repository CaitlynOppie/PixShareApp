package za.ac.nwu.PixShare.Logic.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.persistence.BucketName;
import za.ac.nwu.PixShare.Logic.service.ImageService;
import za.ac.nwu.PixShare.Translator.flow.ImageTranslator;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Transactional
@Component("ImageService")
public class ImageServiceImpl implements ImageService {

    private final ImageTranslator imageTranslator;

    @Autowired
    public ImageServiceImpl(ImageTranslator imageTranslator) {
        this.imageTranslator = imageTranslator;
    }

    //ADD IMAGE TO AWS
        public void uploadImage(MultipartFile file, ImageDTO imageDTO) {
            //Test if image is not empty
            if (file.isEmpty()) {
                throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
            }
            //Test if file is an image
            if (!Arrays.asList(IMAGE_JPEG, IMAGE_PNG, IMAGE_GIF, IMAGE_BMP, IMAGE_SVG).contains(file.getContentType())) {
                throw new IllegalStateException("File must be an image");
            }

            File convImage = new File(file.getOriginalFilename());
            try (FileOutputStream fileOutputStream = new FileOutputStream(convImage)) {
                fileOutputStream.write(file.getBytes());
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            File image = convImage;
            String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), imageDTO.getUserID());
            String filename = String.format("%s-%s", file.getName(), imageDTO.getUserID());
            imageDTO.setLink(filename);
            imageDTO.setName(file.getOriginalFilename());
            imageDTO.setSize(file.getSize());
            imageTranslator.uploadImage(BucketName.PROFILE_IMAGE.getBucketName(), filename, imageDTO, image);
            convImage.delete();

        }

}
