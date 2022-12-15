/*
 *  Name: Tony Rodriguez
 *  
 *  Class: CS1450 (T/R)
 *  
 *  Due:  Mar 3, 2021 
 *  
 *  Description: Assignment #5
 *  
 *  Purpose: Learn to write a generic class, generic methods, 
 *  plus create and use stacks.
 *  
 *  Synopsis: I will create a JCF stack, place several values onto 
 *  the stack, find the largest value on the stack, then print the 
 *  largest as well as all values on the stack. I will also take one 
 *  unsorted stack and create a new stack containing ONLY the duplicate 
 *  values. Finally I will display the duplicate stack and original stack. 
 *   
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class RodriguezTonyAssignment5 {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		//Creating scanner object to read number.txt file
		File numbers = new File("numbers.txt");
		Scanner readNumFile = new Scanner (numbers);
		
		//Creating scanner object for strings.txt file
		File strings = new File("strings.txt");
		Scanner readStringFile = new Scanner (strings);
		
		//Original values that will be passed into first stack
		int[] values = {10, 17, 19, 63, 42, 8, 2, 7};
		
		//Stack that will hold values obtained from the array 
		Stack<Integer> stackOfNums = new Stack<>();
		
		//Filling stack with the values obtained from the array 
		for(int i = 0; i < values.length; i++) {
			
			stackOfNums.push(values[i]);
			
		}//for loop
		
		//Printing first stack
		System.out.println("Stack1 - Values and Largest Value");
		System.out.println("---------------------------------------------");
		printStack(stackOfNums);
		
		//Obtaining and printing the largest number by using 
		//findLargest method
		int largestNum = findLargest(stackOfNums);
		System.out.printf("Largest number: %d", largestNum);
		
		//Creating generic stack that will hold the values from the file
		GenericStack<Integer> myStack = new GenericStack<>();
		
		//Reading in and passing in values to the stack
		while(readNumFile.hasNext()) {
			
			myStack.push(readNumFile.nextInt());
			
		}
		
		readNumFile.close();
		
		//Printing the stack that took in the values from the file
		System.out.println("\n\nStack2 - Integer Values Read from File");
		System.out.println("---------------------------------------------");
		printGenericStack(myStack);
		
		//Creating new stack that will hold all of the duplicate integers
		//that were read into the previous stack from the file 
		GenericStack<Integer> duplicatesStack = new GenericStack<>();
		
		//Calling createDuplicatesStack to fill duplicates stack
		createDuplicatesStack
		(myStack, duplicatesStack);
		
		//Printing duplicates stack 
		System.out.println("\nDuplicates Stack - Created from Stack2 ");
		System.out.println("---------------------------------------------");
		printGenericStack(duplicatesStack);
		
		//Printing original stack to prove it was unchanged
		System.out.println("\nStack2 - Proving Unchanged after Created Duplicates Stack");
		System.out.println("---------------------------------------------");
		printGenericStack(myStack);
		
		//Creating generic stack that will take in values 
		//read from strings.txt file
		GenericStack<String> stringStack = new GenericStack<>();
		
		//Reading in strings to the stack 
		while(readStringFile.hasNext()) {
			
			stringStack.push(readStringFile.next());
			
		}
		
		readStringFile.close();
		
		//Printing generic stack that holds the strings that 
		//were read in from the file
		System.out.println("\nStack3 - Strings Values Read from File");
		System.out.println("---------------------------------------------");
		printGenericStack(stringStack);
		
		//Creating a stack that will hold the duplicates strings 
		GenericStack<String> stringDuplicates = new GenericStack<>();
		
		//Calling createDuplicatesStack that will file the duplicates 
		//stack with all of the duplicate strings
		createDuplicatesStack
		(stringStack, stringDuplicates);
		
		//Printing the stack that holds all of the string duplicates
		System.out.println("\nDuplicates Stack - Created from Stack3 ");
		System.out.println("---------------------------------------------");
		printGenericStack(stringDuplicates);
		
		//Printing original string stack to prove that it was unchanged
		System.out.println("\nStack2 - Proving Unchanged after Created Duplicates Stack");
		System.out.println("---------------------------------------------");
		printGenericStack(stringStack);
		
		
	}//main
	
	
	
	//findLargest method
	//Looks through a stack and returns the largest value
	public static int findLargest(Stack<Integer> stackOfNums) {
		
		//Temporary stack will hold the values after they are popped 
		//from original stack
		Stack<Integer> tempStack = new Stack<>();
		
		//Variable that will be compared to find the largest number 
		int largestNum = 0; 
		
		while(!stackOfNums.isEmpty()) {
			
			//Comparing current number to the largest number found so far
			//If current number is larger, largest numb is the current number
			if(stackOfNums.peek() > largestNum) {
				largestNum = stackOfNums.peek();
			}
			
			//pushing the value to the temporary stack 
			tempStack.push(stackOfNums.pop());
			
		}//for loop
		
		
		//Filling the original stack back up with all of its original values
			while(!tempStack.isEmpty()) {
			
			stackOfNums.push(tempStack.peek());
			tempStack.pop();
			
		}//for loop
		
		return largestNum;
		
	}//findLargest method
	
	
	//printStack method
	//Will print a non-generic stack while leaving the original stack unchanged
	public static void printStack (Stack<Integer> stack) {
		
		
		//Temporary stack will hold the values of original
		//stack as they are popped off to be printed
		Stack<Integer> tempStack = new Stack<>();
		
		while(!stack.isEmpty()) {
			
			//pushing value to temporary stack and printing the value
			tempStack.push(stack.peek());
			stack.pop();
			System.out.println(tempStack.peek());
			
		}//while loop
		
		//Placing values back into original stack 
		while(!tempStack.isEmpty()) {
			
			stack.push(tempStack.pop());
			
		}
		
		
	}//print stack
	
	
	//printGenericStack method
	//Will print a generic stack while leaving the original stack unchanged
	public static <E> void printGenericStack (GenericStack<E> stack) {
		
		//Temporary stack will hold the values of original
		//stack as they are popped off to be printed
		GenericStack<E> tempStack = new GenericStack<>();
		
		while(!stack.isEmpty()) {
			
			//pushing value to temporary stack and printing the value
			tempStack.push(stack.peek());
			stack.pop();
			System.out.println(tempStack.peek());
			
		}//while loop
		
		//Placing values back into original stack
		while(!tempStack.isEmpty()) {
			
			stack.push(tempStack.pop());
			
		}
		
		
	}//print stack
	
	
	
	//createDuplicatesStack will create a new stack with all of the duplicate 
	//values but keeping the original stack the same 
	public static <E extends Comparable<E>> void createDuplicatesStack
	(GenericStack<E> stack, GenericStack<E> duplicates) {
	
		//Temporary stack will hold the values of original stack
		GenericStack <E> tempStack = new GenericStack<>(); 
		
		
		while(!stack.isEmpty()) {
			
			//Value that is being looked for
			E currentInt = stack.pop();
			
			//If isDuplicate return true, there is a duplicate and the \
			//value is added to duplicate stack 
			if(isDuplicate (stack, currentInt)) {
				
				duplicates.push(currentInt);
				
			}
			
			//If value is a duplicate in the duplicate stack, it is pushed in again
			else if(isDuplicate (duplicates, currentInt)) {
				
				duplicates.push(currentInt);
				
			}
			
			//Pushing value into temporary stack as well
			tempStack.push(currentInt);
		}
		
		//Filling original stack with its original values
		while(!tempStack.isEmpty()) {
			
			stack.push(tempStack.pop());
			
		}
		
	}//createDuplicateStack
	
	
	
	//isDuplicate method will go through a stack and 
	//return true if the value is in the stack more than once
	public static <E extends Comparable<E>> boolean isDuplicate
	(GenericStack<E> stack, E valueLookingFor) {
		
		//temporary stack will hold the values 
		GenericStack<E> tempStack = new GenericStack<>();
		
		while(!stack.isEmpty()) {
			
			//if value is in the stack 
			if(valueLookingFor.compareTo(stack.peek()) == 0) {
				
				//fill original stack back up
				while(!tempStack.isEmpty()) {
					
					stack.push(tempStack.pop());
					
				}
				
				//return true because the value is a duplicate
				return true;
				
			}
			
			//pushing next value to temporary stack
			tempStack.push(stack.pop());
			
		}
		
		//filling the original stack back up
		while(!tempStack.isEmpty()) {
			
			stack.push(tempStack.pop());
			
		}
		
		//returns false if the value is not on the stack 
		return false;
		
	}

}//Assignment 5



//class for the generic stack 
class GenericStack<E>{
	
	//array list is the storage bin for values
	private ArrayList<E> arrayList;
	
	//constructor
	public GenericStack(){
		
		//allocating memory for the array list 
		arrayList = new ArrayList<E>();
		
	}
	
	//return the size of the array list 
	public int getSize() {
		
		return arrayList.size();
		
	}
	
	//returns a boolean depending if the array list is empty
	public boolean isEmpty() {
		
		return arrayList.isEmpty();
		
	}
	
	//returns the last value in the array list
	public E peek() {
		
		return arrayList.get(arrayList.size() - 1);
		
	}
	
	//adds the value to the array list
	public void push(E value) {
		
		arrayList.add(value);
		
	}
	
	//removes a value from the back of the array list
	public E pop() {
		
		E value = arrayList.get(getSize() - 1);
		arrayList.remove(getSize() - 1);
		return value;
	}
	
}//generic stack
