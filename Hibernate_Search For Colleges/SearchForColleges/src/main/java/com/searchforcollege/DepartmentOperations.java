package com.searchforcollege;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.employ.dao.EmployeeDao;
import com.employ.daoimpl.EmployeeDaoImpl;
import com.searchforcollege.dao.CollegeDao;
import com.searchforcollege.dao.CourseDao;
import com.searchforcollege.dao.DepartmentDao;

import com.searchforcollege.daoimpl.CollegeDaoImpl;
import com.searchforcollege.daoimpl.CourseDaoImpl;
import com.searchforcollege.daoimpl.DepartmentDaoImpl;

import com.searchforcollege.util.HibernateUtil;
import com.searchforcolleges.entities.College;
import com.searchforcolleges.entities.Course;
import com.searchforcolleges.entities.Department;


public class DepartmentOperations {
	
		
	DepartmentDao departmentDao=new DepartmentDaoImpl();   
	
	  static void departmentOperations(DepartmentDao departmentDao) 
	    {Scanner scanner = new Scanner(System.in);
   	 boolean exit = false;
     while (!exit) {
	        System.out.println("Department Operations:");
	        System.out.println("1. Update Department");
	        System.out.println("2.create Department");
	        System.out.println("3. Get Department by ID");
	        System.out.println("4. Get all Departments");
	        System.out.println("5. Delete Department");
	      
	        System.out.println("0. Back to Main Menu");
	        int choice = scanner.nextInt();
	        switch (choice) {
	            case 1:
	                updateDepartmentOperation(departmentDao, null);
	                break;
	            case 2:
	            	createDepartmentOperation(departmentDao, null);
	            	break;
	            case 3:
	                getDepartmentByIdOperation(departmentDao);
	                break;
	            case 4:
	            	getAllDepartmentsOperation(departmentDao);
	            	break;
	            case 5:
	                deleteDepartmentOperation(departmentDao);
	                break;
	            case 0:
	                 System.out.println("Returning to main menu...");
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice");
            }
	         }
     scanner.next();}
	 
	
	/////////////////////////
	    private static void deleteDepartmentOperation(DepartmentDao departmentDao) {	   
	    	Scanner scanner=new Scanner(System.in);  
	    	
	    	try {    	            	     
	   	        System.out.println("Enter authentication key:");
	    	        String authKey = scanner.next();
	    	        if (!authKey.equals("nan2")) { // Check authentication key
	    	            System.out.println("Authentication failed. Operation aborted.");
	    	            return;
	    	        }

	    	      
	    	        System.out.println("Enter department ID:");
	    	        int departmentId = scanner.nextInt();

	    	        // Call deleteDepartment method in DepartmentDaoImpl with authKey and departmentId parameters
	    	        departmentDao.deleteDepartment(authKey, departmentId);

	    	       
	    	    } catch (Exception e) {
	    	    
	    	        e.printStackTrace();
	    	        System.out.println("Failed to delete department");
	    	    }
	    	}

	
		//////update department////
	    //+++++++++++++
    static public void updateDepartmentOperation(DepartmentDao departmentDao, List<College> colleges) {
   	 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	CollegeDao collegeDao = new CollegeDaoImpl(sessionFactory);
    	Scanner scanner = new Scanner(System.in);
	        try {
	            // Prompt user for authentication key
	            System.out.println("Enter authentication key:");
	            String authKey = scanner.next();
	            if (!authKey.equals("nan2")) { // Check authentication key
	                System.out.println("Authentication failed. Operation aborted.");
	                return;
	            }

	            // Prompt user for department ID and new details
	            System.out.println("Enter department ID:");
	            int departmentId = scanner.nextInt();
	            scanner.nextLine(); // Consume newline character

	            System.out.println("Enter department name:");
	            String name = scanner.nextLine();

	            System.out.println("Enter HOD:");
	            String hod = scanner.nextLine();

	            System.out.println("Enter college ID:");
		        int collegeId = Integer.parseInt(scanner.nextLine());// Assuming user provides the college ID
		        College college = collegeDao.getCollegeById(collegeId);
	            // Retrieve the College entity from the list of colleges using collegeId
	            
	            // Create a new Department object with the updated details
	            Department department = new Department();
	            department.setDEPARTMENTID(departmentId);
	            department.setDEPARTMENT_NAME(name);
	            department.setHOD(hod);
	            department.setCollege(college);

	            // Call the updateDepartment method in DepartmentDao to persist the updated department
	            departmentDao.updateDepartment(authKey, department);

	            // Commit transaction after successful update
	            System.out.println("Department updated successfully.");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Failed to update department");
	        } finally {
	            scanner.close();
	        }
	    }
    
	  ///create/////
	  ///=========
    private static void createDepartmentOperation(DepartmentDao departmentDao, List<College> colleges)  {
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	CollegeDao collegeDao = new CollegeDaoImpl(sessionFactory);
    	Scanner scanner = new Scanner(System.in);
		  try {
	            // Prompt user for authentication key
	            System.out.println("Enter authentication key:");
	            String authKey = scanner.next();
	            if (!authKey.equals("nan2")) { // Check authentication key
	                System.out.println("Authentication failed. Operation aborted.");
	                return;
	            }
	         
	            // Prompt user for department details
	            System.out.println("Enter department ID:");
	            int departmentId = scanner.nextInt();
	            scanner.nextLine(); // Consume newline character

	            System.out.println("Enter department name:");
	            String name = scanner.nextLine();

	            System.out.println("Enter HOD:");
	            String hod = scanner.nextLine();

	            System.out.println("Enter college ID:");
		        int collegeId = Integer.parseInt(scanner.nextLine());// Assuming user provides the college ID
		        College college = collegeDao.getCollegeById(collegeId);
	            // Create a new Department object with the provided details
	            Department department = new Department();
	            department.setDEPARTMENTID(departmentId);
	            department.setDEPARTMENT_NAME(name);
	            department.setHOD(hod);
	            department.setCollege(college);

	            // Call the createDepartment method in DepartmentDao to persist the department
	            departmentDao.createDepartment(authKey, department);

	            // Commit transaction after successful creation
	            System.out.println("Department created successfully.");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Failed to create department");
	        } finally {
	            scanner.close();
	        }
	    }
	
////get by department id/////
////+++++++++++++++
    static void getDepartmentByIdOperation(DepartmentDao departmentDao) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter department ID:");
            int departmentId = scanner.nextInt();
            Department department = departmentDao.getDepartmentById(departmentId);

            if (department != null) {
                System.out.println("Department Information:");
                System.out.println("DEPARTMENT ID: " + department.getDEPARTMENTID());
                System.out.println("DEPARTMENT Name: " + department.getDEPARTMENT_NAME());
                System.out.println("Department HOD: " + department.getHOD());

                College college = department.getCollege();
                if (college != null) {
                    System.out.println("COLLEGE ID: " + college.getCOLLEGEID());
                    // Print other college details as needed
                } else {
                    System.out.println("Associated college not found.");
                }
            } else {
                System.out.println("Department not found for ID: " + departmentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving department.");
        } finally {
            scanner.close(); // Close the scanner to prevent resource leak
        }
    }
    
	
///to get all departments////
///+++++++++++++++
	  static void getAllDepartmentsOperation(DepartmentDao departmentDao) {
		    
		    try {
		        // Begin transaction
		       

		        List<Department> departments = departmentDao.getAllDepartments();

		        if (departments != null && !departments.isEmpty()) {
		            System.out.println("All Departments:");
		            for (Department department : departments) {
		                System.out.println("Department Information:");
		                System.out.println("DEPARTMENT ID: " + department.getDEPARTMENTID());
		                System.out.println("DEPARTMENT Name: " + department.getDEPARTMENT_NAME());
		                System.out.println("DEPARTMENT HOD: " + department.getHOD());

		                College college = department.getCollege(); // Retrieve associated college
		                if (college != null) {
		                    System.out.println("College ID: " + college.getCOLLEGEID());
		                } else {
		                    System.out.println("Associated college not found");
		                }

		                List<Course> courses = department.getCourse(); // Retrieve associated courses
		                if (courses != null && !courses.isEmpty()) {
		                    System.out.println("Courses:");
		                    for (Course course : courses) {
		                        System.out.println("Course ID: " + course.getCOURSEID());
		                        // Print other course details as needed
		                    }
		                } else {
		                    System.out.println("No courses found for this department.");
		                }

		                System.out.println("-------------------");
		            }
		        } else {
		            System.out.println("No departments found.");
		        }
		        // Commit transaction after retrieving all departments
		    } catch (Exception e) {
		       
		        
		        e.printStackTrace();
		        System.out.println("Failed to retrieve departments.");
		    }
		
	 }


}
