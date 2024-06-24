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

import com.example.demo.dto.AuthorDto;
import com.example.demo.service.AuthorService;

@RestController
@RequestMapping("/api")
public class AuthorController {

	@Autowired
	public AuthorService authorservice;
	
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
	@DeleteMapping("/deleteuser/{id}")
	public void deleteUser(@PathVariable Integer id) {
		authorservice.deleteUser(id);
	}
}
