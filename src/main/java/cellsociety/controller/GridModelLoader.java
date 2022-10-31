package cellsociety.controller;

import static cellsociety.Main.DEFAULT_RESOURCE_PACKAGE;
import static cellsociety.Main.GRID_MODEL_CHOOSER_FOLDER;

import cellsociety.model.DefaultGridModel;
import cellsociety.model.GridModel;
import cellsociety.model.NeighborhoodsLoader;
import cellsociety.model.statehandlers.StateHandler;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.ResourceBundle;

/**
 * Loads the correct GridModel for this simulation type. The pairings between simulation types and
 * GridModels are located in gridmodel_chooser.properties in the resources folder
 *
 * @author Ryan Wolfram
 */
public class GridModelLoader {

  public static final String RESOURCE_FILE_NAME = "gridmodel_chooser";
  public static final String GRID_MODEL_PACKAGE_PATH = DEFAULT_RESOURCE_PACKAGE + "model.";

  /**
   * Returns the correct GridModel for this simulation type. The pairings between simulation types
   * and GridModels are located in a properties file in the resources folder. If no pairing for this
   * simulation type exists in the properties file, return a DefaultGridModel.
   *
   * @param simType             the type of simulation
   * @param neighborhoodsLoader the NeighborhoodsLoader passed to new GridModel
   * @param stateHandler        the StateHandler passed to the new GridModel
   * @return GridModel
   */
  public GridModel getGridModel(String simType, NeighborhoodsLoader neighborhoodsLoader,
      StateHandler stateHandler) {

    ResourceBundle mapping = ResourceBundle.getBundle(
        DEFAULT_RESOURCE_PACKAGE + GRID_MODEL_CHOOSER_FOLDER + RESOURCE_FILE_NAME);
    Collection<String> simTypes = getSimTypes(mapping);

    if (!simTypes.contains(simType)) {
      return new DefaultGridModel(neighborhoodsLoader, stateHandler);
    } else {
      return getGridModelInstance(mapping.getString(simType), neighborhoodsLoader, stateHandler);
    }

  }


  // use reflection to get the correct GridModel instance
  private GridModel getGridModelInstance(String modelType,
      NeighborhoodsLoader neighborhoodsLoader, StateHandler stateHandler) {
    try {

      Class<?> clazz = Class.forName(GRID_MODEL_PACKAGE_PATH + modelType);
      return (GridModel) clazz.getDeclaredConstructor(NeighborhoodsLoader.class,
          StateHandler.class).newInstance(neighborhoodsLoader, stateHandler);

    } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
             InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  // load a Collection of all simTypes in the properties file
  private Collection<String> getSimTypes(ResourceBundle mapping) {
    Enumeration<String> en = mapping.getKeys();
    Collection<String> simTypes = new HashSet<>();
    while (en.hasMoreElements()) {
      simTypes.add(en.nextElement());
    }

    return simTypes;
  }
}
