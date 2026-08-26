package com.Ducat.order_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuantityResponseDTO {
    private String bookName;
    private String bookAuthor;
    private int bookQuantity;
}
