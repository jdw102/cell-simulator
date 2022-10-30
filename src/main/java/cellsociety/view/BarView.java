package cellsociety.view;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class BarView extends DataView {

  private final double SPACICNG = 50;
  private Map<String, Integer> count = new HashMap<>();
  private Map<String, Rectangle> rectangleMap = new HashMap<>();
  private int total;
  private HBox histogramBox;

  /**
   * Create a new view for the grid of cell.
   *
   * @param width  the width of the grid
   * @param height the height of the grid
   */
  public BarView(double width, double height) {
    super(width, height);
    count = new HashMap<>();
    rectangleMap = new HashMap<>();
    histogramBox = new HBox();
  }

  @Override
  public void addCell(CellView cellView, int rowIdx, int colIdx) {
    super.addCell(cellView, rowIdx, colIdx);
  }

  @Override
  public void clear() {
    super.clear();
    count.clear();
    rectangleMap.clear();
    histogramBox.getChildren().clear();
  }

  @Override
  public void setSimType(String type) {
    super.setSimType(type);
  }

  public HBox getHistogram() {
    return histogramBox;
  }

  public void makeHistogramBars() {
    StateColors colors = super.getStateColors();
    calculateCount();
    for (String s : count.keySet()) {
      Rectangle r = new Rectangle();
      r.setFill(colors.getColor(s));
      setRectangleDimensions(r, count.get(s));
      rectangleMap.put(s, r);
    }
    histogramBox.getChildren().addAll(rectangleMap.values());
  }

  private void calculateCount() {
    count.clear();
    while (super.hasNext()) {
      CellView c = super.next();
      String state = c.getStateName();
      count.putIfAbsent(state, 0);
      count.put(state, count.get(state) + 1);
      total++;
    }
    super.resetIterator();
  }

  public void updateHistogram() {
    calculateCount();
    for (String s : rectangleMap.keySet()) {
      int num = count.get(s);
      Rectangle r = rectangleMap.get(s);
      setRectangleDimensions(r, num);
    }
  }

  private void setRectangleDimensions(Rectangle r, double num) {
    double width = SPACICNG;
    double height = (num / total) * super.getGridHeight();
    r.setWidth(width);
    r.setHeight(height);
  }

}
