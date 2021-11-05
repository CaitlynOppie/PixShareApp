package za.ac.nwu.PixShare.Controller.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;
import za.ac.nwu.PixShare.Domain.service.Response;
import za.ac.nwu.PixShare.Service.service.UserService;

@RestController
@RequestMapping("")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(userService.addUser(userDTO), HttpStatus.OK);
    }

//    @GetMapping(
//            path = "/"
//    )
//    public String login() {
//        return "User logged in";
//    }

    @DeleteMapping(
            path = "/user/delete/{userID}")
    @ApiOperation(value = "Deletes the users profile.", notes = "Deletes user profile.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User profile was deleted", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> deleteUser(@PathVariable Integer userID) throws Exception {
        return new ResponseEntity<>(userService.deleteUser(userID), HttpStatus.OK);
    }

    @PutMapping(
            path = "/user/changePassword/{email}/{password}")
    @ApiOperation(value = "Changes the password for the user's profile.", notes = "Changes the password for the user's profile.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Password changed", response = Response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class)
    })
    public ResponseEntity<String> changePassword(@PathVariable String email, @PathVariable String password) throws Exception {
        return new ResponseEntity<>(userService.changePassword(email,password), HttpStatus.OK);
    }
}
