import java.util.ArrayList;

public class State implements Comparable<State>{

	private Node city;
	private int totalCost;
	private State previousState;
	private ArrayList<Edge> tripsNeeded;
	private Edge travelled;
	private int hCost;
	
	/**
	 * constructor for State
	 * @param name name is the name of the node. this is from the super
	 * @param transferTime is the time it takes to transfer at the node. this is from the super
	 * @param totalCost total cost is the total cost that it has taken to get to this node
	 * @param previousState is the previous state that was opened to get to this state
	 */
	public State(Node city, int totalCost, int hCost, State previousState, Edge travelled) {
		this.city = city;
		this.totalCost = totalCost;
		this.hCost = hCost;
		this.previousState = previousState;
		this.travelled = travelled;
	}
	
	/**
	 * getter for hCost
	 * @return the hCost
	 */
	public int gethCost(){
		return hCost;
	}
	
	/**
	 * getter for the Edge travelled on to get to this State
	 * @return the edge
	 */
	public Edge getTravelled(){
		return travelled;
	}
	
	/**
	 * setter for tripsNeeded
	 * @param tripsNeeded
	 */
	public void setTrips(ArrayList<Edge> tripsNeeded){
		this.tripsNeeded = tripsNeeded;
	}
	
	/**
	 * adds a trip to the trips needed
	 * @param trip is the trip to be added
	 */
	public void addTrip(Edge trip){
		tripsNeeded.add(trip);
	}
	
	/**
	 * removes a trip from the trips needed
	 * @param trip is the trip to be removed
	 */
	public void removeTrip(Edge trip){
		tripsNeeded.remove(trip);
	}
	
	/**
	 * getter for trips needed
	 * @return arrayList of edges fro trips needed
	 */
	public ArrayList<Edge> getTripsNeeded(){
		return tripsNeeded;
	}
	/**
	 * setter for totalCost
	 * @param totalCost
	 * @preconditions that the State exists and that a valid int is given
	 * @postconditions that the total cost has been updated to the new total cost
	 */
	public void setTotalCost(int totalCost){
		this.totalCost = totalCost;
	}
	
	/**
	 * getter for totalCost
	 * @returns totalcost
	 * @preconditions that the state exists
	 * @postconditions that the totalcost is returned
	 */
	public int getTotalCost(){
		return totalCost-hCost;
	}
	
	/**
	 * setter for the previous state
	 * @param previousState
	 * @preconditions that the State exists and a valid state is given
	 * @postconditions that a previousState is now set
	 */
	public void setPreviousState(State previousState){
		this.previousState = previousState;
	}
	
	
	/**
	 * getter for previousState
	 * @return the previous State
	 * @preconditions that the State exists and that previous State has been given correctly
	 * @postconditions that the previous State is returned
	 */
	public State getPreviousState(){
		return previousState;
	}
	
	public Node getCity(){
		return city;
	}
	
	/**
	 * this is for comparing States
	 * @params o o is the other State being compared with this one
	 * @return an integer revealing the comparison result
	 * @preconditions that both states exist
	 * @postconditions that an integer has returned as a result of the comparison
	 */
	@Override
	public int compareTo(State o) {
		if(this.equals(o)){
			return 0;
		} else if(this.totalCost > o.totalCost){
			return 1;
		} else {
			return -1;
		}
		
	}

	
}
