import java.io.FileNotFoundException;
import java.io.IOException;

public class TestStudents
{
    public static void main(String[] args)
        throws FileNotFoundException
    {
	IdeaNode a = new IdeaNode(1, 1); // ssn, rating
	a.setIdeaNum(1); // idea number 
	a.setBlurb("aaaaaaaaaaaaaaaaaaaa");
	
	IdeaNode b = new IdeaNode(2, 2);
	b.setIdeaNum(2);
	b.setBlurb("bbbbbbbbbbbbbbbbbbbb");

	IdeaNode c = new IdeaNode(3, 3);
	c.setIdeaNum(3);
	c.setBlurb("cccccccccccccccccccc");

	QueueA q1 = new QueueA();
	q1.enqueue(a);
	q1.enqueue(b);
	q1.enqueue(c);

	QueueA q2 = new QueueA();
	q2.enqueue(c);
	q2.enqueue(a);
	q2.enqueue(b);

	QueueA q3 = new QueueA();
	q3.enqueue(b);
	q3.enqueue(c);
	q3.enqueue(a);

	QueueA q4 = new QueueA();
	q4.enqueue(b);
	q4.enqueue(a);
	q4.enqueue(c);
	q4.average();
	
        Student Adam = new Student("Adam", 1, "a", 1);
	Adam.setIdeaQueue(q1);
        Student Bea = new Student("Bea", 2, "b", 2);
	Bea.setIdeaQueue(q2);
        Student Carter = new Student("Carter", 3, "c", 3);
	Carter.setIdeaQueue(q3);
        Student Delia = new Student("Delia", 4, "d", 4);
	Delia.setIdeaQueue(q4);
        StudentBST t = new StudentBST();
        t.insert(Adam);
        t.insert(Bea);
        t.insert(Carter);
        t.insert(Delia);
        try
        {
	    t.printTree();
            t.saveData();
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
        t.delete(Adam);
	t.printTree();
	System.out.println("adam deleted");
        t.delete(Bea);
        t.delete(Carter);
        t.delete(Delia);
	t.printTree();
        try
        {
            t.loadData();
	    t.printTree();
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
	t.delete(Carter);
        t.delete(Delia);
        try
        {
            t.saveData();
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
        t.printTree();
    }
}
