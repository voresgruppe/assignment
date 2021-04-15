package dk.voresgruppe.gui.AdministratorView;

import dk.voresgruppe.be.Administrator;
import dk.voresgruppe.bll.AdministratorManager;
import dk.voresgruppe.bll.CourseManager;
import dk.voresgruppe.bll.EducationManager;
import dk.voresgruppe.bll.TeacherManager;
import dk.voresgruppe.gui.AdministratorView.ManageAdministratorsView.ManageAdministratorsView;
import dk.voresgruppe.gui.AdministratorView.ManageCoursesView.ManageCoursesViewController;
import dk.voresgruppe.gui.AdministratorView.ManageEducationView.ManageEducationsViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorViewController {
    private Administrator loggedAdministrator;
    private AdministratorManager aMan = new AdministratorManager();
    private TeacherManager tMan  = new TeacherManager();
    private CourseManager cMan = new CourseManager();
    private EducationManager eMan = new EducationManager();


    public void setLoggedAdministrator(Administrator loggedAdministrator) {
        this.loggedAdministrator = loggedAdministrator;
    }

    public void manageAdministrators(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageAdministratorsView/ManageAdministratorsView.fxml"));
            Parent mainLayout = loader.load();
            ManageAdministratorsView mac = loader.getController();
            mac.setAMan(aMan);
            mac.init();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void manageTeachers(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageTeachersView/ManageTeachersView.fxml"));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void manageStudents(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageStudentsView/ManageStudentsView.fxml"));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void manageCourses(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageCoursesView/ManageCoursesView.fxml"));
            Parent mainLayout = loader.load();
            ManageCoursesViewController mcc = loader.getController();
            mcc.setCMan(cMan);
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void manageEducations(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageEducationView/ManageEducationsView.fxml"));
            Parent mainLayout = loader.load();
            ManageEducationsViewController mec = loader.getController();
            mec.seteMan(eMan);
            mec.init();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
