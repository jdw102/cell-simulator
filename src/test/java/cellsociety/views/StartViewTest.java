package cellsociety.views;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.view.StartView;
import java.awt.Dimension;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.DukeApplicationTest;

public class StartViewTest extends DukeApplicationTest {

  public static final Dimension MIN_SIZE = new Dimension(300, 300);
  public static final Dimension START_SIZE = new Dimension(400, 500);
  private StartView startView;

  @Override
  public void start(Stage primaryStage) {
    startView = new StartView();
    primaryStage.setScene(startView.setUpScene(START_SIZE.width, START_SIZE.height,
        event -> System.out.println("Start")));
    primaryStage.setMinHeight(MIN_SIZE.height);
    primaryStage.setMinWidth(MIN_SIZE.width);
    primaryStage.show();
  }

  @ParameterizedTest
  @CsvSource({
      "English",
      "Spanish",
      "Italian"
  })
  void testLanguageSelector(String language) {
    ComboBox<String> languages = lookup("#LanguageSelector").query();
    select(languages, language);
    String actual = startView.getStartLanguage();
    sleep(300);
    assertEquals(language, actual);
  }
}
