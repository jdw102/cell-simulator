package cellsociety.controller;

import cellsociety.State;
import cellsociety.model.StateHandler;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class InitialStateReader {

  private static final int NUM_ROWS_INDEX = 0;
  private static final int NUM_COLS_INDEX = 1;

  private int myNumRows;

  private int myNumCols;
  private File myFile;
  private int[][] statesAsInts;
  private StateHandler myStateHandler;

  public InitialStateReader(StateHandler stateHandler, File f)
      throws CsvValidationException, IOException {
    myFile = f;
    statesAsInts = parse();

    myStateHandler = stateHandler;
  }

  //Read numbers from a file into a grid.
  private int[][] parse() throws IOException, CsvValidationException {
    int[][] outputArray;

    CSVReader myCSVReader = new CSVReader(new FileReader(myFile));

    setDimensions(myCSVReader);

    outputArray = new int[myNumRows][myNumCols];

    String[] nextLine;
    int row = 0;
    while ((nextLine = myCSVReader.readNext()) != null) {
      for (int col = 0; col < myNumCols; col++) {

        validateCell(nextLine, col);

        int current_val = Integer.parseInt(nextLine[col]);

        outputArray[row][col] = current_val;
        validateState(current_val);

      }
      row += 1;
    }
    return outputArray;
  }

  private void validateCell(String[] line, int index) {
    try {
      Integer.parseInt(line[index]);

    } catch (Exception e) {
      //if index out of bounds, suggests incorrect num cols
      //if could not parse int then incorrect file format
    }
  }

  private void validateState(int value) {
    try {
      myStateHandler.getMapping(value);
    } catch (Exception e) {
      // no such state mapping exists, i.e. not a valid int for this simulation
    }
  }

  private void setDimensions(CSVReader myCSVReader) throws IOException, CsvValidationException {
    int[] outputIntArray; /// should be [num_rows, num_cols]

    String[] firstLine = null;

    try {
      firstLine = myCSVReader.readNext();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    int numRows = 0;
    int numCols = 0;

    try {
      numRows = Integer.parseInt(firstLine[NUM_ROWS_INDEX]);
    } catch (Exception e) {
      /// num rows not given
    }
    try {
      numCols = Integer.parseInt(firstLine[NUM_COLS_INDEX]);
    } catch (Exception e) {
      /// num cols not given
    }

    myNumRows = numRows;
    myNumCols = numCols;
  }

  public State createStateInstance(int row, int col) {
    int valOfState = statesAsInts[row][col];
    try {
      return (State) myStateHandler.getMapping(valOfState).getDeclaredConstructor().newInstance();
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

  public int getStateValue(int row, int col) {
    return statesAsInts[row][col];
  }

  public int getNumRows() {
    return myNumRows;
  }

  public int getNumCols() {
    return myNumCols;
  }

}
