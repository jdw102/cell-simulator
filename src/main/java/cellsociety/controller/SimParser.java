package cellsociety.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SimParser {

  private Properties properties;

  public SimParser(File simFile) throws IOException { // catch this in controller and tell view
    properties = null;
    readSimFile(simFile);
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
    String type = properties.getProperty(RequiredSimKeys.TYPE.getSimFileKeyName());
    String title = properties.getProperty(RequiredSimKeys.TITLE.getSimFileKeyName());
    String author = properties.getProperty(RequiredSimKeys.AUTHOR.getSimFileKeyName());
    String description = properties.getProperty(RequiredSimKeys.DESCRIPTION.getSimFileKeyName());
    return new GameDisplayInfo(type, title, author, description);
  }

  /**
   * Creates file object from init state csv file so that it can be read by the caller
   *
   * @return File of init state csv
   */
  public File getInitStateCsv() {
    String initStatesCsvName = properties.getProperty(
        RequiredSimKeys.INITIALSTATES.getSimFileKeyName());
    return new File(initStatesCsvName);
  }
}
