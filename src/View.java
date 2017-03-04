import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class View extends Application{

    private Label user = new Label("User:");
    private TextField text = new TextField();
    private Button button = new Button("Search");
    private String userName;

    public void start(Stage primaryStage) throws TwitterException {
        Pane aPane = new Pane();

        user.relocate(100,50);
        user.setPrefSize(50,30);

        text.relocate(175, 50);
        text.setPrefSize(150,30);

        button.relocate(200,100);
        button.setPrefSize(100,30);
        button.setDisable(true);


        aPane.getChildren().addAll(user, text, button);
        primaryStage.setTitle("Make Twitter Positive Again");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane, 500, 150));
        primaryStage.show();

        button.setOnAction(actionEvent ->{
            userName = text.getText();
            User newUser = new User(userName);
        });


        button.disableProperty().bind(
                Bindings.isEmpty(text.textProperty())
                                );



    }

    public static void main(String[] args) {
        launch(args);

    }

    public void update(){

    }

    public void search() throws TwitterException {

        System.out.println("THIS IS USERNAME: " + userName);

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("ME0BKDlR2hWaSGlc41lm2AGdE");
        cb.setDebugEnabled(true).setOAuthConsumerSecret("zLuOiqDd37LoAoXLO7EUZgjwDg6zzuR5qrLcyQlGiiztfRwwKA");
        cb.setDebugEnabled(true).setOAuthAccessToken("780890285727440896-xsL2jycwpbKzw8N1RvPR5O1t04eN2nE");
        cb.setDebugEnabled(true).setOAuthAccessTokenSecret("TIeQMGQrgguBsFGAbgqRM4BvL4R8dBSIt2E2BaGHapNsm");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter = tf.getInstance();
        Paging page = new Paging(1,100);
        List<Status> users = twitter.getUserTimeline(userName, page);
        for (Status user : users) {
            System.out.println(user + "1");
        }
    }
}

