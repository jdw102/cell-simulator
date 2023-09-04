package cellsociety.view;

/**
 * Exception thrown when user inputs an invalid color value
 *
 * @author Jerry Worthy
 */
public class StateColorsFormatException extends Exception {

  public static final String STATE_COLORS_FORMAT_MESSAGE = "Incorrect color formatting in simulation with title %s";

  public StateColorsFormatException(String simTitle) {
    super(String.format(STATE_COLORS_FORMAT_MESSAGE, simTitle));
  }
}
