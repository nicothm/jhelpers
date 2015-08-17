package devtools.gui;

import javax.swing.*;

/**
 * Helpers for Swing-Applications.
 * <P>Created by nico on 15.08.15.</P>
 */
public final class SwingHelpers {
    private SwingHelpers() {}

    /**
     * Runs the given function on the swing-edt-thread.
     *
     * @see SwingUtilities#invokeLater(Runnable)
     * @param r the function that should be executed on the gui-thread.
     */
    public static void onEdt(Runnable r) {
        SwingUtilities.invokeLater(r);
    }
}
