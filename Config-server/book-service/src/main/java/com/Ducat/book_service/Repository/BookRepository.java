package com.Ducat.book_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ducat.book_service.Entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
    boolean existsByBookName(String bookName);

    void deleteByBookName(String bookName);
}
