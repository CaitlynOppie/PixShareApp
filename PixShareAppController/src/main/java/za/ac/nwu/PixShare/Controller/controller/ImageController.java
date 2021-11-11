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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000" )
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


    @GetMapping(
            path = "/image/view/{imgName}/{userID}")
    @ApiOperation(value = "Views image of a user.", notes = "Views image of a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image viewed", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<byte[]> viewImage(@PathVariable String imgName, @PathVariable Integer userID) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = imageService.downloadImage(imgName, userID);

        return ResponseEntity.ok()
                .contentType(contentType(imgName))
                .body(byteArrayOutputStream.toByteArray());
    }

    @GetMapping(
            path = "/image/viewAll/{userID}")
    @ApiOperation(value = "Views all images of a user.", notes = "Views all images of a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Images views", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
//    public List<ResponseEntity<byte[]>> viewAllImage(@RequestParam Integer userID) throws Exception {
//        List<String> names = imageService.listAllImages(userID);
//        List<ResponseEntity<byte[]>> response = new ArrayList<>();
//        for(String name: names)
//        {
//            ByteArrayOutputStream byteArrayOutputStream = imageService.downloadImage(name, userID);
//
//            response.add(ResponseEntity.ok()
//                    .contentType(contentType(name))
//                    .body(byteArrayOutputStream.toByteArray()));
//        }
//        return response;
//    }
    public List<String> viewAllImage(@PathVariable Integer userID) throws Exception {
        List<String> list = imageService.listAllImages(userID);
        List<String> names = new ArrayList<>();
        for(String name: list){
            int slash = name.indexOf("/");
            names.add(name.substring(slash+1));
        }
        return names;
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

