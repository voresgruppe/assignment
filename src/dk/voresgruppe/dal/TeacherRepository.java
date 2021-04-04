package dk.voresgruppe.dal;

import dk.voresgruppe.be.Teacher;
import dk.voresgruppe.be.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {
    public List<Teacher> loadTeacher() {
        List<Teacher> allTeachers = new ArrayList<>();
        File file = new File("resources/data/MockTeacherData");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Teacher teacher = stringLineToTeacher(line);
                allTeachers.add(teacher);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allTeachers;
    }

    private Teacher stringLineToTeacher(String line) {
        String[] arrStudent = line.split(",");
        String fName = arrStudent[0];
        String lName = arrStudent[1];
        String birthDay = arrStudent[2];

        User teacherLogin = new User(arrStudent[3], arrStudent[4]);
        return new Teacher(fName, lName, birthDay, teacherLogin);
    }

}
