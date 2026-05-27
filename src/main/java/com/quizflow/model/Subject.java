package com.quizflow.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Subject Model - Represents a quiz subject (Math, Science, History, Language, Technology)
 */
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int subjectId;
    private String subjectName;
    private String description;
    private List<Question> questions;
    
    // 5 Available Subjects
    public static final String MATH = "Mathematics";
    public static final String SCIENCE = "Science";
    public static final String HISTORY = "History";
    public static final String LANGUAGE = "Language";
    public static final String TECHNOLOGY = "Technology";
    
    public Subject() {
        this.questions = new ArrayList<>();
    }
    
    public Subject(int subjectId, String subjectName, String description) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.description = description;
        this.questions = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
    
    public String getSubjectName() {
        return subjectName;
    }
    
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
    
    @Override
    public String toString() {
        return subjectName;
    }
}