package za.ac.nwu.PixShare.Service.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;
import za.ac.nwu.PixShare.Repo.persistence.ImageRepository;
import za.ac.nwu.PixShare.Repo.persistence.SharedImageRepository;
import za.ac.nwu.PixShare.Repo.persistence.UserRepository;
import za.ac.nwu.PixShare.Service.service.UserService;

import javax.transaction.Transactional;

@Transactional
@Component("UserService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //    ADD NEW USER
    @Override
    public String addUser(UserDTO userDTO) throws Exception {
        try{
            String password = userDTO.getPassword();
            String encryptedPassword = passwordEncoder.encode(password);
            userDTO.setPassword(encryptedPassword);
            userRepository.save(userDTO.getUser());
            return "Welcome to the PixShare family " + userDTO.getFirstName();
        }catch (Exception e){
            throw new Exception("User could not be added",e);
        }

    }

    @Override
    public String deleteUser(Integer userID) throws Exception {
        try {
            userRepository.deleteById(userID);
            return "Sorry to see you go.";
        }catch (Exception e) {
            throw new Exception("User profile could not be deleted", e);
        }
    }

    @Override
    public String changePassword(String email, String newPassword) throws Exception {
        try {
            String encryptedPassword = passwordEncoder.encode(newPassword);
            userRepository.changePassword(encryptedPassword,email);
            return "Password for " + email + " has successfully be changed";
        }catch (Exception e) {
            throw new Exception("User password could not be changed", e);
        }
    }


}
