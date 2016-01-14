package driver;

import application.ItineraryGenerator;
import application.ItinerarySort;
import databases.ClientDatabase;
import databases.FlightDatabase;
import databases.NoSuchEmailException;
import databases.NoSuchFlightException;
import io.LoadDataFromFile;
import itinerary.FlightItinerary;
import transportation.Flight;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that implements the basic functionality of the Flight Booking Application.
 * 1. At creation it initializes the instance variables used to store the
 *     current state of the Flight Booking Application.
 * 2. Allows user classes to access methods and data structures related to Flight and
 *     FlightItinerary objects.
 * @author Raphael Alviz, Zain Amir, Ian Ferguson, Andy Liang, Johnathan Tsoi
 */
public class FlightBookingApplication {
  
  private static FlightDatabase flightDatabase = new FlightDatabase();
  
  private static ClientDatabase clientDatabase = new ClientDatabase();
  
  private static final Logger fLogger =
      Logger.getLogger(FlightBookingApplication.class.getPackage().getName());
  
  /**
   * Uploads client information to the clientDatabase from the file at the given path.
   * @param filePath A String giving the path to an input csv file of client information with
   *          lines in the format:
   *          LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate (the
   *          ExpiryDate is stored in the format YYYY-MM-DD)
   */
  public static void uploadClientInfo(String filePath) {
    try {
      LoadDataFromFile.uploadUserInfo(clientDatabase, filePath);
    } catch (FileNotFoundException e) {
      fLogger.log(Level.SEVERE, "Cannot perform upload. File not found.", e);
    } catch (IOException e) {
      fLogger.log(Level.SEVERE, "Cannot perform upload. I/O error occured while reading"
          + " from file.", e);
    } catch (ParseException e) {
      fLogger.log(Level.SEVERE, "Cannot perform upload. Date given in format other than"
          + " YYYY-MM-DD.", e);
    }
  }
  
  /**
   * Uploads flight information to the FlightDatabase from the file at the given path.
   * @param filePath A String giving the path to an input csv file of flight information with
   *          lines in the format:
   *          Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
   *          Destination,Price (the dates are in the format YYYY-MM-DD; the
   *          price has exactly two decimal places)
   */
  public static void uploadFlightInfo(String filePath) {
    try {
      LoadDataFromFile.uploadFlightInfo(flightDatabase, filePath);
    } catch (FileNotFoundException e) {
      fLogger.log(Level.SEVERE, "Cannot perform upload. File not found.", e);
    } catch (IOException e) {
      fLogger.log(Level.SEVERE, "Cannot perform upload. I/O error occured while reading"
          + " from file.", e);
    } catch (ParseException e) {
      fLogger.log(Level.SEVERE, "Cannot perform upload. Date given in format other than"
          + " YYYY-MM-DD.", e);
    }
  }
  
  /**
   * Returns a String representation of the Client with the given email address.
   * @param email A String representing the email of the Client to be returned.
   * @return A String representation of the Client who's email was given.
   */
  public static String getClient(String email) {
    String client = "";
    try {
      client = clientDatabase.getClient(email).toString();
    } catch (NoSuchEmailException e) {
      fLogger.log(Level.SEVERE, "Cannot retrieve user data. No user with given email found"
          + " in database.", e);
    }
    return client;
  }
  
  /**
   * Returns a String representation of the Flight with the given flight number.
   * @param flightNumber A String representing the flight number of the Flight to be returned.
   * @return A String representation of the Flight who's flight number was given.
   */
  public static String getFlight(String flightNumber) {
    String flight = "";
    try {
      flight = flightDatabase.getFlight(flightNumber).toString();
    } catch (NoSuchFlightException e) {
      fLogger.log(Level.SEVERE, "Cannot retrieve flight data. No flight with given flight"
          + " number found in database.", e);
    }
    return flight;
  }
  
  /**
   * Returns a String representation of all flights that depart from origin and arrive at
   * destination on the given date.
   * @param date A String representing the date on which the Flights will depart given in the
   *     YYYY-MM-DD format.
   * @param origin A String representing the origin from which the Flights depart.
   * @param destination A String representing the destination of the Flights
   * @return the flights that depart from origin and arrive at destination on
   *         the given date formatted with one flight per line in exactly this
   *         format:
   *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
   *         ,Price (the dates are in the format YYYY-MM-DD; the price has
   *         exactly two decimal places).
   */
  public static String getFlights(String date, String origin, String destination) {
    ArrayList<Flight> flights = new ArrayList<Flight>();
    try {
      flights = ItineraryGenerator.getFlights(flightDatabase, date, origin,
          destination);
    } catch (ParseException e) {
      fLogger.log(Level.SEVERE, "Cannot perform task because of an inccorect date format.", e);
    }
    String flightString = "";
    for (Flight flight : flights) {
      flightString += flight.toString() + "\n";
    }
    return flightString;
  }
  
  /** Returns all itineraries that depart from origin and arrive at destination
  * on the given date. With no more than 6 hours stop over time between the arrival and
  * departure of a Flight.
  * @param date A String representing a departure date (in the format YYYY-MM-DD).
  * @param origin A String representing the origin of the itineraries.
  * @param destination A String representing the final destination of the itineraries.
  * @return itineraries that depart from origin and arrive at destination on
  *         the given date with stop overs at or under 6 hours. Each itinerary
  *         in the output should contain one line per flight, in the format:
  *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
  *         followed by total price (on its own line, exactly two decimal
  *         places), followed by total duration (on its own line, in format
  *         HH:MM).
  */
  public static String getItineraries(String date, String origin, String destination) {
    ArrayList<FlightItinerary> itineraries = new ArrayList<FlightItinerary>();
    try {
      itineraries = ItineraryGenerator.getItineraries(flightDatabase, date, origin,
          destination);
    } catch (ParseException e) {
      fLogger.log(Level.SEVERE, "Cannot perform task because of an inccorect date format.", e);
    }
    String itineraryString = "";
    for (FlightItinerary itinerary : itineraries) {
      itineraryString += itinerary.toString();
    }
    return itineraryString;
  }
  
  /**
   * Returns all itineraries that depart from origin and arrive at destination
   * on the given date in non-decreasing order by total cost. With no more than 6 hours
   * stop over time between the arrival and departure of a Flight.
   * @param date A String representing a departure date (in the format YYYY-MM-DD).
   * @param origin A String representing the origin of the itineraries.
   * @param destination A String representing the final destination of the itineraries.
   * @return itineraries (sorted in non-decreasing order of total itinerary
   *         cost) that depart from origin and arrive at destination on the
   *         given date with stop overs at or under 6 hours. Each itinerary in
   *         the output should contain one line per flight, in the format:
   *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
   *         followed by total price (on its own line, exactly two decimal
   *         places), followed by total duration (on its own line, in format
   *         HH:MM).
   */
  public static String getItinerariesSortedByCost(String date, String origin,
      String destination) {
    ArrayList<FlightItinerary> itineraries = new ArrayList<FlightItinerary>();
    try {
      itineraries = ItineraryGenerator.getItineraries(flightDatabase, date, origin,
          destination);
      itineraries = (ArrayList<FlightItinerary>) ItinerarySort.sortCost(itineraries);
    } catch (ParseException e) {
      fLogger.log(Level.SEVERE, "Cannot perform task because of an inccorect date format.", e);
    }
    String itineraryString = "";
    for (FlightItinerary itinerary : itineraries) {
      itineraryString += itinerary.toString();
    }
    return itineraryString;
  }
  
  /**
   * Returns all itineraries that depart from origin and arrive at destination
   * on the given date in non-decreasing order by total travel time. With no more than
   * 6 hours stop over time between the arrival and departure of a Flight.
   * @param date A String representing a departure date (in the format YYYY-MM-DD).
   * @param origin A String representing the origin of the itineraries.
   * @param destination A String representing the final destination of the itineraries.
   * @return A String of itineraries (sorted in non-decreasing order of travel itinerary
   *         travel time) that depart from origin and arrive at destination on
   *         the given date with stopovers at or under 6 hours. Each itinerary
   *         in the output should contain one line per flight, in the format:
   *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
   *         followed by total price (on its own line, exactly two decimal
   *         places), followed by total duration (on its own line, in format
   *         HH:MM).
   */
  public static String getItinerariesSortedByTime(String date, String origin,
      String destination) {
    ArrayList<FlightItinerary> itineraries = new ArrayList<FlightItinerary>();
    try {
      itineraries = ItineraryGenerator.getItineraries(flightDatabase, date, origin,
          destination);
      itineraries = (ArrayList<FlightItinerary>) ItinerarySort.sortTravelTime(itineraries);
    } catch (ParseException e) {
      fLogger.log(Level.SEVERE, "Cannot perform task because of an inccorect date format.", e);
    }
    String itineraryString = "";
    for (FlightItinerary itinerary : itineraries) {
      itineraryString += itinerary.toString();
    }
    return itineraryString;
  }
  
}
