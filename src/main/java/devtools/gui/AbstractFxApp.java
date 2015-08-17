package devtools.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**Abstract class for creating JavaFx-Applications.
 *
 * <P>This class overrides the start method and calls createMainScene(), addComponents().</P>
 * <P>Created by nico on 16.08.15.</P>
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

    /**
     * This method returns the main scene of the application.
     * @return a scene that's bind to the primary-stage.
     */
    protected abstract Scene createMainScene();

    /**
     * This method adds all components to the main-container.
     */
    protected abstract void addComponents();
}
