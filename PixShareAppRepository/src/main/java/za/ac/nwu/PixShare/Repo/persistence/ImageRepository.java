package za.ac.nwu.PixShare.Repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.PixShare.Domain.persistence.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
