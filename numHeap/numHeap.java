import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class numHeap {
    private Student[] pq;
    private int n;
    private static final int maxsize = 511;         // capacity of the array

    public numHeap() {
    // constructor method
        n = 0;      // keeps track of the next open spot in the array
        pq = new Student[maxsize + 1];        // declare array
    }

    public boolean isEmpty()
    // sees if the heap is empty or not
        { return n == 0; }

    public int length()
    // returns length of the array
        { return n; }
	
	public Student[] getArray() {
		return pq;
	}
	
	public void clear() {
		for (int i=0;i<pq.length;i++) {
			pq[i]=null;
		}
		n=0;
	}


    private static int parent(int i)
    // returns index of the parent node for the given index of a child node
        { return (i - 1) / 2; }

    private static int leftChild(int i)
    // returns the index of a given parent node's left child
        { return (2 * i) + 1; }

    private static int rightChild(int i)
    // returns the index of a given parent node's right child
        { return (2*i) + 2; }

    public Student findMin()
    // returns the node at the front of the priority queue without deleting it
        { return pq[0]; }

    public int getLastStudentNum() {
        int ideaNumCount = 0;
        try {
            FileReader infile = new FileReader("ideaHeap/ideaStat.txt");
            Scanner reader = new Scanner(infile);
            // read the number in the file
            ideaNumCount = Integer.parseInt(reader.nextLine());
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Input/output error " + e);
        }
        return ideaNumCount;
    }


    public int insert(Student newNode) {
    // inserts a node into the priority queue heap
        if (n >= maxsize) {         // screen for a full array
            System.out.println("Maximum amount of students reached. Delete some for more interaction.");
            return 0;
        }

	else {
		pq[n] = newNode;        // enter node into the last position in the array
		n = n + 1;              // increment n

		int i = n - 1;          // reorder array from the end towards the front if the child is less than the parent
		while (i != 0 && pq[i].getNum() < pq[parent(i)].getNum()) {
		    Student temp = pq[i];              // switch and reorder parent and child as we move up the heap
		    pq[i] = pq[parent(i)];
		    pq[parent(i)] = temp;
		    i = parent(i);
		}
		return newNode.getNum();
	}
    }
	public void sortUp(int i) {
       // reorder array from the end towards the front if the child is less than the parent
		while (i != 0 && pq[i].getNum() < pq[parent(i)].getNum()) {
		    Student temp = pq[i];              // switch and reorder parent and child as we move up the heap
		    pq[i] = pq[parent(i)];
		    pq[parent(i)] = temp;
		    i = parent(i);
		}
	}

	public void remove(int num) {
		for (int i=0;i<pq.length;i++) {
			if (pq[i] != null) {
				if (pq[i].getSSN()==num) {
					pq[i].setNum(-1);
					sortUp(i);
					deleteMin();
					break;
				}
			}
		}
	}

    private void reOrder(int i) {
    // sorts out the array from a given index position down to the end
        int left = leftChild(i);    // get the left and right children of the given node
        int right = rightChild(i);

        int bigOne = i;        // make a variable to hold node with largest rating - assign to given node

        // compare left and right children keys to largest, reassign varaibles
        if (left <= n && pq[left].getNum() < pq[bigOne].getNum()) {
            bigOne = left;
        }
        if (right <= n && pq[right].getNum() < pq[bigOne].getNum()) {
            bigOne = right;
        }

        if (bigOne != i) {     // switch nodes so the largest one is the top
            Student temp = pq[i];
            pq[i] = pq[bigOne];
            pq[bigOne] = temp;
            reOrder(bigOne);       // continue sorting down
        }
    }

    public Student deleteMin() {
    // delete and return the first node in the priority queue heap
        Student min = pq[0];

        pq[0] = pq[n - 1];      // replace first value with the last entered node
        n = n - 1;              // decrement n

        reOrder(0);             // sort the heap down from the top
        return min;
    }

    public void printQueue() {
    // prints contents of the heap array in order
        for (int i = 0; i < n; i++) {
            System.out.println(pq[i].getNum() + ": " + pq[i].getSSN() + ": " + pq[i].getRating());
        }
        System.out.println();
    }
}
