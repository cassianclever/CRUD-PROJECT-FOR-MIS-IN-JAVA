package rw.ac.unilak.mit.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.unilak.mit.project.entity.Course;
import rw.ac.unilak.mit.project.repository.CourseRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        if (courseRepository.existsByTitle(course.getTitle())) {
            return null;
        }
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse == null) return null;

        // Check if title is being changed and already exists
        if (!existingCourse.getTitle().equals(course.getTitle()) &&
                courseRepository.existsByTitle(course.getTitle())) {
            return null;
        }

        existingCourse.setTitle(course.getTitle());
        existingCourse.setCredits(course.getCredits());
        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
