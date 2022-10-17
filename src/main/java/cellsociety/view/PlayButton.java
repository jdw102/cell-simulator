package cellsociety.view;

import cellsociety.ViewUtils;
import javafx.scene.control.Button;

/**
 * A class that contains the play button and handles the logic
 * of toggling between play and pause.
 */
public class PlayButton {
    private boolean playState;
    private final Button button;
    private final ViewUtils viewUtils;

    public PlayButton(ViewUtils utils) {
        viewUtils = utils;
        playState = true;
        button = viewUtils.makeButton("PlayButton", event -> togglePlay());
    }

    private void togglePlay() {
        setPlay(!playState);
    }

    private void changeGraphic(String img, String tooltip) {
        viewUtils.changeButtonGraphic(img, button);
        viewUtils.attachTooltip(tooltip, button);
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
