package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.model.Neighborhood;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class StateHandler {

  private static final String STATE_SUFFIX = "State";
  private static final String PROPERTIES_PACKAGE = "cellsociety.statehandlers.";
  private final String statesPackage;
  private final String handlerName;
  private final SimulationStates states;
  private Map<Integer, Enum> statesNumMap;

  /**
   * @param states        The class of the Enum that has the states for the specific simulation
   *                      type
   * @param handler       Name of the specific concrete state handler
   * @param statesPackage Package where the Enum of states for the simulation is located to be
   *                      accessed
   * @throws RuntimeException
   */
  StateHandler(Class<?> states, String handler, String statesPackage) throws RuntimeException {
    this.handlerName = handler;
    this.states = new SimulationStates(states);
    this.statesPackage = statesPackage;
    try {
      loadStates();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void loadStates() throws Exception {
    statesNumMap = new HashMap<>();
    ResourceBundle myResources = ResourceBundle.getBundle(PROPERTIES_PACKAGE + handlerName);
    for (String key : myResources.keySet()) {
      Enum currEnum = states.getEnum(key);
      int val;
      try {
        val = Integer.parseInt(myResources.getString(key));
      } catch (Exception e) {
        throw new Exception(e);
      }
      statesNumMap.put(val, currEnum);
    }
  }

  /**
   * Determines what the next state of a given Neighborhood should be in the simulation
   *
   * @param currNeighborhood The current neighborhood being examined to determine next state of
   * @return What the state of the neighborhood's center cell should change to
   */
  public abstract State figureOutNextState(Neighborhood currNeighborhood);

  /**
   * Retrieves the state for a given integer value input in the grid, such as in the init state
   * file
   *
   * @param stateValue The number that represents a state in the init state file
   * @return The Enum that corresponds to the given state value
   */
  public Enum getMapping(int stateValue) {
    return statesNumMap.get(stateValue);
  }

  /**
   * Change the state to a new state
   *
   * @param currNeighborhood The neighborhood where a state change should be made
   * @return The new state to be changed to
   */
  public State getToggledState(Neighborhood currNeighborhood) {
    Enum state = currNeighborhood.getStateEnum();
    Enum nextState = states.getNextEnum(state);
    return getStateInstance(nextState);
  }

  /**
   * Uses reflection, given an Enum state name, returns an instance of the class with that given
   * name
   *
   * @param state The name of the class to return an instance of
   * @return An instance of the class corresponding to the input state name
   */
  public State getStateInstance(Enum state) {
    String stateName = getEnumString(state);
    State retState = null;
    try {
      retState = (State) Class.forName(statesPackage + stateName).getConstructor().newInstance();
    } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
             IllegalAccessException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
    return retState;
  }

  private String getEnumString(Enum state) {
    String myState = state.toString();
    String simpleName = (myState.toLowerCase()).split(STATE_SUFFIX.toLowerCase())[0];

    String outputName = simpleName.substring(0, 1).toUpperCase()
        + simpleName.substring(1);

    return outputName + STATE_SUFFIX;
  }
}
