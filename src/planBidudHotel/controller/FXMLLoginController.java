package planBidudHotel.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.hsqldb.rights.User;
import planBidudHotel.utils.Constant;
import planBidudHotel.utils.Routes;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class FXMLLoginController extends  BaseFXMLController implements Initializable {

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
    private Preferences prefs = Preferences.userNodeForPackage(User.class);


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void switchToRegister(ActionEvent actionEvent) {
        loginAnchorPane.setVisible(false);
        registerAnchorPane.setVisible(true);
    }

    public void switchToLogin(ActionEvent actionEvent) {
        loginAnchorPane.setVisible(true);
        registerAnchorPane.setVisible(false);
    }

    public void goToHome(ActionEvent actionEvent) {
        loadScreen(Routes.HOME,"Home",loginContainerAnchorPane);
    }

    public void loginAction(ActionEvent actionEvent) {
        if(username.getText().equals("Admin") && password.getText().equals("Admin")){
            prefs.put(Constant.USER_NAME,"Admin");
            prefs.put(Constant.USER_PASSWORE,"Admin");
            prefs.putBoolean(Constant.USER_AUTHENTICATED,true);
            loadScreen(Routes.HOME,"Home Auth",loginContainerAnchorPane);
        }else {
            new Alert(Alert.AlertType.ERROR,"username or password invalid", ButtonType.OK).show();
        }
    }
}
