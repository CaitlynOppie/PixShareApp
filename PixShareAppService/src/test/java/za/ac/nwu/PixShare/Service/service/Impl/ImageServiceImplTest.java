package za.ac.nwu.PixShare.Service.service.Impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImplTest {

    @Mock
    private ImageServiceImpl imageService;

    ImageDTO image;

    @Before
    public void setUp() throws Exception {
        image = new ImageDTO(129, "pixshare/105/MyFamily.png", "MyFamily.png", 110.5, "2021/11/19", 105);
    }

    @After
    public void tearDown() throws Exception {
        image = null;
    }

    @Test
    public void deleteImage() throws Exception {
        assertNotNull(image);
        String delMsg = imageService.deleteImage(image.getUserID(),image.getName());
        verify(imageService, atLeastOnce()).deleteImage(image.getUserID(),image.getName());
    }

    @Test
    public void listAllImages() throws Exception {
        assertNotNull(image);
        List<String> imgList = imageService.listAllImages(image.getUserID());
        assertNotNull(image);
        verify(imageService, atLeastOnce()).listAllImages(image.getUserID());
    }

    @Test
    public void getAllUserImage() throws Exception {
        assertNotNull(image);
        List<ImageDTO> images = imageService.getAllUserImage(image.getUserID());
        assertNotNull(images);
        verify(imageService, atLeastOnce()).getAllUserImage(image.getUserID());
    }
}