package cellsociety.model;

import cellsociety.State;
import cellsociety.cellstates.watorworldcellstates.EmptyState;
import cellsociety.cellstates.watorworldcellstates.FishState;
import cellsociety.cellstates.watorworldcellstates.WatorWorldCellState;
import cellsociety.cellstates.watorworldcellstates.SharkState;
import cellsociety.model.statehandlers.StateHandler;

public class WatorWorldGridModel extends DefaultGridModel {

  public static final int REPRODUCTION_THRESHOLD = 10;
  public static final int ENERGY_THRESHOLD = 0;
  public static final int ENERGY_PER_FISH_EATEN = 3;
  private final NeighborhoodsLoader myNeighborhoodsLoader;
  private final StateHandler myStateHandler;

  public WatorWorldGridModel(NeighborhoodsLoader neighborhoodsLoader, StateHandler stateHandler) {
    super(neighborhoodsLoader, stateHandler);
    myNeighborhoodsLoader = neighborhoodsLoader;
    myStateHandler = stateHandler;
  }

  @Override
  protected void determineNextStates() {
    setNextStateForFish();
    setNextStateForSharks();
  }

  private void setNextStateForFish() {
    int numNeighborhoods = myNeighborhoodsLoader.getNumNeighborhoods();

    // loop through all Neighborhoods
    for (int i = 0; i < numNeighborhoods; i++) {
      Neighborhood currNeighborhood = myNeighborhoodsLoader.getNeighborhood(i);
      State currState = currNeighborhood.getState();

      // Only figure out next states for fish
      if (currNeighborhood.getStateEnum().equals(WatorWorldCellState.FISH)) {
        FishState fishState = (FishState) currState;
        State nextState = myStateHandler.figureOutNextState(currNeighborhood);

        // Only move fish if this currNeighborhood's next state is EMPTY, meaning the
        // fish is moving out of its current spot
        if (nextState.getStateEnum().equals(WatorWorldCellState.EMPTY)) {

          // Move the fish into a neighbor with an EMPTY next state. If not possible,
          // set the current neighborhood's next state back to the current Fish state
          boolean fishMoved = currNeighborhood.setNextStateOfRandomNeighborWithNextState(
              WatorWorldCellState.EMPTY, currState);
          if (!fishMoved) {
            currNeighborhood.updateCellNextState(fishState);
          } else {

            // If the fish is ready to reproduce, leave behind a new fish in the current
            // neighborhood
            if (fishState.getReproductionCounter() > REPRODUCTION_THRESHOLD) {
              currNeighborhood.updateCellNextState(new FishState());
              fishState.setReproductionCounter(-1);
            } else {
              currNeighborhood.updateCellNextState(new EmptyState());
            }
          }
        }

        fishState.setReproductionCounter(fishState.getReproductionCounter() + 1);
      }
    }
  }

  private void setNextStateForSharks() {
    int numNeighborhoods = myNeighborhoodsLoader.getNumNeighborhoods();

    // loop through all Neighborhoods
    for (int i = 0; i < numNeighborhoods; i++) {
      Neighborhood currNeighborhood = myNeighborhoodsLoader.getNeighborhood(i);
      State currState = currNeighborhood.getState();

      // Only figure out next states for fish
      if (currNeighborhood.getStateEnum().equals(WatorWorldCellState.SHARK)) {
        SharkState sharkState = (SharkState) currState;

        // If shark's energy threshold is less than min threshold, it dies
        if (sharkState.getEnergyCounter() <= ENERGY_THRESHOLD) {
          currNeighborhood.updateCellNextState(new EmptyState());
          continue;
        }

        // Shark stays alive
        else {
          State nextState = myStateHandler.figureOutNextState(currNeighborhood);

          // Only move shark if this currNeighborhood's next state is EMPTY, meaning the
          // shark is moving out of its current spot
          if (nextState.getStateEnum().equals(WatorWorldCellState.EMPTY)) {

            boolean ateFish = currNeighborhood.setNextStateOfRandomNeighborWithNextState(
                WatorWorldCellState.FISH, sharkState);
            if (!ateFish) {
              currNeighborhood.setNextStateOfRandomNeighborWithNextState(
                  WatorWorldCellState.EMPTY, sharkState);
            } else {
              sharkState.setEnergyCounter(sharkState.getEnergyCounter() + ENERGY_PER_FISH_EATEN);
            }

            currNeighborhood.updateCellNextState(new EmptyState());
          }
        }

        // If the fish is ready to reproduce, leave behind a new fish in the current
        // neighborhood
        if (sharkState.getReproductionCounter() > REPRODUCTION_THRESHOLD) {
          currNeighborhood.updateCellNextState(new SharkState());
          sharkState.setReproductionCounter(-1);
        }

        sharkState.setReproductionCounter(sharkState.getReproductionCounter() + 1);
        sharkState.setEnergyCounter(sharkState.getEnergyCounter() - 1);
      }
    }
  }

}