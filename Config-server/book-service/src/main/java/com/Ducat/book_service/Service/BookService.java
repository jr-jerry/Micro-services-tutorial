package com.Ducat.book_service.Service;

import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Ducat.book_service.DTO.BookDTO;
import com.Ducat.book_service.DTO.GenericResponseDTO;
import com.Ducat.book_service.DTO.QuantityRequestDTO;
import com.Ducat.book_service.DTO.QuantityResponseDTO;
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

    public BookEntity saveEntity(BookDTO bookDTO) {
        BookEntity emptyBookEntity = modelMapper.map(bookDTO, BookEntity.class);
        BookEntity savedBookEntity = bookRepository.save(emptyBookEntity);
        return savedBookEntity;
    }

    // delete
    public Map<String, String> deleteEntity(String bookName) {

        boolean exist = bookRepository.existsByBookName(bookName);
        if (exist == true) {
            System.out.println("Book found ");
            bookRepository.deleteByBookName(bookName);
            return Map.of("Message", "book hard deleted ");
        } else {
            System.out.println("book not found ");
            return Map.of("Message", "book doen't exist with this bookName");
        }
    }

    public ResponseEntity<GenericResponseDTO<?>> getQuantiy(QuantityRequestDTO quantityRequestDTO) {
        String bookAuthor = quantityRequestDTO.getBookAuthor();
        String bookName = quantityRequestDTO.getBookName();

        Optional<BookEntity> optional = bookRepository.findByBookNameAndBookAuthor(bookName, bookAuthor);
        BookEntity bookEntity = null;

        
        if (optional.isPresent()) {
            bookEntity = optional.get();
            QuantityResponseDTO quantityResponseDTO = new QuantityResponseDTO(quantityRequestDTO.getBookName(),
                    quantityRequestDTO.getBookAuthor(), bookEntity.getBookQuantity());

            GenericResponseDTO<QuantityResponseDTO> genericResponseDTO = GenericResponseDTO
                    .buildGenericResponse(quantityResponseDTO, "book avaliable  ", 200);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(genericResponseDTO);

        }
        GenericResponseDTO<BookDTO> genericResponseDTO = GenericResponseDTO
                .buildGenericResponse(this.modelMapper.map(bookEntity, BookDTO.class), "book not avaliable  ", 200);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genericResponseDTO);

    }

}
