package com.LMS.userManagement.records;

import java.util.List;

public record HomeData(

        Banner bannerDetails,

        List<?> courseDetails,

        PromoData promoDetails

) {
}
