package za.ac.nwu.PixShare.Translator.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSconfig {
    //    Config settings for AWS
    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIA5XWT7GHF573OC4VR",
                "VsJ/hR15almvRKZbZxhehlLniWezOyltB79VPxhh"
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
