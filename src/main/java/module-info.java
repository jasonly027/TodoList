module net.jasonly.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens net.jasonly027.todolist to javafx.fxml;
    exports net.jasonly027.todolist;
    exports net.jasonly027.todolist.controllers;
    opens net.jasonly027.todolist.controllers to javafx.fxml;
}