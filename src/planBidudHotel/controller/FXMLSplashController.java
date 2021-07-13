package planBidudHotel.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import planBidudHotel.utils.Constants;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLSplashController implements Initializable {

    @FXML
    VBox ap;

    class ShowSplashScreen extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(3000);
                Platform.runLater(() -> {
                    loadLogin();
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLSplashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        void loadLogin() {
            try {
                Stage stage = null;
                Parent myNewScene = null;
                stage = (Stage) ap.getScene().getWindow();
                myNewScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.LOGIN)));
                Scene scene = new Scene(myNewScene);
                stage.setScene(scene);
                stage.setTitle("Login & register");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger("").log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ShowSplashScreen().start();
    }
}
