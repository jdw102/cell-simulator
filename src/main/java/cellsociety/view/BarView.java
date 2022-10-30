package cellsociety.view;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class BarView extends DataView {

  private final double BUFFER = 50;
  private final double HOVER_SCALE = 1.01;
  private Map<String, Integer> count = new HashMap<>();
  private Map<String, Rectangle> rectangleMap = new HashMap<>();
  private int total;
  private HBox histogramBox;
  private BorderPane borderPane;

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
    histogramBox.getStyleClass().add("histogram-box");
    borderPane = new BorderPane();
  }

  @Override
  public void addCell(CellView cellView, int rowIdx, int colIdx) {
    super.addCell(cellView, rowIdx, colIdx);
    total++;
  }

  @Override
  public void clear() {
    super.clear();
    count.clear();
    rectangleMap.clear();
    histogramBox.getChildren().clear();
    total = 0;
  }

  @Override
  public void resize(double width, double height) {
    super.resize(width, height);
    for (String s : rectangleMap.keySet()) {
      Rectangle r = rectangleMap.get(s);
      setRectangleDimensions(r, count.get(s));
    }
  }

  public void makeHistogramBars() {
    StateColors colors = super.getStateColors();
    calculateCount();
    for (String s : count.keySet()) {
      Rectangle r = new Rectangle();
      r.setFill(colors.getColor(s));
      setRectangleDimensions(r, count.get(s));
      rectangleMap.put(s, r);
      r.setOnMouseEntered(event -> onRectangleHover(r));
      r.setOnMouseExited(event -> offRectangleHover(r));
      attachTooltip(s, r);
    }
    histogramBox.getChildren().addAll(rectangleMap.values());
    histogramBox.setAlignment(Pos.BOTTOM_CENTER);
    borderPane.setBottom(histogramBox);
  }

  private void calculateCount() {
    count.clear();
    while (super.getStateColors().hasNext()) {
      count.put(super.getStateColors().next(), 0);
    }
    super.getStateColors().resetIterator();
    while (super.hasNext()) {
      CellView c = super.next();
      String state = c.getStateName();
      count.put(state, count.get(state) + 1);
    }
    super.resetIterator();
  }

  public void updateHistogram() {
    calculateCount();
    for (String s : rectangleMap.keySet()) {
      int num = count.get(s);
      Rectangle r = rectangleMap.get(s);
      setRectangleDimensions(r, num);
      attachTooltip(s, r);
    }
  }

  private void setRectangleDimensions(Rectangle r, double num) {
    double width = super.getWidth() / count.keySet().size() - BUFFER;
    double height = (num / total) * super.getHeight();
    r.setWidth(width);
    r.setHeight(height);
  }

  public void updateColors() {
    while (super.getStateColors().hasNext()) {
      String state = super.getStateColors().next();
      Rectangle r = rectangleMap.get(state);
      r.setFill(super.getStateColors().getColor(state));
    }
    super.getStateColors().resetIterator();

  }

  @Override
  public Node getNode() {
    return borderPane;
  }

  private void onRectangleHover(Rectangle r) {
    r.setScaleX(HOVER_SCALE);
  }

  private void offRectangleHover(Rectangle r) {
    r.setScaleX(1 / HOVER_SCALE);
  }

  private void attachTooltip(String state, Rectangle r) {
    Tooltip tooltip = new Tooltip(String.format("%s: %s", state, count.get(state)));
    Tooltip.install(r, tooltip);
  }
}
