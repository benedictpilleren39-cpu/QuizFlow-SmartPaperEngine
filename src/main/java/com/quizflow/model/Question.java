package com.quizflow.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Question Model - Represents a quiz question with multiple types
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int questionId;
    private int subjectId;
    private String questionText;
    private QuestionType questionType;
    private String correctAnswer;
    private int marks;
    private String difficulty; // EASY, MEDIUM, HARD
    private List<String> options; // For MCQ, True/False, etc.
    private List<String> dragDropItems; // For drag and drop
    private String explanation;
    
    public Question() {
        this.options = new ArrayList<>();
        this.dragDropItems = new ArrayList<>();
    }
    
    public Question(int questionId, int subjectId, String questionText, 
                   QuestionType questionType, String correctAnswer, int marks) {
        this.questionId = questionId;
        this.subjectId = subjectId;
        this.questionText = questionText;
        this.questionType = questionType;
        this.correctAnswer = correctAnswer;
        this.marks = marks;
        this.options = new ArrayList<>();
        this.dragDropItems = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getQuestionId() {
        return questionId;
    }
    
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    
    public int getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
    
    public String getQuestionText() {
        return questionText;
    }
    
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    
    public QuestionType getQuestionType() {
        return questionType;
    }
    
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
    
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
    public int getMarks() {
        return marks;
    }
    
    public void setMarks(int marks) {
        this.marks = marks;
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    public List<String> getOptions() {
        return options;
    }
    
    public void setOptions(List<String> options) {
        this.options = options;
    }
    
    public void addOption(String option) {
        this.options.add(option);
    }
    
    public List<String> getDragDropItems() {
        return dragDropItems;
    }
    
    public void setDragDropItems(List<String> dragDropItems) {
        this.dragDropItems = dragDropItems;
    }
    
    public void addDragDropItem(String item) {
        this.dragDropItems.add(item);
    }
    
    public String getExplanation() {
        return explanation;
    }
    
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    
    @Override
    public String toString() {
        return "Q" + questionId + ": " + questionText.substring(0, Math.min(30, questionText.length())) + "...";
    }
}