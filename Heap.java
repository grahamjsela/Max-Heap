import java.util.NoSuchElementException;
import java.util.Vector;

/*
*
* Graham Sela
* V00852324
* Assignment 5
*
*/

public class Heap<E extends Comparable<E>> {

	private Vector<E> heapArray;;

	public Heap() {
		heapArray = new Vector<E>();							//Uses the already defined vector class .setSize(int) to set the size of the array to 1.
	}

	public boolean isEmpty(){
		if (heapArray.isEmpty() == true) {				//Uses the already defined vector class .isEmpty() to test whether the 
			return true;								// vector array is empty or not.
		} else {
			return false;
		}
	}

	public int size(){
		return heapArray.size();						//Uses already defined vector class .size() to return the size fo the heap.
	}
	
	public void insert(E item){
		if (heapArray.capacity() == heapArray.size()) {				//If the vector array is full, it doubles the size as that is how many more elements
			int i = size() * 2;							// can be added to complete a full row.
			heapArray.setSize(i);
		}

		heapArray.add(item);
				
		int temp = size() - 1;
		bubbleUp(item, temp);

	}

	private void bubbleUp(E item,int temp) {			//Bubble up the bigger item to as high as it needs to be.
		int curr = (temp - 1) / 2;

		if (item.compareTo(heapArray.elementAt(curr)) <= 0 || curr < 0) { 		//Base case, in which the item is in the right position
			return;													//Ends the recursive sequence.
		} else {
			swap(temp, curr);
			bubbleUp(item, curr);
		}		

	}
	
	public E removeRootItem(){				//Swaps last and first elements, then deletes the last element.
		E temp = heapArray.elementAt(0);
		swap(0, size() - 1);
		heapArray.remove(heapArray.size() - 1);
		bubbleDown(heapArray.elementAt(0), 0);						//Calls the recursive method that reorders the heap.
		return temp;
	}

	private void bubbleDown(E item, int index) {			//Recursive method to continually move a smaller element down.
		if ((index + 1) * 2 > size() || item.compareTo(heapArray.elementAt(index)) > 0 ) {
			return;								//Base case in which, if children are not in array, and if the item is bigger than children
		} else {
			int i = biggerChild(index);			//Swaps child and parent with bigger of two children
			swap(index, i);					
			bubbleDown(item, i); 
		}
	}

	private int biggerChild(int index) {			//Returns which child is bigger given by indices.
		int leftChild = index * 2 + 1;
		int rightChild = index * 2 + 2;

		if (heapArray.elementAt(leftChild).compareTo(heapArray.elementAt(rightChild)) >= 0) {
			return leftChild;
		} else {
			return rightChild;
		}

	}
	
	public E getRootItem(){							//Returns first element in the heap
		return heapArray.firstElement();
	}
	
	private void swap(int i, int j) {				//Swaps the elements at given indices i,j.
		E temp = heapArray.elementAt(i);
		heapArray.set(i, heapArray.elementAt(j));
		heapArray.set(j, temp);
	}




	/******** Tool methods ********/
	private int indexOf(E p){
		for (int i = 1; i < heapArray.size(); i++) {
			if (heapArray.elementAt(i).equals(p))   {
				return i;
			}
		}
		return -1;
	}

	/******** Tool methods ********/
	private int getParenetindex(int child){
		return child/2;
	}

	/********  DEBUG USE methods ********/
	public void print_vector() {
		System.out.println(" *************** Array is ***************");
		for (int i = 0; i < heapArray.size(); i++){
			System.out.println(heapArray.elementAt(i));
		}
	}

	public static void main(String args []){
		
		//Simple Er_Patient test.
		Heap <ER_Patient> hp = new Heap <ER_Patient>();
		hp.insert(new ER_Patient ("Chronic"));	
		hp.insert(new ER_Patient ("Life-threatening"));
		hp.print_vector();	
		

		//Advanced Integer Test that tests all methods.
		Heap <Integer> hp1 = new Heap <Integer>();
		//Checks whether the vector is empty.
		System.out.println("Is the array empty? " + hp1.isEmpty());
		//Inserts ints.
		hp1.insert(1);
		//After an array has an item inserted, checks whether it is empty or not.
		System.out.println("Is the array empty? " + hp1.isEmpty());
		hp1.insert(5);
		hp1.insert(7);
		hp1.insert(2);
		hp1.insert(4);
		hp1.insert(8);
		//Before root item is removed, print the root item.
		System.out.println("The root of the vector is: " + hp1.getRootItem());
		hp1.print_vector();
		//Prints the size of the vector.
		System.out.println("The size of the array is: " + hp1.size());
		//Removes the root.
		hp1.removeRootItem();
		//After the root item is removed, print the root item.
		System.out.println("The root of the vector is: " + hp1.getRootItem());
		hp1.print_vector();
		//Prints new size after root has been removed.
		System.out.println("The size of the array is: " + hp1.size());


		//Advanced Er_Patient test that tests all methods.
		Heap <ER_Patient> hp2 = new Heap <ER_Patient>();
		//Checks whether the vector is empty.
		System.out.println("Is the array empty? " + hp2.isEmpty());
		//Inserts Patients.
		hp2.insert(new ER_Patient ("Life-threatening"));
		//After an array has an item inserted, checks whether it is empty or not.
		System.out.println("Is the array empty? " + hp2.isEmpty());
		hp2.insert(new ER_Patient ("Chronic"));
		hp2.insert(new ER_Patient ("Walk-in"));
		hp2.insert(new ER_Patient ("Major fracture"));
		hp2.insert(new ER_Patient ("Life-threatening"));
		hp2.insert(new ER_Patient ("Life-threatening"));
		hp2.insert(new ER_Patient ("Chronic"));
		hp2.insert(new ER_Patient ("Walk-in"));
		hp2.insert(new ER_Patient ("Major fracture"));
		//Before root item is removed, print the root item.
		System.out.println("The root of the vector is: " + hp2.getRootItem());
		hp2.print_vector();
		//Prints the size of the vector.
		System.out.println("The size of the array is: " + hp2.size());
		//Removes the root.
		hp2.removeRootItem();
		//After the root item is removed, print the root item.
		System.out.println("The root of the vector is: " + hp2.getRootItem());
		hp2.print_vector();
		//Prints new size after root has been removed.
		System.out.println("The size of the array is: " + hp2.size());
	}
}
