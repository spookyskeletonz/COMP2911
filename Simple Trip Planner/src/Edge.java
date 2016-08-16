
public class Edge {

	private Node from;
	private Node to;
	private int travelTime;
	
	/**
	 * constructor for Edge
	 * @param node1
	 * @param node2
	 * @param travelTime
	 */
	public Edge(Node from, Node to, int travelTime){
		this.from = from;
		this.to = to;
		this.travelTime = travelTime;
	}
	
	/**
	 * getter for Node1
	 * @return returns node1
	 * @preconditions that the edge has been created
	 * @postconditions that the node1 has been returned
	 */
	public Node getFrom(){
		return from;
	}
	
	/**
	 * getter for Node2
	 * @return returns node2
	 * @preconditions that the edge has been created
	 * @postconditions that the node2 has been returned
	 */
	public Node getTo(){
		return to;
	}
	
	/**
	 * getter for travelTime
	 * @return returns the travel time
	 * @preconditions that the edge has been created
	 * @postconditions that the int for travel time in minutes for the edge has been returned
	 */
	public int getTravelTime(){
		return travelTime;
	}
	
}
