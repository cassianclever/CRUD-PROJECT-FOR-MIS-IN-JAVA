package rw.ac.unilak.mit.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.unilak.mit.project.entity.Lecturer;

import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    Optional<Lecturer> findByEmail(String email);
    boolean existsByEmail(String email);
}
