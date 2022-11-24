module javafxexamen.examenjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens javafxexamen.examenjavafx to javafx.fxml;
    exports javafxexamen.examenjavafx;
}
