package com.springboot.mysql.service;

import com.springboot.mysql.exception.ValidationException;
import com.springboot.mysql.modal.ErrorSource;
import com.springboot.mysql.modal.Student;
import com.springboot.mysql.repository.StudentRepository;
import com.springboot.mysql.util.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity<List<Student>> getAllStudents()
    {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getStudent(Long id)
    {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent())
        {
            return new ResponseEntity<>(student,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createStudent(Student student)
    {
        try
        {
            StudentValidator.studentValidator(student);
            studentRepository.save(student);
            Optional<Student> newStudent =  studentRepository.findById(student.getId());
            if(newStudent.isPresent())
            {
                return new ResponseEntity<>(newStudent.get(),HttpStatus.CREATED);
            }
            ErrorSource errorSource = new ErrorSource("ERR_IN_USER_CREATION","Error has occurred while creating the Student");
            return new ResponseEntity<>(errorSource,HttpStatus.INTERNAL_SERVER_ERROR);

        }
        catch (ValidationException ex)
        {
            ErrorSource errorSource = new ErrorSource("ERR_INVALID_INPUT",ex.getMessage());
            return new ResponseEntity<>(errorSource,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> removeStudent(Long id)
    {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent())
        {
            try
            {
                studentRepository.deleteById(id);
                return new ResponseEntity<>(null,HttpStatus.OK);
            }
            catch(Exception exception)
            {
                ErrorSource errorSource = new ErrorSource("ERR_IN_RESOURCE_DELETION",exception.getMessage());
                return new ResponseEntity<>(errorSource,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
