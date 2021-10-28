package za.ac.nwu.PixShare.Logic.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public interface ImageService {

    String uploadImage(MultipartFile file, Integer userID) throws IOException;

    String deleteImage(String imgName);

    byte[] downloadImage(String imgName) throws IOException;
}
