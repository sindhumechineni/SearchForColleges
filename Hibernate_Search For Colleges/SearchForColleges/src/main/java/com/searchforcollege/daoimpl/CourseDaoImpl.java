package com.searchforcollege.daoimpl;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.searchforcollege.dao.CourseDao;
import com.searchforcollege.util.HibernateUtil;
import com.searchforcolleges.entities.Course;

public class CourseDaoImpl implements CourseDao {

    private final SessionFactory sessionFactory;   
    public CourseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    
    @Override
    public void deleteCourse(String authKey, Long courseId) {
        String courseKey = "nan2";

        if (!authenticate(courseKey, authKey)) {
            System.out.println("Authentication failed. Course deletion aborted.");
            return;
        }

        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction(); // Begin transaction

            Course course = session.get(Course.class, courseId); // Retrieve course by ID
            if (course != null) {
                session.delete(course); // Perform deletion operation
                transaction.commit(); // Commit transaction
                System.out.println("Course deleted successfully.");
            } else {
                System.out.println("Course not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to delete the course.");
        }
    }

    public boolean authenticate(String courseKey, String authKey) {
        return authKey.equals(courseKey);
    }

    
    
    @Override
    public List<Course> getAllCourses() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query<Course> query = session.createQuery("FROM Course", Course.class);
            List<Course> courses = query.list();
            transaction.commit();
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Course updateCourse(String authKey, Course course) {
        String courseKey = "nan2";

        if (!authenticate(courseKey, authKey)) {
            System.out.println("Authentication failed. Course update aborted.");
            return null;
        }

        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction(); // Begin transaction
            session.update(course);
            transaction.commit();
            // Retrieve the existing course entity from the database           
                return course;
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to update the course.");
            return null;
        }
    }

    @Override
    public Course createCourse(String authKey, Course course) {
        if (!authenticate("nan2", authKey)) {
            System.out.println("Authentication failed. Course creation aborted.");
            return null;
        }

        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
            
            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Course getCourseById(long id)  {
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            transaction.commit();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

 
    @Override
    public List<Object[]> getCoursesByDepartmentId(int departmentId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "SELECT c.COURSEID, c.COURSE_NAME FROM Course c WHERE c.departments.id = :departmentId";
            Query<Object[]> query = session.createQuery(hql);
            query.setParameter("departmentId", departmentId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

} 

