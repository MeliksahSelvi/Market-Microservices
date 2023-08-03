package com.meliksah.orderservice.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author mselvi
 * @Created 30.07.2023
 */

@Data
public class RestResponse<T> {

    private T data;
    private Date responseDate;
    private boolean isSuccess;

    private RestResponse(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
        responseDate = new Date();
    }

    private RestResponse(boolean isSuccess, String messages) {
        this.isSuccess = isSuccess;
        responseDate = new Date();
    }


    public static <T> RestResponse of(T t) {
        return new RestResponse<T>(t, true);
    }

    public static <T> RestResponse empty() {
        return new RestResponse<T>(null, true);
    }

    public static <T> RestResponse error(T t) {
        return new RestResponse<T>(t, false);
    }
}
