package com.example.emailwriter.controller;

import com.example.emailwriter.models.Requests.EmailRequest;
import com.example.emailwriter.service.EmailWriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EmailWriterController {
    private final EmailWriterService emailWriterService;

    @PostMapping("/generate")
    public ResponseEntity<String> generate(@RequestBody EmailRequest request){
        try {
            String response = emailWriterService.generate(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
