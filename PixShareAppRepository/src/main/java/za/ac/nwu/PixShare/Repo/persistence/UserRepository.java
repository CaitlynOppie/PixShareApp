package za.ac.nwu.PixShare.Repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.PixShare.Domain.DTO.UserDTO;
import za.ac.nwu.PixShare.Domain.persistence.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    UserDTO findByEmail(String email);
}
