import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

public class VenueHireSystem {

	private ArrayList<Venue> venues;
	private ArrayList<Request> requests;

	public VenueHireSystem() {
		venues = new ArrayList<Venue>();
		requests = new ArrayList<Request>();
	}

	public static void main(String[] args) {
		VenueHireSystem v = new VenueHireSystem();
		v.readFile(args);

	}

	public ArrayList<Venue> getVenues() {
		return venues;
	}

	public ArrayList<Request> getRequests() {
		return requests;
	}

	/**
	 * newVenue creates a new venue and adds it to the arrayList of venues
	 * preconditions: venueHireSystem has been initiated. postconditions: a new
	 * venue with matching properties given in params exists in the arrayList of
	 * venues
	 * 
	 * @param name
	 *            name is the name of the venue
	 * @param roomName
	 *            roomName is the roomName of the initial room being placed in
	 *            the venue
	 * @param type
	 *            type is the type of the initial room being placed in the venue
	 */
	public void newVenue(String name, String roomName, String type) {
		ArrayList<Room> rooms = new ArrayList<Room>();
		Venue ven = new Venue(name, rooms);
		venues.add(ven);
		addRoom(name, roomName, type);
	}

	/**
	 * addRoom adds a room to the arrayList of rooms in a venue preconditions:
	 * that venueHireSystem has been initiated postconditions:either a room with
	 * name and type matching params has been added the the rooms arrayList of
	 * the venue with name matching params OR we get the message shown
	 * 
	 * @param venName
	 *            venName gives the name of the venue that will add the room
	 * @param name
	 *            name is the name of the room
	 * @param type
	 *            type is the type of the room being added
	 */
	public void addRoom(String venName, String name, String type) {
		for (int count = 0; count < venues.size(); count++) {
			if (venues.get(count).getName().equals(venName)) {
				Venue ven = venues.get(count);
				ven.addRoom(name, type);
				return;
			}
		}
		System.out.println("Unable to find Venue " + venName);
	}

	/**
	 * makeReq makes a request for a reservation preconditions: venueHireSystem
	 * has been initiated. that valid input for room requirements has been given
	 * postconditions: either a new request has been added to the requests
	 * array, and the rooms used have been given the start and end date of a new
	 * reservation OR we get the message request Rejected
	 * 
	 * @param id
	 *            id is the id of the reservation
	 * @param roomReqs
	 *            roomReqs is an arrayList of the room requirements
	 * @param start
	 *            start is the calendar start of the reservation
	 * @param end
	 *            end is the calendar end of the reservation
	 */
	public void makeReq(int id, ArrayList<String> roomReqs, Calendar start, Calendar end) {
		Request r = new Request(id, roomReqs, start, end);
		if (r.confirmPoss(venues)) {
			requests.add(r);
			System.out.print("Reservation " + id + " " + r.getVenName());
			Collections.sort(r.getRoomsUsed());
			for (int x = 0; x < r.getRoomsUsed().size(); x++) {
				System.out.print(" " + r.getRoomsUsed().get(x).getName());
			}
			System.out.println();
			return;
		} else
			r = null;
		System.out.println("Request rejected");
	}

	/**
	 * changeRequest will try to change a request preconditions: venueHireSystem
	 * has been initiated postconditions: that either a request with id matching
	 * param will be changed to a request matching given params OR we get the
	 * message change Rejected
	 * 
	 * @param id
	 *            id is the id of the request to be changed
	 * @param roomReqs
	 *            roomReqs is an ArrayList of String for the room requirements
	 * @param start
	 *            start is the new calendar start of the request
	 * @param end
	 *            end is the new calendar end of the request
	 */
	public void changeRequest(int id, ArrayList<String> roomReqs, Calendar start, Calendar end) {
		for (int x = 0; x < requests.size(); x++) {
			if (requests.get(x).getId() == id) {
				if (requests.get(x).changeRequest(roomReqs, start, end, venues)) {
					System.out.print("Change " + id + " " + requests.get(x).getVenName());
					Collections.sort(requests.get(x).getRoomsUsed());
					for (int y = 0; y < requests.get(x).getRoomsUsed().size(); y++) {
						System.out.print(" " + requests.get(x).getRoomsUsed().get(y).getName());
					}
					System.out.println();
					return;
				} else {
					break;
				}
			}
		}
		System.out.println("Change rejected");
	}

	/**
	 * cancelRequest will attempt to cancel a request with id matching param
	 * preconditions: venueHireSystem has been initiated postconditions: either
	 * a request has been removed from the arrayList of requests OR we get the
	 * message Cancel Rejected
	 * 
	 * @param id
	 *            id is the id of the request to be rejected
	 */
	public void cancelRequest(int id) {
		for (int x = 0; x < requests.size(); x++) {
			if (requests.get(x).getId() == id) {
				requests.get(x).cancel();
				requests.remove(x);
				System.out.println("Cancel " + id);
				return;
			}
		}
		System.out.println("Cancel rejected");
	}

	private int parseMonth(String month) {
		if (month.equals("Jan"))
			return Calendar.JANUARY;
		if (month.equals("Feb"))
			return Calendar.FEBRUARY;
		if (month.equals("Mar"))
			return Calendar.MARCH;
		if (month.equals("Apr"))
			return Calendar.APRIL;
		if (month.equals("May"))
			return Calendar.MAY;
		if (month.equals("Jun"))
			return Calendar.JUNE;
		if (month.equals("Jul"))
			return Calendar.JULY;
		if (month.equals("Aug"))
			return Calendar.AUGUST;
		if (month.equals("Sep"))
			return Calendar.SEPTEMBER;
		if (month.equals("Oct"))
			return Calendar.OCTOBER;
		if (month.equals("Nov"))
			return Calendar.NOVEMBER;
		if (month.equals("Dec"))
			return Calendar.DECEMBER;
		else
			return -1;
	}

	private String revParseMonth(int month) {
		if (month == Calendar.JANUARY)
			return "Jan";
		if (month == Calendar.FEBRUARY)
			return "Feb";
		if (month == Calendar.MARCH)
			return "Mar";
		if (month == Calendar.APRIL)
			return "Apr";
		if (month == Calendar.MAY)
			return "May";
		if (month == Calendar.JUNE)
			return "Jun";
		if (month == Calendar.JULY)
			return "Jul";
		if (month == Calendar.AUGUST)
			return "Aug";
		if (month == Calendar.SEPTEMBER)
			return "Sep";
		if (month == Calendar.OCTOBER)
			return "Oct";
		if (month == Calendar.NOVEMBER)
			return "Nov";
		if (month == Calendar.DECEMBER)
			return "Dec";
		else
			return null;
	}

	/**
	 * readFile is the method that will read input from a textfile matching the
	 * command line arguments preconditions: venueHireSystem has been initiated,
	 * a file has been given in the command line arguments
	 * 
	 * @param args
	 *            args is the argument given in the command line
	 */
	public void readFile(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc.hasNext()) {
			String[] line = sc.nextLine().split(" ");
			int l = 0;
			if (line[l].equals("Venue")) {
				l++;
				String name = (line[l]);
				l++;
				String roomName = line[l];
				l++;
				String type = line[l];
				if (getVenues().size() == 0) {
					newVenue(name, roomName, type);
				} else {
					for (int count = 0; count < getVenues().size(); count++) {
						if (getVenues().get(count).getName().equals(name)) {
							addRoom(name, roomName, type);
							break;
						} else if (count == getVenues().size() - 1) {
							newVenue(name, roomName, type);
							break;
						}
					}
				}
			} else if (line[l].equals("Request")) {
				l++;
				int id = Integer.parseInt(line[l]);
				l++;
				int month1 = parseMonth(line[l]);
				l++;
				if (month1 == -1)
					return;
				int day1 = Integer.parseInt(line[l]);
				l++;
				int month2 = parseMonth(line[l]);
				if (month2 == -1)
					return;
				l++;
				int day2 = Integer.parseInt(line[l]);
				l++;
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				start.set(Calendar.MONTH, month1);
				start.set(Calendar.DAY_OF_MONTH, day1);
				end.set(Calendar.MONTH, month2);
				end.set(Calendar.DAY_OF_MONTH, day2);
				ArrayList<String> reqs = new ArrayList<String>();
				while (l < line.length) {
					reqs.add(line[l]);
					l++;
				}
				makeReq(id, reqs, start, end);
			} else if (line[l].equals("Change")) {
				l++;
				int id = Integer.parseInt(line[l]);
				l++;
				int month1 = parseMonth(line[l]);
				l++;
				if (month1 == -1)
					return;
				int day1 = Integer.parseInt(line[l]);
				l++;
				int month2 = parseMonth(line[l]);
				if (month2 == -1)
					return;
				l++;
				int day2 = Integer.parseInt(line[l]);
				l++;
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				start.set(Calendar.MONTH, month1);
				start.set(Calendar.DAY_OF_MONTH, day1);
				end.set(Calendar.MONTH, month2);
				end.set(Calendar.DAY_OF_MONTH, day2);
				ArrayList<String> reqs = new ArrayList<String>();
				while (l < line.length) {
					reqs.add(line[l]);
					l++;
				}
				changeRequest(id, reqs, start, end);
			} else if (line[l].equals("Cancel")) {
				l++;
				int id = Integer.parseInt(line[l]);
				cancelRequest(id);
			} else if (line[l].equals("Print")) {
				l++;
				String venName = line[l];
				printVenue(venName);
			}
		}
	}

	/**
	 * printVenue will print all the rooms of a given venue, including
	 * reservation dates if there are any in order preconditions:
	 * venueHireSystem has been initiated, a venue with name matching param
	 * exists postconditions: the venue has been printed to completion
	 * 
	 * @param venName
	 *            venName is the name of the venue to printed
	 */
	public void printVenue(String venName) {
		int var = 0;
		for (int count = 0; count < venues.size(); count++) {
			if (venues.get(count).getName().equals(venName)) {
				var = count;
				break;
			}
		}
		Venue ven = venues.get(var);
		for (int rCount = 0; rCount < ven.getRooms().size(); rCount++) {
			System.out.print(ven.getName() + " ");
			Room r = ven.getRooms().get(rCount);
			r.sortDates();
			System.out.print(r.getName() + " ");
			for (int dCount = 0; dCount + 1 < r.getBookDates().size(); dCount = dCount + 2) {
				Calendar start = r.getBookDates().get(dCount);
				Calendar end = r.getBookDates().get(dCount + 1);
				System.out.print(revParseMonth(start.get(Calendar.MONTH)) + " ");
				System.out.print(start.get(Calendar.DAY_OF_MONTH) + " ");
				System.out.print(end.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR) + 1);
				if (end != r.getBookDates().get(r.getBookDates().size() - 1))
					System.out.print(" ");
			}
			System.out.println();
		}

	}
}
