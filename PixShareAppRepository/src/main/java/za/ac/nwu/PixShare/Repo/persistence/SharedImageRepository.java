package za.ac.nwu.PixShare.Repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.PixShare.Domain.persistence.SharedImage;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SharedImageRepository extends JpaRepository<SharedImage, Integer> {

    @Query(value = "SELECT s.sharedImageID FROM SharedImage s WHERE s.imageID = :imgID AND s.userIDShared.userID = :sharedID AND s.userIDSharer.userID = :sharerID")
    Integer getSharedID(Integer imgID, Integer sharerID, Integer sharedID);

//    @Query(value = "SELECT s.userIDShared FROM SharedImage s WHERE s.imgName = :imgName AND s.userIDSharer.userID = :sharerID")
//    List<Integer> getSharedUserID(String imgName, Integer sharerID);

//    @Query(value = "SELECT s.sharedImageID FROM SharedImage s WHERE s.imgName = :imgName AND s.userIDSharer.userID = :sharerID")
//    List<Integer> getSharedImageID(String imgName, Integer sharerID);
}
