package com.searchforcollege.dao;
import java.util.List;
import com.searchforcolleges.entities.Student;
public interface StudentDao {
	 Student createStudent(Student student);
	    Student updateStudent(Student student);
	    Student getStudentById(long id);
	    List<Student> getAllStudents();
	    boolean deleteStudent(long id);

}
