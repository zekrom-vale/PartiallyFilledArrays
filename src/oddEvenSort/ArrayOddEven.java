package oddEvenSort;

class ArrayOddEven
{
	private final long[] a;                 // ref to array a
	private int nElems;               // number of data items
	//--------------------------------------------------------------


	public ArrayOddEven(final int max)          // constructor
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
	public void oddEvenSort(){
		boolean done=false;
		while(!done){
			done=true;
			for(int s=0; s<2; s++){
				for(int i=0; i<this.nElems; i+=2){
					if(this.a[i+1]>this.a[i+1]){
						done=false;
						final long temp=this.a[i];
						this.a[i]=this.a[i+1];
						this.a[i+1]=temp;
					}
				}
			}
		}
	}
	//--------------------------------------------------------------
}  // end class ArrayBub
