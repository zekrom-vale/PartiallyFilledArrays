package problem1;

class OrderedArray{
	long[] a;                 // ref to array a
	int nElems;               // number of data items
	//-----------------------------------------------------------


	public OrderedArray(final int max)          // constructor
	{
		this.a=new long[max];             // create array
		this.nElems=0;
	}

	//-----------------------------------------------------------
	public boolean delete(final long value){
		final int j=this.find(value);
		if(j==this.nElems)                  // can't find it
			return false;
		for(int k=j; k<this.nElems; k++) // move bigger ones down
			this.a[k]=this.a[k+1];
		this.nElems--;                   // decrement size
		return true;
	}  // end delete()
		//-----------------------------------------------------------

	public void display()             // displays array contents
	{
		for(int j=0; j<this.nElems; j++)       // for each element,
			System.out.print(this.a[j]+" ");  // display it
		System.out.println("");
	}

	//-----------------------------------------------------------
	//-----------------------------------------------------------
	public int find(final long searchKey){
		int lowerBound=0;
		int upperBound=this.nElems-1;
		int curIn;

		while(true){
			curIn=(lowerBound+upperBound)/2;
			if(this.a[curIn]==searchKey) return curIn;              // found it
			else if(lowerBound>upperBound) return this.nElems;             // can't find it
			else                          // divide range
			{
				if(this.a[curIn]<searchKey) lowerBound=curIn+1; // it's in upper half
				else upperBound=curIn-1; // it's in lower half
			}  // end else divide range
		}  // end while
	}  // end find()
		//-----------------------------------------------------------

	public void insert(final long value)    // put element into array
	{
		int j;
		for(j=0; j<this.nElems; j++)        // find where it goes
			if(
				this.a[j]>value
			)            // (linear search)
				break;
		for(int k=this.nElems; k>j; k--)    // move bigger ones up
			this.a[k]=this.a[k-1];
		this.a[j]=value;                  // insert it
		this.nElems++;                      // increment size
	}  // end insert()
		//-----------------------------------------------------------

	public int size(){
		return this.nElems;
	}
}  // end class OrdArray