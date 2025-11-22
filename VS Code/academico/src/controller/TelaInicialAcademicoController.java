package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import util.CaminhoArquivo;

public class TelaInicialAcademicoController implements Initializable {

    @FXML
    private Button buttonAluno;
    @FXML
    private AnchorPane anchorPaneTelaInicialLogo;
    @FXML
    private Button buttonDisciplina;
    @FXML
    private Button buttonTurma;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    private void trocarTela(AnchorPane telaAtual, String caminhoNovaTelaFXML) throws IOException {
        AnchorPane novaTela = (AnchorPane) FXMLLoader.load(getClass().getResource(caminhoNovaTelaFXML));
        telaAtual.getChildren().setAll(novaTela);
    }

    @FXML
    private void handleButtonAluno(ActionEvent event) {
        try {
            trocarTela(anchorPaneTelaInicialLogo, CaminhoArquivo.TELA_ALUNO);
        } catch (IOException ex) {
            System.out.println("Erro ao carregar a Tela Aluno: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleButtonDisciplina(ActionEvent event) {
        try {
            trocarTela(anchorPaneTelaInicialLogo, CaminhoArquivo.TELA_DISCIPLINA);
        } catch (IOException ex) {
            System.out.println("Erro ao carregar a Tela Disciplina: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleButtonTurma(ActionEvent event) {
        try {
            trocarTela(anchorPaneTelaInicialLogo, CaminhoArquivo.TELA_TURMA);
        } catch (IOException ex) {
            System.out.println("Erro ao carregar a Tela Turma: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
