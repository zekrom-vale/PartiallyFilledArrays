package insertionSort;

class ArrayIns
{
	private final long[] a;                 // ref to array a
	private int size;               // number of data items
	public ArrayIns(final int max)          // constructor
	{
		this.a = new long[max];                 // create the array
		this.size=0;                        // no items yet
	}
	public void display()             // displays array contents
	{
		for(int j=0; j<this.size; j++)       // for each element,
			System.out.print(this.a[j] + " ");  // display it
		System.out.println("");
	}
	public void insert(final long value)    // put element into array
	{
		this.a[this.size]=value;             // insert it
		this.size++;                      // increment size
	}
	public void insertionSort()
	{
		int in, out;

		for(out=1; out<this.size; out++)     // out is dividing line
		{
			final long temp = this.a[out];            // remove marked item
			in = out;                      // start shifts at out
			while(in>0 && this.a[in-1] >= temp) // until one is smaller,
			{
				this.a[in] = this.a[in-1];            // shift item to right
				--in;                       // go left one position
			}
			this.a[in] = temp;                  // insert marked item
		}  // end for
	}  // end insertionSort()

	public long median(){
		//Sort the array to be able to find the median
		this.insertionSort();
		final int at=this.size/2;
		//If even
		if(this.size%2==0){
			return (this.a[at]+this.a[at+1])/2L;
		}
		//If odd
		return this.a[at];
	}
}  // end class ArrayIns
