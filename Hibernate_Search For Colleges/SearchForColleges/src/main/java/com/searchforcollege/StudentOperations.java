package com.searchforcollege;

import java.util.List;
import java.util.Scanner;

import com.searchforcollege.dao.StudentDao;
import com.searchforcollege.daoimpl.StudentDaoImpl;
import com.searchforcollege.util.HibernateUtil;
import com.searchforcolleges.entities.Student;

public class StudentOperations {

    static StudentDao studentDao = new StudentDaoImpl(HibernateUtil.getSessionFactory());
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        studentOperations(studentDao);
    }

    static void studentOperations(StudentDao studentDao) {
        while (true) {
            // Student operations menu
            System.out.println("Student Operations:");
            System.out.println("1. Update Student");
            System.out.println("2. Create Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Get All Students");
            System.out.println("5. Get Student by ID");
            System.out.println("0. Back to Main Menu");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    updateStudentOperation();
                    break;
                case 2:
                    createStudentOperation();
                    break;
                case 3:
                    deleteStudentOperation();
                    break;
                case 4:
                    getAllStudentsOperation();
                    break;
                case 5:
                    getStudentByIdOperation();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static void updateStudentOperation() {
        // Prompt user for student ID and new details
        System.out.println("Enter student ID to update:");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println("Enter updated student name:");
        String name = scanner.nextLine();
        System.out.println("Enter updated student age:");
        int age = scanner.nextInt();
        System.out.println("Enter student address:");
        String address = scanner.next();
        System.out.println("Enter student gender:");
        String gender = scanner.next();
        System.out.println("Enter student GPA:");
        int gpa = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println("Enter student email:");
        String email = scanner.nextLine();
        // Create a new Student object with the updated details
        Student updatedStudent = new Student();
        updatedStudent.setSTUDENTID((long) studentId);
        updatedStudent.setSTU_NAME(name);
        updatedStudent.setSTU_AGE(age);
        updatedStudent.setSTU_ADDRESS(address);
        updatedStudent.setSTU_GENDER(gender);
        updatedStudent.setSTU_GPA(gpa);
        updatedStudent.setSTU_EMAIL(email);

        studentDao.updateStudent(updatedStudent);
        System.out.println("Student updated successfully.");
    }
    static void createStudentOperation() {
        System.out.println("Enter student name:");
        String studentName = scanner.next();
        System.out.println("Enter student gender:");
        String gender = scanner.next();
        System.out.println("Enter student age:");
        int age = scanner.nextInt();
        System.out.println("Enter student address:");
        String address = scanner.next();
        System.out.println("Enter student GPA:");
        int gpa = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println("Enter student email:");
        String email = scanner.nextLine();

        // Create a new Student object with the input details
        Student newStudent = new Student();
        newStudent.setSTU_NAME(studentName);
        newStudent.setSTU_AGE(age);
        newStudent.setSTU_ADDRESS(address);
        newStudent.setSTU_GENDER(gender);
        newStudent.setSTU_GPA(gpa);
        newStudent.setSTU_EMAIL(email);

        // Call DAO method to create the student
        if (studentDao.createStudent(newStudent) != null) {
            System.out.println("Student created successfully.");
           
        } else {
            System.out.println("Failed to create student.");
        }
    }

    // Delete student
    static void deleteStudentOperation() {
        System.out.println("Enter student ID to delete:");
        int studentId = scanner.nextInt();

        boolean deletionStatus = studentDao.deleteStudent(studentId);

        if (deletionStatus) {
            System.out.println("Failed to delete student");
        } else {
            System.out.println("Student deleted successfully");
        }
    }

    // Get all students
    static void getAllStudentsOperation() {
        List<Student> students = studentDao.getAllStudents();
        if (students != null && !students.isEmpty()) {
            System.out.println("All Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        } else {
            System.out.println("No students found.");
        }
    }
    
    // Get student by ID
    static void getStudentByIdOperation() {
        System.out.println("Enter student ID:");
        int studentId = scanner.nextInt();

        Student student = studentDao.getStudentById(studentId);
        if (student != null) {
            System.out.println("Student Information:");
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }
}