package br.com.ienh.trabalhofinalprontuarios;

import br.com.ienh.trabalhofinalprontuarios.database.entidades.Paciente;
import br.com.ienh.trabalhofinalprontuarios.database.entidades.PacienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PacientesController {
    @FXML
    private TableView<Paciente> tabelaPacientes;

    @FXML
    private TableColumn<Paciente, Integer> colunaId;

    @FXML
    private TableColumn<Paciente, String> colunaNome;

    @FXML
    private TableColumn<Paciente, String> colunaDataNascimento;

    @FXML
    private TableColumn<Paciente, Short> colunaSexo;

    @FXML
    private TableColumn<Paciente, String> colunaCpf;

    @FXML
    protected void initialize() throws Exception {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        colunaSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    }

    @FXML
    protected void onPesquisarPorTodosBtnClick() throws Exception{
        visualizarPacientes();
    }

    private void popularTabela(List<Paciente> pacientes) throws Exception {
        ObservableList<Paciente> pacienteList = FXCollections.observableArrayList();
        pacienteList.addAll(pacientes);
        tabelaPacientes.setItems(pacienteList);
    }

    private void visualizarPacientes() throws Exception {
        List<Paciente> pacientes = PacienteDAO.obterTodosPacientes();
        popularTabela(pacientes);
    }

}
