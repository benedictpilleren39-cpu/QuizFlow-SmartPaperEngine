package com.quizflow.export;

import com.quizflow.model.*;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;

import java.io.*;
import java.util.List;

/**
 * Word Exporter - Exports quiz to .docx format using Apache POI
 */
public class WordExporter {
    
    /**
     * Export quiz to Word document
     */
    public static void exportQuizToWord(Quiz quiz, Subject subject, String outputPath) throws IOException {
        XWPFDocument document = new XWPFDocument();
        
        // Add title
        XWPFParagraph titlePara = document.createParagraph();
        titlePara.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = titlePara.createRun();
        titleRun.setText(quiz.getQuizTitle());
        titleRun.setBold(true);
        titleRun.setFontSize(18);
        
        // Add subject name
        XWPFParagraph subjectPara = document.createParagraph();
        subjectPara.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun subjectRun = subjectPara.createRun();
        subjectRun.setText("Subject: " + subject.getSubjectName());
        subjectRun.setFontSize(12);
        
        // Add quiz info
        XWPFParagraph infoPara = document.createParagraph();
        infoPara.createRun().setText("Total Marks: " + quiz.calculateTotalMarks());
        infoPara.createRun().addBreak();
        infoPara.createRun().setText("Time Limit: " + quiz.getTotalTime() + " minutes");
        infoPara.createRun().addBreak();
        infoPara.createRun().setText("Instructor: " + (quiz.getInstructorName() != null ? quiz.getInstructorName() : "N/A"));
        
        // Add instructions
        XWPFParagraph instructionsPara = document.createParagraph();
        XWPFRun instRun = instructionsPara.createRun();
        instRun.setText("\nInstructions:");
        instRun.setBold(true);
        document.createParagraph().createRun().setText("1. Answer all questions carefully");
        document.createParagraph().createRun().setText("2. Write your answers clearly");
        document.createParagraph().createRun().setText("3. Show all working for calculations");
        document.createParagraph().createRun().setText("4. No external aids permitted");
        
        // Add questions
        List<Question> questions = quiz.getQuestions();
        int questionNum = 1;
        
        for (Question question : questions) {
            addQuestionToDocument(document, question, questionNum);
            questionNum++;
        }
        
        // Write to file
        new File(outputPath).getParentFile().mkdirs();
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            document.write(fos);
        }
        document.close();
    }
    
    /**
     * Add a question to the Word document based on its type
     */
    private static void addQuestionToDocument(XWPFDocument document, Question question, int questionNum) {
        XWPFParagraph questionPara = document.createParagraph();
        
        XWPFRun questionRun = questionPara.createRun();
        questionRun.setText(questionNum + ". " + question.getQuestionText());
        questionRun.setBold(true);
        
        // Add marks
        XWPFRun marksRun = questionPara.createRun();
        marksRun.setText(" [" + question.getMarks() + " marks]");
        marksRun.setItalic(true);
        
        // Add options based on question type
        switch (question.getQuestionType()) {
            case MULTIPLE_CHOICE:
                addMultipleChoiceOptions(document, question);
                break;
            case TRUE_FALSE:
                addTrueFalseOptions(document);
                break;
            case DRAG_DROP:
                addDragDropOptions(document, question);
                break;
            case FILL_BLANK:
                addFillBlankOption(document);
                break;
            case MATCHING:
                addMatchingOptions(document, question);
                break;
            case SHORT_ANSWER:
                addShortAnswerSpace(document);
                break;
            case ESSAY:
                addEssaySpace(document);
                break;
        }
        
        // Add spacing
        document.createParagraph();
    }
    
    private static void addMultipleChoiceOptions(XWPFDocument document, Question question) {
        for (int i = 0; i < question.getOptions().size(); i++) {
            XWPFParagraph optionPara = document.createParagraph();
            optionPara.setIndentationLeft(Units.toTwip(0.5f));
            XWPFRun optionRun = optionPara.createRun();
            char optionChar = (char) ('A' + i);
            optionRun.setText(optionChar + ") " + question.getOptions().get(i));
        }
    }
    
    private static void addTrueFalseOptions(XWPFDocument document) {
        XWPFParagraph truePara = document.createParagraph();
        truePara.setIndentationLeft(Units.toTwip(0.5f));
        truePara.createRun().setText("☐ True");
        
        XWPFParagraph falsePara = document.createParagraph();
        falsePara.setIndentationLeft(Units.toTwip(0.5f));
        falsePara.createRun().setText("☐ False");
    }
    
    private static void addDragDropOptions(XWPFDocument document, Question question) {
        XWPFParagraph itemsPara = document.createParagraph();
        itemsPara.createRun().setText("Items to drag and drop:");
        itemsPara.createRun().setBold(true);
        
        for (String item : question.getDragDropItems()) {
            XWPFParagraph itemPara = document.createParagraph();
            itemPara.setIndentationLeft(Units.toTwip(0.5f));
            itemPara.createRun().setText("• " + item);
        }
    }
    
    private static void addFillBlankOption(XWPFDocument document) {
        XWPFParagraph blankPara = document.createParagraph();
        blankPara.setIndentationLeft(Units.toTwip(0.5f));
        blankPara.createRun().setText("Answer: _________________________________");
    }
    
    private static void addMatchingOptions(XWPFDocument document, Question question) {
        XWPFParagraph matchPara = document.createParagraph();
        matchPara.createRun().setText("Match the following:");
        matchPara.createRun().setBold(true);
        
        XWPFTable table = document.createTable(question.getOptions().size() + 1, 2);
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("Column A");
        headerRow.getCell(1).setText("Column B");
    }
    
    private static void addShortAnswerSpace(XWPFDocument document) {
        for (int i = 0; i < 3; i++) {
            document.createParagraph().createRun().setText("_____________________________________________________");
        }
    }
    
    private static void addEssaySpace(XWPFDocument document) {
        for (int i = 0; i < 6; i++) {
            document.createParagraph().createRun().setText("_____________________________________________________");
        }
    }
}