package com.searchforcolleges.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Table(name="departments")

public class Department
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEPARTMENTID")
	private int DEPARTMENTID;
	
	@Column(name="DEPARTMENT_NAME")
	private String DEPARTMENT_NAME;
	
	@Column(name="HOD")
	private String HOD;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "COLLEGEID") 
	private College colleges; 

	public College getCollege() {
		return colleges;
	}

	public void setCollege(College college) {
		this.colleges = college;
	}
	//@OneToMany(mappedBy="departments",cascade = CascadeType.ALL,orphanRemoval=true)
	// private List<Lecturer>lecturers;
	
	@OneToMany(mappedBy="departments",cascade = CascadeType.ALL,orphanRemoval=true)
	private List<Course>course;

	public int getDEPARTMENTID() {
		return DEPARTMENTID;
	}

	public void setDEPARTMENTID(int dEPARTMENTID) {
		DEPARTMENTID = dEPARTMENTID;
	}

	public String getDEPARTMENT_NAME() {
		return DEPARTMENT_NAME;
	}

	public void setDEPARTMENT_NAME(String dEPARTMENT_NAME) {
		DEPARTMENT_NAME = dEPARTMENT_NAME;
	}

	public String getHOD() {
		return HOD;
	}

	public void setHOD(String hOD) {
		HOD = hOD;
	}
//	public List<Lecturer> getLecturers() {
	//	return lecturers;
	//}

	//public void setLecturers(List<Lecturer> lecturers) {
	//	this.lecturers = lecturers;
	//}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Department [DEPARTMENTID=" + DEPARTMENTID + ", DEPARTMENT_NAME=" + DEPARTMENT_NAME + ", HOD=" + HOD
				+ ", colleges=" + colleges +  ", course=" + course + "]";
	}
	//department have  one to many relation with course

}