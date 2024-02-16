package com.LMS.userManagement.controller;

import com.LMS.userManagement.awsS3.AWSS3Service;
import com.LMS.userManagement.util.AWSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AwsS3Controller {


    @Autowired
    AWSS3Service awss3Service;

    @Autowired
    S3Client s3Client;
    private final AWSUtil awsUtil;
    private String awsUrl="https://krays-lms-s3.s3.ap-south-1.amazonaws.com/";


    public AwsS3Controller(AWSUtil awsUtil) {
        this.awsUtil = awsUtil;
    }

    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public  String saveFile(@RequestPart MultipartFile file,@RequestParam String courseId) throws IOException {
       String key="LmsCourse/"+courseId+"/"+UUID.randomUUID().toString();
        awss3Service.putObject(key,file);
        return awsUrl+key;
    }

    @GetMapping(value = "/fetchFile",produces ="video/mp4")
    public ResponseEntity<?> getFile(@RequestHeader String key){
      byte[] file=  awss3Service.getObject(key);
      if(file==null){
          return ResponseEntity.ok("No Files Found");
      }
      return ResponseEntity.ok(file);
    }
//
//    @PostMapping("/saveFileToS3")
//    public  String saveFileToS3(@RequestPart MultipartFile file,@RequestHeader String key) throws IOException {
//        // Convert MultipartFile to File
//        File convertedFile = awsUtil.convertMultiPartToFile(file);
//
//        ObjectMetadata meta = new ObjectMetadata();
//        meta.setContentType("video/mp4");
//        meta.setContentLength(file.getSize())
//
//        s3Client.putObject(PutObjectRequest.builder()
//                .bucket("krays-lms-s3")
//                .key(key)
//                .build(), convertedFile.toPath());
//
//        // Cleanup: Delete the temporary file
//        convertedFile.delete();
//        return "file successfully uploaded Key ::"+key;
//    }



}
