import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph {

	private ArrayList<Node> cities;
	private ArrayList<Edge> paths;
	private ArrayList<Edge> tripsRequested;
	/**
	 * constructor for Graph
	 * @preconditions none
	 * @postconditions a new Graph has been created with empty ArrayLists for cities and paths
	 */
	public Graph(){
		this.cities = new ArrayList<Node>();
		this.paths = new ArrayList<Edge>();
		this.tripsRequested = new ArrayList<Edge>();
	}
	
	/**
	 * adds a city to the arrayList
	 * @param name is the name of the city
	 * @param transferTime is the time it takes to transfer in the city
	 * @preconditions that the graph exists
	 * @postconditions that there is a new node in the ArrayList of cities
	 */
	public void addCity(String name, int transferTime){
		Node n = new Node(name, transferTime);
		cities.add(n);
	}
	
	/**
	 * adds a path between two cities
	 * @param name1 is the name of the first city
	 * @param name2 is the name of the second city
	 * @param travelTime is the time it takes to travel between cities. the weight of the edge
	 * @preconditions that the graph exists, that the two nodes exist
	 * @postconditions that an edge is added between the two nodes, as well as the postconditions of the node addEdge method
	 */
	public void addPath(String name1, String name2, int travelTime){
		for(int i = 0; i<cities.size(); i++){
			if(cities.get(i).getName().equals(name1)){
				for(int j = 0; j<cities.size(); j++){
					if(cities.get(j).getName().equals(name2)){
						Edge newEdge = new Edge(cities.get(i), cities.get(j), travelTime);
						Edge newEdge2 = new Edge(cities.get(j), cities.get(i), travelTime);
						cities.get(i).AddEdge(newEdge);
						cities.get(j).AddEdge(newEdge2);
						paths.add(newEdge);
						paths.add(newEdge2);
						break;
					}
				}
				break;
			}
		}
	}
	
	/**
	 * The method for A* Search
	 * @param london is just a string with "london"
	 * @preconditions that the graph exists
	 * @postconditions that the trips have all been fulfilled 
	 */
	public void AStarSearch(String london){
		Node startNode = null;
		for(Node j : cities){
			if(j.getName().equals(london)) startNode = j;
		}
		State current = new State(startNode, 0, 0, null, null);
		State newState;
		current.setTrips(tripsRequested);;
		PriorityQueue<State> queue = new PriorityQueue<State>();
		queue.add(current);
		int numExpanded = 0;
		while(!current.getTripsNeeded().isEmpty()){
			current = queue.remove();
			numExpanded++;
			
			if(current.getTravelled()!=null){
				if(current.getTripsNeeded().contains(current.getTravelled())){
					current.removeTrip(current.getTravelled());
				}
			}
			
			for(Edge k : current.getCity().getSurround()){
				boolean flag = false;
				for(Edge m : tripsRequested){
					if(k.getTo()==m.getFrom()||k.getTo()==m.getTo()){
						flag = true;
						break;
					}
				}
				if(!flag) continue;
				newState = new State(k.getTo(), current.getTotalCost()+k.getTravelTime()+k.getTo().getTransferTime()+heuristic(current), heuristic(current), current, k);
				ArrayList<Edge> newNeeded = new ArrayList<Edge>();
				newNeeded.addAll(current.getTripsNeeded());
				newState.setTrips(newNeeded);
				if(!queue.contains(newState)) queue.add(newState);
			}
		}
		Stack<String> stack = new Stack<String>();
		int totalCost = current.getTotalCost()-current.getCity().getTransferTime();
		while(current!=null){
			stack.push((current.getCity().getName()));
			current = current.getPreviousState();
		}
		showTrip(totalCost, numExpanded, stack);	
	}
	
	/**
	 * my heuristic will simply add the required costs for all the trips still required at a current state. this reduced the nodes expanded for the sample input from ~6300 to 161 
	 * @param current is the current state
	 * @return this will return an int, hcost which is the heuristic cost
	 * @preconditions that the current state is correctly initiated
	 * @postconditions that an int has been returned
	 */
	public int heuristic(State current){
		int hCost = 0;
		for(Edge reqTrip : current.getTripsNeeded()){
			hCost = hCost+reqTrip.getTravelTime()+reqTrip.getTo().getTransferTime();
		}
		return hCost;
	}

	/**
	 * showTrip will show the trip. it is the output
	 * @param totalCost is the total cost
	 * @param nodesExpanded
	 * @param stack is the stack of trip names
	 */
	public void showTrip(int totalCost, int nodesExpanded, Stack<String> stack){
		System.out.println(nodesExpanded+" nodes expanded");
		System.out.println("cost = "+totalCost);
		String city1 = stack.pop();
		while(!stack.empty()){
			String city2 = stack.pop();
			System.out.println("Trip "+city1+" to "+city2);
			city1 = city2;
		}
	}

	/**
	 * this will add to the trips requested the edge for the trip
	 * @param name1 is the name for the first node
	 * @param name2 is the name for the second node
	 */
	public void addTripsRequested(String from, String to){
		for(Node n: cities){
			if(n.getName().equals(from)){
				for(Node j: cities){
					if(j.getName().equals(to)){
						for(Edge k: paths){
							if(k.getFrom().getName().equals(from)&&k.getTo().getName().equals(to)){
								tripsRequested.add(k);
								return;
							}
						}return;
					}
				}break;
			}
		}
	}

}
