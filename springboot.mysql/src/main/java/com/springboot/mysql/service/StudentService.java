package com.springboot.mysql.service;

import com.springboot.mysql.modal.Student;
import com.springboot.mysql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id)
    {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student createStudent(Student student)
    {
        studentRepository.save(student);
        return studentRepository.findById(student.getId()).orElse(null);
    }

    public String removeStudent(Long id)
    {
        studentRepository.deleteById(id);
        return "Successfully removed the student with id : " + id;
    }
}
