package br.com.ienh.trabalhofinalprontuarios;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InicialController {

    @FXML
    protected void onAcessarPacientesButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("janela-pacientes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 500);
        stage.setTitle("Informações de pacientes");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onAcessarSobreButtonClick() {

    }
}
