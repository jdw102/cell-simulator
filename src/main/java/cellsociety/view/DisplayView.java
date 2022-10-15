package cellsociety.view;

import cellsociety.ViewUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import static cellsociety.ViewUtils.makeButton;

public class DisplayView {
    private static final String STYLESHEET = "default.css";
    public static final String DEFAULT_RESOURCE_FOLDER = "/cellsociety/";
    private static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.";
    private ResourceBundle myResources;
    private GridInputs gridInputs;
    private ViewUtils viewUtils;
    private HBox simInputsBox;
    private GridView cellGrid;
    private final double HEIGHT_BUFFER = 170;
    private final double WIDTH_BUFFER = 50;

    public DisplayView(String language){
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        viewUtils = new ViewUtils(myResources);
        gridInputs = new GridInputs();
        simInputsBox = makeSimInputsBox();
    }
    public Scene makeScene(int width, int height, Stage stage){
        cellGrid = new GridView(10, 10, width - WIDTH_BUFFER, height - HEIGHT_BUFFER );
        stage.heightProperty().addListener((obs, oldval, newVal) -> cellGrid.resizeGrid(stage.getWidth() - WIDTH_BUFFER, stage.getHeight() - HEIGHT_BUFFER));
        stage.widthProperty().addListener((obs, oldval, newVal) -> cellGrid.resizeGrid(stage.getWidth() - WIDTH_BUFFER, stage.getHeight() - HEIGHT_BUFFER));
        BorderPane root = new BorderPane();
        root.setCenter(cellGrid.getGrid());
        root.setBottom(gridInputs.getContainer());
        root.setTop(simInputsBox);
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        return scene;
    }
    public HBox makeSimInputsBox(){
        ComboBox<String> c = new ComboBox<>();
        c.getItems().add("GameOfLife");
        c.setValue("GameOfLife");
        Button saveButton = makeButton("SaveButton", event -> System.out.println("Save"));
        Button importButton = makeButton("ImportButton", event -> System.out.println("Import"));
        Button infoButton = makeButton("InfoButton", event -> System.out.println("Info"));
        HBox b = new HBox(saveButton, importButton, infoButton, c);
        b.getStyleClass().add("sim-inputs-container");
        return b;
    }
}
