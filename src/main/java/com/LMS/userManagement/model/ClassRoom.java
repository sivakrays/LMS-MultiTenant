package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "classRoom")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String classroomId;

    private String classRoomName;

    //  private List<Long> userIds;

    private Long createdBy;

    private Long noOfUsers;

}
