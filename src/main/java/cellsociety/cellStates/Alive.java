package cellsociety.cellStates;

import cellsociety.State;

public class Alive implements State {

  public Alive() {

  }

  @Override
  public <T extends Enum<T>> T getState() {
    return null;
  }

  @Override
  public Enum getSimulationType() {
    return null;
  }
}
