package cellsociety.model;

import cellsociety.State;

import java.util.List;

public interface StateFigureOuter {
    State figureOutNextState(int cellRowIndex, int cellColIndex, List<List<CellModel>> cells);
}
