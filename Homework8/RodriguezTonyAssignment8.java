/*
 *  Name: Tony Rodriguez
 *  
 *  Class: CS1450 (T/R)
 *  
 *  Due:  April 6, 2021 
 *  
 *  Description: Assignment #8
 *  
 *  Purpose: Using a 2D array, array lists, queues, a stack, and iterators.
 *  
 *  Synopsis: This assignment provides the opportunity to work with a 2D array, 
 *  array lists, queues, a stack, and iterators.  In this assignment, I will 
 *  write a program to unscramble two secret messages that have been 
 *  encrypted with a simple Route Cipher.
 *   
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class RodriguezTonyAssignment8 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Scanner object the ill read the listMessage file
		File message = new File("listMessage.txt");
		Scanner readMessage = new Scanner (message);
		
		//Scanner object that will read the listKey file
		File listKey = new File("listKey.txt");
		Scanner readKey = new Scanner (listKey);
		
		//Scanner object that will read the queueMessage file
		File queueMessage = new File("queueMessage.txt");
		Scanner readQueueMessage = new Scanner (queueMessage);
		
		//Scanner object that will read the queueKey file
		File queueKey = new File("queueKey.txt");
		Scanner readQueueKey = new Scanner (queueKey);
		
		
		//Creating the 2 array lists that will be used 
		//One store the rout key and 2d array sizes as well 
		//as starting location
		//The second will be used to store the information from 
		//the listKey.txt file
		ArrayList<Character> list1 = new ArrayList<>();
		ArrayList<Character> list2 = new ArrayList<>();
		

		
		//Temporary string that will be used to store the original 
		//message from the file
		String tempString1 = readMessage.nextLine();
			
		//Displaying the original message
		System.out.printf("The original message is: %s", tempString1);
			
		
		//Reading the message characters from the string 
		//to the first array list  
		for(int i = 0; i < tempString1.length(); i++) {
				
			//Adding chars from the String to the first list 
			list1.add(tempString1.charAt(i));
				
		}//for
		
		
		//Reading the key as a string 
		String route = readKey.nextLine();
		
		//Adding chars individually from the route String
		//to the second array list 
		for(int i = 0; i < route.length(); i++) {
			
			list2.add(route.charAt(i));
			
		}
		
		
		//Creating the array list Iterators
		Iterator<Character> msgIterator = list1.iterator();
		Iterator<Character> keyIterator = list2.iterator();
		
		
		//creating the decoder object to the decode the message
		//and reading all the 2d array details into the method
		Decoder decoder = new Decoder(readKey.nextInt(), readKey.nextInt(), 
				readKey.nextInt(), readKey.nextInt());
		
		//Calling the unscramble() method and storing the string returned 
		String unscrambledMsg = decoder.unscramble(msgIterator, keyIterator);
		
		//displaying the unscrambled message
		System.out.printf("\nThe secret meesage is: %s", unscrambledMsg);
		
		
		//making the two queues that will be used for the second message
		Queue<Character> queue1 = new LinkedList<>();
		Queue<Character> queue2 = new LinkedList<>();
	
			
		//Temporary string that will be used to store the original 
		//message 
		String tempString2 = readQueueMessage.nextLine();
			
		//Displaying the original message
		System.out.printf("\n\nThe original message is: %s", tempString2);
			
		
		//Reading the message characters from the string
		//to the first queue 
		for(int i = 0; i < tempString2.length(); i++) {
					
			//Adding chars from the String to the first queue
			queue1.offer(tempString2.charAt(i));
						
		}//for
			
		
		//Reading the key as a string 
		String qRoute = readQueueKey.next();
		
		
		//Adding chars individually from the route String
		//to the second queue
		for(int i = 0; i < qRoute.length(); i++) {
			
			queue2.offer(qRoute.charAt(i));
			
		}
		

		//Creating the array list Iterators
		Iterator<Character> qMsgIterator = queue1.iterator();
		Iterator<Character> qKeyIterator = queue2.iterator();
		
		//creating the decoder object to the decode the message
		//and reading all the 2d array details into the method
		Decoder qDecoder = new Decoder(readQueueKey.nextInt(), readQueueKey.nextInt(), readQueueKey.nextInt(), readQueueKey.nextInt());
		
		//Calling the unscramble() method and storing the string returned 
		String qUnscrambledMsg = qDecoder.unscramble(qMsgIterator, qKeyIterator);
		
		//printing secret message
		System.out.printf("\nThe secret meesage is: %s", qUnscrambledMsg);
		
		
		//Closing all of the scanner
		readMessage.close();
		readKey.close();
		readQueueMessage.close();
		readQueueKey.close();
		
	}//main

}//Assignment 8 



//decoder class
class Decoder{
	
	
	//Instance variable for decoder class
	private int startingRow;
	private int startingColumn;
	private char[][] messageArray;
	private Stack stack;
	
	
	//decoder constructor
	public Decoder (int numRows, int numCols, int startingRow, int startingCol) {
		
		//Filling in all of the data fields
		this.startingRow = startingRow;
		this.startingColumn = startingCol; 
	    stack = new Stack();
		messageArray = new char[numRows][numCols];
		
	}//decoder constructor 
	
	
	//unscramble message that will reveal the secret message 
	//from the original message
	public String unscramble (Iterator <Character> msgIterator, Iterator <Character> keyIterator) {
		
		
		//Obtaining the amount of columns and rows
		int cols = messageArray[0].length;
		int rows1 = messageArray.length;
		
		
		//counter will be used to count columns traversed
		int colCout = 0;
		
		//while loop will be used to traverse the columns
		while(colCout < cols) {
			
			//Traversing the rows
			for(int i = 0; i < rows1; i++) {
				
				//adding the character from message iterator
				//to the index in the 2d array
				messageArray[i][colCout] = msgIterator.next();
				
			}//for loop
			
			colCout++;
			
		}//while loop
		
		
		//counters that will be used to keep track 
		//of current row and column
		int curRow = startingRow;
		int curCol = startingColumn;
		
		//pushing the first char to the starting point
		stack.push(messageArray[startingRow][startingColumn]);
		
		//Using the iterator to read the key
		while(keyIterator.hasNext()) {
			
			
			//reading in the char
			char key = keyIterator.next();
			
			//instruction for moving up the array list 
			if(key == 'u') {
				
				curRow--;
				stack.push(messageArray[curRow][curCol]);
				
			}
			
			//instruction for moving down the array list 
			else if(key == 'l') {
				
				curCol--;
				stack.push(messageArray[curRow][curCol]);
			}
			
			//instruction for moving right in the array list 
			else if(key == 'r') {
				
				curCol++;
				stack.push(messageArray[curRow][curCol]);
				
			}
			
			
			//instruction for moving down the array list 
			else if(key == 'd') {
				
				curRow++;
				stack.push(messageArray[curRow][curCol]);
				
			}
			
		}//while
		
		
		//creating an array of the chars that will be turned into a string
		char [] charArray = new char[stack.getSize()];
		
		//emptying the stack into the array 
		int index = 0;
		while(!stack.isEmpty()) {
			
			charArray[index] = stack.pop();
			index++;
			
		}
		
	
		//creating the array of chars to a string 
		String str = new String(charArray);
	
		return str;

	}//unscramble
	
}//Decoder



//Stack class
class Stack{
	
	//array list is the storage bin for values
	private ArrayList<Character> arrayList;
	
	//constructor
	public Stack(){
		
		//allocating memory for the array list 
		arrayList = new ArrayList<>();
		
	}
	
	//return the size of the array list 
	public int getSize() {
		
		return arrayList.size();
		
	}
	
	//returns a boolean depending if the array list is empty
	public boolean isEmpty() {
		
		return arrayList.isEmpty();
		
	}
	
	//adds the value to the array list
	public void push(Character value) {
		
		arrayList.add(value);
		
	}
	
	//removes a value from the back of the array list
	public Character pop() {
		
		Character value = arrayList.get(getSize() - 1);
		arrayList.remove(getSize() - 1);
		return value;
	}
	
}//stack
