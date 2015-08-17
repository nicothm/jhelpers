package devtools.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**Holds functions for creating simple javafx-dialogs.
 *
 * <P>Created by nico on 17.08.15.</P>
 */
public final class FxDialogs {
    private FxDialogs() { }

    private static Alert abstractAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    /**
     * Creates an information dialog.
     *
     * @see FxDialogs#newInformationDialog(String, String, String)
     * @param title the text for the title-bar
     * @param content the content/message of the dialog
     */
    public static void newInformationDialog(String title, String content) {
        newInformationDialog(title, null, content);
    }

    /**
     * Creates an information dialog.
     *
     * @param title the text for the title-bar
     * @param header the text for the header
     * @param content the content/message of the dialog
     */
    public static void newInformationDialog(String title, String header, String content) {
        Alert alert = abstractAlert(Alert.AlertType.INFORMATION, title, header, content);
        alert.showAndWait();
    }

    /**
     * Creates an warning dialog.
     *
     * @see FxDialogs#newWarningDialog(String, String, String)
     * @param title the text for the title-bar
     * @param content the content/message of the warning-dialog
     */
    public static void newWarningDialog(String title,  String content) {
        newWarningDialog(title, null, content);
    }

    /**
     * Creates an warning dialog.
     *
     * @param title the text for the title-bar
     * @param header the text for the header
     * @param content the content/message of the warning-dialog
     */
    public static void newWarningDialog(String title, String header, String content) {
        Alert alert = abstractAlert(Alert.AlertType.WARNING, title, header, content);
        alert.showAndWait();
    }

    /**
     * Creates an error dialog.
     * @see FxDialogs#newErrorDialog(String, String, String)
     * @param title the text for the title-bar
     * @param content the content/message of the error-dialog
     */
    public static void newErrorDialog(String title, String content) {
        newErrorDialog(title,null, content);
    }

    /**
     * Creates an error dialog.
     *
     * @param title the text for the title-bar
     * @param header the text for the header
     * @param content the content/message of the error-dialog
     */
    public static void newErrorDialog(String title, String header, String content) {
        Alert alert = abstractAlert(Alert.AlertType.ERROR, title, header, content);
        alert.showAndWait();
    }

    /**
     * Creates an exception dialog which shows the stacktrace in the dialog.
     *
     * @see FxDialogs#newExceptionDialog(String, String, String, Exception)
     * @param title the text for the title-bar
     * @param content the description what happened
     * @param e the exception that occured
     */
    public static void newExceptionDialog(String title, String content, Exception e) {
        newExceptionDialog(title,null,content,e);
    }

    /**
     * Creates an exception dialog which shows the stacktrace in the dialog.
     *
     * @param title the text for the title-bar
     * @param header the text for the header
     * @param content the description what happened
     * @param e the exception that occured
     */
    public static void newExceptionDialog(String title, String header, String content, Exception e) {
        Alert alert = abstractAlert(Alert.AlertType.ERROR, title, header, content);

        // Create Exception text.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane pane = new GridPane();
        pane.setMaxWidth(Double.MAX_VALUE);
        pane.add(label, 0, 0);
        pane.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(pane);
        alert.showAndWait();
    }

    /**
     * Creates an confirmation dialog.
     *
     * @see FxDialogs#newConfirmationDialog(String, String, String)
     * @see Alert#showAndWait()
     * @param title the text for the title-bar
     * @param content the description what has to be confirmed
     * @return an optional that represents the button that has pressed.
     *         If the optional is present than ok was pressed,
     *         if it's not present the user has chosen cancel or closed the dialog.
     */
    public static Optional<ButtonType> newConfirmationDialog(String title, String content) {
        return newConfirmationDialog(title,null,content);
    }

    /**
     * Creates an confirmation dialog.
     *
     * @param title the text for the title-bar
     * @param header the text for the header
     * @param content the description what has to be confirmed
     * @return an optional that represents the button that has pressed.
     *         If the optional is present than ok was pressed,
     *         if it's not present the user has chosen cancel or closed the dialog.
     */
    public static Optional<ButtonType> newConfirmationDialog(String title, String header, String content) {
        Alert alert = abstractAlert(Alert.AlertType.CONFIRMATION, title, header, content);
        return alert.showAndWait();
    }
}
