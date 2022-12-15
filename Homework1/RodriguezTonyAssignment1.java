/*
 *  Name: Tony Rodriguez
 *  
 *  Class: CS1450 (T/R)
 *  
 *  Due:  Jan 27, 2021 
 *  
 *  Description: Assignment #1
 *  
 *  Purpose: Get my mind back into writing code.  
 *  Review of loops, arrays, and files.
 *  
 *  Synopsis: Review arrays by filling an array 
 *  with values and then performing several array 
 *  manipulations. To review files, I will perform 
 *  a simple writing to and reading from a file.   
 *   
*/

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;



public class RodriguezTonyAssignment1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//original array
		int[] array = {1, 18, 10, 2, 16, 8, 15, 9, 9, 17, 14, 18, 1, 19, 18, 2, 1};
		
		System.out.println("Values in the array (Before sort)");
		
		//printing array before being sorted
		for(int i = 0; i < array.length; i++ ) {
			System.out.printf("arrayValues[%d] = %d\n", i, array[i]);
		}
		
		//using the .sort method within the arrays class to sort 
		//the array in ascending order
		Arrays.sort(array);
		
		//creating new file named assignment1.txt
		File fileName = new File("assignment1.txt");
		
		//creating a new printwriter object
		PrintWriter outputFile = new PrintWriter (fileName);
		
		//counters that will be used to count even and odd numbers
		int evenCounter = 0;
		int oddCounter = 0;
		int valueCounter = 0;
		
		//checking each index in the array to see if the value is even or odd and 
		//incrementing their corresponding counter
		for(int i = 0; i < array.length; i++) {
			
			if(array[i] % 2 == 0) {
				outputFile.printf("%d\n", array[i]);
				evenCounter++;
			}//if
			
			else {
				outputFile.printf("%d\n", array[i]);
				oddCounter++;
			}//else
			
			valueCounter++;
			
		}//for loop
		
		
		//obtaining the path for the file 
		String absolutePath = fileName.getCanonicalPath();
		
		
		//printing counters and path
		System.out.printf("\nTotal number of values printed to file: %d", valueCounter);
		System.out.printf("\n\nNumber of even numbers: %d\n", evenCounter );
		System.out.printf("\nNumber of odd numbers: %d\n", oddCounter);
		System.out.printf("\nFile is in directory: %s", absolutePath);
		
		//closing the file
		outputFile.close();
		
		
		//arrays for non duplicates
		int evenArray[] = new int [evenCounter];
		int oddArray[] = new int [oddCounter];
		
		//will be used to check previous index for duplicates
		int evenIndex = 0;
		int oddIndex = 0;
		
		//creating new scanner object to read the file
		Scanner readFile = new Scanner (fileName);
		
		
		//counters that will be used to determine correct size for arrays
		int evenArraySize = 0;
		int oddArraySize = 0; 
		
		
		//while the next value in file is an int
		while(readFile.hasNextInt()) {
			
			int fileValue = readFile.nextInt();
			
			if(fileValue % 2 == 0) {
				
				if(evenIndex == 0) {
					evenArray[evenIndex] = fileValue;
					evenIndex++;
					evenArraySize++;
				}//if
				
				else if(evenIndex != 0 && fileValue != evenArray[evenIndex - 1]) {
					evenArray[evenIndex] = fileValue;
					evenIndex++;
					evenArraySize++;
				}//else if
				
			}//outer if
			
			if(fileValue % 2 != 0) {
				
				if(oddIndex == 0) {
					oddArray[oddIndex] = fileValue;
					oddIndex++;
					oddArraySize++;
				}//if
				
				else if(oddIndex != 0 && fileValue != oddArray[oddIndex - 1]) {
					oddArray[oddIndex] = fileValue;
					oddIndex++;
					oddArraySize++;
				}//else if
				
			}//outer if 
			
		}//while loop
		
		//closing file reader
		readFile.close();
		
		
		
		//Creating correct size arrays by using the counters for size
		int noDuplicateEvenArray[] = new int[evenArraySize];
		int noDuplicateOddArray[] = new int [oddArraySize];
		
		//copying values over to correctly sized array 
		for(int i = 0; i < noDuplicateEvenArray.length; i++) {
			noDuplicateEvenArray[i] = evenArray[i];
		}
		
		//copying values over to correctly sized array
		for(int i = 0; i < noDuplicateOddArray.length; i++) {
			noDuplicateOddArray[i] = oddArray[i];
		}
		
		
		
		//Printing output of arrays with no duplicates
		System.out.println("\n\n\n\nEven array with no duplicates");
		System.out.println("---------------------------------");
		
		for(int i = 0; i < evenArray.length; i++) {
			System.out.printf("evenArray[%d] = %d\n", i, evenArray[i]);
		}
		
		System.out.println("\n\nOdd array with no duplicates");
		System.out.println("---------------------------------");
		
		for(int i = 0; i < oddArray.length; i++) {
			System.out.printf("oddArray[%d] = %d\n", i, evenArray[i]);
		}
		
		
		
		//Printing arrays with non duplicate and non zeros
		System.out.println("\n\nEven array with no duplicates or extra zeros");
		System.out.println("--------------------------------------------------");
		
		for(int i = 0; i < noDuplicateEvenArray.length; i++) {
			System.out.printf("evenArray[%d] = %d\n", i, noDuplicateEvenArray[i]);
		}
		
		
		System.out.println("\n\nOdd array with no duplicates or extra zeros");
		System.out.println("-----------------------------------------------");
		
		for(int i = 0; i < noDuplicateOddArray.length; i++) {
			System.out.printf("oddArray[%d] = %d\n", i, noDuplicateOddArray[i]);
		}
		
		
		
	}//main
	
}//class


