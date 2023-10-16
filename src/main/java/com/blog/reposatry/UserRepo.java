package com.blog.reposatry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
