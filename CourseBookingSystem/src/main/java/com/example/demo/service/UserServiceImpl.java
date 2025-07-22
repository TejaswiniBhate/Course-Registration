package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User login(String userName, String password) {
		
		User user=userRepository.findByUsername(userName);
		
		if(user!=null && user.getPassword().equals(password))
		{
			return user;
			
		}
		return null;
	}

}
