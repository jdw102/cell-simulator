package cellsociety.view;

public class StateColorsFormatException extends Exception {

  public static final String STATE_COLORS_FORMAT_MESSAGE = "Incorrect color formatting in simulation of title %s";

  public StateColorsFormatException(String simTitle) {
    super(String.format(STATE_COLORS_FORMAT_MESSAGE, simTitle));
  }
}
