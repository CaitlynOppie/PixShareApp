package za.ac.nwu.PixShare.Service.service.Impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.DTO.SharedImageDTO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SharedImageServiceImplTest {

    @Mock
    SharedImageServiceImpl sharedImageService;

    SharedImageDTO sharedDTO;
    ImageDTO image;

    @Before
    public void setUp() throws Exception {
        sharedDTO = new SharedImageDTO(112, "IMG_20201213_085225 (2).jpg", 108, 108, 106);
        image = new ImageDTO(129, "pixshare/105/MyFamily.png", "MyFamily.png", 110.5, "2021/11/19", 105);
    }

    @After
    public void tearDown() throws Exception {
        sharedDTO = null;
    }

    @Test
    public void shareImage() throws Exception {
        assertNotNull(sharedDTO);
        String msg = sharedImageService.shareImage(image, sharedDTO.getUserIDShared());
        verify(sharedImageService, atLeastOnce()).shareImage(image, sharedDTO.getUserIDShared());
    }


    @Test
    public void deleteSharedImage() throws Exception {
        assertNotNull(sharedDTO);
        String msg = sharedImageService.deleteSharedImage(sharedDTO.getSharedImageID(), sharedDTO.getImgName(), sharedDTO.getUserIDShared());
        verify(sharedImageService, atLeastOnce()).deleteSharedImage(sharedDTO.getSharedImageID(), sharedDTO.getImgName(), sharedDTO.getUserIDShared());
    }
}