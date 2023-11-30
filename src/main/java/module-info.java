module br.com.ienh.trabalhofinalprontuarios {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens br.com.ienh.trabalhofinalprontuarios to javafx.fxml;
    opens br.com.ienh.trabalhofinalprontuarios.database.entidades to javafx.base;
    exports br.com.ienh.trabalhofinalprontuarios;
    opens br.com.ienh.trabalhofinalprontuarios.database to javafx.base;
}