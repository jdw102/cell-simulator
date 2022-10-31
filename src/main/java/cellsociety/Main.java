package cellsociety;

import cellsociety.controller.Controller;
import cellsociety.view.DisplayView;
import cellsociety.view.StartView;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  // kind of data files to look for
  public static final String DEFAULT_COLORS_PACKAGE = "cellsociety.sim_colors.";

  public static final String INTERNAL_CONFIGURATION = "cellsociety.Configuration";
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
  public static final Dimension MIN_SIZE = new Dimension(300, 300);
  public static final Dimension START_SIZE = new Dimension(400, 500);
  public static final String TITLE = "CellSociety";
  public static final String DEFAULT_RESOURCE_FOLDER = "/cellsociety/";
  public static final String DEFAULT_LANGUAGE_FOLDER = "languages/";
  public static final String DEFAULT_STYLESHEET_FOLDER = "stylesheets/";
  public static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
  public static final String DEFAULT_BLANK_SIMS_FOLDER = "blank_sims/";
  public static final String DEFAULT_SIM_COLORS_FOLDER = "/sim_colors";
  public static final String GRID_MODEL_CHOOSER_FOLDER = "gridmodel_chooser/";
  public static final String BLANK_SIM_TAG = "Blank.sim";
  public static final String DATA_FILE_SIM_EXTENSION = "*.sim";
  public static final String STYLESHEET_TAG = ".css";
  public static final String SETTINGS_PACKAGE = "Settings";
  public static final String PROPERTIES_PACKAGE = "statehandlers.";
  public static final String STATE_HANDLER_TAG = "StateHandler";
  public static final String CSV_FILE_EXTENSION = ".csv";
  public static final ResourceBundle SETTINGS = ResourceBundle.getBundle(
      DEFAULT_RESOURCE_PACKAGE + SETTINGS_PACKAGE);


  /**
   * @see Application#start(Stage)
   */
  @Override
  public void start(Stage primaryStage) {
    openStartView(primaryStage);
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

  private void openDisplayView(StartView startView, Stage stage) {
    stage.close();
    Stage newStage = new Stage();
    newStage.setTitle(TITLE);
    DisplayView view = new DisplayView(startView.getStartLanguage(), newStage);
    Controller controller = new Controller(view);
    view.setController(controller);
    newStage.setScene(view.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
    newStage.setMinHeight(MIN_SIZE.height);
    newStage.setMinWidth(MIN_SIZE.width);
    newStage.show();
  }

  private void openStartView(Stage stage) {
    StartView startView = new StartView();
    stage.setScene(startView.setUpScene(START_SIZE.width, START_SIZE.height,
        event -> openDisplayView(startView, stage)));
    stage.setMinHeight(MIN_SIZE.height);
    stage.setMinWidth(MIN_SIZE.width);
    stage.show();
  }
}
