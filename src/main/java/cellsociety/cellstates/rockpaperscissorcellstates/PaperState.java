package cellsociety.cellstates.rockpaperscissorcellstates;

import cellsociety.State;

/**
 * @author Mazen Selim
 * Represents paper in the rock paper scissor simulation
 */
public class PaperState implements State {

  @Override
  public Enum getStateEnum() {
    return RockPaperScissorCellState.PAPER;
  }
}
