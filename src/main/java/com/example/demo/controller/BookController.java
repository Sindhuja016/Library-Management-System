package com.example.demo.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @Autowired
    private PagedResourcesAssembler<BookDto> pagedResourcesAssembler;

    @GetMapping("/getbyid")
    public BookDto filterBooksById(@RequestParam Integer id) {
        return bookService.filterBookById(id);
    }

    @GetMapping("/by-genre")
    public PagedModel<EntityModel<BookDto>> filterBooksByGenre(
            @RequestParam String genre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<BookDto> bookPage = bookService.filterBookByGenre(genre, pageable);
        return pagedResourcesAssembler.toModel(bookPage);
    }

    @GetMapping("/by-date")
    public PagedModel<EntityModel<BookDto>> filterBooksByDate(
            @RequestParam LocalDate publicationDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<BookDto> bookPage = bookService.filterBookByDate(publicationDate, pageable);
        return pagedResourcesAssembler.toModel(bookPage);
    }

    @GetMapping("/filter")
    public PagedModel<EntityModel<BookDto>> filterByGenreLang(
            @RequestParam String genre,
            @RequestParam String language,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<BookDto> bookPage = bookService.filterByGenreLang(genre, language, pageable);
        return pagedResourcesAssembler.toModel(bookPage);
    }

    @GetMapping("/filterbook")
    public PagedModel<EntityModel<BookDto>> filterByBookLang(
            @RequestParam String title,
            @RequestParam String language,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<BookDto> bookPage = bookService.filterByBookLang(title, language, pageable);
        return pagedResourcesAssembler.toModel(bookPage);
    }

    @GetMapping("/filters")
    public PagedModel<EntityModel<BookDto>> filterBySearchKey(
            @RequestParam String title,
            @RequestParam String language,
            @RequestParam(required = false) String searchKey,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<BookDto> bookPage = bookService.filterBySearchKey(title, language, searchKey, pageable);
        return pagedResourcesAssembler.toModel(bookPage);
    }

    @GetMapping("/search")
    public PagedModel<EntityModel<BookDto>> filterByKey(
            @RequestParam String searchKey,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<BookDto> bookPage = bookService.filterByKey(searchKey, pageable);
        return pagedResourcesAssembler.toModel(bookPage);
    }

    @GetMapping("/byauthor")
    public PagedModel<EntityModel<BookDto>> filterBooksByAuthor(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<BookDto> bookPage = bookService.filterBookByAuthor(name, pageable);
        return pagedResourcesAssembler.toModel(bookPage);
    }
}
