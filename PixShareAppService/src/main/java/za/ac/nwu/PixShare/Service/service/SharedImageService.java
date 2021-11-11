package za.ac.nwu.PixShare.Service.service;

public interface SharedImageService {

    String shareImage(Integer imgID, Integer sharerID, Integer sharedID, String imgName) throws Exception;

    String deleteSharedImage(Integer sharedImgID, String imgName, Integer sharedID) throws Exception;

}
