
/*
IdeaNodeL.java
for DS-final-project-main/ideaHeap/
Tahsin Tasnim
COM212 - Student Idea database Manager
	A Basic IdeaNodeL Class implementation with instance variables of idea number, SSN, idea description, idea rating, and next IdeaNodeL location, and 
	methods getIdeaNum, setIdeaNum, getSSN, setSSN, getBlurb, setBlurb, getRating, setRating, getNext, and setNext
*/


public class IdeaNodeL {
	// declare private instance variables
	private int ideaNum = 001101;		// code identifying brand new ideas that need to be given a unique number
	private int sSN;
	private String blurb = "Default: Empty.";
	private int rating;
 	private IdeaNodeL next;		// IdeaNodeL pointer next

	public IdeaNodeL(int sSN0, int rating0) {
	// IdeaNodeL constructor method
	// differentiate parameters from instance variables
		sSN = sSN0;
		rating = rating0;
		}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb0) {
	// insert blurb separately for easier use
		blurb = blurb0;
	}

	public int getIdeaNum(){
		return ideaNum;
	}

	public void setIdeaNum(int ideaNum0) {
		ideaNum = ideaNum0;
	}

	public int getSSN(){
		return sSN;
	}

	public void setSSN(int sSN0){
		sSN = sSN0;
	}

	public int getRating(){
		return rating;
	}

	public void setRating(int rating0){
		rating = rating0;
	}

	public IdeaNodeL getNext(){
	// return where next pointer is pointing for the next IdeaNodeL
		return next;
	}

	public void setNext(IdeaNodeL next0){
	// reassign where pointer is pointing for the next IdeaNodeL, return nothing
		next = next0;
	}
}