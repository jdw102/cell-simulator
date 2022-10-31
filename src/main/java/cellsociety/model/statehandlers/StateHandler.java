package cellsociety.model.statehandlers;

import static cellsociety.Main.SETTINGS;

import cellsociety.State;
import cellsociety.controller.IncorrectInputException;
import cellsociety.model.Neighborhood;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Abstract class for shared functionality amongst state handlers
 *
 * @author Mazen Selim
 * @author Daniel Feinblatt
 */
public abstract class StateHandler {

  private static final String STATE_SUFFIX = "State";
  private static final String PROPERTIES_PACKAGE_FORMAT = "cellsociety.statehandlers.%s";

  private static final String CELL_STATES_PACKAGE_FORMAT = "cellsociety.cellstates.%scellstates.";

  private static final String HANDLER_NAME_FORMAT = "%sStateHandler";

  private static final String DEFAULT_PARAMATER_FORMAT_KEY = "%sDefaultParameter";
  private final String defaultParameterKey;
  private final String statesPackage;
  private final String handlerName;
  private final String simulationName;
  private final String propertiesPackage;
  private final SimulationStates states;
  private Map<Integer, Enum> statesNumMap;

  private double parameter;

  /**
   * @param states  The class of the Enum that has the states for the specific simulation type
   * @param simType Name of the corresponding simulation
   * @throws RuntimeException
   */
  StateHandler(Class<?> states, String simType) {
    this.handlerName = String.format(HANDLER_NAME_FORMAT, simType);
    this.states = new SimulationStates(states);
    this.simulationName = simType;
    this.statesPackage = String.format(CELL_STATES_PACKAGE_FORMAT, simType.toLowerCase());
    this.propertiesPackage = String.format(PROPERTIES_PACKAGE_FORMAT, handlerName);
    this.defaultParameterKey = String.format(DEFAULT_PARAMATER_FORMAT_KEY, simType);
  }

  private void loadStates() throws IncorrectInputException {
    statesNumMap = new HashMap<>();
    ResourceBundle myResources = ResourceBundle.getBundle(propertiesPackage);
    for (String key : myResources.keySet()) {
      Enum currEnum = states.getEnum(key);
      int val;
      try {
        val = Integer.parseInt(myResources.getString(key));
      } catch (NumberFormatException | IndexOutOfBoundsException e) {
        throw new IncorrectInputException(handlerName, states.getEnum(key).toString());
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

    String outputName = simpleName.substring(0, 1).toUpperCase() + simpleName.substring(1);

    return outputName + STATE_SUFFIX;
  }

  public void setParameter(double parameter) throws InvalidParameterException {
    throw new InvalidParameterException(simulationName, String.valueOf(parameter));
  }

  public void loadDefaults() throws IncorrectInputException, InvalidParameterException,
      MissingParameterException {
    String parameterString = null;
    try {
      parameterString = SETTINGS.getString(defaultParameterKey).strip();
    } catch (NullPointerException | MissingResourceException | ClassCastException e) {
      throw new MissingParameterException(simulationName);
    }
    try {
      parameter = Double.parseDouble(parameterString);
    } catch (NumberFormatException | IndexOutOfBoundsException e) {
      throw new InvalidParameterException(parameterString, simulationName);
    } finally {
      try {
        loadStates();
      } catch (IncorrectInputException e) {
        throw e;
      }
    }
  }

  protected double getParameter() {
    return parameter;
  }

  protected void overwriteParameter(double parameter) {
    this.parameter = parameter;
  }

}
