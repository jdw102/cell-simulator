package cellsociety.controller;

import cellsociety.State;
import cellsociety.model.GameOfLifeStateHandler;
import cellsociety.model.StateHandler;
import java.lang.reflect.InvocationTargetException;

public class StateHandlerLoader {

  private final String mySimType;

  private static final String STATE_HANDLER_SUFFIX = "StateHandler";

  private static final String STATE_HANDLER_PACKAGE = "cellsociety.model.";

  public StateHandlerLoader(String simType) {
    mySimType = simType;
  }

  //Right now just gets a game of life state handler, will expand in future.

  private String getCorrectClassName(String input) {
    int n = input.length();
    int max = 1 << n;

    input = input.toLowerCase();


    for (int i = 0; i < max; i++) {
      char combination[] = input.toCharArray();

      for (int j = 0; j < n; j++) {
        if (((i >> j) & 1) == 1)
          combination[j] = (char)(combination[j] - 32);
        }

      String currString = String.valueOf(combination);
      if(isValidClass(currString)) {
        System.out.println(currString);
        return currString;
      }
    }
    return null;
  }

  private boolean isValidClass(String myClassString) {
    try {
      Class.forName(STATE_HANDLER_PACKAGE + myClassString + STATE_HANDLER_SUFFIX, false, getClass().getClassLoader());
      return true;
    } catch (Error | Exception e) {
      return false;
    }
  }

  public StateHandler getStateHandler()  {
    Class clazz = null;
    try{
      clazz =  Class.forName(STATE_HANDLER_PACKAGE + mySimType + STATE_HANDLER_SUFFIX);
    } catch(NoClassDefFoundError | Exception e) {
      String altClassName = getCorrectClassName(mySimType);
      try{
        clazz =  Class.forName(STATE_HANDLER_PACKAGE + altClassName + STATE_HANDLER_SUFFIX);
      } catch(Exception e2) {
//        throw e2;
      }
    }
    if(clazz == null) {
      return null;
    }
    try {
      return (StateHandler) clazz.getDeclaredConstructor().newInstance();
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
