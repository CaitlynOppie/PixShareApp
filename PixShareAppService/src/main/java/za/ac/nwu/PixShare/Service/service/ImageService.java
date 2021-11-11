package za.ac.nwu.PixShare.Service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public interface ImageService {

    String uploadImage(MultipartFile image, Integer userID) throws IOException;

    String deleteImage(String imgName, Integer userID) throws Exception;

    ByteArrayOutputStream downloadImage(String imgName, Integer userID) throws IOException;

    List<String> listAllImages(Integer userID) throws Exception;

    List<ImageDTO> getAllUserImage(Integer userID) throws Exception;

//    String updateMetadata(Integer userID, String oldName, String newName);
}
