package com.springboot.mysql.util;

import com.springboot.mysql.exception.ValidationException;
import com.springboot.mysql.modal.Student;

public class StudentValidator
{
    public static void studentValidator(Student student ) throws ValidationException
    {
        if(student.getId() == null )
        {
            throw new ValidationException("Student Id is not specified");
        } else if ( student.getFirstName().isBlank())
        {
            throw new ValidationException("Student first name is not specified");
        } else if ( student.getLastName().isBlank())
        {
            throw new ValidationException("Student last name is not specified");
        } else if ( student.getBirthday() == null )
        {
            throw new ValidationException("Student birthday is not specified");
        } else if (student.getDepartment().isBlank())
        {
            throw new ValidationException("Student department is not specified");
        }
    }
}
