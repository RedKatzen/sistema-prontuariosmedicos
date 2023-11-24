module br.com.ienh.trabalhofinalprontuarios {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.ienh.trabalhofinalprontuarios to javafx.fxml;
    exports br.com.ienh.trabalhofinalprontuarios;
}