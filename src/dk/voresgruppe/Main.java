package dk.voresgruppe;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.bll.AdministratorManager;
import dk.voresgruppe.dal.db.DatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        java.net.URL u = getClass().getResource("gui/LoginView.fxml");
        if (u == null){
            System.out.println("pis");
        }
        Parent root = FXMLLoader.load(u);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
