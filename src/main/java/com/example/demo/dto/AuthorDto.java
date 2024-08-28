package com.example.demo.dto;



import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;


public class AuthorDto  extends RepresentationModel<AuthorDto> {

    private Integer id;
    private String name;
    private List<BookDto> books=new ArrayList<>();;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BookDto> getBooks() {
		return books;
	}
	public void setBooks(List<BookDto> books) {
		this.books = books;
	}
	public AuthorDto(Integer id, String name, List<BookDto> books) {
		super();
		this.id = id;
		this.name = name;
		this.books = books;
	}
	public AuthorDto() {
		super();
	
	}
	

    
    
}