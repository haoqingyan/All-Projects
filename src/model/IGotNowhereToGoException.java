package model;

/**
 * This exception may be thrown by a TicTacToeStrategy 
 * when it is asked to return the player's move in the
 * case where there are no more move from which to select
 *  
 * @author mercer
 */
public class IGotNowhereToGoException extends RuntimeException {

  public IGotNowhereToGoException(String message) {
    super(message);
  }
}