package cellsociety.controller;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.statehandlers.StateHandler;
import java.io.File;
import org.junit.jupiter.api.BeforeAll;
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
  void getsInitialStates(String mySimType){
    StateHandlerLoader stateHandlerLoaderTester = new StateHandlerLoader();
    StateHandler stateHandlerTester = null;
    try {
      stateHandlerTester = stateHandlerLoaderTester.getStateHandler(mySimType);
    } catch (Exception e) {
      // do nothing
    }

    for(int i = 0; i < 3; i ++) {

      File myFile = myUtilities.getTestFile(mySimType, i);

      InitialStateReader stateReaderTester = null;

      try {
        stateReaderTester = new InitialStateReader(stateHandlerTester,
            myFile);
      } catch (Exception e) {
        // do nothing
      }
      int[][] expected = myUtilities.getExpectedGrid(mySimType, i);

      for(int row = 0; row < expected.length; row++) {
        for(int col = 0; col < expected[row].length; col++) {
          String expectedString = mySimType + ", expectedGrids[" + i + "], " + "row: " + row + " col: " + col +" = " +
              expected[row][col];
          String actualString = mySimType + ", expectedGrids[" + i + "], " + "row: " + row + " col: " + col +" = " +
              stateReaderTester.getStateValue(row, col);
          assertEquals(expectedString, actualString);
        }
      }

      String expectedRowsString = mySimType + ", expectedGrids[" + i + "] has " + expected.length + "rows";
      String actualRowsString = mySimType + ", expectedGrids[" + i + "] has " + stateReaderTester.getNumRows() + "rows";

      String expectedColsString = mySimType + ", expectedGrids[" + i + "] has " + expected[0].length + "cols";
      String actualColsString = mySimType + ", expectedGrids[" + i + "] has " + stateReaderTester.getNumCols() + "cols";

      assertEquals(expectedRowsString, actualRowsString);
      assertEquals(expectedColsString, actualColsString);
    }

  }

}
