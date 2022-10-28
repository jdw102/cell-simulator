package cellsociety.cellstates.rockpaperscissorcellstates;

import cellsociety.State;

public class ScissorState implements State {

  @Override
  public Enum getStateEnum() {
    return RockPaperScissorCellState.SCISSOR;
  }
}
