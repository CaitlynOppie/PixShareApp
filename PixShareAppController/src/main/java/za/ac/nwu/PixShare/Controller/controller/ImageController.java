package za.ac.nwu.PixShare.Controller.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.service.Response;
import za.ac.nwu.PixShare.Service.service.ImageService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

//  UPLOAD IMAGE
    @PostMapping(
            path = "/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Uploads new image.", notes = "Uploads a new Image to AWS.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was uploaded", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> uploadImage(@RequestParam("userID") Integer userID, @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(imageService.uploadImage(file, userID), HttpStatus.OK);
    }

//  DELETE IMAGE
    @DeleteMapping(
            path = "/image/delete/{userID}/{imgName}")
    @ApiOperation(value = "Deletes image.", notes = "Deletes image from AWS.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was deleted", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> deleteFile(
            @PathVariable Integer userID,
            @PathVariable String imgName) throws Exception {
        return new ResponseEntity<>(imageService.deleteImage(userID, imgName), HttpStatus.OK);
    }

//  DOWNLOAD IMAGE
    @GetMapping(
            path = "/image/download/{userID}/{imgName}")
    @ApiOperation(value = "Downloads image to user's PC.", notes = "Downloads image from AWS to user's PC.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image downloaded", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<byte[]> downloadImage(
            @PathVariable Integer userID,
            @PathVariable String imgName) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = imageService.downloadImage(imgName, userID);

        return ResponseEntity.ok()
                .contentType(contentType(imgName))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + imgName + "\"")
                .body(byteArrayOutputStream.toByteArray());
    }




    @GetMapping(
            path = "/image/view/{userID}/{imgName}")
    @ApiOperation(value = "Views image of a user.", notes = "Views image of a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image viewed", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<byte[]> viewImage(
            @PathVariable Integer userID,
            @PathVariable String imgName) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = imageService.downloadImage(imgName, userID);

        return ResponseEntity.ok()
                .contentType(contentType(imgName))
                .body(byteArrayOutputStream.toByteArray());
    }

    //    GET ALL OBJECTS

    @GetMapping(
            path = "/image/viewAll/{userID}")
    @ApiOperation(value = "Gets all images of a user.", notes = "Gets all images of a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Images views", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<Response<List<ImageDTO>>> viewAllImages(@PathVariable("userID") Integer userID) throws Exception {
        List<ImageDTO> images = imageService.getAllUserImage(userID);
        Response<List<ImageDTO>> response = new Response<>(true,images);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    private MediaType contentType(String imgName) {
        if(imgName.contains("png")){
            return MediaType.IMAGE_PNG;
        } else if(imgName.contains("jpeg") || imgName.contains("jpg")){
            return MediaType.IMAGE_JPEG;
        }else
        {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }


}

