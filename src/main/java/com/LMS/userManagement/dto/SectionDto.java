package com.LMS.userManagement.dto;

import com.LMS.userManagement.model.SubSection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {
    private Integer sectionId;
    private Integer courseId;
    private Integer key;
    private String title;
    private List<SubSectionDto> subSections;
}
