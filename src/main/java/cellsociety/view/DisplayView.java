package cellsociety.view;

import static cellsociety.Main.BLANK_SIM_TAG;
import static cellsociety.Main.DATA_FILE_SIM_EXTENSION;
import static cellsociety.Main.DEFAULT_BLANK_SIMS_FOLDER;
import static cellsociety.Main.DEFAULT_COLORS_PACKAGE;
import static cellsociety.Main.DEFAULT_LANGUAGE_FOLDER;
import static cellsociety.Main.DEFAULT_RESOURCE_FOLDER;
import static cellsociety.Main.DEFAULT_RESOURCE_PACKAGE;
import static cellsociety.Main.DEFAULT_SIM_COLORS_FOLDER;
import static cellsociety.Main.DEFAULT_SIZE;
import static cellsociety.Main.DEFAULT_STYLESHEET_FOLDER;
import static cellsociety.Main.MIN_SIZE;
import static cellsociety.Main.PROPERTIES_PACKAGE;
import static cellsociety.Main.SETTINGS_PACKAGE;
import static cellsociety.Main.STATE_HANDLER_TAG;
import static cellsociety.Main.STYLESHEET_TAG;
import static cellsociety.Main.TITLE;

import cellsociety.GameDisplayInfo;
import cellsociety.controller.Controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * A class that contains all the view and UI components.
 */
public class DisplayView {

  private final ResourceBundle myResources;
  private final double HEIGHT_BUFFER = 170;
  private final double WIDTH_BUFFER = 50;
  private final Stage STAGE;
  private final FileChooser FILE_CHOOSER;
  private final InputFactory inputFactory;
  private final HBox simInputsBox;
  private final InfoText infoText;
  private final InfoPopUp infoPopUp;
  private final Map<String, File> simDefaults;
  private final ResourceBundle settings;
  private final String currLanguage;
  private GridInputs gridInputs;
  private GridView cellGrid;
  private Controller controller;
  private ComboBox<String> typeSelector;
  private ComboBox<String> themeSelector;
  private String currentSimType;
  private File currentSimFile;
  private ColorPopUp colorPopUp;
  private Scene scene;
  private boolean setDefault;
  private ResourceBundle simStates;
  private Button newWindowButton;
  private BarView barView;
  private BorderPane root;
  private Map<String, DataView> dataViewMap;
  private StateColors currentStateColors;

  /**
   * Create a new view.
   *
   * @param language the language displayed by the components
   * @param stage    the stage displaying the scene
   */
  public DisplayView(String language, Stage stage) {
    currLanguage = language;
    settings = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SETTINGS_PACKAGE);
    currentSimType = settings.getString("DefaultSim");
    simStates = ResourceBundle.getBundle(
        DEFAULT_RESOURCE_PACKAGE + PROPERTIES_PACKAGE + currentSimType + STATE_HANDLER_TAG);
    STAGE = stage;
    myResources = ResourceBundle.getBundle(
        DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGE_FOLDER + language);
    infoText = new InfoText();
    inputFactory = new InputFactory(myResources);
    simInputsBox = makeSimInputsBox();
    String stylesheet =
        DEFAULT_RESOURCE_FOLDER + DEFAULT_STYLESHEET_FOLDER + themeSelector.getValue()
            + STYLESHEET_TAG;
    infoPopUp = new InfoPopUp(infoText, myResources.getString("InfoPopUpTitle"),
        stylesheet,
        inputFactory);
    simDefaults = makeDefaultFileMap();
    currentSimFile = simDefaults.get(currentSimType);
    setDefault = true;
    FILE_CHOOSER = inputFactory.makeChooser(DATA_FILE_SIM_EXTENSION);
  }

  public void setController(Controller controller) {
    this.controller = controller;
    gridInputs = new GridInputs(inputFactory, controller, settings);
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
    barView = new BarView(width - WIDTH_BUFFER, height - HEIGHT_BUFFER);
    makeDataViewMap(new ArrayList<>(Arrays.asList(cellGrid, barView)));
    currentStateColors = getNewColors(currentSimType);
    cellGrid.setStateColors(currentStateColors);
    barView.setStateColors(currentStateColors);
    barView.setController(controller);
    cellGrid.setController(controller);
    gridInputs.setHistogram(barView);
    String stylesheet =
        DEFAULT_RESOURCE_FOLDER + DEFAULT_STYLESHEET_FOLDER + themeSelector.getValue()
            + STYLESHEET_TAG;
    colorPopUp = new ColorPopUp(myResources.getString("ColorPopUpTitle"),
        stylesheet, cellGrid,
        inputFactory, barView);
    colorPopUp.setStateColors(currentStateColors);
    STAGE.heightProperty().addListener(
        (obs, oldval, newVal) -> resizeDataViews(STAGE.getWidth() - WIDTH_BUFFER,
            STAGE.getHeight() - HEIGHT_BUFFER));
    STAGE.widthProperty().addListener(
        (obs, oldval, newVal) -> resizeDataViews(STAGE.getWidth() - WIDTH_BUFFER,
            STAGE.getHeight() - HEIGHT_BUFFER));
    root = new BorderPane();
    root.setBottom(gridInputs.getContainer());
    root.setTop(simInputsBox);
    String defaultViewName = settings.getString("DefaultDataView");
    root.setCenter(dataViewMap.get(defaultViewName).getNode());
    ComboBox<String> viewSelector = inputFactory.makeDataViewComboBox(dataViewMap, root);
    simInputsBox.getChildren().add(0, viewSelector);
    attachTooltip(viewSelector);
    scene = new Scene(root, width, height);
    viewSelector.setValue(defaultViewName);
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
  private HBox makeSimInputsBox() {
    typeSelector = makeComboBoxFromFolder("SimSelector", DEFAULT_SIM_COLORS_FOLDER,
        event -> changeSimulation(typeSelector.getValue()));
    typeSelector.setValue(currentSimType);
    themeSelector = makeComboBoxFromFolder("ThemeSelector", DEFAULT_STYLESHEET_FOLDER,
        event -> changeStyleSheet(themeSelector.getValue()));
    themeSelector.setValue(settings.getString("DefaultTheme"));
    attachTooltip(typeSelector);
    attachTooltip(themeSelector);
    Button saveButton = inputFactory.makeButton("SaveButton", event -> saveFile());
    Button importButton = inputFactory.makeButton("ImportButton", event -> openFile());
    Button infoButton = inputFactory.makeButton("InfoButton", event -> infoPopUp.open());
    Button resetButton = inputFactory.makeButton("ResetButton",
        event -> setupSimulation(currentSimFile));
    Button changeColorButton = inputFactory.makeButton("ChangeColorButton",
        event -> colorPopUp.open());
    newWindowButton = inputFactory.makeButton("NewWindowButton", event -> openNewWindow());
    attachTooltip(saveButton);
    attachTooltip(importButton);
    attachTooltip(infoButton);
    attachTooltip(resetButton);
    attachTooltip(changeColorButton);
    attachTooltip(newWindowButton);
    HBox b = new HBox(themeSelector, newWindowButton, changeColorButton, saveButton,
        importButton,
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
    Alert alert = new Alert(AlertType.ERROR, exception.getMessage());
    alert.getDialogPane().setId("AlertPane");
    alert.showAndWait();
  }

  /**
   * Changes the simulation type of the grid, also loads default sim if user selects from combo
   * box.
   *
   * @param s the type of simulation
   */
  private void changeSimulation(String s) {
    cellGrid.clear();
    currentSimType = s;
    simStates = ResourceBundle.getBundle(
        DEFAULT_RESOURCE_PACKAGE + PROPERTIES_PACKAGE + currentSimType + STATE_HANDLER_TAG);
    currentStateColors = getNewColors(currentSimType);
    cellGrid.setStateColors(currentStateColors);
    barView.setStateColors(currentStateColors);
    colorPopUp.setStateColors(cellGrid.getStateColors());
    if (setDefault) {
      setupSimulation(simDefaults.get(currentSimType));
    }
  }

  /**
   * Gets the grid view. Used in the controller and sim set up to add the cells to the grid.
   *
   * @return the grid view
   */
  public GridView getGridView() {
    return cellGrid;
  }

  /**
   * Sets the info text displayed by the pop-up and the type displayed by the combo box.
   *
   * @param info the record that contains the information read from the sim file, passed from the
   *             controller
   */
  public void setInfoText(GameDisplayInfo info) {
    if (!currentSimType.equals(info.type())) {
      currentSimType = info.type();
      setDefault = false;
      typeSelector.setValue(currentSimType);
      currentStateColors = getNewColors(currentSimType);
      cellGrid.setStateColors(currentStateColors);
      barView.setStateColors(currentStateColors);
      colorPopUp.setStateColors(currentStateColors);
      simStates = ResourceBundle.getBundle(
          DEFAULT_RESOURCE_PACKAGE + PROPERTIES_PACKAGE + currentSimType + STATE_HANDLER_TAG);
      setDefault = true;
    }
    readStateColors(info.colors(), info.title());
    infoText.setText(info.title(), info.author(),
        info.description());
    infoPopUp.changeInfoText(infoText);
  }

  /**
   * Sets the color resource bundle that will be passed to each cell view.
   *
   * @return the map the maps the sim type to the default sim file
   */
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

  /**
   * Clears the grid, updates the current file, and sets up the simulation using the controller.
   *
   * @param f the sim file to set up
   */
  public void setupSimulation(File f) {
    currentSimFile = f;
    cellGrid.clear();
    barView.clear();
    controller.setupSimulation(currentSimFile);
    barView.makeHistogramBars();
  }

  /**
   * Attaches a tooltip label to a node.
   *
   * @param node the node to attach a tooltip
   */
  private void attachTooltip(Node node) {
    inputFactory.attachTooltip(String.format("%sTooltip", node.getId()), node);
  }

  /**
   * Creates a combo box that gets its items from contents of folder.
   *
   * @param id      the id of the combobox
   * @param folder  the path to the folder to be read
   * @param handler the event triggered on item selection
   */
  private ComboBox<String> makeComboBoxFromFolder(String id, String folder,
      EventHandler<ActionEvent> handler) {
    ComboBox<String> comboBox = new ComboBox<>();
    comboBox.getStyleClass().add("sim-selector");
    comboBox.setId("ThemeSelector");
    addComboBoxItemsFromFolder(folder, comboBox);
    comboBox.setOnAction(handler);
    comboBox.setId(id);
    return comboBox;
  }

  /**
   * Adds file names in folder to combo box.
   *
   * @param folder the path to the folder to be read
   * @param c      the combobox to which items are added
   */
  private void addComboBoxItemsFromFolder(String folder, ComboBox<String> c) {
    File directory = new File(
        getClass().getResource(DEFAULT_RESOURCE_FOLDER + folder).getPath());
    String[] files = directory.list();
    if (files != null) {
      for (String f : files) {
        String s = f.split("\\.")[0];
        c.getItems().add(s);
      }
    }
  }

  /**
   * Updates the stylesheet of the application.
   *
   * @param s the string referencing the path to the stylesheet
   */
  private void changeStyleSheet(String s) {
    String newStylesheet = DEFAULT_RESOURCE_FOLDER + DEFAULT_STYLESHEET_FOLDER + s + STYLESHEET_TAG;
    scene.getStylesheets().clear();
    scene.getStylesheets().add(newStylesheet);
    infoPopUp.changeStyleSheet(newStylesheet);
    colorPopUp.changeStyleSheet(newStylesheet);
  }

  private void saveFile() {
    File f = FILE_CHOOSER.showSaveDialog(STAGE);
    if (f != null) {
      SimFileWriter simWriter = new SimFileWriter(currentSimType, simStates, currentStateColors);
      try {
        simWriter.createSim(f, infoText, cellGrid);
      } catch (FileNotFoundException e) {
        showMessage(e);
      }
    }
  }

  public File getCurrentStateFile() {
    File f = new File("data/temp.sim");
    SimFileWriter simFileWriter = new SimFileWriter(currentSimType, simStates, currentStateColors);
    try {
      simFileWriter.createSim(f, infoText, cellGrid);
    } catch (FileNotFoundException e) {
      showMessage(e);
    }
    return f;
  }

  public void openNewWindow() {
    gridInputs.getPlayButton().pause();
    Stage newStage = new Stage();
    newStage.setTitle(TITLE);
    DisplayView view = new DisplayView(currLanguage, newStage);
    Controller controller = new Controller(view);
    view.setController(controller);
    newStage.setScene(view.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
    newStage.setMinHeight(MIN_SIZE.height);
    newStage.setMinWidth(MIN_SIZE.width);
    newStage.show();
    File f = getCurrentStateFile();
    view.setupSimulation(f);
  }

  public void addCellView(CellView cell, int row, int col) {
    cellGrid.addCell(cell, row, col);
    barView.addCell(cell, row, col);
  }

  private StateColors getNewColors(String type) {
    return new StateColors(ResourceBundle.getBundle(DEFAULT_COLORS_PACKAGE + type));
  }

  private void resizeDataViews(double width, double height) {
    for (String s : dataViewMap.keySet()) {
      dataViewMap.get(s).resize(width, height);
    }
  }

  private void makeDataViewMap(List<DataView> views) {
    dataViewMap = new HashMap<>();
    for (DataView d : views) {
      String key = String.format("%sName", d.getClass().getSimpleName());
      dataViewMap.put(myResources.getString(key), d);
    }
  }

  private void readStateColors(String[] colors, String title) {
    for (String s : colors) {
      Color c;
      try {
        c = Color.valueOf(s);
        if (currentStateColors.hasNext()) {
          String state = currentStateColors.next();
          currentStateColors.changeStateColor(state, c);
        } else {
          break;
        }
      } catch (IllegalArgumentException | NullPointerException e) {
        showMessage(new StateColorsFormatException(title));
      }
    }
    currentStateColors.resetIterator();
    colorPopUp.setStateColors(currentStateColors);
  }
}
