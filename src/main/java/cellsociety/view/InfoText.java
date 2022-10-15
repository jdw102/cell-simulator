package cellsociety.view;

public class InfoText {
    private String title;
    private String author;
    private String description;

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
