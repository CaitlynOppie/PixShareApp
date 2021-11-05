package za.ac.nwu.PixShare.Controller.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.PixShare.Domain.service.Response;
import za.ac.nwu.PixShare.Service.service.ImageService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

//  UPLOAD IMAGE
    @PostMapping(
            path = "/image/upload/{userID}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Uploads new image.", notes = "Uploads a new Image to AWS.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was uploaded", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> uploadImage(@PathVariable("userID") Integer userID, @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(imageService.uploadImage(file, userID), HttpStatus.OK);
    }

//  DELETE IMAGE
    @DeleteMapping(
            path = "/image/delete/{imgName}/{userID}")
    @ApiOperation(value = "Deletes image.", notes = "Deletes image from AWS.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was deleted", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> deleteFile(@PathVariable String imgName, @PathVariable Integer userID) throws Exception {
        return new ResponseEntity<>(imageService.deleteImage(imgName, userID), HttpStatus.OK);
    }

//    View Image

//    UPDATE IMAGE METADATA
//    @PutMapping(
//            path = "/image/updateMetadata/{imgOldName}/{imgNewName}/{userID}")
//    @ApiOperation(value = "Updates image metadata.", notes = "Updates image metadata.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Image metadata updated", response = Response.class),
//            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
//            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
//    })
//    public ResponseEntity<String> updateMetadata(@PathVariable String imgOldName, @PathVariable String imgNewName, @PathVariable Integer userID){
//        return new ResponseEntity<>(imageService.updateMetadata(userID,imgOldName,imgNewName), HttpStatus.OK);
//    }

//  DOWNLOAD IMAGE
    @GetMapping(
            path = "/image/download/{imgName}/{userID}")
    @ApiOperation(value = "Downloads image to user's PC.", notes = "Downloads image from AWS to user's PC.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image downloaded", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<byte[]> downloadImage(@PathVariable String imgName, @PathVariable Integer userID) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = imageService.downloadImage(imgName, userID);

        return ResponseEntity.ok()
                .contentType(contentType(imgName))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + imgName + "\"")
                .body(byteArrayOutputStream.toByteArray());
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

