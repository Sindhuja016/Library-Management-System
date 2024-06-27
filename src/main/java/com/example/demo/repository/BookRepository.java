package com.example.demo.repository;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE b.genre=:genre AND b.language=:language")
    Page<Book> filterByGenreAndLang(@Param("genre") String genre, @Param("language") String language, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.title=:title AND b.language=:language")
    Page<Book> filterByTitleAndLang(@Param("title") String title, @Param("language") String language, Pageable pageable);

    @Query("SELECT b FROM Book b " +
           "WHERE b.title = :title " +
           "AND b.language = :language " +
           "AND (:searchKey IS NULL OR " +
           "     CONCAT(b.id, b.genre, b.author.name) " +
           "     LIKE CONCAT('%', :searchKey, '%'))")
    Page<Book> filterBookBySearchKey(@Param("title") String title, @Param("language") String language, @Param("searchKey") String searchKey, Pageable pageable);

    @Query(value="SELECT * FROM book WHERE genre = :genre", nativeQuery=true)
    Page<Book> filterByGenre(@Param("genre") String genre, Pageable pageable);

    @Query(value = "SELECT * FROM Book b " +
            "WHERE b.title LIKE %:searchKey% " +
            "OR b.language LIKE %:searchKey% " +
            "OR b.genre LIKE %:searchKey% " +
            "OR b.author_id = :searchKey", nativeQuery = true)
    Page<Book> searchByKey(@Param("searchKey") String searchKey, Pageable pageable);
    
    @Query(value = "SELECT b.* FROM Book b " +
                   "INNER JOIN Author a ON b.author_id = a.id " +
                   "WHERE a.name LIKE %:name%", nativeQuery = true)
    Page<Book> findByAuthor(@Param("name") String name, Pageable pageable);

    @Query(value="SELECT * FROM book b WHERE b.publication_Date=:publicationDate",nativeQuery=true)
    Page<Book> filterByDate(@Param("publicationDate") LocalDate publicationDate, Pageable pageable);
}
