package za.ac.nwu.PixShare.Controller.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.PixShare.Domain.service.Response;
import za.ac.nwu.PixShare.Service.service.SharedImageService;

@RestController
@RequestMapping("")
public class SharedImageController {

    private final SharedImageService sharedImageService;

    @Autowired
    public SharedImageController(SharedImageService sharedImageService) {
        this.sharedImageService = sharedImageService;
    }

    @PostMapping(
            path = "/sharedImage/shareImage/{imgLink}/{sharerUserID}/{sharedUserID}")
    @ApiOperation(value = "Shares an image with another user.", notes = "Shares an image with another user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image shared", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> shareImage(@PathVariable("imgLink") String imgLink, @PathVariable("sharerUserID") Integer sharerUserID, @PathVariable("sharedUserID") Integer sharedUserID) throws Exception {
        return new ResponseEntity<>(sharedImageService.shareImage(imgLink,sharerUserID, sharedUserID), HttpStatus.OK);
    }
}
