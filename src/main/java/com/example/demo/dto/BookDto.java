package com.example.demo.dto;



public class BookDto {

    private Integer id;
    private String title;
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
	public BookDto(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public BookDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
}