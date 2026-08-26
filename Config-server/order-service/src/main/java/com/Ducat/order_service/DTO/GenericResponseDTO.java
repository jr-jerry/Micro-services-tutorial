package com.Ducat.order_service.DTO;

import lombok.Setter;

import java.util.Date;

import lombok.Getter;

@Getter
@Setter
public class GenericResponseDTO<T> {
    private Date timestamp;
    private T data;
    private String message;
    private int statusCode;
 
   
    public static <T> GenericResponseDTO<T> buildGenericResponse(T data, String message, int statusCode) {
        GenericResponseDTO<T> response = new GenericResponseDTO<>();
        response.setTimestamp(new Date(System.currentTimeMillis()));
        response.setData(data);
        response.setMessage(message);
        response.setStatusCode(statusCode);
        return response;
    }
}