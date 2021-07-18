package planBidudHotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.hsqldb.rights.User;
import planBidudHotel.utils.Constant;
import planBidudHotel.utils.Routes;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public abstract class BaseFXMLController implements Initializable {
    private Preferences prefs = Preferences.userNodeForPackage(User.class);

    @FXML
    protected Pane parentView;

    public void setParentView(Pane parentView){
        this.parentView = parentView;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("BaseFXMLController");
    }
    protected void checkAuthBeforeLoadScreen(String route,String title,Pane anchorPaneContainer){
        if(!prefs.getBoolean(Constant.USER_AUTHENTICATED,false)){
            loadScreen(Routes.LOGIN,title,anchorPaneContainer);
        }else {
            loadScreen(route,"login",anchorPaneContainer);
        }
    }

    void loadScreen(String route,String title,Pane anchorPaneContainer) {
        try {
            Stage stage = (Stage) anchorPaneContainer.getScene().getWindow();
            Parent myNewScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(route)));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger("").log(Level.SEVERE, null, ex);
        }
    }
}
