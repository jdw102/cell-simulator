package cellsociety.view;

import javafx.animation.Timeline;
import javafx.scene.control.Button;

/**
 * A class that contains the play button and handles the logic of toggling between play and pause.
 */
public class PlayButton {

  private final Button button;
  private final InputMaker inputMaker;
  private boolean playState;
  private Timeline animation;

  /**
   * Creates new play button.
   *
   * @param utils     the input maker
   * @param animation the animation that will be played and paused
   */
  public PlayButton(InputMaker utils, Timeline animation, GridInputs inputs) {
    this.animation = animation;
    inputMaker = utils;
    playState = true;
    button = inputMaker.makeButton("PlayButton", event -> togglePlay(inputs));
  }

  /**
   * Sets the function of the button to either play or pause and either disable or enable the step
   * buttons.
   */
  private void togglePlay(GridInputs inputs) {
    if (playState) {
      play();
    } else {
      pause();
    }
    inputs.disableStepButtons(playState);
    playState = !playState;
  }

  /**
   * Changes the graphic of the button.
   *
   * @param img     the new image id string
   * @param tooltip the new tooltip label id string
   */
  private void changeGraphic(String img, String tooltip) {
    inputMaker.changeButtonGraphic(img, button);
    inputMaker.attachTooltip(tooltip, button);
  }

  public Button getButton() {
    return button;
  }

  /**
   * Plays the animation and changes the button to show a pause graphic and tooltip.
   */
  private void play() {
    animation.play();
    changeGraphic("PauseButton", "PauseButtonTooltip");
  }

  /**
   * Pauses the animation and changes the button to show a play graphic and tooltip.
   */
  private void pause() {
    animation.pause();
    changeGraphic("PlayButton", "PlayButtonTooltip");
  }
}
