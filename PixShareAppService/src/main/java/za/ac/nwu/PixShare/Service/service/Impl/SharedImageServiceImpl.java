package za.ac.nwu.PixShare.Service.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.persistence.SharedImage;
import za.ac.nwu.PixShare.Repo.persistence.SharedImageRepository;
import za.ac.nwu.PixShare.Service.service.SharedImageService;

import javax.transaction.Transactional;

@Transactional
@Component("SharedImageService")
public class SharedImageServiceImpl implements SharedImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SharedImageServiceImpl.class);

    private final SharedImageRepository sharedImageRepository;

    @Autowired
    public SharedImageServiceImpl(SharedImageRepository sharedImageRepository) {
        this.sharedImageRepository = sharedImageRepository;
    }

    @Override
    public String shareImage(String imgLink, Integer sharerID, Integer sharedID) throws Exception {
        try{
            LOGGER.info("The image link provide is {}, the userID of the user that the image is shared with is {} and the userID" +
                    " of the user who shared the image is {}", imgLink, sharedID, sharerID);
            SharedImage sharedImage = new SharedImage(sharedID, sharerID, imgLink);
            sharedImageRepository.save(sharedImage);
            LOGGER.info("The output object is {}", sharedImage);
            return "Image shared";
        }catch (Exception e){
            throw new Exception("Image could not be shared", e);
        }


    }
}
