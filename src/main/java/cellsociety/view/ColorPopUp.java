package cellsociety.view;

import java.util.ResourceBundle;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ColorPopUp {

  private final Dialog<ResourceBundle> dialog;
  private final GridView gridView;
  private final InputFactory inputFactory;

  public ColorPopUp(String title, String styleSheet, GridView grid, InputFactory factory) {
    inputFactory = factory;
    gridView = grid;
    dialog = new Dialog<>();
    dialog.getDialogPane().getStylesheets()
        .add(getClass().getResource(styleSheet).toExternalForm());
    dialog.setTitle(title);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
    inputFactory.changeButtonTypeLabel("CloseColorsButton",
        dialog.getDialogPane().lookupButton(ButtonType.CLOSE));
    dialog.getDialogPane().setId("ColorPane");
  }

  private VBox makeColorPickersBox(StateColors colors) {
    Label topLabel = inputFactory.makeLabel("ColorPopUpHeader");
    topLabel.getStyleClass().add("info-header");
    HBox topBox = new HBox(topLabel);
    topBox.getStyleClass().add("info-header-container");
    VBox box = new VBox(topBox);
    for (String state : colors.getStates()) {
      HBox colorBox = makeColorPickerBox(state, colors);
      box.getChildren().add(colorBox);
    }
    box.getStyleClass().add("pop-up-content");
    return box;
  }


  public void open() {
    dialog.showAndWait();
  }

  public void setStateColors(StateColors colors) {
    VBox box = makeColorPickersBox(colors);
    dialog.getDialogPane().setContent(box);
  }

  private HBox makeColorPickerBox(String state, StateColors colors) {
    Label colorLabel = new Label(state);
    colorLabel.getStyleClass().add("info-text-label");
    ColorPicker cp = new ColorPicker();
    cp.setValue(colors.getColor(state));
    cp.setOnAction(event -> {
      colors.changeStateColor(state, cp.getValue());
      gridView.updateCellColors();
    });
    cp.setId(state + "ColorPicker");
    cp.getStyleClass().add("state-color-picker");
    HBox colorBox = new HBox(colorLabel, cp);
    colorBox.getStyleClass().add("state-color-box");
    return colorBox;
  }

}
