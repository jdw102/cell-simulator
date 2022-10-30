package cellsociety.cellstates.wator;

import cellsociety.State;

public class SharkState extends Animal implements State {
  public static final int STARTING_ENERGY_LEVEL = 3;
  private int energyCounter = STARTING_ENERGY_LEVEL;

  @Override
  public Enum getStateEnum() {
    return WatorCellState.SHARK;
  }

  public int getEnergyCounter() {
    return energyCounter;
  }

  public void setEnergyCounter(int num) {
    energyCounter = num;
  }

}
