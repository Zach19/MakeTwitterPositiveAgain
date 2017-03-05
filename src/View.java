import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class View extends Pane{

    private Label user = new Label("User:");
    private TextField text = new TextField();
    private Button button = new Button("Search");

    public TextField getText(){return text;}
    public Button getButton(){return button;}

    private User model;

    public View (User m){
        model = m;

        user.relocate(100,50);
        user.setPrefSize(50,30);

        text.relocate(175, 50);
        text.setPrefSize(150,30);

        button.relocate(200,100);
        button.setPrefSize(100,30);
        button.setDisable(true);

        button.disableProperty().bind(
                Bindings.isEmpty(text.textProperty())
        );

        getChildren().addAll(user, text, button);
    }

    public void update(){

    }

}