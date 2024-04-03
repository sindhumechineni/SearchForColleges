package com.searchforcollege.dao;
import java.util.List;
import com.searchforcolleges.entities.Department;

	public interface DepartmentDao {
		 void deleteDepartment(String authKey, int id);
	    Department createDepartment(String authKey,Department department);
	   Department updateDepartment(String authKey,Department department);
	    Department getDepartmentById(int id);
	    List<Department> getAllDepartments();

	}



