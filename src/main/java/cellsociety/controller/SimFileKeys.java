package cellsociety.controller;

/**
 * The posible keys in a sim file. To add more possible entries, can simply add them as options
 * here
 *
 * @author Daniel Feinblatt
 */
public enum SimFileKeys {
  TYPE("Type"),
  TITLE("Title"),
  AUTHOR("Author"),
  DESCRIPTION("Description"),
  INITIALSTATES("InitialStates"),
  STATECOLORS("StateColors"),
  PARAMS("Parameters");

  private final String simFileKeyName;

  SimFileKeys(String simFileKeyName) {
    this.simFileKeyName = simFileKeyName;
  }

  public String getSimFileKeyName() {
    return simFileKeyName;
  }
}
