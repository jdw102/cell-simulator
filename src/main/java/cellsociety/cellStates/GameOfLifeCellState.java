package cellsociety.cellStates;

import cellsociety.State;

/**
 * Contains States for the GameOfLife
 */
public enum GameOfLifeCellState implements State {
  DEAD,
  ALIVE;

  /**
   * TODO: Change this implementation. Should we return another enum? Should this enum be located in a different file,
   * with other enum options too?
   *
   * @return type of game
   */
  @Override
  public Enum getSimulationType() {
    enum temp {GAME_OF_LIFE}
    return temp.GAME_OF_LIFE;
  }

  /**
   * Not currently sure how to implement this. Isn't the value of the enum itself equivalent to "the
   * state" of this enum? If that's the case, I don't see why we need this method. I only know the
   * very basics of enums
   *
   * @return null
   */
  @Override
  public <T extends Enum<T>> T getState() {
    return null;
  }

}