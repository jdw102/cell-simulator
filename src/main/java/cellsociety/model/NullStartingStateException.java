package cellsociety.model;

public class NullStartingStateException extends RuntimeException {

  public static final String WRONG_FILE_TYPE_MESSAGE = "The starting state of a CellModel cannot be null";

  public NullStartingStateException() {
    super(WRONG_FILE_TYPE_MESSAGE);
  }
}
