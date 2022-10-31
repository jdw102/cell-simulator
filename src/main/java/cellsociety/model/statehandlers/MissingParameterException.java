package cellsociety.model.statehandlers;

public class MissingParameterException extends Exception {

  private static final String MISSING_PARAMTER_MESSAGE = "Could not find a default parameter for "
      + "simulation %s.";

  public MissingParameterException(String simType) {
    super(String.format(MISSING_PARAMTER_MESSAGE, simType));
  }

}
