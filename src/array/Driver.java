/**
 *
 */
package array;

/**
 * @author Zekrom
 *
 */
public class Driver{

	/**
	 * Main method
	 *
	 * @param args
	 *                 Command line arguments
	 */
	public static void main(final String[] args){
		final OrderedArray<Integer> arr
		=new OrderedArray<>(20, 1, 2, 5, 7, 3, 5, 8, 10, 22);
		System.out.println(arr);
		arr.insert(21);
		System.out.println(arr);
		arr.deleteIndex(3);
		System.out.println(arr);
		arr.insert(12, 332, -23);
		System.out.println(arr);

	}

}
