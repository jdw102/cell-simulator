package cellsociety.controller;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.StateHandler;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class InitialStateReaderTest {


  StateHandlerLoader stateHandlerLoaderTester = new StateHandlerLoader("gameoflife");
  StateHandler stateHandlerTester = stateHandlerLoaderTester.getStateHandler();
  File initTestFile = new File("data/game_of_life/blinkers.csv");

  InitialStateReader initialStateReaderTester = new InitialStateReader(stateHandlerTester, initTestFile);

  InitialStateReaderTest() throws CsvValidationException, IOException, WrongFileTypeException {
  }

  @Test
  void getState() {
    int[][] expected = {
        {0,0,0,0,0,0,0,1,1,1},
        {0,0,0,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0},
        {1,0,0,0,1,0,0,0,0,0},
        {1,0,0,0,1,0,0,0,0,0},
        {0,0,0,0,1,0,0,0,0,1},
        {0,0,0,0,0,0,0,0,0,1},
        {0,1,1,1,0,0,0,0,0,1},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,1,1,1,0,0,0},
    };
    for(int row = 0; row < expected.length; row++) {
      for(int col = 0; col < expected[row].length; col++) {
        assertEquals(expected[row][col], initialStateReaderTester.getStateValue(row, col));
      }
    }
  }

  @Test
  void getNumRows() {
    assertEquals(10, initialStateReaderTester.getNumRows());
  }

  @Test
  void getNumCols() {
    assertEquals(10, initialStateReaderTester.getNumRows());
  }
}