package problem1;

/**
 * OrderedArrayApp class
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
		final OrderedArray<Integer> arr=new OrderedArray<>(
			maxSize, 1, 4, 5, 9, 13, 32, 54, 89, 190, 300, 321, 542, 790, 900,
			1002
			);

		arr.insert(6, 2, 3, 1000, 1, 58, 403, 700, 1400, 300, 9, 1545, 500);
		//arr.insert(1, 2, 3, 6, 9, 58, 300, 403, 500, 700, 1000, 1400, 1545);

		arr.display();

		final OrderedArray<Integer> arr2=new OrderedArray<>(maxSize);

		arr2.insert(77);                // insert 10 items
		arr2.insert(99);
		arr2.insert(77);
		arr2.insert(44);
		arr2.insert(55);
		arr2.insert(22);
		arr2.insert(88);
		arr2.insert(11);
		arr2.insert(00);
		arr2.insert(66);
		arr2.insert(33);

		final int searchKey=55;            // search for item
		if(
			arr2.find(searchKey)!=arr.size()
			) System.out.println("Found "+searchKey);
		else System.out.println("Can't find "+searchKey);

		arr2.display();                 // display items

		arr2.delete(00);                // delete 3 items
		arr2.delete(55);
		arr2.delete(99);

		arr.display();                 // display items again
	}  // end main()

}
