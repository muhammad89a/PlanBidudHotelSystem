package planBidudHotel.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import planBidudHotel.utils.Routes;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLSplashController extends  BaseFXMLController implements Initializable {

    @FXML
    AnchorPane ap;

    class ShowSplashScreen extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(2000);
                Platform.runLater(() -> {
                    loadScreen(Routes.HOME,"Home",ap);
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
