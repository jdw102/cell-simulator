package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellstates.gameoflifecellstates.AliveState;
import cellsociety.cellstates.gameoflifecellstates.DeadState;
import cellsociety.cellstates.gameoflifecellstates.GameOfLifeCellState;
import cellsociety.model.Neighborhood;
import java.util.HashMap;
import java.util.Map;

public class GameOfLifeStateHandler extends StateHandler {

  //  private static final ResourceBundle PROPERTIES = ResourceBundle.getBundle();
//  private static enum STATES = GameOfLifeCellState;
  private Map<Integer, Class> stateOfValue;

  private static final String STATES_PACKAGE = "cellsociety.cellstates.gameoflifecellstates.";
  private static final String HANDLER_NAME = "GameOfLifeStateHandler";

  public GameOfLifeStateHandler() throws RuntimeException {
    super(GameOfLifeCellState.values(), HANDLER_NAME, STATES_PACKAGE);

    stateOfValue = new HashMap<>();

    stateOfValue.put(1, AliveState.class);
    stateOfValue.put(0, DeadState.class);

    System.out.println("Successfully instantiated");
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    int liveNeighbors = currNeighborhood.count(GameOfLifeCellState.ALIVE);
    if ((currNeighborhood.isState(GameOfLifeCellState.ALIVE) && liveNeighbors == 2)
        || liveNeighbors == 3) {
      return getStateInstance(GameOfLifeCellState.ALIVE);
    } else {
      return getStateInstance(GameOfLifeCellState.DEAD);
    }
  }


}
