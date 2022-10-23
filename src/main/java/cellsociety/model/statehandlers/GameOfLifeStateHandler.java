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
  Map<Integer, Class> stateOfValue;

  public GameOfLifeStateHandler() {
//    super(PROPERTIES);

    stateOfValue = new HashMap<>();

    stateOfValue.put(1, Alive.class);
    stateOfValue.put(0, Dead.class);
  }

  public State figureOutNextState(Neighborhood currNeighborhood) {
    int liveNeighbors = currNeighborhood.count(new Alive());
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
