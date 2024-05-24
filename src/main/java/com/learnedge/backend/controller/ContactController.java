package com.learnedge.backend.controller;

import com.learnedge.backend.request.ContactFormDto;
import com.learnedge.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContactController {

    @Autowired
    private EmailService emailService;
    
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/submit")
    public ResponseEntity<String> handleContactForm(@RequestBody ContactFormDto contactForm) {
        // Process form data
        String receiverAddress = contactForm.getEmail();
        String subject = contactForm.getSubject();
        String message = contactForm.getMessage();

        // Send email
        try {
            emailService.sendEmail(receiverAddress, subject, message);
            return ResponseEntity.ok("Form submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit form");
        }}

}