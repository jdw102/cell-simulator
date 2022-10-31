package cellsociety.cellstates.rockpaperscissorcellstates;

import cellsociety.State;

/**
 * @author Mazen Selim
 * Represents scissor in the rock paper scissor simulation
 */
public class ScissorState implements State {

  @Override
  public Enum getStateEnum() {
    return RockPaperScissorCellState.SCISSOR;
  }
}
