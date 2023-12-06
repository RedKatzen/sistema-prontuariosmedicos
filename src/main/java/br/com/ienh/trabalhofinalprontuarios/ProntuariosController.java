package br.com.ienh.trabalhofinalprontuarios;

import br.com.ienh.trabalhofinalprontuarios.database.MedicoDAO;
import br.com.ienh.trabalhofinalprontuarios.database.ProntuarioDAO;
import br.com.ienh.trabalhofinalprontuarios.database.entidades.Medico;
import br.com.ienh.trabalhofinalprontuarios.database.entidades.Prontuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProntuariosController {

    @FXML
    private Label lblPesquisaId;

    @FXML
    private TextField txtPesquisaId;

    @FXML
    private Label lblErro;

    @FXML
    private TableView<Prontuario> tabelaProntuarios = new TableView<>();

    @FXML
    private TableColumn<Prontuario, Integer> colunaId;

    @FXML
    private TableColumn<Prontuario, String> colunaPacienteId;

    @FXML
    private TableColumn<Prontuario, String> colunaDataAtendimento;

    @FXML
    private TableColumn<Prontuario, String> colunaDataAlta;

    @FXML
    private TableColumn<Prontuario, String> colunaAlergia;

    @FXML
    private TableColumn<Prontuario, Integer> colunaMedicoId;

    @FXML
    private Label lblModo;

    @FXML
    private TextField txtPacienteId;

    @FXML
    private TextField txtDataAtendimento;

    @FXML
    private TextField txtDataAlta;

    @FXML
    private TextField txtAlergia;

    @FXML
    private TextField txtMedicoId;

    private Prontuario prontuarioEmEdicao;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnSairModoEdicao;

    @FXML
    private Button btnExcluir;

    private boolean modoEdicao;

    @FXML
    protected void initialize() throws Exception {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaPacienteId.setCellValueFactory(new PropertyValueFactory<>("paciente_id"));
        colunaDataAtendimento.setCellValueFactory(new PropertyValueFactory<>("data_atendimento"));
        colunaDataAlta.setCellValueFactory(new PropertyValueFactory<>("data_alta"));
        colunaAlergia.setCellValueFactory(new PropertyValueFactory<>("alergia"));
        colunaMedicoId.setCellValueFactory(new PropertyValueFactory<>("medico_id"));

        setModoInsercao();
        visualizarProntuarios();
    }

    @FXML
    protected void onJanelaPacientesBtnClick() {
        InicialController.changeScreen(1);
    }

    @FXML
    protected void onJanelaMedicosBtnClick() {
        InicialController.changeScreen(2);
    }

    @FXML
    protected void onPesquisarPorIdBtnClick() throws Exception {
        int idBusca = Integer.parseInt(txtPesquisaId.getText());
        Prontuario prontuario = ProntuarioDAO.obterPorId(idBusca);
        if(prontuario != null) {
            List<Prontuario> prontuarios = new ArrayList<>();
            prontuarios.add(prontuario);
            popularTabela(prontuarios);
        }
    }

    @FXML
    protected void onPesquisarPorTodosBtnClick() throws Exception{
        visualizarProntuarios();
    }

    @FXML
    protected void onSalvarBtnClick() throws Exception {
        int qtdModificacoes = 0;
        int pacienteId = Integer.parseInt(txtPacienteId.getText());
        String dataAtendimento = txtDataAtendimento.getText();
        String dataAlta = txtDataAlta.getText();
        String alergia = txtAlergia.getText();
        int medicoId = Integer.parseInt(txtMedicoId.getText());

        if(modoEdicao) {
            prontuarioEmEdicao.setPaciente_id(pacienteId);
            prontuarioEmEdicao.setData_atendimento(dataAtendimento);
            prontuarioEmEdicao.setData_alta(dataAlta);
            prontuarioEmEdicao.setAlergia(alergia);
            prontuarioEmEdicao.setMedico_id(medicoId);
            qtdModificacoes = ProntuarioDAO.atualiza(prontuarioEmEdicao);
        }else{
            Prontuario prontuario = new Prontuario(pacienteId, dataAtendimento, dataAlta, alergia, medicoId);
            qtdModificacoes = ProntuarioDAO.insere(prontuario);
        }

        if(qtdModificacoes > 0){
            mostrarTodos();
            setModoInsercao();
            limpaCampos();
        }
    }

    @FXML
    protected void onEditarBtnClick() {
        Prontuario prontuario = tabelaProntuarios.getSelectionModel().getSelectedItem();
        if(prontuario != null) {
            setModoEdicao(prontuario);
        }
    }

    @FXML
    protected void onSairEdicaoBtnClick() {
        setModoInsercao();
        limpaCampos();
    }

    @FXML
    protected void onExcluirBtnClick() throws Exception{
        Prontuario prontuario = tabelaProntuarios.getSelectionModel().getSelectedItem();
        if(prontuario != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exclusão");
            alert.setHeaderText("Você está processando a exclusão do prontuário de id " + prontuario.getId());
            alert.setContentText("Deseja continuar?");

            Optional<ButtonType> resultado = alert.showAndWait();
            if(resultado.get() == ButtonType.OK){
                int qtdModificacoes = ProntuarioDAO.exclui(prontuario);
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

    private void setModoEdicao(Prontuario prontuario) {
        modoEdicao = true;
        lblModo.setText("Modo edição");
        txtPacienteId.setText(String.valueOf(prontuario.getPaciente_id()));
        txtDataAtendimento.setText(prontuario.getData_atendimento());
        txtDataAlta.setText(prontuario.getData_alta());
        txtAlergia.setText(prontuario.getAlergia());
        txtMedicoId.setText(String.valueOf(prontuario.getMedico_id()));
        prontuarioEmEdicao = prontuario;
        btnSairModoEdicao.setVisible(true);
        btnEditar.setVisible(false);
    }

    private void limpaCampos() {
        txtPacienteId.setText("");
        txtDataAtendimento.setText("");
        txtDataAlta.setText("");
        txtAlergia.setText("");
        txtMedicoId.setText("");
        txtPesquisaId.setText("");
    }

    private void mostrarTodos() throws Exception {
        List<Prontuario> prontuarios = ProntuarioDAO.obterTodosProntuarios();
        popularTabela(prontuarios);
    }

    private void popularTabela(List<Prontuario> prontuarios) throws Exception {
        ObservableList<Prontuario> prontuarioList = FXCollections.observableArrayList();
        prontuarioList.addAll(prontuarios);
        tabelaProntuarios.setItems(prontuarioList);
    }

    private void visualizarProntuarios() throws Exception {
        List<Prontuario> prontuarios = ProntuarioDAO.obterTodosProntuarios();
        popularTabela(prontuarios);
    }

}
