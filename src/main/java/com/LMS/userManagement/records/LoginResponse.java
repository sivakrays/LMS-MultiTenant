package com.LMS.userManagement.records;

import com.LMS.userManagement.dto.AuthenticationResponse;
import com.LMS.userManagement.model.Home;

import java.util.List;

public record LoginResponse(
        AuthenticationResponse auth,

      Home home
      //  List<HomeData> dataList
      //  HomeData homeData

        ) {
}
