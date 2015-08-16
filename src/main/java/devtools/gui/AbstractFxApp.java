package devtools.gui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**Abstract class for creating JavaFx-Applications. <br/>
 *
 * This class overrides the start method and calls createMainScene(), addComponents().
 * <br/><br/>Created by nico on 16.08.15.
 */
public abstract class AbstractFxApp extends Application {
    protected Stage stage; //initialized through start()
    protected Scene scene; //initialized through createMainScene()

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.scene = createMainScene();
        stage.setScene(scene);
        addComponents();
        stage.show();
    }

    public abstract Scene createMainScene();
    public abstract void addComponents();
}
