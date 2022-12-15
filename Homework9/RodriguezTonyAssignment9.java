/*
 *  Name: Tony Rodriguez
 *  
 *  Class: CS1450 (T/R)
 *  
 *  Due:  April, 22 2021 
 *  
 *  Description: Assignment #9
 *  
 *  Purpose: Learning to create and manipulate a linked list.  
 *  
 *  Synopsis: This assignment provides me with the opportunity to implement a single-linked list 
 *and a double-linked list with methods to add in sorted order, remove and 
 *replace, and print elements.
 *
 *   
*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RodriguezTonyAssignment9 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Creating scanner object to read OlympicEvents.txt file
		File olympicEventsFile = new File("OlympicEvents.txt");
		Scanner olympicEvents = new Scanner (olympicEventsFile);
		
		//Creating scanner object to read OlympicEventsReplace.txt file
		File olympicEventsReplaceFile = new File("OlympicEventsReplace.txt");
		Scanner olympicEventsReplace = new Scanner (olympicEventsReplaceFile);
		
		//Creating the doubly and singly linked lists
		EventLinkedList singleList = new EventLinkedList();
		DoubleLinkedList doubleList = new DoubleLinkedList();
		
		
		//reading info from OlympicEvents file 
		//to singly linked list
		while(olympicEvents.hasNext()) {
			
			//passing the temporary event made from info in file to singly linked list
			Event tempEvent = new Event(olympicEvents.next(), olympicEvents.next(), 
					olympicEvents.nextInt(), olympicEvents.nextLine());
			
			//adding the event in order
			singleList.addInOrder(tempEvent);
			
		}//while
		
		
		//printing the singly linked list
		System.out.println("Olympic Events in sorted order\n");
		
		System.out.println("Time\tType\t\t Event Name\t\tVenue");
		System.out.println("-----------------------------------"
				+ "-------------------------------------");
		singleList.printList();
		
		
		//reading the vents that will be replaced in the linked list 
		//from the olympic event replace file 
		while(olympicEventsReplace.hasNext()) {
			
			//creating temporary event that will be passed into the remove and replace method 
			Event tempEvent = new Event(olympicEventsReplace.next(), olympicEventsReplace.next(), 
					olympicEventsReplace.nextInt(), olympicEventsReplace.nextLine());
			
			//calling the remove anjd replace method on the singly linked list 
			singleList.removeAndReplaceEvent(tempEvent);
			
		}//while
		
		
		//printing the singly linked list with the replaced events 
		System.out.println("\n");
		System.out.println("Olympic Events Updated - Replaced Certain Events");
		System.out.println("\nTime\tType\t\t Event Name\t\tVenue");
		System.out.println("-----------------------------------"
				+ "-------------------------------------");
		singleList.printList();
		
		
		//building the doubly linked list from the singly linked list 
		doubleList.build(singleList);
		
		System.out.println("\n");
		
		
		///printing the doubly linked list backwards 
		System.out.println("Events in Doubly Linked List - Printed Backwards");
		System.out.println("\nTime\tType\t\t Event Name\t\tVenue");
		System.out.println("-----------------------------------"
				+ "-------------------------------------");
		
		doubleList.printListBackwards();
		
		
		//printing the size of the singly linked list after vuilding the doubly 
		System.out.printf("\n\nNumber of nodes in single linked list: %d", singleList.getSize());
		
		//closing the files
		olympicEvents.close();
		olympicEventsReplace.close();
		
		
		
	}//main

}//assignment9



//Event object class
class Event implements Comparable<Event>{
	
	
	//instance vars for event object
	private String venue;
	private String type;
	private int time;
	private String name;
	
	
	
	//event constructor
	public Event(String venue, String type, int time, String name) {
		
		
		//setting instance variables for event object
		//to incoming values 
		this.venue = venue;
		this.type = type;
		this.time = time;
		this.name = name;
		
	}//constructor
	
	
	public int getTime() {
		return time;
	}//getTime
	
	
	public String toString() {
		return String.format("%-4s\t%-10s\t%-20s\t%s", time, type, name, venue);
	}//toString
	
	
	//making the event objects comparable 
	//
	 public int compareTo(Event otherEvent) {
		 
	        if(time < otherEvent.getTime() ) {
	            return -1;
	        }
	        
	        else if (time > otherEvent.getTime()) {
	            return 1;
	        }
	        
	        else {
	            return 0;
	        }

	        
	    }//compareTo

	
}//Event



class EventLinkedList {
	
	
	private Node head;
	private Node tail;
	private int size;
	
	
	public EventLinkedList () {
		head = null; 
		tail = null;
		size = 0;
	}//Linked List constructor
	
	
	public int getSize() {
		return size;
	}
	
	
	public void addInOrder(Event eventToAdd) {
		
		Node previous = null;
		Node current = head;
		Node eventNode = new Node(eventToAdd);
		
		
		//if there is no list
		if(head == null) {
			head = tail = eventNode;
			size++;
		}
		
		else {
			
			boolean isLess = false;
			
			
			//While we havent reached the end of the list and 
			//the node to add is not less than the current node
			while(current != null && !isLess) {
				
				if(eventNode.event.compareTo(current.event) == -1) {
					
					isLess = true;
					
				}
				
				
				//traversing through the nodes 
				else {
					previous = current;
					current = current.next;
				}
				
			}//while
			
			
			
			if(isLess) {
				
				
				//if the first in the list
				if(previous == null) {
					
					eventNode.next = current;
				
					head = eventNode;
					
					size++;
					
				}
				
				
				//inserting the event node before the other 
				else {
					
					eventNode.next = current;
					previous.next = eventNode;
					size++;
					
				}//else
				
				
				
			}//if isLess
			
		}//else
		
		
		
	}//addInOrder
	
	
	//removes and replaces a node with same time
	public void removeAndReplaceEvent(Event replacementEvent) {
		
		
		Node previous = null;
		Node current = head;
		Node replaceNode = new Node(replacementEvent);
		
		
		boolean same = false;
		
		
		//while we havent reached the end of the list and the nodes are not the same 
		while(current != null && !same) {
			
			
			//if they are the same 
			if(current.event.compareTo(replacementEvent) == 0) {
				
				same = true;
				
			}
			
			
			//traversing through the nodes
			else {
				previous = current;
				current = current.next;
			}
			
		}//while
		
		
		//if they are the same 
		if(same) {
			
			
			//if its the first in the list 
			if(previous == null) {
				
				
				//connecting the node to the node in front of the current node
				replaceNode.next = current.next;
				
				//connecting the head to the inserted node 
				head = replaceNode;
				
			}
			
			else {
				
				//connecting the node to the node that is next of current
				//and connecting the node to node behind current
				replaceNode.next = current.next;
				previous.next = replaceNode;
				
			}
			
			
			
		}//if found
		
	}//removeAndReplace
	
	
	
	//removes an event from the list
	public Event removeEvent() {
		
		
		//nothing in the list
		if (size == 0) {
			return null;
		}
		
		
		else {
			
			//returning the node at the beginning of the list
			Node temp = head;
			head = head.next;
			size--;
			
			if(head == null) {
				tail = null;
				
			}
			
			
			return temp.event;
			
		}//else
		
	}//removeEvent
	
	
	
	public void printList() {
		
		
		Node current = head;
		
		//traversing through the list and calling the toString() method 
		//on the events in each node
		while(current != null) {
			
			System.out.println(current.event.toString());
			
			current = current.next;
			
		}
		
		
		
	}//printList
	
	
	//Node self referential class
	private static class Node {
		
		private Event event;
		private Node next;
		
		public Node (Event event) {
			
			this.event = event;
			next = null;
			
		}//constructor
	}//Node
		
}//LinkedList



//doubly linked list class
class DoubleLinkedList{
	
	private DoubleNode head;
	private DoubleNode tail;
	
	
	//constructor for doubly linked list
	public DoubleLinkedList() {
		
		head = null;
		tail = null;
		
	}//constructor
	
	
	//builds the doubly linked list from a singly linked list
	public void build (EventLinkedList eventList)  {
		
		
		//removes nodes from the singly linked list and uses them to build the doubly
		for(int i = 0; i < eventList.getSize() + 4; i++) {
			
			//tempnode will be used to insert the nodes
			DoubleNode tempNode = new DoubleNode(eventList.removeEvent());
			
			//if there is no list
			if(head == null) {
				
				head = tail = tempNode;
				
				
			}
			
			
			//adding the node to the end of the list
			else {
				
				
				tail.next = tempNode;
				DoubleNode node = tail;
				tail = tempNode;
				tail.previous = node;
			}
			
			
		}//for
		
	}//build
	
	
	//prints the doubly linked list backwards
	public void printListBackwards() {
		
		boolean isDone  = false;
		
		//while it has not reached the end of the list
		while(!isDone) {
			
			DoubleNode current = tail;
			
			//reached the end of list
			if(current == null) {
				
				isDone = true;
			}
			
			
			//traversing through the list from the end and 
			//calling the toString() method on each node
			else {
				
				current.previous = tail.previous;
				
				System.out.println(current.event.toString());
				
				tail = current.previous;
				
			}
			
			
		}
		
		
	}//Print List backwards
	
	
	
	//self referential double node class
	private static class DoubleNode {
		
		private Event event;
		private DoubleNode next;
		private DoubleNode previous;
		
		public DoubleNode (Event event) {
			
			this.event = event;
			next = null;
			previous = null;
			
		}//constructor
		
	}//doubleNode

}//DoubleLinkedList
