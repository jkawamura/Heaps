package polling.treesheaps;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvException;

/**
 * Main driver class for lab 6. Takes in a single command line argument and reads the file
 * into an arrayheap.
 *
 * @author Joseph Kawamura
 * @version 15 April 2020
 */
public class Main {
    public static void main(String[] args) {

        ArrayList<String[]> myEntries; //makes an arraylist to store all the rows in the file
        ArrayHeap<Candidate> candidateHeap = new ArrayHeap<>(); //creates an instance of the arrayheap class

        try {
            //imports the specified file into an arraylist of string arrays
            CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(args[0]));
            myEntries = new ArrayList<>(reader.readAll());

            //catches errors with import
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return;
        }

        //loops through all the rows in the file and adds each one to the arrayheap
        for (String[] row : myEntries) {
            Candidate input = new Candidate(row);
            candidateHeap.insert(input);
        }

        //sets a counter equal to the initial size because size will decrease as removeMax is called
        int counter = candidateHeap.size();

        //loops through the length of the array and calls removeMax each time
        for(int i = 0; i < counter; i++){

            //prints out the largest value in the heap and removes it
            System.out.println(candidateHeap.removeMax());
        }


        heapTest();
    	// TODO: first create tests for your ArrayHeap class (in a separate method below).

        // TODO: then read in the polling data from the given file (just one) and create
		// a heap of candidates to be sorted by their polling numbers
    }

    public static void heapTest(){
        ArrayHeap<Integer> test = new ArrayHeap<>();
        test.insert(-2);
        test.insert(3);
        test.insert(9);
        test.insert(-7);
        test.insert(1);
        test.insert(2);
        test.insert(6);
        test.insert(-3);
        //test.insert(1);
        System.out.println(test);

        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(-2,3,9,-7,1,2,6,-3));
        System.out.println(input);
        ArrayHeap<Integer> test2 = new ArrayHeap<>(input);
        System.out.println(test2);
        test2.sort();
        System.out.println(test2);

        ArrayHeap<Integer> letterHeap = new ArrayHeap<Integer>();
        letterHeap.insert(8);
        letterHeap.insert(6);
        letterHeap.insert(7);
        letterHeap.insert(3);
        letterHeap.insert(2);
        letterHeap.insert(5); // inserting again, will still include
        letterHeap.insert(4);
        letterHeap.insert(1);


        System.out.println("Now a heap: ");
        System.out.println(letterHeap);

        letterHeap.sort();
        System.out.println("\nNow sorted: ");
        System.out.println(letterHeap);

        ArrayHeap<Character> letter = new ArrayHeap<Character>();
        letter.insert('A');
        letter.insert('C');
        letter.insert('G');
        letter.insert('B');
        letter.insert('D');
        letter.insert('G'); // inserting again, will still include
        letter.insert('F');
        letter.insert('E');
        letter.insert('H');
        letter.insert('I');

        System.out.println("Now a heap: ");
        System.out.println(letter);

        letter.sort();
        System.out.println("\nNow sorted: ");
        System.out.println(letter);
    }
}
