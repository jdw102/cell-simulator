package cellsociety.controller;

/**
 * Exception for when a user gives a file that is not of the expected type
 *
 * @author Daniel Feinblatt
 */
public class WrongFileTypeException extends RuntimeException {

  public static final String WRONG_FILE_TYPE_MESSAGE = "Must provide a file of type %s, but was provided a file of type %s";

  public WrongFileTypeException(String expectedFileType, String providedFileType) {
    super(String.format(WRONG_FILE_TYPE_MESSAGE, expectedFileType, providedFileType));
  }
}
