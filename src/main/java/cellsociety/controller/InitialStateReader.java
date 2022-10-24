package cellsociety.controller;

import cellsociety.Coordinate;
import cellsociety.State;
import cellsociety.model.statehandlers.StateHandler;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


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
      throws CsvValidationException, IOException, WrongFileTypeException, NumberFormatException,
      IndexOutOfBoundsException {
    this.myStateHandler = stateHandler;
    isFileTypeCorrect(f, CSV_FILE_TYPE);
    myFile = f;
    statesAsInts = parse();
  }

  /**
   * Reads in initial states from CSV, checks for any issues.
   * @return
   * @throws IOException
   * @throws CsvValidationException
   * @throws NumberFormatException
   * @throws IndexOutOfBoundsException
   */
  private int[][] parse()
      throws IOException, CsvValidationException, NumberFormatException,
      IndexOutOfBoundsException {
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

      }
      row += 1;
    }
    return outputArray;
  }

  /**
   * Validates that a particular cell is valid
   * @param line the line in the cell of interest
   * @param index the index of the component
   * @throws NumberFormatException non-integer input
   * @throws IndexOutOfBoundsException input shorter than expected
   */
  private void validateCell(String[] line, int index)
      throws NumberFormatException,
      IndexOutOfBoundsException {
    int value = Integer.parseInt(line[index]);
    myStateHandler.getMapping(value);
  }

  /**
   * Reads out the dimensions from the first line of the CSV, catches any exceptions.
   * @param myCSVReader
   */
  private void setDimensions(CSVReader myCSVReader) {

    String[] firstLine = null;

    try {
      firstLine = myCSVReader.readNext();
    } catch (Exception e) {
      throw new RuntimeException(String.format("Empty file passed for initial states: %s", myFile));
    }

    int numRows = 0;
    int numCols = 0;

    try {
      numRows = Integer.parseInt(firstLine[NUM_ROWS_INDEX]);
    } catch (Exception e) {
      throw new RuntimeException(String.format("Missing number of rows in %s", myFile));
    }
    try {
      numCols = Integer.parseInt(firstLine[NUM_COLS_INDEX]);
    } catch (Exception e) {
      throw new RuntimeException(String.format("Missing number of columns in %s", myFile));
    }

    myNumRows = numRows;
    myNumCols = numCols;
  }


  /**
   * Class to instantiate an object of a given cell state
   *
   * @param coord the x,y coordinate in the grid of cells
   * @return the newly instantiated state object
   */
  public State createStateInstance(Coordinate coord) {
    int x = coord.x();
    int y = coord.y();
    int valOfState = statesAsInts[x][y];
    Enum state = myStateHandler.getMapping(valOfState);

    return myStateHandler.getStateInstance(state);
  }

  /**
   * A method for testing that the states were read in correctly.
   *
   * @param myCoord the x,y position of a cell's coordinate
   * @return the integer (denoting the state) at a specific coordinate
   */
  protected int getStateValue(Coordinate myCoord) {
    int x = myCoord.x();
    int y = myCoord.y();

    return statesAsInts[x][y];
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
