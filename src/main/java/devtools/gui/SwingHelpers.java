package devtools.gui;

import javax.swing.*;

/**
 * Created by nico on 15.08.15.
 */
public final class SwingHelpers {
    private SwingHelpers() {}

    public static void onEdt(Runnable r) {
        SwingUtilities.invokeLater(r);
    }
}
