package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubSectionDto {

    private String subSectionId;

    private String sectionId;

    private Integer key;

    private String title;

    private String description;

    private String link;

}
