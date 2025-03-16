module org.openjfx.inf122finalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.openjfx.inf122finalproject to javafx.fxml;
    exports org.openjfx.inf122finalproject;
}