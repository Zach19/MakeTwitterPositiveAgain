/**
 * Created by mikesayegh on 2017-03-04.
 */
public class Tweet {
    private String content;
    private String date;

    public Tweet(String currentContent, String currentDate){
        this.content = currentContent;
        this.date = currentDate;
    }
    public Tweet(){
        this.content = "";
        this.date = "";
    }

    public String getContent(){return this.content;}
    public String getDate(){return this.date;}





}
