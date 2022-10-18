package cellsociety.model;

import cellsociety.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintains a 2D List of CellModels
 */
public class GridModel {
    List<List<CellModel>> myCells;
    StateHandler myStateFigureOuter;

    /**
     * Creates the GridModel
     * @param cellStateGrid 2D List of initial states the cells will contain
     * @param stateFigureOuter the StateFigureOuter that determines the states of cells each time
     *                         GridModel determines the next state of the cells in the grid
     */
    public GridModel(List<List<State>> cellStateGrid, StateHandler stateFigureOuter) {
        loadMyCells(cellStateGrid);
        myStateFigureOuter = stateFigureOuter;
    }

    /**
     * Determine the next state of the grid.
     */
    public void iterateCellStates() {
        determineNextStates();
        setCurrentStatesToNextStates();
    }

    private void determineNextStates() {
        for (int i = 0; i < myCells.size(); i++) {
            for (int k = 0; k < myCells.get(i).size(); k++) {
                CellModel currentCell = myCells.get(i).get(k);
                currentCell.setMyNextState(myStateFigureOuter.figureOutNextState(i, k, myCells));
            }
        }
    }

    private void setCurrentStatesToNextStates() {
        for (List<CellModel> myCell : myCells) {
            for (CellModel currentCell : myCell) {
                currentCell.setMyCurrentState(currentCell.getMyNextState());
            }
        }
    }

    private void loadMyCells(List<List<State>> cellGrid) {
        myCells = new ArrayList<>();
        for (List<State> states : cellGrid) {
            List<CellModel> newRow = new ArrayList<>();
            loadRow(newRow, states);
            myCells.add(newRow);
        }
    }

    // helper for loadMyCells
    private void loadRow(List<CellModel> newRow, List<State> states) {
        for (State state : states) {
            newRow.add(new CellModel(state));
        }
    }

}
