package cs.b07.cscb07project.backEnd.users;

import java.util.ArrayList;
import java.util.List;

import cs.b07.cscb07project.backEnd.driver.FlightBookingApplication;
import cs.b07.cscb07project.backEnd.itinerary.FlightItinerary;
import cs.b07.cscb07project.backEnd.transportation.Flight;

/**
 * A User class that allows user with features like search flights and
 * Itineraries, display sorted itineraries by totalCost and travelTime, view all
 * client information, upload personal/billing information and flight
 * information.
 * 
 * @author Raphael Alviz, Zain Amir, Ian Ferguson, Andy Liang, Johnathan Tsoi
 */
public class Admin extends User {

	/**
	 * Serialization.
	 */
	private static final long serialVersionUID = -7396076393832964193L;
	private List<Flight> bookedFlights;
	private List<FlightItinerary> bookedFlightItineraries = new ArrayList<>();

	/**
	 * Creates a new Admin object with given user name and password.
	 * 
	 * @param userName
	 *            A String user name for the given user (must be unique).
	 */
	public Admin(String userName) {
		super(userName, true);
		bookedFlights = new ArrayList<Flight>();
		this.bookedFlightItineraries = bookedFlightItineraries;

	}

	public String getClientInfo(String userName) {
		return FlightBookingApplication.getUser(userName).toString();
	}

	public void addFlight(String flightNumber, String departureTime,
			String arrivalTime, String airline, String origin,
			String destination, double cost, int numSeats) {
		FlightBookingApplication.addFlight(flightNumber, departureTime,
				arrivalTime, airline, origin, destination, cost, numSeats);
	}

	public void editFlightAirline(String flightNumber, String airline) {
		Flight flight = FlightBookingApplication.getFlight(flightNumber);
		flight.setAirline(airline);
	}

	@Override
	public void bookFlight(Flight flight) {
		flight.increaseSeatsSold();
		getBookedFlights().add(flight);
	}

	@Override
	public void bookFlightItinerary(FlightItinerary itinerary) {
		for (Flight flight : itinerary.sequenceOfFlights()) {
			flight.increaseSeatsSold();
		}
		addBookedFlightItineraries(itinerary);
	}
	
	/**
	 * Allows for the administrator to book a flight for a client.
	 * @param flight
	 *          Fight to be booked.
	 * @param client
	 * 			Client in which administrator is booking flight for.
	 */
	public void bookClientFlight(Flight flight, Client client) {
		client.bookFlight(flight);
	}
	
	/**
	 * Allows for the administrator to book a flight itinerary for a client. 
	 * @param itinerary
	 *          Itinerary in which to be booked.
	 * @param client
	 * 			Client in which administrator is booking flight itinerary for.
	 */
	public void bookClientFlightItinerary(FlightItinerary itinerary, Client client) {
		client.bookFlightItinerary(itinerary);
	}

	/**
	 * @return the bookedFlights
	 */
	public List<Flight> getBookedFlights() {
		return bookedFlights;
	}

	/**
	 * @param flight to be added to booked flights.
	 */
	public void addBookedFlights(Flight flight) {
		bookedFlights.add(flight);
	}

	/**
	 * @return the bookedFlightItineraries
	 */
	public List<FlightItinerary> getBookedFlightItineraries() {
		return bookedFlightItineraries;
	}

	/**
	 * @param itinerary to be added too booked itineraries
	 */
	public void addBookedFlightItineraries(FlightItinerary itinerary) {
		bookedFlightItineraries.add(itinerary);
	}
	
}
