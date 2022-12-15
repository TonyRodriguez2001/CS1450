/*
 *  Name: Tony Rodriguez
 *  
 *  Class: CS1450 (T/R)
 *  
 *  Due:  Jan 28, 2021 
 *  
 *  Description: Assignment #2
 *  
 *  Purpose: Review of classes, objects, inheritance and polymorphism.  
 *  
 *  Synopsis: I will create various classes and subclassed 
 *  dealing with adventures and their prices. I will create 
 *  a polymorphic array that will hold various adventures as
 *  well as a company. I will then display all of the details
 *  of the adventures from the file.
 *   
*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RodriguezTonyAssignment2 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		//openning file for reading
		File file = new File("Adventures.txt");
		
		
		//creating scanner object to read from file
		Scanner readFile = new Scanner (file);
		
		
		//obtaining array size from file
		int arraySize = readFile.nextInt();
		
		
		//creating array of size specified in file
		Adventure[] adventuresArray = new Adventure[arraySize];
		
		
		
		//creating array of adventures from file
		for(int i = 0; i < adventuresArray.length; i++) {
			
			//reading in the type of the object
			String type = readFile.next();
			
			//will create zipline object if the type is zipline
			if(type.equals("Zipline")) {
				
				adventuresArray[i] = new Zipline(readFile.nextDouble(), readFile.nextDouble());
			}
			
			//will create snorkel object if the type is snorkel
			else if(type.equals("Snorkel")) {
				
				adventuresArray[i] = new Snorkel(readFile.nextDouble(), readFile.nextDouble());
				
			}
			
			//will create helicopter object if the type is snorkel
			else if(type.equals("Helicopter")) {
				
				adventuresArray[i] = new Helicopter(readFile.nextDouble(), readFile.nextDouble());
				
			}
			
		}//for loop
		
		
		//printing all of the adventures in the polymorphic adventuresArray 
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("Type\t     Time    Price       Description");
		System.out.println("----------------------------------------------------------------------------------------------");
		
		
		//using for loop to display adventures
		for(int i = 0; i < adventuresArray.length; i++) {
			
			System.out.printf("%-12s %-7.2f $%-10.2f %-17s\n",adventuresArray[i].getType(), adventuresArray[i].getTime(), 
					adventuresArray[i].getPrice(), adventuresArray[i].description());
			
		}//for loop
		
		
		//creating new adventure company object 
		AdventureCompany company = new AdventureCompany("Snorkel");
		
		
		//passing in adventuresArray to company
		//to create array in the adventure company object
		company.setupCompany(adventuresArray);
		
		
		//printing the array from the adventure company 
		company.printCompanyDetails();
		
		readFile.close();
	}//main

}//class



class Adventure{
	
	//instance variables for adventure objects
	private String type;
	private double time;
	private double price;
	
	
	//constructor
	public Adventure(String type, double time, double price) {
		
		this.type = type;
		this.time = time;
		this.price = price;
		
	}//Adventure Constructor
	
	
	//getter for the type
	public String getType(){
		
		return type;
		
	}//getType
	
	
	//getter for the time
	public double getTime() {
		
		return time;
		
	}//getTime
	
	
	//getter for the price
	public double getPrice() {
		
		return price;
		
	}//getPrice
	
	
	//method will display the adventure 
	public String description() {
		
		return "Adventure Type:" + getType() + " Time:" + getTime() + " Adventure Price: " + getPrice();	
		
	}
	
}//Adventure class



//zipline class
class Zipline extends Adventure{

	
	//constructore for zipline objects
	public Zipline(double time, double price) {
		
		super("Zipline",time, price);
	
	}
	
	
	//overridden display method
	@Override
	public String description() {
		return "Put on a harness, connect to a cable, enjoy an aerial view";	
	}

}//Zipline Class



//snorkel class 
class Snorkel extends Adventure{
	
	
	//constructor for snorkel object
	public Snorkel(double time, double price) {
		
		super("Snorkel", time, price);
		
	}
	
	
	//overriden display method
	@Override
	public String description() {
		
		return "Use a diving mask & breathing tube, enjoy underwater world";	
		
	}
	
}//Snorkel Class



//helicopter class
class Helicopter extends Adventure{
	
	
	//constructor for helicopter object
	public Helicopter(double time, double price) {
		
		super("Helicopter", time, price);
		
	}
	
	
	//overridden display method
	@Override
	public String description() {
		
		return "Watch the world unfold from a bird's eye view";	
		
	}
	
}//Helicopter Class



//adventure company class
class AdventureCompany{
	
	//instance variables
	private String niche;
	private Adventure[] availableAdventures;
	
	
	//adventure company constructor
	public AdventureCompany(String niche) {
		
		this.niche = niche;
		
	}
	
	
	//setup company method will bring in polymorphic array 
	//to create array of specified niche
	public void setupCompany (Adventure[] adventures) {
		
		int arraySize = 0; 
		
		//bringin in adventure object from polymorphic array
		//if the type specified by the niche 
		for(int i = 0; i < adventures.length; i++) {
			
			//iterating through polymorphic array
			//looking to see if type matches niche
			//counting the amount of objects
			if(adventures[i].getType().equals(niche)) {
				arraySize++;
			}
			
		}//for loop
		
		
		//allocating array of the size returned from for loop
		availableAdventures = new Adventure[arraySize];
		
		int counter = 0;
		
		//iterating through polymorphic array
		//looking to see if type matches niche
		//and bringing them into array
		for(int i = 0; i < adventures.length; i++) {
			
			if(adventures[i].getType().equals(niche)) {
				
				availableAdventures[counter] = adventures[i];
				counter++;
			}
			
		}//for loop
		
	}//setupCompany
	
	
	//print company method will display all of the contents of the array
	public void printCompanyDetails() {
		
		System.out.println("\n\n\nCS1450 Adventures");
		System.out.println("--------------------------------------------");
		System.out.printf("Company's niche:\t%s adventures", niche);
		System.out.printf("\nAdventures available: %d", availableAdventures.length);
		System.out.println("");
		
		
		for(int i = 0; i < availableAdventures.length; i++) {
			
			System.out.printf("\n%s --- %.2f hours --- $%.2f", availableAdventures[i].getType(), 
					availableAdventures[i].getTime(), availableAdventures[i].getPrice());
			
		}//for loop
		
	}
	
}//AdventureCompany class