import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Nicholas Treu
 */
public class ArraysTest {

    ArrayList<int[]> arraysList;

    // Create an ArrayList of int[] of a random size and add random ints to it
    @Before
    public void setUp() throws Exception {
        arraysList = new ArrayList<>(100);
        for(int i = 0; i < 100; i++){
            // Create an int[] between 1 - 100 in size
            int randomNumber = 1 + (int)(Math.random() * 100);
            int[] temp = new int[randomNumber];
            
            // Add to it random numbers between 1 and 1000
            for(int j = 0; j < temp.length; j++){
                int randomNum = 1 + (int)(Math.random() * 1000);
                temp[j] = randomNum;
            }
            
            // Add the array to arraysList.
            arraysList.add(temp);
        }
    }

    // Clear the arraysList and then run garbage collector
    @After
    public void tearDown() throws Exception {
        arraysList.clear();
        System.gc();
    }

    /*
        Test if that after calling Arrays.sort(), the arrays are still the same size.
     */
    @Test
    public void testArraysSize(){
        for(int i = 0; i < arraysList.size(); i++){
            // Get the initial size of the array at the index in the ArrayList
            int initialSize = arraysList.get(i).length;

            // Now sort the array at this index in the ArrayList
            Arrays.sort(arraysList.get(i));

            // Now get the size of the array after it has been sorted
            int afterSortedSize = arraysList.get(i).length;

            // If the array sizes aren't equal, then fail the test
            if(initialSize != afterSortedSize){
                fail();
            }
        }
    }

    /*
        Test that all the values once sorted either are the same or increase from the
        value before it
     */
    @Test
    public void testValuesIncreaseOrSame(){
        for(int i = 0; i < arraysList.size(); i++){
            int[] temp = arraysList.get(i);

            // Sort the array
            Arrays.sort(temp);

            // Now test that every item is either the same or one greater than its previous index
            for(int j = 1; j < temp.length; j++){
                if(temp[j] < temp[j-1]){
                    fail();
                }
            }
        }
    }

    /*
        Test that every int that is in the original array is still in the sorted array
     */
    @Test
    public void testEquality(){
        for(int i = 0; i < arraysList.size(); i++){
            int[] arrayCopy = new int[arraysList.get(i).length];

            // Make a copy of the array
            System.arraycopy(arraysList.get(i), 0, arrayCopy, 0, arraysList.get(i).length);

            // Sort both of them
            Arrays.sort(arraysList.get(i));
            Arrays.sort(arrayCopy);

            // Now that they are both sorted, check either or not they are equal.
            assertTrue(Arrays.equals(arraysList.get(i), arrayCopy));
        }
    }
}
