package problem3;

/**
 * ArrayOddEven class<br>
 * Implements a simple {@code UnnorderedArray<long>} that provides an Odd Even Sorting method.
 *
 * @author Shawn Graven (Zekrom)
 * @date   9/23/19
 *
 * @see    #ArrayOddEven(int)
 * @see    #oddEvenSort()
 */
public class ArrayOddEven{
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
	 * Sorts the array via Odd Even Sorting, using standard numerical order
	 */
	public void oddEvenSort(){
		boolean sorted=false;
		while(!sorted){
			sorted=true;
			for(int s=0; s<2; s++){
				for(int i=s; i<this.size-1; i+=2){
					if(this.arr[i]>this.arr[i+1]){
						sorted=false;
						final long temp=this.arr[i];
						this.arr[i]=this.arr[i+1];
						this.arr[i+1]=temp;
					}
				}
			}
		}
	}
}  // end class ArrayBub
