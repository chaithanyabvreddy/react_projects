package com.setquestions;

public class SetQuestionsForm {
	private Long id;
	private Integer questionid;
	private String questionName;
	private String correctAnswer;
	private boolean answerVisible;
	
	public Integer getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
	}

	public boolean isAnswerVisible() {
		return answerVisible;
	}

	public void setAnswerVisible(boolean answerVisible) {
		this.answerVisible = answerVisible;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}


}
