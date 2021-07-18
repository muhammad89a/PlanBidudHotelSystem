package planBidudHotel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.internal.operators.observable.ObservableJust;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import planBidudHotel.database.DataBaseAPI;
import planBidudHotel.entities.Request;
import planBidudHotel.utils.Routes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLHandleRequestController implements Initializable {

    @FXML
    private AnchorPane anchorPaneContainer;

    @FXML
    private JFXButton btnAddNewTraveler;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Button backBtn;

    @FXML
    private JFXTextArea jfxTextArea;

    @FXML
    private JFXButton sendRequestBtn;

    @FXML
    private JFXTextField idTf;


    @FXML
    private JFXTextField validatedText;

    @FXML
    private JFXTextField emailTF;

    @FXML
    private JFXTextField flightNumberTf;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getRequests();
    }

    public void onBackPressed(ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == backBtn) {
            loadHome();
        }
    }

    void loadHome() {
        try {
            Stage stage = (Stage) anchorPaneContainer.getScene().getWindow();
            Parent myNewScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Routes.HOME)));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger("").log(Level.SEVERE, null, ex);
        }
    }

    private void getRequests() {
        DataBaseAPI.getInstance().getRequests()
                .observeOn(Schedulers.io())
                .doOnSubscribe((it)->{
                    System.out.println("doOnSubscribe");
                    progressIndicator.setVisible(true);
                })
                .subscribeOn(JavaFxScheduler.platform())
                .doAfterTerminate(()->{
                    System.out.println("doAfterTerminate");
                    progressIndicator.setVisible(false);
                })
                .subscribe(it -> {
                    for (Request rs : it) {
                        System.out.println(rs);
                    }
                })
                .dispose();

    }
}
