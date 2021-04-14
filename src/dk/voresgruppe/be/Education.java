package dk.voresgruppe.be;

import javafx.beans.property.SimpleStringProperty;

public class Education {
    private int iD;
    private String name;

    public Education(String name) {
        this.name = name;
    }

    public int getiD() {
        return iD;
    }

    public SimpleStringProperty getIdProperty(){
        return new SimpleStringProperty(String.valueOf(iD));
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }
    public SimpleStringProperty getNameProperty(){
        return new SimpleStringProperty(String.valueOf(name));
    }

    public void setName(String name) {
        this.name = name;
    }
}
