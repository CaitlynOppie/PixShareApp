package za.ac.nwu.PixShare.Service.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface ImageService {

    String uploadImage(MultipartFile image, Integer userID) throws IOException;

    String deleteImage(String imgName, Integer userID);

    ByteArrayOutputStream downloadImage(String imgName, Integer userID) throws IOException;

//    String updateMetadata(Integer userID, String oldName, String newName);
}
