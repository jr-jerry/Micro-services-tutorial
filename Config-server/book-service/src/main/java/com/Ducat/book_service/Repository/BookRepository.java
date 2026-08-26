package com.Ducat.book_service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ducat.book_service.Entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
    boolean existsByBookName(String bookName);

    void deleteByBookName(String bookName);

    Optional<BookEntity> findByBookNameAndBookAuthor(String bookName, String bookAuthor);
}
