module net.jasonly.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens net.jasonly027.todolist to javafx.fxml;
    exports net.jasonly027.todolist;
    exports net.jasonly027.todolist.controllers;
    exports net.jasonly027.todolist.models;
    exports net.jasonly027.todolist.lib;
    opens net.jasonly027.todolist.components to javafx.fxml;
    opens net.jasonly027.todolist.controllers to javafx.fxml;
}