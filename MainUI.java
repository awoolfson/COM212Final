// Joram Stith
// Data Structures
// Dr. Parker

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.PrintWriter;
import java.io.IOException;

public class MainUI {

  private Scanner keyboard;
  private boolean inpError;
	// initialize all of our data structures
  private WebHash objHash;
  private StudentBST objBst;
  private IdeaHeap objHeap;
  private numHeap stuHeap;

  public MainUI() {
    keyboard = new Scanner(System.in);
	  // create our data structures to load data into
    objHash = new WebHash();
    objBst = new StudentBST();
    objHeap = new IdeaHeap();
	stuHeap = new numHeap();
	}

	public void traverserBST(Student r, numHeap nh)
	// inserts a Student node into the numHeap
	{	
		nh.insert(r);
		if (r.getLeft() != null) {
			traverserBST(r.getLeft(), nh);
		}

		if (r.getRight() != null) {
			traverserBST(r.getRight(), nh);
		}

	}
	
	public void traverseBST(StudentBST ob, numHeap nh) 
	// calls traverser
	{
		if (ob.isEmptyTree() == false) {
			nh.clear();
			traverserBST(ob.getRoot(), nh);
		}
		numHeap nh0 = new numHeap();
		for (int i =0; i<nh.length();i++) {
			if (nh.isEmpty()==false) {
				nh0.insert(nh.deleteMin());
			}
		}
		nh=nh0;
	}

  public void load() {

    // Load all the data stored in our files
    System.out.println("load all the data");
    try {
      objHash.loadData();
      objHeap.loadData();
      objBst.loadData();
	traverseBST(objBst, stuHeap); // traverse the BST and enter the information into our studentHeap
	
    }
    catch(IOException e) {
      System.out.println("Load file error:\t" + e);
      System.out.println("Exiting with status code 1...");
      System.exit(1);
    }
  }

  public void save(boolean close) {
    // Save methods scan the data structures and writes them into files
    // Save all data, exit if close
    try {
      objHash.saveData();
      objHeap.saveData();
      objBst.saveData();
    }
    catch(IOException e) {
      System.out.println("Load file error:\t" + e);
      System.out.println("Exiting with status code 1...");
      System.exit(1);
    }
    clearScreen();
    System.out.println("Data saved to disk.");

    if(close) {
	traverseBST(objBst, stuHeap);
      System.out.println("Exiting with status code 0...");
      System.exit(0);
    }
	else {
		traverseBST(objBst, stuHeap);
	}

    System.out.println("Press enter to return to menu."); 		// if the user doesn't want to exit, let them return to menu
    keyboard.nextLine();
  }

  public static void clearScreen() {
  // emptys out the screen between methods
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public int getNumberInput(int low, int high) {
  // makes sure the input we get from users are valid
    boolean inpError;
    int lookupNum = -1;
    do {
      inpError = true;
      System.out.print("> ");
      try {
        String input = keyboard.nextLine();
        lookupNum = Integer.parseInt(input);
        if(lookupNum <= high && lookupNum >= low) {
          inpError = false;
        }
        else {
          System.out.println("Sorry, invalid option. Please try again.");
        }
      }
      catch (NumberFormatException e) {
        System.out.println("Sorry, invalid input. Please try again.");
      }
    } while(inpError);
    return lookupNum;
  }

  public int getNumberInput() {
  // makes sure the input we get from users are valid
    boolean inpError;
    int lookupNum = -1;
    do {
      inpError = true;
      System.out.print("> ");
      try {
        String input = keyboard.nextLine();
        lookupNum = Integer.parseInt(input);
        inpError = false;
      }
      catch (NumberFormatException e) {
        System.out.println("Sorry, invalid input. Please try again.");
      }
    } while(inpError);
    return lookupNum;
  }

  public void mainLoop() {
  // creates the menu and gives users the ability to perform the given tasks
    while(true) {
      clearScreen();
      System.out.println("Welcome to Student Idea Database Manager!");
      System.out.println("Please select an option from the following list:");
      System.out.println("\n________ Student Tasks: ________");
      System.out.println("0.\tView Student");
      System.out.println("1.\tAdd Student");
      System.out.println("2.\tRemove Student");
      System.out.println("3.\tUpdate Student Info");
      System.out.println("4.\tShow All Students");
      System.out.println("5.\tFind Student Login Name");
      System.out.println("\n________ Idea Tasks: ________");
      System.out.println("6.\tAdd Student Idea");
      System.out.println("7.\tSell Global Top Idea");
      System.out.println("8.\tSearch Any Idea");
      System.out.println("9.\tShow All Ideas");
      System.out.println("\n________ General Tasks: ________");
      System.out.println("10.\tSave Current Data");
      System.out.println("11.\tSave and Exit");
      System.out.println("\nEnter number of choice:");

      int choice = getNumberInput();

      switch(choice) {
        case 0:
          // View Student
          viewStudent();
          break;

        case 1:
          // Add Student
          addStudent();
          break;

        case 2:
          // Remove Student
          removeStudent();
          break;

        case 3:
          // Update student Info
          updateStudent();
          break;

	case 4:
          // print all students
	 printAll();
	break;
		
        case 5:
          // Find Student Login Name
          lookupEmail();
          break;

        case 6:
          // Add student idea
          addStudentIdea();
          break;

        case 7:
          // Sell Global Top Idea
          getTopIdea();
          break;
	
	case 8:
          // Search Any Idea
          searchIdea();
          break;
	
	case 9:
          // Show All Ideas
          printAllIdeas();
          break;
	
        case 10:
          // Save current data
          save(false);
          break;

        case 11:
          // Save and exit
          save(true);
          break;
      }
    }
  }

  public void viewStudent() {
  // displays all of the information in a student's Student node, accessed through SSN in the studentBST
    clearScreen();
    System.out.println("Lookup Student Information:");
    System.out.println("Enter Student SSN to search:");
    int inpSSN = getNumberInput(0000, 9999);     // get SSN
    Student response = objBst.search(inpSSN);		// search for the SSN in the BST
    System.out.println("Results:");
    if(response ==null) {
      System.out.println("student not found in database.");
    }
    else {
      System.out.println("Student Name:\t" + response.getName());
      System.out.println("Student Number:\t" + response.getNum());
      System.out.println("Email Login:\t" + response.getEmail());
      System.out.println("Student SSN:\t" + response.getKey());
	System.out.println("Average Rating of Ideas:\t" + response.getRating());

	if (response.getIdeas() != null) {		// display all of a student's ideas if they are entered in the system
		for (int i=0;i<10;i++) {
			if(response.getIdeas().getArray()[i] != null) {
				System.out.println(response.getIdeas().getArray()[i].getBlurb());
				System.out.println("Idea #:");
				System.out.println(response.getIdeas().getArray()[i].getIdeaNum());
				System.out.println("Idea rating:");
				System.out.println(response.getIdeas().getArray()[i].getRating());
			}
		}
	}
	else {
		System.out.println("Student ideas and average ratings not yet implemented.");
	}
    }

    System.out.println("Press enter to return to menu.");
    keyboard.nextLine();
  }

  public void addStudent() {
    // Add a student into the system
    clearScreen();
    System.out.println("Add new student to database:");

     // enter the nexcessary information
    System.out.print("Enter student Last Name:\n> ");
    String inpLastName = keyboard.nextLine();

    System.out.print("Enter student email username:\n> ");
    String inpEmailName = keyboard.nextLine();

    System.out.println("Enter student SSN:");
    int inpSSN = getNumberInput(0000, 9999);

    System.out.println("Enter student number:");
    int inpNum = getNumberInput();

    QueueA inpIdeas = new QueueA();	// initialize and add a queue for ideas to attach to the Student node

    Student inpStudent = new Student(inpLastName, inpSSN, inpEmailName, inpNum);
	inpStudent.setIdeaQueue(inpIdeas);
    objBst.insert(inpStudent);			// insert into the student bst and the hash
    objHash.insert(inpNum, inpEmailName);
	stuHeap.insert(inpStudent);
    System.out.println("Student successfully added.");

    System.out.println("Press enter to return to menu.");
    keyboard.nextLine();
  }

  public void removeStudent() {
    // Remove a student from the system
    clearScreen();
    System.out.println("Delete Student from database:");

    System.out.println("Enter student SSN:");   	// get the SSN and search for if the student exists in the first place
    int inpSSN = getNumberInput(0000, 9999);
    Student target = objBst.search(inpSSN);

    if(target == null) {
      System.out.println("User was not found in database.");
    }
    else {
      int key = target.getNum();
      objBst.delete(target);			// delete student from the studentBST and the studentHeap
	stuHeap.remove(target.getSSN());
      System.out.println("User deleted from student database.");

      // Delete hash entry
      if(objHash.lookUp(key).contains("not Found in Database")) {
        System.out.println("User not in email database, skipping.");
      }
      else {
        objHash.delete(key);
        System.out.println("User deleted from email database.");
      }
    }

    System.out.println("Press enter to return to menu.");
    keyboard.nextLine();
  }

  public void updateStudent() {
    // Update student information
    clearScreen();
    System.out.println("Update a student's information:");
    System.out.println("Enter Student SSN to search:");
    // access the desired student's information using their SSN for the BST
    int inpSSN = getNumberInput(0000, 9999);
    Student response = objBst.search(inpSSN);
    if(response == null) {
      System.out.println("student not found in database.");
      return;
    }
    // choose which field to update 
    System.out.println("Which field would you like to update?");
    System.out.println("1.\tStudent Name:\t" + response.getName());
    System.out.println("2.\tStudent Number:\t" + response.getNum());
    System.out.println("3.\tEmail Login:\t" + response.getEmail());

    int choice = getNumberInput(1,3);
    switch(choice) {
      case 1:
        System.out.print("Enter new name for student:\n> ");
        String name = keyboard.nextLine();
        response.setName(name);
	int num=response.getNum();
	stuHeap.remove(response.getSSN());
	response.setNum(num);
	stuHeap.insert(response);
        break;
      case 2:
        System.out.println("Enter new number for student:");
        int inp = getNumberInput();

        // Delete old key pair from hash
        objHash.delete(response.getNum());
        response.setNum(inp);
        // Add new key pair to hash
	objHash.insert(inp, response.getEmail());
	int num0=response.getNum();
	stuHeap.remove(response.getSSN());
	response.setNum(num0);
	stuHeap.insert(response);
        break;

      case 3:
        System.out.print("Enter new email for student:\n> ");
        String email = keyboard.nextLine();

        // Delete old key pair from hash
        objHash.delete(response.getNum());
        response.setEmail(email);
        // Add new key pair to hash
        objHash.insert(response.getNum(), email);
	int num1=response.getNum();
	stuHeap.remove(response.getSSN());
	response.setNum(num1);
	stuHeap.insert(response);
        break;
    }
    System.out.println("Information updated.");

    System.out.println("Press enter to return to menu.");
    keyboard.nextLine();
  }

  public void addStudentIdea() {
    // Add a student idea
    clearScreen();
    System.out.println("Add Student Idea to student list and global database:");
    // Enter student SSN
    System.out.println("Please enter SSN of student:");
    int ssn = getNumberInput(0000, 9999);
	  
    Student x = objBst.search(ssn);
    if(x == null) { // if a student with that SSN doesn't exist in BST
      System.out.println("Sorry, that student is not currently registered.");
     System.out.println("Press enter to return to menu.");
    keyboard.nextLine();
     return;
    }

    // Enter idea text
    System.out.print("Please enter idea text:\n> ");
    String ideaText = keyboard.nextLine();
    while(ideaText.length() < 20) {
      System.out.print("Please enter at least 20 characters.\n> ");
      ideaText = keyboard.nextLine();
    }

    // Add idea rating
    System.out.println("Please enter idea rating (0-100):");
    int ideaScore = getNumberInput(0, 100);

    // Create input IdeaNode
    IdeaNode inpNode = new IdeaNode(ssn, ideaScore);
    inpNode.setBlurb(ideaText);
    
    int ideaNumber = objHeap.insert(inpNode);
    
    IdeaNode IN = inpNode; 
    IN.setIdeaNum(ideaNumber);
    Student Stu= objBst.search(ssn);
    // Student eStu= objHash.lookUp(Stu.getNum());
    Stu.addIdea(IN);
    // eStu.eAddIdea(IN);
    
    System.out.println("Idea entered to global database.");

    System.out.println("Press enter to return to menu.");
    keyboard.nextLine();
  }
  	
  
  public void printAll() {
	  // print out all of the students currently registered in the student database
	  clearScreen();
	  if (objBst.isEmptyTree()==true) {
		  System.out.println("no students found!");
			System.out.println("Press enter to return to menu.");
    			keyboard.nextLine();
	  }
	  
	  else {
			traverseBST(objBst, stuHeap);
			Student s;			// loop through the studentHeap and print out the information
		  	int n = stuHeap.getArray().length;
			for (int i = 0; i<n; i++) {
				if (stuHeap.getArray()[i] != null) {
					s=stuHeap.getArray()[i];		// pop out off heap and print correspondingly to print in student-number order
					s.setIdeaQueue(objBst.search(s.getSSN()).getIdeas());
					System.out.println(s.getName());
				  System.out.print("student number: ");
				  System.out.println(s.getNum());
				  System.out.print("student email: ");
				  System.out.println(s.getEmail());
				  System.out.print("student SSN: ");
				  System.out.println(s.getSSN());
				  System.out.print("student average idea score: ");
				  System.out.println(s.getRating());
					System.out.println("");
				}
			  }
			System.out.println("Press enter to return to menu.");
    			keyboard.nextLine();
		}
	}

  public void getTopIdea() {
    // dequeue top idea from objHeap
    IdeaNode topIdea = objHeap.deleteMax();
	  
if (topIdea != null) {
	  
    clearScreen();
    System.out.println("The best Conn College has to offer...");
    System.out.println("Idea Number:\t" + topIdea.getIdeaNum());
    System.out.println("Submitter SSN:\t" + topIdea.getSSN());
    System.out.println("Idea Rating:\t" + topIdea.getRating());
    System.out.println("Idea Text:\t" + topIdea.getBlurb()); 
}

else {System.out.println("There are no ideas currently!"); }

    System.out.println("Press enter to return to menu.");
    keyboard.nextLine();
  }

  public void lookupEmail() {
	  // get the user a student's email login from their student number
    // MUST RUN WebHash_Load FIRST!
    clearScreen();
    System.out.println("________ Student Email Login Lookup ________");
    System.out.println("Enter student number to receive their email login name in stunning O(1) average time!");
    int lookup_num = getNumberInput();		// get the student number
    System.out.println(lookup_num);
    String loginName = objHash.lookUp(lookup_num);
    System.out.println("Email login for key " + Integer.toString(lookup_num) + " is:\t" + loginName);
    System.out.println("Press enter to return to menu.");
    keyboard.nextLine();
  }
  
  public void printAllIdeas() {
	  // print every idea ever entered into the system
	clearScreen();
	System.out.println("All ideas:");
	  System.out.println();
	objHeap.print();

    System.out.println();
    System.out.println("What would you like to do?");
    System.out.println("1.\tDelete an idea.\t");
    System.out.println("2.\tReturn to menu.\t");

    int choice = getNumberInput(1,2);
    switch(choice) {
      case 1:
	      System.out.println();
	      System.out.println("Enter the Idea Number:");
              int inp = getNumberInput();

        // option to delete an idea from the linked list of ideas
        IdeaNodeL x = objHeap.deleteFromAllIdeas(inp); 
      if (x == null) {System.out.println("We do not have that idea.");}
      else {System.out.println("Idea successfully removed.");}
        break;
	
	case 2:
             break;
    }
    System.out.println("Press enter to return to menu.");
    keyboard.nextLine(); 
  } 
  
  public void searchIdea() {
   // search up any idea from the idea number
	clearScreen();
    System.out.println("Lookup Any Idea!:");
    System.out.println("Enter an Idea Number to search:");
    int ideanum = getNumberInput(0, 999999999);		// get the SSN
    IdeaNode response = objHeap.search(ideanum);
    System.out.println("Results:");
    if (response !=null) {				// if the idea exists, print everything
	System.out.println("Idea Number:\t" + response.getIdeaNum());
      System.out.println("Student SSN:\t" + response.getSSN());
      System.out.println("Idea Rating:\t" + response.getRating());
      System.out.println("Idea Text:\t" + response.getBlurb());
    }
    else {
      System.out.println("Idea does not exist.");
    }

    System.out.println("Press enter to return to menu.");
    keyboard.nextLine();
  }

  public static void main(String[] args) {
    MainUI run = new MainUI();
    run.load();
    run.mainLoop();
  }
}
