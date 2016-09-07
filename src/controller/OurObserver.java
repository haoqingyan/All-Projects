package controller;
/** 
 * A simpler version of java's class Observable
 * 
 * @author mercer
*/

/** 
 * A simpler version of Observer
 * 
 * Usually use a JPanel to implement this interface because 
 * the JPanel can be drawn any number of ways whenever the 
 * model notifies that class that it's state has changed. 
 *  
 * @author mercer
 */

public interface OurObserver {
  public void update();
}
