package com.sai.projectfinal;

public class quizQuestions{
	private String  questions=new String();
	private String[] options=new String[100];
	private int answers;
	private int questionId;
	public quizQuestions(String questions,String[] options,int answers,int questionId) {
		this.questions=questions;
		this.options=options;
		this.answers=answers;
		this.questionId=questionId;
	}
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public int getAnswers() {
		return answers;
	}
	public void setAnswers(int answers) {
		this.answers = answers;
	}
	


}
