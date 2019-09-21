package orderedArray;

/**
 * OrderedArrayApp
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
		/*@off
		arr.insert(1);
		arr.insert(2);
		arr.insert(3);
		arr.insert(6);
		arr.insert(9);
		arr.insert(58);
		arr.insert(300);
		arr.insert(403);
		arr.insert(500);
		arr.insert(700);
		arr.insert(100);
		arr.insert(1400);
		arr.insert(1545);
		/*
		@off
		arr.insert(77);                // insert 10 items
		arr.insert(99);
		arr.insert(77);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);
		@on*/

		//final int searchKey=55;            // search for item
		//if(
		//	arr.find(searchKey)!=arr.size()
		//) System.out.println("Found "+searchKey);
		//else System.out.println("Can't find "+searchKey);

		arr.display();                 // display items

		arr.delete(00);                // delete 3 items
		arr.delete(55);
		arr.delete(99);

		arr.display();                 // display items again
	}  // end main()

}
