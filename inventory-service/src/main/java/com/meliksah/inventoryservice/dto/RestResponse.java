package com.meliksah.inventoryservice.dto;

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
    private String messages;

    private RestResponse(T data, boolean isSuccess, String messages){
        this(data, isSuccess);
        this.messages=messages;
    }

    private RestResponse(T data, boolean isSuccess){
        this.data=data;
        this.isSuccess=isSuccess;
        responseDate=new Date();
    }


    public static <T> RestResponse of(T t){
        return new RestResponse<T>(t,true);
    }

    public static <T> RestResponse empty(){
        return new RestResponse<T>(null,true);
    }

    public static <T> RestResponse error(T t,String messages){
        return new RestResponse<T>(t,false,messages);
    }
}
