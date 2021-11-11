package za.ac.nwu.PixShare.Service.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;
import za.ac.nwu.PixShare.Domain.persistence.BucketName;
import za.ac.nwu.PixShare.Repo.persistence.UserRepository;
import za.ac.nwu.PixShare.Service.service.UserService;

import javax.transaction.Transactional;

@Transactional
@Component("UserService")
public class UserServiceImpl implements UserService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final AmazonS3 s3;
    private String bucketName = BucketName.IMAGE.getBucketName();
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(AmazonS3 s3, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.s3 = s3;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //    ADD NEW USER
    @Override
    public String addUser(UserDTO userDTO) throws Exception {
        try{
            LOGGER.info("The input is {}", userDTO);
            String password = userDTO.getPassword();
            String encryptedPassword = passwordEncoder.encode(password);
            userDTO.setPassword(encryptedPassword);
            userRepository.save(userDTO.getUser());
//            delete images from s3 folder
            LOGGER.info("The output object is {}", userDTO.getUser());
            return "Welcome to the PixShare family " + userDTO.getFirstName();
        }catch (Exception e){
            throw new Exception("User could not be added",e);
        }

    }

    @Override
    public String deleteUser(Integer userID) throws Exception {
        try {
            LOGGER.info("The provided userID is {}", userID);
            userRepository.deleteById(userID);
            return "Sorry to see you go.";
        }catch (Exception e) {
            throw new Exception("User profile could not be deleted", e);
        }
    }

    @Override
    public String changePassword(String email, String newPassword) throws Exception {
        try {
            LOGGER.info("The email for the user is {}", email);
            String encryptedPassword = passwordEncoder.encode(newPassword);
            userRepository.changePassword(encryptedPassword,email);
            return "Password for " + email + " has successfully be changed";
        }catch (Exception e) {
            throw new Exception("User password could not be changed", e);
        }
    }


}
