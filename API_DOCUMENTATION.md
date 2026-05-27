# QuizFlow - API Documentation

## Package Structure

```
com.quizflow
├── gui
│   └── QuizFlowGUI
├── model
│   ├── Subject
│   ├── Question
│   ├── QuestionType
│   └── Quiz
├── data
│   └── QuizDatabase
└── export
    ├── WordExporter
    └── PDFExporter
```

---

## Model Classes

### Subject Class

**Location:** `com.quizflow.model.Subject`

**Constants:**
```java
public static final String MATH = "Mathematics";
public static final String SCIENCE = "Science";
public static final String HISTORY = "History";
public static final String LANGUAGE = "Language";
public static final String TECHNOLOGY = "Technology";
```

**Properties:**
```java
private int subjectId;              // Subject ID
private String subjectName;         // Subject name
private String description;         // Subject description
private List<Question> questions;   // Associated questions
```

**Methods:**
```java
// Constructors
Subject()
Subject(int subjectId, String subjectName, String description)

// Getters
int getSubjectId()
String getSubjectName()
String getDescription()
List<Question> getQuestions()

// Setters
void setSubjectId(int subjectId)
void setSubjectName(String subjectName)
void setDescription(String description)
void setQuestions(List<Question> questions)

// Methods
void addQuestion(Question question)
String toString()
```

**Example Usage:**
```java
Subject math = new Subject(1, "Mathematics", "Math topics");
math.addQuestion(question1);
math.addQuestion(question2);
```

---

### QuestionType Enum

**Location:** `com.quizflow.model.QuestionType`

**Values:**
```java
MULTIPLE_CHOICE("Multiple Choice")
TRUE_FALSE("True/False")
DRAG_DROP("Drag and Drop")
SHORT_ANSWER("Short Answer")
FILL_BLANK("Fill in the Blank")
MATCHING("Matching")
ESSAY("Essay")
```

**Methods:**
```java
String getDisplayName()
String toString()
```

**Example Usage:**
```java
QuestionType type = QuestionType.MULTIPLE_CHOICE;
System.out.println(type.getDisplayName()); // "Multiple Choice"
```

---

### Question Class

**Location:** `com.quizflow.model.Question`

**Properties:**
```java
private int questionId;             // Auto-incremented
private int subjectId;              // Parent subject
private String questionText;        // Question content
private QuestionType questionType;  // Type of question
private String correctAnswer;       // Correct answer
private int marks;                  // Question marks
private String difficulty;          // EASY, MEDIUM, HARD
private List<String> options;       // MCQ options
private List<String> dragDropItems; // Drag items
private String explanation;         // Answer explanation
```

**Methods:**
```java
// Constructors
Question()
Question(int questionId, int subjectId, String questionText, 
         QuestionType questionType, String correctAnswer, int marks)

// Getters/Setters
int getQuestionId() / void setQuestionId(int)
int getSubjectId() / void setSubjectId(int)
String getQuestionText() / void setQuestionText(String)
QuestionType getQuestionType() / void setQuestionType(QuestionType)
String getCorrectAnswer() / void setCorrectAnswer(String)
int getMarks() / void setMarks(int)
String getDifficulty() / void setDifficulty(String)
List<String> getOptions() / void setOptions(List<String>)
List<String> getDragDropItems() / void setDragDropItems(List<String>)
String getExplanation() / void setExplanation(String)

// Methods
void addOption(String option)
void addDragDropItem(String item)
String toString()
```

**Example Usage:**
```java
Question q = new Question();
q.setSubjectId(1);
q.setQuestionText("What is 2+2?");
q.setQuestionType(QuestionType.MULTIPLE_CHOICE);
q.setMarks(1);
q.setDifficulty("EASY");
q.setCorrectAnswer("B");
q.addOption("3");
q.addOption("4");
q.addOption("5");
q.addOption("6");
```

---

### Quiz Class

**Location:** `com.quizflow.model.Quiz`

**Properties:**
```java
private int quizId;                 // Auto-incremented
private int subjectId;              // Subject ID
private String quizTitle;           // Quiz title
private String quizDescription;     // Description
private List<Question> questions;   // Quiz questions
private int totalMarks;             // Total marks
private int totalTime;              // Time in minutes
private LocalDateTime createdDate;  // Creation time
private String instructorName;      // Instructor name
```

**Methods:**
```java
// Constructors
Quiz()
Quiz(int quizId, int subjectId, String quizTitle)

// Getters/Setters
int getQuizId() / void setQuizId(int)
int getSubjectId() / void setSubjectId(int)
String getQuizTitle() / void setQuizTitle(String)
String getQuizDescription() / void setQuizDescription(String)
List<Question> getQuestions() / void setQuestions(List<Question>)
int getTotalMarks() / void setTotalMarks(int)
int getTotalTime() / void setTotalTime(int)
LocalDateTime getCreatedDate() / void setCreatedDate(LocalDateTime)
String getInstructorName() / void setInstructorName(String)

// Methods
void addQuestion(Question question)
int calculateTotalMarks()  // Sum of all question marks
String toString()
```

---

## Database Class

### QuizDatabase Class

**Location:** `com.quizflow.data.QuizDatabase`

**Type:** Singleton

**Methods:**

```java
// Singleton
static QuizDatabase getInstance()

// Subject Operations
Subject getSubject(int subjectId)
List<Subject> getAllSubjects()

// Question Operations
void addQuestion(Question question)
Question getQuestion(int questionId)
List<Question> getQuestionsBySubject(int subjectId)
List<Question> getAllQuestions()

// Quiz Operations
void addQuiz(Quiz quiz)
Quiz getQuiz(int quizId)
List<Quiz> getQuizzesBySubject(int subjectId)
List<Quiz> getAllQuizzes()

// Statistics
int getTotalQuestions()
int getTotalQuizzes()
```

---

## Export Classes

### WordExporter Class

**Location:** `com.quizflow.export.WordExporter`

**Methods:**

```java
public static void exportQuizToWord(Quiz quiz, Subject subject, String outputPath)
    throws IOException
```

---

### PDFExporter Class

**Location:** `com.quizflow.export.PDFExporter`

**Methods:**

```java
public static void exportQuizToPDF(Quiz quiz, Subject subject, String outputPath)
    throws IOException
```

---

## GUI Class

### QuizFlowGUI Class

**Location:** `com.quizflow.gui.QuizFlowGUI`

**Extends:** `JFrame`

**Main Method:**
```java
public static void main(String[] args)
```

---

## Common Patterns

### Adding a Question
```java
QuizDatabase db = QuizDatabase.getInstance();
Question q = new Question();
q.setSubjectId(1);
q.setQuestionText("Your question?");
q.setQuestionType(QuestionType.MULTIPLE_CHOICE);
q.setMarks(1);
q.setDifficulty("EASY");
q.setCorrectAnswer("A");
q.addOption("Option A");
q.addOption("Option B");
db.addQuestion(q);
```

### Creating a Quiz
```java
QuizDatabase db = QuizDatabase.getInstance();
List<Question> questions = db.getQuestionsBySubject(1);

Quiz quiz = new Quiz();
quiz.setSubjectId(1);
quiz.setQuizTitle("Quiz Title");
quiz.setTotalTime(30);
quiz.setInstructorName("Instructor");

for (Question q : questions) {
    quiz.addQuestion(q);
}
quiz.setTotalMarks(quiz.calculateTotalMarks());
db.addQuiz(quiz);
```

### Exporting a Quiz
```java
QuizDatabase db = QuizDatabase.getInstance();
Quiz quiz = db.getQuiz(1);
Subject subject = db.getSubject(quiz.getSubjectId());

// Export to Word
WordExporter.exportQuizToWord(quiz, subject, "output/quiz.docx");

// Export to PDF
PDFExporter.exportQuizToPDF(quiz, subject, "output/quiz.pdf");
```

---

## Error Handling

### Common Exceptions

**IOException**
```java
try {
    WordExporter.exportQuizToWord(quiz, subject, path);
} catch (IOException e) {
    System.err.println("Export failed: " + e.getMessage());
}
```

**NullPointerException**
```java
Subject subject = db.getSubject(subjectId);
if (subject == null) {
    System.err.println("Subject not found");
} else {
    // Process subject
}
```

---

## Best Practices

1. **Always get singleton instance**
   ```java
   QuizDatabase db = QuizDatabase.getInstance();
   ```

2. **Check for null before using**
   ```java
   Subject s = db.getSubject(id);
   if (s != null) { /* use s */ }
   ```

3. **Set all required fields**
   ```java
   Question q = new Question();
   q.setSubjectId(1);        // Required
   q.setQuestionText("..."); // Required
   q.setQuestionType(...);   // Required
   q.setCorrectAnswer("..."); // Required
   q.setMarks(1);            // Required
   ```

4. **Calculate total marks after adding questions**
   ```java
   quiz.setTotalMarks(quiz.calculateTotalMarks());
   ```

5. **Create output directory**
   ```java
   new File("output").mkdirs();
   ```

6. **Use try-catch for exports**
   ```java
   try {
       WordExporter.exportQuizToWord(quiz, subject, path);
   } catch (IOException e) {
       // Handle error
   }
   ```

---

**Version:** 1.0.0
**Last Updated:** May 27, 2026
**Status:** Production Ready