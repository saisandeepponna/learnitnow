package com.sai.projectfinal;

public class CoursesAndChapters {
 private String courseName;
 private int chapterId;
 private String chapterName;
 private int courseId;
public CoursesAndChapters(String courseName, int chapterId, String chapterName,int courseId) {
	super();
	this.courseName = courseName;
	this.chapterId = chapterId;
	this.chapterName = chapterName;
	this.courseId=courseId;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public int getChapterId() {
	return chapterId;
}
public void setChapterId(int chapterId) {
	this.chapterId = chapterId;
}
public String getChapterName() {
	return chapterName;
}
public void setChapterName(String chapterName) {
	this.chapterName = chapterName;
}
public void setCourseId(int chapterId) {
	this.courseId = courseId;
}
public int getCourseId() {
	return courseId;
}
@Override
public String toString() {
	return "CoursesAndChapters [courseName=" + courseName + ", chapterId=" + chapterId + ", chapterName=" + chapterName
			+ "]";
}

 
}
