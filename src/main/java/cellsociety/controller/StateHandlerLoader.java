package cellsociety.controller;

import cellsociety.model.statehandlers.StateHandler;
import java.lang.reflect.InvocationTargetException;

public class StateHandlerLoader {
  public static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.model.statehandlers.";

  public StateHandler getStateHandler(String gameType) throws ClassNotFoundException {
    Class<?> clazz = Class.forName(String.format("%s%sStateHandler", DEFAULT_RESOURCE_PACKAGE, gameType));
    return instantiateNewStateHandler(clazz);
  }

  private StateHandler instantiateNewStateHandler(Class<?> clazz) {
    try {
      return (StateHandler) clazz.getDeclaredConstructor().newInstance();
    } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
