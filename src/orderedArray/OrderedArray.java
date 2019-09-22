package orderedArray;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.BinaryOperator;

/**
 * Partly Filled Ordered Array, must implement comparable&lt;Type&gt;
 *
 * @param <E>
 *                The type of the array
 * @see       Comparable
 * @see       #merge(OrderedArray, OrderedArray)
 * @see       #mergeClone(OrderedArray, OrderedArray)
 */
public class OrderedArray <E extends Comparable<E>>{
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
		//Static methods still have access to private feilds of the same class, even if you don't use this
		final OrderedArray<T> destArr
		=new OrderedArray<>(arr1.arr.length+arr2.arr.length);
		int i=0, j=0, k=0;
		while(i<arr1.size&&j<arr2.size){
			//Find the lowest value in arr1 and arr2 (Already sorted)
			if(arr1.get(i).compareTo(arr2.get(j))<0){
				destArr.arr[k++]=arr1.get(i++);//If arr1 is smaller put arr1[i] in to dest
			}
			else{
				destArr.arr[k++]=arr1.get(j++);//Otherwise put arr2[j] into dest
			}
		}
		//If arr1 has more
		if(i<arr1.size){
			//Copy data from arr1 to dest
			while(i<arr1.size){
				destArr.arr[k++]=arr1.get(i++);
			}
		}
		//If arr2 has more
		else if(j<arr2.size){
			//Copy data from arr2 to dest
			while(j<arr1.size){
				destArr.arr[k++]=arr1.get(j++);
			}
		}
		return destArr;
	}

	/**
	 * Merges two arrays together and returns a <u>new</u> OrderedArray as well as new copyies of the
	 * object according to the copy constructior of the object
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
		while(i<arr1.size&&j<arr2.size){
			//Find the lowest value in arr1 and arr2 (Already sorted)
			final T I=arr1.get(i), J=arr2.get(j);
			if(I.compareTo(J)<0){
				destArr.arr[k++]
					=OrderedArray.clone(I);//If arr1 is smaller put arr1[i] in to dest
				i++;
			}
			else{
				destArr.arr[k++]
					=OrderedArray.clone(J);//Otherwise put arr2[j] into dest
				j++;
			}
		}
		//If arr1 has more
		if(i<arr1.size){
			//Copy data from arr1 to dest
			while(i<arr1.size){
				destArr.arr[k++]
					=OrderedArray.clone(arr1.get(i));//If arr1 is smaller put arr1[i] in to dest
				i++;
			}
		}
		//If arr2 has more
		else if(j<arr2.size){
			//Copy data from arr2 to dest
			while(j<arr1.size){
				destArr.arr[k++]
					=OrderedArray.clone(arr1.get(j));//Otherwise put arr2[j] into dest
				j++;
			}
		}
		return destArr;
	}

	/**
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
	 * Reference to array
	 */
	private final Object[] arr;


	/**
	 * Current size of array
	 */
	private int size;


	/**
	 * @param max
	 *                The capacity of the array
	 */
	public OrderedArray(final int max){
		//Crate a generic supertype to hold the generic type
		this.arr=new Object[max];
		this.size=0;
	}

	/**
	 * @param max
	 *                       The capacity of the array
	 * @param collection
	 *                       The values to add
	 */
	@SafeVarargs
	public OrderedArray(final int max, final E... collection){
		//Copy the given array into this.arr and convert to supertype Object[]
		this.arr=Arrays.copyOf(collection, max, Object[].class);
		this.size=collection.length;
		this.oddEvenSort();
	}

	/**
	 * Copies the OrderedArray
	 *
	 * @param  orderedArray
	 *                                       The OrderedArray to copy
	 * @param  flag
	 *                                       use true to use copy constructor
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("javadoc")
	public OrderedArray(
		final OrderedArray<E> orderedArray, final Boolean... flag
		) throws NoSuchMethodException, InstantiationException,
	IllegalAccessException, InvocationTargetException{
		if(flag.length>0&&flag[0]){
			this.arr=new Object[orderedArray.arr.length];
			for(int i=0; i<orderedArray.size; i++){
				//Don't exactly know if this is correct
				//Dynamicaly access the copy constructor to copy object
				final Class<?> e=orderedArray.get(i).getClass();
				this.arr[i]
					=e.getConstructor(e).newInstance(orderedArray.get(i));
			}
			this.size=orderedArray.size;
		}
		else{
			this.arr=new Object[orderedArray.arr.length];
			for(int i=0; i<orderedArray.size; i++){
				this.arr[i]=orderedArray.arr[i];
			}
			this.size=orderedArray.size;
		}
	}

	/**
	 * Copies the OrderedArray
	 *
	 * @param orderedArray
	 *                         The OrderedArray to copy
	 * @param capacity
	 *                         The new capacity of the array
	 */
	public OrderedArray(final OrderedArray<E> orderedArray, final int capacity){
		this.arr=new Object[capacity];
		for(int i=0; i<orderedArray.size&&i<capacity; i++){
			this.arr[i]=orderedArray.arr[i];
		}
		this.size=orderedArray.size;
	}

	/**
	 * @return The capacity of the array
	 */
	public int capacity(){
		return this.arr.length;
	}

	/**
	 * Removes the given value
	 *
	 * @param  value
	 *                   The value to remove
	 * @return       A boolean indicating success
	 */
	public boolean delete(final E value){
		int i=this.find(value);
		if(i!=-1){
			while(i<this.size) this.arr[i]=this.arr[++i];
			this.size--;
			return true;
		}
		return false;
	}

	/**
	 * Removes the given index
	 *
	 * @param  index
	 *                   The index to remove
	 * @return       A boolean indicating success
	 */
	public boolean deleteIndex(int index){
		if(0<=index&&index<this.size){
			while(index<this.size) this.arr[index]=this.arr[++index];
			this.size--;
			return true;
		}
		return false;
	}

	/**
	 * Prints the array to the console
	 */
	public void display(){
		System.out.println(this.toString());
	}

	/**
	 * Finds the given value
	 *
	 * @param  value
	 *                   The value to find
	 * @return       the index of the value
	 */
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
	 * @param  index
	 *                   The value to get
	 * @return       The boxed element at index
	 */
	@SuppressWarnings("unchecked")
	public E get(final int index){
		//Does not need to be cheked as it must be E or a subtype of E
		//Only way to violate this is within this class
		return (E)this.arr[index];
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
		//Inserrt the value
		this.arr[index]=value;
		this.size++;
		//Return suggess
		return true;
	}


	/**
	 * Adds all given values in a more effecent way than calling {@link #insert(Comparable)}
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
	@SuppressWarnings("unchecked")
	public int insert(final E... values){
		final int[] between=new int[this.size+1],	//The array to hold the amout of items inbetween
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
			//Add each previous number to get the sum of all the prevous numbers
			between[i]+=between[i-1];
		}

		//Move the numbers
		for(int i=this.size-1; i>=0; i--){
			//Shift each number the appropreate amount staring at the end
			//This way each number only needs to be moved once
			this.arr[i+between[i]]=this.arr[i];
		}
		//Required to keep track of the sift ammount (Otherwise needs more operations to find null spots)
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
	public float medianIndex(){
		//If even
		if(this.size%2==0) return this.size/2+.5f;
		//If odd
		return this.size/2;
	}

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
				for(int i=s; i<this.size-1; i+=2){
					//If out of order
					if(this.get(i).compareTo(this.get(i+1))>0){
						//Set done to false
						done=false;
						//Swap positions with temp variable
						//Java does not support deconstruction assignment
						//This would allow us to not use a variable
						final Object obj=this.arr[i];
						this.arr[i]=this.arr[i+1];
						this.arr[i+1]=obj;
					}
				}
			}
		}
	}

	/**
	 * @return The current size of the array
	 */
	public int size(){
		return this.size;
	}

	/**
	 * Formats the array to a string
	 */
	@Override
	public String toString(){
		final StringBuilder builder=new StringBuilder();
		builder.append("[");
		//Loop for each element and append it to the builder
		for(int j=0; j<this.size-1; j++)
			builder.append(this.get(j)).append(", ");
		builder.append(this.get(this.size-1));//Don't append an extra ", "
		builder.append("]");
		return builder.toString();
	}
}
