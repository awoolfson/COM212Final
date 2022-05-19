// Joram Stith
// Data Structures
// Dr. Parker

public class StudentEmailNode {
  /**
  * This class holds the necessary information for a student email node.
  * All class level variables are public, since all can be freely accessed and modified.
  * @param inp_number   Student Number (int)
  * @param inp_name     Student Email Username (string)
  */

  // Class level variables
  public int studentNumber;
  public String emailUserName;
  private int SSN;
  private double avg;
  private String name;
  // private QueueA ideas;

  // Constructor (student number, student email username)
  public StudentEmailNode(int inp_number, String inp_name) {
      studentNumber = inp_number;
      emailUserName = inp_name;
	//SSN=SSN0;
	//avg=avg0;
	//name=name0;
  }
  
        public int getNum() {
		return studentNumber;
	}
	public String getEmail() {
		return emailUserName;
	} 
	
	/*
	public void eSetIdeaQueue(QueueA ideas0) {
		ideas = ideas0;
	}
	
	public void eAddIdea(IdeaNode i) {
		ideas.enqueue(i);
	}
	
	public int getSSN() {
		return SSN;
	}
	public double egetAvg() {
		ideas.average();
		return ideas.getAvg();
	}
	public String getName() {
		return name;
	}
	*/

  // toString method with basic information - for debugging
  @Override
  public String toString() {
    return "StudentEmailNode with studentNumber " + Integer.toString(studentNumber) + ", emailUserName " + emailUserName;
  }
}
