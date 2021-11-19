package za.ac.nwu.PixShare.Service.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;
import za.ac.nwu.PixShare.Domain.persistence.BucketName;
import za.ac.nwu.PixShare.Repo.persistence.UserRepository;
import za.ac.nwu.PixShare.Service.service.UserService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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
    public UserDTO addUser(UserDTO userDTO) throws Exception {
        try{
            LOGGER.info("The input is {}", userDTO);
            if(null == userDTO.getFirstName()){
                userDTO.setUserID(0);
                userDTO.setFirstName("Name");
                userDTO.setLastName("Surname");
                userDTO.setEmail("email@gmail.com");
                userDTO.setPassword("123456789");
            }
            String password = userDTO.getPassword();
            String encryptedPassword = passwordEncoder.encode(password);
            userDTO.setPassword(encryptedPassword);
            userRepository.save(userDTO.getUser());
//            delete images from s3 folder
            LOGGER.info("The output object is {}", userDTO.getUser());
            return userDTO;
        }catch (Exception e){
            throw new Exception("User could not be added",e);
        }

    }

    @Override
    public Boolean checkUserExists(Integer uID) throws Exception {
        try{

            Boolean userExists = false;

            List<Integer> users = userRepository.getAllUserID();
            LOGGER.info("The users are {}", users);
            for(Integer user: users){
                if(Objects.equals(uID, user)){
                    userExists = true;
                }
            }
            LOGGER.info("The output is {}", userExists);
            return userExists;
        }catch(Exception e){
            throw new Exception("Users could not be obtained",e);
        }

    }

    @Override
    public Integer getID(String email) throws Exception {
        try{
            return userRepository.getUserID(email);
        }catch (Exception e){
            throw new Exception("Users could not be obtained",e);
        }
    }

    @Override
    public Boolean userValid(String email, String password) throws Exception {
        try{
            LOGGER.info("The email is {}, and the password is {}", email,password);
            Boolean valid;
            String validPW = userRepository.getPassword(email);
            String encryptPassword = passwordEncoder.encode(password);
            LOGGER.info("The valid password is {}, and the provided password is {}", validPW,encryptPassword);
            if(validPW == encryptPassword){
                valid = true;
            }else
            {
                valid = false;
            }
            LOGGER.info("The output is {}", valid);
            return valid;
        }catch (Exception ex){
            throw new Exception("User credentials incorrect");
        }
    }


}
