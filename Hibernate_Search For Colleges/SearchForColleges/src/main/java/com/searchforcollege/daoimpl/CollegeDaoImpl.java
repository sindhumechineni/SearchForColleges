package com.searchforcollege.daoimpl;
import org.hibernate.query.Query;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.searchforcollege.dao.CollegeDao;
import com.searchforcollege.util.HibernateUtil;
import com.searchforcolleges.entities.College;
import com.searchforcolleges.entities.Department;

public class CollegeDaoImpl implements CollegeDao 
{
    private SessionFactory sessionFactory = null;
    public CollegeDaoImpl(SessionFactory sessionFactory) //constructor
    {
    this.sessionFactory = sessionFactory;
    }
    
    
    //Operations
    @Override
    public void deleteCollege(String authKey, int collegeId) 
    {
        if (!authenticate("nan2", authKey))
        {
            System.out.println("Authentication failed. College deletion aborted.");
            return;
        }
        try
        { 
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction(); // Begin transaction
            College college = session.get(College.class, collegeId);
            if (college != null) {
                session.delete(college); // Perform deletion operation
                transaction.commit(); // Commit transaction
                System.out.println("College deleted successfully.");
            } else {
                System.out.println("College not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to delete the college.");
        }
    }
/////////////////////////
    private boolean authenticate(String collegeKey, String authKey) {
        return authKey.equals(collegeKey);
    }
//////////////////////////
    @Override
    public List<College> getAllColleges() {
        Transaction transaction = null;
        List<College> colleges = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<College> query = session.createQuery("FROM College", College.class);
            colleges = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return colleges;
    }


///////////////////////
    @Override
    public College updateCollege(String authKey, College college)
    {
        if (!authenticate("nan2", authKey)) 
        {
            System.out.println("Authentication failed. College update aborted.");
            return null;
        }

        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction(); 
            // Begin transaction
            // Update the college object
            session.update(college);
            transaction.commit(); // Commit transaction
            return college;
        }
        catch (Exception e) {
            e.printStackTrace();
           
        } return null;
    }
    ///////////////////////////////
    @Override
    public College createCollege(String authKey, College college) {
        if (!authenticate("nan2", authKey)) {
            System.out.println("Authentication failed. College creation aborted.");
            return null;
        }

        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction(); // Begin transaction

            session.save(college); // Save the college entity

            transaction.commit(); // Commit transaction

            return college;
        } catch (Exception e) {
            e.printStackTrace();
            
        }return null;
    }
    //////////////////////////////
    @Override
    public College getCollegeById(int collegeId) 
    {       
        try (Session session = sessionFactory.openSession()) {
            // Begin the transaction
        	  Transaction transaction = session.beginTransaction();            
            College college = session.get(College.class, collegeId);            
            // Commit the transaction
           transaction.commit();            
            return college;
       
        } catch (Exception e) {
            e.printStackTrace();
            
        }return null;
    }


}
