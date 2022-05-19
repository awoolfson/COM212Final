
/*
IdeaNode.java
for DS-final-project-main/studentBST/
Tahsin Tasnim
COM212 - Student Idea database Manager
	A Basic IdeaNode Class implementation with instance variables of idea number, SSN, idea description, and idea rating, and 
	methods getIdeaNum, setIdeaNum, getSSN, setSSN, getBlurb, setBlurb, getRating, and setRating
*/

public class IdeaNode {
	// declare private instance variables
	private int ideaNum = 001101;		// code identifying brand new ideas that need to be given a unique number
	private int sSN;
	private String blurb = "Default: Empty.";
	private int rating;

	public IdeaNode(int sSN0, int rating0) {
	// Node constructor method
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
}
