package cellsociety.view;

import cellsociety.Observer;
import cellsociety.model.CellModel;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;
/**
 * A view class that observes the cell model and changes color based on the state of the model.
 */
public class CellView implements Observer {
  private Pane cellPane;
  private Rectangle rectangle;
  private final int BORDER_SIZE = 1;
  private CellModel model;
  private ResourceBundle colorBundle;

  public CellView(){
    rectangle = new Rectangle();
    cellPane = new Pane(rectangle);
    cellPane.getStyleClass().add("cell-pane");
  }
  @Override
  public void update() {
//    rectangle.setFill((Paint) colorOptions.getObject(model.getName().name()));
  }
  public Pane getCellPane(){
    return cellPane;
  }
  public Rectangle getRectangle(){
    return rectangle;
  }
  public void setDimensions(int width, int height){
    rectangle.setWidth(width);
    rectangle.setHeight(height);

  }
}
