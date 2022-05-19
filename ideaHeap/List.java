/*
List.java
for DS-final-project-main/ideaHeap/
Tahsin Tasnim
COM212 - Student Idea Database Manager
	Linked List Class with methods: List(), length(), isEmpty(), searchReturn(key), searchRemove(key), insert(IdeaNodeL), printList(), saveList(), and clearList().
*/

import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;


public class List {
	// declare private instance variables
	private IdeaNodeL head;  	// IdeaNodeL pointer for beginning of list
	private int n;		// counter variable


	public List() {
		// constructor method
		head = null;
		n = 0;
	} 

	public int length(){
	 	// returns n 
		return n;
	}

 	public boolean isEmpty() {
 		// returns true if the list is empty and false if it is not
 		return n == 0;
 	}	

 	public IdeaNodeL searchReturn(int key0) {
 		// returns a pointer to the IdeaNodeL where the key = key0

		if (n == 0) {			// screen for an empty list
			System.out.println("Empty List!");
			return null;}

 		IdeaNodeL temp = head;

 		while (temp != null) { 			// before the list ends
 			if (temp.getIdeaNum() == key0) {		// base case: we've found our node with a matching idea number
 				return temp;	}
 			else {						// recursive case: we move to the next pointer to search
 				temp = temp.getNext();	}
 		}
 		return null;				// its not in the list
 	}

 	public IdeaNodeL searchRemove(int key0) {
 		// returns a pointer to the IdeaNodeL where the key = key0 and removes it from the list
		
		if (n == 0) {			// screen for an empty list
			System.out.println("Empty List!");
			return null;}

		if (head.getIdeaNum() == key0)	{			// if its the first item in the list
			IdeaNodeL found = head;
			head = head.getNext();			// redirect pointers around the item we're looking for
			found.setNext(null);

			n--;					// decrement n
			return found;}

		IdeaNodeL temp = head;			// if its not the first item

 		while (temp.getNext() != null) { 			// before the list ends
 			if (temp.getNext().getIdeaNum() == key0) {		// base case: we've found our node with a mathcing idea number
 				IdeaNodeL found = temp.getNext();
 				temp.setNext(temp.getNext().getNext());			// redirect pointers around the item we're looking for
 				found.setNext(null);

 				n--;					// decrement n
 				return found;}

 			else {						// recursive case: we move to the next pointer to search
 				temp = temp.getNext();		}
 			}	
 		return null;				// its not in the list
 	}

 	public void insert(IdeaNodeL xX) {
 		// inserts xX into the list
 		xX.setNext(head);		// attach it to the front of the list
		head = xX;
		n++;			// increment n
 	}

	public void printList() {
		// prints the keys of each of the nodes of the list in the order they are in the list
		System.out.println(n);		// print length of list

		if (n == 0) {			// screen for an empty list
			System.out.println("Empty List!");
			return;}

		IdeaNodeL temp = head;

 		while (temp != null) { 			// before the list ends, format and print all the information for each node
			System.out.println("Idea Number: " + temp.getIdeaNum());
			System.out.println("Student SSN: " + temp.getSSN());
			System.out.println("Idea Rating: " + temp.getRating());
			System.out.println("Idea Description: " + temp.getBlurb());
			System.out.println();
 		 	temp = temp.getNext();	}
	}

 	public void saveList(String filename) {
 	// scans a linked list and reads it into a file
 		try {
        PrintWriter outfile = new PrintWriter(filename);
        IdeaNodeL temp = head;

        while (temp != null) { 			// create loop that reads through the list and prints it to the file
			outfile.println(temp.getIdeaNum());
			outfile.println(temp.getSSN());
			outfile.println(temp.getRating());
			outfile.println(temp.getBlurb());
 		 	temp = temp.getNext();	
 		 }
          outfile.close();
      }

      catch (IOException e) {
          System.out.println("Input/output error " + e);
      }
 	}
	
 	public void clearList() {
	// clear out the list
 		head = null;
 		n = 0;
 	} 
	}
	
	
	
	
	
	
	
	
	
	
	