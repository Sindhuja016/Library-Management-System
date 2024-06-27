package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.AuthorDto;
import com.example.demo.service.AuthorService;

@RestController
@RequestMapping("/api")
public class AuthorController {

	@Autowired
	public AuthorService authorservice;
	
	 @Autowired
	 private PagedResourcesAssembler<AuthorDto> pagedResourcesAssembler;

	@PostMapping("/create")
	public List<AuthorDto> createUser(@RequestBody List<AuthorDto> dto){
		return authorservice.createUser(dto);
	}
	
	@GetMapping("/getall")
	public List<AuthorDto> getAllUser()
	{
		return authorservice.getAllUser();
	}
	@GetMapping("/getbyid/{id}")
	public AuthorDto getById(@PathVariable Integer id) {
		return authorservice.getById(id);
		
	}
	@GetMapping("/getbyname")
	public AuthorDto getByName(@RequestParam String name) {
		return authorservice.getByName(name);
	}
	@PutMapping("/updateuser/{id}")
	public AuthorDto updateUser(@RequestBody AuthorDto dto,@PathVariable Integer id) {
		return authorservice.updateUser(dto,id);
		
	}
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
	        authorservice.deleteUser(id);
	        return ResponseEntity.noContent().build();
	    }
	 
	 @DeleteMapping("/deleteall")
	 public void deleteAll() {
		 authorservice.deleteAll();
	 }
	 
	
	 
    @GetMapping("/authors")
	public PagedModel<EntityModel<AuthorDto>> getAllAuthors(
	            @RequestParam(value = "page", defaultValue = "0") int page,
	            @RequestParam(value = "size", defaultValue = "10") int size) {
	        Page<AuthorDto> authorsPage = authorservice.getAllAuthors(page, size);
	        return pagedResourcesAssembler.toModel(authorsPage);
	    }
    
}
