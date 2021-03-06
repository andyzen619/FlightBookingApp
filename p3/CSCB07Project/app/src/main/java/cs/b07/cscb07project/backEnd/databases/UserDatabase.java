package cs.b07.cscb07project.backEnd.databases;

import cs.b07.cscb07project.backEnd.users.Client;
import cs.b07.cscb07project.backEnd.users.User;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A database containing Client objects. Allows adding and removing of Client
 * objects and as well as retrieval of specific Client information by email.
 * @author Raphael Alviz, Zain Amir, Ian Ferguson, Andy Liang, Johnathan Tsoi
 */
public class UserDatabase implements Serializable {
  
  private static final long serialVersionUID = 3802400865379720426L;
  // HashMap mapping a User's unique user name address to the User object for quick access
  // and storage of User information.
  private HashMap<String, User> userDatabase;

  /**
   * Creates a new empty UserDatabase object.
   */
  public UserDatabase() {
    userDatabase = new HashMap<String, User>();
  }

  /**
   * Adds a client to the UserDatabase using the given User's user name as the unique
   * access key. If the User object contains an user name already present in the
   * UserDatabase, the new User will overwrite the data stored for the existing User
   * with that user name.
   * @param user The User object to be added to the UserDatabase.
   */
  public void addUser(User user) {
    // Adds the user to the UserDatabase with the userName as the key
    userDatabase.put(user.getUserName(), user);
  }

  /**
   * (Overloading) If a client is not already created, it is created in here
   * with the corresponding parameters below. If the given email is already present in the
   * ClientDatabase, the Client object will not be added and nothing will happen.
   * @param userName A String user name for the Client to be created.
   * @param lastName A String representing the last name of the Client.
   * @param firstName A String representing the first name of the Client.
   * @param email A String representing the email address of the Client.
   * @param address A String representing the street address of the Client.
   * @param creditCardNumber A String representing the credit card number of the Client.
   * @param expiryDate A String representing the expiry date of the Client's credit card. Must
   *     be given in the format YYYY-MM-DD.
   * @throws ParseException Throws a ParseException if credit card expiry date is not given
   *     in the YYYY-MM-DD format.
   */
  public void addClient(String userName, String lastName, String firstName,
      String email, String address, String creditCardNumber, String expiryDate)
          throws ParseException {
    Client newClient = new Client(userName, lastName, firstName, email, address,
        creditCardNumber, expiryDate);
    this.addUser(newClient);
  }

  /**
   * (Overloading) Adds an array of User objects to the UserDatabase. If a User object
   * contains an user name already present in the UserDatabase, that User object will overwrite
   * the existing User with that user name.
   * @param users An array of User objects that will be added to the UserDatabase.
   */
  public void addUsers(ArrayList<User> users) {
    for (User user : users) {
      this.addUser(user);
    }
  }

  /**
   * Removes the client from the clientDatabase. If the clients does not exist
   * then nothing will be done.
   * @param client The Client object to be removed from the clientDatabse.
   */
  public void removeUser(User user) {
    userDatabase.remove(user.getUserName());
  }

  /**
   * Removes the User with the given user name from the UserDatabase. If a User
   * with that user name is not present in the UserDatabase, it does nothing.
   * @param userName A String representing the user name of the User object to be
   *     removed from the UserDatabase.
   */
  public void removeUser(String userName) {
    userDatabase.remove(userName);
  }

  /**
   * Returns the User object with the given user name.
   * 
   * @param userName
   *         The A String user name of User to be returned.
   * @return The User object with the given user name. Null if the User
   *         does not exist in the UserDatabase.
   * @throws NoSuchUserNameException is thrown if no such user name exists in
   *          the UserDatabase.
   */
  public User getUser(String userName) throws NoSuchUserNameException {
    if (!userDatabase.containsKey(userName)) {
      throw new NoSuchUserNameException();
    } else {
      return userDatabase.get(userName);
    }
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    String userString = "";
    for (User user : userDatabase.values()) {
      userString += (user.toString() + "\n");
    }
    return userString;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userDatabase == null) ? 0 : userDatabase.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    UserDatabase other = (UserDatabase) obj;
    if (userDatabase == null) {
      if (other.userDatabase != null) {
        return false;
      }
    } else if (!userDatabase.equals(other.userDatabase)) {
      return false;
    }
    return true;
  }
}


