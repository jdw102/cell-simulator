package cellsociety.controller;

import cellsociety.GameDisplayInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads the contents of a sim file
 *
 * @author Daniel Feinblatt
 */
public class SimParser extends FileParser {

  public static final String SIM_FILE_TYPE = "sim";
  private final String pathToFiles;
  private Properties properties;

  /**
   * Checks if file is a sim file, then reads the contents of the sim file
   *
   * @param simFile The sim file describing the simulation to be read
   * @throws IOException
   * @throws WrongFileTypeException
   */
  public SimParser(File simFile)
      throws IOException, WrongFileTypeException {
    properties = null;
    pathToFiles = new File(simFile.getParent()).getParent();
    // Check if it is a .sim file
    isFileTypeCorrect(simFile, SIM_FILE_TYPE);
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
    String type = properties.getProperty(SimFileKeys.TYPE.getSimFileKeyName());
    String title = properties.getProperty(SimFileKeys.TITLE.getSimFileKeyName());
    String author = properties.getProperty(SimFileKeys.AUTHOR.getSimFileKeyName());
    String description = properties.getProperty(SimFileKeys.DESCRIPTION.getSimFileKeyName());
    String colorString = properties.getProperty(SimFileKeys.STATECOLORS.getSimFileKeyName());
    String params = getParams();
    String[] colors = new String[0];
    if (colorString != null) {
      colorString.replaceAll("\\s", "");
      colors = colorString.split(",");
    }
    return new GameDisplayInfo(type, title, author, description, colors, params);
  }

  /**
   * Creates file object from init state csv file so that it can be read by the caller
   *
   * @return File of init state csv
   */
  public File getInitStateCsv() throws WrongFileTypeException {
    String initStatesCsvName = properties.getProperty(
        SimFileKeys.INITIALSTATES.getSimFileKeyName());
    File csvFile = new File(pathToFiles, initStatesCsvName);
    isFileTypeCorrect(csvFile, "csv");
    return csvFile;
  }

  public String getParams() {
    String params = properties.getProperty(SimFileKeys.PARAMS.getSimFileKeyName());
    return params;
  }
}
