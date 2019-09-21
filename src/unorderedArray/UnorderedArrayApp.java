package unorderedArray;

class UnorderedArrayApp{
	public static void main(final String[] args){
		final int maxSize=100;            // array size
		final UnorderedArray<Integer> arr=new UnorderedArray<>(maxSize); // create the array

		arr.insert(77, 99, 44, 55, 22, 88, 11, 00, 66, 33);

		arr.display();                // display items

		final int searchKey=35;           // search for item
		if(arr.exists(searchKey)) System.out.println("Found "+searchKey);
		else System.out.println("Can't find "+searchKey);

		arr.delete(00);               // delete 3 items
		arr.delete(55);
		arr.delete(99);

		arr.display();                // display items again
	}  // end main()
}  // end class UnorderedArrayApp
