package za.ac.nwu.PixShare.Logic.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;

@Service
public interface ImageService {

    void uploadImage(MultipartFile file, ImageDTO imageDTO);
}
