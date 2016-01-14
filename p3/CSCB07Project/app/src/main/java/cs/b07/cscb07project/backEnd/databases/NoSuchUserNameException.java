package cs.b07.cscb07project.backEnd.databases;

/**
 * Indicates an attempt to find a user using an email that doesn't exist.
 * 
 * @author Raphael Alviz, Zain Amir, Ian Ferguson, Andy Liang, Johnathan Tsoi
 */
public class NoSuchUserNameException extends Exception {

  /**
   * Serialization.
   */
  private static final long serialVersionUID = -1853915305418053143L;

  /**
   * Constructs a new NoSuchUserNameException with null as its detail message.
   */
  public NoSuchUserNameException() {

  }
  
  /**
   * Constructs a new NoSuchUserNameException with its detail message.
   * @param message to be printed.
   */
  public NoSuchUserNameException(String message) {
    super(message);
  }

}
