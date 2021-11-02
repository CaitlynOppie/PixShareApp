package za.ac.nwu.PixShare.Service.service;

import za.ac.nwu.PixShare.Domain.DTO.UserDTO;

public interface UserService {

    String addUser(UserDTO userDTO) throws Exception;
}
