package za.ac.nwu.PixShare.Repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.PixShare.Domain.persistence.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
