package cellsociety.controller;

public class InvalidSimulationException extends RuntimeException {

  private static final String INVALID_SIMULATION_MESSAGE = "%s simulation not recognized.";

  public InvalidSimulationException(String simulationType) {
    super(String.format(INVALID_SIMULATION_MESSAGE, simulationType));
  }

}
