package cellsociety.model;

import cellsociety.model.statehandlers.StateHandler;
import java.util.ArrayList;
import java.util.List;

public class WatorGridModel extends DefaultGridModel {
  private final NeighborhoodsLoader myNeighborhoodsLoader;
  private final StateHandler myStateHandler;

  public WatorGridModel(NeighborhoodsLoader neighborhoodsLoader, StateHandler stateHandler) {
    super(neighborhoodsLoader, stateHandler);
    myNeighborhoodsLoader = neighborhoodsLoader;
    myStateHandler = stateHandler;
  }


}
