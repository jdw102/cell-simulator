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
 *
 * @author Mazen Selim
 * @author Daniel Feinblatt
 */
public class InitialStateReader extends FileParser {

  public static final String CSV_FILE_TYPE = "csv";
  private static final int NUM_ROWS_INDEX = 1;
  private static final int NUM_COLS_INDEX = 0;
  private final File myFile;
  private final int[][] statesAsInts;
  private final StateHandler myStateHandler;
  private int myNumRows;
  private int myNumCols;

  /**
   * Checks that the file received is a csv, then parses the file
   *
   * @param stateHandler Defines how the rules of interaction of the simulation
   * @param f            The file to read the initial state from
   * @throws CsvValidationException
   * @throws IOException
   * @throws WrongFileTypeException
   * @throws IncorrectInputException
   */
  public InitialStateReader(StateHandler stateHandler, File f)
      throws CsvValidationException, IOException, WrongFileTypeException, IncorrectInputException {
    this.myStateHandler = stateHandler;
    isFileTypeCorrect(f, CSV_FILE_TYPE);
    myFile = f;
    statesAsInts = parse();
  }

  /**
   * Reads in initial states from CSV, checks for any issues.
   *
   * @return 2d integer array of the values read from the initial state csv
   * @throws IOException
   * @throws CsvValidationException
   * @throws IncorrectInputException
   */
  private int[][] parse()
      throws IOException, CsvValidationException, IncorrectInputException {
    int[][] outputArray;
    CSVReader myCSVReader = null;
    myCSVReader = new CSVReader(new FileReader(myFile));
    setDimensions(myCSVReader);
    outputArray = new int[myNumRows][myNumCols];
    String[] nextLine;
    int row = 0;
    while ((nextLine = myCSVReader.readNext()) != null) {
      for (int col = 0; col < myNumCols; col++) {
        validateCell(nextLine, col);
        int current_val = Integer.parseInt(nextLine[col].strip());
        outputArray[row][col] = current_val;
      }
      row += 1;
    }
    return outputArray;
  }

  /**
   * Validates a cell to ensure its input can be processed
   *
   * @param line  the line in the cell of interest
   * @param index the index of the component
   * @throws IncorrectInputException
   */
  private void validateCell(String[] line, int index) throws IncorrectInputException {
    int value;
    try {
      value = Integer.parseInt(line[index].strip());
    } catch (NumberFormatException | IndexOutOfBoundsException e) {
      throw new IncorrectInputException(myFile.getName(), line[index]);
    }
    Enum stateChecker = myStateHandler.getMapping(value);
    if (stateChecker == null) {
      throw new IncorrectInputException(myFile.getName(), value);
    }
  }

  /**
   * Reads out the dimensions from the first line of the CSV, catches any exceptions.
   *
   * @param myCSVReader
   */
  private void setDimensions(CSVReader myCSVReader) throws IncorrectInputException {
    String[] firstLine = null;
    try {
      firstLine = myCSVReader.readNext();
    } catch (IOException | CsvValidationException e) {
      throw new IncorrectInputException(myFile.getName(), "any");
    }

    int numRows = 0;
    int numCols = 0;

    try {
      numRows = Integer.parseInt(firstLine[NUM_ROWS_INDEX].strip());
    } catch (IndexOutOfBoundsException | NumberFormatException e) {
      throw new IncorrectInputException(myFile.getName(), "rows");
    }
    try {
      numCols = Integer.parseInt(firstLine[NUM_COLS_INDEX].strip());
    } catch (IndexOutOfBoundsException | NumberFormatException e) {
      throw new IncorrectInputException(myFile.getName(), "columns");
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
