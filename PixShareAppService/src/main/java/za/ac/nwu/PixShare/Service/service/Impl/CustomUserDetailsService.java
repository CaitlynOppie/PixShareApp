package za.ac.nwu.PixShare.Service.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;
import za.ac.nwu.PixShare.Repo.persistence.UserRepository;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO userDTO = userRepository.findByEmail(email);
        CustomUserDetails userDetails = null;
        if(userDTO != null){
            userDetails = new CustomUserDetails();
            userDetails.setUserDTO(userDTO);
        }
        else{
            throw new UsernameNotFoundException("User with email: " + email + " does not exist");
        }

        return userDetails;
    }
}
