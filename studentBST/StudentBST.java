//Auden Woolfson, Student BST, COM212 final
// Implementation of findStudentIdeas done by Tahsin and Jangael
/*
fill the requirement to be able to search for student records in under O(n) time using the SSN
*/

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class StudentBST
{
	private Student root;
	private String filePath ="bstData.csv";
	private String filePath2 = "studentBST/studentIdeas.txt";
	
	public StudentBST()
	{
		root=null;
	}
	
	public Student getRoot() {
		return root;
	}
	
	public boolean isEmptyTree() //checks if there is a root
	{
		return root==null;
	}
	
	public void insertr(Student r, Student x) //recursively searches through the tree for where to put the inserted node and adds it
	{
		if (x.getKey()<r.getKey())
		{
			if (r.getLeft()==null)
			{
				r.setLeft(x);
			}
			else
			{
				insertr(r.getLeft(), x);
			}
		}
		else if (x.getKey()>r.getKey())
		{
			if (r.getRight()==null)
			{
				r.setRight(x);
			}
			else
			{
				insertr(r.getRight(), x);
			}
		}
	}
	
	public void insert(Student x) //either adds a root, or calls insertr
	{
		if (isEmptyTree())
		{
			root=x;
		}
		else
		{
			insertr(root, x);
		}
	}
	
	public void traverser(Student r) //recursivley prints every Node in the tree
	{
		if (r != null)
			{
				traverser(r.getLeft());
				System.out.print(r.getKey());
				System.out.print(" ");
				traverser(r.getRight());
			}
	}
	
	public void traverse() //calls traverser
	{
		traverser(root);
		System.out.println("");
	}
	
	private Student succ(Student target) //finds a Nodes successor, removes it from the tree, and returns it
	{
		Student sParent = target;
		Student s = target;
		Student temp = target.getRight();
		int its = 0;
		while (temp != null)
		{
			sParent=s;
			s=temp;
			its=its+1;
			temp=temp.getLeft();
		}
		if (its>1)
		{
			sParent.setLeft(s.getRight());
		}
		else
		{
			sParent.setRight(s.getRight());
		}
		return s;
	}
	
	public void delete(Student a) //removes a node from the tree
	{
		Student temp = root;
		Student parent = root;
		boolean isLeft=false;
		while (temp.getKey() != a.getKey())
		{
			parent=temp;
			if (a.getKey()<temp.getKey())
			{
				isLeft=true;
				temp=parent.getLeft();
			}
			else if (a.getKey()>temp.getKey())
			{
				isLeft=false;
				temp=parent.getRight();
			}
		}
		if (temp.getLeft()==null && temp.getRight()==null)
		{
			if (temp==root)
			{
				root=null;
			}
			else
			{
				if (isLeft)
				{
					parent.setLeft(null);
				}
				else
				{
					parent.setRight(null);
				}
			}
		}
		else if (temp.getLeft()==null)
		{
			if (temp==root)
			{
				root=root.getRight();
			}
			else if (isLeft)
			{
				parent.setLeft(temp.getRight());
			}
			else
			{
				parent.setRight(temp.getRight());
			}
		}
		else if (temp.getRight()==null)
		{
			if (temp==root)
			{
				root=root.getLeft();
			}
			else if (isLeft)
			{
				parent.setLeft(temp.getLeft());
			}
			else
			{
				parent.setRight(temp.getLeft());
			}
		}
		else
		{
			Student succ=succ(temp);
			succ.setRight(temp.getRight());
			succ.setLeft(temp.getLeft());
			if (temp==root)
			{
				root=succ;
			}
			else if (isLeft)
			{
				parent.setLeft(succ);
			}
			else
			{
				parent.setRight(succ);
			}
		}
	}
	
	public Student search(int Key) //returns the Node in the tree with the input Key
	{
		if (root != null) {
			Student temp = root;
			while (temp.getKey() != Key)
			{
				if (temp.getKey()>Key)
				{
					if (temp.getLeft() != null) {
						temp=temp.getLeft();
					}
					else {
						return null;
					}
				}
				else if (temp.getKey()<Key)
				{
					if (temp.getRight() != null) {
						temp=temp.getRight();
					}
					else {
						return null;
					}
				}
			}
			return temp;
		}
		else {
			return null;
		}
	}
	
	public void printTree() //prints the Nodes in the tree with their left and and right children
	{
		if (root==null) {
			System.out.println("empty tree");
		}
		else {
			printTree2(root);
			System.out.println();
		}
	}
	
	private void printTree2(Student tree)
	{
		if (tree != null)
		{
			System.out.print(tree.getKey() + " ");
			if (tree.getLeft() != null)
			{
				System.out.print("Left: " + tree.getLeft().getKey() + " ");
			}
			else
			{
				System.out.print("Left: null ");
			}
			if (tree.getRight() != null)
			{
				System.out.println("Right: " + tree.getRight().getKey() + " ");
			}
			else
			{
				System.out.println("Right: null ");
			}
			printTree2(tree.getLeft());
			printTree2(tree.getRight());

		}
	}
	public void saveData()
	//Saves the root in a text file, and calls a recursive function to save the rest similarly
		throws FileNotFoundException, IOException
	{
		PrintWriter out = null;
		try
		{
			if (root != null)
			{
				String sData="";
				out = new PrintWriter(filePath);
				out.print(root.getName());
				out.print(",");
				out.print(root.getKey());
				out.print(",");
				out.print(root.getEmail());
				out.print(",");
				out.print(root.getNum());
				out.print(",");
				out.print(root.getIdeas().saveData());
				sData+=saveData2(root, sData);
				out.println(sData);
				out.close();
			}
		}
		catch (FileNotFoundException fnfe)
		{
			System.out.println("file not found (reader)");
		}
	}
	public String saveData2(Student s, String sData)
		throws IOException
	//recursive function to save all the students into a text file
	{
		try {
			sData+="\n";
			if (s.getLeft() != null)
			{
				System.out.println("left");
				sData += s.getLeft().getName() + "," + s.getLeft().getKey() + "," + s.getLeft().getEmail() + "," + s.getLeft().getNum() + ",";
				sData += s.getLeft().getIdeas().saveData();
				sData += saveData2(s.getLeft(), "");
			}
			if (s.getRight() != null)
			{
				System.out.println("right");
				sData += s.getRight().getName() + "," + s.getRight().getKey() + "," + s.getRight().getEmail() + "," + s.getRight().getNum() + ",";
				sData += s.getRight().getIdeas().saveData();
				sData += saveData2(s.getRight(), "");
			}
		}
		catch (IOException e) {
			System.out.println("Input/output error " + e);
		}
		return sData;
	}

	
	public void loadData()
	//reads a text file and calls a recursive function to turn it in to Student objects in the tree
		throws FileNotFoundException, IOException
	{
		try  {  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("bstData.csv"));  
			String line = "";  
			String splitBy = ",";  
			
			while ((line = br.readLine()) != null)   //returns a Boolean value  
				{  
					String[] studentInfo = line.split(splitBy);   // use comma as separator 
					if (studentInfo.length > 1) {
						for (int i = 0; i < studentInfo.length; i++) {
							System.out.println(i + ":" + studentInfo[i]) ;}
							
						String name0 = studentInfo[0];
						int SSN0 = Integer.parseInt(studentInfo[1]);
						String email0 = studentInfo[2];
						int num0 = Integer.parseInt(studentInfo[3]);
							
						System.out.println("TAHSIN");
						System.out.println (" name " + name0 +  " SSN " + SSN0 + " email " + email0 + " student num " + num0 );
						
						int len = studentInfo.length;
						int ideaIndex = 3;
						int max = (len - 4) / 3;
						QueueA ideas = new QueueA();

						for (int i = 0; i < (max); i++) {
							ideaIndex++;
							int ideaNum = Integer.parseInt(studentInfo[ideaIndex]);
							ideaIndex++;
							int rating = Integer.parseInt(studentInfo[ideaIndex]);
							ideaIndex++;
							String blurb = studentInfo[ideaIndex];
							
							IdeaNode nodeX = new IdeaNode(SSN0, rating); 
							nodeX.setIdeaNum(ideaNum);

							// reset the idea's blurb from the newnode default description to its entered description
							nodeX.setBlurb(blurb);
							ideas.enqueue(nodeX); 	
						}
					Student a = new Student(name0, SSN0, email0, num0);
					a.setIdeaQueue(ideas);
					insert(a);
					printTree(); 
				}
			}	
		}   
		catch(FileNotFoundException fnfe) {
			System.out.println("file not found (reader)");
		}
		catch(IOException e) {
			System.out.println("io exception");
		} 
	}
}
