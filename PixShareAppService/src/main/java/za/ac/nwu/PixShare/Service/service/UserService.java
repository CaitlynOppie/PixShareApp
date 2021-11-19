package za.ac.nwu.PixShare.Service.service;

import org.springframework.stereotype.Service;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;

import java.sql.SQLException;

@Service
public interface UserService {

    UserDTO addUser(UserDTO userDTO) throws Exception;

    Boolean checkUserExists(Integer uID) throws Exception;

    Integer getID(String email) throws Exception;

    Boolean userValid(String email, String password) throws Exception;
}
