package za.ac.nwu.PixShare.Controller.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;
import za.ac.nwu.PixShare.Domain.service.Response;
import za.ac.nwu.PixShare.Service.service.UserService;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(
            path = "/"
    )
    public String login() {
        return "login";
    }

    @PostMapping(
            path = "/user/add"
    )
    @ApiOperation(value = "Adds new user to the system.", notes = "Adds new user to the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was added", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> addUser(@ModelAttribute UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(userService.addUser(userDTO), HttpStatus.OK);
    }

    @GetMapping(
            path = "/user/exists/{userID}")
    @ApiOperation(value = "Checks if a user exists.", notes = "Checks if a user exists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All user IDs displayed", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<Response<Boolean>> checkUserExists(@PathVariable("userID") Integer userID) throws Exception {
        Boolean exists = userService.getAllUsers(userID);
        Response<Boolean> response = new Response<>(true,exists);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            path = "/user/userID/{email}")
    @ApiOperation(value = "Gets userID based on email provided.", notes = "Gets userID based on email provided.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User ID provided", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<Response<Integer>> getUserID(@PathVariable("email") String email) throws Exception {
        Integer userID = userService.getUserID(email);
        Response<Integer> response = new Response<>(true,userID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            path = "/user/validUser/{email}/{password}")
    @ApiOperation(value = "Checks if user is valid.", notes = "Checks if user is valid.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User validadted", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<Response<Boolean>> validUser(
            @PathVariable("email") String email,
            @PathVariable("password") String password) throws Exception {
        Boolean valid = userService.userValid(email,password);
        Response<Boolean> response = new Response<>(true,valid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
