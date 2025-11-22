package academico;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.CaminhoArquivo;

public class Principal extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(CaminhoArquivo.TELA_LOGIN));
            Scene scene = new Scene(root);

            Image imagemIconeDaJanela = new Image("icons/icon_logo.png");
            stage.getIcons().add(imagemIconeDaJanela);// define o icone da janela
            stage.setTitle("Acadêmico");
            stage.setResizable(false);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
