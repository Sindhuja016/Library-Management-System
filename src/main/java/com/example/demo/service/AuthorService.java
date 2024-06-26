package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.controller.AuthorController;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class AuthorService {
	
    @Autowired
    private AuthorRepository aRepo;
	public List<AuthorDto> createUser(List<AuthorDto> dto) {
	   
		List<AuthorDto> e=new ArrayList<>();
		
		for(AuthorDto dt:dto) {
			Author a=new Author();
			a.setName(dt.getName());
			
			List<Book> book=new ArrayList<>();
			for(BookDto bdt:dt.getBooks()) {
				Book b=new Book();
				b.setTitle(bdt.getTitle());
				b.setAuthor(a);
				b.setPublicationDate(bdt.getPublicationDate());
				b.setLanguage(bdt.getLanguage());
				b.setGenre(bdt.getGenre());
				book.add(b);
			}
			a.setBooks(book);
			
			Author aobj=aRepo.save(a);
			
			AuthorDto aDto = new AuthorDto();
	        aDto.setId(aobj.getId());
	        aDto.setName(aobj.getName());
			
			List<BookDto> bt=new ArrayList<>();
			for(Book be:aobj.getBooks()) {
				BookDto bd=new BookDto();
				bd.setId(be.getId());
				bd.setTitle(be.getTitle());
				bd.setPublicationDate(be.getPublicationDate());
				bd.setLanguage(be.getLanguage());
				bd.setGenre(be.getGenre());
			    bt.add(bd);	
			}
			aDto.setBooks(bt);
		    e.add(aDto);
		}
		return e;
	}
	public List<AuthorDto> getAllUser() {
		
		List<AuthorDto> e=new ArrayList<>();

		List<Author> aob=aRepo.findAll();
		
		for(Author aobj:aob) {
		AuthorDto aDto = new AuthorDto();
        aDto.setId(aobj.getId());
        aDto.setName(aobj.getName());
		
		List<BookDto> bt=new ArrayList<>();
		for(Book be:aobj.getBooks()) {
			BookDto bd=new BookDto();
			bd.setId(be.getId());
			bd.setTitle(be.getTitle());
			bd.setPublicationDate(be.getPublicationDate());
			bd.setLanguage(be.getLanguage());
			bd.setGenre(be.getGenre());
			
		    bt.add(bd);	
		}
		aDto.setBooks(bt);
	    e.add(aDto);
	}
	return e;
	}
	public AuthorDto getById(Integer id) {
		
	  Optional<Author> op=aRepo.findById(id);
	  Author a=op.get();
	  AuthorDto ad=new AuthorDto();
	  ad.setId(a.getId());
	  ad.setName(a.getName());
	  
	  List<BookDto> bt=new ArrayList<>();
		for(Book be:a.getBooks()) {
			BookDto bd=new BookDto();
			bd.setId(be.getId());
			bd.setTitle(be.getTitle());
			bd.setPublicationDate(be.getPublicationDate());
			bd.setLanguage(be.getLanguage());
			bd.setGenre(be.getGenre());
		    bt.add(bd);	
		}
		ad.setBooks(bt);
		
		return ad;
	  
	}
	public AuthorDto getByName(String name) {
		Optional<Author> op=aRepo.findByName(name);
		  Author a=op.get();
		  AuthorDto ad=new AuthorDto();
		  ad.setId(a.getId());
		  ad.setName(a.getName());
		  
		  List<BookDto> bt=new ArrayList<>();
			for(Book be:a.getBooks()) {
				BookDto bd=new BookDto();
				bd.setId(be.getId());
				bd.setTitle(be.getTitle());
				bd.setPublicationDate(be.getPublicationDate());
				bd.setLanguage(be.getLanguage());
				bd.setGenre(be.getGenre());
			    bt.add(bd);	
			}
			ad.setBooks(bt);
			
			return ad;
	}
	public AuthorDto updateUser(AuthorDto dto, Integer id) {
      
		Optional<Author> op=aRepo.findById(id);
		Author ab=op.get();
		ab.setName(dto.getName());
		
		List<Book> book=ab.getBooks();
		for(BookDto b:dto.getBooks()) {
			Book bk=new Book();
			bk.setTitle(b.getTitle());
			bk.setAuthor(ab);
			
			book.add(bk);
		}
	
		ab.setBooks(book);
		
		Author a=aRepo.save(ab);
		
		 AuthorDto ad=new AuthorDto();
		  ad.setId(a.getId());
		  ad.setName(a.getName());
		  
		  List<BookDto> bt=new ArrayList<>();
			for(Book be:a.getBooks()) {
				BookDto bd=new BookDto();
				bd.setId(be.getId());
				bd.setTitle(be.getTitle());
				bd.setPublicationDate(be.getPublicationDate());
				bd.setLanguage(be.getLanguage());
				bd.setGenre(be.getGenre());
			    bt.add(bd);	
			}
			
			ad.setBooks(bt);
			
			return ad;

		
	}
	public void deleteUser(Integer id) {
		aRepo.deleteById(id);
		

	}
	
	public Page<AuthorDto> getAllAuthors(int page, int size) {
	        PageRequest pageable=PageRequest.of(page, size);
	        Page<Author> author=aRepo.findAll(pageable);
	        
	        List<AuthorDto> adto=new ArrayList<>();
	        for(Author a:author) {
	        	AuthorDto dt=new AuthorDto();
	        	dt.setId(a.getId());
	        	dt.setName(a.getName());
	        	
	        	List<BookDto> book=new ArrayList<>();
	        	for(Book b:a.getBooks()) {
	        		BookDto bdto=new BookDto();
	        		bdto.setId(b.getId());
	        		bdto.setTitle(b.getTitle());
	        		bdto.setPublicationDate(b.getPublicationDate());
					bdto.setLanguage(b.getLanguage());
					bdto.setGenre(b.getGenre());
	        		book.add(bdto);
	        	}
	        	
	        	dt.setBooks(book);
	        	dt.add(linkTo(methodOn(AuthorController.class).getById(dt.getId())).withSelfRel());
	        	dt.add(linkTo(methodOn(AuthorController.class).getByName(dt.getName())).withSelfRel());
	            dt.add(linkTo(methodOn(AuthorController.class).updateUser(dt, dt.getId())).withRel("update"));
	            dt.add(linkTo(methodOn(AuthorController.class).deleteUser(dt.getId())).withRel("delete"));

	        	adto.add(dt);
	        }
	        return new  PageImpl<>(adto,pageable,author.getTotalElements());

	    }
	public void deleteAll() {
		aRepo.deleteAll();
	}
	
	



}
