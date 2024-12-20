//package com.LMS.userManagement.util;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Objects;
//
//@Component
//public class AWSUtil {
//
//    String serverPath="https://krays-lms-s3.s3.ap-south-1.amazonaws.com/";
//
//    public File convertMultiPartToFile(MultipartFile file) throws IOException {
//        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
//        file.getInputStream();
//        FileOutputStream fos = new FileOutputStream(serverPath + convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convFile;
//    }
//
//
//}
