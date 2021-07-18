package planBidudHotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import planBidudHotel.database.Consts;
import planBidudHotel.database.DatabaseConn;
import planBidudHotel.entities.Country;
import planBidudHotel.utils.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLHomeController extends BaseFXMLController implements Initializable {

    @FXML
    private AnchorPane homeContainer;


    @FXML
    private Button btnHandleRequest;

    @FXML
    private Button btnEmployees;

    @FXML
    private Button btn_Timetable;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnReports;

    @FXML
    private Button btnOpenRequest;

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnHandleRequest) {
            checkAuthBeforeLoadScreen(Routes.HANDLE_REQUEST, "Handle Request", homeContainer);
        } else if (mouseEvent.getSource() == btnOpenRequest) {
            loadScreen(Routes.OPEN_REQUEST, "Open Request", homeContainer);
        } else if (mouseEvent.getSource() == btn_Timetable) {
            checkAuthBeforeLoadScreen("/home/fxml/Timetable.fxml", "", homeContainer);
        } else if (mouseEvent.getSource() == btnSettings) {
            checkAuthBeforeLoadScreen(Routes.SETTINGS, "Settings", homeContainer);
        } else if (mouseEvent.getSource() == btnReports) {
            checkAuthBeforeLoadScreen(Routes.REPORTS, "REPORTS", homeContainer);
        }
    }

    @Override
    public void setParentView(Pane parentView) {
        this.parentView = homeContainer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location,resources);
        setParentView(homeContainer);
    }
}

