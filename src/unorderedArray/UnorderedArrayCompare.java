package unorderedArray;

/**
 * Unordered Partly filled Array, with sorting functionality {@link UnorderedArray} for non
 * comparable objects<br>
 * Requires Comparable implementation
 * 
 * @see       Comparable
 *
 * @param <E>
 *                The element type
 */
public class UnorderedArrayCompare <E extends Comparable<E>>
	extends UnorderedArray<E>{

	/**
	 * Crates an empty unordered array
	 *
	 * @param max
	 *                The maximum size of the array
	 */
	public UnorderedArrayCompare(final int max){
		super(max);
	}

	/**
	 * Crates an empty unordered array
	 *
	 * @param max
	 *                       The maximum size of the array
	 * @param collection
	 *                       The values to init with
	 */
	@SafeVarargs
	public UnorderedArrayCompare(final int max, final E... collection){
		super(max, collection);
	}

	@SuppressWarnings("unchecked")
	public void oddEvenSort(){
		boolean done=false;
		while(!done){
			done=true;
			for(int s=0; s<2; s++){
				for(int i=0; i<this.size(); i+=2){
					if(this.get(i+1).compareTo(this.get(i+1))>0){
						done=false;
						final Object obj=this.get(i);
						this.set(i, this.get(i+1));
						this.set(i+1, (E)obj);
					}
				}
			}
		}
	}
}
