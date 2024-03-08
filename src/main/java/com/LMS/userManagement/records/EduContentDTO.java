package com.LMS.userManagement.records;

public record EduContentDTO(Long id,

                            String imageTitle,
                            String image,
                            String imageContent,
                            String tenantId,
                            String standard) {
}
