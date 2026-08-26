package com.Ducat.book_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuantityRequestDTO {
    private String bookName;
    private String bookAuthor;
    private int bookQuantity;
}
