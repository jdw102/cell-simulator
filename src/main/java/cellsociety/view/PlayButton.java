package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static cellsociety.ViewUtils.attachTooltip;
import static cellsociety.ViewUtils.makeButton;
import static cellsociety.ViewUtils.changeButtonGraphic;
/**
 * A class that contains the play button and handles the logic
 * of toggling between play and pause.
 */
public class PlayButton {
    private boolean playState;
    private Button button;
    public PlayButton(){
        playState = true;
        button = makeButton("PlayButton", event -> togglePlay());
    }
    private void togglePlay(){
        setPlay(!playState);
    }
    private void changeGraphic(String img, String tooltip){
        changeButtonGraphic(img, button);
        attachTooltip(tooltip, button);
    }
    public Button getButton(){
        return button;
    }
    /**
     * Sets the function of the button to either play or pause.
     * @param b a boolean that if true triggers play and if false triggers pause
     */
    public void setPlay(boolean b){
        playState = b;
        if (playState){
            changeGraphic("PlayButton", "PlayButtonTooltip");
        }
        else{
            changeGraphic("PauseButton", "PauseButtonTooltip");
        }
    }
}
