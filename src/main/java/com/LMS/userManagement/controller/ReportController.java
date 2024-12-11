package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.ReportDto;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/getReport")
    public CommonResponse<ReportDto> getReport(long userId){
        return reportService.getReport(userId);
    }


}
