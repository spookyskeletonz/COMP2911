import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TripPlanner {
	
	private Graph map;
	
	/**
	 * constructor for TripPlanner
	 */
	public TripPlanner(){
		Graph map = new Graph();
		this.map = map;
		
	}
	
	/**
	 * main method for program. will initiate the search and run a readfile method
	 * @param args is the filename for input
	 * @preconditions that the filename has been given correctly
	 * @postconditions that the entire program has run correctly
	 */
	public static void main(String[] args) {
		TripPlanner t = new TripPlanner();
		t.readFile(args);
		t.map.AStarSearch("London");
	}
	
	/**
	 * readFile will read in the file and pass the methods as needed
	 * @param args is the filename
	 * @preconditions that the tripPLanner has been created properly and the filename has been given properly
	 * @postconditions that the file has been read and all the nodes and edges have been created, and trips needed added to the list
	 */
	public void readFile(String[] args){
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc.hasNext()) {
			String[] line = sc.nextLine().split(" ");
			int l = 0;
			if (line[l].equals("Transfer")) {
				l++;
				int transferTime = Integer.parseInt(line[l]);
				l++;
				String cityName = line[l];
				map.addCity(cityName, transferTime);
			} else if (line[l].equals("Time")){
				l++;
				int travelTime = Integer.parseInt(line[l]);
				l++;
				String name1 = line[l];
				l++;
				String name2 = line[l];
				map.addPath(name1, name2, travelTime);
			} else if (line[l].equals("Trip")){
				l++;
				String name1 = line[l];
				l++;
				String name2 = line[l];
				map.addTripsRequested(name1, name2);
			}
		}
		sc.close();
	}

}