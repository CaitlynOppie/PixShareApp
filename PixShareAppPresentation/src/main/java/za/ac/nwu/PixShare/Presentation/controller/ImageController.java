package za.ac.nwu.PixShare.Presentation.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.service.Response;
import za.ac.nwu.PixShare.Logic.service.ImageService;
import za.ac.nwu.PixShare.Logic.service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(
            path = "/upload/{userID}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Uploads new image.", notes = "Uploads a new Image to AWS.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was uploaded", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public void uploadImage(@PathVariable("userID") Integer userID, @RequestParam("file")MultipartFile file) throws IOException {
        imageService.uploadImage(file, userID);
    }

    @DeleteMapping(
            path ="/delete/{imgName}")
    @ApiOperation(value = "Deletes image.", notes = "Deletes image from AWS.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was deleted", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> deleteFile(@PathVariable String imgName) {
        return new ResponseEntity<>(imageService.deleteImage(imgName), HttpStatus.OK);
    }

//    View Image

    @GetMapping(
            path ="/download/{imgName}")
    @ApiOperation(value = "Downloads image to user's PC.", notes = "Downloads image from AWS to user's PC.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image downloaded", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable String imgName) throws IOException {
        byte[] data = imageService.downloadImage(imgName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-disposition", "attachment; filename=\"" + imgName + "\"")
                .body(resource);
    }
}
