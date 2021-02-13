package dk.voresgruppe.util;

import javafx.scene.control.Alert;

public class UserError {
    /**
     * Shows an alert with a app-defined error
     * @param header header and title text
     * @param error error-message
     */
    public static void showError(String header, String error) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText(header);
        a.setTitle(header);
        a.setContentText(error);
        a.show();
    }
}
