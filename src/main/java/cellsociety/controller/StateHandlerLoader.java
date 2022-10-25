package cellsociety.controller;

import cellsociety.model.statehandlers.StateHandler;
import java.lang.reflect.InvocationTargetException;

public class StateHandlerLoader {

  private static final String STATE_HANDLER_SUFFIX = "StateHandler";

  private static final String STATE_HANDLER_PACKAGE = "cellsociety.model.statehandlers.";

  //Right now just gets a game of life state handler, will expand in future.

  /**
   * Remediate incorrect user input, algorithm to brute force all possible capitalization combinations
   * in a minimal amount of time. Any more readable algorithm will be noticeably slow.
   * Learned from a GeeksForGeeks tutorial.
   * @param input
   * @return
   */
  private String getCorrectClassName(String input) {
    int n = input.length();
    int max = 1 << n;

    input = input.toLowerCase();

    for (int i = 0; i < max; i++) {
      char combination[] = input.toCharArray();

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
    return null;
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


  // @Todo: throw class not found exception
  public StateHandler getStateHandler(String simType) throws ClassNotFoundException {
    Class clazz = null;
    try {
      clazz = Class.forName(STATE_HANDLER_PACKAGE + simType + STATE_HANDLER_SUFFIX);
    } catch (NoClassDefFoundError | Exception e) {
      String altClassName = getCorrectClassName(simType);
      try {
        clazz = Class.forName(STATE_HANDLER_PACKAGE + altClassName + STATE_HANDLER_SUFFIX);
      }  catch (NoClassDefFoundError | Exception ex){
        throw new RuntimeException(ex);
      }
    }
    if (clazz == null) {
      throw new RuntimeException("Unable to instantiate StateHandler");
    }

    try {
      return (StateHandler) clazz.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | RuntimeException | IllegalAccessException |
             InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }


  private StateHandler instantiateNewStateHandler(Class<?> clazz) {
    try {
      return (StateHandler) clazz.getDeclaredConstructor().newInstance();
    } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
             NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
