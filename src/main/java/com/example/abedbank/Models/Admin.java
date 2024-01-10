package com.example.abedbank.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {

    private final StringProperty id;
    private final StringProperty username;
    private final StringProperty password;

    public Admin(String id, String username, String password) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.username = new SimpleStringProperty(this, "username", username);
        this.password = new SimpleStringProperty(this, "password", password);
    }

    public StringProperty idProperty() {
        return this.id;
    }

    public StringProperty usernameProperty() {
        return this.username;
    }

    public StringProperty passwordProperty() {
        return this.password;
    }
}

