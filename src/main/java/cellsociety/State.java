package cellsociety;

public interface State {
  <T extends Enum<T>> T getState();
  Enum getSimulationType();
}