//Auden Woolfson, NodeBST (binary search tree), COM212, 4/14/2022
/*
This is an altered Node class that adds the left and right pointers instead of the next pointer. This class
is specifically made to be used for the binary search tree class. In this case, it is called student to be used
in the final project.
*/

public class Student
{
	private String name;
	private int SSN;
	private Student left;
	private Student right;
	private String email;
	private int num;
	private QueueA ideas;
	private double avgRating;

	public Student(String name0, int SSN0, String email0, int num0)
	{
		name = name0;
		SSN = SSN0;
		email = email0;
		num = num0;
	}
	public void addIdea(IdeaNode i)
	{
		if (ideas != null) {
			ideas.enqueue(i);
		}
	}
	public void setIdeaQueue(QueueA IdeaQueue0)
	{
		ideas=IdeaQueue0;
	}
	public void setLeft(Student left0)
	{
		left=left0;
	}
	public void setRight(Student right0)
	{
		right=right0;
	}
	public Student getRight()
	{
		return right;
	}
	public Student getLeft()
	{
		return left;
	}
	public int getKey()
	{
		return SSN;
	}
	public int getSSN()
	{
		return SSN;
	}
	public String getName()
	{
		return name;
	}
	public String getEmail()
	{
		return email;
	}
	public int getNum()
	{
		return num;
	}
	public void setName(String inpName) {
		name = inpName;
	}
	public void setNum(int inp) {
		num = inp;
	}
	public void setEmail(String inpStr) {
		email = inpStr;
	}
	public void setSSN(int inpSSN) {
		SSN = inpSSN;
	}
	public QueueA getIdeas() {
		return ideas;
	}
	
	public double getRating() {
		ideas.average();
		avgRating = ideas.getAvg();
		return avgRating;
		}
}
