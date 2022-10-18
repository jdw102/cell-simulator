package cellsociety.cellStates.gameOfLifeCellStates;

public class Alive extends GameOfLifeCellState {

  @Override
  public Enum getStateEnum() {
    return CellStates.ALIVE;
  }
}
