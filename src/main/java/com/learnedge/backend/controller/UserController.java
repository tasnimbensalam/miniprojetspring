package com.learnedge.backend.controller;

import com.learnedge.backend.entity.Course;
import com.learnedge.backend.entity.User;
import com.learnedge.backend.request.LoginRequest;
import com.learnedge.backend.service.CourseService;
import com.learnedge.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired  
    private UserService userService;

    @Autowired   
    private CourseService courseService;

   
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/adduser")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

  
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getusers")
    public List <User> fetchUsersList(){
        return userService.fetchUsersList();
    }

    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public User fetchUserById(@PathVariable("id") Long userId ){
        return userService.fetchUserById(userId);
    }

    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/email/{email}")
    public User fetchUserByEmail(@PathVariable("email") String email ){
        return userService.fetchUserByEmail(email);
    }

   
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/notRegCourses/{id}")
    public List<Course> getNotRegCourses( @PathVariable("id") Long userId ){
        return courseService.findUnenrolledCourses(userId);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/regCourses/{id}")
    public List<Course> getRegCourses( @PathVariable("id") Long userId ){
        return courseService.findEnrolledCourses(userId);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String userEmail = loginRequest.getUserEmail();
        String password = loginRequest.getPassword();

        if (userService.authenticateUser(userEmail, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }





}
