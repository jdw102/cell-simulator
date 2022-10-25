package cellsociety.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cellsociety.Coordinate;
import cellsociety.model.statehandlers.GameOfLifeStateHandler;
import cellsociety.model.statehandlers.StateHandler;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class InitialStateReaderTest {

  static String[] validSimTypes;
  static TestUtility myUtilities = null;

  static String[] getSimTypes() {
    return validSimTypes;
  }

  @BeforeAll
  static void setUp() {
    myUtilities = new TestUtility();
    validSimTypes = myUtilities.getValidSimTypes();
  }

  @ParameterizedTest
  @MethodSource("getSimTypes")
  void getsInitialStates(String mySimType) {
    StateHandlerLoader stateHandlerLoaderTester = new StateHandlerLoader();
    StateHandler stateHandlerTester = null;
    try {
      stateHandlerTester = stateHandlerLoaderTester.getStateHandler(mySimType);
    } catch (Exception e) {
      // do nothing
    }

    for (int i = 0; i < 3; i++) {

      File myFile = myUtilities.getTestFile(mySimType, i);

      InitialStateReader stateReaderTester = null;

      try {
        stateReaderTester = new InitialStateReader(stateHandlerTester,
            myFile);
      } catch (Exception e) {
        // do nothing
      }
      int[][] expected = myUtilities.getExpectedGrid(mySimType, i);

      for (int row = 0; row < expected.length; row++) {
        for (int col = 0; col < expected[row].length; col++) {

          Coordinate myCoord = new Coordinate(row, col);
          String expectedString =
              mySimType + ", expectedGrids[" + i + "], " + "row: " + row + " col: " + col + " = " +
                  expected[row][col];
          String actualString =
              mySimType + ", expectedGrids[" + i + "], " + "row: " + row + " col: " + col + " = " +
                  stateReaderTester.getStateValue(myCoord);
          assertEquals(expectedString, actualString);
        }
      }

      String expectedRowsString =
          mySimType + ", expectedGrids[" + i + "] has " + expected.length + "rows";
      String actualRowsString =
          mySimType + ", expectedGrids[" + i + "] has " + stateReaderTester.getNumRows() + "rows";

      String expectedColsString =
          mySimType + ", expectedGrids[" + i + "] has " + expected[0].length + "cols";
      String actualColsString =
          mySimType + ", expectedGrids[" + i + "] has " + stateReaderTester.getNumCols() + "cols";

      assertEquals(expectedRowsString, actualRowsString);
      assertEquals(expectedColsString, actualColsString);
    }

  }

  @Test
  void throwsMissingFileExceptionTest() {
    StateHandler myStateHandler = new GameOfLifeStateHandler();
    File file = new File("badinputfiles/gameoflifebadinput1.csv");

    assertThrows(FileNotFoundException.class, () -> new InitialStateReader(myStateHandler, file));
  }

  @Test
  void missingColumnInformationException() {
    StateHandler myStateHandler = new GameOfLifeStateHandler();
    File file = new File("src/test/resources/badinputfiles/gameoflifebadinput1.csv");
    String expected = "Incorrect or missing information in file gameoflifebadinput1.csv. Could not"
        + " interpret columns data";
    String actual = assertThrows(IncorrectInputException.class,
        () -> new InitialStateReader(myStateHandler, file)).getMessage();

    assertEquals(expected, actual);
  }

  @Test
  void missingRowInformationException() {
    StateHandler myStateHandler = new GameOfLifeStateHandler();
    File file = new File("src/test/resources/badinputfiles/gameoflifebadinput0.csv");
    String expected = "Incorrect or missing information in file gameoflifebadinput0.csv. Could not"
        + " interpret rows data";
    String actual = assertThrows(IncorrectInputException.class,
        () -> new InitialStateReader(myStateHandler, file)).getMessage();

    assertEquals(expected, actual);
  }

  @Test
  void invalidStateValueException() {
    StateHandler myStateHandler = new GameOfLifeStateHandler();
    File file = new File("src/test/resources/badinputfiles/gameoflifebadinput2.csv");
    String expected = "Incorrect value passed in file gameoflifebadinput2.csv: 4.\n"
        + "Integer out of bounds for the simulation. Review properties file for this simulation to see valid inputs.";
    String actual = assertThrows(IncorrectInputException.class,
        () -> new InitialStateReader(myStateHandler, file)).getMessage();

    assertEquals(expected, actual);
  }

}
