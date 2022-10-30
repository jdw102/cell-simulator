package cellsociety.controller;

public enum SimFileKeys {
  TYPE("Type"),
  TITLE("Title"),
  AUTHOR("Author"),
  DESCRIPTION("Description"),
  INITIALSTATES("InitialStates"),
  STATECOLORS("StateColors"),
  PARAMS("Params");

  private final String simFileKeyName;

  SimFileKeys(String simFileKeyName) {
    this.simFileKeyName = simFileKeyName;
  }

  public String getSimFileKeyName() {
    return simFileKeyName;
  }
}
