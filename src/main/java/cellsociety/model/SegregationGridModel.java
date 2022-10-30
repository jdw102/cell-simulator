package cellsociety.model;

import cellsociety.State;
import cellsociety.cellstates.segregationcellstates.SegregationCellState;
import cellsociety.model.statehandlers.SegregationStateHandler;
import cellsociety.model.statehandlers.StateHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SegregationGridModel extends DefaultGridModel {

  private NeighborhoodsLoader myNeighborhoodsLoader;
  private StateHandler myStateHandler;
  private List<Neighborhood> myNeighborhoodsWithEmptyNextState;

  public SegregationGridModel(NeighborhoodsLoader neighborhoodsLoader, StateHandler stateHandler) {
    super(neighborhoodsLoader, stateHandler);
    myNeighborhoodsLoader = neighborhoodsLoader;
    myStateHandler = stateHandler;
    myNeighborhoodsWithEmptyNextState = new ArrayList<>();
  }

  @Override
  protected void determineNextStates() {

    int numNeighborhoods = myNeighborhoodsLoader.getNumNeighborhoods();
    for (int i = 0; i < numNeighborhoods; i++) {
      updateListOfNeighborhoodsWithEmptyNextState();
      Neighborhood currNeighborhood = myNeighborhoodsLoader.getNeighborhood(i);

      State currState = currNeighborhood.getState();
      if (currState.getStateEnum().equals(SegregationCellState.EMPTY)) {
        continue;
      }

      // check what the next state of this neighborhood should be, and set it
      State nextState = myStateHandler.figureOutNextState(currNeighborhood);
      currNeighborhood.updateCellNextState(nextState);

      // if nextState is empty, the cell must need to move (since the currState
      // isn't empty). In this case, get a random neighborhood with an empty next state,
      // and set its next state to currState
      if (nextState.getStateEnum().equals(SegregationCellState.EMPTY)) {
        Neighborhood targetNeighborhood = getRandomNeighborhoodWithEmptyNextState(currNeighborhood);
        targetNeighborhood.updateCellNextState(currState);
      }
    }
  }

  private void updateListOfNeighborhoodsWithEmptyNextState() {
    myNeighborhoodsWithEmptyNextState.clear();
    for (int i = 0; i < myNeighborhoodsLoader.getNumNeighborhoods(); i++) {
      if (myNeighborhoodsLoader.getNeighborhood(i).isState(SegregationCellState.EMPTY)) {
        myNeighborhoodsWithEmptyNextState.add(myNeighborhoodsLoader.getNeighborhood(i));
      }
    }
  }

  private Neighborhood getRandomNeighborhoodWithEmptyNextState(Neighborhood neighborhood) {
    // handle edge case of neighborhood being the only CellModel with state of EMPTY
    if (myNeighborhoodsWithEmptyNextState.size() == 0) {
      if (neighborhood.nextStateIsState(SegregationCellState.EMPTY)) {
        return neighborhood;
      } else {
        // should never happen unless improper usage of method
        throw new RuntimeException(
            "There are no neighborhoods with a nextState of EMPTY in this grid");
      }
    }

    // typical case
    Random rand = new Random();
    return myNeighborhoodsWithEmptyNextState.get(
        rand.nextInt(0, myNeighborhoodsWithEmptyNextState.size()));
  }
}
