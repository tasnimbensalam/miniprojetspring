package com.learnedge.backend.controller;

import com.learnedge.backend.entity.Course;
import com.learnedge.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/addcourse")
    public Course saveCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getcourses")
    public List<Course> fetchCoursesList(){
        return courseService.fetchCoursesList();
    }

}
