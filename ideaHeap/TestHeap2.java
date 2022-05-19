/* 
TestHeap2.java
Tahsin Tasnim
COM212 Final Project, Student Idea Database

This is a test file to model a second use of the UI and IdeaHeap
Loads the previous data of the heap for user interaction
Adds and interacts with a total of 10 additional IdeaNodes into the system
Saves the updated IdeaHeap at the end
*/

public class TestHeap2 {
    public static void main(String[] args) {

    IdeaHeap a = new IdeaHeap();
	a.loadData("idealog.txt");
	a.printQueue();

	System.out.println();

	System.out.println("isEmpty = " + a.isEmpty());
	System.out.println("length = " + + a.length());

	IdeaNode xNode = new IdeaNode(3789, 76);
	xNode.setBlurb("Install napping areas in the library");

	IdeaNode yNode = new IdeaNode(4021, 69);
	yNode.setBlurb("Mandatory enrollment in at least 1 club for students");

	IdeaNode zNode = new IdeaNode(9073, 43);
	zNode.setBlurb("Club festivals every spring semester for every club");

	IdeaNode kNode = new IdeaNode(2021, 85);
	kNode.setBlurb("Student vs Faculty/Staff tournaments (athletic or otherwise)");

	IdeaNode aNode = new IdeaNode(1130, 97);
	aNode.setBlurb("Bring back the Knowlton (I think) dining room");

	IdeaNode bNode = new IdeaNode(4000, 21);
	bNode.setBlurb("Bring in a boba maker. We won't complain anymore");

	IdeaNode cNode = new IdeaNode(9289, 12);
	cNode.setBlurb("Required COM110 and GWs231 for every student");

	IdeaNode dNode = new IdeaNode(8456, 55);
	dNode.setBlurb("School wide dances and events for holidays and special occasions (ie christmas and valentines day)");

	IdeaNode mNode = new IdeaNode(7731, 88);
	mNode.setBlurb("Take sexual assault cases seriously");

	IdeaNode nNode = new IdeaNode(6425, 91);
	nNode.setBlurb("Help build homeless shelters and encourage public housing");

//////////////////////
	System.out.println();
	System.out.println("inserting 3789, 4021, 9073, 2021...");
	a.insert(xNode);
	a.insert(yNode);
	a.insert(zNode);
	a.insert(kNode);

	a.printQueue();
	System.out.println("length = " + + a.length());

	System.out.println();
	System.out.println("inserting 7731, 6425...");
	a.insert(mNode);
	a.insert(nNode);

	a.printQueue();
	System.out.println("length = " + + a.length());

	System.out.println();
	System.out.println("deleting max...");

	for (int i = 1; i < 4; i++)  {
		IdeaNode temp = a.deleteMax();
		System.out.println(temp.getSSN() + " Blurb: " + temp.getBlurb());}

	System.out.println();
	a.printQueue();
	System.out.println("isEmpty = " + a.isEmpty());
	System.out.println("length = " + + a.length());

	System.out.println();
	System.out.println("inserting 1130");
	a.insert(aNode);

	System.out.println();
	a.printQueue();
	System.out.println("length = " + + a.length());


	System.out.println("inserting 4000, 9289, 8456...");
	a.insert(bNode);
	a.insert(cNode);
	a.insert(dNode);

	a.printQueue();
	System.out.println("length = " + + a.length());
	
	System.out.println();
	System.out.println("deleting max...");
	IdeaNode temp = a.deleteMax();
	System.out.println(temp.getSSN() + " Blurb: " + temp.getBlurb());

	System.out.println();
	a.printQueue();
	System.out.println("length = " + + a.length());
	System.out.println("isEmpty = " + a.isEmpty());

	a.saveData();
	}
} 