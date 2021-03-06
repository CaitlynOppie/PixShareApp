package za.ac.nwu.PixShare.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.PixShare.Domain.persistence.Image;
import za.ac.nwu.PixShare.Domain.persistence.User;

import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "Image", description = "A DTO that represents the Image")
public class ImageDTO {

    private Integer imageID;
    private String link;
    private String name;
    private double size;
    private String date;
    private Integer userID;

    public ImageDTO() {
    }

    public ImageDTO(Integer imageID, String link, String name, double size, String date, Integer userID) {
        this.imageID = imageID;
        this.link = link;
        this.name = name;
        this.size = size;
        this.date = date;
        this.userID = userID;
    }

    public ImageDTO(String link, String name, double size, String date, Integer userID) {
        this.link = link;
        this.name = name;
        this.size = size;
        this.date = date;
        this.userID = userID;
    }

    public ImageDTO(String link) {
        this.link = link;
    }

    public ImageDTO(Integer imageID) {
        this.imageID = imageID;
    }

    public ImageDTO(Image image) {
        this.setImageID(image.getImageID());
        this.setName(image.getName());
        this.setSize(image.getSize());
        this.setLink(image.getLink());
        this.setDate(image.getDate());
        if (null != image.getUserID()) {
            this.userID = image.getUserID().getUserID();
        }
    }

    public ImageDTO(ImageDTO imageDto) {
        this.setImageID(imageDto.getImageID());
        this.setName(imageDto.getName());
        this.setSize(imageDto.getSize());
        this.setLink(imageDto.getLink());
        this.setDate(imageDto.getDate());
        if (null != imageDto.getUserID()) {
            this.userID = imageDto.getUserID();
        }
    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    @ApiModelProperty(position = 2,
            value = "Image link",
            name = "link",
            notes = "Identifies the path of the image",
            dataType = "java.lang.String",
            example = "pixshare/6/Caitlyn.png",
            required = true)

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @ApiModelProperty(position = 3,
            value = "Image name",
            name = "name",
            notes = "Identifies the name of the image",
            dataType = "java.lang.String",
            example = "IMG-20211018",
            required = true)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(position = 4,
            value = "Image size",
            name = "size",
            notes = "Identifies the size of the image",
            dataType = "java.lang.double",
            example = "25",
            required = true)

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @ApiModelProperty(position = 5,
            value = "Image date",
            name = "date",
            notes = "Identifies the date the image was taken or created",
            dataType = "java.time.LocalDate",
            example = "2021-10-18",
            required = true)

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ApiModelProperty(position = 6,
            value = "Image userID",
            name = "userID",
            notes = "Identifies the user who originally uploaded the image",
            dataType = "java.lang.Integer",
            example = "01",
            required = true)

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @JsonIgnore
    public Image getImage() {
        return new Image(getDate(), getLink(), getName(), getSize(), getUserID());
    }

    @JsonIgnore
    public ImageDTO getImageDTO() {
        return new ImageDTO(getLink(), getName(), getSize(), getDate(), getUserID());
    }

    @JsonIgnore
    public Image getImgID() {
        return new Image(imageID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDTO imageDTO = (ImageDTO) o;
        return Double.compare(imageDTO.size, size) == 0 && Objects.equals(imageID, imageDTO.imageID) && Objects.equals(link, imageDTO.link) && Objects.equals(name, imageDTO.name) && Objects.equals(date, imageDTO.date) && Objects.equals(userID, imageDTO.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageID, link, name, size, date, userID);
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "imageID=" + imageID +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", date='" + date + '\'' +
                ", userID=" + userID +
                '}';
    }
}


