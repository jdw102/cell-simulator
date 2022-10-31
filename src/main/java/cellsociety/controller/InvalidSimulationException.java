package cellsociety.controller;

/**
 * Exception thrown when user input a simulation name that is not an option
 *
 * @author Mazen Selim
 */
public class InvalidSimulationException extends RuntimeException {

  private static final String INVALID_SIMULATION_MESSAGE = "%s simulation not recognized.";

  public InvalidSimulationException(String simulationType) {
    super(String.format(INVALID_SIMULATION_MESSAGE, simulationType));
  }
}
