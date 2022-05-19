/* 
TestHeap.java
Tahsin Tasnim
COM212 Final Project, Student Idea Database

This is a test file to model the first use of the UI and IdeaHeap
Adds and interacts with a total of 10 IdeaNodes into the system
Saves the updated IdeaHeap at the end
*/

public class TestHeap {
    public static void main(String[] args) {

	IdeaHeap a = new IdeaHeap();

	System.out.println("isEmpty = " + a.isEmpty());
	System.out.println("length = " + + a.length());

	IdeaNode xNode = new IdeaNode(0000, 75);
	xNode.setBlurb("Put orbeez in sewers to prevent flooding");

	IdeaNode yNode = new IdeaNode(1234, 60);
	yNode.setBlurb("Solar powered gokarts all over campus");

	IdeaNode zNode = new IdeaNode(3567, 43);
	zNode.setBlurb("Teach heimlich maneuver to every SA and floor gov");

	IdeaNode kNode = new IdeaNode(9821, 88);
	kNode.setBlurb("Pay the professors better so they stay here");

	IdeaNode aNode = new IdeaNode(1127, 97);
	aNode.setBlurb("Have mandatory student campus-clean up days every month");

	IdeaNode bNode = new IdeaNode(5021, 64);
	bNode.setBlurb("Have a designated nap area in the library");

	IdeaNode cNode = new IdeaNode(5657, 10);
	cNode.setBlurb("Have a local restaurant bring boba once every two weeks");

	IdeaNode dNode = new IdeaNode(9378, 55);
	dNode.setBlurb("School-wide games/activities tournaments to boost school spirit");

	IdeaNode mNode = new IdeaNode(4041, 88);
	mNode.setBlurb("Open another coffee shop in Unity House");

	IdeaNode nNode = new IdeaNode(7426, 91);
	nNode.setBlurb("Let New London youth visit campus for activities");

	System.out.println();
	System.out.println("inserting 0000, 1234, 3567, 9821...");
	a.insert(xNode);
	a.insert(yNode);
	a.insert(zNode);
	a.insert(kNode);

	a.printQueue();
	System.out.println("length = " + + a.length());

	System.out.println();
	System.out.println("inserting 4041, 7426...");
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
	System.out.println("inserting 1127");
	a.insert(aNode);

	System.out.println();
	a.printQueue();
	System.out.println("length = " + + a.length());


	System.out.println("inserting 5021, 5657, 9378...");
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

	System.out.println("CHECK 123");

	a.print();

	System.out.println("CHECK 456");

	IdeaNodeL x = a.deleteFromAllIdeas(5);
	System.out.println(x.getIdeaNum());

	System.out.println();
	System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

	a.print();

	a.saveData();
	}
}
