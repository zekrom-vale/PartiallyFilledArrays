package selectionSort;

class ArraySel
{
	private final long[] a;                 // ref to array a
	private int nElems;               // number of data items
	//--------------------------------------------------------------
	public ArraySel(final int max)          // constructor
	{
		this.a = new long[max];                 // create the array
		this.nElems = 0;                        // no items yet
	}
	//--------------------------------------------------------------
	public void display()             // displays array contents
	{
		for(int j=0; j<this.nElems; j++)       // for each element,
			System.out.print(this.a[j] + " ");  // display it
		System.out.println("");
	}
	//--------------------------------------------------------------
	public void insert(final long value)    // put element into array
	{
		this.a[this.nElems] = value;             // insert it
		this.nElems++;                      // increment size
	}
	//--------------------------------------------------------------
	public void selectionSort()
	{
		int out, in, min;

		for(out=0; out<this.nElems-1; out++)   // outer loop
		{
			min = out;                     // minimum
			for(in=out+1; in<this.nElems; in++) // inner loop
				if(this.a[in] < this.a[min] )         // if min greater,
					min = in;               // we have a new min
			this.swap(out, min);                // swap them
		}  // end for(out)
	}  // end selectionSort()
	//--------------------------------------------------------------
	private void swap(final int one, final int two)
	{
		final long temp = this.a[one];
		this.a[one] = this.a[two];
		this.a[two] = temp;
	}
	//--------------------------------------------------------------
}  // end class ArraySel