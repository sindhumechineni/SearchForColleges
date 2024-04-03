package com.searchforcollege.dao;
import java.util.List;
import com.searchforcolleges.entities.Course;

public interface CourseDao {

    void deleteCourse(String authKey, Long courseId);
    Course updateCourse(String authKey, Course course);
    Course createCourse(String authKey, Course course);
    List<Course> getAllCourses();
	Course getCourseById(long id);
	List<Object[]> getCoursesByDepartmentId(int departmentId);
}
