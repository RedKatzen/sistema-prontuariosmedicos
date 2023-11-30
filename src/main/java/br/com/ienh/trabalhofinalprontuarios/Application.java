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
        FXMLLoader janelaPacientes = new FXMLLoader(Application.class.getResource("janela-pacientes-view.fxml"));
        FXMLLoader janelaMedicos = new FXMLLoader(Application.class.getResource("janela-medicos-view.fxml"));
        FXMLLoader janelaProntuarios = new FXMLLoader(Application.class.getResource("janela-prontuarios-view.fxml"));

        primaryStage = stage;
        primaryStage.setTitle("Sistemas Prontuários");

        Parent parentJanelaPacientes = janelaPacientes.load();
        Parent parentJanelaMedicos = janelaMedicos.load();
    //    Parent parentJanelaProntuarios = janelaProntuarios.load();

        sceneJanelaPacientes = new Scene(parentJanelaPacientes, 1100, 550);
        sceneJanelaMedicos = new Scene(parentJanelaMedicos, 1100, 550);
    //    sceneJanelaProntuarios = new Scene(parentJanelaProntuarios, 1100, 550);

        stage.setScene(sceneJanelaPacientes);
        stage.show();
        TesteConn.test();
    }

    public static void changeScreen(int opcao) {
        switch (opcao) {
            case 1:
                primaryStage.setScene(sceneJanelaMedicos);
                break;
            case 2:
                primaryStage.setScene(sceneJanelaMedicos);
                break;
            case 3:
                primaryStage.setScene(sceneJanelaProntuarios);
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}