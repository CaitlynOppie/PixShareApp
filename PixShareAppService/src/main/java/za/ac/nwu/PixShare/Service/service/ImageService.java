package za.ac.nwu.PixShare.Service.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface ImageService {

    String uploadImage(MultipartFile image, Integer userID) throws IOException;

    String deleteImage(String imgName, Integer userID) throws Exception;

    ByteArrayOutputStream downloadImage(String imgName, Integer userID) throws IOException;

    List<String> listAllImages(Integer userID) throws Exception;

//    String updateMetadata(Integer userID, String oldName, String newName);
}
