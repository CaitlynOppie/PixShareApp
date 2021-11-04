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
    @JoinColumn(name = "USER_ID_SHARER")
    @JsonBackReference
    private User userIDSharer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID_SHARED")
    @JsonBackReference
    private User userIDShared;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LINK")
    @JsonBackReference
    private Image link;

    public SharedImage() {
    }

    public SharedImage(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    public SharedImage(Integer sharedImageID, User userIDShared, User userIDSharer, Image link) {
        this.sharedImageID = sharedImageID;
        this.userIDShared = userIDShared;
        this.userIDSharer = userIDSharer;
        this.link = link;
    }

    public SharedImage(Integer sharedImageID, Integer userIDShared, Integer userIDSharer, String link) {
        this.sharedImageID = sharedImageID;
        this.userIDShared = new User(userIDShared);
        this.userIDSharer = new User(userIDSharer);
        this.link = new Image(link);
    }

    public Integer getSharedImageID() {
        return sharedImageID;
    }

    public void setSharedImageID(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
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

    public Image getLink() {
        return link;
    }

    public void setLink(Image link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedImage that = (SharedImage) o;
        return Objects.equals(sharedImageID, that.sharedImageID) && Objects.equals(userIDSharer, that.userIDSharer) && Objects.equals(userIDShared, that.userIDShared) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sharedImageID, userIDSharer, userIDShared, link);
    }

    @Override
    public String toString() {
        return "SharedImage{" +
                "sharedImageID=" + sharedImageID +
                ", userIDSharer=" + userIDSharer +
                ", userIDShared=" + userIDShared +
                ", link=" + link +
                '}';
    }
}



