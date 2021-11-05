package za.ac.nwu.PixShare.Repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;
import za.ac.nwu.PixShare.Domain.persistence.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    UserDTO findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "" +
            "UPDATE User u SET u.password = :newPassword" +
            " WHERE u.email = :email")
    void changePassword(String newPassword, String email);

}
