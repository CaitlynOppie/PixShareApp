package za.ac.nwu.PixShare.Domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema = "pixsharedb",name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "imageID")
    private Integer imageID;

    @Column(name = "link")
    private String link;

    @Column(name = "img_name")
    private String name;

    @Column(name = "size")
    private double size;

    @Column(name = "img_date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    @JsonBackReference
    private User userID;

    @OneToMany(targetEntity = SharedImage.class, fetch = FetchType.LAZY, mappedBy = "imageID")
    @JsonManagedReference
    private Set<SharedImage> sharedImage;

    public Image() {
    }

    public Image(String link) {
        this.link = link;
    }

    public Image(Integer imageID) {
        this.imageID = imageID;
    }

    public Image(String date, String link, String name, double size,  Integer userID) {
        this.link = link;
        this.name = name;
        this.size = size;
        this.date = date;
        this.userID = new User(userID);
    }

    public Image(String link, User userID) {
        this.link = link;
        this.userID = userID;
    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public String getLink() {
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
        return Double.compare(image.size, size) == 0 && Objects.equals(imageID, image.imageID) && Objects.equals(link, image.link) && Objects.equals(name, image.name) && Objects.equals(date, image.date) && Objects.equals(userID, image.userID) && Objects.equals(sharedImage, image.sharedImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageID, link, name, size, date, userID, sharedImage);
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageID=" + imageID +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", date='" + date + '\'' +
                ", userID=" + userID +
                ", sharedImage=" + sharedImage +
                '}';
    }
}
