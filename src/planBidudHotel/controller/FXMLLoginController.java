package planBidudHotel.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

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
