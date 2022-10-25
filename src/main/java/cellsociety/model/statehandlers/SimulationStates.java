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

  public Enum getNextEnum(Enum currState) {
    int stateIdx = getEnumIndex(currState);
    return states[(stateIdx + 1) % states.length];
  }

  private int getEnumIndex(Enum state) {
    for (int i = 0; i < states.length; i++) {
      if (states[i].equals(state)) {
        return i;
      }
    }
    return -1;
  }

  public Enum getEnum(String enumCandidate) throws RuntimeException {
    for (Enum e : states) {
      if (e.toString().equalsIgnoreCase(enumCandidate)) {
        return e;
      }
    }
    throw new RuntimeException(String.format("No such Enum: %s", enumCandidate));
  }
}
