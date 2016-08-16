import java.util.ArrayList;

public class Venue {
	private String name;
	private ArrayList<Room> rooms;
	/**
	 * constructor for Venue
	 * preconditions: that venueHiresystem has created the venue with newVenue method
	 * postconditions: There is a new venue
	 * @param name name is the name of the venue
	 * @param rooms rooms is an arrayList of the rooms in the venue
	 */
	public Venue(String name, ArrayList<Room> rooms){
		this.name = name;
		this.rooms = rooms;
	}
	
	/**
	 * addRoom will add a room to the venue with details matching params
	 * preconditions: that the venue exists
	 * postconditions: a new room has been added to the arrayList of rooms in Venue with details matching params
	 * @param name name is the name of the room to be added
	 * @param type type id the type of the room
	 */
	public void addRoom(String name, String type){
		Room e = new Room(this.name, name, type, rooms.size());
		rooms.add(e);
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Room> getRooms(){
		return rooms;
	}

}
