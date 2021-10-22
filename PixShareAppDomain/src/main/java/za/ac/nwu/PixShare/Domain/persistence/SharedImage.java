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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User userID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE_ID")
    @JsonBackReference
    private Image imageID;

    public SharedImage() {
    }

    public SharedImage(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    public SharedImage(Integer sharedImageID, User userID, Image imageID) {
        this.sharedImageID = sharedImageID;
        this.userID = userID;
        this.imageID = imageID;
    }

    public SharedImage(Integer sharedImageID, Integer userID, Integer imageID) {
        this.sharedImageID = sharedImageID;
        this.userID = new User(userID);
        this.imageID = new Image(imageID);
    }

    public Integer getSharedImageID() {
        return sharedImageID;
    }

    public void setSharedImageID(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
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
        return Objects.equals(sharedImageID, that.sharedImageID) && Objects.equals(userID, that.userID) && Objects.equals(imageID, that.imageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sharedImageID, userID, imageID);
    }

    @Override
    public String toString() {
        return "SharedImage{" +
                "sharedImageID=" + sharedImageID +
                ", userID=" + userID +
                ", imageID=" + imageID +
                '}';
    }
}

