package cellsociety.controller;

import cellsociety.model.GameOfLifeStateHandler;
import cellsociety.model.StateHandler;

public class StateHandlerLoader {

  private final String mySimType;

  public StateHandlerLoader(String simType) {
    mySimType = simType.toLowerCase();
  }

  //Right now just gets a game of life state handler, will expand in future.
  public StateHandler getStateHandler() {
    return new GameOfLifeStateHandler();
  }
}
