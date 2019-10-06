package array;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.BinaryOperator;

/**
 * OrderedArray class<br>
 *
 * Implements a complex {@link OrderedArray} that provides sorting, merging, insertion,
 * multi-insertion, deletion, multi-deletion, searching, median values, printing, and cloning all
 * with generics. <br>
 * <br>
 * Code has been revised to be generic and adds many methods, see first method for quick grading
 * {@link #merge(OrderedArray, OrderedArray)} (For shallow copy not reference copy) and
 * {@link #mergeClone(OrderedArray, OrderedArray)} (For deep copy)
 *
 * Partly Filled Ordered Array, must implement {@code Comparable<Type>}
 *
 * @author     Shawn Graven (Zekrom)
 * @date       9/23/19
 *
 * @param  <E>
 *                 The type of the array
 * @see        Comparable
 * @see        #merge(OrderedArray, OrderedArray)
 * @see        #mergeClone(OrderedArray, OrderedArray)
 */
public class OrderedArray <E extends Comparable<E>> extends UnorderedArrayCompare{	//<|E| extends |supperclass|&|interface|&|interface|...>
	/**
	 * Clones an object according to the copy constructor of the object<br>
	 * <b>UNTESTED</b>
	 *
	 * @param  <T>
	 * @param  obj
	 * @return                           A new instance of the object
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>> T clone(final T obj)
		throws InstantiationException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException{
		return (T)obj.getClass().getConstructor(obj.getClass())
			.newInstance(obj);
	}

	/**
	 * Merges two arrays together and returns a <u>new</u> OrderedArray
	 *
	 * @param  <T>
	 *                  The type of the arrays, must implement comparable
	 * @param  arr1
	 *                  An OrderedArray to merge
	 * @param  arr2
	 *                  Another OrderedArray to merge
	 * @return      A merged OrderedArray
	 */
	public static <T extends Comparable<T>> OrderedArray<T>
	merge(final OrderedArray<T> arr1, final OrderedArray<T> arr2){
		//Static methods still have access to private fields of the same class, even if you don't use this
		final OrderedArray<T> destArr
		=new OrderedArray<>(arr1.capacity()+arr2.capacity());
		int i=0, j=0, k=0;
		while(i<arr1.size&&j<arr2.size){
			//Find the lowest value in arr1 and arr2 (Already sorted)
			if(arr1.get(i).compareTo(arr2.get(j))<0){
				destArr.set(k++, arr1.get(i++));//If arr1 is smaller put arr1[i] in to dest
			}
			else{
				destArr.set(k++, arr2.get(j++));//Otherwise put arr2[j] into dest
			}
		}
		//If arr1 has more
		if(i<arr1.size()){
			//Copy data from arr1 to dest
			while(i<arr1.size()){
				destArr.set(k++, arr1.get(i++));
			}
		}
		//If arr2 has more
		else if(j<arr2.size){
			//Copy data from arr2 to dest
			while(j<arr1.size){
				destArr.set(k++, arr2.get(j++));
			}
		}
		//Update destArray Size
		destArr.setSize(arr1.size()+arr2.size());
		return destArr;
	}

	/**
	 * Merges two arrays together and returns a <u>new</u> OrderedArray as well as new copies of the
	 * object according to the copy constructor of the object.<br>
	 * <br>
	 * Must implement the copy constructor, not available with {@code <? extends Number>}
	 *
	 * @param  <T>
	 *                                       The type of the arrays, must implement comparable
	 * @param  arr1
	 *                                       An OrderedArray to merge
	 * @param  arr2
	 *                                       Another OrderedArray to merge
	 * @return                           A merged OrderedArray
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("javadoc")
	public static <T extends Comparable<T>> OrderedArray<T>
	mergeClone(final OrderedArray<T> arr1, final OrderedArray<T> arr2) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		final OrderedArray<T> destArr
		=new OrderedArray<>(arr1.arr.length+arr2.arr.length);
		int i=0, j=0, k=0;
		while(i<arr1.size()&&j<arr2.size()){
			//Find the lowest value in arr1 and arr2 (Already sorted)
			final T I=arr1.get(i), J=arr2.get(j);
			if(I.compareTo(J)<0){
				destArr.set(k++, OrderedArray.clone(I));//If arr1 is smaller put arr1[i] in to dest
				i++;
			}
			else{
				destArr.set(k++, OrderedArray.clone(J));//Otherwise put arr2[j] into dest
				j++;
			}
		}
		//If arr1 has more
		if(i<arr1.size()){
			//Copy data from arr1 to dest
			while(i<arr1.size()){
				destArr.set(k++, OrderedArray.clone(arr1.get(i)));//If arr1 is smaller put arr1[i] in to dest
				i++;
			}
		}
		//If arr2 has more
		else if(j<arr2.size){
			//Copy data from arr2 to dest
			while(j<arr1.size){
				destArr.set(k++, OrderedArray.clone(arr2.get(j)));//Otherwise put arr2[j] into dest
				j++;
			}
		}
		//Update destArray Size
		destArr.setSize(arr1.size()+arr2.size());
		return destArr;
	}

	/**
	 * @param max
	 *                The capacity of the array
	 */
	public OrderedArray(final int max){
		//Crate a generic supertype to hold the generic type
		super(max);
	}

	/**
	 * @param max
	 *                       The capacity of the array
	 * @param collection
	 *                       The values to add
	 */
	@SafeVarargs
	public OrderedArray(final int max, final E... collection){
		super(max, collection);
		this.quickSort();
	}

	public int binSearch(final E target){
		return this.binSearch(target, 0, this.size()-1);
	}

	private int binSearch(final E target, final int lowerBound, final int upperBound){
		if(lowerBound>upperBound)return -1;
		final int index=(upperBound+lowerBound)/2;
		final int comp=target.compareTo(this.get(index));
		if(comp==0)return index;
		if(comp>0)return this.binSearch(target, index+1, upperBound);
		return this.binSearch(target, lowerBound, index-1);
	}

	/**
	 * Finds the given value
	 *
	 * @param  value
	 *                   The value to find
	 * @return       the index of the value
	 */
	@Override
	public int find(final E value){
		int lowerBound=0, upperBound=this.size-1, index;

		while(lowerBound<=upperBound){
			index=(lowerBound+upperBound)/2;
			if(value.compareTo(this.get(index))==0) return index;	//Found target
			if(value.compareTo(this.get(index))>0) lowerBound=index+1;	// it's in upper half
			else upperBound=index-1;	// it's in lower half
		}
		return -1;
	}
	
	/**
	 * Inserts the given value
	 *
	 * @param  value
	 *                   The value to insert
	 *
	 *                   <pre>
	 * <code>
	 * 40
	 * 1, 2, 4, 6, 7,  9 , 12,  34 ,  65 , 90
	 * 1, 2, 4, 6, 7, [9], 12,  34 ,  65 , 90
	 * 1, 2, 4, 6, 7, [9], 12,  34 ,  65 , 90
	 * 1, 2, 4, 6, 7, 9->, 12, [34],  65 , 90
	 * 1, 2, 4, 6, 7, 9->, 12, 34->, [65], 90
	 * 1, 2, 4, 6, 7, 9->, 12, 34->, <-65, 90
	 *
	 * 5
	 * 1, 2,  4 ,  6 , 7,  9 , 12, 34, 65, 90
	 * 1, 2,  4 ,  6 , 7, [9], 12, 34, 65, 90
	 * 1, 2, [4],  6 , 7, <-9, 12, 34, 65, 90
	 * 1, 2, 4->, [6], 7, <-9, 12, 34, 65, 90
	 * 1, 2, 4->, <-6, 7, <-9, 12, 34, 65, 90
	 *
	 * 0
	 *  1 , 2,  4 ,  6 , 7,  9 , 12, 34, 65, 90
	 *  1 , 2,  4 ,  6 , 7, [9], 12, 34, 65, 90
	 *  1 , 2, [4],  6 , 7, <-9, 12, 34, 65, 90
	 *  1 , 2, <-4, [6], 7, <-9, 12, 34, 65, 90
	 * [1], 2, <-4, [6], 7, <-9, 12, 34, 65, 90
	 * <-1, 2, <-4, [6], 7, <-9, 12, 34, 65, 90
	 * </code>
	 *                   </pre>
	 *
	 * @return       A boolean indicating success
	 */
	@Override
	public boolean insert(final E value){
		//Can the item fit?
		if(this.size==this.arr.length) return false;
		//Search for insertion position with psudo-binary search
		int lowerBound=0, upperBound=this.size-1, index=0;
		while(lowerBound<=upperBound){
			index=(lowerBound+upperBound)/2;
			if(value.compareTo(this.get(index))>0) lowerBound=++index;	// it's in upper half
			else upperBound=index-1;	// it's in lower half
		}
		//Move all elements after the position to make space
		for(int i=this.size-1; i>index-1; i--){
			this.arr[i+1]=this.arr[i];
		}
		//Insert the value
		this.arr[index]=value;
		this.size++;
		//Return success
		return true;
	}

	/**
	 * Adds all given values in a more efficient way than calling {@link #insert(Comparable)}
	 *
	 * @param  values
	 *                    The values to add
	 * @return        The amount of values added
	 *
	 *                <pre>
	 * Elements
	 * 1, 4, 5, 9, 13, 32, 54, 89, 190, 300, 321, 542, 790, 900, 1002
	 * Insert
	 * 1, 2, 3, 6, 9, 58, 300, 403, 500, 700, 1000, 1400, 1545
	 * Create
	 * 1,
	 * - 1, 2, 3,
	 * 4, 5,
	 * - 6,
	 * 9,
	 * - 9,
	 * 13, 32, 54,
	 * - 58,
	 * 89, 190, 300,
	 * - 300,
	 * 321,
	 * - 403, 500,
	 * 542,
	 * - 700,
	 * 790,
	 * - 1000,
	 * 1002,
	 * - 1400, 1545
	 *
	 * Process
	 * 1, 4, 5, 9, 13, 32, 54, 89, 190, 300, 321, 542, 790, 900, 1002
	 *0 3   0  1  1   0   0   1   0    0    1    2    1    1    1     2
	 * Obtain the above numbers by binary simi-insertion
	 * Save the insertion index to an array
	 * Move each number the sum of the "numbers between" that is to the left of it
	 *
	 *                </pre>
	 *
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int insert(final E... values){
		final int[] between=new int[this.size+1],	//The array to hold the amount of items in between
			position=new int[values.length];	//The array to hold the determined pre-positions of the values
		for(int v=0; v<values.length; v++){
			final E value=values[v];
			//Find where values[v] goes via binary simi-insertion
			int lowerBound=0, upperBound=this.size-1, index=0;
			while(lowerBound<=upperBound){
				index=(lowerBound+upperBound)/2;
				if(value.compareTo(this.get(index))>0){
					lowerBound=index+1;	// it's in upper half
				}
				else{
					upperBound=index-1;	// it's in lower half
				}
			}
			//If the item should be put at the start
			if(index==0&&values[v].compareTo(this.get(index))<0){
				position[v]=0;
				between[0]++;
			}
			//Otherwise
			else{
				position[v]=index;
				between[index+1]++;
			}
		}

		//Get the distance to move each number
		for(int i=1; i<between.length; i++){
			//Add each previous number to get the sum of all the previous numbers
			between[i]+=between[i-1];
		}

		//Move the numbers
		for(int i=this.size-1; i>=0; i--){
			//Shift each number the appropriate amount staring at the end
			//This way each number only needs to be moved once
			this.arr[i+between[i]]=this.arr[i];
		}
		//Required to keep track of the sift amount (Otherwise needs more operations to find null spots)
		final int[] shift=new int[this.size];

		//Fill the spots and use binary insertion sorting
		for(int i=0; i<position.length; i++){
			int lowerBound=position[i]+						//Where it would be before shift
				between[position[i]],						//Where it is after shift
				upperBound=lowerBound+shift[position[i]],	//Shift if more than one fits in hole
				index=lowerBound;							//Current index
			final int top=upperBound;
			shift[position[i]]++;
			//Find the right hole
			while(lowerBound<=upperBound){
				index=(lowerBound+upperBound)/2;
				if(values[i].compareTo(this.get(index))>0){
					lowerBound=++index;	// it's in upper half
				}
				else{
					upperBound=index-1;	// it's in lower half
				}
			}
			for(int j=top; j>index-1; j--){
				this.arr[j+1]=this.arr[j];
			}
			this.arr[index]=values[i];
		}
		//Increment size
		this.size+=values.length;

		return 0;
	}

	/**
	 * Finds the one or two median values
	 *
	 * @param  average
	 *                     A function that finds the average between two elements<br>
	 *                     Required as generics do not support math operators and not all objects can
	 *                     work with average.
	 *
	 * @return         The median value
	 */
	@Override
	public E median(final BinaryOperator<E> average){
		final int at=this.size/2;
		//If even
		if(this.size%2==0){
			return average.apply(this.get(at), this.get(at+1));
		}
		//If odd
		return this.get(at);
	}

	/**
	 * @return The median of the OrderedArray<br>
	 *         Returns n.5 if the size is even
	 */
	@Override
	public float medianIndex(){
		//If even
		if(this.size%2==0) return this.size/2+.5f;
		//If odd
		return this.size/2;
	}

	/*
	private void mergeSort(final int start, final int end){
		if(start==end)return;
		final int middel=(start+end)/2;
		this.mergeSort(start, middel);
		this.mergeSort(middel+1, end);
		//Sort
		final Object[] arr1=Arrays.copyOfRange(this.arr, start, middel+1),
			arr2=Arrays.copyOfRange(this.arr, middel, end);
		
		for(int i=start; i<=end; i++){
			
		}
	}
	*/

	/**
	 * Sorts the array according to compare
	 */
	public void oddEvenSort(){
		boolean done=false;
		while(!done){
			done=true;
			//Even index for s=0 and odd index for s=1
			for(int s=0; s<2; s++){
				//Loop through the even or odd indexes of the array
				for(int i=s; i<this.size()-1; i+=2){
					//If out of order
					if(this.get(i).compareTo(this.get(i+1))>0){
						//Set done to false
						done=false;
						//Swap positions with temp variable
						//Java does not support deconstruction assignment
						//This would allow us to not use a variable
						final Object obj=this.get(i);
						this.set(i, this.get(i+1));
						this.set(i+1, obj);
					}
				}
			}
		}
	}
}
