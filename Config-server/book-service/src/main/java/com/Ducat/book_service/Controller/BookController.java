package com.Ducat.book_service.Controller;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ducat.book_service.DTO.BookDTO;
import com.Ducat.book_service.DTO.BookResponseDTO;
import com.Ducat.book_service.Entity.BookEntity;
import com.Ducat.book_service.Service.BookService;

@RestController
@RequestMapping("/api/book-service")
public class BookController {
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveEndpoint(@RequestBody BookDTO bookDTO){
       BookEntity savedBookEntity= bookService.saveEntity(bookDTO);

       BookResponseDTO responseDTO=this.modelMapper.map(savedBookEntity,BookResponseDTO.class);
       responseDTO.setBookStatus("saved");
       
       return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEndpoint(@RequestParam String bookName){
       Map<String,String> mapResponse= bookService.deleteEntity(bookName);
       return new ResponseEntity<>(mapResponse,HttpStatus.NO_CONTENT);
    }
}
