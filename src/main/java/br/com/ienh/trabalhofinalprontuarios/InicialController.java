package br.com.ienh.trabalhofinalprontuarios;

import br.com.ienh.trabalhofinalprontuarios.database.TesteConn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class InicialController {

    private static Scene sceneJanelaPacientes, sceneJanelaMedicos, sceneJanelaProntuarios, sceneJanelaSobre;
    private static Stage primaryStage;
    private static FXMLLoader janelaPacientes, janelaMedicos, janelaProntuarios, janelaSobre;

    @FXML
    private ImageView imageViewLogo;


    @FXML
    protected void initialize() {
        try {
            Image img = new Image("C:\\Aula\\Gabriel\\ADS\\TecnicasDeProgramacao\\TRABALHO-FINAL\\sistema-prontuariosmedicos-master_0.2\\src\\main\\fonts\\img\\logo.png");
            imageViewLogo.setImage(img);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAcessarPacientesBtnClick() throws IOException {
        Stage stage = new Stage();

        janelaPacientes = new FXMLLoader(Application.class.getResource("janela-pacientes-view.fxml"));
        janelaMedicos = new FXMLLoader(Application.class.getResource("janela-medicos-view.fxml"));
        janelaProntuarios = new FXMLLoader(Application.class.getResource("janela-prontuarios-view.fxml"));

        primaryStage = stage;
        primaryStage.setTitle("Health Care - Sistemas Prontuários");

        Parent parentJanelaPacientes = janelaPacientes.load();
        Parent parentJanelaMedicos = janelaMedicos.load();
        Parent parentJanelaProntuarios = janelaProntuarios.load();

        sceneJanelaPacientes = new Scene(parentJanelaPacientes, 1100, 550);
        sceneJanelaMedicos = new Scene(parentJanelaMedicos, 1100, 620);
        sceneJanelaProntuarios = new Scene(parentJanelaProntuarios, 1200, 620);

        stage.setScene(sceneJanelaProntuarios);
        stage.show();
    }

    public static void changeScreen(int opcao) {
        switch (opcao) {
            case 1:
                primaryStage.setScene(sceneJanelaPacientes);
                primaryStage.setTitle("Health Care - Info. Pacientes");
                break;
            case 2:
                primaryStage.setScene(sceneJanelaMedicos);
                primaryStage.setTitle("Health Care - Info. Médicos");
                break;
            case 3:
                primaryStage.setScene(sceneJanelaProntuarios);
                primaryStage.setTitle("Health Care - Prontuários");
                break;
        }
    }

    @FXML
    protected void onAcessarSobreBtnClick() throws IOException {
        Stage stage = new Stage();

        janelaSobre = new FXMLLoader(Application.class.getResource("janela-sobre-view.fxml"));

        primaryStage = stage;
        primaryStage.setTitle("Health Care - Informações");

        Parent parentJanelaSobre = janelaSobre.load();
        sceneJanelaSobre = new Scene(parentJanelaSobre, 500, 500);

        stage.setScene(sceneJanelaSobre);
        stage.show();
    }
}
