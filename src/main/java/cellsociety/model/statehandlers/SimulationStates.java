package cellsociety.model.statehandlers;

public class SimulationStates {

  private Enum[] states;

  public SimulationStates(Class<?> simulationStates) {
    try {
      states = (Enum[]) simulationStates.getEnumConstants();
    } catch (Exception e) {
      throw new RuntimeException("Fatal Error: Non-Enum passed as Enum. Cannot recover from this. "
          + "Game over.");
    }
  }

  /**
   * Cycles to the next Enum in the simulation
   * @param currState
   * @return
   */
  public Enum getNextEnum(Enum currState) {
    int stateIdx = getEnumIndex(currState);
    return states[(stateIdx + 1) % states.length];
  }

  /**
   * Gets the index of an Enum in the array of enums
   * @param state
   * @return
   */
  private int getEnumIndex(Enum state) {
    for (int i = 0; i < states.length; i++) {
      if (states[i].equals(state)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Gets the corresponding Enum to a string
   * @param enumCandidate
   * @return
   * @throws RuntimeException
   */
  public Enum getEnum(String enumCandidate) throws RuntimeException {
    for (Enum e : states) {
      if (e.toString().equalsIgnoreCase(enumCandidate)) {
        return e;
      }
    }
    throw new RuntimeException(String.format("No such Enum: %s", enumCandidate));
  }
}
