package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	  private StudentService studentService;
	
	 @GetMapping("/courses")
	    public String viewAllCourses(Model model, HttpSession session) {
	        List<Course> courses = courseService.getAllCourses();
	        model.addAttribute("courses", courses);

	        Student student = (Student) session.getAttribute("student");
	        if (student != null) {
	            model.addAttribute("studentId", student.getId());
	        }

	        return "student_courses"; 
	    }
	 
	 @PostMapping("/unenroll")
	 public String unenrollInCourse(@RequestParam Long studentId,
	                              @RequestParam Long courseId) {
	     System.out.println("Student ID: " + studentId + ", Course ID: " + courseId);
	     studentService.unenrollCourse(studentId, courseId);
	     return "redirect:/student/my-courses";
	 }
	 @PostMapping("/enroll")
	 public String enrollInCourse(@RequestParam Long studentId,
	                              @RequestParam Long courseId) {
	     System.out.println("Student ID: " + studentId + ", Course ID: " + courseId);
	     studentService.enrollCourse(studentId, courseId);
	     return "redirect:/student/my-courses";
	 }
	 
	 @GetMapping("/my-courses")
	    public String viewMyCourses(HttpSession session, Model model) {
	        Student student = (Student) session.getAttribute("student");

	        if (student != null) {
	            List<Course> enrolledCourses = studentService.getEnrolledCourses(student.getId());
	            model.addAttribute("courses", studentService.getEnrolledCourses(student.getId()));
	            model.addAttribute("student", student);
	        }

	        return "my_courses";
	    }
	 
}
