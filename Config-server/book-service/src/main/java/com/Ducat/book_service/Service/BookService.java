package com.Ducat.book_service.Service;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.Ducat.book_service.DTO.BookDTO;
import com.Ducat.book_service.Entity.BookEntity;
import com.Ducat.book_service.Repository.BookRepository;

@Service
public class BookService {
    private BookRepository bookRepository;
    private ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }
    
    public BookEntity saveEntity(BookDTO bookDTO){
        BookEntity emptyBookEntity=modelMapper.map(bookDTO,BookEntity.class);
       BookEntity savedBookEntity= bookRepository.save(emptyBookEntity);
       return savedBookEntity;
    }


    //delete
    public Map<String,String> deleteEntity(String bookName){
   
       boolean exist= bookRepository.existsByBookName(bookName);
       if(exist==true){
        System.out.println("Book found ");
            bookRepository.deleteByBookName(bookName);
            return Map.of("Message","book hard deleted ");
       }else{
            System.out.println("book not found ");
            return Map.of("Message","book doen't exist with this bookName");
       }
    }
    
}
