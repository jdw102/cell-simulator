package cellsociety.controller;

import cellsociety.model.statehandlers.GameOfLifeStateHandler;
import cellsociety.model.statehandlers.StateHandler;
import cellsociety.view.GridView;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import cellsociety.controller.InitialStateReaderTest.*;
import org.junit.jupiter.params.provider.ValueSource;

class CellSpawnerTest {

  static TestUtility myUtilities;

  static String[] validSimTypes;
  @BeforeAll
  static void setUp() {
    myUtilities = new TestUtility();
    validSimTypes = myUtilities.getValidSimTypes();;

  }


  @ParameterizedTest
  @MethodSource("getSimTypes")
  void getCell(String simType) {

    StateHandlerLoader myLoader = new StateHandlerLoader();

    StateHandler myStateHandler = null;

    try {
      myStateHandler = myLoader.getStateHandler(simType);
    } catch(Exception e) {
      // do nothing
    }

    for(int i = 0; i < 3; i++) {

      File myFile = myUtilities.getTestFile(simType, i);

      InitialStateReader myInitialStateReader = null;

      try {
        myInitialStateReader = new InitialStateReader(myStateHandler, myFile);
      } catch (Exception e) {
        //do nothing
      }

      int width = myInitialStateReader.getNumCols();
      int height = myInitialStateReader.getNumRows();

      GridView myGridView = new GridView(width, height);

      myGridView.setSimType(simType);

      CellSpawner myCellSpawnerTester = new CellSpawner(myGridView, myInitialStateReader);

      myGridView.setSimType(simType);

      for(int j = 0; j < height; j ++) {
        for (int k = 0; k < width; k++) {
          String modelOutput = String.format("CellModel[%d][%d] in %s simulation test %d instantiated\n", j,k,simType,i);
          String viewOutput = String.format("CellView[%d][%d] in %s simulation test %d instantiated\n", j,k,simType,i);
          String actualModelOutput = modelOutput;
          String actualViewOutput = viewOutput;

          if(myCellSpawnerTester.getCell(j,k) == null) {
            actualModelOutput = null;
          }

          if(myCellSpawnerTester.getCellView(j,k) == null) {
            actualViewOutput = null;
          }
          Assertions.assertEquals(modelOutput, actualModelOutput);
          Assertions.assertEquals(viewOutput, actualViewOutput);
        }
      }

    }
  }

  static String[] getSimTypes() {
    return validSimTypes;
  }

}