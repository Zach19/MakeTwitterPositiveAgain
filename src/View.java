import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.*;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;

public class View extends Application{

    private Label user = new Label("User:");
    private TextField text = new TextField();
    private Button button = new Button("Search");

    public void start(Stage primaryStage) {
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
            String username = text.getText();
            System.out.println(username);
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
}

