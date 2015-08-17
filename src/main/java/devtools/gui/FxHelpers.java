package devtools.gui;

import javafx.application.Platform;

/**
 * Helpers for JavaFx-Applications.
 * <P>Created by nico on 15.08.15.</P>
 */
public final class FxHelpers {
    private FxHelpers() {}

    /**
     * Runs the given function on the fx-gui-thread.
     *
     * @see Platform#runLater(Runnable)
     * @param r the function that should be executed on the gui-thread.
     */
    public static void onFxEdt(Runnable r) {
        Platform.runLater(r);
    }
}
