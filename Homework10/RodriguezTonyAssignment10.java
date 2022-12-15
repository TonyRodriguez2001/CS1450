/*
 *  Name: Tony Rodriguez
 *  
 *  Class: CS1450 (T/R)
 *  
 *  Due:  April, 28 2021 
 *  
 *  Description: Assignment #10
 *  
 *  Purpose: Learning to create and manipulate a binary tree.  
 *  
 *  Synopsis: This assignment provides the opportunity to implement 
 *  a binary search tree and to implement a level-order traversal 
 *  of the tree.  The tree will be filled with parrots and when a 
 *  level order traversal is performed the parrots will sing a song.
 *
 *   
*/

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RodriguezTonyAssignment10 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//Creating scanner object to read OlympicEvents.txt file
		File parrotsFile = new File("parrots.txt");
		Scanner parrots = new Scanner (parrotsFile);
				
	
		BinaryTree cookieTree = new BinaryTree();
		
		//PriorityQueue<Parrot> pQueue = new PriorityQueue<>();
		while(parrots.hasNext()) {
			
			//inserting parrot object to tree from the file
			cookieTree.insert(new Parrot(parrots.nextInt(), parrots.next(), parrots.next()));
			
			
			
		}//while
		
		
		System.out.println("\tParrots Song");
		System.out.println("-------------------------------");
		
		//traversing the tree in level order and printing song words
		cookieTree.levelOrder();
		
		System.out.println("\n\n\tParrots Names");
		System.out.println("-------------------------------");
		//visiting leaves to get names
		cookieTree.visitTreeLeaves();
		
		//closing parrots file
		parrots.close();
		
		
	}//main

}//Assignment 10\

class Parrot implements Comparable<Parrot>{
	
	//data fields
	private int id;
	private String name;
	private String songWord;
	
	
	//constructor
	public Parrot(int id, String name, String songWord) {
		
		this.id = id;
		this.name = name;
		this.songWord = songWord; 
		
	}//Parrot 
	
	public String getName() {
		return name;
	}//getName
	
	public String sing() {
		return songWord;
	}//sing

	@Override
	public int compareTo(Parrot otherParrot) {
		// TODO Auto-generated method stub
		
		if(id < otherParrot.id ) {
            return -1;
        }//if
		
        
        else if (id > otherParrot.id) {
            return 1;
        }//else if
        
		
        else {
            return 0;
        }//else
	
	}//class
	
}//parrot class



//binary tree class
class BinaryTree{
	
	private TreeNode root;
	
	//constructor
	public BinaryTree() {
		
		root = null;
		
	}//BinaryTree
	
	
	public boolean insert(Parrot parrotToAdd) {
		
		
		//inserts at root if there is no tree
		if(root == null) {
			
			root = new TreeNode(parrotToAdd);
			
		}//if
		
		
		else {
			
			TreeNode parent = null;
			TreeNode current = root;
			
			
			while(current != null) {
				
				//traversing to left if parrot is less
				if(parrotToAdd.compareTo(current.parrot) < 0) {
					
					parent = current;
					current = current.left;
					
				}//if
				
				
				//traversing to right if parrot is greater
				else if(parrotToAdd.compareTo(current.parrot) > 0) {
					
					parent = current;
					current = current.right;
					
				}//else if
				
				
				else {
					
					return false;
				}//else
				
			}//while
			
			
			//adding parrot as left child
			if(parrotToAdd.compareTo(parent.parrot) < 0) {
				
				parent.left = new TreeNode(parrotToAdd);
				
			}
			
			//adding parrot as right child
			else {
				
				parent.right = new TreeNode(parrotToAdd);
				
			}//else
			
			
		}//else
		
		return true;
		
	}//insert
	
	
	
	//performs a level order traversal of the binary tree
	public void levelOrder() {
		
		
		//if there is a tree
		if(root != null) {
			
			//queue will hold the nodes
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);
			
			
			while(!queue.isEmpty()) {
				
				TreeNode tempNode = queue.remove();
				
				System.out.printf(" %s ", tempNode.parrot.sing());
				
				//if tempNode has a left child
				if(tempNode.left != null) {
					queue.offer(tempNode.left);
				}
				
				//if tempNode has a right child
				if(tempNode.right != null) {
					queue.offer(tempNode.right);
				}
				
			}//while
			
			
		}//if
		
	}//levelOrder
	
	
	
	//visits all the leaves from left to right
	private void visitLeaves(TreeNode aNode ) {
		
		
		//Base case
		if(aNode == null) {
			return;
		}
		
		
		//checking if it is a leaf
		if(aNode.left == null && aNode.right == null) {
			System.out.printf("\n%s", aNode.parrot.getName());
		}
		
		//recursive calls
		visitLeaves(aNode.left);
		visitLeaves(aNode.right);
		
		
	}//visitLeaves
	
	
	//calls the recursive vistLeaves method
	public void visitTreeLeaves() {
		visitLeaves(root);
	}
	
	
	//Nodes used in binary tree
	private static class TreeNode{
		
		//left and right references 
		//and parrot object
		private Parrot parrot;
		private TreeNode left;
		private TreeNode right;
		
		public TreeNode(Parrot parrot) {
			this.parrot = parrot;
		}//parrot
		
	}//Tree Node
	
}//BinaryTree 
