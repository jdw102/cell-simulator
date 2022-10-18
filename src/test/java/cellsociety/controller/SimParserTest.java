package cellsociety.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class SimParserTest {

  public static final String DEFAULT_RESOURCE_FOLDER = "/cellsociety/";

  @Test
  void SimFileParserReadsAllRequiredFieldsTest() throws IOException, WrongFileTypeException {
    // Arrange
    File blinkerSimFile = new File(
        getClass().getResource(DEFAULT_RESOURCE_FOLDER + "blinkers.sim").getPath());

    // Act
    SimParser simParser = new SimParser(blinkerSimFile);
    GameDisplayInfo gameDisplayInfo = simParser.getGameDisplayInfo();

    // Assert
    assertEquals("GameOfLife", gameDisplayInfo.type());
    assertEquals("Blinkers", gameDisplayInfo.title());
    assertEquals("John Conway", gameDisplayInfo.author());
    assertEquals(
        "Examples of a blinker, a line of cells 3 wide that switches back and forth from vertical to horizontal",
        gameDisplayInfo.description());
  }

  @Test
  void SimFileParserGetInitStateCsvTest() throws IOException, WrongFileTypeException {
    // Arrange
    File blinkerSimFile = new File(
        getClass().getResource(DEFAULT_RESOURCE_FOLDER + "blinkers.sim").getPath());
    String expectedCsvFilePath = "game_of_life\\blinkers.csv";

    // Act
    SimParser simParser = new SimParser(blinkerSimFile);
    File actualCsvFile = simParser.getInitStateCsv();

    // Assert
    assertEquals(expectedCsvFilePath, actualCsvFile.getPath());
  }
}
