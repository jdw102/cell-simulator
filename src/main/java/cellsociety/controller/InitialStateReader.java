package cellsociety.controller;

import cellsociety.State;
import cellsociety.model.statehandlers.StateHandler;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


/**
 * Class in charge of reading in initial states (as ints)
 */
public class InitialStateReader extends FileParser {

  public static final String CSV_FILE_TYPE = "csv";
  private static final int NUM_ROWS_INDEX = 1;
  private static final int NUM_COLS_INDEX = 0;
  private int myNumRows;

  private int myNumCols;
  private final File myFile;
  private final int[][] statesAsInts;
  private final StateHandler myStateHandler;

  public InitialStateReader(StateHandler stateHandler, File f)
      throws CsvValidationException, IOException, WrongFileTypeException {
    this.myStateHandler = stateHandler;
    isFileTypeCorrect(f, CSV_FILE_TYPE);
    myFile = f;
    statesAsInts = parse();
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

        validateState(current_val);
        outputArray[row][col] = current_val;

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
      throw new RuntimeException(e);
    }
  }

  private void validateState(int value) {
    try {
      myStateHandler.getMapping(value);
    } catch (Exception e) {
      // no such state mapping exists, i.e. not a valid int for this simulation
      throw new RuntimeException(e);
    }
  }

  private void setDimensions(CSVReader myCSVReader) throws IOException, CsvValidationException {
    int[] outputIntArray; /// should be [num_rows, num_cols]

    String[] firstLine = null;

    try {
      firstLine = myCSVReader.readNext();
    } catch (Exception e) {
    }

    int numRows = 0;
    int numCols = 0;

    try {
      numRows = Integer.parseInt(firstLine[NUM_ROWS_INDEX]);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    try {
      numCols = Integer.parseInt(firstLine[NUM_COLS_INDEX]);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    myNumRows = numRows;
    myNumCols = numCols;
  }


  /**
   * Class to instantiate an object of a given cell state
   *
   * @param row the x coordinate in the grid of cells
   * @param col the y coordinate in the grid of cells
   * @return the newly instantiated state object
   */
  public State createStateInstance(int row, int col) {
    int valOfState = statesAsInts[row][col];
    Enum state = myStateHandler.getMapping(valOfState);

    return myStateHandler.getStateInstance(state);
  }

  /**
   * A method for testing that the states were read in correctly.
   *
   * @param row the x position of a cell's coordinate
   * @param col the y position of a cell's coordinate
   * @return the integer (denoting the state) at a specific coordinate
   */
  protected int getStateValue(int row, int col) {
    return statesAsInts[row][col];
  }


  /**
   * A method to obtain the length of a cell grid
   *
   * @return the number of rows in the grid
   */
  public int getNumRows() {
    return myNumRows;
  }

  /**
   * A method to obtain the width of a cell grid
   *
   * @return the number of columns in the grid
   */
  public int getNumCols() {
    return myNumCols;
  }

}
