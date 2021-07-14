package planBidudHotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import planBidudHotel.utils.Constants;

import java.io.IOException;
import java.net.URL;
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
            loadStage(Constants.HANDLE_REQUEST,"Handle Request",homeContainer);
        } else if (mouseEvent.getSource() == btnOpenRequest) {
            loadStage(Constants.OPEN_REQUEST,"Open Request",homeContainer);
        } else if (mouseEvent.getSource() == btn_Timetable) {
            loadStage("/home/fxml/Timetable.fxml","",homeContainer);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}
