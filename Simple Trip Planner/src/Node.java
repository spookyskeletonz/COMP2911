import java.util.ArrayList;

public class Node {
	
	private ArrayList<Edge> surround;
	private String name;
	private int transferTime;
	
	/**
	 * Constructor for Node class
	 * @param name name is the name of the node
	 * @param transferTime transferTime is the time for transfer at the node
	 * @preconditions conditions are that the name and transfer time are given
	 * @postconditions conditions are that the node is created with correct name and transfer time
	 */
	public Node(String name, int transferTime){
		this.transferTime = transferTime;
		this.name = name;
		this.surround = new ArrayList<Edge>();
		
	}
	
	/**
	 * getter for name of node
	 * @return a string of the name of node
	 * @preconditions that the node is created
	 * @postconditions that the name has been returned as a string
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * getter for the surrounding edges of the node
	 * @return an ArrayList of the surrounding edges
	 * @preconditions that the node is created and has has an ArrayList of edges
	 * @postconditions that the ArrayList named surround of edges has been returned
	 */
	public ArrayList<Edge> getSurround(){
		return this.surround;
	}
	
	/**
	 * getter for the transfer time of the node
	 * @return an int giving the time in minutes that it takes to transfer at the node
	 * @preconditions that the node has been created and has an int storing the transfer time
	 * @postconditions that the int for transfer time has been returned
	 */
	public int getTransferTime(){
		return this.transferTime;
	}
	
	/**
	 *Adds an edge to the ArrayList surround for the node
	 * @param newEdge newEdge is the edge given to be added
	 * @preconditions that the edge is given, that the node has been created, that there is an ArrayList to store the edge
	 * @postconditions that the ArrayList surround has had the edge added to it, or nothing happens if the edge is already in the ArrayList
	 */
	public void AddEdge(Edge newEdge){
		if(surround.contains(newEdge)){
			return;
		} else {
			surround.add(newEdge);
		}
	}
	
	/**
	 * Removes an edge from the ArrayList surround
	 * @param removeEdge is the edge to be removed from the array
	 * @preconditions that the node exists, that the edge to be removed has been given, that there is an ArrayList from which to remove the node
	 * @postconditions that the node has been remove, or nothing happens if the edge is not in the ArrayList
	 */
	public void RemoveEdge(Edge removeEdge){
		if(surround.contains(removeEdge)){
			surround.remove(removeEdge);
		}
	}
	
}
