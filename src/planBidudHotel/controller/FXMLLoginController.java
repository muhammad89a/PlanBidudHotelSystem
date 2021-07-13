package planBidudHotel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import planBidudHotel.utils.Constants;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLLoginController implements Initializable {

    @FXML
    private AnchorPane loginContainerAnchorPane;
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private AnchorPane registerAnchorPane;

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    JFXButton btnLogin;
    @FXML
    Label messageLabel;

    String aaa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
