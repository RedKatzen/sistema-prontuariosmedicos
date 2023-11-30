package br.com.ienh.trabalhofinalprontuarios;

import br.com.ienh.trabalhofinalprontuarios.database.TesteConn;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {                             // a mudar tela
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("tabela-pacientes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 500);
        stage.setTitle("Sistema de Prontuários");
        stage.setScene(scene);
        stage.show();
        TesteConn.test();
    }

    public static void main(String[] args) {
        launch();
    }
}