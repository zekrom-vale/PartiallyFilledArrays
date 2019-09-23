package bubbleSort;

class ArrayBub
{
	private final long[] a;                 // ref to array a
	private int size;               // number of data items


	public ArrayBub(final int max)          // constructor 
	{
		this.a = new long[max];                 // create the array
		this.size = 0;                        // no items yet
	}
	public void bubbleSort()
	{
		int out, in;

		for(out=this.size-1; out>1; out--)   // outer loop (backward)
			for(in=0; in<out; in++)        // inner loop (forward)
				if( this.a[in] > this.a[in+1] )       // out of order?
					this.swap(in, in+1);          // swap them
	}  // end bubbleSort()
	public void display()             // displays array contents
	{
		for(int j=0; j<this.size; j++)       // for each element,
			System.out.print(this.a[j] + " ");  // display it
		System.out.println("");
	}
	public void insert(final long value)    // put element into array
	{
		this.a[this.size] = value;             // insert it
		this.size++;                      // increment size
	}
	private void swap(final int one, final int two)
	{
		final long temp = this.a[one];
		this.a[one] = this.a[two];
		this.a[two] = temp;
	}
}  // end class ArrayBub