package cellsociety.model.statehandlers;

public class InvalidParameterException extends Exception {

  private static final String INVALID_PARAMETER_MESSAGE = "Cannot reconcile parameter %s passed to"
      + "simulation %s.";

  public InvalidParameterException(String parameter, String simulation) {
    super(String.format(INVALID_PARAMETER_MESSAGE, parameter, simulation));
  }
}
