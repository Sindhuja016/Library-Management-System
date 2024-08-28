package com.example.demo.service;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bRepo;

    public BookDto filterBookById(Integer id) {
        Optional<Book> book1 = bRepo.findById(id);
        Book b = book1.get();
        return changeToDto(b);
    }

    public Page<BookDto> filterBookByGenre(String genre, PageRequest pageable) {
        Page<Book> bookPage = bRepo.filterByGenre(genre, pageable);
        return bookPage.map(this::changeToDto);
    }

    public Page<BookDto> filterByGenreLang(String genre, String language, PageRequest pageable) {
        Page<Book> bookPage = bRepo.filterByGenreAndLang(genre, language, pageable);
        return bookPage.map(this::changeToDto);
    }

    public Page<BookDto> filterByBookLang(String title, String language, PageRequest pageable) {
        Page<Book> bookPage = bRepo.filterByTitleAndLang(title, language, pageable);
        return bookPage.map(this::changeToDto);
    }

    public Page<BookDto> filterBySearchKey(String title, String language, String searchKey, PageRequest pageable) {
        Page<Book> bookPage = bRepo.filterBookBySearchKey(title, language, searchKey, pageable);
        return bookPage.map(this::changeToDto);
    }

    public Page<BookDto> filterByKey(String searchKey, PageRequest pageable) {
        Page<Book> bookPage = bRepo.searchByKey(searchKey, pageable);
        return bookPage.map(this::changeToDto);
    }

    public Page<BookDto> filterBookByAuthor(String name, PageRequest pageable) {
        Page<Book> bookPage = bRepo.findByAuthor(name, pageable);
        return bookPage.map(this::changeToDto);
    }

    public Page<BookDto> filterBookByDate(LocalDate publicationDate, PageRequest pageable) {
        Page<Book> bookPage = bRepo.filterByDate(publicationDate, pageable);
        return bookPage.map(this::changeToDto);
    }
    public void deleteUser(Integer id) {
		bRepo.deleteById(id);
		

	}

    private BookDto changeToDto(Book b) {
        BookDto dt = new BookDto();
        dt.setId(b.getId());
        dt.setTitle(b.getTitle());
        dt.setPublicationDate(b.getPublicationDate());
        dt.setGenre(b.getGenre());
        dt.setLanguage(b.getLanguage());
        return dt;
    }
}
