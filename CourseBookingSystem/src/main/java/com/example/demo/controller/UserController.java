package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Student;
import com.example.demo.Entity.User;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/login")
	public String showLoginForm()
	{
		return "login";
	}

	 @PostMapping("/login")
	public String login(@RequestParam String emailOrUsername,@RequestParam String password,
			HttpSession session,Model model)
	{
		User user=userService.login(emailOrUsername,password);
		if(user!=null)
		{
			session.setAttribute("admin", user);
			return "redirect:/admin/dashboard";
		}
		Student student=studentService.loginStudent(emailOrUsername,password);
		if(student!=null)
		{
			session.setAttribute("student", student);
			return "redirect:/student/my-courses";
		}
		model.addAttribute("error", "Invalid credentials");
		return "login";
	}
	
	   @PostMapping("/register")
	    public String registerStudent(@ModelAttribute Student student, Model model) {
	        studentService.registerStudent(student);
	        model.addAttribute("msg", "Registration successful. Please login.");
	        return "redirect:/login";
	    }
	   
	   @GetMapping("/logout")
	   public String logout(HttpSession session)
	   {
		   session.invalidate();
		   return "redirect:/login";
	   }
	   
	   @GetMapping("/register")
	   public String showRegisterForm(Model model) {
	       model.addAttribute("student", new Student());
	       return "register";
	   }
}
