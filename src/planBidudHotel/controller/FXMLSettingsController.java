package planBidudHotel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.hsqldb.rights.User;
import planBidudHotel.utils.Constant;

import java.net.URL;
import java.util.ResourceBundle;
import  java.util.prefs.*;
import  java.util.prefs.*;

public class FXMLSettingsController implements Initializable {
    @FXML
    public JFXButton backBtn;

    @FXML
    private AnchorPane anchorPaneContainer;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private Preferences prefs = Preferences.userNodeForPackage(User.class);


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDefaultValues();
    }

    @FXML
    void saveNewUser(ActionEvent event) {
        String user = username.getText();
        String pass = password.getText();
        prefs.put(Constant.USER_NAME,user);
        prefs.put(Constant.USER_PASSWORE,pass);
    }

    private void initDefaultValues() {
        username.setText(prefs.get(Constant.USER_NAME, "Muhammad"));
        password.setText(prefs.get(Constant.USER_PASSWORE, "$Mahamid$"));
    }

    public void onBackPressed(ActionEvent actionEvent) {

    }
}
