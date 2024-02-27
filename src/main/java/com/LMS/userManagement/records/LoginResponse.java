package com.LMS.userManagement.records;

import com.LMS.userManagement.dto.AuthenticationResponse;

import java.util.List;

public record LoginResponse(
        AuthenticationResponse auth,

        List<HomeData> dataList

        ) {
}
