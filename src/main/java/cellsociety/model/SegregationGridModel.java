package cellsociety.model;

import cellsociety.cellstates.segregationcellstates.SegregationCellState;
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
    // keep list of all empty states
      // options: - loop through NeighborhoodsLoader and see which states are empty
      //          - have functionality in NeighborhoodsLoader to keep list of all empty states,
      //          then get a random state
      //          - keep list of empty slots and update it based on when you call the toggle
      //          method or call updateState
    // get a random empty state


  }

  private void updateListOfNeighborhoodsWithEmptyNextState() {
    myNeighborhoodsWithEmptyNextState.clear();
    for (int i = 0; i < myNeighborhoodsLoader.getNumNeighborhoods(); i++) {
      if (myNeighborhoodsLoader.getNeighborhood(i).isState(SegregationCellState.EMPTY)) {
        myNeighborhoodsWithEmptyNextState.add(myNeighborhoodsLoader.getNeighborhood(i));
      }
    }
  }

  private Neighborhood getRandomNeighborhoodWithEmptyNextState() {
    Random rand = new Random();
    return myNeighborhoodsWithEmptyNextState.get(rand.nextInt(0, myNeighborhoodsWithEmptyNextState.size()));
  }
}
