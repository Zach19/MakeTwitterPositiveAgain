import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import twitter4j.TwitterException;

import java.util.List;

/**
 * Created by mikesayegh on 2017-03-04.
 */
public class IndicoApp extends Application {
    private User model;
    private View view;

    public void start(Stage primaryStage){
        model = new User("");
        view = new View(model);

        view.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tweetList();
            }
        });


        primaryStage.setTitle("Make Twitter Positive Again");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 1000, 650));
        primaryStage.show();


    }


    public void tweetList(){

        User newUser = new User(view.getText().getText());
        try {
            List<Tweet> temp = newUser.compileTweets();
            System.out.println(temp.get(0).getContent());
        } catch(TwitterException e){
            e.printStackTrace();
        }

    }



}
