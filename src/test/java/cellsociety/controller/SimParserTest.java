package cellsociety.controller;

import static cellsociety.controller.FileParserTest.DEFAULT_RESOURCE_FOLDER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.GameDisplayInfo;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class SimParserTest {

  @Test
  void constructorThrowsWrongFileTypeExceptionTest() {
    File blinkerCsvFile = new File(
        getClass().getResource(DEFAULT_RESOURCE_FOLDER + "game_of_life/blinkers.csv").getPath());
    String expectedMessage = "Must provide a file of type sim, but was provided a file of type csv";

    Exception thrownException = assertThrows(WrongFileTypeException.class,
        () -> new SimParser(blinkerCsvFile));
    assertEquals(expectedMessage, thrownException.getMessage());
  }

  @Test
  void constructorThrowsIOExceptionTest() {
    File randomFile = new File("/thisFileDoesNotExist.sim");
    String expectedMessage = "\\thisFileDoesNotExist.sim (The system cannot find the file specified)";

    Exception thrownException = assertThrows(IOException.class, () -> new SimParser(randomFile));
    assertEquals(expectedMessage, thrownException.getMessage());
  }

  @Test
  void constructorReadsAllRequiredFieldsTest() throws IOException, WrongFileTypeException {
    // Arrange
    File blinkerSimFile = new File(
        getClass().getResource(DEFAULT_RESOURCE_FOLDER + "game_of_life/blinkers.sim").getPath());

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
  void getInitStateCsvTest() throws IOException, WrongFileTypeException {
    // Arrange
    File blinkerSimFile = new File(
        getClass().getResource(DEFAULT_RESOURCE_FOLDER + "game_of_life/blinkers.sim").getPath());
    String expectedCsvFilePath = "game_of_life\\blinkers.csv";

    // Act
    SimParser simParser = new SimParser(blinkerSimFile);
    File actualCsvFile = simParser.getInitStateCsv();

    // Assert
    assertTrue(actualCsvFile.getPath().endsWith(expectedCsvFilePath));
  }

  @Test
  void getInitStateCsvNotCsvTest() throws IOException {
    // Arrange
    File simFile = new File(
        getClass().getResource("/badinputfiles/spreadingfirebadcsvfile.sim").getPath());
    String expectedExceptionMessage = "Must provide a file of type csv, but was provided a file of type html";

    // Act
    SimParser simParser = new SimParser(simFile);
    WrongFileTypeException wrongFileTypeException = assertThrows(WrongFileTypeException.class, () -> simParser.getInitStateCsv());

    assertEquals(expectedExceptionMessage, wrongFileTypeException.getMessage());
  }
}
