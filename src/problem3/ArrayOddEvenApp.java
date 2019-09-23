package problem3;

/**
 * ArrayOddEvenApp class
 *
 * Drives the {@link ArrayOddEven} class, inserts the following numbers into an {@link ArrayOddEven}
 * object
 *
 * <pre>
 * 77, 99, 44, 55, 22, 88, 11, 0, 66, 33
 * </pre>
 *
 * @author Shawn Graven (Zekrom)
 * @date   9/23/19
 *
 * @see    #main(String[])
 */
public class ArrayOddEvenApp{
	/**
	 * @param args
	 *                 Args
	 */
	public static void main(final String[] args)
	{
		final int maxSize = 100;            // array size
		ArrayOddEven arr;                 // reference to array
		arr = new ArrayOddEven(maxSize);  // create the array

		arr.insert(77);               // insert 10 items
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);

		arr.display();                // display items

		arr.oddEvenSort();             // bubble sort them

		arr.display();                // display them again
	}  // end main()
}  // end class BubbleSortApp
