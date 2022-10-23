package cellsociety.view;

import cellsociety.GameDisplayInfo;
import cellsociety.controller.Controller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
  private final String DATA_FILE_SIM_EXTENSION = "*.sim";
  private final InputFactory inputFactory;
  private final HBox simInputsBox;
  private final String SIMULATION_KEYS_FILE = "SimulationKeys";
  private final String DEFAULT_FILE_PATH = "blank.sim";
  private final InfoText infoText;
  private final InfoPopUp infoPopUp;
  private final Map<String, File> simDefaults;
  private final ResourceBundle simulationKeys;
  private final String DEFAULT_SIM = "GameOfLife";
  private GridInputs gridInputs;
  private GridView cellGrid;
  private Controller controller;
  private ComboBox<String> typeSelector;
  private String currentSimType;
  private File currentSimFile;
  private ColorPopUp colorPopUp;

  /**
   * Create a new view.
   *
   * @param language the language displayed by the components
   * @param stage    the stage displaying the scene
   */
  public DisplayView(String language, Stage stage) {
    currentSimType = DEFAULT_SIM;
    STAGE = stage;
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    simulationKeys = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SIMULATION_KEYS_FILE);
    infoText = new InfoText("", "", "");
    inputFactory = new InputFactory(myResources);
    infoPopUp = new InfoPopUp(infoText, myResources.getString("InfoPopUpTitle"),
        DEFAULT_RESOURCE_FOLDER + STYLESHEET, inputFactory);
    simInputsBox = makeSimInputsBox();
    simDefaults = makeDefaultFileMap();
    currentSimFile = simDefaults.get(currentSimType);
    FILE_CHOOSER = inputFactory.makeChooser(DATA_FILE_SIM_EXTENSION);
    System.out.println(FILE_CHOOSER.getExtensionFilters());
  }

  public void setController(Controller controller) {
    this.controller = controller;
    gridInputs = new GridInputs(inputFactory, controller);
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
    cellGrid.setSimType(currentSimType);
    colorPopUp = new ColorPopUp(myResources.getString("ColorPopUpTitle"),
        DEFAULT_RESOURCE_FOLDER + STYLESHEET, cellGrid, inputFactory);
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
    setupSimulation(currentSimFile);
    return scene;
  }

  /**
   * Creates the inputs relating to the loading and saving of simulations, contained within an
   * HBox.
   *
   * @return the HBox containing the inputs
   */
  private HBox makeSimInputsBox() {
    typeSelector = makeTypeSelector();
    inputFactory.attachTooltip("SimulationTypeTooltip", typeSelector);
    Button saveButton = inputFactory.makeButton("SaveButton", event -> System.out.println("Save"));
    Button importButton = inputFactory.makeButton("ImportButton", event -> openFile());
    Button infoButton = inputFactory.makeButton("InfoButton", event -> infoPopUp.open());
    Button resetButton = inputFactory.makeButton("ResetButton",
        event -> setupSimulation(currentSimFile));
    Button changeColorButton = inputFactory.makeButton("ChangeColorButton",
        event -> colorPopUp.open());
    inputFactory.attachTooltip("SaveButtonTooltip", saveButton);
    inputFactory.attachTooltip("ImportButtonTooltip", importButton);
    inputFactory.attachTooltip("InfoButtonTooltip", infoButton);
    inputFactory.attachTooltip("ResetButtonTooltip", resetButton);
    inputFactory.attachTooltip("ChangeColorButtonTooltip", changeColorButton);
    HBox b = new HBox(changeColorButton, saveButton, importButton, infoButton, typeSelector,
        resetButton);
    b.getStyleClass().add("sim-inputs-container");
    return b;
  }

  /**
   * Opens a file and passes the file to the controller for simulation setup.
   */
  private void openFile() {
    File dataFile = FILE_CHOOSER.showOpenDialog(STAGE);
    if (dataFile != null) {
      setupSimulation(dataFile);
    }
  }

  /**
   * Displays alert message to user.
   *
   * @param exception the exception
   */
  // display given message to user using the given type of Alert dialog box
  public void showMessage(Exception exception) {
    new Alert(AlertType.ERROR, exception.getMessage()).showAndWait();
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
    c.setOnAction(event -> {
      currentSimType = c.getValue();
      currentSimFile = simDefaults.get(currentSimType);
      setupSimulation(currentSimFile);
    });
    return c;
  }

  public GridView getGridView() {
    return cellGrid;
  }

  public void setInfoText(GameDisplayInfo text) {
    if (!currentSimType.equals(text.type())) {
      cellGrid.setSimType(currentSimType);
      currentSimType = text.type();
      typeSelector.setValue(currentSimType);
    }
    infoText.setTitle(text.title());
    infoText.setAuthor(text.author());
    infoText.setDescription(text.description());
    infoPopUp.changeInfoText(infoText);
  }

  private Map<String, File> makeDefaultFileMap() {
    Map<String, File> map = new HashMap<>();
    for (String s : typeSelector.getItems()) {
      File f = new File(
          "C:\\Users\\User\\IdeaProjects\\cellsociety_team06\\data\\" + simulationKeys.getString(
              s) + "\\" + DEFAULT_FILE_PATH);
      map.put(s, f);
    }
    return map;
  }

  public void setupSimulation(File f) {
    currentSimFile = f;
    cellGrid.clearGrid();
    controller.setupSimulation(currentSimFile);
    colorPopUp.setStateColors(cellGrid.getStateColors());
  }
}
