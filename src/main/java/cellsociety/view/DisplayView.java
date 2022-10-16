package cellsociety.view;

import cellsociety.ViewUtils;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ResourceBundle;

import static cellsociety.ViewUtils.attachTooltip;
import static cellsociety.ViewUtils.makeButton;


/**
 * A class that contains all the view and UI components.
 */
public class DisplayView {
    private static final String STYLESHEET = "default.css";
    public static final String DEFAULT_RESOURCE_FOLDER = "/cellsociety/";
    private static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
    private static final String DEFAULT_COLOR_OPTIONS = "CellColors";
    private final ResourceBundle myResources;
    private ResourceBundle colorOptions;
    private GridInputs gridInputs;
    private ViewUtils viewUtils;
    private HBox simInputsBox;
    private GridView cellGrid;
    private final double HEIGHT_BUFFER = 170;
    private final double WIDTH_BUFFER = 50;
    private InfoText infoText;
    private InfoPopUp infoPopUp;
    private final FileChooser FILE_CHOOSER;
    private final Stage STAGE;

    /**
     * Create a new view.
     * @param language the language displayed by the components
     * @param fileChooser the file chooser
     * @param stage the stage displaying the scene
     */
    public DisplayView(String language, FileChooser fileChooser, Stage stage){
        FILE_CHOOSER = fileChooser;
        STAGE = stage;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        viewUtils = new ViewUtils(myResources);
        gridInputs = new GridInputs();
        simInputsBox = makeSimInputsBox();
        colorOptions = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_COLOR_OPTIONS);
        infoText = new InfoText("Test Title", "Jerry Worthy", "Blah blah blah");
    }
    /**
     * Create the scene to display the view components.
     * @param width the width of the scene
     * @param height the height of the scene
     * @return the scene
     */
    public Scene makeScene(int width, int height){
        cellGrid = new GridView(10, 10, width - WIDTH_BUFFER, height - HEIGHT_BUFFER, colorOptions);
        STAGE.heightProperty().addListener((obs, oldval, newVal) -> cellGrid.resizeGrid(STAGE.getWidth() - WIDTH_BUFFER, STAGE.getHeight() - HEIGHT_BUFFER));
        STAGE.widthProperty().addListener((obs, oldval, newVal) -> cellGrid.resizeGrid(STAGE.getWidth() - WIDTH_BUFFER, STAGE.getHeight() - HEIGHT_BUFFER));
        BorderPane root = new BorderPane();
        root.setCenter(cellGrid.getGrid());
        root.setBottom(gridInputs.getContainer());
        root.setTop(simInputsBox);
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        infoPopUp = new InfoPopUp(infoText, myResources.getString("InfoPopUpTitle"), DEFAULT_RESOURCE_FOLDER + STYLESHEET);
        return scene;
    }
    /**
     * Creates the inputs relating to the loading and saving of simulations,
     * contained within an HBox.
     * @return the HBox containing the inputs
     */
    private HBox makeSimInputsBox(){
        ComboBox<String> c = new ComboBox<>();
        c.getItems().add(myResources.getString("GAME_OF_LIFE"));
        c.setValue(myResources.getString("GAME_OF_LIFE"));
        attachTooltip("SimulationTypeTooltip", c);
        Button saveButton = makeButton("SaveButton", event -> System.out.println("Save"));
        Button importButton = makeButton("ImportButton", event -> openFile());
        Button infoButton = makeButton("InfoButton", event -> infoPopUp.open());
        attachTooltip("SaveButtonTooltip", saveButton);
        attachTooltip("ImportButtonTooltip", importButton);
        attachTooltip("InfoButtonTooltip", infoButton);
        HBox b = new HBox(saveButton, importButton, infoButton, c);
        b.getStyleClass().add("sim-inputs-container");
        return b;
    }
    private void openFile(){
        try {
            File dataFile = FILE_CHOOSER.showOpenDialog(STAGE);
            if (dataFile != null) {
                int sum = sumCSVData(new FileReader(dataFile));
                showMessage(Alert.AlertType.INFORMATION, "" + sum);
            }
        }
        catch (IOException e) {
            // should never happen since user selected the file
            showMessage(Alert.AlertType.ERROR, "Invalid Data File Given");
        }
    }
    // display given message to user using the given type of Alert dialog box
    private void showMessage (Alert.AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }/**
     * Returns sum of values in the given CSV data source (could be anything that is readable).
     */
    public int sumCSVData (Reader dataReader) {
        // this syntax automatically close file resources if an exception occurs
        try (CSVReader csvReader = new CSVReader(dataReader)) {
            int total = 0;
            // get headers separately
            String[] headers = csvReader.readNext();
            // read rest of data line by line
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String value : nextRecord) {
                    total += Integer.parseInt(value);
                }
            }
            return total;
        }
        catch (IOException | CsvValidationException e) {
            showMessage(Alert.AlertType.ERROR, "Invalid CSV Data");
            return 0;
        }
    }

}
