import io.indico.api.utils.IndicoException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import twitter4j.TwitterException;

import java.io.IOException;

public class IndicoApp extends Application {
    private User model;
    private View view;

    public void start(Stage primaryStage) throws IndicoException{
        model = new User("");
        view = new View(model);

        view.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    tweetList();
                } catch (IndicoException e) {
                    e.printStackTrace();
                } catch (TwitterException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        primaryStage.setTitle("Make Twitter Positive Again");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 1000, 650));
        primaryStage.show();


    }


    public void tweetList() throws IndicoException, TwitterException, IOException {

        User newUser = new User(view.getText().getText());
        newUser.compileTweets();
        newUser.setSentimentValues();
        double total = newUser.calculate();
        view.getTopTweet().setText("Your Most Positive Tweet:   " + newUser.mostPositiveTweet());
        view.getWorstTweet().setText("Your Most Negative Tweet:   " + newUser.mostNegativeTweet());
        view.getScore().setText("Your Score:   " + total);
        view.getUsername().setText("@" + newUser.getUserName());

    }



}