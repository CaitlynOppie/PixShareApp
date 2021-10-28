package za.ac.nwu.PixShare.Domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema = "pixsharedb",name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMAGE_ID")
    private Integer imageID;

    @Column(name = "IMAGE_LINK")
    private String link;

    @Column(name = "IMAGE_NAME")
    private String name;

    @Column(name = "IMAGE_SIZE")
    private double size;

    @Column(name = "IMAGE_LOCATION")
    private String location;

    @Column(name = "IMAGE_DATE")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User userID;

    @OneToMany(targetEntity = SharedImage.class, fetch = FetchType.LAZY, mappedBy = "userID")
    @JsonManagedReference
    private Set<SharedImage> sharedImage;

    public Image() {
    }

    public Image(Integer imageID) {
        this.imageID = imageID;
    }

    public Image(Integer imageID, String link, String name, double size, String location, String date, User userID) {
        this.imageID = imageID;
        this.link = link;
        this.name = name;
        this.size = size;
        this.location = location;
        this.date = date;
        this.userID = userID;
    }

    public Image(Integer imageID, String link, String name, double size, String location, String date, Integer userID) {
        this.imageID = imageID;
        this.link = link;
        this.name = name;
        this.size = size;
        this.location = location;
        this.date = date;
        this.userID = new User(userID);
    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public String getlink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(imageID, image.imageID) && Objects.equals(link, image.link) && Objects.equals(name, image.name) && Objects.equals(size, image.size) && Objects.equals(location, image.location) && Objects.equals(date, image.date) && Objects.equals(userID, image.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageID, link, name, size, location, date, userID);
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageID=" + imageID +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", userID=" + userID +
                '}';
    }
}
