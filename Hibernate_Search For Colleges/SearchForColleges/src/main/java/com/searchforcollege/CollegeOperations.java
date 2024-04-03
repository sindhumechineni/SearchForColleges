package com.searchforcollege;

import java.util.List;
import java.util.Scanner;

import javax.persistence.OptimisticLockException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.searchforcollege.dao.CollegeDao;
import com.searchforcollege.daoimpl.CollegeDaoImpl;
import com.searchforcollege.util.HibernateUtil;
import com.searchforcolleges.entities.College;

public class CollegeOperations {
    static Scanner scanner = new Scanner(System.in);
    static CollegeDao collegeDao = new CollegeDaoImpl(HibernateUtil.getSessionFactory());   
    static DepartmentOperations departmentOperations = new DepartmentOperations(); // Create an instance

    static void collegeOperations(CollegeDao collegeDao) {
        boolean exit = false;
        while (!exit) {
            System.out.println("College Operations:");
            System.out.println("1. Update College");
            System.out.println("2. Get College by ID");
            System.out.println("3. Get All Colleges");
            System.out.println("4. Delete College");
            System.out.println("5. Create College");
            System.out.println("0. Back to Main Menu");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    updateCollegeOperation(collegeDao);
                    break;
                case 2:
                    getCollegeByIdOperation(collegeDao);
                    break;
                case 3:
                    getAllCollegesOperation(collegeDao);
                    break;
                case 4:
                    deleteCollegeOperation(collegeDao);
                    break;
                case 5:
                    createCollegeOperation(collegeDao);
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice");
            }
        }
    }



	static void createCollegeOperation(CollegeDao collegeDao) {
        while (true) {
            System.out.println("Enter authentication key:");
            String authKey = scanner.next();
            if (!authKey.equals("nan2")) { // Check authentication key
                System.out.println("Authentication failed. Operation aborted.");
                return;
            }
            System.out.println("Enter college ID:");
            int collegeId = scanner.nextInt();
            scanner.nextLine(); 
            System.out.println("Enter college name:");
            String name = scanner.nextLine();
            System.out.println("Enter college ranking:");
            int ranking = scanner.nextInt();
            System.out.println("Enter college website:");
            String website = scanner.next();
            System.out.println("Enter college address:");
            String address = scanner.next();

            College college = new College();
            college.setCOLLEGENAME(name);
            college.setCOLLEGEID(collegeId);
            college.setRANKING(ranking);
            college.setWEBSITE(website);
            college.setADDRESS(address);

            College createdCollege = collegeDao.createCollege(authKey, college);
            if (createdCollege != null) {
                System.out.println("College created successfully.");
                break; // Exit loop after successful operation
            } else {
                System.out.println("Failed to create college.");
            }
        }
    }

    static void updateCollegeOperation(CollegeDao collegeDao) {
        while (true) {
            System.out.println("Enter authentication key:");
            String authKey = scanner.next();
            if (!authKey.equals("nan2")) { // Check authentication key
                System.out.println("Authentication failed. Operation aborted.");
                return;
            }
            scanner.nextLine(); 
            System.out.println("Enter college name:");
            String name = scanner.nextLine();
            System.out.println("Enter college address:");
            String address = scanner.nextLine();
            System.out.println("Enter college ID:");
            int collegeId = scanner.nextInt();
            System.out.println("Enter college ranking:");
            int ranking = scanner.nextInt();
            System.out.println("Enter college website:");
            String website = scanner.next();

            College college = new College();
            college.setCOLLEGENAME(name);
            college.setADDRESS(address);
            college.setCOLLEGEID(collegeId);
            college.setRANKING(ranking);
            college.setWEBSITE(website);

            try {
                College updatedCollege = collegeDao.updateCollege(authKey, college);
                if (updatedCollege != null) {
                    System.out.println("College updated successfully.");
                    break; // Exit loop after successful operation
                } else {
                    System.out.println("Failed to update college.");
                }
            } catch (OptimisticLockException e) {
                System.out.println("Failed to update college due to concurrency issue. Please try again.");
            }
        }
    }

    static void getCollegeByIdOperation(CollegeDao collegeDao) {
        while (true) {
            System.out.println("Enter college ID:");
            int collegeId = scanner.nextInt();

            try {
                
                College college = collegeDao.getCollegeById(collegeId); 
                if (college != null) 
                {
                    System.out.println("College Information:");
                    System.out.println("ID: " + college.getCOLLEGEID());
                    System.out.println("Name: " + college.getCOLLEGENAME());
                    System.out.println("Location: " + college.getADDRESS());
                    System.out.println("Ranking: " + college.getRANKING());
                    System.out.println("Website: " + college.getWEBSITE());
                   
                } else {
                    System.out.println("College not found.");
                }

                break;
               
              
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to retrieve college information.");
            }
        }
    }

    static void getAllCollegesOperation(CollegeDao collegeDao) {
        while (true) {
            try {
                
                List<College> colleges = collegeDao.getAllColleges();
              

                if (colleges != null && !colleges.isEmpty()) {
                    System.out.println("All Colleges:");
                    for (College college : colleges) {
                        System.out.println("ID: " + college.getCOLLEGEID() + ", Name: " + college.getCOLLEGENAME() +
                                ", Location: " + college.getADDRESS() + ", Ranking: " + college.getRANKING() +
                                ", Website: " + college.getWEBSITE());
                    }
                } else {
                    System.out.println("No colleges found.");
                }
                break; // Exit loop after successful operation
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to retrieve colleges.");
            }
        }
    }

    static void deleteCollegeOperation(CollegeDao collegeDao) {
        while (true) {
        	
            System.out.println("Enter authentication key:");
            String authKey = scanner.next();
            if (!authKey.equals("nan2")) {
                System.out.println("Authentication failed. Operation aborted.");
                return;
            }
            System.out.println("Enter college ID:");
            int collegeId = scanner.nextInt();
            collegeDao.deleteCollege(authKey, collegeId);
            break; // Exit loop after successful operation
        }
    }

}


 	
