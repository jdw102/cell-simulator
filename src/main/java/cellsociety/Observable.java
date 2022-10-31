package cellsociety;

/**
 * Communicates state changes with an Observer object
 *
 * @author Daniel Feinblatt
 */
public interface Observable {

  /**
   * Add an observer to the list of observers to be notified on state change
   *
   * @param observer
   */
  void addObserver(Observer observer);

  /**
   * Remove an observer from the list of observers
   *
   * @param observer
   */
  void removeObserver(Observer observer);

  /**
   * Notify observers of a state change
   */
  void notifyObservers();
}
