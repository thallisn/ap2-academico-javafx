package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.CaminhoArquivo;

public class TelaLoginController implements Initializable {

    @FXML
    private TextField textFieldEmail;
    @FXML
    private PasswordField passwordFieldSenha;
    @FXML
    private Button buttonAcessar;
    @FXML
    private Label labelAcessoInvalido;
    @FXML
    private AnchorPane anchorPaneTelaLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void trocarTela(AnchorPane telaAtual, String caminhoNovaTelaFXML) throws IOException {
        AnchorPane novaTela = (AnchorPane) FXMLLoader.load(getClass().getResource(caminhoNovaTelaFXML));
        telaAtual.getChildren().setAll(novaTela);
    }

    @FXML
    private void handleButtonAcessar(ActionEvent event) {

        if (!textFieldEmail.getText().isEmpty() && !passwordFieldSenha.getText().isEmpty()) {
            labelAcessoInvalido.setText("");
            try {
                trocarTela(anchorPaneTelaLogin, CaminhoArquivo.TELA_INICIAL_ACADEMICO);

            } catch (IOException ex) {
                System.err.println("Erro ao fazer login: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            labelAcessoInvalido.setText("Email ou senha inválidos.");
            textFieldEmail.requestFocus();
        }
    }
}
