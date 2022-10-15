package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static cellsociety.ViewUtils.attachTooltip;
import static cellsociety.ViewUtils.makeButton;
import static cellsociety.ViewUtils.changeButtonGraphic;

public class PlayButton {
    private boolean playState;
    private Button button;
    private Image playImg;
    private Image pauseImg;
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
