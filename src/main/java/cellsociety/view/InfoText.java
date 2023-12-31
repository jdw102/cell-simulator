package cellsociety.view;

/**
 * A view class that contains the title, author, and description of the simulation
 *
 * @author Jerry Worthy
 */
public class InfoText {

  private String title;
  private String author;
  private String description;
  private String param;

  public InfoText() {
    title = "";
    author = "";
    description = "";
    param = "";
  }

  public void setText(String title, String author, String description, String param) {
    this.title = title;
    this.author = author;
    this.description = description;
    this.param = param;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getDescription() {
    return description;
  }

  public String getParam() {
    return param;
  }
}
