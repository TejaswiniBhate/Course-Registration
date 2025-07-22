package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Course;

public interface CourseService {
	Course saveCourse(Course course);
	List<Course> getAllCourses();
	Course getCourseById(Long id);
	 void deleteCourse(Long id);;
	 List<Course> searchCourses(String keyword);
	
	
}
