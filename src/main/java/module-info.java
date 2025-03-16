module org.openjfx.inf122finalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens org.openjfx.inf122finalproject to javafx.fxml;
    exports org.openjfx.inf122finalproject;
}