package za.ac.nwu.PixShare.Domain.persistence;

public enum BucketName {
// Set up bucket
    IMAGE("pixshare");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
