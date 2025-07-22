package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.Course;
import com.example.demo.service.CourseService;

@Controller
public class CourseController {
	
	 @Autowired
	    private CourseService courseService;

	    @GetMapping("/courses/add")
	    public String showAddCourseForm(Model model) {
	        model.addAttribute("course", new Course());
	        return "add-course";
	    }

	    @PostMapping("/courses/add")
	    public String addCourse(@ModelAttribute Course course) {
	        courseService.saveCourse(course);
	        return "redirect:/courses";
	    }

	    @GetMapping("/courses")
	    public String listCourses(Model model) {
	        model.addAttribute("courses", courseService.getAllCourses());
	        return "course-list";
	    }	
	    
	    
	    @GetMapping("/courses/delete/{id}")
	    public String deleteCourse(@PathVariable("id") Long id) {
	        courseService.deleteCourse(id);  // This method deletes the course
	        return "redirect:/courses";      // Redirects back to the list
	    }
	    

}
