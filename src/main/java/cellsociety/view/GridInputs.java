package cellsociety.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A class that contains all the inputs that control the grid views animation.
 */
public class GridInputs {

  private final double MAX_SPEED = 4.0;
  private final double MIN_SPEED = 0.5;
  private final double DEFAULT_SPEED = 1.0;
  private final HBox container;
  private final PlayButton playButton;
  private final InputMaker inputMaker;

  /**
   * Create a new grid inputs container.
   */
  public GridInputs(InputMaker utils) {
    inputMaker = utils;
    Button backwardButton = inputMaker.makeButton("BackwardButton",
        event -> System.out.println("Backward"));
    playButton = new PlayButton(utils);
    Button forwardButton = inputMaker.makeButton("ForwardButton",
        event -> System.out.println("Forward"));
    inputMaker.attachTooltip("BackwardButtonTooltip", backwardButton);
    inputMaker.attachTooltip("ForwardButtonTooltip", forwardButton);
    VBox sliderBox = makeSliderBox();
    Region padderRegion = new Region();
    padderRegion.prefWidthProperty().bind(sliderBox.widthProperty());
    container = new HBox(padderRegion, backwardButton, playButton.getButton(), forwardButton,
        sliderBox);
    container.getStyleClass().add("grid-inputs-container");
  }

  /**
   * @return HBox containing all the components
   */
  public HBox getContainer() {
    return container;
  }

  /**
   * @param speedLabel the TextField that acts as the label
   * @return the slider that adjusts teh animation speed
   */
  private Slider makeSpeedSlider(TextField speedLabel) {
    Slider s = new Slider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
    s.valueProperty().addListener((obs, oldval, newVal) -> {
      s.setValue(Math.round(newVal.doubleValue() * 2) / 2.0);
      speedLabel.setText("x" + s.getValue());
    });
    s.setId("SpeedSlider");
    inputMaker.attachTooltip("SpeedSliderTooltip", s);
    return s;
  }

  /**
   * @return VBox containing the slider and label
   */
  private VBox makeSliderBox() {
    TextField speedLabel = new TextField("x" + DEFAULT_SPEED);
    speedLabel.setDisable(true);
    speedLabel.setId("SpeedLabel");
    speedLabel.getStyleClass().add("speed-text-field");
    Slider speedSlider = makeSpeedSlider(speedLabel);
    VBox box = new VBox(speedSlider, speedLabel);
    box.setAlignment(Pos.CENTER);
    return box;
  }
}
