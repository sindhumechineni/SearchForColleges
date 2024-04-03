package com.searchforcolleges.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Student")
public class Student
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENTID") // Specify the column name for the primary key
	 private Long STUDENTID;

  
	@Column(name = "STU_NAME")
    private String STU_NAME;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "STU_AGE")
    private int STU_AGE;
    
    @Column(name="STU_ADDRESS")
    private String STU_ADDRESS;
    
    @Column(name="STU_GENDER")
    private String STU_GENDER;
    
    @Column(name="STU_GPA")
    private int STU_GPA;
    
    @Column(name="STU_EMAIL")
    private String STU_EMAIL;

	public Long getSTUDENTID() {
		return STUDENTID;
	}

	public void setSTUDENTID(Long sTUDENTID) {
		STUDENTID = sTUDENTID;
	}
	
	public String getSTU_NAME() {
		return STU_NAME;
	}

	public void setSTU_NAME(String sTU_NAME) {
		STU_NAME = sTU_NAME;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSTU_AGE() {
		return STU_AGE;
	}

	public void setSTU_AGE(int sTU_AGE) {
		STU_AGE = sTU_AGE;
	}

	public String getSTU_ADDRESS() {
		return STU_ADDRESS;
	}

	public void setSTU_ADDRESS(String sTU_ADDRESS) {
		STU_ADDRESS = sTU_ADDRESS;
	}

	public String getSTU_GENDER() {
		return STU_GENDER;
	}

	public void setSTU_GENDER(String sTU_GENDER) {
		STU_GENDER = sTU_GENDER;
	}

	public int getSTU_GPA() {
		return STU_GPA;
	}

	public void setSTU_GPA(int sTU_GPA) {
		STU_GPA = sTU_GPA;
	}

	public String getSTU_EMAIL() {
		return STU_EMAIL;
	}

	public void setSTU_EMAIL(String sTU_EMAIL) {
		STU_EMAIL = sTU_EMAIL;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Student(String name, String gender, int age, double gpa, String address, String email) {
	   
	}


	@Override
	public String toString() {
		return "Student [STUDENTID=" + STUDENTID + ", STU_NAME=" + STU_NAME + ", lastName=" + lastName + ", STU_AGE="
				+ STU_AGE + ", STU_ADDRESS=" + STU_ADDRESS + ", STU_GENDER=" + STU_GENDER + ", STU_GPA=" + STU_GPA
				+ ", STU_EMAIL=" + STU_EMAIL + "]";
	}

	
    
  

}
