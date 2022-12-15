/* 

*  Name: Tony Rodriguez 
*   
*  Class: CS1450 (T/R) 
*   
*  Due:  Feb 23, 2021  
*   
*  Description: Assignment #4 
*   
*  Purpose: Learn to use objects that contain objects and work with comparable and collections
*   
*  I will create a program that models an aquatic center, such as the aquatic center in 
*Tokyo that will be used during the 2021 Olympics.  The aquatic center contains a ready 
*room and a pool, both of these hold athletes.  The number of seats in the ready room 
*and the number of lanes in the pool, are specified in the Venue.txt file. To setup the 
*aquatic center’s ready room and pool, I will read the name of the venue, number of seats, 
*and number of lanes from the venue file and create an AquaticCenter object that contains
* two regular arrays based on these values. Next, I will fill the aquatic center’s ready room with 
*athletes.  For each athlete in the file Athletes.txt, I will read the athlete’s details, create the athlete, 
*and place the athlete in their specified seat in the aquatic center’s ready room. After all athletes are 
*placed into the aquatic center’s ready room, I will display the ready room and pool, and print an athlete 
*report showing the athletes that are ready.  The report will display the athletes in order from fastest
*qualifying time to lowest qualifying time.  
*    
*/ 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RodriguezTonyAssignment4 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Creating a new scanner object that will be used to read the Athlete.txt file
		File athleteFile = new File("Athletes.txt");
		Scanner readAthleteFile = new Scanner (athleteFile);
		
		//Creating a new scanner object that will be used to read the Venue.txt file
		File venueFile = new File("Venue.txt");
		Scanner readVenueFile = new Scanner (venueFile);
		
		//Creating the aquaticCenter object that will house all of the Athletes
		//The constructor is reading in the aquiticCenter's attribute from the Venue.txt file
		AquaticCenter aquaticCenter = new AquaticCenter(readVenueFile.nextLine(), 
				readVenueFile.nextInt(), readVenueFile.nextInt());
		
		//While loop runs through the entire Athlete.txt file 
		while(readAthleteFile.hasNext()) {
			
			//reading in the the index where the athlete will be placed 
			int index = readAthleteFile.nextInt();
			
			//creating the athlete while reading in their name, time, event, and name
			Athlete athlete = new Athlete(readAthleteFile.next(), readAthleteFile.nextDouble(), 
					readAthleteFile.next(), readAthleteFile.nextLine());
			
			//adding the athlete to the ready room array 
			aquaticCenter.addAthleteToReadyRoom(index, athlete);
			
		}//while loop
		
		//closing the read files 
		readAthleteFile.close();
		readVenueFile.close();
		
		//displaying the athletes in the ready room 
		aquaticCenter.displayReadyRoom();
		
		//displaying the athletes in the pool 
		aquaticCenter.displayPool();
		
		//printing the athletes that are ready 
		printReadyAthletes(aquaticCenter);
		
	}//main

	//method will print all of the athletes that are ready 
	//athletes will be sorted by time
	public static void printReadyAthletes(AquaticCenter aquaticCenter) {
		
		System.out.println("\n\n--------------------------------------------------------");
		System.out.println("\t\tATHLETES IN READY ROOM");
		System.out.println("--------------------------------------------------------");
		System.out.println("Name\t\t\tTime\tEvent\t\tTeam");
		
		
		//ArrayList will hold the Athletes
		ArrayList<Athlete> readyRoomAthletes = new ArrayList<Athlete>();
		
		//Adding all of the athletes from the ready room array into the ArrayList 
		for(int i = 0; i < aquaticCenter.getNumberReadyRoomSeats(); i++) {
			
			if(aquaticCenter.getAthleteInReadyRoom(i) != null) {
				
				readyRoomAthletes.add(aquaticCenter.getAthleteInReadyRoom(i));
				
			}
			
		}//for loop
		
		//sorting the athletes based on their times 
		Collections.sort(readyRoomAthletes);
		
		//printing all of the athletes from the array list
		for(int i = 0; i < readyRoomAthletes.size(); i++) {
			
			System.out.printf("\n%s", readyRoomAthletes.get(i).toString());
			
		}//for loop

		
    }//print ready Athletes
	
}//Assignment4



//aquatic center class
class AquaticCenter{
	
	//instance variables 
	private String name;
	private int numberReadyRoomSeats;
	private Athlete readyRoom[];
	private int numberLanes;
	private Athlete pool[];
	
	
	//constructor 
	public AquaticCenter (String name, int numberReadyRoomSeats, int numberLanes) {
		
		this.name = name;
		this.numberReadyRoomSeats = numberReadyRoomSeats;
		this.numberLanes = numberLanes;
		
		readyRoom = new Athlete[numberReadyRoomSeats];	
		pool = new Athlete[numberLanes];
		
	}//constructor
	
	
	//getter for the name
	public String getName() {
		
		return name;
		
	}//getName
	
	
	//getter for the number of ready room seats 
	public int getNumberReadyRoomSeats() {
		
		return numberReadyRoomSeats;
		
	}//getNumberReadyRoomSeats
	
	
	//getter for the number of lanes in the pool 
	public int getNumberLanes() {
		
		return numberLanes;
		
	}//getNumberLanes
	
	
	//method to add an athletes to the ready room array
	public void addAthleteToReadyRoom (int seat, Athlete athlete) {
		
		readyRoom[seat] = athlete;
		
	}//addAthleteToReadyRoom
	
	
	//method to add an athlete to the pool array 
	public void addAthleteToPool (int lane, Athlete athlete) {
		
		pool[lane] = athlete;
		
	}//addAthleteToPool
	
	
	//method to get an athlete from the ready room 
	//if the seat is not empty
	public Athlete getAthleteInReadyRoom (int seat) {
		
		if(readyRoom[seat] != null) {
			
			return readyRoom[seat];
			
		}
		
		else {
			return null;
		}
		
	}//getAthleteInReadyRoom
	
	
	//method to display athletes in ready room 
	public void displayReadyRoom() {
		
		System.out.println("----------------------------");
		System.out.println("       READY ROOM");
		System.out.println("----------------------------");
		
		System.out.println("Seat\tAthlete");
		System.out.println("----------------------------");
		
		//for loop is iterating through the array and displaying the athletes 
		//Only if the seat (index) is not empty
		for(int i = 0; i < readyRoom.length; i++) {
			
			if(readyRoom[i] != null) {
				
				System.out.printf("\n%d\t%s", i, readyRoom[i].getName());
					
			}
			
			else {
				System.out.printf("\n%d\t -------", i);
			}
			
		}//for loop
		
	}//displayReadyRoom
	
	
	//method to display the athletes in the pool 
	public void displayPool() {
		
		System.out.println("\n\n----------------------------");
		System.out.println("       Pool");
		System.out.println("----------------------------");
		
		System.out.println("Lane\tAthlete");
		System.out.println("----------------------------");
		
		
		//for loop is iterating through the pool array and displaying the athletes 
		//if the seat is not empty, it will display that athlete
		for(int i = 0; i < pool.length; i++) {
			
			if(pool[i] != null) {
				
				System.out.printf("\n%d\t%s", i, pool[i].getName());
				
			}
			
			else {
				
				System.out.printf("\n%d\t-------", i);
				
			}
			
		}//for loop
		
		
	}//displayPool
	
}//AquaticCenter



//Athlete class
class Athlete implements Comparable<Athlete>{
	
	//instance variables
	private String team;
	private double time;
	private String event;
	private String name;
	
	//constructor for the Athlete 
	public Athlete (String team, double time, String event, String name) {
		
		this.team = team;
		this.time = time; 
		this.event = event;
		this.name = name;
	}
	
	//getter for team
	public String getTeam() {
        return team;
    }
	
	//getter for time
    public Double getTime() {
        return time;
    }
    
    //getter for event
    public String getEvent() {
        return event;
    }
    
    //getter for name 
    public String getName() {
        return name;
    }
    
    //to string method will be used to print athletes
    public String toString() {
        return String.format("%s\t\t%.2f\t%-10s\t%-10s", name, time, event, team);
    }
    
    //compare to method will be used to compare the athletes based on their time 
    public int compareTo(Athlete otherAthlete) {
        if(time < otherAthlete.getTime() ) {
            return -1;
        }
        
        else if (time > otherAthlete.time) {
            return 1;
        }
        
        else {
            return 0;
        }

        
    }//compareTo 

    
}//Athlete
	
