package controller;

import data.RepositorioDisciplina;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Disciplina;
import model.IDisciplina;

public class TelaCadastrarDisciplinaController implements Initializable {

    @FXML
    private Button buttonCadastarDisciplina;
    @FXML
    private Button buttonCancelarCadastroDisciplina;
    @FXML
    private TextField textFieldIdDisciplina;
    @FXML
    private TextField textFieldNomeDisciplina;
    @FXML
    private TextField textFieldCargaHorariaDisciplina;
    @FXML
    private Label labelStatusCadastrarDisciplina;

    private Stage dialogStage;

    private IDisciplina bancoDeDadosDisciplinas = new RepositorioDisciplina();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limparCamposDaTela();
    }

    public void limparCamposDaTela() {
        this.textFieldIdDisciplina.clear();
        this.textFieldNomeDisciplina.clear();
        this.textFieldCargaHorariaDisciplina.clear();

        this.labelStatusCadastrarDisciplina.setText("");

        this.textFieldNomeDisciplina.requestFocus();
    }

    @FXML
    private void handleCadastrarDisciplina(ActionEvent event) {

        if (!textFieldNomeDisciplina.getText().isEmpty() && !textFieldCargaHorariaDisciplina.getText().isEmpty()) {
            String nome = textFieldNomeDisciplina.getText().toUpperCase();
            int cargaHoraria = Integer.parseInt(textFieldCargaHorariaDisciplina.getText());

            bancoDeDadosDisciplinas.createDisciplina(new Disciplina(nome, cargaHoraria));

            this.dialogStage.close();
        } else {
            labelStatusCadastrarDisciplina.setText("Dados inválidos.");
            textFieldNomeDisciplina.requestFocus();
        }
    }

    @FXML
    private void handleCancelarCadastroDisciplina(ActionEvent event) {
        this.dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
