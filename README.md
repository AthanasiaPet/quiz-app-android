#  Multiple Choice Quiz App (Android)

An Android multiple choice quiz application developed as a semester project for a postgraduate program at the University of Western Attica.  
The app allows users to take a timed quiz with images, sound effects, and a final results screen.

---

##  Application Overview

The application follows this flow:

1. **Intro screen** with developer information
2. **Candidate information screen** (name & surname)
3. **Quiz screen**:
   - random selection of questions from a question bank
   - variable number of options per question
   - one correct answer per question
   - optional images per question
   - time-limited exam (timer)
4. **Results screen** displaying:
   - candidate name
   - score (correct / total questions)
   - test completion time
   - feedback message based on performance
5. Option to **retry the quiz**

---

##  Features

-  Multiple choice questions
-  Image support in questions
-  Countdown timer
-  Sound effects during quiz and results
-  Result persistence using SharedPreferences
-  Quiz retry functionality
-  Clean and user-friendly UI

---

##  Technologies Used

- **Java**
- **Android Studio**
- **ConstraintLayout**
- **MediaPlayer**
- **SharedPreferences**
- **JSON** (question database)

---

##  Question Data

Quiz questions are stored in a **JSON file**, including:
- question text
- list of possible answers
- index of the correct answer
- optional image name (drawable resource)

---

##  How to Run

1. Open the project in **Android Studio**
2. Build and run on an emulator or a physical Android device

---

##  Notes

This application was developed according to the semester assignment specifications and includes additional features to enhance user experience.
