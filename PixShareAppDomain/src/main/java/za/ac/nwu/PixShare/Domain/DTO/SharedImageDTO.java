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
    private String imgName;
    private Integer userIDShared;
    private Integer userIDSharer;
    private Integer imageID;

    public SharedImageDTO() {
    }

    public SharedImageDTO(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    public SharedImageDTO(Integer sharedImageID, String imgName, Integer userIDShared, Integer userIDSharer, Integer imageID) {
        this.sharedImageID = sharedImageID;
        this.imgName = imgName;
        this.userIDShared = userIDShared;
        this.userIDSharer = userIDSharer;
        this.imageID = imageID;
    }

    public SharedImageDTO(SharedImage sharedImage) {
        this.setSharedImageID(sharedImage.getSharedImageID());
        this.setImgName(sharedImage.getImgName());
        if (null != sharedImage.getUserIDShared()){
            this.userIDShared = sharedImage.getUserIDShared().getUserID();
        }
        if (null != sharedImage.getUserIDSharer()){
            this.userIDSharer = sharedImage.getUserIDSharer().getUserID();
        }
        if (null != sharedImage.getImageID()){
            this.imageID = sharedImage.getImageID().getImageID();
        }
    }

    public SharedImageDTO(SharedImageDTO sharedImageDto) {
        this.setSharedImageID(sharedImageDto.getSharedImageID());
        this.setImgName(sharedImageDto.getImgName());
        if (null != sharedImageDto.getUserIDShared()){
            this.userIDShared = sharedImageDto.getUserIDShared();
        }
        if (null != sharedImageDto.getUserIDSharer()){
            this.userIDSharer = sharedImageDto.getUserIDSharer();
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

    @ApiModelProperty(position = 2,
            value = "String imgName",
            name = "imgName",
            notes = "Identifies the image",
            dataType = "java.lang.String",
            example = "Caitlyn.png",
            required = true)


    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @ApiModelProperty(position = 3,
            value = "SharedImage userIDSharer",
            name = "userID",
            notes = "Identifies the user who shares the image",
            dataType = "java.lang.Integer",
            example = "3",
            required = true)

    public Integer getUserIDShared() {
        return userIDShared;
    }

    public void setUserIDShared(Integer userIDShared) {
        this.userIDShared = userIDShared;
    }

    @ApiModelProperty(position = 4,
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




    @JsonIgnore
    public SharedImage getSharedImage(){
        return new SharedImage(getSharedImageID(),getImgName(), getUserIDShared(), getUserIDSharer(), getImageID());
    }

    @JsonIgnore
    public SharedImageDTO getSharedImageDTO(){
        return new SharedImageDTO(getSharedImageID(), getImgName(), getUserIDShared(), getUserIDSharer(), getImageID());
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
        return Objects.equals(sharedImageID, that.sharedImageID) && Objects.equals(imgName, that.imgName) && Objects.equals(userIDShared, that.userIDShared) && Objects.equals(userIDSharer, that.userIDSharer) && Objects.equals(imageID, that.imageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sharedImageID, imgName, userIDShared, userIDSharer, imageID);
    }

    @Override
    public String toString() {
        return "SharedImageDTO{" +
                "sharedImageID=" + sharedImageID +
                ", imgName='" + imgName + '\'' +
                ", userIDShared=" + userIDShared +
                ", userIDSharer=" + userIDSharer +
                ", imageID=" + imageID +
                '}';
    }
}
