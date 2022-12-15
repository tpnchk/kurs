module com.example.kursova {
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.web;
    requires java.sql;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
    opens com.example.kursova to javafx.fxml;
    exports com.example.kursova;
    exports com.example.kursova.controller;
    opens com.example.kursova.controller to javafx.fxml;
}