package cellsociety.view;

/**
 * A view class that contains the title, author, and description of the simulation
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

  public void setText(String title, String author, String description) {
    this.title = title;
    this.author = author;
    this.description = description;
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
}
