package com.LMS.userManagement.awsS3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;


@Configuration
public class AWSstorageConfig {


    private String accessKey="AKIAXQS3Y3Q367JXJIVP";

    private String secretKey="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";

    // @Value("${aws.region}")
    private String region;

    AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKey,secretKey);

    @Bean
    public S3Client s3Client() {
        S3Client client = S3Client.builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(() -> awsBasicCredentials)
                .build();
        return client;
    }
}
