package com.LMS.userManagement.service;

import com.LMS.userManagement.dto.ClassRoomDashBoardDto;
import com.LMS.userManagement.dto.ClassRoomDto;
import com.LMS.userManagement.dto.UserClassRoomDto;
import com.LMS.userManagement.model.ClassRoom;
import com.LMS.userManagement.model.User;
import com.LMS.userManagement.repository.ClassRoomRepository;
import com.LMS.userManagement.repository.UserRepository;
import com.LMS.userManagement.response.CommonResponse;
import com.LMS.userManagement.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService {

    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    UserRepository userRepository;


    public CommonResponse<ClassRoom> createClassRoom(ClassRoomDto classRoomDto) {

        List<Long> userIds = classRoomDto.getUsers();
        Long count = (long) userIds.size();

        ClassRoom classRoom = ClassRoom.builder()
                .classRoomName(classRoomDto.getClassRoomName())
                .users(classRoomDto.getUsers())
                .createdBy(classRoomDto.getCreatedBy())
                .noOfUsers(count)
                .build();

        try {

            ClassRoom newClassRoom = classRoomRepository.save(classRoom);
            return CommonResponse.<ClassRoom>builder()
                    .statusCode(Constant.SUCCESS)
                    .status(true)
                    .data(newClassRoom)
                    .message("ClassRoom Created Successfully !!")
                    .build();

        }catch (Exception e){

            return CommonResponse.<ClassRoom>builder()
                    .statusCode(Constant.FORBIDDEN)
                    .status(false)
                    .data(classRoom)
                    .message("Unable to create the ClassRoom")
                    .build();

        }

    }

    public CommonResponse<List<ClassRoomDashBoardDto>> getAllClassRooms(long userId) {
        List<ClassRoomDashBoardDto> dashboardDtos = new ArrayList<>();

        try {
            List<ClassRoom> classRooms = classRoomRepository.findByCreatedBy(userId);

            for (ClassRoom classRoom : classRooms) {
                ClassRoomDashBoardDto dashBoardDto = new ClassRoomDashBoardDto();
                dashBoardDto.setClassRoomName(classRoom.getClassRoomName());
                dashBoardDto.setNoOfUsers((long) classRoom.getUsers().size());
                dashboardDtos.add(dashBoardDto);
            }

            return CommonResponse.<List<ClassRoomDashBoardDto>>builder()
                    .status(true)
                    .data(dashboardDtos)
                    .statusCode(Constant.SUCCESS)
                    .message("ClassRoom dashboard data fetched successfully")
                    .build();

        } catch (Exception e) {
            return CommonResponse.<List<ClassRoomDashBoardDto>>builder()
                    .status(false)
                    .data(new ArrayList<>())
                    .statusCode(Constant.FORBIDDEN)
                    .message("An error occurred while fetching ClassRoom dashboard data")
                    .build();
        }
    }

    public CommonResponse<List<UserClassRoomDto>> getAllUsers() {

        List<UserClassRoomDto> userDtos = new ArrayList<>();

        try {

            List<User> users = userRepository.findAll();

            for (User user : users) {
                UserClassRoomDto userDto = new UserClassRoomDto();
                userDto.setUserId(user.getId());
                userDto.setUserName(user.getName());
                userDtos.add(userDto);
            }

            return CommonResponse.<List<UserClassRoomDto>>builder()
                    .status(true)
                    .message("Users fetched successfully")
                    .data(userDtos)
                    .statusCode(Constant.SUCCESS)
                    .build();

        } catch (Exception e) {

            return CommonResponse.<List<UserClassRoomDto>>builder()
                    .status(false)
                    .message("An error occurred while fetching users")
                    .statusCode(Constant.FORBIDDEN)
                    .build();

        }
    }

    public CommonResponse<String> deleteClassRoom(Long classRoomId) {

        Optional<ClassRoom> classRoom = classRoomRepository.findById(classRoomId);

        try {

            if (classRoom.isEmpty()){

                return CommonResponse.<String>builder()
                        .status(true)
                        .message("There is no classroom")
                        .statusCode(Constant.NO_CONTENT)
                        .build();

            }else {

                classRoomRepository.deleteById(classRoomId);
                return CommonResponse.<String>builder()
                        .status(true)
                        .statusCode(Constant.SUCCESS)
                        .message("Deleted Successfully")
                        .build();

            }

        }catch (Exception e){

            e.printStackTrace();
            return CommonResponse.<String>builder()
                    .status(false)
                    .statusCode(Constant.FORBIDDEN)
                    .message("Something Went Wrong..")
                    .build();

        }

    }
}
