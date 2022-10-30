package cellsociety.view;

import static cellsociety.Main.CSV_FILE_EXTENSION;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SimFileWriter {

  private String currentSimType;
  private ResourceBundle simStates;

  public SimFileWriter(String simType, ResourceBundle states) {
    currentSimType = simType;
    simStates = states;
  }

  public void createSim(File f, InfoText infoText, GridView cellGrid) throws FileNotFoundException {
    String folder = f.getParent();
    String name = f.getName().substring(0, f.getName().indexOf('.'));
    createCSV(folder, name, cellGrid);
    String relFolder = f.getParentFile().getName();
    String type = String.format("Type=%s", currentSimType);
    String initialStates = String.format("InitialStates=%s/%s%s", relFolder, name,
        CSV_FILE_EXTENSION);
    String title = String.format("Title=%s", infoText.getTitle());
    String author = String.format("Author=%s", infoText.getAuthor());
    String description = String.format("Description=%s", infoText.getDescription());
    List<String> list = new ArrayList<>(
        Arrays.asList(type, initialStates, title, author, description));
    PrintWriter printWriter = new PrintWriter(f);
    for (String s : list) {
      printWriter.println(s);
    }
    printWriter.close();
  }

  private void createCSV(String folder, String name, GridView cellGrid)
      throws FileNotFoundException {
    int numRows = cellGrid.getNumRows();
    int numCols = cellGrid.getNumCols();
    String dimension = String.format("%s,%s", Integer.toString(numRows),
        Integer.toString(numCols));
    String[][] initialData = readInitialData(cellGrid);
    String[] csvData = createCSVData(initialData);
    File f = new File(String.format("%s/%s%s", folder, name, CSV_FILE_EXTENSION));
    f.delete();
    PrintWriter printWriter = new PrintWriter(f);
    printWriter.println(dimension);
    for (String s : csvData) {
      printWriter.println(s);
    }
    printWriter.close();
  }

  private String[][] readInitialData(GridView cellGrid) {
    String[][] initialData = new String[cellGrid.getNumRows()][cellGrid.getNumCols()];
    while (cellGrid.hasNext()) {
      CellView c = cellGrid.next();
      int i = c.getCoordinate().x();
      int j = c.getCoordinate().y();
      initialData[j][i] = simStates.getString(c.getStateName());
    }
    cellGrid.resetIterator();
    return initialData;
  }

  private String[] createCSVData(String[][] initialData) {
    String[] csvData = new String[initialData.length];
    for (int k = 0; k < initialData.length; k++) {
      String s = String.join(",", initialData[k]);
      csvData[k] = String.format("%s", s);
    }
    return csvData;
  }

}
