package com.quizflow.data;

import com.quizflow.model.*;
import java.util.*;

/**
 * In-Memory Database for QuizFlow - Stores subjects, questions, and quizzes
 */
public class QuizDatabase {
    private static QuizDatabase instance;
    private Map<Integer, Subject> subjects;
    private Map<Integer, Question> questions;
    private Map<Integer, Quiz> quizzes;
    private int questionIdCounter = 1;
    private int quizIdCounter = 1;
    
    private QuizDatabase() {
        this.subjects = new HashMap<>();
        this.questions = new HashMap<>();
        this.quizzes = new HashMap<>();
        initializeDefaultSubjects();
    }
    
    /**
     * Get singleton instance
     */
    public static synchronized QuizDatabase getInstance() {
        if (instance == null) {
            instance = new QuizDatabase();
        }
        return instance;
    }
    
    /**
     * Initialize 5 default subjects
     */
    private void initializeDefaultSubjects() {
        subjects.put(1, new Subject(1, "Mathematics", "Numbers, Algebra, Geometry, Calculus"));
        subjects.put(2, new Subject(2, "Science", "Physics, Chemistry, Biology"));
        subjects.put(3, new Subject(3, "History", "World Events, Civilizations, Dates"));
        subjects.put(4, new Subject(4, "Language", "English, Grammar, Vocabulary, Literature"));
        subjects.put(5, new Subject(5, "Technology", "IT, Programming, Networks, Software"));
    }
    
    // ============== SUBJECT METHODS ==============
    public Subject getSubject(int subjectId) {
        return subjects.get(subjectId);
    }
    
    public List<Subject> getAllSubjects() {
        return new ArrayList<>(subjects.values());
    }
    
    // ============== QUESTION METHODS ==============
    public void addQuestion(Question question) {
        question.setQuestionId(questionIdCounter++);
        questions.put(question.getQuestionId(), question);
        
        Subject subject = subjects.get(question.getSubjectId());
        if (subject != null) {
            subject.addQuestion(question);
        }
    }
    
    public Question getQuestion(int questionId) {
        return questions.get(questionId);
    }
    
    public List<Question> getQuestionsBySubject(int subjectId) {
        List<Question> subjectQuestions = new ArrayList<>();
        for (Question q : questions.values()) {
            if (q.getSubjectId() == subjectId) {
                subjectQuestions.add(q);
            }
        }
        return subjectQuestions;
    }
    
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions.values());
    }
    
    // ============== QUIZ METHODS ==============
    public void addQuiz(Quiz quiz) {
        quiz.setQuizId(quizIdCounter++);
        quizzes.put(quiz.getQuizId(), quiz);
    }
    
    public Quiz getQuiz(int quizId) {
        return quizzes.get(quizId);
    }
    
    public List<Quiz> getQuizzesBySubject(int subjectId) {
        List<Quiz> subjectQuizzes = new ArrayList<>();
        for (Quiz q : quizzes.values()) {
            if (q.getSubjectId() == subjectId) {
                subjectQuizzes.add(q);
            }
        }
        return subjectQuizzes;
    }
    
    public List<Quiz> getAllQuizzes() {
        return new ArrayList<>(quizzes.values());
    }
    
    public int getTotalQuestions() {
        return questions.size();
    }
    
    public int getTotalQuizzes() {
        return quizzes.size();
    }
}