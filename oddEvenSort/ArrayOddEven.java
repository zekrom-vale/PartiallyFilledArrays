package oddEvenSort;

class ArrayOddEven
{
	private long[] a;                 // ref to array a
	private int nElems;               // number of data items
	//--------------------------------------------------------------
	public ArrayBub(int max)          // constructor
	   {
	   a = new long[max];                 // create the array
	   nElems = 0;                        // no items yet
	   }
	//--------------------------------------------------------------
	public void insert(long value)    // put element into array
	   {
	   a[nElems] = value;             // insert it
	   nElems++;                      // increment size
	   }
	//--------------------------------------------------------------
	public void display()             // displays array contents
	   {
	   for(int j=0; j<nElems; j++)       // for each element,
	      System.out.print(a[j] + " ");  // display it
	   System.out.println("");
	   }
	//--------------------------------------------------------------
	public void oddEvenSort(){
      		
	   }
	//--------------------------------------------------------------
}  // end class ArrayBub
