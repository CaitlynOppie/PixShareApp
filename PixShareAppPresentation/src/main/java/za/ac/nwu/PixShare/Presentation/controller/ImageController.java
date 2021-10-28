package za.ac.nwu.PixShare.Presentation.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.service.Response;
import za.ac.nwu.PixShare.Logic.service.ImageService;
import za.ac.nwu.PixShare.Logic.service.UserService;

import java.sql.SQLException;
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
            path = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Uploads new image.", notes = "Uploads a new Image to aws along with metadata to DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was uploaded", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<Response<ImageDTO>> create(
            @ApiParam(value = "Request body to upload Image.", required = true)
            @RequestParam("file") MultipartFile file,
            @RequestBody ImageDTO image) throws SQLException {
        imageService.uploadImage(file,image);
        Response<ImageDTO> response = new Response<>(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
