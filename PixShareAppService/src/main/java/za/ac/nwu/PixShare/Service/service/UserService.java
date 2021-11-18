package za.ac.nwu.PixShare.Service.service;

import org.springframework.stereotype.Service;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;

import java.sql.SQLException;

@Service
public interface UserService {

    String addUser(UserDTO userDTO) throws Exception;

    Boolean getAllUsers(Integer uID) throws Exception;

    Integer getUserID(String email) throws Exception;

    Boolean userValid(String email, String password) throws Exception;
}
