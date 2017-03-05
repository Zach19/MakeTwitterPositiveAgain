import io.indico.api.utils.IndicoException;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import twitter4j.TwitterException;

import java.io.IOException;
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
            User newUser = null;
            try {
                newUser = new User(userName);
            } catch (IndicoException e) {
                e.printStackTrace();
            }
            try {
                IndicoJudgement judger = new IndicoJudgement();
                newUser.compileTweets();
                newUser.setSentimentValues();
                System.out.println(newUser.calculate());

            } catch (IndicoException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TwitterException e) {
                e.printStackTrace();
            }

        });


        button.disableProperty().bind(
                Bindings.isEmpty(text.textProperty()));



    }

    public static void main(String[] args) {
        launch(args);

    }

    public void update(){

    }

}

