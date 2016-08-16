import java.util.ArrayList;
import java.util.Calendar;

public class Request {

	private int id;
	private ArrayList<String> roomReqs;
	private Calendar start;
	private Calendar end;
	private ArrayList<Room> roomsUsed;
	private String venName;

	/**
	 * Constructor for request preconditions: that venueHireSystem has been
	 * initiated
	 * 
	 * @param id
	 *            id is the is of the request
	 * @param roomReqs
	 *            roomReqs this is an arraylist of all the requirements needed
	 *            in the request, including both type and number of rooms
	 * @param start
	 *            start is the start date for the reservation
	 * @param end
	 *            end is the end date for the reservation
	 * @param roomsUsed
	 *            roomsUsed is the rooms that are used in the request. it is
	 *            filled after the request has been confirmed possible
	 * @param venName
	 *            is the venue name of the rooms that are used in the request
	 */
	public Request(int id, ArrayList<String> roomReqs, Calendar start, Calendar end) {
		this.id = id;
		this.roomReqs = roomReqs;
		this.start = start;
		this.end = end;
		roomsUsed = new ArrayList<Room>();
	}

	public int getId() {
		return id;
	}

	public ArrayList<String> getRoomReqs() {
		return roomReqs;
	}

	public void setRoomReqs(ArrayList<String> roomReqs) {
		this.roomReqs = roomReqs;
	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getEnd() {
		return end;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}

	public ArrayList<Room> getRoomsUsed() {
		return roomsUsed;
	}

	public String getVenName() {
		return venName;
	}

	/**
	 * confirmPoss confirms that a request is possible and if it is it will add
	 * the bookdates to the rooms that the request will use preconditions: that
	 * the request exists postconditions: this will either return true and
	 * confirm the booking dates for the rooms found, or it will return false
	 * 
	 * @param venues
	 *            is the ArrayList of venues
	 * @return will return a boolean whether it can make the reservation or not
	 */
	public boolean confirmPoss(ArrayList<Venue> venues) {
		int totalRoomCount = 0;
		for (int preCount = 0; preCount < roomReqs.size(); preCount = preCount + 2) {
			totalRoomCount = totalRoomCount + Integer.parseInt(roomReqs.get(preCount));
		}
		ArrayList<Room> selectedRooms = new ArrayList<Room>();
		for (int count = 0; count < venues.size(); count++) {
			Venue v = venues.get(count);
			ArrayList<Room> Room = v.getRooms();
			int CnumRooms = 0;
			int CtypeRooms = 1;
			while (CtypeRooms < roomReqs.size()) {
				int numRooms = Integer.parseInt(roomReqs.get(CnumRooms));
				String typeRooms = roomReqs.get(CtypeRooms);
				int checkCounter = 0;
				int regCounter = 0;
				while (checkCounter < numRooms && regCounter < Room.size()) {
					Room r = Room.get(regCounter);
					if (r.getType().equals(typeRooms) && !selectedRooms.contains(r)) {
						if (r.getBookDates().size() == 0) {
							selectedRooms.add(r);
							checkCounter++;
						} else {
							boolean startCheck = true;
							boolean endCheck = true;
							for (int dateCount = 0; dateCount < r.getBookDates().size() - 1; dateCount = dateCount
									+ 2) {
								Calendar rStart = r.getBookDates().get(dateCount);
								Calendar rEnd = r.getBookDates().get(dateCount + 1);
								if (start.get(Calendar.DAY_OF_YEAR) >= rStart.get(Calendar.DAY_OF_YEAR)
										&& start.get(Calendar.DAY_OF_YEAR) <= rEnd.get(Calendar.DAY_OF_YEAR))
									startCheck = false;
								if (end.get(Calendar.DAY_OF_YEAR) <= rEnd.get(Calendar.DAY_OF_YEAR)
										&& end.get(Calendar.DAY_OF_YEAR) >= rStart.get(Calendar.DAY_OF_YEAR))
									endCheck = false;
								if (start.get(Calendar.DAY_OF_YEAR) <= rStart.get(Calendar.DAY_OF_YEAR)
										&& end.get(Calendar.DAY_OF_YEAR) >= rEnd.get(Calendar.DAY_OF_YEAR)) {
									startCheck = false;
									endCheck = false;
								}
							}
							if (startCheck && endCheck) {
								selectedRooms.add(r);
								checkCounter++;
							}
						}
					}
					regCounter++;
				}
				if (checkCounter == numRooms) {
					CnumRooms = CnumRooms + 2;
					CtypeRooms = CtypeRooms + 2;
				} else {
					selectedRooms.clear();
					break;
				}
			}
			if (selectedRooms.size() == totalRoomCount) {
				for (int finalCount = 0; finalCount < selectedRooms.size(); finalCount++) {
					roomsUsed.add(selectedRooms.get(finalCount));
					selectedRooms.get(finalCount).addBookDates(start, end);
					selectedRooms.get(finalCount).setOccup(true);
					venName = selectedRooms.get(finalCount).getVenName();
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * this will attempt to change the request to the new parameters given
	 * preconditions: that this request exists postconditions: that either the
	 * change has been made with data matching params and it returns true OR no
	 * change has been made and returns false
	 * 
	 * @param roomReqs
	 *            roomReqs is the requirements of the rooms needed in an
	 *            ArrayList of strings
	 * @param start
	 *            start is the new calendar start of the reservation
	 * @param end
	 *            end is the new calendar end of the reservation
	 * @param venues
	 *            is the arraylist of venues in the system
	 * @return will return a boolean whether it can change or not
	 */
	public boolean changeRequest(ArrayList<String> roomReqs, Calendar start, Calendar end, ArrayList<Venue> venues) {
		Request r = new Request(-1, roomReqs, start, end);
		for (int x = 0; x < this.roomsUsed.size(); x++) {
			this.roomsUsed.get(x).setOccup(false);
			this.roomsUsed.get(x).getBookDates().remove(this.start);
			this.roomsUsed.get(x).getBookDates().remove(this.end);
		}
		if (r.confirmPoss(venues)) {
			this.end = end;
			this.roomReqs = roomReqs;
			this.start = start;
			this.roomsUsed = r.roomsUsed;
			this.venName = roomsUsed.get(0).getVenName();
			r = null;
			return true;
		} else {
			for (int x = 0; x < this.roomsUsed.size(); x++) {
				this.roomsUsed.get(x).setOccup(true);
				this.roomsUsed.get(x).addBookDates(this.start, this.end);
			}
			return false;
		}

	}

	/**
	 * cancel will cancel this reservation preconditions: that this reservation
	 * exists postconditions: that all the rooms used in this reservation have
	 * these book dates removed and set occup to false if not occupied anymore
	 */
	public void cancel() {
		for (int x = 0; x < roomsUsed.size(); x++) {
			roomsUsed.get(x).getBookDates().remove(start);
			roomsUsed.get(x).getBookDates().remove(end);
			if (roomsUsed.get(x).getBookDates().size() == 0) {
				roomsUsed.get(x).setOccup(false);
			}
		}
	}

}
