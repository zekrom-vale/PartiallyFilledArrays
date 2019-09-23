package oddEvenSort;

class ArrayOddEven
{
	/**
	 * The reference to the long array
	 */
	private final long[] arr;	//Final allows writing to it but not replacing an existing value
	/**
	 * The current size of the array
	 */
	private int size;


	/**
	 * Alternate constructor
	 *
	 * @param max
	 *                The maximum capacity of the array
	 */
	public ArrayOddEven(final int max){
		this.arr=new long[max];                 // create the array
		this.size=0;                        // no items yet
	}
	/**
	 * Prints the array to the console
	 */
	public void display(){
		//For each element print it to the console including an extra space at the end
		for(int j=0; j<this.size; j++) System.out.print(this.arr[j]+" ");
		System.out.println("");
	}
	/**
	 * Inserts the given value into the array
	 *
	 * @param value
	 *                  The value to insert
	 */
	public void insert(final long value){
		this.arr[this.size]=value;             // insert it
		this.size++;                      // increment size
	}
	/**
	 * Sorts the array via Odd Even Sorting, using standard numrical order
	 */
	public void oddEvenSort(){
		boolean done=false;
		while(!done){
			done=true;
			for(int s=0; s<2; s++){
				for(int i=0; i<this.size; i+=2){
					if(this.arr[i]>this.arr[i+1]){
						done=false;
						final long temp=this.arr[i];
						this.arr[i]=this.arr[i+1];
						this.arr[i+1]=temp;
					}
				}
			}
		}
	}
}  // end class ArrayBub
