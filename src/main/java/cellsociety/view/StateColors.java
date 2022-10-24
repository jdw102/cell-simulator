package cellsociety.view;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.scene.paint.Color;

public class StateColors {

  private final Map<String, Color> colorMap;


  public StateColors(ResourceBundle colors) {
    colorMap = new HashMap<>();
    Enumeration<String> keys = colors.getKeys();
    while (keys.hasMoreElements()) {
      String s = keys.nextElement();
      colorMap.put(s, Color.valueOf(colors.getString(s)));
    }
  }

  public Color getColor(String state) {
    return colorMap.get(state);
  }

  public void changeStateColor(String state, Color color) {
    colorMap.put(state, color);
  }

  public Set<String> getStates() {
    return colorMap.keySet();
  }
}
