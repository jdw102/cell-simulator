package cellsociety.model;

import cellsociety.Observable;
import cellsociety.Observer;
import cellsociety.State;

import java.util.List;

/**
 * Contains the current state and next state of cells. Alerts list of Observers
 * when the current state of this cell changes
 */
public class CellModel implements Observable {
  private State myCurrentState;
  private State myNextState;
  private Enum myCellType;
  private List<Observer> myObservers;

  /**
   * Get a new CellModel
   * @param startingState the starting state of this CellModel.
   */
  public CellModel(State startingState) {
    myCurrentState = startingState;
    myNextState = startingState;
    myCellType = startingState.getSimulationType();
  }

  /**
   * Add an Observer to this CellModel. Whenever the CellModel's state changes,
   * the Observer will be notified.
   * @param observer the Observer to be notified
   */
  @Override
  public void addObserver(Observer observer) {
    myObservers.add(observer);
  }

  /**
   * Remove an Observer from this CellModel's list of Observers
   * @param observer the Observer to be removed
   */
  @Override
  public void removeObserver(Observer observer) {
    myObservers.remove(observer);
  }

  /**
   * Returns an enum representing the game that this Cell corresponds with
   * TODO: GIVE THIS METHOD A BETTER NAME?
   * @return Enum
   */
  public Enum getName() {
    return myCellType;
  }

  /**
   * Set current state of this cell. Alerts observers that this cell's state has changed
   * @param state this cell's new current state
   */
  public void setMyCurrentState(State state) {
    myCurrentState = state;
    alertMyObservers();
  }

  /**
   * Get current state of this cell
   * @return myCurrentState
   */
  public State getMyCurrentState() {
    return myCurrentState;
  }

  /**
   * Get the next state of this cell
   */
  public State getMyNextState() {
    return myNextState;
  }

  /**
   * Set next state of this cell
   * @param state this cell's next state
   */
  public void setMyNextState(State state) {
    myNextState = state;
  }

  // TODO: Should we put this method in the Observable interface?
  // TODO: Finish writing this method
  private void alertMyObservers() {
    for (Observer observer : myObservers) {
      // do some action

    }
  }
}
