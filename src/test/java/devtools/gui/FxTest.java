package devtools.gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by nico on 16.08.15.
 */
public class FxTest extends AbstractFxApp {
    private final VBox mainContainer = new VBox();

    @Override
    public Scene createMainScene() {
        return new Scene(mainContainer, 200,200);
    }

    @Override
    public void addComponents() {
        mainContainer.getChildren().addAll(new Label("Test label1"), new Label("Test label2"));
    }


    public static void main(String args[]) {
        Application.launch(args);
    }
}
