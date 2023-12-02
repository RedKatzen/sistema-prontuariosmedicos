package br.com.ienh.trabalhofinalprontuarios;

import br.com.ienh.trabalhofinalprontuarios.database.TesteConn;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private static Scene sceneJanelaPacientes, sceneJanelaMedicos, sceneJanelaProntuarios;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader janelaInicial = new FXMLLoader(Application.class.getResource("tela-inicial-view.fxml"));
        Scene scene = new Scene(janelaInicial.load(), 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}