package rw.ac.unilak.mit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.unilak.mit.project.entity.Student;
import rw.ac.unilak.mit.project.service.StudentService;
import rw.ac.unilak.mit.project.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("create")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        Student newStudent = studentService.createStudent(student);
        if (newStudent != null) {
            return ResponseEntity.ok(newStudent);
        } else {
            return ResponseEntity.badRequest().body("An error occurred while creating student");
        }
    }

    @GetMapping("find-all")
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        Student currentStudent = studentService.getStudent(studentId);
        if (currentStudent == null) {
            return ResponseEntity.badRequest().body("Student not found");
        }

        // Check if email is being changed and already exists
        if (!currentStudent.getEmail().equals(student.getEmail()) &&
                studentRepository.existsByEmail(student.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        currentStudent.setFirstName(student.getFirstName());
        currentStudent.setLastName(student.getLastName());
        currentStudent.setEmail(student.getEmail());
        currentStudent.setPhoneNumber(student.getPhoneNumber());

        Student updatedStudent = studentService.createStudent(currentStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable int studentId) {
        Student currentStudent = studentService.getStudent(studentId);
        if (currentStudent == null) {
            return ResponseEntity.badRequest().body("Student not found");
        }

        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
