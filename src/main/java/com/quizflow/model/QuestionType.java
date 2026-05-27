package com.quizflow.model;

/**
 * Enumeration for Question Types in QuizFlow
 */
public enum QuestionType {
    MULTIPLE_CHOICE("Multiple Choice"),
    TRUE_FALSE("True/False"),
    DRAG_DROP("Drag and Drop"),
    SHORT_ANSWER("Short Answer"),
    FILL_BLANK("Fill in the Blank"),
    MATCHING("Matching"),
    ESSAY("Essay");
    
    private final String displayName;
    
    QuestionType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}