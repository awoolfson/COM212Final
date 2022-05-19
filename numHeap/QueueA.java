/*
QueueA.java
for DS-final-project-main/numHeap/
Tahsin Tasnim
COM212 - Student Idea database Manager
	A basic queue class with an array implementation with instance variables of size, n, front, end, queue0, and avg 
	and methods QueueA(), front(), dequeue(), enqueue(), clear(), isEmpty(s), length(), getArray(), loadData(), saveData(), average(), and getAvg()
*/

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;


public class QueueA {
	// declare private instance variables

	private int size; 
	private int n;		// counter variable
	private int front;	// keeps track of front node
	private int end;
	private IdeaNode[] queue0;
	private double avg=0;		// the average ff all ideas currently in queue

	public QueueA() {
		// returns a newly created empty queue

		queue0 = new IdeaNode[10]; 
		size = 10;
		n = 0;		// n is the next empty spot in the array for the stack
		front = 0;
	} 

	public IdeaNode front()  {
		// returns the first element in the queue
		if (n == 0) {
			return null;
		}
		else {
			return queue0[front];
		}
	}

	public IdeaNode dequeue() { 
		// returns and deletes the first element in the queue		
		if (n == 0) {
			return null;
		}
		else {
			int temp = front;
			front = (front + 1) % size;
			n--;
			return queue0[temp];
		}
	}

	public void enqueue(IdeaNode x) {
		// add another idea into the queue
		if ((n +1) == 10) { // when reaches 10 nodes, dequues the oldest nodes 
			dequeue();
		}
		
		end = (front + n) % size;
		queue0[end] = x;
		n++;
		
	}
	public void clear() {
		// emptys the queue
		while (isEmpty() == false){
			dequeue();
		}
	}

	public boolean isEmpty()	{ 
			return (n == 0);
	}
	
	public int length() {
		return n;
	}
	public IdeaNode[] getArray()
	{
		return queue0;
	}
	public BufferedReader loadData(BufferedReader reader, int ssn)
		throws NumberFormatException, IOException {
      // reads text file and creates the heap structure for use at the beginning of UI interaction

      try { 
	
	BufferedReader reader0 = reader;
	for (int i = 0; i < 10; i++) {
		reader0 = reader;
		if (reader0.readLine() != null) {
		    int ideaNum =  Integer.parseInt(reader.readLine());
		    int rating = Integer.parseInt(reader.readLine());
		    String blurb = reader.readLine();

		    // create the idea node based on the gathered data
		    IdeaNode nodeX = new IdeaNode(ssn, rating); 
		    nodeX.setIdeaNum(ideaNum);

		    // reset the idea's blurb from the newnode default description to its entered description
		    nodeX.setBlurb(blurb);
		    enqueue(nodeX);     // insert node into heap
		}
		else {
		}
	}
      }

      catch (NumberFormatException e) {
          System.out.println("This is not a number: " + e);
      }
	return reader;
    }

    public String saveData()
	{
	
      // turns the data structure into a text file at the end of UI interaction

          // create loop that reads through the heap and adds it to the idealog
		String qData="";
		int nn=n;
		for (int i = 0; i < nn; i++) {
		IdeaNode b = dequeue();
              	qData+=b.getIdeaNum()+",";
             	qData+=b.getRating()+",";
              	qData+=b.getBlurb();
		if (i != nn-1) {
			qData+=",";
		}
		
        }
		return qData;
      
    }

	 public void average() {
		 // averages the idea ratings for all the ideas currently in the queue		 
		if (isEmpty() == false) { 
			int tot=0;
			int count=0;
			for (int i=0; i<10; i++) {
				if (queue0[i] != null) {
					count++;
					tot = tot + queue0[i].getRating();
				}
			}
			avg = tot / count;
		}
	}
	
	public double getAvg() {
		// returns the idea rating average 		
		average();
		return avg;
	}
}