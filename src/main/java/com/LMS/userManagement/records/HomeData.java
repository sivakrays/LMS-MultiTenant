package com.LMS.userManagement.records;

import java.util.List;

public record HomeData(

        Banner bannerDetails,

        List<FeaturedCourse> courseDetails,

        PromoData promoDetails

) {
}
