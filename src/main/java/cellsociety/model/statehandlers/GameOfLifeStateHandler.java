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
  private static final String PROPERTIES_FILE = "GameOfLifeStateHandler";
  private static final String STATES_PACKAGE = "cellsociety.cellstates.gameoflifecellstates.";

  public GameOfLifeStateHandler() {
    super(GameOfLifeCellState.values(), PROPERTIES_FILE, STATES_PACKAGE);

    stateOfValue = new HashMap<>();

    stateOfValue.put(1, AliveState.class);
    stateOfValue.put(0, DeadState.class);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    int liveNeighbors = currNeighborhood.count(GameOfLifeCellState.ALIVE);
    if ((currNeighborhood.isState(GameOfLifeCellState.ALIVE) && liveNeighbors == 2)
        || liveNeighbors == 3) {
      return new AliveState();
    } else {
      return new DeadState();
    }
  }

  public Class getMapping(int stateValue) {
    return stateOfValue.get(stateValue);
  }

}
