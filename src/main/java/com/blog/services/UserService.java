package com.blog.services;

import java.util.List;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.paylods.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto dto);
	public UserDto updateUser(UserDto dto, Long id) throws ResourceNotFoundException;
	public UserDto getUserById(Long id) throws ResourceNotFoundException;
	public List<UserDto> getAllUsers();
	public void delateUser(Long id) throws ResourceNotFoundException ;
	
}
