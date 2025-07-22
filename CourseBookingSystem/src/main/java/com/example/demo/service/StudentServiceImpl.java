package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	 @Autowired
	    private StudentRepository studentRepository;
	 
	 @Autowired
	 private CourseRepository courseRepository;
	
	
	@Override
	public Student registerStudent(Student student) {
		
		return studentRepository.save(student);
	}

	@Override
	public Student loginStudent(String emailOrUsername, String password) {
		
		Student student=studentRepository.findByEmail(emailOrUsername);
		if(student!=null && student.getPassword().equals(password))
		{
			return student ;
		}
		return null;
	}

	@Override
	public void enrollCourse(long studentId, long courseId) {
		
		Student student=studentRepository.findById(studentId).orElse(null);
		  Course course = courseRepository.findById(courseId).orElse(null);
		  if(student!=null && course!=null)
		  {
			  student.getCourses().add(course);
			  studentRepository.save(student);
		  }
		
	}

	@Override
	public List<Course> getEnrolledCourses(long studentId) {
		  Student student = studentRepository.findById(studentId).orElse(null);
	        return (student != null) ? student.getCourses() : null;
	}

	@Override
	public void unenrollCourse(Long studentId, Long courseId) {
		 Student student = studentRepository.findById(studentId).orElse(null);
		    Course course = courseRepository.findById(courseId).orElse(null);

		    if (student != null && course != null) {
		        student.getCourses().remove(course); // Remove course from student
		        studentRepository.save(student);     // Save updated student
		    }
		
	}

}
