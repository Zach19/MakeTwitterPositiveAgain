import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class View extends Pane{

    private Label user = new Label("User:");
    private Label username = new Label("@username");
    private Label topTweet = new Label("Your Most Positive Tweet:");
    private Label worstTweet = new Label("Your Most Negative Tweet:");
    private Label score = new Label("Your Score:");
    private TextField text = new TextField();
    private Button button = new Button("Search");

    public TextField getText(){return text;}
    public Button getButton(){return button;}
    public Label getUsername(){return username;}
    public Label getTopTweet(){return topTweet;}
    public Label getWorstTweet(){return worstTweet;}
    public Label getScore(){return score;}


    private User model;

    public View (User m){
        model = m;

        user.relocate(50,50);
        user.setPrefSize(50,30);

        text.relocate(125, 50);
        text.setPrefSize(150,30);

        button.relocate(150,100);
        button.setPrefSize(100,30);
        button.setDisable(true);

        username.relocate(125, 150);
        username.setPrefSize(250, 30);
        username.setFont(Font.font ("Arial Black", 20));

        topTweet.relocate(125, 200);
        topTweet.setPrefSize(700, 30);
        topTweet.setFont(Font.font ("Arial", 10));

        worstTweet.relocate(125, 225);
        worstTweet.setPrefSize(700, 30);
        worstTweet.setFont(Font.font ("Arial", 10));

        score.relocate(50, 300);
        score.setPrefSize(300, 30);
        score.setFont(Font.font ("Arial Black", 20));

        button.disableProperty().bind(
                Bindings.isEmpty(text.textProperty())
        );

        getChildren().addAll(user, text, button, username, topTweet, worstTweet, score);
    }

    public void update(){

    }

}