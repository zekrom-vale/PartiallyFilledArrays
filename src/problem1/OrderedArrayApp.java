package problem1;

/**
 * OrderedArrayApp class<br>
 * <br>
 * Driver of the {@link OrderedArray} class to test the
 * {@link OrderedArray#merge(OrderedArray, OrderedArray)} method
 *
 * @author Shawn Graven (Zekrom)
 * @date   9/23/19
 *
 * @see    #main(String[])
 */
public class OrderedArrayApp{

	/**
	 * @param args
	 *                 Args
	 */
	public static void main(final String[] args){
		final int maxSize=100;             // array size

		//Create OrderedArray's to test merge

		final OrderedArray<Integer> arr2=new OrderedArray<>(
			maxSize, 1, 4, 5, 9, 13, 32, 54, 89, 190, 300, 321, 542, 790, 900,
			1002
			), arr3=new OrderedArray<>(
				maxSize, 6, 2, 3, 1000, 1, 58, 403, 700, 1400, 300, 9, 1545, 500
				);

		//Merge arr2 and arr3, do not deep copy (ie the Integer box)
		OrderedArray.<Integer>merge(arr2, arr3).display();
	}  // end main()

}
