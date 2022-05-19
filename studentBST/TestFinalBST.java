import java.io.FileNotFoundException;
import java.io.IOException;

class TestFinalBST{
	public static void main(String[] args) 
			throws FileNotFoundException, IOException {
		StudentBST Students=new StudentBST();
		try {
			Students.loadData();
			Students.saveData();
		}
		catch (FileNotFoundException ff) {
			System.out.println("file not found");
		}
	}
}