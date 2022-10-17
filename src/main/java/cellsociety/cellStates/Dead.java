package cellsociety.cellStates;

import cellsociety.State;

public class Dead implements State {

  public Dead() {

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
