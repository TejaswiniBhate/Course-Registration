package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

	 List<Course> findByNameContainingIgnoreCase(String keyword);
}
