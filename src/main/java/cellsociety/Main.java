package cellsociety;

import cellsociety.controller.Controller;
import cellsociety.view.DisplayView;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  // kind of data files to look for
  public static final String INTERNAL_CONFIGURATION = "cellsociety.Configuration";
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
  public static final Dimension MIN_SIZE = new Dimension(300, 300);
  public static final String DEFAULT_LANGUAGE = "English";
  public static final String TITLE = "CellSociety";

  /**
   * @see Application#start(Stage)
   */
  @Override
  public void start(Stage primaryStage) {
    DisplayView view = new DisplayView(DEFAULT_LANGUAGE, primaryStage);
    Controller controller = new Controller(view);
    view.setController(controller);
    // give the window a title
    primaryStage.setTitle(TITLE);
    //add our user interface components to Frame and show it
    primaryStage.setScene(view.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
    primaryStage.setMinHeight(MIN_SIZE.height);
    primaryStage.setMinWidth(MIN_SIZE.width);
    primaryStage.show();
  }

  /**
   * A method to test getting internal resources.
   */
  public double getVersion() {
    ResourceBundle resources = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);
    return Double.parseDouble(resources.getString("Version"));
  }

  /**
   * Default version of main() is actually included within JavaFX!
   */
}
