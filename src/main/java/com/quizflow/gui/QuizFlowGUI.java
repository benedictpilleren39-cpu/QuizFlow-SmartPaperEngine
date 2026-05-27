package com.quizflow.gui;

import com.quizflow.data.QuizDatabase;
import com.quizflow.export.PDFExporter;
import com.quizflow.export.WordExporter;
import com.quizflow.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * QuizFlow GUI - Complete GUI Application for NetBeans
 */
public class QuizFlowGUI extends JFrame {
    private QuizDatabase database;
    private JTabbedPane tabbedPane;
    
    public QuizFlowGUI() {
        this.database = QuizDatabase.getInstance();
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("QuizFlow - Smart Paper Quiz Engine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("Dashboard", createDashboardPanel());
        tabbedPane.addTab("Subjects", createSubjectsPanel());
        tabbedPane.addTab("Create Question", createQuestionPanel());
        tabbedPane.addTab("Create Quiz", createQuizPanel());
        tabbedPane.addTab("View Questions", createViewQuestionsPanel());
        tabbedPane.addTab("View Quizzes", createViewQuizzesPanel());
        tabbedPane.addTab("Export", createExportPanel());
        
        add(tabbedPane);
        setVisible(true);
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        // Title
        JLabel titleLabel = new JLabel("QuizFlow - 5-in-1 Smart Paper Engine");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, gbc);
        
        gbc.gridy++;
        panel.add(new JLabel(" "), gbc);
        
        // Info
        gbc.gridy++;
        JLabel infoLabel = new JLabel("Welcome to QuizFlow!");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(infoLabel, gbc);
        
        gbc.gridy++;
        JLabel subjectsLabel = new JLabel("5 Main Subjects:");
        subjectsLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(subjectsLabel, gbc);
        
        gbc.gridy++;
        panel.add(new JLabel("1. Mathematics  2. Science  3. History  4. Language  5. Technology"), gbc);
        
        gbc.gridy++;
        panel.add(new JLabel(" "), gbc);
        
        gbc.gridy++;
        JLabel typesLabel = new JLabel("Question Types: Multiple Choice | True/False | Drag & Drop | Short Answer | Fill Blank | Matching | Essay");
        typesLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        panel.add(typesLabel, gbc);
        
        gbc.gridy++;
        JLabel statsLabel = new JLabel("Export Formats: Word (.docx) | PDF (.pdf)");
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        panel.add(statsLabel, gbc);
        
        gbc.gridy++;
        panel.add(new JLabel(" "), gbc);
        
        gbc.gridy++;
        JButton statsButton = new JButton("View Statistics");
        statsButton.addActionListener(e -> showStatistics());
        panel.add(statsButton, gbc);
        
        panel.setBackground(new Color(240, 240, 240));
        return panel;
    }
    
    private JPanel createSubjectsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        List<Subject> subjects = database.getAllSubjects();
        
        String[] columns = {"ID", "Subject Name", "Description", "Questions"};
        Object[][] data = new Object[subjects.size()][4];
        
        for (int i = 0; i < subjects.size(); i++) {
            Subject s = subjects.get(i);
            data[i][0] = s.getSubjectId();
            data[i][1] = s.getSubjectName();
            data[i][2] = s.getDescription();
            data[i][3] = s.getQuestions().size();
        }
        
        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.setDefaultEditor(Object.class, null);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createQuestionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        // Subject
        panel.add(new JLabel("Subject:"), gbc);
        gbc.gridx = 1;
        JComboBox<Subject> subjectCombo = new JComboBox<>(database.getAllSubjects().toArray(new Subject[0]));
        panel.add(subjectCombo, gbc);
        
        // Question
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Question:"), gbc);
        gbc.gridx = 1;
        JTextArea questionArea = new JTextArea(3, 30);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(questionArea), gbc);
        
        // Question Type
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        JComboBox<QuestionType> typeCombo = new JComboBox<>(QuestionType.values());
        panel.add(typeCombo, gbc);
        
        // Marks
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Marks:"), gbc);
        gbc.gridx = 1;
        JSpinner marksSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
        panel.add(marksSpinner, gbc);
        
        // Difficulty
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Difficulty:"), gbc);
        gbc.gridx = 1;
        String[] difficulties = {"EASY", "MEDIUM", "HARD"};
        JComboBox<String> diffCombo = new JComboBox<>(difficulties);
        panel.add(diffCombo, gbc);
        
        // Answer
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Answer:"), gbc);
        gbc.gridx = 1;
        JTextField answerField = new JTextField(30);
        panel.add(answerField, gbc);
        
        // Options (for MCQ)
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Options (comma-separated):"), gbc);
        gbc.gridx = 1;
        JTextArea optionsArea = new JTextArea(2, 30);
        optionsArea.setLineWrap(true);
        panel.add(new JScrollPane(optionsArea), gbc);
        
        // Save Button
        gbc.gridx = 0;
        gbc.gridy++;
        JButton saveBtn = new JButton("Save Question");
        saveBtn.addActionListener(e -> {
            try {
                Subject subject = (Subject) subjectCombo.getSelectedItem();
                Question q = new Question();
                q.setSubjectId(subject.getSubjectId());
                q.setQuestionText(questionArea.getText());
                q.setQuestionType((QuestionType) typeCombo.getSelectedItem());
                q.setMarks((Integer) marksSpinner.getValue());
                q.setDifficulty((String) diffCombo.getSelectedItem());
                q.setCorrectAnswer(answerField.getText());
                
                // Add options
                String optionsText = optionsArea.getText().trim();
                if (!optionsText.isEmpty()) {
                    String[] opts = optionsText.split(",");
                    for (String opt : opts) {
                        q.addOption(opt.trim());
                    }
                }
                
                database.addQuestion(q);
                JOptionPane.showMessageDialog(panel, "Question saved successfully!");
                questionArea.setText("");
                answerField.setText("");
                optionsArea.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(saveBtn, gbc);
        
        return panel;
    }
    
    private JPanel createQuizPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        // Subject
        panel.add(new JLabel("Subject:"), gbc);
        gbc.gridx = 1;
        JComboBox<Subject> subjectCombo = new JComboBox<>(database.getAllSubjects().toArray(new Subject[0]));
        subjectCombo.addActionListener(e -> {
            // Update questions list
        });
        panel.add(subjectCombo, gbc);
        
        // Title
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Quiz Title:"), gbc);
        gbc.gridx = 1;
        JTextField titleField = new JTextField(30);
        panel.add(titleField, gbc);
        
        // Time
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Time (minutes):"), gbc);
        gbc.gridx = 1;
        JSpinner timeSpinner = new JSpinner(new SpinnerNumberModel(30, 5, 300, 5));
        panel.add(timeSpinner, gbc);
        
        // Instructor
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Instructor:"), gbc);
        gbc.gridx = 1;
        JTextField instructorField = new JTextField(30);
        panel.add(instructorField, gbc);
        
        // Questions List
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Select Questions:"), gbc);
        gbc.gridx = 1;
        JList<Question> questionList = new JList<>(new DefaultListModel<>());
        questionList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        updateQuestionList(questionList, subjectCombo, database);
        subjectCombo.addActionListener(e -> updateQuestionList(questionList, subjectCombo, database));
        panel.add(new JScrollPane(questionList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), gbc);
        
        // Create Button
        gbc.gridx = 0;
        gbc.gridy++;
        JButton createBtn = new JButton("Create Quiz");
        createBtn.addActionListener(e -> {
            try {
                Subject subject = (Subject) subjectCombo.getSelectedItem();
                Quiz quiz = new Quiz();
                quiz.setSubjectId(subject.getSubjectId());
                quiz.setQuizTitle(titleField.getText());
                quiz.setTotalTime((Integer) timeSpinner.getValue());
                quiz.setInstructorName(instructorField.getText());
                
                for (Question q : questionList.getSelectedValuesList()) {
                    quiz.addQuestion(q);
                }
                
                quiz.setTotalMarks(quiz.calculateTotalMarks());
                database.addQuiz(quiz);
                JOptionPane.showMessageDialog(panel, "Quiz created successfully!");
                titleField.setText("");
                instructorField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(createBtn, gbc);
        
        return panel;
    }
    
    private JPanel createViewQuestionsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        List<Question> questions = database.getAllQuestions();
        
        String[] columns = {"ID", "Subject", "Question", "Type", "Marks", "Difficulty"};
        Object[][] data = new Object[questions.size()][6];
        
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            Subject s = database.getSubject(q.getSubjectId());
            data[i][0] = q.getQuestionId();
            data[i][1] = s != null ? s.getSubjectName() : "Unknown";
            data[i][2] = q.getQuestionText().substring(0, Math.min(30, q.getQuestionText().length())) + "...";
            data[i][3] = q.getQuestionType().getDisplayName();
            data[i][4] = q.getMarks();
            data[i][5] = q.getDifficulty();
        }
        
        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.setDefaultEditor(Object.class, null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Refresh button
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> tabbedPane.setSelectedIndex(4));
        panel.add(refreshBtn, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createViewQuizzesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        List<Quiz> quizzes = database.getAllQuizzes();
        
        String[] columns = {"ID", "Title", "Subject", "Questions", "Total Marks", "Time", "Instructor"};
        Object[][] data = new Object[quizzes.size()][7];
        
        for (int i = 0; i < quizzes.size(); i++) {
            Quiz quiz = quizzes.get(i);
            Subject s = database.getSubject(quiz.getSubjectId());
            data[i][0] = quiz.getQuizId();
            data[i][1] = quiz.getQuizTitle();
            data[i][2] = s != null ? s.getSubjectName() : "Unknown";
            data[i][3] = quiz.getQuestions().size();
            data[i][4] = quiz.calculateTotalMarks();
            data[i][5] = quiz.getTotalTime();
            data[i][6] = quiz.getInstructorName() != null ? quiz.getInstructorName() : "N/A";
        }
        
        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.setDefaultEditor(Object.class, null);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createExportPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        panel.add(new JLabel("Select Quiz to Export:"), gbc);
        
        gbc.gridy++;
        JComboBox<Quiz> quizCombo = new JComboBox<>(database.getAllQuizzes().toArray(new Quiz[0]));
        panel.add(quizCombo, gbc);
        
        // Export to Word
        gbc.gridy++;
        JButton wordBtn = new JButton("Export to Word (.docx)");
        wordBtn.addActionListener(e -> {
            try {
                Quiz quiz = (Quiz) quizCombo.getSelectedItem();
                if (quiz != null) {
                    Subject subject = database.getSubject(quiz.getSubjectId());
                    String path = "output/" + quiz.getQuizTitle().replaceAll("\\s+", "_") + ".docx";
                    WordExporter.exportQuizToWord(quiz, subject, path);
                    JOptionPane.showMessageDialog(panel, "Quiz exported to Word!\nFile: " + path);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(wordBtn, gbc);
        
        // Export to PDF
        gbc.gridy++;
        JButton pdfBtn = new JButton("Export to PDF (.pdf)");
        pdfBtn.addActionListener(e -> {
            try {
                Quiz quiz = (Quiz) quizCombo.getSelectedItem();
                if (quiz != null) {
                    Subject subject = database.getSubject(quiz.getSubjectId());
                    String path = "output/" + quiz.getQuizTitle().replaceAll("\\s+", "_") + ".pdf";
                    PDFExporter.exportQuizToPDF(quiz, subject, path);
                    JOptionPane.showMessageDialog(panel, "Quiz exported to PDF!\nFile: " + path);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(pdfBtn, gbc);
        
        return panel;
    }
    
    private void updateQuestionList(JList<Question> questionList, JComboBox<Subject> subjectCombo, QuizDatabase db) {
        Subject subject = (Subject) subjectCombo.getSelectedItem();
        if (subject != null) {
            List<Question> questions = db.getQuestionsBySubject(subject.getSubjectId());
            DefaultListModel<Question> model = new DefaultListModel<>();
            for (Question q : questions) {
                model.addElement(q);
            }
            questionList.setModel(model);
        }
    }
    
    private void showStatistics() {
        String stats = "Total Questions: " + database.getTotalQuestions() + "\n" +
                       "Total Quizzes: " + database.getTotalQuizzes() + "\n" +
                       "Total Subjects: 5";
        JOptionPane.showMessageDialog(this, stats, "Database Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizFlowGUI());
    }
}