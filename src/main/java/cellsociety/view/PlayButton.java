package cellsociety.view;

import javafx.scene.control.Button;

/**
 * A class that contains the play button and handles the logic of toggling between play and pause.
 */
public class PlayButton {

  private final Button button;
  private final InputMaker inputMaker;
  private boolean playState;

  public PlayButton(InputMaker utils) {
    inputMaker = utils;
    playState = true;
    button = inputMaker.makeButton("PlayButton", event -> togglePlay());
  }

  private void togglePlay() {
    setPlay(!playState);
  }

  private void changeGraphic(String img, String tooltip) {
    inputMaker.changeButtonGraphic(img, button);
    inputMaker.attachTooltip(tooltip, button);
  }

  public Button getButton() {
    return button;
  }

  /**
   * Sets the function of the button to either play or pause.
   *
   * @param b a boolean that if true triggers play and if false triggers pause
   */
  public void setPlay(boolean b) {
    playState = b;
    if (playState) {
      changeGraphic("PlayButton", "PlayButtonTooltip");
    } else {
      changeGraphic("PauseButton", "PauseButtonTooltip");
    }
  }
}
