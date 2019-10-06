package array;

import java.util.function.BinaryOperator;
//TODO import  Array_sample code and Simple Sorting_sample
/**
 * UnorderedArrayCompare class<br>
 *
 * Unordered Partly filled Array, with sorting functionality {@link UnorderedArray} for non
 * comparable objects<br>
 * Requires Comparable implementation
 *
 * @author     Shawn Graven (Zekrom)
 * @date       9/23/19
 *
 * @see        UnorderedArray
 * @see        Comparable
 * @see        #oddEvenSort()
 * @see        #median(BinaryOperator)
 *
 * @param  <E>
 *                 The element type
 */
public class UnorderedArrayCompare <E extends Comparable<E>>
extends UnorderedArray<E>{

	/**
	 * @param  n
	 *                   The size of the UnorderedArray
	 * @param  lower
	 *                   The minimum value
	 * @param  upper
	 *                   The maximum value
	 * @return       A random filled {@code QuickSort<Double>}
	 */
	public static UnorderedArrayCompare<Double> generateRandomDouble(
		final int n, final double lower, final double upper
		){
		//Create the Double QuickSort Array
		final UnorderedArrayCompare<Double> arr=new UnorderedArrayCompare<>(n);
		//Fill the QuickSort with random doubles
		new Random().doubles(n, lower, upper).forEach(x->{
			arr.insert(x);
		});
		return arr;
	}

	/**
	 * @param  n
	 *                   The size of the UnorderedArray
	 * @param  lower
	 *                   The minimum value
	 * @param  upper
	 *                   The maximum value
	 * @return       A random filled {@code QuickSort<Integer>}
	 */
	public static UnorderedArrayCompare<Integer>
	generateRandomInt(final int n, final int lower, final int upper){
		//Create the Integer QuickSort Array
		final UnorderedArrayCompare<Integer> arr=new UnorderedArrayCompare<>(n);
		//Fill the QuickSort with random ints
		new Random().ints(n, lower, upper).forEach(x->{
			arr.insert(x);
		});
		return arr;
	}


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
	}/**
	 * Simple manual sort to assist in {@link #quickSort()}
	 *
	 * @param left
	 *                  The left index to start at
	 * @param right
	 *                  The right index to stop at
	 */
	private void manualSort(final int left, final int right){
		switch(right-left) {
			case 0:
				//If size 1 do nothing
				return;
			case 1:
				//If size 2 swap if out of order
				this.swapIf(left, right);
				return;
			case 2:
				//If size 3 swap each position if out of order
				this.swapIf(left, left+1);
				this.swapIf(left, right);
				this.swapIf(left+1, right);
		}

	}

	/**
	 * Sorts the unordered array based on {@link Comparable} implementation via Bubble Sort
	 */
	public void bubbleSort(){
		int upperBound=this.size()-1;
		//Loop until upperBound is 1 (Can't compare just one item)
		while(upperBound>1){
			//Compare up to upperBound
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
	 * Sorts the unordered array based on {@link Comparable} implementation via Insertion Sort
	 */
	public void insertionSort(){
		//For each element except for the first one do the flowing
		//Don't do it for the first one as it is in the correct position among itself
		for(int out=1; out<this.size(); out++){
			//Save the "inserted" element to move the others
			final E temp=this.get(out);
			//Move the existing elements to make room and find where it fits
			int in=out;
			while(in>0&&this.get(in-1).compareTo(temp)>=0){
				this.set(in, this.get(--in));
			}
			//Set the last position to reference
			this.set(in, temp);
		}
	}

	/**
	 * Sorts the unordered array based on {@link Comparable} implementation via Odd Even Sort
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
	 * Sorts the unordered array based on {@link Comparable} implementation via Selection Sort
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
					//Update the minimum value to be correct
					minValue=inValue;
				}
			}
		}
	}/**
	 * Finds the median value, if it is even it will use the average function <br>
	 * <b>Partly sorts the array</b>
	 *
	 * @param  average
	 *                     A function that finds the average between two elements<br>
	 *                     Required as generics do not support math operators and not all objects can
	 *                     work with average.
	 *
	 * @return         The median value
	 */
	public E median(final BinaryOperator<E> average){
		final int mid=this.size()/2;	//Median index of the array
		final boolean even=this.size()%2==0;	//Is the array even in size
		//Pseudo-sort the array to get median in the right spot
		this.median(0, this.size()-1, mid, even);
		if(even){
			//If even return the average
			//Medians must be in the middle now
			return average.apply(this.get(mid-1), this.get(mid));
		}
		//We know the median is in the middle now
		return this.get(mid);
	}


	/**
	 * Internal median method
	 *
	 * @param left
	 *                  The left index to start at
	 * @param right
	 *                  The right index to stop at
	 * @param mid
	 *                  The middle of the array
	 * @param even
	 *                  Is the array even in size
	 */
	private void median(
		final int left, final int right, final int mid, final boolean even
		){
		final int x=right-left;	//Equal to size-1 (No +1 to reduce operators)
		if(x<=2){
			//If size <=3 simple sort
			this.manualSort(left, right);
			return;
		}
		//Partion the elements and return the partition's index
		final int partition=this.partition(left, right);
		if(even){
			//If even size handle differently
			if(left+1==right) return;//End of search
			//If partition is greater than the lower middle of the array search left
			if(partition>mid-1) this.median(left, partition-1, mid, even);	//Search left
			//If partition is less than the upper middle of the array search right
			else if(partition<mid+1) this.median(partition+1, right, mid, even);	//Search right
			else{
				//If it is on the boundary, must search both
				//Search both
				this.median(left, partition-1, mid, even);	//Search left
				this.median(partition+1, right, mid, even);	//Search right
			}
		}
		//odd
		if(partition==mid) return;		//Found it!
		if(partition>mid){
			//If partition is greater than the middle of the array search left
			this.median(left, partition-1, mid, even);	//Search left
			return;
		}
		//If partition is less than the middle of the array search right
		this.median(partition+1, right, mid, even);	//Search right
	}

	/**
	 * Finds the one or two median values and returns it as an array<br>
	 * <b>Partly sorts the array</b>
	 *
	 * @return The median value
	 */
	public Object[] medianBoth(){
		final int mid=this.size()/2;	//The median index of the array
		final boolean even=this.size()%2==0;	//Is the size even?
		//Pseudo-sort the array to get median in the right spot
		this.median(0, this.size()-1, mid, even);
		if(even){
			//If even create an object array of 2
			final Object[] arr=(Object[])Array.newInstance(Object.class, 2);
			arr[0]=this.get(mid-1);
			arr[1]=this.get(mid);
			return arr;
		}
		//If odd return an object array of 1
		final Object[] arr=(Object[])Array.newInstance(Object.class, 1);
		arr[0]=this.get(mid);
		return arr;
	}

	/**
	 * Returns the pseudo-median value (checks 3 values)
	 *
	 * @param  left
	 *                   The left index to start at
	 * @param  right
	 *                   The right index to stop at
	 * @return       The median value's index
	 */
	private int
	medianOfThree(final int left, final int right){
		final int mid=(right+left)/2;	//The middle of the section
		final boolean lgtr=this.get(left).compareTo(this.get(right))>0,
			lgtm=this.get(left).compareTo(this.get(mid))>0;
			if(lgtr){
				//l>r
				if(lgtm){
					//l>m
					//Mid must be right or mid
					return this.get(mid).compareTo(this.get(right))<0?right:mid;
				}
				//else
				//l<m
				//m>l>r
				return mid;
			}
			if(lgtm){
				//l>m
				//l<r
				//r>l>m
				return left;
			}
			//else
			//l<m
			//l<r
			//Must be mid or right

			return this.get(mid).compareTo(this.get(right))>0?right:mid;
	}

	/**
	 * Partitions part of the array
	 *
	 * @param  left
	 *                   The left index to start at
	 * @param  right
	 *                   The right index to stop at
	 * @return       The index of the pivot value
	 */
	public int
	partition(final int left, final int right){
		//Puts the median in the margin so it is not moved
		this.swap(this.medianOfThree(left, right), right);
		final E pivot=this.get(right);
		int lowerBound=left-1, upperBound=right;
		while(lowerBound<upperBound){
			//Don't bother swapping elements on the right side
			//While the pivot is greater than the element increment lowerBound
			while(
				lowerBound<upperBound&&
				pivot.compareTo(this.get(++lowerBound))>0
				);
			//While the pivot is less than or equal to the element decrement upperBound
			while(
				lowerBound<upperBound&&
				pivot.compareTo(this.get(--upperBound))<=0
				);
			//Swap the elements as they are out of order
			this.swap(lowerBound, upperBound);
		}
		//Restore pivot
		this.swap(lowerBound, right);

		return lowerBound;
	}

	/**
	 * Sorts the array via the {@code quicksort2} algorithm
	 */
	public void quickSort(){
		//Starts the quicksort
		this.quickSort(0, this.size()-1);
	}

	/**
	 * Internal quicksort method
	 *
	 * @param left
	 *                  The left index to start at
	 * @param right
	 *                  The right index to stop at
	 */
	private void quickSort(final int left, final int right){
		final int x=right-left;
		if(x<=2){
			//If size<=3 simple sort
			this.manualSort(left, right);
			return;
		}
		//Partition the section
		final int partition=this.partition(left, right);
		//Recurse right and left sections
		this.quickSort(left, partition-1);
		this.quickSort(partition+1, right);
	}

	/**
	 * Swaps the indexes
	 *
	 * @param index1
	 *                   The first index
	 * @param index2
	 *                   The second index
	 */
	private void swap(final int index1, final int index2){
		//Don't swap if they have the same index
		if(index1==index2) return;
		//Store arr[index1] in a temp variable
		final E temp=this.get(index1);
		//Override arr[index1] with arr[index2]
		this.set(index1, this.get(index2));
		//Override arr[index2] with temp
		this.set(index2, temp);
	}

	/**
	 * Swaps the indexes only if out of order
	 *
	 * @param lowerIndex
	 *                       The lower index
	 * @param upperIndex
	 *                       The upper index
	 */
	private void
	swapIf(final int lowerIndex, final int upperIndex){
		//Only swap if arr[lowerIndex]>arr[upperIndex]
		if(
			this.get(lowerIndex).compareTo(this.get(upperIndex))>0
			) this.swap(lowerIndex, upperIndex);
	}
}
