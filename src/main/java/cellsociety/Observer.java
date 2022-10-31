package cellsociety;

/**
 * Updates based on changes from an Observable
 *
 * @author Daniel Feinblatt
 */
public interface Observer {

  /**
   * Update based on the observable change
   */
  void update();
}
