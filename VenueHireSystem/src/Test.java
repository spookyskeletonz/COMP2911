import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

public class Test {

	@org.junit.Test
	public void tests() {
		//testing one room in one venue and one request for that room
		VenueHireSystem test = new VenueHireSystem();
		test.newVenue("one", "roomOne", "small");
		ArrayList<String> req = new ArrayList<String>();
		req.add("1");
		req.add("small");
		Calendar strt = Calendar.getInstance();
		strt.set(2016, 3, 28);
		Calendar nd = Calendar.getInstance();
		nd.set(2016, 3, 30);
		test.makeReq(1, req, strt, nd);
		ArrayList<Request> requests = test.getRequests();
		assertEquals(requests.size(), 1);
		assertEquals(requests.get(0).getId(), 1);
		assertEquals(requests.get(0).getRoomReqs(), req);
		assertEquals(requests.get(0).getStart(), strt);
		assertEquals(requests.get(0).getEnd(), nd);
		assertEquals(strt, test.getVenues().get(0).getRooms().get(0).getBookDates().get(0));
		assertEquals(nd, test.getVenues().get(0).getRooms().get(0).getBookDates().get(1));
		System.out.println("test1 passed");
		
		//testing two rooms in one venue and one request for both rooms
		test = new VenueHireSystem();
		test.newVenue("one", "roomOne", "small");
		test.addRoom("one", "roomTwo", "small");
		req = new ArrayList<String>();
		req.add("2");
		req.add("small");
		test.makeReq(1, req, strt, nd);
		requests = test.getRequests();
		assertEquals(requests.size(), 1);
		assertEquals(requests.get(0).getId(), 1);
		assertEquals(requests.get(0).getRoomReqs(), req);
		assertEquals(requests.get(0).getStart(), strt);
		assertEquals(requests.get(0).getEnd(), nd);
		assertEquals(strt, test.getVenues().get(0).getRooms().get(0).getBookDates().get(0));
		assertEquals(nd, test.getVenues().get(0).getRooms().get(0).getBookDates().get(1));
		assertEquals(strt, test.getVenues().get(0).getRooms().get(1).getBookDates().get(0));
		assertEquals(nd, test.getVenues().get(0).getRooms().get(1).getBookDates().get(1));
		System.out.println("test2 passed");
		
		//testing one request which cannot be fulfilled
		test = new VenueHireSystem();
		test.newVenue("one", "roomOne", "small");
		req = new ArrayList<String>();
		req.add("1");
		req.add("large");
		test.makeReq(1, req, strt, nd);
		requests = test.getRequests();
		assertEquals(requests.size(), 0);
		assertEquals(test.getVenues().get(0).getRooms().get(0).getBookDates(), new ArrayList<Calendar>());
		System.out.println("test3 passed");
		
		//testing request from two different venues
		test = new VenueHireSystem();
		test.newVenue("one", "roomOne", "small");
		test.newVenue("two", "roomOne", "large");
		req = new ArrayList<String>();
		req.add("1");
		req.add("small");
		req.add("1");
		req.add("large");
		test.makeReq(1, req, strt, nd);
		requests = test.getRequests();
		assertEquals(requests.size(), 0);
		System.out.println("test4 passed");
		
		//testing request for 2 different types of room
		test = new VenueHireSystem();
		test.newVenue("one", "roomOne", "small");
		test.addRoom("one", "roomTwo", "large");
		req = new ArrayList<String>();
		req.add("1");
		req.add("small");
		req.add("1");
		req.add("large");
		test.makeReq(1, req, strt, nd);
		requests = test.getRequests();
		assertEquals(requests.size(), 1);
		assertEquals(requests.get(0).getId(), 1);
		assertEquals(requests.get(0).getRoomReqs(), req);
		assertEquals(requests.get(0).getStart(), strt);
		assertEquals(requests.get(0).getEnd(), nd);
		assertEquals(strt, test.getVenues().get(0).getRooms().get(0).getBookDates().get(0));
		assertEquals(nd, test.getVenues().get(0).getRooms().get(0).getBookDates().get(1));
		assertEquals(strt, test.getVenues().get(0).getRooms().get(1).getBookDates().get(0));
		assertEquals(nd, test.getVenues().get(0).getRooms().get(1).getBookDates().get(1));
		System.out.println("test5 passed");
		
		//testing changing request
		req = new ArrayList<String>();
		req.add("1");
		req.add("large");
		test.changeRequest(1, req, strt, nd);
		assertEquals(new ArrayList<Calendar>(), test.getVenues().get(0).getRooms().get(0).getBookDates());
		assertEquals(strt, test.getVenues().get(0).getRooms().get(1).getBookDates().get(0));
		assertEquals(nd, test.getVenues().get(0).getRooms().get(1).getBookDates().get(1));
		System.out.println("test6 passed");
		
		//testing cancelling request
		test.cancelRequest(1);
		assertEquals(requests.size(), 0);
		assertEquals(new ArrayList<Calendar>(), test.getVenues().get(0).getRooms().get(0).getBookDates());
		assertEquals(new ArrayList<Calendar>(), test.getVenues().get(0).getRooms().get(1).getBookDates());
		System.out.println("test7 passed");
		
		//testing cancelling request which does not exist
		test.cancelRequest(5);
		System.out.println("If the above line says Cancel rejected -> test8 passed");
		
	}

}
