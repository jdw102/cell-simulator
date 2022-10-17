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

  public PlayButton(InputMaker utils, Timeline animation) {
    this.animation = animation;
    inputMaker = utils;
    playState = true;
    button = inputMaker.makeButton("PlayButton", event -> togglePlay());
  }

  /**
   * Sets the function of the button to either play or pause.
   */
  private void togglePlay() {
    if (playState) {
      play();
    } else {
      pause();
    }
    playState = !playState;
  }

  private void changeGraphic(String img, String tooltip) {
    inputMaker.changeButtonGraphic(img, button);
    inputMaker.attachTooltip(tooltip, button);
  }

  public Button getButton() {
    return button;
  }

  private void play() {
    animation.play();
    changeGraphic("PauseButton", "PauseButtonTooltip");
  }

  private void pause() {
    animation.pause();
    changeGraphic("PlayButton", "PlayButtonTooltip");
  }
}
