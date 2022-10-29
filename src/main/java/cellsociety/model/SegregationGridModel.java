package cellsociety.model;

import cellsociety.model.statehandlers.GameOfLifeStateHandler;
import cellsociety.model.statehandlers.StateHandler;
import java.util.Collection;

public class SegregationGridModel extends DefaultGridModel {
  private NeighborhoodsLoader myNeighborhoodsLoader;
  private StateHandler myStateHandler;
  private Collection<CellModel> myEmptyStates;

  public SegregationGridModel(NeighborhoodsLoader neighborhoodsLoader, StateHandler stateHandler) {
    super(neighborhoodsLoader, stateHandler);
    myNeighborhoodsLoader = neighborhoodsLoader;
    myStateHandler = stateHandler;
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

  private void updateEmptyStates() {
//    myEmptyStates.clear();
//    for (int i = 0; i < myNeighborhoodsLoader.getNumNeighborhoods(); i++) {
//      if (myNeighborhoodsLoader.getNeighborhood(i).isState())
//    }
  }

  private void getRandomEmptyState() {

  }
}
