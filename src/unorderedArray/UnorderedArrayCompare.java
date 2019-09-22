package unorderedArray;

import java.util.function.BinaryOperator;
//TODO import  Array_sample code and Simple Sorting_sample
/**
 * Unordered Partly filled Array, with sorting functionality {@link UnorderedArray} for non
 * comparable objects<br>
 * Requires Comparable implementation
 *
 * @see       UnorderedArray
 * @see       Comparable
 * @see       #oddEvenSort()
 * @see       #median(BinaryOperator)
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

	//TODO TEST
	/**
	 * Sorts the unordered array based on {@link Comparable} implimentation via Bubble Sort
	 */
	public void bubbleSort(){
		int upperBound=this.size()-1;
		//Loop until upperBound is 1 (Can't compare just one item)
		while(upperBound>1){
			//Compare upto upperBound
			for(int j=0; j<upperBound; j++){
				if(this.get(j).compareTo(this.get(j+1))>0){
					final E obj=this.get(j);
					this.set(j, this.get(j+1));
					this.set(j+1, obj);
				}
			}
			//Decrease as this.get(upperBound) is sorted
			upperBound--;

		}
	}

	//TODO TEST
	/**
	 * Sorts the unordered array based on {@link Comparable} implimentation via Insertion Sort
	 */
	public void insertionSort(){
		//For each element except for the first one do the folowing
		//Don't do it for the first one as it is in the correct position amoung itself
		for(int out=1; out<this.size(); out++){
			//Save the "inserted" element to move the others
			final E temp=this.get(out);
			//Move the existing elements to make room and find where it fits
			int in=out;
			while(in>0&&this.get(in-1).compareTo(temp)>=0){
				this.set(in, this.get(--in));
			}
			//Set the last positon to refference
			this.set(in, temp);
		}
	}



	/**
	 * Finds <b>only one</b>median value, ignores if the {@code size()} is even <br>
	 * <b>Will sort the array!</b>
	 *
	 * @return         The median value
	 */
	public E median(){
		//Sort the array to be able to find the median
		this.bubbleSort();
		return this.get(this.size()/2);
	}

	/**
	 * Finds the median value, if it is even it will use the {@code average} function <br>
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
		this.bubbleSort();
		final int at=this.size()/2;
		//If even
		if(this.size()%2==0){
			return average.apply(this.get(at), this.get(at+1));
		}
		//If odd
		return this.get(at);
	}

	/**
	 * Finds the one or two median values and retuns it as an array<br>
	 * <b>Will sort the array!</b>
	 *
	 * @return         The median value
	 */
	public Object[] medianBoth(){
		//Sort the array to be able to find the median
		this.bubbleSort();
		final int at=this.size()/2;
		//If even
		if(this.size()%2==0){
			return new Object[]{this.get(at), this.get(at+1)};
		}
		//If odd
		return new Object[]{this.get(at)};
	}

	/**
	 * Sorts the unordered array based on {@link Comparable} implimentation via Odd Even Sort
	 */
	public void oddEvenSort(){
		boolean done=false;
		while(!done){
			done=true;
			for(int s=0; s<2; s++){
				for(int i=0; i<this.size(); i+=2){
					if(this.get(i+1).compareTo(this.get(i+1))>0){
						done=false;
						final E obj=this.get(i);
						this.set(i, this.get(i+1));
						this.set(i+1, obj);
					}
				}
			}
		}
	}

	//TODO TEST
	/**
	 * Sorts the unordered array based on {@link Comparable} implimentation via Selection Sort
	 */
	public void selectionSort(){
		for(int out=0; out<this.size()-1; out++){
			final int min=out;
			E minValue=this.get(min);
			for(int in=out+1; in<this.size(); in++){
				final E inValue=this.get(in);
				//If out of order swap values
				if(inValue.compareTo(minValue)<0){
					this.set(out, minValue);
					this.set(in, inValue);
					//Update the minumum value to be correct
					minValue=inValue;
				}
			}
		}
	}
}
