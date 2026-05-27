# QuizFlow - 5-in-1 Smart Paper Engine

## Overview
QuizFlow is a complete Java-based quiz generation and management system with a professional GUI interface. It supports **5 main subjects** with multiple question types and exports to **Word (.docx)** and **PDF** formats.

## Features

### ✅ 5 Main Subjects
1. **Mathematics** - Numbers, Algebra, Geometry, Calculus
2. **Science** - Physics, Chemistry, Biology
3. **History** - World Events, Civilizations, Dates
4. **Language** - English, Grammar, Vocabulary, Literature
5. **Technology** - IT, Programming, Networks, Software

### ✅ 7 Question Types
- Multiple Choice (MCQ)
- True/False
- Drag and Drop
- Short Answer
- Fill in the Blank
- Matching
- Essay

### ✅ Export Formats
- **Word** - Professional .docx documents using Apache POI
- **PDF** - Print-ready .pdf files using iText

## Technology Stack
- **Language:** Java 11+
- **Build:** Maven
- **GUI:** Swing (NetBeans Compatible)
- **Word Export:** Apache POI 5.2.3
- **PDF Export:** iText 7.2.5

## Installation & Setup

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- NetBeans 30 (optional)

### Steps

1. **Clone Repository**
```bash
git clone https://github.com/benedictpilleren39-cpu/QuizFlow-SmartPaperEngine.git
cd QuizFlow-SmartPaperEngine
```

2. **Build Project**
```bash
mvn clean package
```

3. **Run Application**
```bash
java -jar target/quizflow-gui.jar
```

### Run in NetBeans
1. Open NetBeans 30
2. File → Open Project
3. Select QuizFlow-SmartPaperEngine
4. Press F6 to run

## Usage Guide

### Main Interface Tabs

#### 1. Dashboard
- Overview of QuizFlow
- View statistics
- Quick reference guide

#### 2. Subjects
- View all 5 available subjects
- See descriptions
- Track questions per subject

#### 3. Create Question
- Add new questions to subjects
- Select question type
- Set marks and difficulty
- Add options/items as needed

#### 4. Create Quiz
- Combine multiple questions into a quiz
- Set quiz title and time limit
- Add instructor information
- Select questions to include

#### 5. View Questions
- Browse all created questions
- See type, marks, difficulty
- Quick reference table

#### 6. View Quizzes
- List all created quizzes
- View details: questions, marks, time
- Instructor information

#### 7. Export
- Export quiz to Word or PDF
- Auto-formatted with:
  - Title and subject
  - Instructions
  - All questions with options
  - Answer spaces
  - Instructor info and time limit

## Quick Start Example

### Step 1: Create a Question
1. Click "Create Question" tab
2. Select Subject: Mathematics
3. Question: "What is 2+2?"
4. Type: Multiple Choice
5. Options: A) 3, B) 4, C) 5, D) 6
6. Correct Answer: B
7. Marks: 1
8. Click "Save Question"

### Step 2: Create More Questions
- Repeat Step 1 to add 5-10 questions

### Step 3: Create a Quiz
1. Click "Create Quiz" tab
2. Subject: Mathematics
3. Title: "Math Quiz 1"
4. Time: 30 minutes
5. Select questions from the list
6. Click "Create Quiz"

### Step 4: Export Quiz
1. Click "Export" tab
2. Select your quiz
3. Choose format (Word or PDF)
4. File saves to `output/` folder

## Project Structure

```
QuizFlow-SmartPaperEngine/
├── src/main/java/com/quizflow/
│   ├── gui/
│   │   └── QuizFlowGUI.java          # Main GUI application
│   ├── model/
│   │   ├── Subject.java              # Subject model
│   │   ├── Question.java             # Question model
│   │   ├── QuestionType.java         # Question type enum
│   │   └── Quiz.java                 # Quiz model
│   ├── data/
│   │   └── QuizDatabase.java         # In-memory database
│   └── export/
│       ├── WordExporter.java         # Word export
│       └── PDFExporter.java          # PDF export
├── output/                           # Exported files
├── pom.xml                           # Maven config
└── README.md
```

## File Output

Exported files are saved in the `output/` folder:
- `Quiz_Title.docx` - Word format
- `Quiz_Title.pdf` - PDF format

## Troubleshooting

### Issue: Build fails
**Solution:** Run `mvn clean install` to download dependencies

### Issue: GUI doesn't open
**Solution:** Ensure Java 11+ is installed: `java -version`

### Issue: Export folder not created
**Solution:** The `output/` folder is created automatically on first export

## Features in Detail

### Question Management
- Create unlimited questions
- Multiple question types
- Set difficulty levels
- Assign marks
- Add explanations

### Quiz Building
- Select specific questions
- Set time limits
- Add instructor info
- Auto-calculate total marks

### Export Capabilities
- Professional formatting
- Support all question types
- Answer spaces for students
- Instructions included
- Print-ready

## Future Enhancements
- Database persistence (SQLite/PostgreSQL)
- User authentication
- Question randomization
- Answer key generation
- Student submission tracking
- Bulk import/export
- Question duplication detection

## License
MIT License - See LICENSE file

## Author
**QuizFlow Development Team**
GitHub: [@benedictpilleren39-cpu](https://github.com/benedictpilleren39-cpu)

## Support
For issues or questions:
- Open an issue on GitHub
- Check the SETUP_INSTRUCTIONS.md file

---
**Version:** 1.0.0
**Last Updated:** May 27, 2026
**Java Version:** 11+
