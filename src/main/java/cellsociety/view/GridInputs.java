package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * A class that contains all the inputs that control the grid views animation.
 */
public class GridInputs {

  private final double MAX_SPEED = 4.0;
  private final double MIN_SPEED = 0.5;
  private final double DEFAULT_SPEED = 1.0;
  private final int FRAMES_PER_SECOND = 5;
  private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  private final HBox container;
  private final PlayButton playButton;
  private final Button forwardButton;
  private final InputFactory inputFactory;
  private final Timeline animation;
  private final Controller controller;


  /**
   * Create a new grid inputs container.
   *
   * @param utils      the input maker that contains methods to create inputs
   * @param controller
   */
  public GridInputs(InputFactory utils, Controller controller) {
    this.controller = controller;
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> stepForward()));
    animation.setRate(DEFAULT_SPEED);
    inputFactory = utils;
    forwardButton = inputFactory.makeButton("ForwardButton",
        event -> stepForward());
    playButton = new PlayButton(utils, animation, this);
    inputFactory.attachTooltip("ForwardButtonTooltip", forwardButton);
    VBox sliderBox = makeSliderBox();
    Region padderRegion = new Region();
    padderRegion.prefWidthProperty().bind(sliderBox.widthProperty());
    container = new HBox(sliderBox, playButton.getButton(), forwardButton);
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
      animation.setRate(s.getValue());
    });
    s.setId("SpeedSlider");
    inputFactory.attachTooltip("SpeedSliderTooltip", s);
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

  /**
   * Jump forward one frame by calling the update state method in the controller.
   */
  private void stepForward() {
    System.out.println("Step forward");
    controller.updateState();
  }


  /**
   * Disables or enables the backward and forward buttons.
   *
   * @param disable if true disables, if false enables
   */
  public void disableStepButtons(Boolean disable) {
    forwardButton.setDisable(disable);
  }
}
