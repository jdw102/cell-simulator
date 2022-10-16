package cellsociety.view;

/**
 * A view class that contains the title, author, and description of the simulation
 */
public class InfoText {
    private String title;
    private String author;
    private String description;
    /**
     * Creates a new instance of the simulation information.
     * @param t the title
     * @param a the author
     * @param d the description
     */
    public InfoText(String t, String a, String d){
        title = t;
        author = a;
        description = d;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String s){
        title = s;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String s){
        author = s;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String s){
        description = s;
    }
}
