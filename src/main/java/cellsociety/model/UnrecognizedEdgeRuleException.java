package cellsociety.model;

public class UnrecognizedEdgeRuleException extends Exception {

  private static String UNRECOGNIZED_EDGE_RULE_MESSAGE = "Could not recognize provided edge rule:"
      + " %s.";

  public UnrecognizedEdgeRuleException(String rule) {
    super(String.format(UNRECOGNIZED_EDGE_RULE_MESSAGE, rule));
  }

}
