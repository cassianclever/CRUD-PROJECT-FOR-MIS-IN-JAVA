package rw.ac.unilak.mit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.unilak.mit.project.entity.Course;
import rw.ac.unilak.mit.project.service.CourseService;
import rw.ac.unilak.mit.project.repository.CourseRepository;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        if (courseRepository.existsByTitle(course.getTitle())) {
            return ResponseEntity.badRequest().body("Course title already exists");
        }

        Course newCourse = courseService.createCourse(course);
        return ResponseEntity.ok(newCourse);
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.badRequest().body("Course not found");
        }
        return ResponseEntity.ok(course);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        if (updatedCourse == null) {
            return ResponseEntity.badRequest().body("Course title already exists or course not found");
        }
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.badRequest().body("Course not found");
        }
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
}
