
package com.searchforcollege;

import java.util.Scanner;

import com.searchforcollege.dao.CollegeDao;
import com.searchforcollege.dao.CourseDao;
import com.searchforcollege.dao.DepartmentDao;
import com.searchforcollege.dao.StudentDao;
import com.searchforcollege.daoimpl.CollegeDaoImpl;
import com.searchforcollege.daoimpl.CourseDaoImpl;
import com.searchforcollege.daoimpl.DepartmentDaoImpl;
import com.searchforcollege.daoimpl.StudentDaoImpl;
import com.searchforcollege.util.HibernateUtil;

public class App {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       // LecturerOperations lecturerOperations = new LecturerOperations();
        CourseOperations courseOperations = new CourseOperations();
        CollegeOperations collegeOperations = new CollegeOperations();
        StudentOperations studentOperations = new StudentOperations();
        DepartmentOperations departmentOperations = new DepartmentOperations();

        CollegeDao collegeDao = new CollegeDaoImpl(HibernateUtil.getSessionFactory());
      //  LecturerDao lecturerDao = new LecturerDaoImpl(HibernateUtil.getSessionFactory());
        DepartmentDao departmentDao = new DepartmentDaoImpl(HibernateUtil.getSessionFactory());
        CourseDao courseDao = new CourseDaoImpl(HibernateUtil.getSessionFactory());
        StudentDao studentDao = new StudentDaoImpl(HibernateUtil.getSessionFactory());
       

        int choice;

        do {
            System.out.println("Choose an option:");
         //   System.out.println("1. Lecturer Operations");
            System.out.println("1. Department Operations");
            System.out.println("2. Course Operations");
            System.out.println("3. Student Operations");
            System.out.println("4. College Operations");
            System.out.println("0. Exit");
            choice = scanner.nextInt();

            switch (choice) {
               // case 1:
                 //   lecturerOperations.lecturerOperations(lecturerDao);
                  //  break;
                case 1:
                    departmentOperations.departmentOperations(departmentDao);
                    break;
                case 2:
                    courseOperations.courseOperations(courseDao);
                    break;
                case 3:
                    studentOperations.studentOperations(studentDao);
                    break;
                case 4:
                    collegeOperations.collegeOperations(collegeDao);
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }
}
