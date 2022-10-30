package cellsociety.model;

import cellsociety.model.statehandlers.StateHandler;

public class PredatorPreyGridModel extends DefaultGridModel {
  private final NeighborhoodsLoader myNeighborhoodsLoader;
  private final StateHandler myStateHandler;

  public PredatorPreyGridModel(NeighborhoodsLoader neighborhoodsLoader, StateHandler stateHandler) {
    super(neighborhoodsLoader, stateHandler);
    myNeighborhoodsLoader = neighborhoodsLoader;
    myStateHandler = stateHandler;
  }


}
