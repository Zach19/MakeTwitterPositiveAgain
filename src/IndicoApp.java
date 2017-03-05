import io.indico.api.utils.IndicoException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import twitter4j.TwitterException;
import java.io.IOException;

import static java.lang.Math.round;

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

        view.getSubmit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    submitTweet();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IndicoException e) {
                    e.printStackTrace();
                }
            }
        });

        view.getEnterTweet().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.getEnterTweet().setText("");
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
        newUser.calculateEmotions();
        double total = newUser.calculate();
        view.getTopTweet().setText("Most Positive :   " + newUser.mostPositiveTweet());
        view.getWorstTweet().setText("Most Negative :   " + newUser.mostNegativeTweet());
        view.getScore().setText("Your Score: " + total + "% Positivity");
        view.getUsername().setText("@" + newUser.getUserName());
        view.getAngerBox().setWidth(newUser.anger*300);
        view.getFearBox().setWidth(newUser.fear*300);
        view.getJoyBox().setWidth(newUser.joy*300);
        view.getSadnessBox().setWidth(newUser.sadness*300);
        view.getSurpriseBox().setWidth(newUser.surprise*300);

    }

    public void submitTweet() throws IOException, IndicoException {
        double score;
        IndicoJudgement judge = new IndicoJudgement();
        score = judge.judgeReception(view.getEnterTweet().getText());
        if(score >= 0.5) {
            view.getPotentialScore().setText("You should post this tweet.\nHas a reception score of " + round(score*100) + "%");
        }
        else{
            view.getPotentialScore().setText("You should not post this tweet.\nHas a reception score of " + round(score*100) + "%");
        }
    }



}