package rw.ac.unilak.mit.project.service;

import rw.ac.unilak.mit.project.entity.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student getStudent(int id);
    List<Student> getAllStudents();
    void deleteStudent(int id);
}
