package cellsociety.controller;

import static java.lang.Character.isLetter;

import cellsociety.model.statehandlers.InvalidParameterException;
import cellsociety.model.statehandlers.MissingParameterException;
import cellsociety.model.statehandlers.StateHandler;
import java.lang.reflect.InvocationTargetException;

public class StateHandlerLoader {

  private static final String STATE_HANDLER_SUFFIX = "StateHandler";

  private static final String STATE_HANDLER_PACKAGE = "cellsociety.model.statehandlers.";

  /**
   * Remediate incorrect user input, algorithm to brute force all possible capitalization
   * combinations in a minimal amount of time. Any more readable algorithm will be noticeably slow.
   * Learned from a GeeksForGeeks: https://www.geeksforgeeks.org/permute-string-changing-case/
   *
   * @param input String to permute every uppercase character possibility
   * @return The class name if a valid one is found
   * @throws InvalidSimulationException thrown if no valid class is found to create
   */
  private String getCorrectClassName(String input) throws InvalidSimulationException {
    int n = input.length();
    int max = 1 << n;

    input = isolateLetters(input);
    input = input.toLowerCase();

    for (int i = 0; i < max; i++) {
      char[] combination = input.toCharArray();

      for (int j = 0; j < n; j++) {
        if (((i >> j) & 1) == 1) {
          combination[j] = (char) (combination[j] - 32);
        }
      }

      String currString = String.valueOf(combination);
      if (isValidClass(currString)) {
        return currString;
      }
    }
    throw new InvalidSimulationException(input);
  }

  private boolean isValidClass(String myClassString) {
    try {
      Class.forName(STATE_HANDLER_PACKAGE + myClassString + STATE_HANDLER_SUFFIX, false,
          getClass().getClassLoader());
      return true;
    } catch (Error | Exception e) {
      return false;
    }
  }

  private String isolateLetters(String input) {
    StringBuilder outputString = new StringBuilder();
    for (char c : input.toCharArray()) {
      if (isLetter(c)) {
        outputString.append(c);
      }
    }
    return outputString.toString();
  }

  /**
   * Uses reflection to get the correct state handler for the type of simulation
   *
   * @param simType The simulation type
   * @return The StateHandler for the simulation type
   * @throws InvalidSimulationException Thrown if the simulation type is invalid
   */
  public StateHandler getStateHandler(String simType, String paramter)
      throws InvalidSimulationException, InvalidParameterException, MissingParameterException {
    Class clazz = null;
    try {
      clazz = Class.forName(STATE_HANDLER_PACKAGE + simType + STATE_HANDLER_SUFFIX);
    } catch (ClassNotFoundException | NoClassDefFoundError e) {
      clazz = attemptCorrectClass(simType);
    }

    StateHandler handler = null;
    try {
      handler = (StateHandler) clazz.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException |
             InvocationTargetException | NoSuchMethodException e) {
      throw new InvalidSimulationException(simType);
    }

    try {
      handler.loadDefaults();
    } catch(IncorrectInputException | InvalidParameterException |
        MissingParameterException e) {
      throw e;
    }

    setParameter(handler, simType, paramter);

    return handler;
  }

  private void setParameter(StateHandler myHandler, String simType, String parameter)
      throws InvalidParameterException {
    if (parameter != null && parameter.strip().length() > 0) {
      parameter = parameter.strip();
      double parameterVal;
      try {
        parameterVal = Double.parseDouble(parameter);
      } catch (NumberFormatException | IndexOutOfBoundsException e) {
        throw new InvalidParameterException(simType, parameter);
      }
      myHandler.setParameter(parameterVal);
    }
  }

  private Class attemptCorrectClass(String simType) throws InvalidSimulationException {
    Class clazz;
    try {
      String altClassName = getCorrectClassName(simType);
      clazz = Class.forName(STATE_HANDLER_PACKAGE + altClassName + STATE_HANDLER_SUFFIX);
    } catch (ClassNotFoundException e) {
      throw new InvalidSimulationException(simType);
    }
    return clazz;
  }
}
