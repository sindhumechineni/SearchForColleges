package com.searchforcollege;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.searchforcollege.dao.CourseDao;
import com.searchforcollege.dao.DepartmentDao;
import com.searchforcollege.daoimpl.CourseDaoImpl;
import com.searchforcollege.daoimpl.DepartmentDaoImpl;
import com.searchforcollege.util.HibernateUtil;
import com.searchforcolleges.entities.Course;
import com.searchforcolleges.entities.Department;

public class CourseOperations {
	private static Course course;
    static Scanner scanner = new Scanner(System.in);
    static CourseDao courseDao = new CourseDaoImpl(HibernateUtil.getSessionFactory());

    static void courseOperations(CourseDao courseDao) {
        // Course operations
        System.out.println("Course Operations:");
        System.out.println("1. Update Course");
        System.out.println("2. Create Course");
        System.out.println("3. Get Course by ID");
        System.out.println("4. Get All Courses");
        System.out.println("5. Delete Course");
        System.out.println("6.get all the courses in a department");
        System.out.println("0. Back to Main Menu");
        DepartmentDao departmentDao = new DepartmentDaoImpl(); // Initialize departmentDao object

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
            	updateCourseOperation(courseDao, departmentDao);
                break;
            case 2:
                createCourseOperation(courseDao,departmentDao);
                break;
            case 3:
                getCourseByIdOperation(courseDao);
                break;
            case 4:
                getAllCoursesOperation(courseDao);
                break;
            case 5:
                deleteCourseOperation(courseDao);
                break;
            case 6:
            	getCoursesByDepartmentIdOperation(courseDao,departmentDao);
            	break;
            case 0:
            	 System.out.println("Returning to main menu...");
                 return; // Return to main menu
             default:
                 System.out.println("Invalid choice");
         }
    }


///to delete course
///+++++++++++++++
    private static void deleteCourseOperation(CourseDao courseDao) {
        System.out.println("Enter authentication key for course:");
        String authKey = scanner.next();
        if (!authKey.equals("nan2")) { // Check authentication key
            System.out.println("Authentication failed. Operation aborted.");
            return;
        }
        System.out.println("Enter course ID:");
        Long courseId = scanner.nextLong();
        courseDao.deleteCourse(authKey, courseId);
    }

    private static void createCourseOperation(CourseDao courseDao, DepartmentDao departmentDao) {
        try {
            // Prompt user for authentication key and course details
            System.out.println("Enter authentication key for course:");
            String authKey = scanner.next();
            if (!authKey.equals("nan2")) { // Check authentication key
                System.out.println("Authentication failed. Operation aborted.");
                return;
            }

            System.out.println("Enter course ID:");
            Long courseId = scanner.nextLong();
            scanner.nextLine(); // Consume newline character

            System.out.println("Enter course name:");
            String name = scanner.nextLine();

            System.out.println("Enter credits:");
            int credits = scanner.nextInt();

            System.out.println("Enter duration:");
            String duration = scanner.next();

            System.out.println("Enter fee:");
            Long fee = scanner.nextLong();

            System.out.println("Enter seats available:");
            int noSeatsAvailable = scanner.nextInt();

            System.out.println("Enter department ID:");
            int departmentId = scanner.nextInt(); // Assuming user provides the department ID

            // Retrieve the Department entity from the database using departmentId
            Department department = null;
            try {
                department = departmentDao.getDepartmentById(departmentId);
                if (department == null) {
                    System.out.println("Department with ID " + departmentId + " does not exist.");
                    return; // Abort operation if department does not exist
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to retrieve department.");
                return; // Abort operation if department retrieval fails
            }

            // Create a new Course object with user-provided details
            Course course = new Course();
            course.setCOURSEID(courseId);
            course.setCOURSE_NAME(name);
            course.setCREDITS(credits);
            course.setDURATION(duration);
            course.setFEE(fee);
            course.setSEATS_AVALIABLE(noSeatsAvailable);
            course.setDepartments(department);

            // Call the createCourse method in CourseDaoImpl with authKey and course parameters
            try {
                Course createdCourse = courseDao.createCourse(authKey, course);
                if (createdCourse != null) {
                    System.out.println("Course created successfully.");
                } else {
                    System.out.println("Failed to create course.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to create course.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

////// to update course
///++++++++++++++
    private static void updateCourseOperation(CourseDao courseDao, DepartmentDao departmentDao) {
        try {
            // Prompt user for authentication key and course details
            System.out.println("Enter authentication key:");
            String authKey = scanner.next();
            if (!authKey.equals("nan2")) { // Check authentication key
                System.out.println("Authentication failed. Operation aborted.");
                return;
            }

            System.out.println("Enter course ID:");
            Long courseId = scanner.nextLong();
            scanner.nextLine(); // Consume newline character

            System.out.println("Enter course name:");
            String name = scanner.nextLine();

            System.out.println("Enter credits:");
            int credits = scanner.nextInt();

            System.out.println("Enter duration:");
            String duration = scanner.next();

            System.out.println("Enter fee:");
            Long fee = scanner.nextLong();

            System.out.println("Enter seats available:");
            int noSeatsAvailable = scanner.nextInt();

            System.out.println("Enter department ID:");
            int departmentId = scanner.nextInt(); // Assuming user provides the department ID

            // Retrieve the Department entity from the database using departmentId
            Department department = null;
            try {
                department = departmentDao.getDepartmentById(departmentId);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to retrieve department.");
                return; // Abort operation if department retrieval fails
            }

            // Create a new Course object with user-provided details
            Course course = new Course();
            course.setCOURSEID(courseId);
            course.setCOURSE_NAME(name);
            course.setCREDITS(credits);
            course.setDURATION(duration);
            course.setFEE(fee);
            course.setSEATS_AVALIABLE(noSeatsAvailable);
            course.setDepartments(department);

            // Call the updateCourse method in CourseDaoImpl with authKey and course parameters
            Course updatedCourse = courseDao.updateCourse(authKey, course);
            if (updatedCourse != null) {
                System.out.println("Course updated successfully.");
            } else {
                System.out.println("Failed to update course.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

///// to gte course by dourseid
///++++++++====

static void getCourseByIdOperation(CourseDao courseDao) {
    try {
        System.out.println("Enter course ID:");
        Long courseId = scanner.nextLong();

        // Retrieve the course by ID
        Course course = courseDao.getCourseById(courseId);

        // Print course details if found
        if (course != null) {
            System.out.println("Course ID: " + course.getCOURSEID());
            System.out.println("Course Name: " + course.getCOURSE_NAME());
            System.out.println("Credits: " + course.getCREDITS());
            System.out.println("Duration: " + course.getDURATION());
            System.out.println("Fee: " + course.getFEE());
            System.out.println("Seats Available: " + course.getSEATS_AVALIABLE());
            Department department = course.getDepartments();
            if (department != null) {
                System.out.println("Department ID: " + department.getDEPARTMENTID());
            } else {
                System.out.println("Department: Not Assigned");
            }
        } else {
            // Print a message if the course is not found
            System.out.println("Course not found.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    ///to get all courses
    //+++++++++++++++++
static void getAllCoursesOperation(CourseDao courseDao) {
    try {
        List<Course> courses = courseDao.getAllCourses();
        if (courses != null && !courses.isEmpty()) {
            // Display all courses
            System.out.println("All Courses:");
            for (Course course : courses) {
                System.out.println("Course ID: " + course.getCOURSEID());
                System.out.println("Course Name: " + course.getCOURSE_NAME());
                System.out.println("Credits: " + course.getCREDITS());
                System.out.println("Duration: " + course.getDURATION());
                System.out.println("Fee: " + course.getFEE());
                System.out.println("Seats Available: " + course.getSEATS_AVALIABLE());
                Department department = course.getDepartments();
                if (department != null) {
                    System.out.println("Department ID: " + department.getDEPARTMENTID());
                } else {
                    System.out.println("Department: Not Assigned");
                }
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No courses found.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Failed to retrieve courses.");
    }
}

static void getCoursesByDepartmentIdOperation(CourseDao courseDao, DepartmentDao departmentDao) {
    Scanner scanner = new Scanner(System.in);
    try {
        System.out.println("Enter Department ID:");
        int departmentId = scanner.nextInt();
        
        Department department = departmentDao.getDepartmentById(departmentId);
        if (department != null) {
            System.out.println("Department ID: " + department.getDEPARTMENTID());
        } else {
            System.out.println("Department: Not Assigned");
            return;
        }
        
        if (courseDao == null) {
            System.out.println("CourseDao is not initialized.");
            return;
        }
        
        List<Object[]> courses = courseDao.getCoursesByDepartmentId(departmentId);
        if (!courses.isEmpty()) {
            System.out.println("Courses for Department ID " + departmentId + ":");
            for (Object[] course : courses) {
                // Adjust the casting here
                Long courseId = (Long) course[0]; // Assuming courseId is a Long
                String courseName = (String) course[1];
                System.out.println("Course ID: " + courseId + ", Course Name: " + courseName);
            }
        } else {
            System.out.println("No courses found for Department ID " + departmentId);
        }

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Failed to retrieve courses for the department.");
    } finally {
        scanner.close();
    }
}
}