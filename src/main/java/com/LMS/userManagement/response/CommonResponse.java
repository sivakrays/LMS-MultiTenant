package com.LMS.userManagement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommonResponse<T> {

    public boolean status=false;
    public String message;
    public T data;
    public int statusCode;
    public String error;
}