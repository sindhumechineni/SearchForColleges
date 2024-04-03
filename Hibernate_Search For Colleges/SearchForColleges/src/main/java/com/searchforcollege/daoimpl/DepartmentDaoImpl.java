package com.searchforcollege.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.searchforcollege.util.HibernateUtil;
import com.searchforcollege.dao.DepartmentDao;
import com.searchforcolleges.entities.Department;


public class DepartmentDaoImpl implements DepartmentDao {
 public SessionFactory sessionFactory;

public DepartmentDaoImpl (){
}
public DepartmentDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
}


	private boolean authenticate(String departmentKey, String authKey) {
        return authKey.equals(departmentKey);
    }
	
	/////////////////
    @Override
    public void deleteDepartment(String authKey, int departmentId) {
    	  if (!authenticate("nan2", authKey)) {
              System.out.println("Authentication failed. Department update aborted.");
              return;
          }
      
		 try(Session session=HibernateUtil.getSession())
		 {
			  session.beginTransaction(); // Start transaction
            Department department = getDepartmentById(departmentId);

            if (department != null) {
                session.delete(department);
                session.getTransaction().commit();
                System.out.println("Department deleted successfully.");
            } else {
                System.out.println("Department not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to delete the department.");
        }
    }
//////////////////////////
    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = null;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Query<Department> query = session.createQuery("FROM Department", Department.class);
            departments = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }
//////////////////////////////////

    @Override
    public Department updateDepartment(String authKey, Department department) {
        if (!authenticate("nan2", authKey)) {
            System.out.println("Authentication failed. Department update aborted.");
            return null;
        }

        try (Session session = HibernateUtil.getSession()) {
         
            Transaction transaction = session.beginTransaction();
            session.update(department); // Update the department in the database
            transaction.commit(); // Commit the transaction
            return department;
        } catch (Exception e) {
            e.printStackTrace();
           
        } return null;
    }

    @Override
    public Department createDepartment(String authKey, Department department) {
        if (!authenticate("nan2", authKey)) {
            System.out.println("Authentication failed. Department creation aborted.");
            return null;
        }

        try (Session session = HibernateUtil.getSession()) {          
            Transaction transaction = session.beginTransaction(); 
            session.save(department); // Save the department to the database
            transaction.commit(); // Commit the transaction
            return department;
        } catch (Exception e) {
            e.printStackTrace();
            
        }return null;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Department department = session.get(Department.class, departmentId);
            return department;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}