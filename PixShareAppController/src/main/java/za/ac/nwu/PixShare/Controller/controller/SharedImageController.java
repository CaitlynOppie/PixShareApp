package za.ac.nwu.PixShare.Controller.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.persistence.Image;
import za.ac.nwu.PixShare.Domain.service.Response;
import za.ac.nwu.PixShare.Service.service.SharedImageService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000")
public class SharedImageController {

    private final SharedImageService sharedImageService;

    @Autowired
    public SharedImageController(SharedImageService sharedImageService) {
        this.sharedImageService = sharedImageService;
    }

    @PostMapping(
            path = "/sharedImage/shareImage")
    @ApiOperation(value = "Shares an image with another user.", notes = "Shares an image with another user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image shared", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> shareImage(
            @RequestParam Integer sharedUserID,
            @RequestParam Integer imageid,
            @RequestParam String date,
            @RequestParam String link,
            @RequestParam String name,
            @RequestParam double size,
            @RequestParam Integer user
    ) throws Exception {
        return new ResponseEntity<>(sharedImageService.shareImage(new ImageDTO(imageid, link, name, size, date, user), sharedUserID), HttpStatus.OK);
    }

    //  DELETE IMAGE
    @DeleteMapping(
            path = "/image/delete/{sharedImgID}/{sharedUserID}/{imgName}")
    @ApiOperation(value = "Deletes shared image.", notes = "Deletes image from AWS.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was deleted", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> deleteSharedImage(
            @PathVariable("sharedImgID") Integer sharedImgID,
            @PathVariable("sharedUserID") Integer sharedUserID,
            @PathVariable("imgName") String imgName) throws Exception {
        return new ResponseEntity<>(sharedImageService.deleteSharedImage(sharedImgID,imgName, sharedUserID), HttpStatus.OK);
    }
}
