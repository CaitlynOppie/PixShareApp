package za.ac.nwu.PixShare.Repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.PixShare.Domain.DTO.ImageDTO;
import za.ac.nwu.PixShare.Domain.persistence.Image;
import za.ac.nwu.PixShare.Domain.persistence.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    void deleteByLink(String imgLink);

    @Query(value = "SELECT i.name FROM Image i WHERE i.imageID = :imgID")
    String getImageName(Integer imgID);

    List<ImageDTO> findAllByUserID(User userID);
}
