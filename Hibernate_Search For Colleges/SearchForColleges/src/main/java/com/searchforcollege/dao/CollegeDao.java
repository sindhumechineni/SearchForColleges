package com.searchforcollege.dao;
import java.util.List;
import com.searchforcolleges.entities.College;
import com.searchforcolleges.entities.Course;
import com.searchforcolleges.entities.Department;
public interface CollegeDao {
		void deleteCollege(String authKey, int id);
		College updateCollege(String authKey, College college);
		College createCollege(String authKey, College college);		  
	    College getCollegeById(int collegeId);
		List<College> getAllColleges();
		
	    }
