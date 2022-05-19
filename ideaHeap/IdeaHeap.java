/*
IdeaHeap.java
Tahsin Tasnim
COM212 Final Project, Student Idea Database
Idea Heap that holds 511 ideas at a time, through which administrators and executives
can access and use idea information, including the highest rated idea

Public use methods: IdeaHeap(), isEmpty(), length(), getLastIdeaNum(), findMax(), insert(), deleteMax(), printQueue(), loadData(), saveData(), print(), deleteFromAllIdeas(), search()
Internal methods include: parent(), leftChild(), rightChild(), addToHistory(), updateStat(), reOrder(), saveData1()

At the end of the UI, the idea heap that was updated by users is saved into idealog.txt
When the UI is opened again, the idea heap's most recent version is recreated using the information saved in idealog.txt
*/

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IdeaHeap {
    private IdeaNode[] pq;
    private int n;
    private static final int maxsize = 511;         // capacity of the array
    private List allIdeas = new List();		// initialize and create a linked list to hold all ideas in the history

    public IdeaHeap() {
    // constructor method
        n = 0;      // keeps track of the next open spot in the array
        pq = new IdeaNode[maxsize];        // declare array
    }

    public boolean isEmpty()
    // sees if the heap is empty or not
        { return n == 0; }

    public int length()
    // returns length of the array
        { return n; }


    private static int parent(int i)
    // returns index of the parent node for the given index of a child node
        { return (i - 1) / 2; }

    private static int leftChild(int i)
    // returns the index of a given parent node's left child
        { return (2 * i) + 1; }

    private static int rightChild(int i)
    // returns the index of a given parent node's right child
        { return (2*i) + 2; }

    public IdeaNode findMax()
    // returns the node at the front of the priority queue without deleting it
        { return pq[0]; }

    public int getLastIdeaNum() {
        int ideaNumCount = 0;
        try {
            FileReader infile = new FileReader("ideaHeap/ideaStat.txt");
            Scanner reader = new Scanner(infile);
            // read the number in the file
            ideaNumCount = Integer.parseInt(reader.nextLine());
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Input/output error " + e);
        }
        return ideaNumCount;
    }

    public int insert(IdeaNode newNode) {
    // inserts a node into the priority queue heap

        if (n >= maxsize) {         // screen for a full array
            System.out.println("Maximum amount of ideas reached. Delete some for more interaction.");
            return 0;
        }

        if (newNode.getIdeaNum() == 001101) {
            // if the entered node is brand new, assign it the next ideaNum in the history
            newNode.setIdeaNum(getLastIdeaNum() + 1); 
            addToHistory(newNode);}

        pq[n] = newNode;        // enter node into the last position in the array
        n = n + 1;              // increment n

        int i = n - 1;          // reorder array from the end towards the front if the child is less than the parent
        while (i != 0 && pq[i].getRating() > pq[parent(i)].getRating()) {
            IdeaNode temp = pq[i];              // switch and reorder parent and child as we move up the heap
            pq[i] = pq[parent(i)];
            pq[parent(i)] = temp;
            i = parent(i);
        }
	// add new idea into the linked list
	IdeaNodeL newNodeL = new IdeaNodeL(newNode.getSSN(), newNode.getRating());		
	newNodeL.setBlurb(newNode.getBlurb());
	newNodeL.setIdeaNum(newNode.getIdeaNum());
	allIdeas.insert(newNodeL);
	return newNode.getIdeaNum();
    }

    private void addToHistory(IdeaNode x) {
	// add a new IdeaNode's information into our file of all ideas ever
        try (FileWriter file = new FileWriter("ideaHeap/ideaHistory.txt", true);
                BufferedWriter b = new BufferedWriter(file);
                PrintWriter outfile = new PrintWriter(b);) {

            outfile.println(x.getIdeaNum());
            outfile.println(x.getSSN());
            outfile.println(x.getRating());
            outfile.println(x.getBlurb());

            outfile.close();
            updateStat();   // update the number of ideas in the history
        }

        catch (IOException e) {
            System.out.println("Input/output error " + e);
        }
    }

    private void updateStat() {
	// updates our stat of how many ideas are in the databse, for idea self-numbering purposes
        try {
            FileReader infile = new FileReader("ideaHeap/ideaStat.txt");
            Scanner reader = new Scanner(infile);

            // get the statistic
            int ideaNumCount = Integer.parseInt(reader.nextLine());
            reader.close();

            PrintWriter outfile = new PrintWriter("ideaHeap/ideaStat.txt");
            outfile.println(ideaNumCount + 1);	// rewrite the stat
            outfile.close();
        }

        catch (IOException e) {
            System.out.println("Input/output error " + e);
        }

        catch (NumberFormatException e) {
            System.out.println(e + ": The input is not a number.");
        }
    }

    private void reOrder(int i) {
    // sorts out the array from a given index position down to the end
        int left = leftChild(i);    // get the left and right children of the given node
        int right = rightChild(i);

        int bigOne = i;        // make a variable to hold node with largest rating - assign to given node

        // compare left and right children keys to largest, reassign varaibles
        if (left <= n && pq[left].getRating() > pq[bigOne].getRating()) {
            bigOne = left;
        }
        if (right <= n && pq[right].getRating() > pq[bigOne].getRating()) {
            bigOne = right;
        }

        if (bigOne != i) {     // switch nodes so the largest one is the top
            IdeaNode temp = pq[i];
            pq[i] = pq[bigOne];
            pq[bigOne] = temp;
            reOrder(bigOne);       // continue sorting down
        }
    }

    public IdeaNode deleteMax() {
    // delete and return the first node in the priority queue heap
	    if (isEmpty() == false) {
        IdeaNode min = pq[0];

        pq[0] = pq[n - 1];      // replace first value with the last entered node
        n = n - 1;              // decrement n

        reOrder(0);             // sort the heap down from the top
        return min;
	    }
	    
	    else {System.out.println("Empty list!"); 
		return null;}
    }

    public void printQueue() {
    // prints contents of the heap array in order
        for (int i = 0; i < n; i++) {
            System.out.println(pq[i].getIdeaNum() + ": " + pq[i].getSSN() + ": " + pq[i].getRating());
        }
        System.out.println();
    }

    public void loadData() {
      // reads text file and creates the heap structure for use at the beginning of UI interaction

      try { FileReader infile = new FileReader("ideaHeap/idealog.txt");
            Scanner reader = new Scanner(infile);

        while (reader.hasNextLine()) {         // while the text file has information
            // collect each idea's information
            int ideaNum =  Integer.parseInt(reader.nextLine());
            int ssn = Integer.parseInt(reader.nextLine());
            int rating = Integer.parseInt(reader.nextLine());
            String blurb = reader.nextLine();

            // create the idea node based on the gathered data
            IdeaNode nodeX = new IdeaNode(ssn, rating);

            // upon creation, the new node will have the ideaNum code of 001101
            // we have to reset the ideaNum to it's ordered, assigned ideaNum based when entered in ideaHistory
            nodeX.setIdeaNum(ideaNum);

            // reset the idea's blurb from the newnode default description to its entered description
            nodeX.setBlurb(blurb);
            insert(nodeX);      // insert node into heap
        }
          infile.close();
      }

      catch (IOException e) {
          System.out.println("Input/output error " + e);
      }

      catch (NumberFormatException e) {
          System.out.println("This is not a number: " + e);
      }
    }

    public void saveData() {
      // scans data structures and writes it into a text file at the end of UI interaction
        saveData1();
        allIdeas.saveList("ideaHeap/ideaHistory.txt");		// read and rewrite the linked list into ideaHistory.txt
    }

private void saveData1() {
      // reads the heap data structure into a text file at the end of UI interaction
      try {
        PrintWriter outfile = new PrintWriter("ideaHeap/idealog.txt");
          // create loop that reads through the heap and adds it to the idealog
          for (int i = 0; i < n; i++) {
              outfile.println(pq[i].getIdeaNum());
              outfile.println(pq[i].getSSN());
              outfile.println(pq[i].getRating());
              outfile.println(pq[i].getBlurb());
        }
          outfile.close();
      }

      catch (IOException e) {
          System.out.println("Input/output error " + e);
      }
    }

    public void print() {
	    // print the linked list to show user all ideas recorded in the system
        if (allIdeas.isEmpty() == false) { 		// while the list has something
            allIdeas.saveList("ideaHeap/ideaHistory.txt"); 	// save the contents of the list
            allIdeas.clearList(); 		// clear the list to recreate 
		// this is done to avoid double-nodes if user hits this option more than once + to constantly maintain the ideaHistory
        }

      try { FileReader infile = new FileReader("ideaHeap/ideaHistory.txt");
            Scanner reader = new Scanner(infile);

        while (reader.hasNextLine()) {         // while the text file has information
            // collect each idea's information
            int ideaNum =  Integer.parseInt(reader.nextLine());
            int ssn = Integer.parseInt(reader.nextLine());
            int rating = Integer.parseInt(reader.nextLine());
            String blurb = reader.nextLine();

            // create the idea node based on the gathered data
            IdeaNodeL nodeX = new IdeaNodeL(ssn, rating);

            // upon creation, the new node will have the ideaNum code of 001101
            // we have to reset the ideaNum to it's ordered, assigned ideaNum based when entered in ideaHistory
            nodeX.setIdeaNum(ideaNum);

            // reset the idea's blurb from the newnode default description to its entered description
            nodeX.setBlurb(blurb);

            allIdeas.insert(nodeX);      // insert node into list of all ideas
        }
        allIdeas.printList();		// print the list
          infile.close();
      }

      catch (IOException e) {
          System.out.println("Input/output error " + e);
      }

      catch (NumberFormatException e) {
          System.out.println("This is not a number: " + e);
      }        
    }

    public IdeaNodeL deleteFromAllIdeas(int ideaNum0) {
	    // delete an idea from the linked list of all ideas
        IdeaNodeL x = allIdeas.searchRemove(ideaNum0);		
        return x;
    } 
    
    public IdeaNode search(int x)  {
	    // search for a specific idea based on idea number
	String ideanum0 =Integer.toString(x);		// turn the given idea number into a string to scan the history file
	IdeaNode found = null;
	    
	try { FileReader infile = new FileReader("ideaHeap/ideaHistory.txt");
            Scanner reader = new Scanner(infile);
	    String ideanum = reader.nextLine();
		
	    while ((ideanum != null) && !(ideanum.equals(ideanum0))) {         
		// while the text file has information and while we don't find the matching idea number
		ideanum = reader.nextLine(); //keep reading down the file
	     }
		
		if (ideanum != null) {	// if the idea exists and we found it, gather info
			int num = Integer.parseInt(ideanum);
			int ssn = Integer.parseInt(reader.nextLine());
			int rating = Integer.parseInt(reader.nextLine());
			String blurb = reader.nextLine();
			// create a node with the gathered info
			found = new IdeaNode(ssn, rating);
			found.setIdeaNum(num);
			found.setBlurb(blurb);
		}
          infile.close();
      }
      
      	catch (FileNotFoundException fnfe) {
			System.out.println("file not found (reader)"); }
			
	finally { return found; // return the node with the information 
	} 
    }
}

