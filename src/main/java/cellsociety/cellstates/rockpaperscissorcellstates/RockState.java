package cellsociety.cellstates.rockpaperscissorcellstates;

import cellsociety.State;

/**
 * @author Mazen Selim
 * Represents rock in the rock paper scissor simulation
 */
public class RockState implements State {
  @Override
  public Enum getStateEnum() {
    return RockPaperScissorCellState.ROCK;
  }
}
