package cellsociety.cellstates.rockpaperscissorcellstates;

import cellsociety.State;

public class RockState implements State {
  @Override
  public Enum getStateEnum() {
    return RockPaperScissorCellState.ROCK;
  }
}
