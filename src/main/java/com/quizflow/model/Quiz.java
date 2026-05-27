package com.quizflow.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Quiz Model - Represents a complete quiz with questions from a subject
 */
public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int quizId;
    private int subjectId;
    private String quizTitle;
    private String quizDescription;
    private List<Question> questions;
    private int totalMarks;
    private int totalTime; // in minutes
    private LocalDateTime createdDate;
    private String instructorName;
    
    public Quiz() {
        this.questions = new ArrayList<>();
        this.createdDate = LocalDateTime.now();
    }
    
    public Quiz(int quizId, int subjectId, String quizTitle) {
        this.quizId = quizId;
        this.subjectId = subjectId;
        this.quizTitle = quizTitle;
        this.questions = new ArrayList<>();
        this.createdDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public int getQuizId() {
        return quizId;
    }
    
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
    
    public int getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
    
    public String getQuizTitle() {
        return quizTitle;
    }
    
    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }
    
    public String getQuizDescription() {
        return quizDescription;
    }
    
    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }
    
    public List<Question> getQuestions() {
        return questions;
    }
    
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    public void addQuestion(Question question) {
        this.questions.add(question);
    }
    
    public int getTotalMarks() {
        return totalMarks;
    }
    
    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
    
    public int getTotalTime() {
        return totalTime;
    }
    
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public String getInstructorName() {
        return instructorName;
    }
    
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
    
    public int calculateTotalMarks() {
        return questions.stream().mapToInt(Question::getMarks).sum();
    }
    
    @Override
    public String toString() {
        return "[" + quizId + "] " + quizTitle;
    }
}