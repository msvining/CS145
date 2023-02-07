//class for the linked list

public class List<T> { //the linked list class <T> for type used

	private T value; //create a variable for the value
	private List<T> next; //create a variable for the next item

	public List() { //constructor method
		
		value = null; //set both to null
		next = null;

	}

	public List<T> GetLast() { //method to get the last item in the list
		
		List<T> current = this; //start at the current index
		while (true) { //loop until done
			
			if (current.next == null) {
				
				break; //if there is no next value, end the loop
				
			}
			current = current.next; //move to the next value in the list
			
		}
		
		return current; //return the last value
		
	}
	
	public T GetValue(int index) { // method to get the value of an index
		
		List<T> item = Get(index); //get the item at the index
		return item.value; //return the value of the item
		
	}
	
	public List<T> Get(int index) { //method to get an item
		
		List<T> current = this.next; //set the current value to the next 
		for (int i = 0; i < index; i++) { //repeat until at the correct item
			
			if (current.next == null) {
				
				break; //if the next value is null, we are at the end
				
			}
			current = current.next; //move to the next value
			
		}
		return current; //return the final value
		
	}
	
	public void Append(T value) { //method to add to the end
		
		List<T> nextVal = new List<T>(); //create a new list item
		nextVal.value = value; //set the value to the one input
		List<T> lastVal = GetLast(); //get the last value of the list
		
		lastVal.next = nextVal; //set the next value of the end to the new item
		
	}
	
	public void Set(T value, int index) { //method to set the value of an item
		
		List<T> toSet = Get(index); //get the list item at the index
		toSet.value = value; //set the value of that item
		
	}
	
	public void Insert(T value, int index) { //method to insert a value at an index
		
		if (index == 0) { //if we are adding to the beginning, just use prepend
			
			Prepend(value); 
			
		} else {
			
			List<T> insertValue = Get(index); //get the list item at the index
			List<T> backValue = Get(index - 1); //get the list item before the index
			List<T> newValue = new List<T>(); //create a new list item
			newValue.value = value; //set the value of the item
	
			backValue.next = newValue; //set the previous value's next to the new value
			newValue.next = insertValue; //set the new value's next to the old value
			
		}
		
	}
	
	public void Remove(int index) { //method to remove an item
		
		if (index == 0) { //if the index is 0
			
			if (this.next != null) { //and there is a value to remove
				this.next = this.next.next; //set the next value to the second next value
			}
			
		} else {
			
			List<T> remValue = Get(index); //get the value to be removed
			List<T> backValue = Get(index - 1); //get the value before
			
			backValue.next = remValue.next; //set the next of the value before to the value ahead
			
		}
		
	}
	
	public void Prepend(T value) { //method to add to the beginning
		
		if (next == null) { //if there are no values, use append
			
			Append(value);
			
		} else {
		
			List<T> current = new List<T>(); //create a new item
			current.value = next.value; //set the item's value to the value of next
			current.next = next.next; //set the item's next to the next next
		
			next.next = current; //set the next next value to the new value
			next.value = value; //set the next value to the input value
		
		}
	}
	
	public int Size() { //method to get the size of the list
		
		int size = 0; //create a variable to hold the size;
		List<T> current = this; //create a variable for the current value
		
		while (true) { //loop until end
			
			if (current.next == null) {
				
				break; //if there is no next value, break
				
			}
			current = current.next; //move to the next item
			size++; //increment size
			
		}
		
		return size; //return size
		
	}
	
}
