package cellsociety.controller;

public enum RequiredSimKeys {
  TYPE("Type"),
  TITLE("Title"),
  AUTHOR("Author"),
  DESCRIPTION("Description"),
  INITIALSTATES("InitialStates");

  private String simFileKeyName;

  RequiredSimKeys(String simFileKeyName) {
    this.simFileKeyName = simFileKeyName;
  }

  public String getSimFileKeyName() {
    return simFileKeyName;
  }
}
