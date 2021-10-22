package za.ac.nwu.PixShare.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.PixShare.Domain.persistence.SharedImage;

import java.util.Objects;

@ApiModel(value = "SharedImage", description = "A DTO that represents the Shared Images")
public class SharedImageDTO {

    private Integer sharedImageID;
    private Integer userID;
    private Integer imageID;

    public SharedImageDTO() {
    }

    public SharedImageDTO(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    public SharedImageDTO(Integer sharedImageID, Integer userID, Integer imageID) {
        this.sharedImageID = sharedImageID;
        this.userID = userID;
        this.imageID = imageID;
    }

    public SharedImageDTO(SharedImage sharedImage) {
        this.setSharedImageID(sharedImage.getSharedImageID());
        if (null != sharedImage.getUserID()){
            this.userID = sharedImage.getUserID().getUserID();
        }
        if (null != sharedImage.getImageID()){
            this.imageID = sharedImage.getImageID().getImageID();
        }
    }

    public SharedImageDTO(SharedImageDTO sharedImageDto) {
        this.setSharedImageID(sharedImageDto.getSharedImageID());
        if (null != sharedImageDto.getUserID()){
            this.userID = sharedImageDto.getUserID();
        }
        if (null != sharedImageDto.getImageID()){
            this.imageID = sharedImageDto.getImageID();
        }
    }

    public Integer getSharedImageID() {
        return sharedImageID;
    }

    public void setSharedImageID(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    @ApiModelProperty(position = 1,
            value = "SharedImage userID",
            name = "userID",
            notes = "Identifies the user who the image is shared with",
            dataType = "java.lang.Integer",
            example = "01",
            required = true)

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @ApiModelProperty(position = 1,
            value = "SharedImage imageID",
            name = "imageID",
            notes = "Identifies the image that has been shared with another user",
            dataType = "java.lang.Integer",
            example = "01",
            required = true)

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }


    @JsonIgnore
    public SharedImage getSharedImage(){
        return new SharedImage(getSharedImageID(), getUserID(), getImageID());
    }

    @JsonIgnore
    public SharedImageDTO getSharedImageDTO(){
        return new SharedImageDTO(getSharedImageID(), getUserID(), getImageID());
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
        return Objects.equals(sharedImageID, that.sharedImageID) && Objects.equals(userID, that.userID) && Objects.equals(imageID, that.imageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sharedImageID, userID, imageID);
    }

    @Override
    public String toString() {
        return "SharedImageDTO{" +
                "sharedImageID=" + sharedImageID +
                ", userID=" + userID +
                ", imageID=" + imageID +
                '}';
    }
}
