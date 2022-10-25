package cellsociety.controller;

public class IncorrectInputException extends RuntimeException {

  private static final String INCORRECT_INPUT_MESSAGE = "Incorrect or missing information in file"
      + "%s. Could not interpret %s data";

  private static final String INCORRECT_INT_MESSAGE = "Incorrect value passed in file %s: %d.\n"
      + "Integer out of bounds for the simulation. Review properties file for this simulation to "
      + "see valid inputs.";

  public IncorrectInputException(String fileName, String infoName) {
    super(String.format(INCORRECT_INPUT_MESSAGE, fileName, infoName));
  }

  public IncorrectInputException(String fileName, int actual) {
    super(String.format(INCORRECT_INT_MESSAGE, fileName, actual));

  }

}
