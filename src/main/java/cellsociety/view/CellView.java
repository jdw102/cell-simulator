package cellsociety.view;

import cellsociety.Observer;
import cellsociety.model.CellModel;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

public class CellView implements Observer {
  private Pane cellPane;
  private Rectangle rectangle;
  private final int BORDER_SIZE = 1;
  private CellModel model;
  private ResourceBundle colorOptions;
  public CellView(double width, double height, ResourceBundle colors){
    colorOptions = colors;
    rectangle = new Rectangle(width - BORDER_SIZE, height - BORDER_SIZE);
    rectangle.setFill(Color.WHITE);
//    rectangle.setFill((Paint) colorOptions.getObject(model.getName().name()));
    cellPane = new Pane(rectangle);
    cellPane.getStyleClass().add("cell-pane");
  }
  @Override
  public void update() {
    rectangle.setFill((Paint) colorOptions.getObject(model.getName().name()));
  }
  public Pane getCellPane(){
    return cellPane;
  }
  public Rectangle getRectangle(){
    return rectangle;
  }
}
