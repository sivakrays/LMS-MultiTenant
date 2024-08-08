package com.LMS.userManagement.records;

public record HomeDTO(
        Long id,
        String tenantId,
        String homeTitle,

        String courseTitle,
        String courseTitle2,
        String theme,
        String standard,
        String educationTitle,
        String promoTitle,

        String promoVideo,
        String promoDescription,
        String bannerImage,

        String supportNumber,

        String bannerHeading,

        String bannerSubHeading,
        String bannerParagraph

) {
}
