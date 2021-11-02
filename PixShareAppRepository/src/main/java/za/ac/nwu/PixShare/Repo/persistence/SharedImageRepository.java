package za.ac.nwu.PixShare.Repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.PixShare.Domain.persistence.SharedImage;

@Repository
public interface SharedImageRepository extends JpaRepository<SharedImage, Integer> {
}
