package cellsociety.controller;

import java.io.File;

/**
 * Parent class for specific parsers made to read specific types of files
 *
 * @author Daniel Feinblatt
 */
public class FileParser {

  /**
   * Checks if the file is the correct type
   *
   * @param file             The file that is being examined
   * @param expectedFileType The type of file (i.e. the string that appends files after the '.')
   * @throws WrongFileTypeException Throws this when the file type is not the expected file type
   */
  protected void isFileTypeCorrect(File file, String expectedFileType)
      throws WrongFileTypeException {
    String[] fileName = file.getName().split("\\.");
    String providedFileType = fileName[fileName.length - 1];
    if (!providedFileType.equals(expectedFileType)) {
      throw new WrongFileTypeException(expectedFileType, providedFileType);
    }
  }
}
