package com.quizflow.export;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.quizflow.model.*;

import java.io.IOException;
import java.util.List;

/**
 * PDF Exporter - Exports quiz to PDF format using iText
 */
public class PDFExporter {
    
    /**
     * Export quiz to PDF document
     */
    public static void exportQuizToPDF(Quiz quiz, Subject subject, String outputPath) throws IOException {
        new java.io.File(outputPath).getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(outputPath);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        
        PdfFont titleFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont normalFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        
        // Add title
        Paragraph title = new Paragraph(quiz.getQuizTitle())
                .setFont(titleFont)
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10);
        document.add(title);
        
        // Add subject
        Paragraph subjectPara = new Paragraph("Subject: " + subject.getSubjectName())
                .setFont(boldFont)
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(15);
        document.add(subjectPara);
        
        // Add quiz information
        Table infoTable = new Table(3);
        infoTable.setWidth(500);
        infoTable.addCell(new Cell().add(new Paragraph("Total Marks: " + quiz.calculateTotalMarks())));
        infoTable.addCell(new Cell().add(new Paragraph("Time: " + quiz.getTotalTime() + " min")));
        infoTable.addCell(new Cell().add(new Paragraph("Instructor: " + (quiz.getInstructorName() != null ? quiz.getInstructorName() : "N/A"))));
        document.add(infoTable);
        document.add(new Paragraph("\n"));
        
        // Add instructions
        Paragraph instructions = new Paragraph("INSTRUCTIONS:")
                .setFont(boldFont)
                .setFontSize(11);
        document.add(instructions);
        
        List<String> instructionsList = List.of(
                "1. Read all questions carefully",
                "2. Attempt all questions",
                "3. Show your working for calculations",
                "4. Answer in the space provided",
                "5. No external aids are permitted"
        );
        
        for (String instruction : instructionsList) {
            document.add(new Paragraph(instruction).setFont(normalFont).setFontSize(10));
        }
        
        document.add(new Paragraph("\n"));
        
        // Add questions
        List<Question> questions = quiz.getQuestions();
        int questionNum = 1;
        
        for (Question question : questions) {
            addQuestionToPDF(document, question, questionNum, boldFont, normalFont);
            questionNum++;
        }
        
        document.close();
    }
    
    /**
     * Add a question to the PDF document
     */
    private static void addQuestionToPDF(Document document, Question question, int questionNum, 
                                        PdfFont boldFont, PdfFont normalFont) {
        // Question text with marks
        Paragraph questionPara = new Paragraph()
                .setFont(boldFont)
                .setFontSize(11);
        questionPara.add(questionNum + ". " + question.getQuestionText());
        questionPara.add(" [" + question.getMarks() + " marks]");
        document.add(questionPara);
        
        // Add options based on question type
        switch (question.getQuestionType()) {
            case MULTIPLE_CHOICE:
                addMCQToPDF(document, question, normalFont);
                break;
            case TRUE_FALSE:
                addTrueFalseToPDF(document, normalFont);
                break;
            case DRAG_DROP:
                addDragDropToPDF(document, question, normalFont);
                break;
            case FILL_BLANK:
                addFillBlankToPDF(document, normalFont);
                break;
            case MATCHING:
                addMatchingToPDF(document, question, normalFont);
                break;
            case SHORT_ANSWER:
                addShortAnswerToPDF(document, normalFont);
                break;
            case ESSAY:
                addEssayToPDF(document, normalFont);
                break;
        }
        
        document.add(new Paragraph("\n").setMarginBottom(10));
    }
    
    private static void addMCQToPDF(Document document, Question question, PdfFont normalFont) {
        for (int i = 0; i < question.getOptions().size(); i++) {
            char optionChar = (char) ('A' + i);
            document.add(new Paragraph(optionChar + ") " + question.getOptions().get(i))
                    .setFont(normalFont)
                    .setFontSize(10)
                    .setMarginLeft(20));
        }
    }
    
    private static void addTrueFalseToPDF(Document document, PdfFont normalFont) {
        document.add(new Paragraph("☐ True").setFont(normalFont).setFontSize(10).setMarginLeft(20));
        document.add(new Paragraph("☐ False").setFont(normalFont).setFontSize(10).setMarginLeft(20));
    }
    
    private static void addDragDropToPDF(Document document, Question question, PdfFont normalFont) {
        document.add(new Paragraph("Items to drag and drop:")
                .setFont(normalFont)
                .setFontSize(10)
                .setMarginLeft(20));
        
        for (String item : question.getDragDropItems()) {
            document.add(new Paragraph("• " + item)
                    .setFont(normalFont)
                    .setFontSize(10)
                    .setMarginLeft(30));
        }
    }
    
    private static void addFillBlankToPDF(Document document, PdfFont normalFont) {
        document.add(new Paragraph("Answer: ___________________________________________________________")
                .setFont(normalFont)
                .setFontSize(10)
                .setMarginLeft(20));
    }
    
    private static void addMatchingToPDF(Document document, Question question, PdfFont normalFont) {
        document.add(new Paragraph("Match the following:")
                .setFont(normalFont)
                .setFontSize(10)
                .setMarginLeft(20));
    }
    
    private static void addShortAnswerToPDF(Document document, PdfFont normalFont) {
        for (int i = 0; i < 3; i++) {
            document.add(new Paragraph("_________________________________________________________")
                    .setFont(normalFont)
                    .setFontSize(10)
                    .setMarginLeft(20));
        }
    }
    
    private static void addEssayToPDF(Document document, PdfFont normalFont) {
        for (int i = 0; i < 6; i++) {
            document.add(new Paragraph("_________________________________________________________")
                    .setFont(normalFont)
                    .setFontSize(10)
                    .setMarginLeft(20));
        }
    }
}