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

public class TelaAtualizarDisciplinaController implements Initializable {

    @FXML
    private Button buttonAtualizarDisciplina;
    @FXML
    private Button buttonCancelarAtualizarDisciplina;
    @FXML
    private TextField textFieldAtualizarIdDisciplina;
    @FXML
    private TextField textFieldAtualizarNomeDisciplina;
    @FXML
    private TextField textFieldAtualizarCargaHorariaDisciplina;
    @FXML
    private Label labelStatusAtualizarDisciplina;

    private IDisciplina bancoDeDadosDisciplinas = new RepositorioDisciplina();

    private Disciplina disciplina;

    private Stage dialogStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleAtualizarDisciplina(ActionEvent event) {
        if (!this.textFieldAtualizarNomeDisciplina.getText().isEmpty() && !textFieldAtualizarCargaHorariaDisciplina.getText().isEmpty()) {
            String novoNome = this.textFieldAtualizarNomeDisciplina.getText().toUpperCase();
            int novaCargaHoraria = Integer.parseInt(this.textFieldAtualizarCargaHorariaDisciplina.getText());

            this.disciplina.setNome(novoNome);
            this.disciplina.setCargaHoraria(novaCargaHoraria);

            bancoDeDadosDisciplinas.updateDisciplina(disciplina);

            this.dialogStage.close();
        } else {
            labelStatusAtualizarDisciplina.setText("Dados inválidos.");
            textFieldAtualizarNomeDisciplina.requestFocus();
        }
    }

    @FXML
    private void handleCancelarAtualizarDisciplina(ActionEvent event) {
        this.dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDisciplina(Disciplina d) {
        this.disciplina = d;
        this.textFieldAtualizarIdDisciplina.setText(String.valueOf(disciplina.getId()));
        this.textFieldAtualizarNomeDisciplina.setText(disciplina.getNome());
        this.textFieldAtualizarCargaHorariaDisciplina.setText(String.valueOf(disciplina.getCargaHoraria()));

    }

    public Disciplina getDisciplina() {
        return disciplina;
    }
}
