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
import planBidudHotel.utils.Reservation;
import planBidudHotel.utils.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLHomeController implements Initializable {

    @FXML
    private AnchorPane homeContainer;


    @FXML
    private Button btnHandleRequest;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btn_Timetable;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnReports;

    @FXML
    private Button btnOpenRequest;

    //my bad - the freaking mouse event
    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnHandleRequest) {
            loadStage(Routes.HANDLE_REQUEST,"Handle Request",homeContainer);
        } else if (mouseEvent.getSource() == btnOpenRequest) {
            loadStage(Routes.OPEN_REQUEST,"Open Request",homeContainer);
        } else if (mouseEvent.getSource() == btn_Timetable) {
            loadStage("/home/fxml/Timetable.fxml","",homeContainer);
        }else if (mouseEvent.getSource() == btnSettings){
            loadStage(Routes.SETTINGS,"Settings",homeContainer);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getReservation();
    }

    void loadStage(String fxml, String title, Pane container) {
        Logger.getLogger("").log(Level.SEVERE, null, title);

        try {
            Stage stage = (Stage) container.getScene().getWindow();
            Parent myNewScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger("").log(Level.SEVERE, null, ex);
        }
    }


    public ArrayList<Reservation> getReservation() {
        ArrayList<Reservation> results = new ArrayList<Reservation>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_RESERVATION);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int i = 1;
                    Reservation r = new Reservation(rs.getLong(i++), rs.getLong(i++), rs.getDate(i++),
                            rs.getDate(i++), rs.getLong(i++));
                    results.add(r);
                    System.out.println(r);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }
}

