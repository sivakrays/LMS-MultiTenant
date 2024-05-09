package com.LMS.userManagement.controller;

import com.LMS.userManagement.model.EduContent;
import com.LMS.userManagement.model.Home;
import com.LMS.userManagement.records.EduContentDTO;
import com.LMS.userManagement.records.HomeDTO;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.HomeScreenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lms/api/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Tag(name = "HomeScreen", description = "Home Screen management APIs")
@RequiredArgsConstructor
public class HomeScreenController {

    private final HomeScreenService homeService;


 @PostMapping(value = "/saveHomeScreen",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public CommonResponse<HomeDTO> saveHomeScreen(@RequestPart("data") HomeDTO homeDTO,
                                                  @RequestPart(value = "file",required = false)  MultipartFile file){
   return homeService.saveHomeScreen(homeDTO,file);
 }

 @PostMapping("/saveEducationContent")
    public CommonResponse<List<EduContentDTO>> savePromoContent(@RequestBody List<EduContentDTO> eduContentDTOList) throws IOException {
     return homeService.savePromoContent(eduContentDTOList);
 }

 @GetMapping("/getHomeScreen")
    public CommonResponse<Home> getHomeScreenByTenantId(@RequestHeader String tenantId){
     return homeService.getHomeScreenByTenantId(tenantId);
 }

 @GetMapping("/getEducationContent")
    public CommonResponse<List<EduContent>> getEducationContent(@RequestHeader String tenantId){
     return homeService.getEducationContent(tenantId);
 }
}
