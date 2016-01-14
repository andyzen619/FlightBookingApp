package application;

/**
 * Constants used in the Flight Booking Application.
 * @author Raphael Alviz, Zain Amir, Ian Ferguson, Andy Liang, Johnathan Tsoi
 */
public class Constants {
  
  /** The maximum allowable time for a stop over. */
  public static final long STOPOVERMAX = 6;
  
  /** The format in which departure dates will be given. */
  public static final String DAY_DATE_FORMAT = new String("yyyy-MM-dd");
  
  /** The format in which flight arrival and departure times will be given. */
  public static final String TIME_DATE_FORMAT = new String("yyyy-MM-dd HH:mm");
  
  /** The format in which travel times will be given. */
  public static final String TIME_FORMAT = new String("HH:mm");
}