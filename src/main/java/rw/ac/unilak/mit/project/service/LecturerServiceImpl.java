package rw.ac.unilak.mit.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.unilak.mit.project.entity.Lecturer;
import rw.ac.unilak.mit.project.repository.LecturerRepository;

import java.util.List;

@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public Lecturer createLecturer(Lecturer lecturer) {
        if (lecturerRepository.existsByEmail(lecturer.getEmail())) {
            return null;
        }
        return lecturerRepository.save(lecturer);
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer getLecturerById(Long id) {
        return lecturerRepository.findById(id).orElse(null);
    }

    @Override
    public Lecturer updateLecturer(Long id, Lecturer lecturer) {
        Lecturer existing = lecturerRepository.findById(id).orElse(null);
        if (existing == null) return null;

        // Check if email is being changed and already exists
        if (!existing.getEmail().equals(lecturer.getEmail()) &&
                lecturerRepository.existsByEmail(lecturer.getEmail())) {
            return null;
        }

        existing.setName(lecturer.getName());
        existing.setEmail(lecturer.getEmail());
        existing.setSpecialization(lecturer.getSpecialization());
        existing.setHireDate(lecturer.getHireDate());

        return lecturerRepository.save(existing);
    }

    @Override
    public void deleteLecturer(Long id) {
        lecturerRepository.deleteById(id);
    }
}
