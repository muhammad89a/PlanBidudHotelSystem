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

import java.io.IOException;
import java.net.URL;
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
                Thread.sleep(5000);

                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("SecondScene.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLSplashController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    ap.getScene().getWindow().hide();
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLSplashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ShowSplashScreen().start();
    }
}
