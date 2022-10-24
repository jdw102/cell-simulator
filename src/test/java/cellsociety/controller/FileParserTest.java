package cellsociety.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import org.junit.jupiter.api.Test;

class FileParserTest {

  public static final String DEFAULT_RESOURCE_FOLDER = "/cellsociety/data/";

  @Test
  void isFileTypeCorrectThrowsWrongFileTypeException() {
    // Arrange
    FileParser fileParser = new FileParser();
    File testFile = new File(DEFAULT_RESOURCE_FOLDER + "game_of_life/blinkers.csv");
    String expectedMessage = "Must provide a file of type sim, but was provided a file of type csv";

    // Act
    WrongFileTypeException wrongFileTypeException = assertThrows(WrongFileTypeException.class,
        () -> fileParser.isFileTypeCorrect(testFile, "sim"));

    // Assert
    assertEquals(expectedMessage, wrongFileTypeException.getMessage());
  }
}