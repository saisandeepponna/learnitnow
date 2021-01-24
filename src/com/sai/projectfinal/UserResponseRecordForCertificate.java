package com.sai.projectfinal;

import java.sql.Date;
import java.sql.Time;

public class UserResponseRecordForCertificate {
private String courseName;
private int highestMarks;
private java.sql.Date date;
private java.sql.Time time;
public UserResponseRecordForCertificate(String courseName, int highestMarks, Date date, Time time) {
	super();
	this.courseName = courseName;
	this.highestMarks = highestMarks;
	this.date = date;
	this.time = time;
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

public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public int getHighestMarks() {
	return highestMarks;
}
public void setHighestMarks(int highestMarks) {
	this.highestMarks = highestMarks;
}

@Override
public String toString() {
	return "UserResponseRecordForCertificate [courseName=" + courseName + ", highestMarks=" + highestMarks + ", date="
			+ date + ", time=" + time + "]";
}

}
