package cellsociety.controller;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.statehandlers.GameOfLifeStateHandler;
import cellsociety.model.statehandlers.StateHandler;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class StateHandlerLoaderTest {

  static String[] myValidSims;
  static TestUtility myUtilities;
  private StateHandlerLoader myStateHandlerLoader = new StateHandlerLoader();

  @BeforeAll
  static void setUp() {
    myUtilities = new TestUtility();
    myValidSims = myUtilities.getValidSimTypes();;
  }

  static String[] getSimTypes() {
    return myValidSims;
  }

  @ParameterizedTest
  @MethodSource("getSimTypes")
  void getsCorrectHandler(String mySimType) {

    StateHandler stateHandler = null;

    String[] validVariations = getValidVariations(mySimType);

    for(String variation: validVariations) {
      try {
        stateHandler = myStateHandlerLoader.getStateHandler(variation, "");
      } catch (Exception e) {
        Assertions.fail();
      }
      assertNotEquals(stateHandler, null);
      assertEquals(stateHandler.getClass().getSimpleName(), mySimType + "StateHandler");
    }
  }

  String[] getValidVariations(String simType) {
    ArrayList<String> myVariations = new ArrayList<>();
    myVariations.add(simType);
    myVariations.add(simType.toLowerCase());
    myVariations.add(simType.toUpperCase());

    return myVariations.toArray(new String[myVariations.size()]);
  }

  @Test
  void catchesInvalidSimulation() {
    StateHandlerLoader myLoader = new StateHandlerLoader();

    String simType = "lame";

    String expected = "lame simulation not recognized.";
    String actual = assertThrows(InvalidSimulationException.class,
        ()-> myLoader.getStateHandler(simType, "")).getMessage();

    assertEquals(expected, actual);
  }


}