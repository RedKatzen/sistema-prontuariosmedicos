package br.com.ienh.trabalhofinalprontuarios;

import br.com.ienh.trabalhofinalprontuarios.database.MedicoDAO;
import br.com.ienh.trabalhofinalprontuarios.database.PacienteDAO;
import br.com.ienh.trabalhofinalprontuarios.database.entidades.Medico;
import br.com.ienh.trabalhofinalprontuarios.database.entidades.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicosController {

    @FXML
    private Label lblPesquisaId;

    @FXML
    private TextField txtPesquisaId;

    @FXML
    private Label lblErro;

    @FXML
    private TableView<Medico> tabelaMedicos = new TableView<>();

    @FXML
    private TableColumn<Medico, Integer> colunaId;

    @FXML
    private TableColumn<Medico, String> colunaNome;

    @FXML
    private TableColumn<Medico, String> colunaEspecialidade;

    @FXML
    private TableColumn<Medico, String> colunaDataAdmissao;

    @FXML
    private TableColumn<Medico, String> colunaCpf;

    @FXML
    private TableColumn<Medico, String> colunaEmail;

    @FXML
    private Label lblModo;

    @FXML
    private Label lblNome;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lblDataAdmissao;

    @FXML
    private TextField txtDataAdmissao;

    @FXML
    private Label lblEspecialidade;

    @FXML
    private TextField txtEspecialidade;

    @FXML
    private Label lblCpf;

    @FXML
    private TextField txtCpf;

    @FXML
    private Label lblEmail;

    @FXML
    private TextField txtEmail;

    private Medico medicoEmEdicao;

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
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaDataAdmissao.setCellValueFactory(new PropertyValueFactory<>("data_admissao"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        setModoInsercao();
        visualizarMedicos();
    }

    @FXML
    protected void onJanelaPacientesBtnClick() {
        InicialController.changeScreen(1);
    }

    @FXML
    protected void onJanelaProntuariosBtnClick() {
        InicialController.changeScreen(3);
    }

    @FXML
    protected void onPesquisarPorIdBtnClick() throws Exception {
        int idBusca = Integer.parseInt(txtPesquisaId.getText());
        Medico medico = MedicoDAO.obterPorId(idBusca);
        if(medico != null) {
            List<Medico> medicos = new ArrayList<>();
            medicos.add(medico);
            popularTabela(medicos);
        }
    }

    @FXML
    protected void onPesquisarPorTodosBtnClick() throws Exception{
        visualizarMedicos();
    }

    @FXML
    protected void onSalvarBtnClick() throws Exception {
        int qtdModificacoes = 0;
        String nome = txtNome.getText();
        String especialidade = txtEspecialidade.getText();
        String cpf = txtCpf.getText();
        String dataAdmissao = txtDataAdmissao.getText();
        String email = txtEmail.getText();

        if(modoEdicao) {
            medicoEmEdicao.setNome(nome);
            medicoEmEdicao.setData_admissao(dataAdmissao);
            medicoEmEdicao.setEspecialidade(especialidade);
            medicoEmEdicao.setEmail(email);
            medicoEmEdicao.setCpf(cpf);
            qtdModificacoes = MedicoDAO.atualiza(medicoEmEdicao);
        }else{
            Medico medico = new Medico(nome, especialidade, cpf, dataAdmissao, email);
            qtdModificacoes = MedicoDAO.insere(medico);
        }

        if(qtdModificacoes > 0){
            mostrarTodos();
            setModoInsercao();
            limpaCampos();
        }
    }

    @FXML
    protected void onEditarBtnClick() {
        Medico medico = tabelaMedicos.getSelectionModel().getSelectedItem();
        if(medico != null) {
            setModoEdicao(medico);
        }
    }

    @FXML
    protected void onSairEdicaoBtnClick() {
        setModoInsercao();
        limpaCampos();
    }

    @FXML
    protected void onExcluirBtnClick() throws Exception{
        Medico medico = tabelaMedicos.getSelectionModel().getSelectedItem();
        if(medico != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exclusão");
            alert.setHeaderText("Você está processando a exclusão do medico de id " + medico.getId() + " (" + medico.getNome() + ")");
            alert.setContentText("Deseja continuar?");

            Optional<ButtonType> resultado = alert.showAndWait();
            if(resultado.get() == ButtonType.OK){
                int qtdModificacoes = MedicoDAO.exclui(medico);
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

    private void setModoEdicao(Medico medico) {
        modoEdicao = true;
        lblModo.setText("Modo edição");
        txtNome.setText(medico.getNome());
        txtEspecialidade.setText(medico.getEspecialidade());
        txtCpf.setText(String.valueOf(medico.getCpf()));
        txtDataAdmissao.setText(medico.getData_admissao());
        txtEmail.setText(medico.getEmail());
        medicoEmEdicao = medico;
        btnSairModoEdicao.setVisible(true);
        btnEditar.setVisible(false);
    }

    private void limpaCampos() {
        txtNome.setText("");
        txtEspecialidade.setText("");
        txtDataAdmissao.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtPesquisaId.setText("");
    }

    private void mostrarTodos() throws Exception {
        List<Medico> medicos = MedicoDAO.obterTodosMedicos();
        popularTabela(medicos);
    }

    private void popularTabela(List<Medico> medicos) throws Exception {
        ObservableList<Medico> medicoList = FXCollections.observableArrayList();
        medicoList.addAll(medicos);
        tabelaMedicos.setItems(medicoList);
    }

    private void visualizarMedicos() throws Exception {
        List<Medico> medicos = MedicoDAO.obterTodosMedicos();
        popularTabela(medicos);
    }
}
