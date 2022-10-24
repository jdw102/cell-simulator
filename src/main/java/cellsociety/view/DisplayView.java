package cellsociety.view;

import cellsociety.GameDisplayInfo;
import cellsociety.controller.Controller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
  public static final String DEFAULT_LANGUAGE_FOLDER = "languages/";
  private static final String DEFAULT_STYLESHEET_FOLDER = "stylesheets/";
  private static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
  private static final String DEFAULT_BLANK_SIMS_FOLDER = "/blank_sims/";
  private static final String DEFAULT_SIM_COLORS_FOLDER = "/sim_colors";
  private static final String BLANK_SIM_TAG = "Blank.sim";
  private static final String STYLESHEET_TAG = ".css";
  private final ResourceBundle myResources;
  private final double HEIGHT_BUFFER = 170;
  private final double WIDTH_BUFFER = 50;
  private final Stage STAGE;
  private final FileChooser FILE_CHOOSER;
  private final String DATA_FILE_SIM_EXTENSION = "*.sim";
  private final InputFactory inputFactory;
  private final HBox simInputsBox;
  private final InfoText infoText;
  private final InfoPopUp infoPopUp;
  private final Map<String, File> simDefaults;
  private final String DEFAULT_SIM = "GameOfLife";
  private GridInputs gridInputs;
  private GridView cellGrid;
  private Controller controller;
  private ComboBox<String> typeSelector;
  private ComboBox<String> themeSelector;
  private String currentSimType;
  private File currentSimFile;
  private ColorPopUp colorPopUp;
  private Scene scene;

  /**
   * Create a new view.
   *
   * @param language the language displayed by the components
   * @param stage    the stage displaying the scene
   */
  public DisplayView(String language, Stage stage, EventHandler<ActionEvent> newWindow) {
    currentSimType = DEFAULT_SIM;
    STAGE = stage;
    myResources = ResourceBundle.getBundle(
        DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGE_FOLDER + language);
    infoText = new InfoText();
    inputFactory = new InputFactory(myResources);
    simInputsBox = makeSimInputsBox(newWindow);
    String stylesheet =
        DEFAULT_RESOURCE_FOLDER + DEFAULT_STYLESHEET_FOLDER + themeSelector.getValue()
            + STYLESHEET_TAG;
    infoPopUp = new InfoPopUp(infoText, myResources.getString("InfoPopUpTitle"),
        stylesheet,
        inputFactory);
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
    String stylesheet =
        DEFAULT_RESOURCE_FOLDER + DEFAULT_STYLESHEET_FOLDER + themeSelector.getValue()
            + STYLESHEET_TAG;
    colorPopUp = new ColorPopUp(myResources.getString("ColorPopUpTitle"),
        stylesheet, cellGrid,
        inputFactory);
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
    scene = new Scene(root, width, height);
    scene.getStylesheets()
        .add(getClass().getResource(
                stylesheet)
            .toExternalForm());
    setupSimulation(currentSimFile);
    return scene;
  }

  /**
   * Creates the inputs relating to the loading and saving of simulations, contained within an
   * HBox.
   *
   * @return the HBox containing the inputs
   */
  private HBox makeSimInputsBox(EventHandler<ActionEvent> newWindow) {
    typeSelector = makeComboBox("SimSelector", DEFAULT_SIM_COLORS_FOLDER,
        event -> changeSimulation(typeSelector.getValue()));
    themeSelector = makeComboBox("ThemeSelector", DEFAULT_STYLESHEET_FOLDER,
        event -> changeStyleSheet(themeSelector.getValue()));
    inputFactory.attachTooltip("SimulationTypeTooltip", typeSelector);
    inputFactory.attachTooltip("ThemeSelectorTooltip", themeSelector);
    Button saveButton = inputFactory.makeButton("SaveButton", event -> System.out.println("Save"));
    Button importButton = inputFactory.makeButton("ImportButton", event -> openFile());
    Button infoButton = inputFactory.makeButton("InfoButton", event -> infoPopUp.open());
    Button resetButton = inputFactory.makeButton("ResetButton",
        event -> setupSimulation(currentSimFile));
    Button changeColorButton = inputFactory.makeButton("ChangeColorButton",
        event -> colorPopUp.open());
    Button newWindowButton = inputFactory.makeButton("NewWindowButton", newWindow);
    attachTooltip(saveButton);
    attachTooltip(importButton);
    attachTooltip(infoButton);
    attachTooltip(resetButton);
    attachTooltip(changeColorButton);
    attachTooltip(newWindowButton);
    HBox b = new HBox(themeSelector, newWindowButton, changeColorButton, saveButton, importButton,
        infoButton,
        typeSelector,
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


  private void changeSimulation(String s) {
    currentSimType = s;
    currentSimFile = simDefaults.get(currentSimType);
    setupSimulation(currentSimFile);
  }

  public GridView getGridView() {
    return cellGrid;
  }

  public void setInfoText(GameDisplayInfo text) {
    if (!currentSimType.equals(text.type())) {
      cellGrid.setSimType(text.type());
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
          getClass().getResource(DEFAULT_RESOURCE_FOLDER + DEFAULT_BLANK_SIMS_FOLDER).getPath() + s
              + BLANK_SIM_TAG);
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

  private void attachTooltip(Button button) {
    inputFactory.attachTooltip(String.format("%sTooltip", button.getId()), button);
  }

  private ComboBox<String> makeComboBox(String id, String folder,
      EventHandler<ActionEvent> handler) {
    ComboBox<String> c = new ComboBox<>();
    c.getStyleClass().add("sim-selector");
    c.setId("ThemeSelector");
    addComboBoxItems(folder, c);
    c.setOnAction(handler);
    c.setId(id);
    return c;
  }

  private void addComboBoxItems(String folder, ComboBox<String> c) {
    File directory = new File(
        getClass().getResource(DEFAULT_RESOURCE_FOLDER + folder).getPath());
    String[] files = directory.list();
    if (files != null) {
      for (String f : files) {
        String s = f.split("\\.")[0];
        c.getItems().add(s);
      }
      c.setValue(c.getItems().get(0));
    }
  }

  private void changeStyleSheet(String s) {
    String newStylesheet = DEFAULT_RESOURCE_FOLDER + DEFAULT_STYLESHEET_FOLDER + s + STYLESHEET_TAG;
    scene.getStylesheets().clear();
    scene.getStylesheets().add(newStylesheet);
    infoPopUp.changeStyleSheet(newStylesheet);
    colorPopUp.changeStyleSheet(newStylesheet);
  }
}
