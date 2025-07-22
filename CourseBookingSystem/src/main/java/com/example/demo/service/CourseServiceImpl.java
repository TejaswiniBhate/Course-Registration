package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Course;
import com.example.demo.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public Course saveCourse(Course course) {
		
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
		
	}

	@Override
	public List<Course> searchCourses(String keyword) {
		return courseRepository.findByNameContainingIgnoreCase(keyword);
	}

}
