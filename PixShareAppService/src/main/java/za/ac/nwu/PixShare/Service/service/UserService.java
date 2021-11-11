package za.ac.nwu.PixShare.Service.service;

import org.springframework.stereotype.Service;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;

@Service
public interface UserService {

    String addUser(UserDTO userDTO) throws Exception;

    String deleteUser(Integer userID) throws Exception;

    String changePassword(String email, String newPassword) throws Exception;
}
