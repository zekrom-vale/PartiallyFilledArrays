package problem2;

class ArrayIns
{
	/**
	 * The reference to the long array
	 */
	private final long[] arr;
	/**
	 * The current size of the array, not the capacity
	 *
	 * @see #arr.length
	 */
	private int size;

	/**
	 * Alternate constructor
	 *
	 * @param max
	 *                The capacity of the array
	 */
	public ArrayIns(final int max){
		this.arr = new long[max];                 // create the array
		this.size=0;                        // no items yet
	}
	/**
	 * Prints the array to the console
	 */
	public void display(){
		//For each element in the array print it to the console
		//Deliminated by a space and a trailing space
		for(int j=0; j<this.size; j++) System.out.print(this.arr[j]+" ");
		System.out.println("");
	}
	/**
	 * Insert the given element to the array
	 *
	 * @param value
	 *                  he value to insert
	 */
	public void insert(final long value){
		this.arr[this.size]=value;             // insert it
		this.size++;                      // increment size
	}
	/**
	 * Sort the array via Insertion Sorting by standard numerical order
	 */
	public void insertionSort(){
		int in, out;

		for(out=1; out<this.size; out++)     // out is dividing line
		{
			final long temp = this.arr[out];            // remove marked item
			in = out;                      // start shifts at out
			while(in>0 && this.arr[in-1] >= temp) // until one is smaller,
			{
				this.arr[in] = this.arr[in-1];            // shift item to right
				--in;                       // go left one position
			}
			this.arr[in] = temp;                  // insert marked item
		}  // end for
	}  // end insertionSort()

	/**
	 * Finds the median value of the array<br>
	 * <b>Sorts the array</b>
	 *
	 * @return The median value of the array
	 */
	public long median(){
		//Sort the array to be able to find the median
		this.insertionSort();
		final int at=this.size/2;
		//If even
		if(this.size%2==0){
			return (this.arr[at]+this.arr[at+1])/2L;
		}
		//If odd
		return this.arr[at];
	}
}
