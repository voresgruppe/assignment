package dk.voresgruppe.dal;

import dk.voresgruppe.be.Student;
import dk.voresgruppe.be.User;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StudentRepository {

    public ArrayList<Student> loadStudents() {

        ArrayList<Student> studentObservableList = new ArrayList<Student>();

        File file = new File("resources/data/MockStudentData.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Student student = stringLineToStudent(line);
                studentObservableList.add(student);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentObservableList;
    }

    private Student stringLineToStudent(String line) {
        String[] arrStudent = line.split(",");
        String fName = arrStudent[0];
        String lName = arrStudent[1];
        String birthDay = arrStudent[2];
        String course = arrStudent[3];
        User studentUser = new User(arrStudent[4], arrStudent[5]);
        return new Student(fName, lName, birthDay, course, studentUser);
    }


}
