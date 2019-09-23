package unorderedArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Unordered Partly filled Array, with no sorting functionality {@link UnorderedArrayCompare} for
 * sorting, requires <code>Comparable&lt;E&gt;</code> interface to be implemented
 *
 * @see       #delete(Object)
 *
 * @param <E>
 *                The element type
 */

public class UnorderedArray <E>{
	/**
	 * Array reference
	 */
	private final Object[] arr;	//Uses Object[] as E[] does not work
	//And Array.newInstance(class) requires a value to get class and may not be the right class

	/**
	 * The current size of the array
	 */
	private int size;


	/**
	 * Crates an empty unordered array
	 *
	 * @param max
	 *                The maximum size of the array
	 */
	public UnorderedArray(final int max){
		this.arr=new Object[max];//Init the array
		this.size=0;	//Empty array
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
	public UnorderedArray(final int max, final E... collection){
		this.arr=Arrays.copyOf(collection, max, Object[].class);
		this.size=collection.length;
	}

	/**
	 * Removes the given value and maintains order
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
	 * Removes all values retaining order
	 *
	 * @param  values
	 *                    The values to remove
	 * @return        The unordered removed values
	 *
	 *                <pre>
	 * <code>
	 * 1, 2, !, 4, 5, !, 8, 12, 13, 21, !, !, 45, 50
	 * 1, 2, 4, -, 5, !, 8, 12, 13, 21, !, !, 45, 50
	 * 1, 2, 4, 5, 8, 12, -, -, 13, 21, !, !, 45, 50
	 * 1, 2, 4, 5, 8, 12, 13, -, -, 21, !, !, 45, 50
	 * 1, 2, 4, 5, 8, 12, 13, 21, -, -, !, !, 45, 50
	 * 1, 2, 4, 5, 8, 12, 13, 21, 45, -, !, !, -, 50
	 * 1, 2, 4, 5, 8, 12, 13, 21, 45, 50, !, !, -, -
	 * </code>
	 *                </pre>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<E> deleteAll(final E... values){
		final ArrayList<Integer> indexes=this.findFlat(values);
		final ArrayList<E> removed=new ArrayList<>(indexes.size());
		//Put null into removed values and add to the removed list
		for(final int i : indexes){
			removed.add(this.get(i));
			this.arr[i]=null;
		}
		//Collapse the gaps logical, not one space at a time
		//Collapse skipping over large gaps of nulls
		int gap=0;
		for(int i=0; i<this.size-gap; i++){
			//Find how many more nulls there are
			while(this.arr[i+gap]==null){
				gap++;
			}
			//Move the (i+gap)th element to the ith position
			this.arr[i]=this.arr[i+gap];
		}
		//May want to remove pointless references from this.size-gap-1 to this.size-1
		this.size-=gap;
		return removed;
	}

	/**
	 * Removes all indicated values and jumbles the order
	 *
	 * @param  values
	 *                    The values to remove
	 * @return        The removed values in a random order, does not correlate as elements are removed
	 *                at the end and the middle
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<E> deleteAllQuick(final E... values){
		return this.deleteIndexQuick(this.findFlat(values));
	}


	/**
	 * Removes all instances of the indicated value and jumbles the order
	 *
	 * @param  value
	 *                   The values to remove
	 * @return       The removed values in a random order, does not correlate as elements are removed at
	 *               the end and the middle
	 */
	public ArrayList<E> deleteAllQuick(final E value){
		return this.deleteIndexQuick(this.findAll(value));
	}

	/**
	 * Removes the given index and retains the order
	 *
	 * @param  index
	 *                   The index to remove
	 * @return       A boolean indicating success
	 */
	public boolean deleteIndex(int index){
		if(index<this.size){
			while(index<this.size) this.arr[index]=this.arr[++index];
			this.arr[this.size--]=null;
			return true;
		}
		return false;
	}

	/**
	 * Removes the given index and jumbles the order
	 *
	 * @param  index
	 *                   The indexes to remove
	 * @return       An ArrayList of removed elements
	 */
	private ArrayList<E> deleteIndexQuick(
		final ArrayList<Integer> indexes
		){
		final ArrayList<E> removed=new ArrayList<>(indexes.size());
		for(final int i : indexes){
			//Slice off the end to prevent moving a to be removed value
			while(indexes.contains(this.size)){
				removed.add(this.get(this.size));
				this.arr[this.size--]=null;
			}
			//If i is not at the end of the array replace it with the last element
			if(i!=this.size){
				this.arr[i]=this.arr[this.size];
				this.arr[this.size--]=null;
			}
		}
		return removed;
	}

	/**
	 * Removes the given index and jumbles the order
	 *
	 * @param  index
	 *                   The index to remove
	 * @return       A boolean indicating success
	 */
	public boolean deleteIndexQuick(final int index){
		if(index<this.size){
			this.arr[index]=this.arr[this.size];
			this.arr[this.size--]=null;
			return true;
		}
		return false;
	}


	/**
	 * Removes the first given value and jumbles the order
	 *
	 * @param  value
	 *                   The value to remove
	 * @return       A boolean indicating success
	 */
	public boolean deleteQuick(final E value){
		final int i=this.find(value);
		if(i!=-1){
			this.arr[i]=this.arr[this.size--];
			return true;
		}
		return false;
	}

	/**
	 * Prints the array to the console
	 */
	public void display(){
		System.out.println(this);
	}

	/**
	 * Does the values exist in the array?
	 *
	 * @param  collection
	 *                        The values to find
	 * @return            A list of found values
	 */
	@SuppressWarnings("unchecked")
	public UnorderedArray<E> exists(final E... collection){
		final UnorderedArray<E> existance
		=new UnorderedArray<>(collection.length);
		final UnorderedArray<E> targets
		=new UnorderedArray<>(collection.length, collection);

		for(int i=0; i<this.size&&targets.size!=0; i++){
			for(int j=0; j<targets.size; j++){
				final E target=targets.get(j);
				if(target.equals(this.arr[i])){
					targets.deleteIndexQuick(j);
					existance.insert(target);
				}
			}
		}
		return existance;
	}

	/**
	 * Does the value exist in the array?
	 *
	 * @param  target
	 *                    The value to find
	 * @return        A boolean of weather it is found
	 */
	public boolean exists(final E target){
		for(int i=0; i<this.size; i++){
			if(target.equals(this.arr[i])) return true;
		}
		return false;
	}


	/**
	 * Find the given value
	 *
	 * @param  target
	 *                    The vale to look for
	 * @return        The index of the value, -1 if not found
	 */
	public int find(final E target){
		for(int j=0; j<this.size; j++) if(target.equals(this.arr[j])) return j;
		return -1;
	}

	/**
	 * Finds the indexes of the given elements
	 *
	 * @param  targets
	 *                     A list of targets to find
	 * @return         A UnorderedArray of ArrayList of Integers of the found values
	 */
	@SuppressWarnings("unchecked")
	public UnorderedArray<ArrayList<Integer>> find(final E... targets){
		final UnorderedArray<ArrayList<Integer>> index
		=new UnorderedArray<>(targets.length);

		this.forEach(el->{
			return new ArrayList<>();
		});
		for(int i=0; i<this.size; i++){
			for(int j=0; j<targets.length; j++){
				if(targets[j].equals(this.arr[i])){
					index.get(j).add(i);
				}
			}
		}
		return index;
	}

	/**
	 * Find the given value
	 *
	 * @param  target
	 *                    The vale to look for
	 * @return        An ArrayList of found indexes, empty if not found
	 */
	public ArrayList<Integer> findAll(final E target){
		final ArrayList<Integer> indexes=new ArrayList<>();
		for(int j=0; j<this.size; j++){
			if(target.equals(this.arr[j])) indexes.add(j);
		}
		return indexes;
	}


	/**
	 * Finds the indexes of the given elements
	 *
	 * @param  targets
	 *                     A list of targets to find
	 * @return         A UnorderedArray of ArrayList of Integers of the index values
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> findFlat(final E... targets){
		final ArrayList<Integer> index=new ArrayList<>(targets.length*3);
		for(int i=0; i<this.size; i++){
			for(int j=0; j<targets.length; j++){
				if(targets[j].equals(this.arr[i])){
					index.add(i);
				}
			}
		}
		return index;
	}

	/**
	 * For each element do the flowing
	 *
	 * @param consumer
	 *                     the lambda to execute on each value
	 */
	public void forEach(final Consumer<E> consumer){
		for(int i=0; i<this.size; i++){
			consumer.accept(this.get(i));
		}
	}


	/**
	 * For each element do the flowing
	 *
	 * @param function
	 *                     the lambda to execute on each value
	 */
	public void forEach(final Function<E, Object> function){
		for(int i=0; i<this.size; i++){
			this.arr[i]=function.apply(this.get(i));
		}
	}

	/**
	 * @param  index
	 *                   The value to get
	 * @return       The boxed element at index
	 */
	@SuppressWarnings("unchecked")
	public E get(final int index){
		return (E)this.arr[index];
	}

	/**
	 * Insert a collection of elements
	 *
	 * @param  elements
	 *                      The elements to add
	 * @return          The amount of elements inserted
	 */
	@SuppressWarnings("unchecked")
	public int insert(final E... elements){
		int i=0;
		while(i<elements.length){
			if(this.size==this.arr.length) return i;
			this.arr[this.size++]=elements[i++];
		}
		return i;
	}

	/**
	 * Insert value into array
	 *
	 * @param value
	 *                  The value to insert
	 */
	public void insert(final E value){
		this.arr[this.size++]=value;
	}

	/**
	 * Sets the value at i
	 *
	 * @param i
	 *                  The index to change
	 * @param value
	 *                  The value to change to
	 */
	public void set(final int i, final E value){
		this.arr[i]=value;
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
			builder.append(this.arr[j]).append(", ");
		builder.append(this.arr[this.size-1]);//Don't append an extra ", "
		builder.append("]");
		return builder.toString();
	}
}
