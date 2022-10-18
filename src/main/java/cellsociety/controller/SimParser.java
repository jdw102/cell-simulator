package cellsociety.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SimParser {

  public static final String SIM_FILE_TYPE = "sim";
  private Properties properties;

  public SimParser(File simFile) throws IOException { // catch this in controller and tell view
    properties = null;
    // Check if it is a .sim file
    isFileTypeSim(simFile);
    readSimFile(simFile);
  }

  private void isFileTypeSim(File simFile) {
    String[] fileName = simFile.getName().split(".");
    String providedFileType = fileName[fileName.length-1];
    if (! providedFileType.equals(SIM_FILE_TYPE)) { // was not provided a .sim file, so throw exception
      throw new WrongFileTypeException(SIM_FILE_TYPE, providedFileType);
    }
  }

  /**
   * Reads a .sim file and loads its data just like a properties file because it follows the java
   * properties file convention
   *
   * @param simFile The file to load simulation information from
   * @throws IOException Thrown if file not found or properties cannot load
   */
  private void readSimFile(File simFile)
      throws IOException {
    try (FileInputStream simFileInputStream = new FileInputStream(simFile)) {
      properties = new Properties();
      properties.load(simFileInputStream);
    }
  }

  /**
   * Creates GameInfo Record for use in the view to display title, author, and description
   *
   * @return GameInfo Record
   */
  public GameDisplayInfo getGameDisplayInfo() {
    String type = properties.getProperty(SimFileKeys.TYPE.getSimFileKeyName());
    String title = properties.getProperty(SimFileKeys.TITLE.getSimFileKeyName());
    String author = properties.getProperty(SimFileKeys.AUTHOR.getSimFileKeyName());
    String description = properties.getProperty(SimFileKeys.DESCRIPTION.getSimFileKeyName());
    return new GameDisplayInfo(type, title, author, description);
  }

  /**
   * Creates file object from init state csv file so that it can be read by the caller
   *
   * @return File of init state csv
   */
  public File getInitStateCsv() {
    String initStatesCsvName = properties.getProperty(
        SimFileKeys.INITIALSTATES.getSimFileKeyName());
    return new File(initStatesCsvName);
  }
}