/*
 *  Name: Tony Rodriguez
 *  
 *  Class: CS1450 (T/R)
 *  
 *  Due:  Mar 22, 2021 
 *  
 *  Description: Assignment #7
 *  
 *  Purpose: Learn about regular queues and priority queues.
 *  
 *  Synopsis: For this assignment, I will write code to simulate 
 *  an Olympic swimming race.  From assignment #4, the athletes have
 *  been placed into designated seats in the ready room and are waiting 
 *  for their race. The next step is for the athletes to join a waiting 
 *  for introduction queue where they wait to be introduced.  As the 
 *  athletes are introduced they are removed from the waiting for 
 *  introduction queue, assigned a lane in the pool, race to obtain a time, 
 *  and finally move to the results queue, where their place depends on the 
 *  time they received during the race.  
 *   
*/


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RodriguezTonyAssignment7 {

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
		
		//creating a new referee object\
		//and a new race 
		RaceReferee reff = new RaceReferee();
		Race tonysRace = new Race();
		
		
		//Moving athletes from ready room to intro queue
		System.out.println("Moving athletes from ready room to wait for introductions line.");
		System.out.println("----------------------------------------------------------------");
		reff.moveAthletesToWaitingForIntro(aquaticCenter, tonysRace);
		
		
		//moving the athlete from the intro queue to the pool
		System.out.println("\n\nMoving athletes to assigned pool lane to prepare for race.");
		System.out.println("----------------------------------------------------------------");
		reff.moveAthletesToPoolLanes(aquaticCenter, tonysRace);
		
		//displaying the pool
		aquaticCenter.displayPool();
		
		//Starts the race simulation
		System.out.println("\n\nRace Referee: On Your Mark, Get Set, Go!!!");
		System.out.println("----------------------------------------------------------------");
		System.out.println("Lane\t Name\t\t\tQualifying\tAdded Seconds\tRace Time");
		System.out.println("----------------------------------------------------------------");
		reff.startRace(aquaticCenter, tonysRace);
		
		//displaying the results of the race
		System.out.println("\n\nRace Referee: Race Results");
		System.out.println("----------------------------------------------------------------");
		System.out.println("Place\t Name\t\t\tRace Time\tEvent\t\tTeam");
		System.out.println("----------------------------------------------------------------");
		reff.displayRaceResults(tonysRace);
		
		
		//displaying the ready room and the pool after the race
		aquaticCenter.displayReadyRoom();
		aquaticCenter.displayPool();
		
		
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
	
	//instance variables for the aquatics center
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
		
		System.out.println("\n\n----------------------------");
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
	
	
	//Removes an athlete at a certain seat in the ready room 
	//and returns the removed athlete
	public Athlete removeAthleteFromReadyRoom (int seat) {
		
		//Creating temporary athlete to hold the athlete that will removed from the 
		Athlete tempAthlete = readyRoom[seat];
		
		//Removing the athlete from the ready room 
		readyRoom[seat] = null;
		
		//returning the temporary athlete 
		return tempAthlete;
		
	}
	
	
	//Removes an athlete in a certain lane in the pool 
	//and return the removed athlete 
	public Athlete removeAthleteFromPool (int lane) {
		
		//Creating a temporary athlete to hold the athlete that is being removed
		Athlete tempAthlete = pool[lane];
		
		//Removing the athlete from the lane in the pool
		pool[lane] = null;
		
		//returning the temporary athlete 
		return tempAthlete;
	}
	
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
    
    
    //Adds the incoming time to the athlete current time
    public void updateTime (double secondsToAdd) {
    	
    	this.time += secondsToAdd;
    	
    }

    
}//Athlete


//Race class
class Race{
	
	
	//Queues used for waiting for introduction 
	//and results 
	private PriorityQueue<Athlete> waitingForIntro;
	private PriorityQueue<Athlete> resultsQueue;
	
	
	//Constructor 
	public Race() {
		
		
		//Allocating memory for the queues
		waitingForIntro = new PriorityQueue<>();
		resultsQueue = new PriorityQueue<>();
	}
	
	
	//method that returns a boolean value indicating if the 
	//waiting for into queue is empty 
	public boolean isWaitingForIntroEmpty() {
		
		return waitingForIntro.isEmpty();
		
	}
	
	
	//adds an athlete to the waiting for introduction queue
	public void addAthleteToWaitingForIntro(Athlete athlete) {
		
		waitingForIntro.offer(athlete);
		
	}
	
	
	//Removes an athlete form the waiting for intro queue
	public Athlete removeAthleteFromWaitingForIntro() {
		
		return waitingForIntro.remove();
		
	}
	
	
	//Returns a boolean value indicating if the results queue is empty 
	public boolean isResultsQueueEmpty() {
		
		return resultsQueue.isEmpty();
		
	}
	
	
	//adds an athlete to the results queue
	public void addAthleteToResultsQueue(Athlete athlete) {
		
		resultsQueue.offer(athlete);
		
	}
	
	
	
	//removes an athlete from the results queue
	public Athlete removeAthleteFromResultsQueue() {
		
		return resultsQueue.remove();
		
	}
	
}//Race class 



///Race referee class
class RaceReferee{
	
	public void moveAthletesToWaitingForIntro (AquaticCenter aquaticsCenter, Race race) {
		
		for(int i = 0; i < aquaticsCenter.getNumberReadyRoomSeats(); i++) {
			
			//Creating a temporary athlete to hold the athlete that i removed from the ready room
			Athlete tempAthlete = aquaticsCenter.removeAthleteFromReadyRoom(i);
			
			if(tempAthlete != null) {
				
				System.out.printf("Seat %d moved to waiting for introduction: %s\n", i, tempAthlete.getName());
				
				//adding the athlete to the waiting for intro queue
				race.addAthleteToWaitingForIntro(tempAthlete);
				
			}
			
		}//for loop
		
		
	}//MoveAthletesToWaitingForIntro
	
	
	
	//Moves athletes to the pool
	public void moveAthletesToPoolLanes (AquaticCenter aquaticsCenter, Race race) {
		
		
		//Specified order used based on times
		int[] order = {4, 5, 3, 6, 2, 7, 1, 8};
		
		int counter = 0; 
		
		while(!race.isWaitingForIntroEmpty()) {
			
			
			//Creating a temporary athlete that will hold the athlete that is removed from 
			//the waiting for intro queue
			Athlete tempAthlete = race.removeAthleteFromWaitingForIntro();
			
			
			//Displaying and welcoming the athlete
			System.out.printf("Please welcome in lane %d: %s\n", order[counter], tempAthlete.getName());
			
			//adding the athlete to the pool
			aquaticsCenter.addAthleteToPool(order[counter], tempAthlete);
			
			counter++;
			
		}//While loop
		
	}//moveAthletesToPoolLanes
	
	
	
	//Starts the race simulation for all athletes
	public void startRace (AquaticCenter aquaticsCenter, Race race) {
		
		
		for(int i = 0; i < aquaticsCenter.getNumberLanes(); i++) {
			
			//removing athlete from lane 
			Athlete tempAthlete = aquaticsCenter.removeAthleteFromPool(i);
			
			
			//If there IS an athlete in that lane
			if(tempAthlete != null) {
				
				//Generating random time to be added to athletes time for the race
				double addedTime = 0 + (double) (Math.random() * 1);
				
				//Displaying athlete and theie info 
				System.out.printf("%d\t%s\t\t%.2f\t\t%.2f\t\t%.2f\n", i, tempAthlete.getName(), 
						tempAthlete.getTime(), addedTime, tempAthlete.getTime() + addedTime);
				
				
				//adding generated time to the athletes original time
				tempAthlete.updateTime(addedTime);
				
				
				//Adding athlete to the results queue
				race.addAthleteToResultsQueue(tempAthlete);
				
			}//if stmt
			
		}//for loop
		
	}//startRace
	
	
	
	//Displays the results of the race 
	public void displayRaceResults (Race race) {
		
		int placeCounter = 1;
		
		
		while(!race.isResultsQueueEmpty()) {
			
			
			//removing athlete from results queue
			//and creating a temporary athlete
			Athlete tempAthlete = race.removeAthleteFromResultsQueue();
			
			
			//Displaying the results for the particular athlete
			System.out.printf("%d\t%s\t\t%.2f\t\t%s\t%s\n", placeCounter, tempAthlete.getName(), 
					tempAthlete.getTime(), tempAthlete.getEvent(), tempAthlete.getTeam());
			
			placeCounter++;
			
		}//while
		
	}//displayraceResults
	
	
}//RaceReferee
	
