package databases;

import users.Client;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A database containing Client objects. Allows adding and removing of Client
 * objects and as well as retrieval of specific Client information by email.
 * @author Raphael Alviz, Zain Amir, Ian Ferguson, Andy Liang, Johnathan Tsoi
 */
public class ClientDatabase implements Serializable {
  
  private static final long serialVersionUID = 3802400865379720426L;
  // HashMap mapping a client's unique email address to the Client object for quick access
  // and storage of client information.
  private HashMap<String, Client> clientDatabase;

  /**
   * Creates a new empty ClientDatabase object.
   */
  public ClientDatabase() {
    clientDatabase = new HashMap<String, Client>();
  }

  /**
   * Adds a client to the ClientDatabase using the given client's email address as the unique
   * access key. If the Client object contains an email already present in the ClientDatabase,
   * the Client object will not be added and nothing will happen.
   * @param client The Client object to be added to the ClientDatabase.
   */
  public void addClient(Client client) {
    // Adds the client to the client database with the email as the key
    clientDatabase.put(client.getEmail(), client);
  }

  /**
   * (Overloading) If a client is not already created, it is created in here
   * with the corresponding parameters below. If the given email is already present in the
   * ClientDatabase, the Client object will not be added and nothing will happen.
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
  public void addClient(String lastName, String firstName, String email, String address,
      String creditCardNumber, String expiryDate) throws ParseException {
    Client newClient = new Client(lastName, firstName, email, address, creditCardNumber,
        expiryDate);
    this.addClient(newClient);
  }

  /**
   * (Overloading) Adds an array of Client objects to the ClientDatabase. If a Client object
   * contains an email already present in the ClientDatabase, that Client object will not be
   * added.
   * @param clients An array of Client objects that will be added to the ClientDatabase.
   */
  public void addClients(ArrayList<Client> clients) {
    for (Client client : clients) {
      this.addClient(client);
    }
  }

  /**
   * Removes the client from the clientDatabase. If the clients does not exist
   * then nothing will be done.
   * @param client The Client object to be removed from the clientDatabse.
   */
  public void removeClient(Client client) {
    clientDatabase.remove(client.getEmail());
  }

  /**
   * Removes the Client with the given email address from the clientDatabase. If a Client
   * with that email is not present in the ClientDatabase, it does nothing.
   * @param clientEmail A String representing the email address of the Client object to be
   *     removed from the ClientDatabase.
   */
  public void removeClient(String clientEmail) {
    clientDatabase.remove(clientEmail);
  }

  /**
   * Takes in the client's email, the key for clientDatabase and will return the
   * client object for the corresponding email.
   * 
   * @param clientEmail
   *          The clientEmail for the corresponding Client to be returned.
   * @return The Client object with the given client e-mail. Null if the client
   *         does not exist in the clientDatabase.
   * @throws NoSuchEmailException is thrown if no such clientEmail exists in
   *          the Database.
   */
  public Client getClient(String clientEmail) throws NoSuchEmailException {
    if (!clientDatabase.containsKey(clientEmail)) {
      throw new NoSuchEmailException();
    } else {
      return clientDatabase.get(clientEmail);
    }
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    String clientString = "";
    for (Client client : clientDatabase.values()) {
      clientString += (client.toString() + "\n");
    }
    return clientString;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((clientDatabase == null) ? 0 : clientDatabase.hashCode());
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
    ClientDatabase other = (ClientDatabase) obj;
    if (clientDatabase == null) {
      if (other.clientDatabase != null) {
        return false;
      }
    } else if (!clientDatabase.equals(other.clientDatabase)) {
      return false;
    }
    return true;
  }
}
