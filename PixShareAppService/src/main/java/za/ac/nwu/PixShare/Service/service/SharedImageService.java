package za.ac.nwu.PixShare.Service.service;

import org.springframework.stereotype.Service;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.persistence.Image;

@Service
public interface SharedImageService {

    String shareImage(ImageDTO img, Integer sharedID) throws Exception;

    String deleteSharedImage(Integer sharedImgID, String imgName, Integer sharedID) throws Exception;

}
