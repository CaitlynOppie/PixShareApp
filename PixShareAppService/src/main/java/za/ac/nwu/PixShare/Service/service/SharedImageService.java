package za.ac.nwu.PixShare.Service.service;

public interface SharedImageService {

    String shareImage(String imgLink, Integer sharerID, Integer sharedID) throws Exception;
}
