package dk.voresgruppe;

import dk.voresgruppe.dal.CourseRepository;
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
        Parent root = FXMLLoader.load(u);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.show();

        CourseRepository cRep = new CourseRepository();
    }
}
