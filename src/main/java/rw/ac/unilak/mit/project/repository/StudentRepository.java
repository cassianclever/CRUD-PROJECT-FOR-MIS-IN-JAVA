package rw.ac.unilak.mit.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.unilak.mit.project.entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);
}
