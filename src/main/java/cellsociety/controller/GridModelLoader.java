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

public class GridModelLoader {

  public static final String RESOURCE_FILE_NAME = "gridmodel_chooser";
  public static final String GRID_MODEL_PACKAGE_PATH = DEFAULT_RESOURCE_PACKAGE + "model.";

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

  private Collection<String> getSimTypes(ResourceBundle mapping) {
    Enumeration<String> en = mapping.getKeys();
    Collection<String> simTypes = new HashSet<>();
    while (en.hasMoreElements()) {
      simTypes.add(en.nextElement());
    }

    return simTypes;
  }
}
