package com.LMS.userManagement.service;

import com.LMS.userManagement.awsS3.AWSS3Service;
import com.LMS.userManagement.model.EduContent;
import com.LMS.userManagement.model.Home;
import com.LMS.userManagement.records.*;
import com.LMS.userManagement.repository.EduContentRepository;
import com.LMS.userManagement.repository.HomeRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import com.LMS.userManagement.mapper.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HomeScreenService {

    private final HomeRepository homeRepository;

    private final EduContentRepository eduContentRepository;

    private final AWSS3Service awss3Service;

    private final CustomMapper mapper;

    private String awsUrl="https://krays-lms-s3.s3.ap-south-1.amazonaws.com/";

    public CommonResponse<HomeDTO> saveHomeScreen(HomeDTO homeDTO,
                                                    MultipartFile file) {
        HomeDTO response;
        String promoVideo=null;
        try {

            if (!file.isEmpty()){
                String key="LmsPromoVideo/"+homeDTO.tenantId()+"/"+homeDTO.standard()+"/"+UUID.randomUUID().toString();
                awss3Service.putObject(key,file);
                promoVideo= awsUrl+key;
            }
            Home home = mapper.DTOToHomeMapper(homeDTO,promoVideo);
            var savedHome = homeRepository.save(home);
            response = mapper.homeToDTOMapper(savedHome);

        } catch (Exception e) {
            return CommonResponse.<HomeDTO>builder()
                    .statusCode(Constant.FORBIDDEN)
                    .data(null)
                    .status(false)
                    .message("Failed to upload")
                    .build();
        }
        return CommonResponse.<HomeDTO>builder()
                .statusCode(Constant.SUCCESS)
                .data(response)
                .status(true)
                .message("Home page uploaded")
                .build();
    }

    public CommonResponse<List<EduContentDTO>> savePromoContent(List<EduContentDTO> eduContentDTOList) throws IOException {
        List<EduContent> content= mapper.DTOToEduContentMappper(eduContentDTOList);
        var contentList= eduContentRepository.saveAll(content);
        List<EduContentDTO> contentDTOList=  mapper.EduContentToDTOMapper(contentList);
        return CommonResponse.<List<EduContentDTO>>builder()
                .message("Uploaded scucessfully")
                .status(true)
                .data(contentDTOList)
                .statusCode(200)
                .build();

    }

    public CommonResponse<HomeDTO> getHomeScreenByTenantId(String tenantId) {
       Home home= homeRepository.findByTenantId(tenantId);
        if (home==null){
           return CommonResponse.<HomeDTO>builder()
                   .status(false)
                   .statusCode(400)
                   .message("Home Page not found")
                   .build();
       }
        var response=  mapper.homeToDTOMapper(home);

        return CommonResponse.<HomeDTO>builder()
                .data(response)
                .status(true)
                .statusCode(Constant.SUCCESS)
                .message("SUCCESS")
                .build();
    }


    public CommonResponse<List<EduContent>> getEducationContent(String tenantId) {
        List<EduContent> contentList= eduContentRepository.findAllByTenantId(tenantId);
        return CommonResponse.<List<EduContent>>builder()
                .message("SUCCESS")
                .status(true)
                .statusCode(Constant.SUCCESS)
                .data(contentList)
                .build();
    }
}
