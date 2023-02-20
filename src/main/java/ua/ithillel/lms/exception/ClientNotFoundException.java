package ua.ithillel.lms.exception;

public class ClientNotFoundException extends Exception {

  public ClientNotFoundException(long id) {
    super("Client with ID#" + id + " has not been found");
  }
}
