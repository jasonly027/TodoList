module net.jasonly.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens net.jasonly.todolist to javafx.fxml;
    exports net.jasonly.todolist;
    exports net.jasonly.todolist.controllers;
    exports net.jasonly.todolist.models;
    exports net.jasonly.todolist.lib;
    opens net.jasonly.todolist.components to javafx.fxml;
    opens net.jasonly.todolist.controllers to javafx.fxml;
}