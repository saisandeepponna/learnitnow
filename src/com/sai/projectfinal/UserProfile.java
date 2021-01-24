package com.sai.projectfinal;

import java.sql.Date;
import java.sql.Time;

public class UserProfile {
	private String courseName;
	private int marks;
	private java.sql.Date date;
	private java.sql.Time time;
	public UserProfile(String courseName, int marks, Date date, Time time) {
		super();
		this.courseName = courseName;
		this.marks = marks;
		this.date = date;
		this.time = time;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public java.sql.Time getTime() {
		return time;
	}
	public void setTime(java.sql.Time time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "UserProfile [courseName=" + courseName + ", marks=" + marks + ", date=" + date + ", time=" + time + "]";
	}

	
	

}
