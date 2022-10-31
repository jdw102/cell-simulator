package cellsociety.controller;

import static cellsociety.Main.DEFAULT_COLORS_PACKAGE;

import cellsociety.Coordinate;
import cellsociety.model.statehandlers.StateHandler;
import cellsociety.view.DisplayView;
import cellsociety.view.StateColors;
import java.io.File;
import java.util.ResourceBundle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CellSpawnerTest {

  static TestUtility myUtilities;

  static String[] validSimTypes;

  @BeforeAll
  static void setUp() {
    myUtilities = new TestUtility();
    validSimTypes = myUtilities.getValidSimTypes();
    ;

  }

  static String[] getSimTypes() {
    return validSimTypes;
  }

  @ParameterizedTest
  @MethodSource("getSimTypes")
  void getCell(String simType) {

    StateHandlerLoader myLoader = new StateHandlerLoader();

    StateHandler myStateHandler = null;

    try {
      myStateHandler = myLoader.getStateHandler(simType);
    } catch (Exception e) {
      // do nothing
    }

    for (int i = 0; i < 3; i++) {

      File myFile = myUtilities.getTestFile(simType, i);

      InitialStateReader myInitialStateReader = null;

      try {
        myInitialStateReader = new InitialStateReader(myStateHandler, myFile);
      } catch (Exception e) {
        //do nothing
      }
      DisplayView view = new DisplayView("English", null);
      StateColors stateColors = new StateColors(
          ResourceBundle.getBundle(DEFAULT_COLORS_PACKAGE + simType));

      view.getGridView().setStateColors(stateColors);

      CellSpawner myCellSpawnerTester = null;

      try {
        myCellSpawnerTester = new CellSpawner(view,
            myInitialStateReader);
      } catch (Exception e) {

      }

      view.getGridView().setStateColors(stateColors);

      for (int j = 0; j < myCellSpawnerTester.getNumRows(); j++) {
        for (int k = 0; k < myCellSpawnerTester.getNumCols(); k++) {
          String modelOutput = String.format(
              "CellModel[%d][%d] in %s simulation test %d instantiated\n", j, k, simType, i);
          String viewOutput = String.format(
              "CellView[%d][%d] in %s simulation test %d instantiated\n", j, k, simType, i);
          String actualModelOutput = modelOutput;
          String actualViewOutput = viewOutput;

          Coordinate myCoord = new Coordinate(j, k);

          if (myCellSpawnerTester.getCell(myCoord) == null) {
            actualModelOutput = null;
          }

          if (myCellSpawnerTester.getCellView(myCoord) == null) {
            actualViewOutput = null;
          }
          Assertions.assertEquals(modelOutput, actualModelOutput);
          Assertions.assertEquals(viewOutput, actualViewOutput);
        }
      }

    }
  }

}