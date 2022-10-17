package cellsociety.view;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * A class that contains all the view and UI components.
 */
public class DisplayView {

  public static final String DEFAULT_RESOURCE_FOLDER = "/cellsociety/";
  private static final String STYLESHEET = "default.css";
  private static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
  private final ResourceBundle myResources;
  private final double HEIGHT_BUFFER = 170;
  private final double WIDTH_BUFFER = 50;
  private final Stage STAGE;
  private final FileChooser FILE_CHOOSER;
  private final String DATA_FILE_CSV_EXTENSION = "*.csv";
  private final GridInputs gridInputs;
  private final InputMaker inputMaker;
  private final HBox simInputsBox;
  private final InfoText infoText;
  private GridView cellGrid;
  private InfoPopUp infoPopUp;

  /**
   * Create a new view.
   *
   * @param language the language displayed by the components
   * @param stage    the stage displaying the scene
   */
  public DisplayView(String language, Stage stage) {
    STAGE = stage;
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    inputMaker = new InputMaker(myResources);
    gridInputs = new GridInputs(inputMaker);
    simInputsBox = makeSimInputsBox();
    infoText = new InfoText("Test Title", "Jerry Worthy", "Blah blah blah");
    FILE_CHOOSER = inputMaker.makeChooser(DATA_FILE_CSV_EXTENSION);
  }

  /**
   * Create the scene to display the view components.
   *
   * @param width  the width of the scene
   * @param height the height of the scene
   * @return the scene
   */
  public Scene makeScene(int width, int height) {
    cellGrid = new GridView(width - WIDTH_BUFFER, height - HEIGHT_BUFFER);
    STAGE.heightProperty().addListener(
        (obs, oldval, newVal) -> cellGrid.resizeGrid(STAGE.getWidth() - WIDTH_BUFFER,
            STAGE.getHeight() - HEIGHT_BUFFER));
    STAGE.widthProperty().addListener(
        (obs, oldval, newVal) -> cellGrid.resizeGrid(STAGE.getWidth() - WIDTH_BUFFER,
            STAGE.getHeight() - HEIGHT_BUFFER));
    BorderPane root = new BorderPane();
    root.setCenter(cellGrid.getGrid());
    root.setBottom(gridInputs.getContainer());
    root.setTop(simInputsBox);
    Scene scene = new Scene(root, width, height);
    scene.getStylesheets()
        .add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    infoPopUp = new InfoPopUp(infoText, myResources.getString("InfoPopUpTitle"),
        DEFAULT_RESOURCE_FOLDER + STYLESHEET, inputMaker);
    return scene;
  }

  /**
   * Creates the inputs relating to the loading and saving of simulations, contained within an
   * HBox.
   *
   * @return the HBox containing the inputs
   */
  private HBox makeSimInputsBox() {
    ComboBox<String> c = makeTypeSelector();
    inputMaker.attachTooltip("SimulationTypeTooltip", c);
    Button saveButton = inputMaker.makeButton("SaveButton", event -> System.out.println("Save"));
    Button importButton = inputMaker.makeButton("ImportButton", event -> openFile());
    Button infoButton = inputMaker.makeButton("InfoButton", event -> infoPopUp.open());
    inputMaker.attachTooltip("SaveButtonTooltip", saveButton);
    inputMaker.attachTooltip("ImportButtonTooltip", importButton);
    inputMaker.attachTooltip("InfoButtonTooltip", infoButton);
    HBox b = new HBox(saveButton, importButton, infoButton, c);
    b.getStyleClass().add("sim-inputs-container");
    return b;
  }

  /**
   * Opens a file and passes the file to the controller for simulation setup.
   */
  private void openFile() {
    File dataFile = FILE_CHOOSER.showOpenDialog(STAGE);
    if (dataFile != null) {
      //controller.setupSimulation(dataFile);
      showMessage(Alert.AlertType.INFORMATION, "");
    }
  }

  /**
   * Displays alert message to user.
   *
   * @param type    the alert type
   * @param message the message to display
   */
  // display given message to user using the given type of Alert dialog box
  private void showMessage(Alert.AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }

  /**
   * Creates combobox to select simulation type.
   *
   * @return the combo box
   */
  private ComboBox<String> makeTypeSelector() {
    ComboBox<String> c = new ComboBox<>();
    c.getStyleClass().add("sim-selector");
    c.setId("SimSelector");
    File colorDirectory = new File(
        getClass().getResource(DEFAULT_RESOURCE_FOLDER + "simcolors").getPath());
    String[] colorFiles = colorDirectory.list();
    if (colorFiles != null) {
      for (String f : colorFiles) {
        String s = f.split("\\.")[0];
        c.getItems().add(s);
      }
      c.setValue(c.getItems().get(0));
    }
    return c;
  }
}
