package za.ac.nwu.PixShare.Repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.PixShare.Domain.persistence.Image;

import javax.transaction.Transactional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

//    @Transactional
//    @Modifying
//    @Query(value = "" +
//            "UPDATE Image i SET i.name = :imgName, i.date = :date, i.link = :newLink" +
//            " WHERE i.link = :oldLink")
//    void updateMetadata(String oldLink, String newLink, String name, String date);
}
