package com.Ducat.book_service.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDTO {
    private String bookName;
    private String bookAuthor;
    private float bookPrice;
    private String bookStatus;
}
