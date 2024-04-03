package com.searchforcollege.daoimpl;
import java.util.List;
import com.searchforcollege.dao.StudentDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.searchforcolleges.entities.Student;

public class StudentDaoImpl implements StudentDao {
    
   
    private final SessionFactory sessionFactory;
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Student createStudent(Student student) {
        // Open a session and begin a transaction for database operations
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            // Save the student entity to the database
            session.save(student);
            // Commit the transaction to persist the changes
            session.getTransaction().commit();
            // Return the created student entity
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // Method to update an existing student record
    @Override
    public Student updateStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            // Update the student entity in the database
            session.update(student);
            session.getTransaction().commit();
            System.out.println("Student updated successfully.");
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve a student by their ID
    @Override
  public  Student getStudentById(long id) {
        try (Session session = sessionFactory.openSession()) {
            // Retrieve the student entity from the database by its ID
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve all students from the database
    @Override
    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            // Retrieve all student entities from the database
            return session.createQuery("FROM Student", Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete a student by their ID
    @Override
    public boolean deleteStudent(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            // Retrieve the student entity by its ID
            Student student = session.get(Student.class, id);
            if (student != null) {
                // Delete the student entity from the database
                session.delete(student);
                session.getTransaction().commit();
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;

    }

}