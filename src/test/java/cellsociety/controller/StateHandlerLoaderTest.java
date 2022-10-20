package cellsociety.controller;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.StateHandler;
import org.junit.jupiter.api.Test;

class StateHandlerLoaderTest {

  private StateHandlerLoader myGameOfLifeStateHandlerLoaderTester1 = new StateHandlerLoader("GameOfLife");
  private StateHandlerLoader myGameOfLifeStateHandlerLoaderTester2 = new StateHandlerLoader("gAmEofLIFe");
  private StateHandlerLoader invalidStateHandler = new StateHandlerLoader("gAmEodaijfLIFe");

  @Test
  void getsCorrectHandler() {

    StateHandler stateHandler1 = myGameOfLifeStateHandlerLoaderTester1.getStateHandler();
    StateHandler stateHandler2 = myGameOfLifeStateHandlerLoaderTester2.getStateHandler();
    StateHandler stateHandler3 = invalidStateHandler.getStateHandler();

    assertEquals(stateHandler1.getClass().getSimpleName(), "GameOfLifeStateHandler");
    assertEquals(stateHandler2.getClass().getSimpleName(), "GameOfLifeStateHandler");
    assertEquals(stateHandler3, null);
  }

}