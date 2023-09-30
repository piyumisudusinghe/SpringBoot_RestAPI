package com.springboot.mysql.controller;

import com.springboot.mysql.modal.Student;
import com.springboot.mysql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id)
    {
        return studentService.getStudent(id);
    }

    @PostMapping("/student/")
    public ResponseEntity<?> createStudent(@RequestBody Student student )
    {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> removeStudent(@PathVariable Long id)
    {
        return studentService.removeStudent(id);
    }
}
