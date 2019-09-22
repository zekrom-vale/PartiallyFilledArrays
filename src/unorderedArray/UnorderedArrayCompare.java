package unorderedArray;

import java.util.function.BinaryOperator;

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

	/**
	 * Finds the one or two median values<br>
	 * <b>Will sort the array!</b>
	 *
	 * @param  average
	 *                     A function that finds the average between two elements<br>
	 *                     Required as generics do not support math operators and not all objects can
	 *                     work with average.
	 *
	 * @return         The median value
	 */
	public E median(final BinaryOperator<E> average){
		//Sort the array to be able to find the median
		this.oddEvenSort();
		final int at=this.size()/2;
		//If even
		if(this.size()%2==0){
			return average.apply(this.get(at), this.get(at+1));
		}
		//If odd
		return this.get(at);
	}

	/**
	 * Sorts the unordered array based on {@link Comparable} implimentation
	 */
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
