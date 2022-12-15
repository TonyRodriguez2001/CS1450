/*
 *  Name: Tony Rodriguez
 *  
 *  Class: CS1450 (T/R)
 *  
 *  Due:  Feb 10, 2021 
 *  
 *  Description: Assignment #3
 *  
 *  Purpose: Learn to write abstract classes and interfaces and use ArrayLists.  
 *  
 *  Synopsis: I will creates a polymorphic array list that will store employee objects. 
 *  The employees in this array list represent a team called Dragon Project
 *  I will then display the role, name and duties for each employee in the array list
 *  lastly, I will build a new team called SpaceX from the current employees
 *   
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class RodriguezTonyAssignment3 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		//Calling file for reading '
		//creating scanner object 
		File file = new File("employees.txt");
		Scanner readFile = new Scanner (file);
		
		//reading first number in file
		String firstNum = readFile.nextLine();
		firstNum = null;
		
		//creating polymorphic array list to hold employee objects from the file 
		ArrayList<Employee> dragonProject = new ArrayList<Employee>();
		 
		//while loop that will be used to read from the file
		while(readFile.hasNext()) {
			
			//reading in type/role, name, and the abilities
			String type = readFile.next();
			String name = readFile.next();
			int designAbility = readFile.nextInt();
			int developingAbility = readFile.nextInt();
			int testingAbility = readFile.nextInt();
			int manageAbility = readFile.nextInt();
			
			
			//if the type/role is a software architect 
			if(type.equals("swa") && designAbility != 0) {
				
				Employee architect = new Architect(name, designAbility);
				architect.setRole("Software Architect");
				architect.setName(name);
				dragonProject.add(architect);
				
			}
			
			
			////if the type/role is a testing engineer
			else if(type.equals("qae") && testingAbility != 0) {
				
				Employee qaEngineer = new QAEngineer(name, testingAbility);	
				qaEngineer.setRole("Testing Engineer");
				qaEngineer.setName(name);
				dragonProject.add(qaEngineer);
				
			}
			
			
			//if the type/role is software engineer 
			else if(type.equals("swe") && testingAbility != 0 && developingAbility != 0) {
				
				Employee softwareEngineer = new SoftwareEngineer(name, testingAbility, developingAbility);
				softwareEngineer.setRole("Software Engineer");
				softwareEngineer.setName(name);
				dragonProject.add(softwareEngineer);
				
			}
			
			
			//if the type/role is a software manager
			else if(type.equals("swm") && manageAbility != 0) {
				
				Employee teamManager = new TeamManager(name, manageAbility);
				teamManager.setRole("Software Manager");
				teamManager.setName(name);
				dragonProject.add(teamManager);
				
			}
			
			
			
		}//while loop
		
		//closing file after reading
		readFile.close();
		
		
		System.out.println("All employees initially on Dragon Project");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Role      		Name      	Duties");
		System.out.println("-------------------------------------------------------------------------------");
		
		//displaying employees in the initial dragonProject team 
		displayEmployees(dragonProject);
		
		System.out.println("\n\n\nBuilding SpaceX Team...\n\n\n");
		
		//displaying employees in the SpaceX team
		displayEmployees(buildTeam(dragonProject, 1, 1, 3, 3));
		
		
		System.out.println("\n\n\nEmployees remaining on Dragon Project");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Role      		Name      	Duties");
		System.out.println("-------------------------------------------------------------------------------");
		
		//displaying employees left on the dragon project team
		displayEmployees(buildTeam(dragonProject,1,1,1,1));
		
	}//main
	
	//Display employyes method
	public static void displayEmployees(ArrayList<Employee> employees) {
           
		//displaying each employee at each index
		for(int i = 0; i < employees.size(); i++){
			
			System.out.printf("%-22s %-15s", employees.get(i).getRole(), 
					employees.get(i).getName());
			
			//accesing each index of the array list
			employees.get(i).duties();
		}
		
    }//displayEmployees
	
	
	//find best method will compare employees of a certain role by skill
	//and return the index of the best one
public static int findBest(ArrayList<Employee> employees, String role) {
		
		int totalBestAbility = 0;
		
		int bestIndex = 0;
		
		
		//for loop itterating through each index of the array list
		//to find the employees of the role being compared
		for(int i = 0; i < employees.size(); i++) {
			
			
			if(employees.get(i).getRole().equals(role)) {
				
				
				//if role is software acrhitect the corresponding skills will 
				//be analyzed an compared to the highest previous skill
				if(role.equals("Software Architect")) {
					
					Designer architect = (Designer) employees.get(i);
					
					int totalAbility = architect.design();
					
					 if(totalAbility > totalBestAbility) {
	                    	totalBestAbility = totalAbility;
	                    	bestIndex = i;
	                    }
				
				}
				
				
				//if role is software engineer the corresponding skills will 
				//be analyzed an compared to the highest previous skill
				else if(role.equals("Software Engineer")) {
					
					Tester engineer = (Tester) employees.get(i);
					int testAbility = engineer.test();
					Developer developer = (Developer) employees.get(i);
					int developAbility = developer.develop();
					
					int totalAbility = testAbility + developAbility;
					
					 if(totalAbility > totalBestAbility) {
	                    	totalBestAbility = totalAbility;
	                    	bestIndex = i;
	                    }
				}
				
				
				//if role is testing engineer the corresponding skills will 
				//be analyzed an compared to the highest previous skill
				else if(role.equals("Testing Engineer")) {
                    Tester qaEngineer = (Tester) employees.get(i);
                    
                   int totalAbility = qaEngineer.test();
                   
                   if(totalAbility > totalBestAbility) {
                   	totalBestAbility = totalAbility;
                   	bestIndex = i;
                   }
                }
				
				
				//if role is software manager the corresponding skills will 
				//be analyzed an compared to the highest previous skill
				else if (role.equals("Software Manager")) {
                    Manager teamManager = (Manager) employees.get(i);
                    
                    int totalAbility = teamManager.manage();
                    
                    if(totalAbility > totalBestAbility) {
                    	totalBestAbility = totalAbility;
                    	bestIndex = i;
                    }
               
                }
				
			}//outer if
			
		}//for loop
		
		return bestIndex;
    }//findBest
	
	

	//build team method will bring in an array list and 
	//build a new one holding the SpaceX team 
	public static ArrayList<Employee> buildTeam(ArrayList<Employee> employees, int numManagers, 
			int numDesigners, int numDevelopers, int numTesters) {
		
		//creating spacex team
		ArrayList<Employee> spaceXTeam = new ArrayList<>();
		
		
		//Variable used as counter
		int numberManagers = 0;
		
		//while loop will itterate while not all software mangers are found
		while(numberManagers != numManagers) {
			
			//Obtaining the index of the best employee
			int bestManager = findBest(employees, "Software Manager");
			
			//Adding employee to SpaceX team 
			spaceXTeam.add(employees.get(bestManager));
			
			//Removing employee from Dragon Project array list
			employees.remove(bestManager);
			
			numberManagers++;
			
		}//managers while
		
		
		//Variable used as counter
		int numberDesigners = 0;
		
		//while loop will itterate while not all software architects are found
		while(numberDesigners != numDesigners) {
			
			//Obtaining the index of the best employee
			int bestDesigner = findBest(employees, "Software Architect");
			
			//Adding employee to SpaceX team 
			spaceXTeam.add(employees.get(bestDesigner));
			
			//Removing employee from Dragon Project array list
			employees.remove(bestDesigner);
			
			numberDesigners++;
			
		}//architects while
		
		
		//Variable used as counter
		int numberEngineers = 0;
		
		//while loop will itterate while not all software engineers are found
		while(numberEngineers != numDevelopers){
			
			//Obtaining the index of the best employee
			int bestEngineer = findBest(employees, "Software Engineer");
			
			//Adding employee to SpaceX team 
			spaceXTeam.add(employees.get(bestEngineer));
			
			//Removing employee from Dragon Project array list
			employees.remove(bestEngineer);
			
			numberEngineers++;
		}//engineer while
		
		
		//Variable used as counter
		int numberTesters = 0;
		
		//while loop will itterate while not all testing engineers are found
		while(numberTesters != numTesters) {
			
			//Obtaining the index of the best employee
			int bestTester = findBest(employees, "Testing Engineer");
			
			//Adding employee to SpaceX team 
			spaceXTeam.add(employees.get(bestTester));
			
			//Removing employee from Dragon Project array list
			employees.remove(bestTester);
			
			numberTesters++;
		}//engineer while
		
		
		return spaceXTeam;
		
    }//buildteam
	
}//assignment3


//Designer interface
interface Designer{
	
	//Design method will be overidden 
	public int design();
	
}//Designer interface


//Developer interface
interface Developer{
	
	//develop method will be overidden 
	public int develop();
	
}//Developer interface


//Tester interface
interface Tester{
	
	//test method will be overidden 
	public int test();
	
}//Tester interface


//Manager interface
interface Manager{
	
	//manage method will be overidden 
	public int manage();
	
}//Manager interface


//employee abstract class
abstract class Employee{
	
	//instance variables 
	private String name;
	private String role;
	
	
	//setter for name
	public void setName(String name) {
		this.name = name;
	}
	
	
	//setter for role
	public void setRole(String role) {
		this.role = role;
	}
	
	
	//getter for name
	public String getName() {
		return name;
	}
	
	//Getter for role
	public String getRole() {
		return role;
	}
	
	//Duties method will be overidden 
	public abstract void duties();
	
}//employee class


//Software Engineer class
class SoftwareEngineer extends Employee implements Developer, Tester{
	
	//instance variables
	private int developAbility;
	private int testAbility;
	
	
	//constructor
	public SoftwareEngineer(String name, int developAbility, int testAbility) {
		
		this.developAbility = developAbility;
		this.testAbility = testAbility;
		
	}
	
	
	//overriden duties method
	@Override
	public void duties() {
		// TODO Auto-generated method stub
		System.out.println("I implement, test, and debug code on a daily basis.");
	}


	//overriden test method
	@Override
	public int test() {
		// TODO Auto-generated method stub
		
		return testAbility;
	}


	//overriden develop method
	@Override
	public int develop() {
		// TODO Auto-generated method stub
		
		return developAbility;
	}
	
}//SoftwareEngineer 



//QA Engineer class
class QAEngineer extends Employee implements Tester{
	
	
	//instance variables
	private int testAbility;
	
	
	//constructor
	public QAEngineer(String name, int testAbility) {
		this.testAbility = testAbility;
	}
	
	
	//overriden duties method
	@Override
	public void duties() {
		// TODO Auto-generated method stub
		System.out.println("I test code on a daily basis.");
	}


	//overriden test method
	@Override
	public int test() {
		// TODO Auto-generated method stub
		
		return testAbility;
		
	}
	
}//QA Engineer class



//architect class
class Architect extends Employee implements Designer{
	
	
	//instance variables
	private int designAbility;
	
	
	//constructor
	public Architect(String name, int designAbility) {
		this.designAbility = designAbility;
	}

	
	//overriden duties method
	@Override
	public void duties() {
		// TODO Auto-generated method stub
		System.out.println("I design systems and interconnections between systems.");
	}

	
	//overriden design method
	@Override
	public int design() {
		// TODO Auto-generated method stub
		
		return designAbility;
		
	}
	
}//Architect class


//team manager class 
class TeamManager extends Employee implements Manager{
	
	
	//instance variables
	private int manageAbility;
	
	
	//constructor
	public TeamManager(String name, int manageAbilty) {
		this.manageAbility = manageAbilty;
	}
	
	
	////overriden manage method
	@Override
	public int manage() {
		// TODO Auto-generated method stub
		
		return manageAbility;
		
	}
	
	
	//overriden duties method
	@Override
	public void duties() {
		// TODO Auto-generated method stub
		System.out.println("I manage a development team. It's like herding cats!");
	}
	
}//Team Manager class