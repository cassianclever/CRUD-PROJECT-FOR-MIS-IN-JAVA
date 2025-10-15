package rw.ac.unilak.mit.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.unilak.mit.project.entity.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTitle(String title);
    boolean existsByTitle(String title);
}
