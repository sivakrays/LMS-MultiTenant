
package com.LMS.userManagement.awsS3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class AWSS3Service {

    @Autowired(required = true)
     S3Client s3Client;

    final String bucketName="krays-lms-s3";



    public void putObject(String key, MultipartFile file) throws IOException {

        PutObjectRequest objectRequest=PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();
        s3Client.putObject(objectRequest, RequestBody.fromBytes(file.getBytes()));

    }

    public byte[] getObject(String key){
        GetObjectRequest getObjectRequest=GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
      ResponseInputStream<GetObjectResponse> response
              = s3Client.getObject(getObjectRequest);

        try {
            byte[] bytes= response.readAllBytes();
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

