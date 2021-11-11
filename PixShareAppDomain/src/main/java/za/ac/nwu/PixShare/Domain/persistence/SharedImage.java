package za.ac.nwu.PixShare.Domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "pixsharedb",name = "sharedimage")
public class SharedImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SHARED_IMAGE_ID")
    private Integer sharedImageID;

    @Column(name = "IMAGE_NAME")
    private String imgName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID_SHARER")
    @JsonBackReference
    private User userIDSharer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID_SHARED")
    @JsonBackReference
    private User userIDShared;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE_ID")
    @JsonBackReference
    private Image imageID;

    public SharedImage() {
    }

    public SharedImage(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    public SharedImage(Integer sharedImageID, String imgName, User userIDSharer, User userIDShared, Image imageID) {
        this.sharedImageID = sharedImageID;
        this.imgName = imgName;
        this.userIDSharer = userIDSharer;
        this.userIDShared = userIDShared;
        this.imageID = imageID;
    }

    public SharedImage(Integer sharedImageID, String imgName, Integer userIDShared, Integer userIDSharer, Integer imageID) {
        this.sharedImageID = sharedImageID;
        this.imgName = imgName;
        this.userIDShared = new User(userIDShared);
        this.userIDSharer = new User(userIDSharer);
        this.imageID = new Image(imageID);
    }

    public SharedImage(String imgName, Integer userIDShared, Integer userIDSharer, Integer imageID) {
        this.imgName = imgName;
        this.userIDShared = new User(userIDShared);
        this.userIDSharer = new User(userIDSharer);
        this.imageID = new Image(imageID);
    }

    public SharedImage(String imgName, User userIDSharer, User userIDShared, Image imageID) {
        this.imgName = imgName;
        this.userIDSharer = userIDSharer;
        this.userIDShared = userIDShared;
        this.imageID = imageID;
    }

    public Integer getSharedImageID() {
        return sharedImageID;
    }

    public void setSharedImageID(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public User getUserIDSharer() {
        return userIDSharer;
    }

    public void setUserIDSharer(User userIDSharer) {
        this.userIDSharer = userIDSharer;
    }

    public User getUserIDShared() {
        return userIDShared;
    }

    public void setUserIDShared(User userIDShared) {
        this.userIDShared = userIDShared;
    }

    public Image getImageID() {
        return imageID;
    }

    public void setImageID(Image imageID) {
        this.imageID = imageID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedImage that = (SharedImage) o;
        return Objects.equals(sharedImageID, that.sharedImageID) && Objects.equals(imgName, that.imgName) && Objects.equals(userIDSharer, that.userIDSharer) && Objects.equals(userIDShared, that.userIDShared) && Objects.equals(imageID, that.imageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sharedImageID, imgName, userIDSharer, userIDShared, imageID);
    }

    @Override
    public String toString() {
        return "SharedImage{" +
                "sharedImageID=" + sharedImageID +
                ", imgName='" + imgName + '\'' +
                ", userIDSharer=" + userIDSharer +
                ", userIDShared=" + userIDShared +
                ", imageID=" + imageID +
                '}';
    }
}



