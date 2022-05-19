# DS-final-project
Jangael Rosales, Joram Stith, Tahsin Tasnim, Auden Woolfson

## Compiling

To keep the code clean, the different .java files are stored in different directories. To compile the program, you must tell the CLASSPATH environmental variable to include the various subdirectories where we have files.

### Linux and Mac

**To Compile on Linux/Mac:**
```bash
javac -cp ./:./numHeap:./:./hash:./ideaHeap:./studentBST MainUI.java
```

**To Run on Linux/Mac:**
```bash
java -cp ./:./numHeap:./:./hash:./ideaHeap:./studentBST MainUI
```

### Windows

**To Compile on Windows:**
```bash
javac -cp ./;./numHeap;./;./hash;./ideaHeap;./studentBST MainUI.java
```

**To Run on Windows:**
```bash
java -cp ./;./numHeap;./;./hash;./ideaHeap;./studentBST MainUI
```

## Class Docs

- MainUI: This class is the UI interface that the user would use to access, enter, or remove the student information and idea information.

- Student: This class holds the student information that's stored in an altered Node class; works with the StudentBST and numHeap. 

- StudentBST: This class performs a binary search tree that holds all the Student nodes together and inserts, removes, searches, and prints out student information that the user is looking for.

- StudentEmailNode: This class holds information that associates a student number with a student email address.

- numHeap: a heap of students that prioritizes low student numbers, so that the students can be printed in a list by student number.

- WebHash.java: This class performs a double hashing algorithm that inserts a StudentEmailNode and matches an email username and student number.

- IdeaNode: This class holds the idea information that’s stored in an altered Node class; works with the IdeaHeap.

- IdeaHeap: This class performs a heap that holds all the IdeaNodes together and inserts, removes, searches, and prints out the idea information that a user is looking for.

- QueueA: This queue class works with the StudentBST to associate the latest 10 of a student's ideas with their Student node

- List: This linked list class works with IdeaHeap to show and delete all ideas recorded in the database 

** Text and CSV files are used to load and save data from the data structures when the user opens and closes the application.
