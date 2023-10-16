package com.blog.controlars;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.paylods.UserDto;
import com.blog.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class UserControlar {
	@Autowired
	public UserServiceImpl userServiceImpl;
	
	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto){
		UserDto uDto= this.userServiceImpl.createUser(dto);
		return new ResponseEntity<UserDto>(uDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto,@PathVariable("id") Long id) throws ResourceNotFoundException{
		UserDto userDto=this.userServiceImpl.updateUser(dto, id);
		return new  ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUSer(@PathVariable("id") Long id) throws ResourceNotFoundException{
		this.userServiceImpl.delateUser(id);
		return new ResponseEntity<String>("record delete successfully",HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users=this.userServiceImpl.getAllUsers();
		return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) throws ResourceNotFoundException{
		UserDto user=this.userServiceImpl.getUserById(id);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
}
