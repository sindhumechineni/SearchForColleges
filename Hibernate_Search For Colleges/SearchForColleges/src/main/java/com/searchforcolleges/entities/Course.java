package com.searchforcolleges.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course
{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="COURSEID")
private Long COURSEID;

@Column(name="COURSE_NAME")
private String COURSE_NAME;

@Column(name="DURATION")
private String DURATION;

@Column(name="CREDITS")
private int CREDITS;

@ManyToOne(cascade=CascadeType.ALL)
@JoinColumn(name="DEPARTMENTID")
private Department departments;


@ManyToMany(mappedBy = "courses",fetch=FetchType.EAGER)
private List<College> colleges;

@Column(name="SEATS_AVALIABLE")
private int SEATS_AVALIABLE;

@Column(name="FEE")
private Long FEE;

public Long getCOURSEID() {
	return COURSEID;
}

public void setCOURSEID(Long cOURSEID) {
	COURSEID = cOURSEID;
}

public String getCOURSE_NAME() {
	return COURSE_NAME;
}

public void setCOURSE_NAME(String cOURSE_NAME) {
	COURSE_NAME = cOURSE_NAME;
}

public String getDURATION() {
	return DURATION;
}

public void setDURATION(String dURATION) {
	DURATION = dURATION;
}

public int getCREDITS() {
	return CREDITS;
}

public void setCREDITS(int cREDITS) {
	CREDITS = cREDITS;
}

public Department getDepartments() {
	return departments;
}

public void setDepartments(Department departments) {
	this.departments = departments;
}

public List<College> getColleges() {
	return colleges;
}

public void setColleges(List<College> colleges) {
	this.colleges = colleges;
}

public int getSEATS_AVALIABLE() {
	return SEATS_AVALIABLE;
}

public void setSEATS_AVALIABLE(int sEATS_AVALIABLE) {
	SEATS_AVALIABLE = sEATS_AVALIABLE;
}

public Long getFEE() {
	return FEE;
}

public void setFEE(Long fEE) {
	FEE = fEE;
}


public Course() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Course [COURSEID=" + COURSEID + ", COURSE_NAME=" + COURSE_NAME + ", DURATION=" + DURATION + ", CREDITS="
			+ CREDITS + ", departments=" + departments + ", colleges=" + colleges + ", SEATS_AVALIABLE="
			+ SEATS_AVALIABLE + ", FEE=" + FEE + "]";
}

	
}



