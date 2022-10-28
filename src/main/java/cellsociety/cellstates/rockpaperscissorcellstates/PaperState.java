package cellsociety.cellstates.rockpaperscissorcellstates;

import cellsociety.State;

public class PaperState implements State {

  @Override
  public Enum getStateEnum() {
    return RockPaperScissorCellState.PAPER;
  }
}
