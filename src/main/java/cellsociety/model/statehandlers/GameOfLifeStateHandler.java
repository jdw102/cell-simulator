package cellsociety.model.statehandlers;

import cellsociety.State;
import cellsociety.cellStates.gameoflifecellstates.Alive;
import cellsociety.cellStates.gameoflifecellstates.Dead;
import cellsociety.cellStates.gameoflifecellstates.GameOfLifeCellState;
import cellsociety.model.Neighborhood;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GameOfLifeStateHandler extends StateHandler {

  //  private static final ResourceBundle PROPERTIES = ResourceBundle.getBundle();
//  private static enum STATES = GameOfLifeCellState;
  private Map<Integer, Class> stateOfValue;
  private static final String PROPERTIES_FILE = "GameOfLifeStateHandler";
  private static final String STATES_PACKAGE = "cellsociety.cellstates.gameoflifecellstates";

  public GameOfLifeStateHandler() {
    super(GameOfLifeCellState.values(), PROPERTIES_FILE, STATES_PACKAGE);

    stateOfValue = new HashMap<>();

    stateOfValue.put(1, Alive.class);
    stateOfValue.put(0, Dead.class);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    int liveNeighbors = currNeighborhood.count(GameOfLifeCellState.ALIVE);
    if ((currNeighborhood.isState(GameOfLifeCellState.ALIVE) && liveNeighbors == 2)
        || liveNeighbors == 3) {
      return new Alive();
    } else {
      return new Dead();
    }
  }

  public Class getMapping(int stateValue) {
    return stateOfValue.get(stateValue);
  }

}
