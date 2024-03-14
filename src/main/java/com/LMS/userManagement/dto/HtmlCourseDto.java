package com.LMS.userManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HtmlCourseDto {

    public Long userId;
    public String courseId;
    public String chapter;
    public Integer chapterOrder;
    public List<ChapterContents> chapterContents;

}
