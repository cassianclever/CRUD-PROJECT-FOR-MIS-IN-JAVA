package rw.ac.unilak.mit.project.service;

import rw.ac.unilak.mit.project.entity.Lecturer;

import java.util.List;

public interface LecturerService {
    Lecturer createLecturer(Lecturer lecturer);
    List<Lecturer> getAllLecturers();
    Lecturer getLecturerById(Long id);
    Lecturer updateLecturer(Long id, Lecturer lecturer);
    void deleteLecturer(Long id);
}
