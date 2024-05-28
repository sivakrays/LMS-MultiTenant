package com.LMS.userManagement.controller;

import com.LMS.userManagement.dto.ClassRoomDashBoardDto;
import com.LMS.userManagement.dto.ClassRoomDto;
import com.LMS.userManagement.dto.ClassRoomNameDto;
import com.LMS.userManagement.dto.UserClassRoomDto;
import com.LMS.userManagement.model.ClassRoom;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.service.ClassRoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms/api/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Tag(name = "ClassRoom", description = "ClassRoom management APIs")
public class ClassRoomController {

    @Autowired
    ClassRoomService classRoomService;

    @PostMapping("/createClassRoom")
    public CommonResponse<List<ClassRoom>> createClassRoom(@RequestBody ClassRoomDto classRoomDto){
        return classRoomService.createClassRoom(classRoomDto);
    }

    @GetMapping("/getAllClassRooms")
    public CommonResponse<List<ClassRoomDashBoardDto>> getAllClassRooms(@RequestParam Long userId){
        return classRoomService.getAllClassRooms(userId);
    }

    @GetMapping("/getAllUsers")
    public CommonResponse<List<UserClassRoomDto>> getAllUsers(){
        return classRoomService.getAllUsers();
    }

    @DeleteMapping("/deleteClassRoom")
    public CommonResponse<List<ClassRoom>> deleteClassRoom(@RequestParam Long classRoomId,
                                                           @RequestParam long userId){
        return classRoomService.deleteClassRoom(classRoomId,userId);
    }

    @GetMapping("/getAllClassRoomNames")
    public CommonResponse<List<ClassRoomNameDto>> getAllClassRoomNames(@RequestParam Long userId){
        return classRoomService.getAllClassRoomNames(userId);
    }

}
