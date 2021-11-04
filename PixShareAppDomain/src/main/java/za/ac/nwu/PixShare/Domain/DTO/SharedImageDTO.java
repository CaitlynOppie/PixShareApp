package za.ac.nwu.PixShare.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.PixShare.Domain.persistence.Image;
import za.ac.nwu.PixShare.Domain.persistence.SharedImage;
import za.ac.nwu.PixShare.Domain.persistence.User;

import java.util.Objects;

@ApiModel(value = "SharedImage", description = "A DTO that represents the Shared Images")
public class SharedImageDTO {

    private Integer sharedImageID;
    private Integer userIDShared;
    private Integer userIDSharer;
    private String link;

    public SharedImageDTO() {
    }

    public SharedImageDTO(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    public SharedImageDTO(Integer sharedImageID, Integer userIDShared, Integer userIDSharer, String link) {
        this.sharedImageID = sharedImageID;
        this.userIDShared = userIDShared;
        this.userIDSharer = userIDSharer;
        this.link = link;
    }

    public SharedImageDTO(SharedImage sharedImage) {
        this.setSharedImageID(sharedImage.getSharedImageID());
        if (null != sharedImage.getUserIDShared()){
            this.userIDShared = sharedImage.getUserIDShared().getUserID();
        }
        if (null != sharedImage.getUserIDSharer()){
            this.userIDSharer = sharedImage.getUserIDSharer().getUserID();
        }
        if (null != sharedImage.getLink()){
            this.link = sharedImage.getLink().getLink();
        }
    }

    public SharedImageDTO(SharedImageDTO sharedImageDto) {
        this.setSharedImageID(sharedImageDto.getSharedImageID());
        if (null != sharedImageDto.getUserIDShared()){
            this.userIDShared = sharedImageDto.getUserIDShared();
        }
        if (null != sharedImageDto.getUserIDSharer()){
            this.userIDSharer = sharedImageDto.getUserIDSharer();
        }
        if (null != sharedImageDto.getLink()){
            this.link = sharedImageDto.getLink();
        }
    }



    public Integer getSharedImageID() {
        return sharedImageID;
    }

    public void setSharedImageID(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    @ApiModelProperty(position = 1,
            value = "SharedImage userIDShared",
            name = "userID",
            notes = "Identifies the user who the image is shared with",
            dataType = "java.lang.Integer",
            example = "1",
            required = true)

    public Integer getUserIDShared() {
        return userIDShared;
    }

    public void setUserIDShared(Integer userIDShared) {
        this.userIDShared = userIDShared;
    }

    @ApiModelProperty(position = 2,
            value = "SharedImage userIDSharer",
            name = "userID",
            notes = "Identifies the user who shares the image",
            dataType = "java.lang.Integer",
            example = "3",
            required = true)

    public Integer getUserIDSharer() {
        return userIDSharer;
    }

    public void setUserIDSharer(Integer userIDSharer) {
        this.userIDSharer = userIDSharer;
    }


    @ApiModelProperty(position = 1,
            value = "SharedImage imageID",
            name = "imageID",
            notes = "Identifies the image that has been shared with another user",
            dataType = "java.lang.Integer",
            example = "01",
            required = true)

    public String getLink() {
        return link;
    }

    public void setLink(String imageID) {
        this.link = link;
    }


    @JsonIgnore
    public SharedImage getSharedImage(){
        return new SharedImage(getSharedImageID(), getUserIDShared(), getUserIDSharer(), getLink());
    }

    @JsonIgnore
    public SharedImageDTO getSharedImageDTO(){
        return new SharedImageDTO(getSharedImageID(), getUserIDShared(), getUserIDSharer(), getLink());
    }

    @JsonIgnore
    public SharedImage getSharedImgID(){
        return new SharedImage(getSharedImageID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedImageDTO that = (SharedImageDTO) o;
        return Objects.equals(sharedImageID, that.sharedImageID) && Objects.equals(userIDShared, that.userIDShared) && Objects.equals(userIDSharer, that.userIDSharer) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sharedImageID, userIDShared, userIDSharer, link);
    }

    @Override
    public String toString() {
        return "SharedImageDTO{" +
                "sharedImageID=" + sharedImageID +
                ", userIDShared=" + userIDShared +
                ", userIDSharer=" + userIDSharer +
                ", link='" + link + '\'' +
                '}';
    }
}
