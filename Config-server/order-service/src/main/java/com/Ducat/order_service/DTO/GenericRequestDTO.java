package com.Ducat.order_service.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericRequestDTO<T> {
    private Date timestamp;
    private T data;
    private String message;
    private int statusCode;
 
}
