import java.util.ArrayList;
import java.util.Calendar;

public class Room implements Comparable<Room> {

	private String name;
	private String type;
	private String venName;
	private ArrayList<Calendar> bookDates;
	private boolean occup;
	private int roomNumber;

	public Room(String venName, String name, String type, int num) {
		this.venName = venName;
		this.name = name;
		this.type = type;
		occup = false;
		bookDates = new ArrayList<Calendar>();
		this.roomNumber = num;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int num) {
		this.roomNumber = num;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getVenName() {
		return venName;
	}

	public ArrayList<Calendar> getBookDates() {
		return bookDates;
	}

	/**
	 * addBookDates adds the required book dates to a room preconditions: that
	 * the room exists postconditions: that the bookdates have been added
	 * correctly
	 * 
	 * @param start
	 *            is the calendar start of the reservation to be added
	 * @param end
	 *            is the calendar end of the reservation
	 */
	public void addBookDates(Calendar start, Calendar end) {
		if (bookDates.size() == 0) {
			bookDates.add(start);
			bookDates.add(end);
			return;
		}
		Calendar start2 = bookDates.get(0);
		if (start.before(start2)) {
			bookDates.add(0, start);
			bookDates.add(1, end);
			return;
		}
		Calendar end2 = bookDates.get(bookDates.size() - 1);
		if (start.get(Calendar.DAY_OF_YEAR) > end2.get(Calendar.DAY_OF_YEAR)) {
			bookDates.add(start);
			bookDates.add(end);
			return;
		}
		for (int count = 0; count < bookDates.size() - 2; count = count + 2) {
			Calendar readEnd = bookDates.get(count + 1);
			Calendar readStartNext = bookDates.get(count + 2);
			if (start.get(Calendar.DAY_OF_YEAR) > readEnd.get(Calendar.DAY_OF_YEAR)
					&& end.get(Calendar.DAY_OF_YEAR) < readStartNext.get(Calendar.DAY_OF_YEAR)) {
				bookDates.add(count + 2, start);
				bookDates.add(count + 3, end);
				return;

			}
		}
	}

	/**
	 * sortDates will sort the book dates of the room preconditions: that the
	 * room exists postconditions: that the book dates have all been sorted in
	 * ascending order
	 */
	public void sortDates() {
		for (int count1 = 0; count1 < bookDates.size(); count1 = count1 + 2) {
			for (int count2 = 2; count2 < bookDates.size() - count1; count2 = count2 + 2) {
				if (bookDates.get(count2).before(bookDates.get(count2 - 2))) {
					Calendar temp = bookDates.get(count2);
					Calendar temp2 = bookDates.get(count2 + 1);
					bookDates.set(count2, bookDates.get(count2 - 2));
					bookDates.set(count2 + 1, bookDates.get(count2 - 1));
					bookDates.set(count2 - 2, temp);
					bookDates.set(count2 - 1, temp2);
				}
			}
		}
	}

	public void setOccup(boolean occup) {
		this.occup = occup;
	}

	public boolean getOccup() {
		return occup;
	}

	@Override
	public int compareTo(Room compareRoom) {
		int compareNum = ((Room) compareRoom).getRoomNumber();
		return this.roomNumber - compareNum;
	}

}