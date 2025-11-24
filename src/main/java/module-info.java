module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
<<<<<<< HEAD

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
=======
>>>>>>> 2af6895 (finishing initial version)
    requires java.desktop;
    requires java.sql;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}