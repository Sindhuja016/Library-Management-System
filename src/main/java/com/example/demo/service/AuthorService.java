package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;

import jakarta.persistence.EntityNotFoundException;

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
			    bt.add(bd);	
			}
			
			ad.setBooks(bt);
			
			return ad;

		
	}
	public void deleteUser(Integer id) {
		aRepo.deleteById(id);
		

	}
	
	



}
