package devtools.gui;

import javafx.application.Platform;

/**
 * Created by nico on 15.08.15.
 */
public final class FxHelpers {
    private FxHelpers() {}

    public static void onFxEdt(Runnable r) {
        Platform.runLater(r);
    }
}
