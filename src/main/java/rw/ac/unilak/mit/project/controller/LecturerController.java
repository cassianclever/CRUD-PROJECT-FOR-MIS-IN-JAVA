package rw.ac.unilak.mit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.unilak.mit.project.entity.Lecturer;
import rw.ac.unilak.mit.project.service.LecturerService;
import rw.ac.unilak.mit.project.repository.LecturerRepository;

import java.util.List;

@RestController
@RequestMapping("/lecturers")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private LecturerRepository lecturerRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createLecturer(@RequestBody Lecturer lecturer) {
        if (lecturerRepository.existsByEmail(lecturer.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        Lecturer newLecturer = lecturerService.createLecturer(lecturer);
        return ResponseEntity.ok(newLecturer);
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> getAllLecturers() {
        return ResponseEntity.ok(lecturerService.getAllLecturers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLecturerById(@PathVariable Long id) {
        Lecturer lecturer = lecturerService.getLecturerById(id);
        if (lecturer == null) {
            return ResponseEntity.badRequest().body("Lecturer not found");
        }
        return ResponseEntity.ok(lecturer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLecturer(@PathVariable Long id, @RequestBody Lecturer lecturer) {
        Lecturer updated = lecturerService.updateLecturer(id, lecturer);
        if (updated == null) {
            return ResponseEntity.badRequest().body("Email already exists or Lecturer not found");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLecturer(@PathVariable Long id) {
        Lecturer lecturer = lecturerService.getLecturerById(id);
        if (lecturer == null) {
            return ResponseEntity.badRequest().body("Lecturer not found");
        }
        lecturerService.deleteLecturer(id);
        return ResponseEntity.ok("Lecturer deleted successfully");
    }
}
