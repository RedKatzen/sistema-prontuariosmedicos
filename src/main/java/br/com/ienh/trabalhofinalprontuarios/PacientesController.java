package br.com.ienh.trabalhofinalprontuarios;

import br.com.ienh.trabalhofinalprontuarios.database.entidades.Paciente;
import br.com.ienh.trabalhofinalprontuarios.database.PacienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacientesController {
    @FXML
    protected TableView<Paciente> tabelaPacientes = new TableView<>();

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
    private TextField txtPesquisaId;

    @FXML
    private Label lblErro;

    @FXML
    private Label lblModo;

    @FXML
    private Label lblNome;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lblDataNascimento;

    @FXML
    private TextField txtDataNascimento;

    @FXML
    private Label lblSexo;

    @FXML
    private TextField txtSexo;

    @FXML
    private Label lblCpf;

    @FXML
    private TextField txtCpf;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnSairModoEdicao;

    @FXML
    private Button btnExcluir;

    @FXML
    private HBox container;

    @FXML
    private HBox rootPacientesView;

    private boolean modoEdicao;

    private Paciente pacienteEmEdicao;

    private Stage tela;

    FXMLLoader janelaPacientes;
    FXMLLoader janelaMedicos;
    FXMLLoader janelaProntuarios;

    @FXML
    protected void initialize() throws Exception {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDataNascimento.setCellValueFactory(new PropertyValueFactory<>("data_nascimento"));
        colunaSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));


        setModoInsercao();
        visualizarPacientes();
    }

    @FXML
    protected void onJanelaMedicosBtnClick() throws IOException {
        InicialController.changeScreen(2);
    }

    @FXML
    protected void onJanelaProntuariosBtnClick() throws IOException {
        InicialController.changeScreen(3);
    }

    public void changeScreen(int opcao) throws IOException {
        switch (opcao) {
            case 1:
                tela.setTitle("Pacientes");
                tela.setScene(new Scene(janelaPacientes.load(), 500, 500));
                break;
            case 2:
                tela.setTitle("Médicos");
                tela.setScene(new Scene(janelaMedicos.load(), 500, 500));
                break;
            case 3:
//                tela.setScene(sceneJanelaProntuarios);
                break;
        }
    }

    @FXML
    protected void onPesquisarPorTodosBtnClick() throws Exception{
        visualizarPacientes();
    }

    @FXML
    protected void onPesquisarPorIdBtnClick() throws Exception {
        int idBusca = Integer.parseInt(txtPesquisaId.getText());
        Paciente paciente = PacienteDAO.obterPorId(idBusca);
        if(paciente != null) {
            List<Paciente> pacientes = new ArrayList<>();
            pacientes.add(paciente);
            popularTabela(pacientes);
        }
    }

    @FXML
    protected void onSalvarBtnClick() throws Exception {
        int qtdModificacoes = 0;
        String nome = txtNome.getText();
        String dataNascimento = txtDataNascimento.getText();
        String sexo = txtSexo.getText();
        String cpf = txtCpf.getText();

        if(modoEdicao) {
            pacienteEmEdicao.setNome(nome);
            pacienteEmEdicao.setData_nascimento(dataNascimento);
            pacienteEmEdicao.setSexo(sexo);
            pacienteEmEdicao.setCpf(cpf);
            qtdModificacoes = PacienteDAO.atualiza(pacienteEmEdicao);
        }else{
            Paciente paciente = new Paciente(nome, dataNascimento, sexo, cpf);
            qtdModificacoes = PacienteDAO.insere(paciente);
        }

        if(qtdModificacoes > 0){
            mostrarTodos();
            setModoInsercao();
            limpaCampos();
        }
    }

    @FXML
    protected void onEditarBtnClick() {
        Paciente paciente = tabelaPacientes.getSelectionModel().getSelectedItem();
        if(paciente != null) {
            setModoEdicao(paciente);
        }
    }

    @FXML
    protected void onSairEdicaoBtnClick() {
        setModoInsercao();
        limpaCampos();
    }

    @FXML
    protected void onExcluirBtnClick() throws Exception{
        Paciente paciente = tabelaPacientes.getSelectionModel().getSelectedItem();
        if(paciente != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exclusão");
            alert.setHeaderText("Você está processando a exclusão do paciente de id " + paciente.getId() + " (" + paciente.getNome() + ")");
            alert.setContentText("Deseja continuar?");

            Optional<ButtonType> resultado = alert.showAndWait();
            if(resultado.get() == ButtonType.OK){
                int qtdModificacoes = PacienteDAO.exclui(paciente);
                if(qtdModificacoes > 0) {
                    mostrarTodos();
                    setModoInsercao();
                }
            }
        }
    }

    private void setModoInsercao() {
        modoEdicao = false;
        lblModo.setText("Modo inserção");
        btnSairModoEdicao.setVisible(false);
        btnEditar.setVisible(true);
    }

    private void setModoEdicao(Paciente paciente) {
        modoEdicao = true;
        lblModo.setText("Modo edição");
        txtNome.setText(paciente.getNome());
        txtDataNascimento.setText(paciente.getData_nascimento());
        txtSexo.setText(paciente.getSexo());
        txtCpf.setText(String.valueOf(paciente.getCpf()));
        pacienteEmEdicao = paciente;
        btnSairModoEdicao.setVisible(true);
        btnEditar.setVisible(false);
    }

    private void limpaCampos() {
        txtNome.setText("");
        txtDataNascimento.setText("");
        txtSexo.setText("");
        txtCpf.setText("");
        txtPesquisaId.setText("");
    }

    private void mostrarTodos() throws Exception {
        List<Paciente> pacientes = PacienteDAO.obterTodosPacientes();
        popularTabela(pacientes);
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
