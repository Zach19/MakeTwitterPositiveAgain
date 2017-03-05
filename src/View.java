import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class View extends Pane{

    private Label user = new Label("User:");
    private Label username = new Label("@username");
    private Label topTweet = new Label("Your Most Positive Tweet:");
    private Label worstTweet = new Label("Your Most Negative Tweet:");
    private Label angerScore = new Label("Anger:");
    private Label joyScore = new Label("Joy:");
    private Label fearScore = new Label("Fear:");
    private Label sadnessScore = new Label("Sadness:");
    private Label surpriseScore = new Label("Surprise:");
    private Label score = new Label("Your Score:");
    private TextField text = new TextField();
    private Button button = new Button("Search");
    private Rectangle angerBox = new Rectangle(120,400,0,30);
    private Rectangle joyBox = new Rectangle(120,450,0,30);
    private Rectangle fearBox = new Rectangle(120,500,0,30);
    private Rectangle sadnessBox = new Rectangle(120,550,0,30);
    private Rectangle surpriseBox = new Rectangle(120,600,0,30);
    private Line seperator = new Line(495,50,495,650);
    private TextField enterTweet = new TextField("Enter a tweet...");
    private Button submit = new Button("Submit Tweet");
    private Label potentialScore = new Label();
    private Label title = new Label("Should I Post This Tweet?");
   // private Image logo;

    public TextField getText(){return text;}
    public Button getButton(){return button;}
    public Label getUsername(){return username;}
    public Label getTopTweet(){return topTweet;}
    public Label getWorstTweet(){return worstTweet;}
    public Label getScore(){return score;}

    public Rectangle getAngerBox() {
        return angerBox;
    }

    public Rectangle getFearBox() {
        return fearBox;
    }

    public Rectangle getJoyBox() {
        return joyBox;
    }

    public Rectangle getSadnessBox() {
        return sadnessBox;
    }

    public Rectangle getSurpriseBox() {
        return surpriseBox;
    }
    public TextField getEnterTweet(){
        return enterTweet;
    }

    public Button getSubmit(){
        return submit;
    }

    public Label getPotentialScore(){
        return potentialScore;
    }

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

        enterTweet.setPrefSize(400,150);
        enterTweet.relocate(520,80);
        enterTweet.setFont(Font.font("Arial", 14));
        enterTweet.setAlignment(Pos.TOP_LEFT);

        submit.setPrefSize(200,30);
        submit.relocate(600,250);

        title.setPrefSize(500,30);
        title.setFont(new Font("Arial Black", 25));
        title.relocate(500,5);
        title.setAlignment(Pos.CENTER);

        username.relocate(125, 150);
        username.setPrefSize(250, 30);
        username.setFont(Font.font ("Arial Black", 20));

        topTweet.relocate(10, 200);
        topTweet.setPrefSize(450, 30);
        topTweet.setFont(Font.font ("Arial", 14));

        worstTweet.relocate(10, 225);
        worstTweet.setPrefSize(450, 30);
        worstTweet.setFont(Font.font ("Arial", 14));

        score.relocate(50, 300);
        score.setPrefSize(400, 30);
        score.setFont(Font.font ("Arial Black", 20));

        potentialScore.relocate(600,350);
        potentialScore.setPrefSize(400,60);
        potentialScore.setFont(new Font("Arial Black", 20));

        angerScore.relocate(50,400);
        angerScore.setPrefSize(100,30);
        joyScore.relocate(50,450);
        joyScore.setPrefSize(100,30);
        fearScore.relocate(50,500);
        fearScore.setPrefSize(100,30);
        sadnessScore.relocate(50,550);
        sadnessScore.setPrefSize(100,30);
        surpriseScore.relocate(50,600);
        surpriseScore.setPrefSize(100,30);

        seperator.setStrokeWidth(10);

        angerBox.setFill(Color.RED);
        joyBox.setFill(Color.GOLD);
        fearBox.setFill(Color.PURPLE);
        sadnessBox.setFill(Color.BLUE);
        surpriseBox.setFill(Color.GREEN);

        button.disableProperty().bind(
                Bindings.isEmpty(text.textProperty())
        );

        getChildren().addAll(user, text, button, username, topTweet, worstTweet, score, angerScore, joyScore, fearScore, sadnessScore, surpriseScore, angerBox,joyBox,fearBox,surpriseBox,sadnessBox,seperator, enterTweet, submit, potentialScore, title);
    }

    public void update(){

    }

}