package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.LeaderBoardDto;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LeaderBoardController {

    @Autowired
    LeaderBoardService leaderBoardService;

    @GetMapping("/getLeaderBoard")
    public CommonResponse<List<LeaderBoardDto>> getLeaderBoard(@RequestParam Integer standard, @RequestParam long userId, @RequestParam Integer daysCount){
        return leaderBoardService.getLeaderBoard(standard,userId, daysCount);
    }

}
