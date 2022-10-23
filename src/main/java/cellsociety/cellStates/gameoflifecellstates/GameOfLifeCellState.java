package cellsociety.cellStates.gameoflifecellstates;


/**
 * Contains States for the GameOfLife
 */
public enum GameOfLifeCellState {
  DEAD,
  ALIVE;

  public GameOfLifeCellState getNextState() {
    return GameOfLifeCellState.DEAD;
  }


}