/* 
TestHeap3.java
Tahsin Tasnim
COM212 Final Project, Student Idea Database

This is a test file to model a third use of the UI and IdeaHeap
Loads the previous data of the heap for user interaction
Checks the contents of the heap after it was loaded
Collects and deletes the highest rated idea
Saves the updated IdeaHeap at the end
*/

public class TestHeap3 {
		public static void main(String[] args) {
			
			IdeaHeap a = new IdeaHeap();
			a.loadData("idealog.txt");

			System.out.println("loading from Test3: " + a.deleteMax().getSSN());

			a.printQueue();	
			a.saveData();
	}
}