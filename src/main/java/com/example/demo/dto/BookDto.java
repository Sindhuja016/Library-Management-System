package com.example.demo.dto;

import java.time.LocalDate;

public class BookDto {

    private Integer id;
    private String title;
    private LocalDate publicationDate;
    
    private String genre;
    private String language;
	
	
	public BookDto(Integer id, String title, LocalDate publicationDate, String isbn, String genre, String language) {
		super();
		this.id = id;
		this.title = title;
		this.publicationDate = publicationDate;
		this.genre = genre;
		this.language = language;
	}
	public BookDto() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
    
}