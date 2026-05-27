# QuizFlow - Quick Start Guide (5 Minutes)

## 🚀 Run It Now!

### Option 1: NetBeans (Easiest)
```
1. Open NetBeans 30
2. File → Open Project
3. Select QuizFlow-SmartPaperEngine folder
4. Press F6
✅ Done! Application starts
```

### Option 2: Command Line
```bash
cd QuizFlow-SmartPaperEngine
mvn clean package
java -jar target/quizflow-gui.jar
```

---

## 📋 What You'll See

A window with 7 tabs:
1. Dashboard
2. Subjects  
3. Create Question
4. Create Quiz
5. View Questions
6. View Quizzes
7. Export

---

## ⚡ 60-Second Demo

### Create Your First Question
```
1. Click "Create Question" tab
2. Subject: Mathematics
3. Question: "What is 5+3?"
4. Type: Multiple Choice
5. Options: "6,7,8,9"
6. Difficulty: EASY
7. Marks: 1
8. Answer: 8
9. Click "Save Question"
✅ Saved!
```

### Create Your First Quiz
```
1. Click "Create Quiz" tab
2. Subject: Mathematics
3. Title: "My First Quiz"
4. Time: 30
5. Instructor: Your Name
6. Select your question
7. Click "Create Quiz"
✅ Quiz created!
```

### Export to Word or PDF
```
1. Click "Export" tab
2. Select "My First Quiz"
3. Click "Export to Word" or "Export to PDF"
✅ File saved to output/ folder!
```

---

## 🎯 Features Ready to Use

✅ **5 Subjects**
- Mathematics
- Science
- History
- Language
- Technology

✅ **7 Question Types**
- Multiple Choice
- True/False
- Drag & Drop
- Short Answer
- Fill Blank
- Matching
- Essay

✅ **2 Export Formats**
- Word (.docx)
- PDF (.pdf)

---

## 📁 What's Where

| File | What It Does |
|------|-------------|
| `QuizFlowGUI.java` | Main application with 7 tabs |
| `Subject.java` | 5 subjects model |
| `Question.java` | Question with 7 types |
| `QuestionType.java` | Enum for types |
| `Quiz.java` | Quiz container |
| `QuizDatabase.java` | Data storage |
| `WordExporter.java` | Create .docx files |
| `PDFExporter.java` | Create .pdf files |

---

## ❓ FAQ

**Q: Do I need to install anything?**
A: Just Java 11+ and Maven. NetBeans is optional.

**Q: Where are my quizzes saved?**
A: In memory only. Create backups by exporting to Word/PDF.

**Q: Can I add more questions?**
A: Yes! Go to "Create Question" tab and repeat.

**Q: How many quizzes can I create?**
A: Unlimited (until you close the app).

**Q: Can I delete questions?**
A: Not in GUI currently. Close and reopen app to reset.

**Q: What if export fails?**
A: Check error message. Usually means output/ folder issue. Create it manually.

---

## 🛠️ Troubleshooting

**App won't start:**
```
1. Check Java version: java -version
2. Should be 11 or higher
3. Reinstall Java if needed
```

**Build fails:**
```
1. Run: mvn clean install
2. Wait for downloads
3. Try again
```

**Can't find project in NetBeans:**
```
1. File → Open Project
2. Navigate to folder with pom.xml
3. Select the folder itself
```

---

## 📞 Need Help?

1. Check SETUP_INSTRUCTIONS.md for detailed setup
2. Check README.md for features overview
3. Open an issue on GitHub
4. Read code comments in source files

---

## ✨ What's Next?

After your first quiz:

1. **Create more questions** - Add 10+ questions across subjects
2. **Make complex quizzes** - Mix question types
3. **Export in both formats** - See Word and PDF output
4. **Share files** - Distribute exported quizzes
5. **Explore code** - Understand architecture

---

**Start using QuizFlow now - Just press F6!** 🎉

Ver 1.0 | May 27, 2026