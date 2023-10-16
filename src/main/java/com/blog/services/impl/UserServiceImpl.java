package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.blog.entity.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.paylods.UserDto;
import com.blog.reposatry.UserRepo;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepo userRepo;

	@Override
	public UserDto createUser(UserDto dto) {
		User user = this.dtoToUser(dto);
		User saveUser = userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto dto, Long id) throws ResourceNotFoundException  {
		User user;
		
			user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found exception"+id));
			
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setPassword(dto.getPassword());
			user.setAbout(dto.getAbout());
			User updateUser = this.userRepo.save(user);
			updateUser.setId(dto.getId());
			dto=this.userToDto(updateUser);
			
		
		return dto;
		
	}

	@Override
	public UserDto getUserById(Long id) throws ResourceNotFoundException {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found exceptiion"+id));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> uDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return uDto;
	}

	@Override
	public void delateUser(Long id) throws ResourceNotFoundException {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found exception"+ id));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setAbout(user.getAbout());
		return dto;
	}
}
