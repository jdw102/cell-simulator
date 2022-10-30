package cellsociety.view;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;

/**
 * Data class to hold a string representing the state name of a cell and the color associated with
 * it.
 */
public class StateColors implements Iterator {

  private final Map<String, Color> colorMap;
  private Iterator<String> iterator;

  /**
   * Creates new instance of the state colors object.
   *
   * @param colors resource bundle to read through keys and create map
   */
  public StateColors(ResourceBundle colors) {
    colorMap = new HashMap<>();
    Enumeration<String> keys = colors.getKeys();
    while (keys.hasMoreElements()) {
      String s = keys.nextElement();
      colorMap.put(s, Color.valueOf(colors.getString(s)));
    }
    iterator = colorMap.keySet().iterator();
  }

  /**
   * Gets the color associated wth the state.
   *
   * @param state the name of the state
   */
  public Color getColor(String state) {
    return colorMap.get(state);
  }

  /**
   * Changes the color associated with the state.
   *
   * @param state the name of the state
   * @param color the color of the state
   */
  public void changeStateColor(String state, Color color) {
    colorMap.put(state, color);
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public String next() {
    return iterator.next();
  }

  public void resetIterator() {
    iterator = colorMap.keySet().iterator();
  }
}
