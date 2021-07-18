package planBidudHotel.controller;

import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import planBidudHotel.database.DataBaseAPI;
import planBidudHotel.entities.*;
import planBidudHotel.utils.Routes;
import planBidudHotel.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FXMLOpenRequestController extends BaseFXMLController implements Initializable {

    @FXML
    private AnchorPane anchorPaneContainer;

    @FXML
    private VBox addNewTravelersContainer;

    @FXML
    private Button backBtn;

    @FXML
    private ComboBox<Country> comboBox;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private TextField idTf,emailTF,flightNumberTf,phoneTf;

    @FXML
    public DatePicker datePicker;


    @FXML
    public Label flightNumberError,idTfError, pickDateError,comboBoxError,emailError, phoneError;


    ArrayList<ToggleGroup> toggleGroups = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getCountries();
        comboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            System.out.println(newValue);
        });

        idTf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                idTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }


    public void onBackPressed(ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == backBtn) {
            loadScreen(Routes.HOME, "Home", anchorPaneContainer);
        }
    }


    public void insertCountry(Country country) {
        DataBaseAPI.getInstance().insertCountry(new Country(0, country.getName(), country.isNeedIsolation()))
                .doOnComplete(() -> {
                    System.out.println("insert Country Completed");
                })
                .subscribe();
    }

    public void insertRequest(Request request) {
        Disposable subscribeInsertRequest = DataBaseAPI.getInstance()
                .insertRequest(new Request(0, request.getEmail(), request.getPhoneNo(),
                        request.getFightNum(), request.getExpectedLandDate(),
                        null, new Date(), Status.Pending, 0))
                .delay(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .doOnSubscribe(it -> {
                    System.out.println("setVisible true");
                    progressIndicator.setVisible(true);
                })
                .doAfterSuccess((it) -> {
                    System.out.println("doAfterSuccess false");
                    progressIndicator.setVisible(false);
                })
                .subscribeOn(JavaFxScheduler.platform())
                .subscribe(it -> new Alert(Alert.AlertType.INFORMATION, "it" + it, ButtonType.OK));
    }

    public void getCountries() {
        DataBaseAPI.getInstance().getCountries()
                .delay(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .doOnSubscribe(it -> {
                    System.out.println("setVisible true");
                    progressIndicator.setVisible(true);
                })
                .doOnTerminate(() -> {
                    System.out.println("setVisible false");
                    progressIndicator.setVisible(false);

                })
                .subscribeOn(JavaFxScheduler.platform())
                .subscribe(it -> {
                    comboBox.getItems().addAll(it);
                });
    }

    public void sendRequestAction(ActionEvent actionEvent) {
        if (validateInputs()) {

            insertRequest(null);
        }
    }

    private boolean validateInputs() {
        idTfError.setVisible(false);
        emailError.setVisible(false);
        pickDateError.setVisible(false);
        comboBoxError.setVisible(false);
        flightNumberError.setVisible(false);
        boolean validate = true;

        if (!Utils.isTextIsNumbers(idTf.getText())) {
            idTfError.setVisible(true);
            validate = false;
        }
        if (!Utils.isTextIsNumbers(flightNumberTf.getText())) {
            flightNumberError.setVisible(true);
            validate = false;
        }
        if (!Utils.isValidEmailAddress(emailTF.getText())) {
            emailError.setVisible(true);
            validate = false;
        }
        if (!Utils.isPhoneNumber(phoneTf.getText())) {
            phoneError.setVisible(true);
            validate = false;
        }

        if (datePicker.getValue() == null) {
            pickDateError.setVisible(true);
            validate = false;
        } else {
            Date landDate = java.sql.Date.valueOf(datePicker.getValue());
            if (!Utils.isFutureDate(landDate)) {
                pickDateError.setVisible(true);
                validate = false;
            }
        }
        if (comboBox.getSelectionModel().getSelectedItem() == null) {
            comboBoxError.setVisible(true);
            validate = false;
        }
        return validate;
    }

    public void addNewTraveler(ActionEvent actionEvent) {
        try {
            createNewTravelerUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Relatives> getRelatives() {
        ArrayList<Relatives> relativesArrayList = new ArrayList<>();
        addNewTravelersContainer.getChildren();
        for (int i = 0; i < addNewTravelersContainer.getChildren().size(); i++) {
            int id = Integer.parseInt(((TextField) addNewTravelersContainer.getChildren().get(i).lookup("#travelerIdTf")).getText());
            String relative = ((RadioButton)toggleGroups.get(i).getSelectedToggle()).getText();
            relativesArrayList.add(new Relatives(0,id, RelativeType.valueOf(relative)));
            System.out.println("---->"+relativesArrayList.get(relativesArrayList.size()-1));
        }
        return relativesArrayList;
    }

    private RelationOwnerDTO getRelationOwnerDTOFromInputs(){
        int id = Integer.parseInt(idTf.getText());
        String email = emailTF.getText();
        Date landDate = java.sql.Date.valueOf(datePicker.getValue());
        Country country = comboBox.getSelectionModel().getSelectedItem();
        String flightNo = flightNumberTf.getText();
        String phoneNo = phoneTf.getText();

        Request request = new Request(email,phoneNo,flightNo,landDate,landDate);
        Citizen citizen = new Citizen(id);
        getRelatives();
        return new RelationOwnerDTO(citizen,request,getRelatives());
    }

    private void createNewTravelerUI() throws IOException {
        System.out.println("click! createNewTravelerUI - " + addNewTravelersContainer.getChildren().size());
        if (addNewTravelersContainer.getChildren().size() < 4) {
            URL url =  getClass().getResource("../views/fxml/new_traveler_item.fxml");
            FXMLLoader.load(Objects.requireNonNull(url));
            addNewTravelersContainer.getChildren().add(FXMLLoader.load(Objects.requireNonNull(url)));
            toggleGroups.add(new ToggleGroup());
            addNewTravelersContainer.getChildren().get(addNewTravelersContainer.getChildren().size()-1).lookupAll("RadioButton")
                    .forEach(it->{
                        ((RadioButton)it).setToggleGroup(toggleGroups.get(toggleGroups.size()-1));
                    });
        } else {
            new Alert(Alert.AlertType.WARNING, "Cant add more than 4 relatives", ButtonType.OK).show();
        }

    }
}
