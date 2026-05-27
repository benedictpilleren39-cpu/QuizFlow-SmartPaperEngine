# QuizFlow - NetBeans 30 Setup Instructions

## Complete Setup Guide for NetBeans 30

### Part 1: Prerequisites Installation

#### 1.1 Install Java Development Kit (JDK)
- Download JDK 11 or higher from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.java.net/)
- Install and set `JAVA_HOME` environment variable
- Verify installation:
  ```bash
  java -version
  javac -version
  ```

#### 1.2 Install Maven
- Download from [Maven Official Site](https://maven.apache.org/download.cgi)
- Extract to a folder (e.g., `C:\apache-maven-3.9.0`)
- Add Maven bin folder to PATH environment variable
- Verify installation:
  ```bash
  mvn -version
  ```

#### 1.3 Install NetBeans 30
- Download from [NetBeans Official Site](https://netbeans.apache.org/download/nb20/)
- Run the installer
- Choose Java SE installation

### Part 2: Project Setup in NetBeans

#### 2.1 Open Project
1. Open NetBeans 30
2. Go to **File** → **Open Project**
3. Navigate to the QuizFlow-SmartPaperEngine folder
4. Click **Open Project**

#### 2.2 Configure Project Properties
1. Right-click on the project in the Project Explorer
2. Select **Properties**
3. **Sources**:
   - Source/Binary Format: **JDK 11** or higher
4. **Libraries**:
   - Ensure all dependencies are loaded
5. Click **OK**

#### 2.3 Build the Project
1. Right-click on the project
2. Select **Clean and Build**
3. Wait for the build to complete
4. Verify: Output should show `BUILD SUCCESS`

### Part 3: Running the Application

#### 3.1 Run from NetBeans
1. Right-click on the project
2. Select **Run**
3. Or press **F6**
4. The GUI application will start in a new window

#### 3.2 Run from Command Line
```bash
cd QuizFlow-SmartPaperEngine
mvn clean package
java -jar target/quizflow-gui.jar
```

### Part 4: Using the Application

#### 4.1 Main Tabs Overview

**Dashboard Tab**
- Welcome screen
- View quick statistics
- Overview of features

**Subjects Tab**
- View all 5 available subjects
- See descriptions
- Track number of questions per subject

**Create Question Tab**
- Subject: Select from 5 subjects
- Question: Enter question text
- Type: Choose from 7 question types
- Marks: Set question marks
- Difficulty: EASY, MEDIUM, HARD
- Answer: Correct answer
- Options: For MCQ questions (comma-separated)
- Click "Save Question"

**Create Quiz Tab**
- Subject: Select subject
- Quiz Title: Name your quiz
- Time: Set duration in minutes
- Instructor: Enter instructor name
- Select Questions: Choose from available questions
- Click "Create Quiz"

**View Questions Tab**
- Table showing all created questions
- Columns: ID, Subject, Question, Type, Marks, Difficulty
- Refresh button to update

**View Quizzes Tab**
- Table showing all created quizzes
- Columns: ID, Title, Subject, Questions, Total Marks, Time, Instructor

**Export Tab**
- Select Quiz: Choose quiz to export
- Export to Word: Creates .docx file
- Export to PDF: Creates .pdf file
- Files saved to `output/` folder

#### 4.2 Sample Workflow

**Step 1: Create Questions**
```
1. Go to "Create Question" tab
2. Subject: Select "Mathematics"
3. Question: "What is the square root of 16?"
4. Type: "Multiple Choice"
5. Options: "2,3,4,5"
6. Difficulty: "EASY"
7. Answer: "4"
8. Marks: "1"
9. Click "Save Question"
10. Repeat for more questions
```

**Step 2: Create a Quiz**
```
1. Go to "Create Quiz" tab
2. Subject: "Mathematics"
3. Quiz Title: "Math Basics"
4. Time: "30"
5. Instructor: "Your Name"
6. Select Questions: Choose 5 questions
7. Click "Create Quiz"
```

**Step 3: Export Quiz**
```
1. Go to "Export" tab
2. Select Quiz: "Math Basics"
3. Click "Export to Word (.docx)" or "Export to PDF (.pdf)"
4. File appears in output/ folder
5. Open with Word or PDF viewer
```

### Part 5: Project Structure in NetBeans

```
QuizFlow-SmartPaperEngine (Root)
│
├── Source Packages
│   └── com.quizflow
│       ├── QuizFlowGUI.java (Main GUI - 7 tabs)
│       ├── gui/
│       │   └── QuizFlowGUI.java
│       ├── model/
│       │   ├── Subject.java
│       │   ├── Question.java
│       │   ├── QuestionType.java
│       │   └── Quiz.java
│       ├── data/
│       │   └── QuizDatabase.java
│       └── export/
│           ├── WordExporter.java
│           └── PDFExporter.java
│
├── Test Packages
│
├── Libraries
│   └── Apache POI, iText, GSON, SQLite (Auto-downloaded)
│
└── pom.xml (Maven Configuration)
```

### Part 6: Troubleshooting

#### Issue: Cannot find Maven
**Solution:**
- Ensure Maven is installed and PATH is set correctly
- Restart NetBeans after setting PATH
- In NetBeans: Tools → Options → Java → Maven → Maven Home

#### Issue: Build Fails with Dependency Errors
**Solution:**
- Right-click project → Clean and Build again
- Delete `~/.m2/repository` folder and rebuild
- Check internet connection (first build downloads dependencies)
- Try: `mvn clean install -U` (update dependencies)

#### Issue: Main Class Not Found
**Solution:**
- Right-click project → Properties → Run
- Main Class: `com.quizflow.gui.QuizFlowGUI`
- Click OK

#### Issue: Output Folder Not Created
**Solution:**
- Folder created automatically on first export
- Or manually create `output` folder in project root

#### Issue: GUI Window Doesn't Appear
**Solution:**
- Check Java version: `java -version` (should be 11+)
- Try running from command line: `java -jar target/quizflow-gui.jar`
- Check for errors in NetBeans output window

#### Issue: Exported Files Not Showing
**Solution:**
- Check `output/` folder in project directory
- Refresh project in NetBeans (F5)
- Try exporting to different location

### Part 7: Customization

#### Change Database:
Edit `QuizDatabase.java` to add/modify subjects or questions

#### Modify Export Format:
Edit `WordExporter.java` or `PDFExporter.java` to customize document layout

#### Add New Question Type:
1. Add to `QuestionType.java` enum
2. Add handler in `WordExporter.java`
3. Add handler in `PDFExporter.java`
4. Update `QuizFlowGUI.java` combo box

### Part 8: Verify Installation

#### Test Step-by-Step:

**Test 1: Run Application**
```
NetBeans → Right-click project → Run (F6)
→ GUI window should open
```

**Test 2: Create a Question**
- Tab: "Create Question"
- Subject: "Mathematics"
- Question: "2+2=?"
- Type: "True/False"
- Answer: "True"
- Marks: 1
- Click "Save Question"
- Result: "Question saved successfully!"

**Test 3: Create a Quiz**
- Tab: "Create Quiz"
- Subject: "Mathematics"
- Title: "Test Quiz"
- Time: 20
- Select question
- Click "Create Quiz"
- Result: "Quiz created successfully!"

**Test 4: Export Quiz**
- Tab: "Export"
- Quiz: Select created quiz
- Click "Export to Word"
- Result: File saved, dialog shows path

**Test 5: Check Exported File**
- Navigate to `output/` folder
- Open `.docx` or `.pdf` file
- Verify quiz content appears

### Part 9: NetBeans Tips & Tricks

#### Keyboard Shortcuts:
- **F6** - Run project
- **Shift+F6** - Run with arguments
- **Ctrl+Shift+B** - Clean and build
- **Ctrl+D** - Delete line
- **Ctrl+K** - Comment line
- **Alt+Shift+F** - Format code
- **Ctrl+Home** - Go to file start
- **Ctrl+End** - Go to file end

#### Debugging:
1. Set breakpoints (click line number)
2. Right-click project → Debug
3. Use Debug toolbar to step through code

#### NetBeans Settings:
- Tools → Options → Appearance
- Tools → Options → Fonts & Colors
- Tools → Options → Java → Formatting

### Part 10: Development Next Steps

1. **Explore the Code**: 
   - Read `QuizFlowGUI.java` for interface layout
   - Read `QuizDatabase.java` for data management
   - Read exporters for document generation

2. **Enhance Features**:
   - Add database persistence (SQLite)
   - Implement question randomization
   - Add answer key generation
   - Create statistics dashboard

3. **Add Testing**:
   - Create JUnit test cases
   - Test export functionality
   - Validate data models

4. **Improve UI**:
   - Add icons and colors
   - Improve layout with GridLayout
   - Add progress bars

5. **Deploy**:
   - Create executable JAR
   - Package as desktop application
   - Add installer

## Quick Reference Commands

```bash
# Navigate to project
cd QuizFlow-SmartPaperEngine

# Clean and build
mvn clean install

# Run application
java -jar target/quizflow-gui.jar

# Generate documentation
mvn javadoc:javadoc

# Run tests
mvn test

# Build without running tests
mvn clean package -DskipTests
```

## Support Resources

- **NetBeans Documentation**: https://netbeans.apache.org/
- **Maven Guide**: https://maven.apache.org/guides/
- **Java Documentation**: https://docs.oracle.com/en/java/
- **Apache POI**: https://poi.apache.org/
- **iText**: https://itextpdf.com/
- **Swing GUI**: https://docs.oracle.com/javase/tutorial/uiswing/

## File Locations

```
QuizFlow-SmartPaperEngine/
├── src/main/java/                    ← Source code
├── src/main/resources/               ← Resources
├── target/                           ← Compiled files
├── output/                           ← Exported quizzes
├── pom.xml                           ← Maven config
├── README.md                         ← Main documentation
└── SETUP_INSTRUCTIONS.md             ← This file
```

## Common Questions

**Q: How do I run the application?**
A: Press F6 in NetBeans or run `mvn clean package` then `java -jar target/quizflow-gui.jar`

**Q: Where are exported files saved?**
A: In the `output/` folder in your project directory

**Q: Can I use Java 8?**
A: No, Java 11+ is required. Download from oracle.com or openjdk.java.net

**Q: How do I add more subjects?**
A: Edit `QuizDatabase.java` and add to `initializeDefaultSubjects()` method

**Q: Can I export to other formats?**
A: Currently Word and PDF. You can extend exporters for other formats.

**Q: How many questions can I create?**
A: Unlimited - stored in memory until application closes

**Q: Does it save data permanently?**
A: No - data is lost when you close. See Part 10 for adding database persistence.

---

**Last Updated:** May 27, 2026
**Version:** 1.0.0
**Java Version:** 11+
**NetBeans Version:** 30