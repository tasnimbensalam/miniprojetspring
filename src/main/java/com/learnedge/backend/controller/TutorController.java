package com.learnedge.backend.controller;

import com.learnedge.backend.entity.Course;
import com.learnedge.backend.entity.Tutor;
import com.learnedge.backend.request.LoginRequestTutor;
import com.learnedge.backend.service.CourseService;
import com.learnedge.backend.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tutor")

public class TutorController {
    @Autowired
    private TutorService tutorService;

    @Autowired
    private CourseService courseService;

    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/addtutor")
    public Tutor addTutor(@RequestBody Tutor tutor){
        return tutorService.saveTutor(tutor);
    }

    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/email/{email}")
    public Tutor fetchTutorByEmail(@PathVariable("email") String email ){
        return tutorService.fetchTutorByEmail(email);
    }

    
    @GetMapping("/courses/{tutorId}")
    public List<Course> getCourseByTutorId(@PathVariable Long tutorId) {
        return courseService.fetchCoursesByTutorId(tutorId);
    }

  
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login" )
    public ResponseEntity<String> login(@RequestBody LoginRequestTutor loginRequestTutor) {
        String TutorEmail = loginRequestTutor.getTutorEmail();
        String password = loginRequestTutor.getPassword();

        if (tutorService.authenticateUser(TutorEmail, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
