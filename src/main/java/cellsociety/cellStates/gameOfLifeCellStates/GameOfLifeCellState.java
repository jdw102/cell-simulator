package cellsociety.cellStates.gameOfLifeCellStates;

import cellsociety.State;

/**
 * Contains States for the GameOfLife
 */
public abstract class GameOfLifeCellState implements State {
    protected enum CellStates {
        DEAD,
        ALIVE;
    }

    /**
     * TODO: Change this implementation. Should we return another enum? Should this enum be located in a different file,
     * with other enum options too?
     * @return type of game
     */
    @Override
    public Enum getSimulationType() {
        enum temp { GAME_OF_LIFE }
        return temp.GAME_OF_LIFE;
    }

}