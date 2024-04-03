package com.searchforcolleges.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="colleges")
public class College
{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="COLLEGEID")
private int COLLEGEID;

@Column(name="COLLEGENAME")
private String COLLEGENAME;

@Column(name="ADDRESS")
private String ADDRESS;

@Column(name="WEBSITE")
private String WEBSITE;

@Column(name="RANKING")
private int RANKING;

@OneToMany(mappedBy = "colleges", cascade = CascadeType.ALL,orphanRemoval=true)
private List<Department> departments;



@ManyToMany(fetch=FetchType.EAGER)
@JoinTable(name = "college_course", joinColumns = @JoinColumn(name = "college_id"),
inverseJoinColumns = @JoinColumn(name = "course_id"))
private List<Course> courses;

public int getCOLLEGEID() {
	return COLLEGEID;
}

public void setCOLLEGEID(int cOLLEGEID) {
	COLLEGEID = cOLLEGEID;
}

public String getCOLLEGENAME() {
	return COLLEGENAME;
}

public void setCOLLEGENAME(String cOLLEGENAME) {
	COLLEGENAME = cOLLEGENAME;
}

public String getADDRESS() {
	return ADDRESS;
}

public void setADDRESS(String aDDRESS) {
	ADDRESS = aDDRESS;
}

public List<Department> getDepartments() {
	return departments;
}

public void setDepartments(List<Department> departments) {
	this.departments = departments;
}



public List<Course> getCourses() {
	return courses;
}

public void setCourses(List<Course> courses) {
	this.courses = courses;
}

public String getWEBSITE() {
	return WEBSITE;
}

public void setWEBSITE(String wEBSITE) {
	WEBSITE = wEBSITE;
}

public int getRANKING() {
	return RANKING;
}

public void setRANKING(int rANKING) {
	RANKING = rANKING;
}

public College() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "College [COLLEGEID=" + COLLEGEID + ", COLLEGENAME=" + COLLEGENAME + ", ADDRESS=" + ADDRESS + ", WEBSITE="
			+ WEBSITE + ", RANKING=" + RANKING + ", departments=" + departments 
			+ ", courses=" + courses + "]";
}


}

