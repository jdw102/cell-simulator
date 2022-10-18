package cellsociety.controller;

import java.io.File;

public class FileParser {

  protected void isFileTypeCorrect(File file, String expectedFileType)
      throws WrongFileTypeException {
    String[] fileName = file.getName().split("\\.");
    String providedFileType = fileName[fileName.length - 1];
    if (!providedFileType.equals(
        expectedFileType)) { // was not provided a .sim file, so throw exception
      throw new WrongFileTypeException(expectedFileType, providedFileType);
    }
  }
}
