package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomDashBoardDto {

    private String classRoomName;

    private String classRoomId;

    private Long noOfUsers;

}
