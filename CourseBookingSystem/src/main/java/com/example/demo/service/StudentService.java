package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;

public interface StudentService {

	Student registerStudent(Student student);
	Student loginStudent(String emailOrUsername,String password);
	void enrollCourse(long studentId,long courseId);
	List<Course> getEnrolledCourses(long studentId);
	void unenrollCourse(Long studentId, Long courseId);
}
