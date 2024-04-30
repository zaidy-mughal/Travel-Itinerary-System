import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

//java fx imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;




public class Main extends Application {

    ArrayList<SignUp> signUpList = new ArrayList<>();
    public static String fileFormat = ".txt";

    static SignUp signUpdata;
    static Traveler travelerData;

    public static void readFromFile() {

        try (Scanner loginScanner = new Scanner(Paths.get("signUpInfo.txt"))) {
            while (loginScanner.hasNext()) {
                String name = loginScanner.next();
                String emaily = loginScanner.next();
                String passy = loginScanner.next();

                if (emailField.getText().equals(emaily) && passwordField.getText().equals(passy)) {

                    signUpdata = new SignUp(name, emaily, passy, passy);

                    try (ObjectInputStream ois = new ObjectInputStream(
                            new FileInputStream(emailField.getText() + fileFormat))) {

                        travelerData = (Traveler) ois.readObject();

                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(emailField.getText() + fileFormat))) {
            oos.writeObject(travelerData);

        } catch (Exception e) {

        }
    }

    Stage stage = new Stage();
    static TextField emailField;
    static PasswordField passwordField;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image image = new Image("Images/LOGO.png");
        stage.getIcons().add(image);

        Label labelLogin = new Label("     LOGIN");
        labelLogin.setStyle("-fx-font-weight: bold;");

        labelLogin.setFont(new Font(25));
        Label labelEmail = new Label("EMAIL :  ");
        labelEmail.setStyle("-fx-font-weight: bold;");

        emailField = new TextField();
        emailField.setPromptText("abc123@email.com");

        Label labelPass = new Label("PASS :   ");
        labelPass.setStyle("-fx-font-weight: bold;");
        passwordField = new PasswordField();
        passwordField.setPromptText("*************");
        Button loginButton = new Button("Login");
        Label registerLabel = new Label("Didn't Have an account? ");
        Button signUpButton = new Button("Signup");
        Label labelError = new Label();

        // GRID PANE
        GridPane loginLayout = new GridPane();
        // layout.setPadding(new Insets(100,125,125,100));
        loginLayout.add(labelLogin, 5, 1);
        loginLayout.add(labelEmail, 4, 3);
        loginLayout.add(emailField, 5, 3);
        loginLayout.add(labelPass, 4, 4);
        loginLayout.add(passwordField, 5, 4);
        loginLayout.add(loginButton, 5, 5);
        loginLayout.add(registerLabel, 5, 6);
        loginLayout.add(signUpButton, 5, 7);
        loginLayout.add(labelError, 5, 8);
        loginLayout.setHgap(15);
        loginLayout.setVgap(15);
        loginLayout.setMaxWidth(500);

        // PHOTO NODE
        Image loginImage = new Image("Images/Login.png");
        ImageView imageView = new ImageView(loginImage);

        BorderPane borderPaneLogin = new BorderPane();
        borderPaneLogin.setLeft(loginLayout);
        borderPaneLogin.setRight(imageView);
        borderPaneLogin.setPadding(new Insets(130, 300, 250, 200));

        Scene loginScene = new Scene(borderPaneLogin, 1000, 600);

        loginButton.setOnAction(e -> {
            Login login = new Login(emailField.getText(), passwordField.getText());
            if (login.checkInput(labelError)) {
                // readFromFile();
                stage.setScene(menuScene(loginScene));
            } else {
                labelError.setText("Incorrect Email or Pass.\nPlease SignUp");
            }
        });

        signUpButton.setOnAction(e -> stage.setScene(signUpScene(loginScene)));


        stage.setTitle("TRAVEL ITINERARY");
        stage.setScene(loginScene);
        stage.setResizable(false);

        stage.show();
    }

    TextField nameField, emailField2;
    PasswordField passwordField2, rePassField;

    // Signup Scene
    public Scene signUpScene(Scene scene) {

        Label labelSignUp = new Label("  SIGN UP");
        labelSignUp.setStyle("-fx-font-weight: bold;");
        labelSignUp.setFont(new Font(25));
        Label labelName = new Label("NAME:  ");
        labelName.setStyle("-fx-font-weight: bold;");
        nameField = new TextField();
        nameField.setPromptText("XYZ");
        Label labelEmail = new Label("EMAIL: ");
        labelEmail.setStyle("-fx-font-weight: bold;");

        emailField2 = new TextField();
        emailField2.setPromptText("abc123@email.com");
        Label labelPass = new Label("PASSWORD:    ");
        labelPass.setStyle("-fx-font-weight: bold;");

        passwordField2 = new PasswordField();
        passwordField2.setPromptText("*************");
        Label labelRePass = new Label("RETYPE PASS: ");
        labelRePass.setStyle("-fx-font-weight: bold;");

        rePassField = new PasswordField();
        rePassField.setPromptText("*************");

        Button submitBotton = new Button("Submit");
        submitBotton.setFont(new Font(18));
        Label labelsubmitted = new Label();
        labelsubmitted.setFont(new Font(15));
        Button goToLoginButton = new Button("Go to Login");
        goToLoginButton.setFont(new Font(18));

        // GRID PANE
        GridPane signUpLayout = new GridPane();
        // layout.setPadding(new Insets(100,125,125,100));
        signUpLayout.add(labelSignUp, 5, 1);
        signUpLayout.add(labelName, 4, 3);
        signUpLayout.add(nameField, 5, 3);
        signUpLayout.add(labelEmail, 4, 4);
        signUpLayout.add(emailField2, 5, 4);
        signUpLayout.add(labelPass, 4, 5);
        signUpLayout.add(passwordField2, 5, 5);
        signUpLayout.add(labelRePass, 4, 6);
        signUpLayout.add(rePassField, 5, 6);

        signUpLayout.add(submitBotton, 5, 7);
        signUpLayout.add(labelsubmitted, 5, 8);
        signUpLayout.add(goToLoginButton, 5, 9);
        signUpLayout.setHgap(15);
        signUpLayout.setVgap(15);
        signUpLayout.setMaxWidth(500);

        // PHOTO NODE
        Image signUpImage = new Image("Images/signup.png");
        ImageView imageView = new ImageView(signUpImage);

        StackPane imageStackPane = new StackPane(imageView);
        // imageStackPane.setAlignment(Pos.TOP_CENTER);
        imageStackPane.setOpaqueInsets(new Insets(50, 10, 10, 10));

        BorderPane borderPaneSignUp = new BorderPane();
        // borderPaneSignUp.setLeft(imageView);
        borderPaneSignUp.setLeft(imageStackPane);
        borderPaneSignUp.setRight(signUpLayout);
        borderPaneSignUp.setPadding(new Insets(125, 250, 100, 250));
        // splitPaneLogin.getItems().addAll(layout,imageStackPane);

        submitBotton.setOnAction(e -> {
            try {
                if (!nameField.getText().isEmpty() && !emailField2.getText().isEmpty()
                        && !passwordField2.getText().isEmpty() && !rePassField.getText().isEmpty()) {

                    SignUp signUp = new SignUp(nameField.getText(), emailField2.getText(), passwordField2.getText(),
                            rePassField.getText());
                    if (signUp.checkSamePassword()) {
                        labelsubmitted.setText("Successfully Submitted!\nGo to Login");
                    } else
                        labelsubmitted.setText("Passwords  Mismatch!");

                    try (Formatter signUpFormatter = new Formatter(new FileWriter("signUpInfo.txt", true))) {

                        signUpFormatter.format("%s %s %s\n", nameField.getText(), emailField2.getText(),
                                passwordField2.getText());
                        File createFile = new File((emailField2.getText() + fileFormat));
                        createFile.createNewFile();

                    } catch (IOException | NullPointerException ex) {
                        labelsubmitted.setText(ex.getMessage());
                    }
                } else
                    throw new NullPointerException("Please fill all fields");
            } catch (NullPointerException ex) {
                labelsubmitted.setText(ex.getMessage());
            }
        });

        Scene signUpScene = new Scene(borderPaneSignUp, 1000, 600);
        goToLoginButton.setOnAction(e -> stage.setScene(scene));

        return signUpScene;
    }

    Button myTrips, bookTrip, touristHotspots, excursions, flight, hotels, reviewDetails, about, logoutButton;

    public StackPane centerPane = new StackPane();

    public Scene menuScene(Scene scene) {

        myTrips = new Button("My Trips");
        myTrips.setFont(new Font(20));
        //// Exeption on retrieving some data...
        bookTrip = new Button("Book Trip");
        bookTrip.setFont(new Font(20));
        touristHotspots = new Button("Tourist Hotspots");
        touristHotspots.setFont(new Font(20));
        
        excursions = new Button("Excursions");
        excursions.setFont(new Font(20));
        flight = new Button("Book Flight");
        flight.setFont(new Font(20));
        hotels = new Button("Book Hotel");
        hotels.setFont(new Font(20));

        reviewDetails = new Button("Review Details");
        reviewDetails.setFont(new Font(20));

        about = new Button("About");
        about.setFont(new Font(20));
        Button logout = new Button("LOGOUT");
        logout.setFont(new Font(20));

        VBox layout1 = new VBox();
        layout1.setSpacing(6);
        layout1.setMinSize(150, 600);
        layout1.getChildren().addAll(myTrips, bookTrip, touristHotspots, excursions, flight, hotels, reviewDetails,
                about, logout);

        for (Node n : layout1.getChildren()) {
            Button b = (Button) n;
            b.setPrefSize(210, 25);
        }

        layout1.setBackground(new Background(new BackgroundImage(new Image("icons/sidePhoto.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));

        HBox top = new HBox();
        top.setMinSize(850, 100);
        // Load the image

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(layout1);
        borderPane.setTop(top);
        top.setBackground(new Background(new BackgroundImage(new Image("icons/upperPhoto.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));

        centerPane.setBackground(new Background(new BackgroundImage(new Image("icons/splash.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
        borderPane.setCenter(centerPane);

        touristHotspots.setOnAction(e -> cities(borderPane));

        excursions.setOnAction(e -> excursion(borderPane));

        flight.setOnAction(e -> flightScene(borderPane));

        hotels.setOnAction(e -> hotels(borderPane));

        reviewDetails.setOnAction(e -> personal(borderPane));

        myTrips.setOnAction(e -> {
            readFromFile();
            myTrips(borderPane);
        });
        bookTrip.setOnAction(e -> {
            readFromFile();
            bookTrip(borderPane);
        });
        about.setOnAction(e -> about(borderPane));

        logout.setOnAction(e -> {
            travelerData = null;
            try {
                start(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        stage.setResizable(false);
        Scene menuScene = new Scene(borderPane, 1000, 600);

        return menuScene;
    }

    public void myTrips(BorderPane viewInfoBorderPane) {

        if (travelerData != null) {
            Label personInfo = new Label("   YOUR PERSONAL INFORMATION");
            personInfo.setStyle("-fx-font-weight: bold;");
            personInfo.setFont(new Font(40));
            personInfo.setTextFill(Color.DARKGOLDENROD);

            HBox view = new HBox(10, personInfo);
            Label nameLabel = createLabel("Name:\t\t");
            nameLabel.setStyle("-fx-font-weight: bold;");
            nameLabel.setTextFill(Color.DARKBLUE);
            Label nameField = createLabel(travelerData.getName());

            Label contactLabel = createLabel("Contact:\t\t");
            contactLabel.setStyle("-fx-font-weight: bold;");
            contactLabel.setTextFill(Color.DARKBLUE);
            Label contactField = createLabel(travelerData.getContactInformation());

            Label nationalityLabel = createLabel("   Nationality:\t ");
            nationalityLabel.setStyle("-fx-font-weight: bold;");
            nationalityLabel.setTextFill(Color.DARKBLUE);
            Label nationalityField = createLabel(travelerData.getNationality());

            Label cnicLabel = createLabel("   CNIC:\t ");
            cnicLabel.setStyle("-fx-font-weight: bold;");
            cnicLabel.setTextFill(Color.DARKBLUE);
            Label cnicField = createLabel(travelerData.getCNIC());

            Label passportLabel = createLabel("Passport:\t ");
            passportLabel.setStyle("-fx-font-weight: bold;");
            passportLabel.setTextFill(Color.DARKBLUE);
            Label passportField = createLabel(travelerData.getPassportNumber());

            Label addressLabel = createLabel("Address:\t\t");
            addressLabel.setStyle("-fx-font-weight: bold;");
            addressLabel.setTextFill(Color.DARKBLUE);
            Label addressField = createLabel(travelerData.getLocation().getLocationStreetNumber() + "  "
                    + travelerData.getLocation().getLocationCity() + "   ");
            Label addresscountry = createLabel(""+travelerData.getLocation().getLocationCountry());


            GridPane personGridPane = new GridPane();
            personGridPane.add(nameLabel,1,1);
            personGridPane.add(nameField,2,1);
            personGridPane.add(cnicLabel,3,1);
            personGridPane.add(cnicField,4,1);

            personGridPane.add(contactLabel,1,2);
            personGridPane.add(contactField,2,2);
            personGridPane.add(nationalityLabel,3,2);
            personGridPane.add(nationalityField,4,2);

            personGridPane.add(passportLabel,1,3);
            personGridPane.add(passportField,2,3);
            personGridPane.add(addressLabel,1,4);
            personGridPane.add(addressField,2,4);
            personGridPane.add(addresscountry,3,4);

            personGridPane.setHgap(15);
            personGridPane.setVgap(15);

            // personal VBox
            VBox personVBox = new VBox(2, view, personGridPane);



            Label viewInfo = new Label("\t  YOUR TRIP INFORMATION");
            viewInfo.setFont(new Font(40));
            viewInfo.setStyle("-fx-font-weight: bold;");
            viewInfo.setTextFill(Color.DARKGOLDENROD);

            HBox em1 = new HBox(10, new Label("          "));
            HBox em2 = new HBox(10, new Label("          "));
            HBox em3 = new HBox(10, new Label("          \n\n\n\n\n\n"));
            
            VBox mainVBox = new VBox(20,personVBox,viewInfo);

            for (int i = 0; i < travelerData.getTrip().size(); i++) {
                Label tripNumberLabel = new Label("Trip " + (i + 1) + " Details");
                tripNumberLabel.setStyle("-fx-font-weight: bold;");
                tripNumberLabel.setTextFill(Color.DARKBLUE);
                tripNumberLabel.setFont(new Font(30));

                Label startDateLabel = createLabel("Start Date:\t\t\t");
                
                startDateLabel.setTextFill(Color.DARKBLUE);
                Label startDateField = createLabel(travelerData.getTrip().get(i).getStartDate() + " ");

                Label endDateLabel = createLabel("End Date:\t\t\t");
                endDateLabel.setTextFill(Color.DARKBLUE);
                Label endDateField = createLabel(travelerData.getTrip().get(i).getEndDate() + " ");

                Label cityLabel = createLabel("City of Trip:\t\t\t");
                cityLabel.setTextFill(Color.DARKBLUE);
                Label cityField = createLabel(travelerData.getTrip().get(i).getLocationOfTrip().getLocationCity());

                Label countryLabel = createLabel("Country of Trip:\t\t ");
                countryLabel.setTextFill(Color.DARKBLUE);

                Label countryField = createLabel(
                        travelerData.getTrip().get(i).getLocationOfTrip().getLocationCountry());

                Label bookedFlightLabel = new Label("");
                bookedFlightLabel.setFont(new Font(25));
                Label bookedFlightField = createLabel("");

                Label bookedHotelLabel = new Label("");
                bookedHotelLabel.setFont(new Font(25));
                Label bookedHotelField = createLabel("");

                Label plannedExcursionsLabel = new Label("");
                plannedExcursionsLabel.setFont(new Font(25));
                Label plannedExcursionsField = createLabel("");

                for (int j = 0; j < travelerData.getTrip().get(i).getActivities().size(); j++) {
                    if (travelerData.getTrip().get(i).getActivities().get(j) instanceof FlightBooking) {
                        bookedFlightLabel.setText("Booked Flight");
                        bookedFlightLabel.setStyle("-fx-font-weight: bold;");
                        bookedFlightLabel.setTextFill(Color.DARKBLUE);
                        bookedFlightField.setText(travelerData.getTrip().get(i).getActivities().get(j).toString());
                    } else if (travelerData.getTrip().get(i).getActivities().get(j) instanceof HotelBooking) {
                        bookedHotelLabel.setText("Booked Hotel");
                        bookedHotelLabel.setStyle("-fx-font-weight: bold;");
                        bookedHotelLabel.setTextFill(Color.DARKBLUE);
                        bookedHotelField.setText(travelerData.getTrip().get(i).getActivities().get(j).toString());
                    } else {
                        plannedExcursionsLabel.setText("Planned Excursions");
                        plannedExcursionsLabel.setStyle("-fx-font-weight: bold;");
                        plannedExcursionsLabel.setTextFill(Color.DARKBLUE);
                        plannedExcursionsField.setText(travelerData.getTrip().get(i).getActivities().get(j).toString());
                    }
                }

                Label totalCostLabel = new Label("Total Cost of Trip");
                totalCostLabel.setStyle("-fx-font-weight: bold;");
                totalCostLabel.setTextFill(Color.DARKBLUE);
                totalCostLabel.setFont(new Font(25));

                Label totalCostField = new Label(Double.toString(travelerData.getTrip().get(i).totalPrice()));
                totalCostField.setFont(new Font(25));

                // Trip Details VBox
                HBox dateHBox = new HBox(10, startDateLabel, startDateField);
                HBox date2HBox = new HBox(10, endDateLabel, endDateField);
                HBox endLab = new HBox(10, cityLabel, cityField);
                HBox countryLab = new HBox(10, countryLabel, countryField, new Label("                  "));
                VBox vbox1 = new VBox(10, bookedFlightLabel, bookedFlightField, new Label("\n\n"));
                VBox vbox2 = new VBox(10, bookedHotelLabel, bookedHotelField, new Label("\n\n"));
                VBox vbox3 = new VBox(10, plannedExcursionsLabel, plannedExcursionsField, new Label("\n\n"));

                HBox totalLab = new HBox(10, totalCostLabel, totalCostField);

                

                // Main VBox containing Personal Details and Trips
                VBox tripVBox = new VBox(20, tripNumberLabel, dateHBox, date2HBox, endLab, countryLab, vbox1, vbox2,
                        vbox3, totalLab, em1, em2);
                tripVBox.setPadding(new Insets(20));
                mainVBox.getChildren().add(tripVBox);

            }

            VBox goToCityVBox = new VBox();
            
            Button goToCityButton = new Button("BOOK A NEW TRIP.");
            goToCityButton.setFont(new Font(30));
            goToCityVBox.getChildren().addAll(new Label("\n\n\n\n"), goToCityButton);
            goToCityVBox.setAlignment(Pos.BASELINE_CENTER);

            goToCityButton.setOnAction(e -> {
                bookTrip(viewInfoBorderPane);
            });

            VBox mainVBox2 = new VBox(mainVBox,goToCityVBox,em1, em2, em3);
            mainVBox.setPadding(new Insets(20));
            mainVBox2.setPadding(new Insets(20));

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);

            scrollPane.setContent(mainVBox2);
            viewInfoBorderPane.setCenter(scrollPane);


        } else {
            VBox goToCityVBox = new VBox();
            Label goToCityLabel = new Label("YOU DO NOT HAVE ANY BOOKED TRIP TO VIEW YOUR TRIP BOOK A NEW TRIP.");
            goToCityLabel.setFont(new Font(20));
            Button goToCityButton = new Button("BOOK A NEW TRIP.");
            goToCityButton.setFont(new Font(30));
            goToCityVBox.getChildren().addAll(new Label("\n\n\n\n"), goToCityLabel, goToCityButton);
            goToCityVBox.setAlignment(Pos.BASELINE_CENTER);
            viewInfoBorderPane.setCenter(goToCityVBox);

            goToCityButton.setOnAction(e -> {
                bookTrip(viewInfoBorderPane);
            });
        }

    }

    private Label createLabel(String labelText) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", 20));
        return label;
    }

    
    DatePicker startDatePicker, endDatePicker;
    TextField contactTextField, nationalityTextField, cnicTextField, passportTextField, addressTextField, cityTextField;
    Location personLocation;

    public void bookTrip(BorderPane bookBorderPane) {

        if (travelerData != null) {

            Label bookYourTripLabel1 = new Label("\t\t\tBOOK ANOTHER TRIP");
            bookYourTripLabel1.setStyle("-fx-font-weight: bold;");
            bookYourTripLabel1.setFont(new Font(35));

            Label startDateLabel1 = new Label("Start Date: ");
            startDateLabel1.setStyle("-fx-font-weight: bold;");
            startDateLabel1.setFont(new Font(15));
            startDatePicker = new DatePicker();
            Label endDateLabel1 = new Label("End Date:   ");
            endDateLabel1.setStyle("-fx-font-weight: bold;");

            endDateLabel1.setFont(new Font(15));
            endDatePicker = new DatePicker();
            Button submit1 = new Button("Submit");
            submit1.setFont(new Font(25));

            Label error = new Label();
            error.setFont(new Font(20));

            VBox vbox3 = new VBox(20,bookYourTripLabel1,new HBox(15,startDateLabel1,startDatePicker),new HBox(15,endDateLabel1,endDatePicker),submit1,error);
            HBox hbox3 = new HBox(new Label("\t\t\t"),vbox3);

            bookBorderPane.setCenter(hbox3);

            submit1.setOnAction(e->{
                if (endDatePicker.getValue().isAfter(startDatePicker.getValue())
                            && startDatePicker.getValue().isAfter(LocalDate.now())) {
                        cities(bookBorderPane);
                    } else {
                        error.setText("Your selected dates are Invalid!!!");
                    }
            });


        } else {
            ScrollPane bookScrollPane = new ScrollPane();

            Label bookYourTripLabel = new Label("\t\t\tBOOK YOUR TRIP");
            bookYourTripLabel.setStyle("-fx-font-weight: bold;");
            bookYourTripLabel.setFont(new Font(35));

            Label startDateLabel = new Label("Start Date: ");
            startDateLabel.setStyle("-fx-font-weight: bold;");
            startDateLabel.setFont(new Font(16));
            startDatePicker = new DatePicker();
            startDatePicker.setStyle("-fx-font-size: 16");
            Label endDateLabel = new Label("End Date:   ");
            endDateLabel.setStyle("-fx-font-weight: bold;");
            endDateLabel.setFont(new Font(16));
            endDatePicker = new DatePicker();
            endDatePicker.setStyle("-fx-font-size: 16");

            Label contactLabel = new Label("Contact No:       ");
            contactLabel.setStyle("-fx-font-weight: bold;");

            contactLabel.setFont(new Font(16));
            contactTextField = new TextField();
            contactTextField.setFont(new Font(16));

            Label nationalityLabel = new Label("Nationality:         ");
            nationalityLabel.setStyle("-fx-font-weight: bold;");

            nationalityLabel.setFont(new Font(16));
            nationalityTextField = new TextField();
            nationalityTextField.setFont(new Font(16));

            Label cnicLabel = new Label("CNIC:         ");
            cnicLabel.setStyle("-fx-font-weight: bold;");

            cnicLabel.setFont(new Font(16));
            cnicTextField = new TextField();
            cnicTextField.setFont(new Font(16));

            Label passportLabel = new Label("Passport No:      ");
            passportLabel.setStyle("-fx-font-weight: bold;");

            passportLabel.setFont(new Font(16));
            passportTextField = new TextField();
            passportTextField.setFont(new Font(16));

            Label addressLabel = new Label("Address:          ");
            addressLabel.setStyle("-fx-font-weight: bold;");

            addressLabel.setFont(new Font(16));
            addressTextField = new TextField();
            addressTextField.setPromptText("Street Number");
            cityTextField = new TextField();
            cityTextField.setPromptText("City");
            cityTextField.setFont(new Font(16));
            addressTextField.setFont(new Font(16));

            Label errorLabel = new Label();
            errorLabel.setStyle("-fx-font-weight: bold;");

            errorLabel.setFont(new Font(15));

            Button submit = new Button("Submit");
            //submit.setStyle("-fx-font-weight: bold;");
            submit.setFont(new Font(26));

            GridPane bookGridPane = new GridPane();
            bookGridPane.add(startDateLabel, 1, 1);
            bookGridPane.add(startDatePicker, 2, 1);
            bookGridPane.add(endDateLabel, 3, 1);
            bookGridPane.add(endDatePicker, 4, 1);

            bookGridPane.add(cnicLabel, 1, 2);
            bookGridPane.add(cnicTextField, 2, 2);
            bookGridPane.add(contactLabel, 3, 2);
            bookGridPane.add(contactTextField, 4, 2);

            bookGridPane.add(nationalityLabel, 1, 3);
            bookGridPane.add(nationalityTextField, 2, 3);
            bookGridPane.add(passportLabel, 3, 3);
            bookGridPane.add(passportTextField, 4, 3);

            bookGridPane.add(addressLabel, 1, 4);
            bookGridPane.add(addressTextField, 2, 4);
            bookGridPane.add(cityTextField, 2, 5);

            bookGridPane.setHgap(15);
            bookGridPane.setVgap(20);

            VBox vbox1 = new VBox(20, bookYourTripLabel, bookGridPane,new HBox(new Label("\t\t\t\t\t\t"),submit),
                    new HBox(new Label("\t\t\t"), errorLabel));

            submit.setOnAction(e -> {

                if (!contactTextField.getText().isEmpty() && !nationalityTextField.getText().isEmpty()
                        && !cnicTextField.getText().isEmpty() && !passportTextField.getText().isEmpty()
                        && !addressTextField.getText().isEmpty() && !cityTextField.getText().isEmpty()) {

                    personLocation = new Location(addressTextField.getText(), cityTextField.getText(), "Pakistan");

                    if (endDatePicker.getValue().isAfter(startDatePicker.getValue())
                            && startDatePicker.getValue().isAfter(LocalDate.now())) {
                        cities(bookBorderPane);
                    } else {
                        errorLabel.setText("Your selected dates are Invalid!!!");
                    }

                } else {
                    errorLabel.setText("Please fill all the fields!!!");
                }

            });

            bookScrollPane.setContent(vbox1);
            bookBorderPane.setCenter(bookScrollPane);

        }

    }

    String tripCity, tripCountry;

    public void cities(BorderPane borderPane) {
        ScrollPane cities = new ScrollPane();
        Label cityTag = new Label("    WORLD'S FAMOUS TRAVELLING SPOTS\n\n");
        cityTag.setStyle("-fx-font-weight: bold;");
        cityTag.setTextFill(Color.DARKBLUE);
        cityTag.setFont(new Font(37));

        Button cb1 = new Button("Dubai");
        Button cb2 = new Button("New York");
        Button cb3 = new Button("London");
        Button cb4 = new Button("Paris");
        Button cb5 = new Button("Beijing");

        ImageView c1 = new ImageView(new Image("icons/dubai.jpeg"));
        c1.setFitHeight(350);
        c1.setFitWidth(350);
        ImageView c2 = new ImageView(new Image("icons/new-york-statue-of-liberty.jpg"));
        c2.setFitHeight(350);
        c2.setFitWidth(350);
        ImageView c3 = new ImageView(new Image("icons/download.jpeg"));
        c3.setFitHeight(350);
        c3.setFitWidth(350);
        ImageView c4 = new ImageView(new Image("icons/0x0.jpg"));
        c4.setFitHeight(350);
        c4.setFitWidth(350);
        ImageView c5 = new ImageView(new Image("icons/download (1).jpeg"));
        c5.setFitHeight(350);
        c5.setFitWidth(350);

        HBox city = new HBox(10); // Set the spacing between nodes in the HBox
        HBox cityTagHBox = new HBox(cityTag);

        VBox column1 = new VBox(10); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(c1, cb1, c3, cb3, c5, cb5, new Label(" \n\n\n\n\n\n "));
        for (Node n : column1.getChildren()) {
            if (n instanceof Button) {
                Button b = (Button) n;
                b.setPrefSize(350, 45);
                b.setFont(new Font(30));
            }
        }

        VBox column2 = new VBox(10);
        column2.getChildren().addAll(c2, cb2, c4, cb4);
        for (Node n : column2.getChildren()) {
            if (n instanceof Button) {
                Button b = (Button) n;
                b.setPrefSize(350, 45);
                b.setFont(new Font(30));
            }
        }

        city.getChildren().addAll(column1, column2);

        VBox cityTagVBox = new VBox(cityTagHBox, city);

        cities.setContent(cityTagVBox);

        borderPane.setCenter(cities);

        cb1.setOnAction(e -> {
            tripCity = "Dubai";
            tripCountry = "Kingdom of Saudi Arabia";
            excursion(borderPane);
        });

        cb2.setOnAction(e -> {
            tripCity = "NewYork";
            tripCountry = "United States";
            excursion(borderPane);
        });

        cb3.setOnAction(e -> {
            tripCity = "London";
            tripCountry = "United Kingdom";
            excursion(borderPane);
        });
        cb4.setOnAction(e -> {
            tripCity = "Paris";
            tripCountry = "France";
            excursion(borderPane);
        });
        cb5.setOnAction(e -> {
            tripCity = "Beijing";
            tripCountry = "China";
            excursion(borderPane);
        });
    }

    public void excursion(BorderPane borderPane) {
        if (tripCity == null) {
            VBox goToCityVBox = new VBox();
            Label goToCityLabel = new Label("First Select Your Trip City to view Excursions.");
            goToCityLabel.setFont(new Font(30));
            Button goToCityButton = new Button("SELECT CITY.");
            goToCityButton.setFont(new Font(30));
            goToCityVBox.getChildren().addAll(new Label("\n\n\n\n"), goToCityLabel, goToCityButton);
            goToCityVBox.setAlignment(Pos.BASELINE_CENTER);
            borderPane.setCenter(goToCityVBox);

            goToCityButton.setOnAction(e -> {
                cities(borderPane);
            });
        } else {
            if (tripCity.equals("Dubai"))
                city1(borderPane);
            else if (tripCity.equals("NewYork"))
                city2(borderPane);
            else if (tripCity.equals("London"))
                city3(borderPane);
            else if (tripCity.equals("Paris"))
                city4(borderPane);
            else if (tripCity.equals("Beijing"))
                city5(borderPane);
        }

    }

    ArrayList<Excursion> excursionsList = new ArrayList<>();
    int count;

    String excursionName;
    String excursionDescription;
    double excursionPrice;

    CheckBox checkBox1 = new CheckBox("Add");
    CheckBox checkBox2 = new CheckBox("Add");
    CheckBox checkBox3 = new CheckBox("Add");
    CheckBox checkBox4 = new CheckBox("Add");
    CheckBox checkBox5 = new CheckBox("Add");

    Label emptyLabel = new Label("\t\t\t\t\t\t\t\t\t");
    Label excursionLabel;

    public void city1(BorderPane borderPane) {

        ScrollPane dubai = new ScrollPane();

        checkBox1.setStyle("-fx-font-size: 25px;");
        checkBox2.setStyle("-fx-font-size: 25px;");
        checkBox3.setStyle("-fx-font-size: 25px;");
        checkBox4.setStyle("-fx-font-size: 25px;");
        checkBox5.setStyle("-fx-font-size: 25px;");

        excursionLabel = new Label("\t\t Excursions in " + tripCity + "\n");
        excursionLabel.setFont(new Font(35));
        excursionLabel.setStyle("-fx-font-weigth: bold;");

        Button cb = new Button("Book Selected Excursions");
        cb.setFont(new Font(20));

        Label name1 = new Label("Future Museam");
        name1.setFont(new Font(25));
        Label cb1 = new Label(
                "Museum of the Future One of Dubai's most famous \nlandmarks, the Museum of the Future (MOTF)\n takes pride of place along the city's\n superhighway, Sheikh Zayed Road. Founded by\n the Dubai Future Foundation and launched on \n22 February 2022, the museum explores how \nsociety could evolve in the coming decades using science and\n technology.\n \n(PRICE   =   13,583.72 RUPEES)\n");
        cb1.setFont(new Font(11));
        VBox vBox1 = new VBox(name1, cb1, checkBox1);

        Label name2 = new Label("Dubai Balloon");
        name2.setFont(new Font(25));
        Label cb2 = new Label(
                "The Dubai Balloon Get unbeatable panoramas\n of Dubai's coastal sights and glittering skyline\n from The Dubai Balloon, a tethered helium balloon which\n takes guests to heights of up to 300 metres\n Located at Aquaventure Waterpark, on Palm Jumeirah\n's outer crescent, The Dubai Balloon is\n the only experience of its kind in the city\n.During your 10-minute trip into the sky\n, you’ll get the best possible view of the Palm\n, and be able to see the full extent of this \nman-made marvel. On a clear day, you can\n even see as far as the Burj Khalifa and Downtown\n Dubai.\n \n(PRICE   =   15339.92 RUPEES)\n");
        cb2.setFont(new Font(11));
        VBox vBox2 = new VBox(name2, cb2, checkBox2);

        Label name3 = new Label("Sky Views");
        name3.setFont(new Font(25));
        Label cb3 = new Label(
                "Sky Views Dubai At 219.5m above ground\n, Sky Views Dubai is the latest attraction \nto sweep visitors off their feet with mesmerising\n perspectives of Downtown Dubai's pristine expanses\n below and the sublime city skyline beyond.\nPerched atop the Address Sky View hotel\n, this architectural marvel comprises three distinct \nofferings for unforgettable and thrill-inducing sightseeing\n escapades – the Observatory, Glass Slide and Edge Walk.\n \n(PRICE   =   19121.12 RUPEES)\n");
        cb3.setFont(new Font(11));
        VBox vBox3 = new VBox(name3, cb3, checkBox3);

        Label name4 = new Label("Deep Dive");
        name4.setFont(new Font(25));
        Label cb4 = new Label(
                "Deep Dive Dubai. A thrilling addition to \nthe city's thriving sports and adventure activities\n scene, Deep Dive Dubai is the place to be\n for all aqua enthusiasts. Opened in July\n 2021, the recreational centre's 60.02m-deep\n pool is certified by Guinness World Records as the\n world's deepest swimming pool for diving.\nAside from its remarkable depth, the pool features\n other unrivalled underwater additions, such as a \n'sunken city'; with its abandoned urban streetscape,\n divers can explore this modern Atlantis-like immersive\n zone and discover intricate details.\n \n(PRICE   =   6097.01 RUPEES)\n");
        cb4.setFont(new Font(11));
        VBox vBox4 = new VBox(name4, cb4, checkBox4);

        Label name5 = new Label("Palm Jumaira");
        name5.setFont(new Font(25));
        Label cb5 = new Label(
                "The View at The Palm Enjoy incomparable views\n of one of Dubai's most talked about landmarks\n at The View at The Palm. Located 240m\n high, the observation deck presents never seen before\n 360-degree panoramas of Palm Jumeirah alongside the sparkling\n waters of the Arabian Gulf and Dubai skyline in\n the distance. The View at The Palm is located on\n level 52 of The Palm Tower, which also\n houses a café, a creative exhibition that showcases\n the development of the island, interactive aquarium-themed tunnels\n and a gift shop.\n \n(PRICE   =   7762.13 RUPEES)\n");
        cb5.setFont(new Font(11));
        VBox vBox5 = new VBox(name5, cb5, checkBox5);

        ImageView c1 = new ImageView(new Image("icons/museum.jpeg"));
        c1.setFitHeight(350);
        c1.setFitWidth(350);
        ImageView c2 = new ImageView(new Image("icons/balloon.jpeg"));
        c2.setFitHeight(350);
        c2.setFitWidth(350);
        ImageView c3 = new ImageView(new Image("icons/skyview.jpeg"));
        c3.setFitHeight(350);
        c3.setFitWidth(350);
        ImageView c4 = new ImageView(new Image("icons/deep.jpeg"));
        c4.setFitHeight(350);
        c4.setFitWidth(350);
        ImageView c5 = new ImageView(new Image("icons/palm.jpeg"));
        c5.setFitHeight(350);
        c5.setFitWidth(350);

        HBox ch1 = new HBox(35); // Set the spacing between nodes in the HBox
        ch1.getChildren().addAll(c1, vBox1);
        HBox ch2 = new HBox(35); // Set the spacing between nodes in the HBox
        ch2.getChildren().addAll(vBox2, c2);

        HBox ch3 = new HBox(35); // Set the spacing between nodes in the HBox
        ch3.getChildren().addAll(c3, vBox3);
        HBox ch4 = new HBox(35); // Set the spacing between nodes in the HBox
        ch4.getChildren().addAll(vBox4, c4);
        HBox ch5 = new HBox(35); // Set the spacing between nodes in the HBox
        ch5.getChildren().addAll(c5, vBox5);
        HBox ch6 = new HBox(35);
        ch6.getChildren().addAll(emptyLabel, cb);

        VBox column1 = new VBox(10); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(excursionLabel, ch1, ch2, ch3, ch4, ch5, ch6, new Label(" \n\n\n\n\n\n "));

        dubai.setContent(column1);

        borderPane.setCenter(dubai);

        cb.setOnAction(e -> {
            if (checkBox1.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name1.getText(), cb1.getText(), 13583.72));
                count++;
            }
            if (checkBox2.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name2.getText(), cb2.getText(), 15339.92));
                count++;
            }
            if (checkBox3.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name3.getText(), cb3.getText(), 19121.12));
                count++;
            }
            if (checkBox4.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name4.getText(), cb4.getText(), 6097.01));
                count++;
            }
            if (checkBox5.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name5.getText(), cb5.getText(), 7762.13));
                count++;
            }
            flightScene(borderPane);
        });

    }

    public void city2(BorderPane borderPane) {

        ScrollPane newYork = new ScrollPane();

        checkBox1.setStyle("-fx-font-size: 25px;");
        checkBox2.setStyle("-fx-font-size: 25px;");
        checkBox3.setStyle("-fx-font-size: 25px;");
        checkBox4.setStyle("-fx-font-size: 25px;");
        checkBox5.setStyle("-fx-font-size: 25px;");

        excursionLabel = new Label("\t\t  Excursions in " + tripCity + "\n");
        excursionLabel.setFont(new Font(35));
        excursionLabel.setStyle("-fx-font-weigth: bold;");

        Button cb = new Button("Book Selected Excursions");
        cb.setFont(new Font(20));

        Label name1 = new Label("NewYork City Food");
        name1.setFont(new Font(25));
        Label cb1 = new Label(
                "NYC Food Tour: Chinatown and Little Italy. \nExplore two historic New York neighborhoods while \nsampling some of the city’s best culinary\n offerings on this food tour of \nChinatown and Little Italy. \nLed by a historian guide, learn all about \nthe development of the neighborhoods and hear\n stories about the people who lived there. \nThe tour focuses on family-owned businesses \nfor an authentic local experience.\n \n(PRICE   =   29957.20 RUPEES)");
        cb1.setFont(new Font(11));
        VBox vbox1 = new VBox(name1, cb1, checkBox1);

        Label name2 = new Label("Helicopter Tour");
        name2.setFont(new Font(25));
        Label cb2 = new Label(
                "Helicopter Tour: Manhattan. Get a \n perspective on New York City that most \nvisitorsmiss when you take to the air\nfor a scenic helicopter tour. See\nManhattan’s most iconic sights,\nfrom the Empire State Building \nto Central Park, in a matter  \nof minutes you even catch a glimpse\n of the majestic\nStatue of Liberty.\n \n(PRICE   =   22139.59 RUPEES)");
        cb2.setFont(new Font(11));
        VBox vbox2 = new VBox(name2, cb2, checkBox2);

        Label name3 = new Label("Washington DC Tour");
        name3.setFont(new Font(25));
        Label cb3 = new Label(
                "Washington DC Day Tour from New York\nCity Visit Washington DC from  \nNew York City on a full-day \ntrip exploring iconic landmarks and memorials \nincluding the White House and Arlington National\nCemetery. With included transport to DC and a  \nguided tour, just sit back and enjoy \nthe ride as your guide brings each destination\nto life. Book early; this tour often \nsells out.\n \n(PRICE   =   35339.92 RUPEES)");
        cb3.setFont(new Font(11));
        VBox vbox3 = new VBox(name3, cb3, checkBox3);

        Label name4 = new Label("Manhattan Architecture");
        name4.setFont(new Font(25));
        Label cb4 = new Label(
                "Manhattan Architecture Yacht Cruise. \nOn this distinctive Manhattan architecture\ntour, you get a perspective on the\ncity that many travelers don’t:\nby boat, as you circle around the island\nof Manhattan. Hop aboard a climate-controlled\n 1920s-style yacht, enjoy a refreshing \nbeverage, and listen to your guide—a\ntrained architect—tell stories about the \ncity’s history, famous skyscrapers, and \nriverfront construction.\n \n(PRICE   =   31931.52 RUPEES)");
        cb4.setFont(new Font(11));
        VBox vbox4 = new VBox(name4, cb4, checkBox4);

        Label name5 = new Label("To Niagara Falls");
        name5.setFont(new Font(25));
        Label cb5 = new Label(
                "Niagara Falls in One Day from New York \nCity Experience one of the country’s most\nfamous sights with a day trip to Niagara\nFalls from Manhattan. See the waterfalls\nfrom many different vantage points and\n have some time to explore Niagara Falls\nState Park on your own. Get close enough\nto the falls to feel their mist,\nand have plenty of great photo ops. \nPlus, leave the worry of driving and\nnavigating to your guide.\n \n(PRICE   =   18131.03 RUPEES)");
        cb5.setFont(new Font(11));
        VBox vbox5 = new VBox(name5, cb5, checkBox5);

        ImageView c1 = new ImageView(new Image("icons/washin1.jpeg"));
        c1.setFitHeight(350);
        c1.setFitWidth(350);
        ImageView c2 = new ImageView(new Image("icons/wahin3.jpg"));
        c2.setFitHeight(350);
        c2.setFitWidth(350);
        ImageView c3 = new ImageView(new Image("icons/washin2.jpeg"));
        c3.setFitHeight(350);
        c3.setFitWidth(350);
        ImageView c4 = new ImageView(new Image("icons/washin4.jpeg"));
        c4.setFitHeight(350);
        c4.setFitWidth(350);
        ImageView c5 = new ImageView(new Image("icons/wa.jpeg"));
        c5.setFitHeight(350);
        c5.setFitWidth(350);

        HBox ch1 = new HBox(35); // Set the spacing between nodes in the HBox
        ch1.getChildren().addAll(c1, vbox1);
        HBox ch2 = new HBox(35); // Set the spacing between nodes in the HBox
        ch2.getChildren().addAll(vbox2, c2);

        HBox ch3 = new HBox(35); // Set the spacing between nodes in the HBox
        ch3.getChildren().addAll(c3, vbox3);
        HBox ch4 = new HBox(35); // Set the spacing between nodes in the HBox
        ch4.getChildren().addAll(vbox4, c4);
        HBox ch5 = new HBox(35); // Set the spacing between nodes in the HBox
        ch5.getChildren().addAll(c5, vbox5);
        HBox ch6 = new HBox(35);
        ch6.getChildren().addAll(emptyLabel, cb);

        VBox column1 = new VBox(10); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(excursionLabel, ch1, ch2, ch3, ch4, ch5, ch6, new Label(" \n\n\n\n\n\n "));

        newYork.setContent(column1);

        borderPane.setCenter(newYork);

        cb.setOnAction(e -> {
            if (checkBox1.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name1.getText(), cb1.getText(), 29957.20));
                count++;
            }
            if (checkBox2.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name2.getText(), cb2.getText(), 22139.59));
                count++;
            }
            if (checkBox3.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name3.getText(), cb3.getText(), 35339.92));
                count++;
            }
            if (checkBox4.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name4.getText(), cb4.getText(), 31931.52));
                count++;
            }
            if (checkBox5.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name5.getText(), cb5.getText(), 18131.03));
                count++;
            }
            flightScene(borderPane);
        });

    }

    public void city3(BorderPane borderPane) {

        ScrollPane london = new ScrollPane();

        checkBox1.setStyle("-fx-font-size: 25px;");
        checkBox2.setStyle("-fx-font-size: 25px;");
        checkBox3.setStyle("-fx-font-size: 25px;");
        checkBox4.setStyle("-fx-font-size: 25px;");
        checkBox5.setStyle("-fx-font-size: 25px;");

        excursionLabel = new Label("\t\t Excursions in " + tripCity + "\n");
        excursionLabel.setFont(new Font(35));
        excursionLabel.setStyle("-fx-font-weigth: bold;");

        Button cb = new Button("Book Selected Excursions");
        cb.setFont(new Font(20));

        Label name1 = new Label("Tower of London");
        name1.setFont(new Font(25));
        Label cb1 = new Label(
                "Tower of London Tickets - See The Crown\n Jewels Step into the grandeur of British history at\n the iconic Tower of London! Marvel at the architectural \nsplendor of the White Tower, home to the renowned Royal \nArmouries. Explore the legendary Traitors' Gate, \nwhere infamous prisoners made their dramatic entrance. See\n the enchanting ravens, guardians of the tower\n's destiny.\n \n(PRICE   =   15449.31 RUPEES)");
        cb1.setFont(new Font(11));
        VBox vbox1 = new VBox(name1, cb1, checkBox1);

        Label name2 = new Label("London Eye");
        name2.setFont(new Font(25));
        Label cb2 = new Label(
                "London Eye. London Eye is the world’s tallest\n cantilever wheel and an the iconic figure in\n the London sky. This wheel attracts nearly\n 4 million visitors every year. This staggering figure \nsuggests the popularity among the tourists and the\n locals.\n \n(PRICE   =   22346.62 RUPEES)");
        cb2.setFont(new Font(11));
        VBox vbox2 = new VBox(name2, cb2, checkBox2);

        Label name3 = new Label("Buckingham Palace");
        name3.setFont(new Font(25));
        Label cb3 = new Label(
                "Buckingham Palace One of the most iconic\n Royal buildings in the United Kingdom, \nBuckingham Palace is the royal residential hub of\n the Queen of England. Notably, it crowns itself as one\n of the very few working Royal Palaces existing across\n the world and it is one of the best places to\n visit in England. On the occasion of both national and\n royal celebrations, the Queen of England gives her\n public appearance through the Palace’s central gallery\n \n(PRICE   =   33739.48 RUPEES)");
        cb3.setFont(new Font(11));
        VBox vbox3 = new VBox(name3, cb3, checkBox3);

        Label name4 = new Label("Warner Bros Studio");
        name4.setFont(new Font(25));
        Label cb4 = new Label(
                "Warner Bros. Studio Tour With the \nWarner Bros. Studio Tour in the UK\n, you can see the Wizarding Wonders of\n the Potterverse. The Warner Bros Studio, located\n in Leavesden, is one of the country'\ns largest film studios.The studio's Making\n of Harry Potter tour has been its top-rated attraction\n worldwide since it initially launched in 2012\n. It is home to the world's only permanent filmmaking\n display of its magnitude.\n \n(PRICE   =   27486.65 RUPEES)");
        cb4.setFont(new Font(11));
        VBox vbox4 = new VBox(name4, cb4, checkBox4);

        Label name5 = new Label("Sea life Centre");
        name5.setFont(new Font(25));
        Label cb5 = new Label(
                "Sea life Centre Sea Life Centre London\n Aquarium, located on the ground floor of\n County Hall on the banks of the river Thames,\n is a family-friendly tourist attraction that\n receives about 1 million visitors every year. It\n is the safe haven of underwater sea creatures\n and other marine animals since they are preserved and\n nurtured in their natural habitat. The aquarium rose to popularity\n in 2005 when it displayed three robotic fish that could\n swim around and cross the obstacles like\n real fish\n \n(PRICE   =  19947.07 RUPEES)");
        cb5.setFont(new Font(11));
        VBox vbox5 = new VBox(name5, cb5, checkBox5);

        ImageView c1 = new ImageView(new Image("icons/tower.jpeg"));
        c1.setFitHeight(350);
        c1.setFitWidth(350);
        ImageView c2 = new ImageView(new Image("icons/eye.jpeg"));
        c2.setFitHeight(350);
        c2.setFitWidth(350);
        ImageView c3 = new ImageView(new Image("icons/palace.jpeg"));
        c3.setFitHeight(350);
        c3.setFitWidth(350);
        ImageView c4 = new ImageView(new Image("icons/warner.jpeg"));
        c4.setFitHeight(350);
        c4.setFitWidth(350);
        ImageView c5 = new ImageView(new Image("icons/sea.jpeg"));
        c5.setFitHeight(350);
        c5.setFitWidth(350);

        HBox ch1 = new HBox(35); // Set the spacing between nodes in the HBox
        ch1.getChildren().addAll(c1, vbox1);
        HBox ch2 = new HBox(35); // Set the spacing between nodes in the HBox
        ch2.getChildren().addAll(vbox2, c2);
        HBox ch3 = new HBox(35); // Set the spacing between nodes in the HBox
        ch3.getChildren().addAll(c3, vbox3);
        HBox ch4 = new HBox(35); // Set the spacing between nodes in the HBox
        ch4.getChildren().addAll(vbox4, c4);
        HBox ch5 = new HBox(35); // Set the spacing between nodes in the HBox
        ch5.getChildren().addAll(c5, vbox5);
        HBox ch6 = new HBox(35);
        ch6.getChildren().addAll(emptyLabel, cb);

        VBox column1 = new VBox(10); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(excursionLabel, ch1, ch2, ch3, ch4, ch5, ch6, new Label(" \n\n\n\n\n\n "));

        london.setContent(column1);
        borderPane.setCenter(london);

        cb.setOnAction(e -> {
            if (checkBox1.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name1.getText(), cb1.getText(), 15449.31));
                count++;
            }
            if (checkBox2.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name2.getText(), cb2.getText(), 22346.62));
                count++;
            }
            if (checkBox3.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name3.getText(), cb3.getText(), 33739.48));
                count++;
            }
            if (checkBox4.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name4.getText(), cb4.getText(), 27486.65));
                count++;
            }
            if (checkBox5.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name5.getText(), cb5.getText(), 19947.07));
                count++;
            }
            flightScene(borderPane);
        });

    }

    public void city4(BorderPane borderPane) {

        ScrollPane paris = new ScrollPane();

        checkBox1.setStyle("-fx-font-size: 25px;");
        checkBox2.setStyle("-fx-font-size: 25px;");
        checkBox3.setStyle("-fx-font-size: 25px;");
        checkBox4.setStyle("-fx-font-size: 25px;");
        checkBox5.setStyle("-fx-font-size: 25px;");

        excursionLabel = new Label("\t\t Excursions in " + tripCity + "\n");
        excursionLabel.setFont(new Font(35));
        excursionLabel.setStyle("-fx-font-weigth: bold;");

        Button cb = new Button("Book Selected Excursions");
        cb.setFont(new Font(20));

        Label name1 = new Label("The Eiffel Tower");
        name1.setFont(new Font(25));
        Label cb1 = new Label(
                "Eiffel Tower Visit the most symbolic monument\n in France, the legendary Eiffel Tower. In order\n to avoid the never-ending queues, we’re offering\n you queue jump tickets that allow quick access to the \n2nd and 3rd floors. Once inside, you\n can take your time making the most of the magnificent view\n over Paris. However, we advise that you book\n quickly as places are limited.\n \n(PRICE   =   15563.05 RUPEES)");
        cb1.setFont(new Font(11));
        VBox vbox1 = new VBox(name1, cb1, checkBox1);

        Label name2 = new Label("Louvre Museum");
        name2.setFont(new Font(25));
        Label cb2 = new Label(
                "Louvre is home to more than half a million\n works. At any one time 35,000 of them\n are on display. This magnificent museum is the most\n important symbol of the cultural influence of France through the\n centuries. That's why so many millions of\n people flock to The Louvre from around the world\n, making it the most visited art museum\n on earth.\n \n(PRICE   =   24291.44 RUPEES)");
        cb2.setFont(new Font(11));
        VBox vbox2 = new VBox(name2, cb2, checkBox2);

        Label name3 = new Label("Dinner Seine Cruise");
        name3.setFont(new Font(25));
        Label cb3 = new Label(
                "Dinner Seine Cruise in Paris. A dinner\n cruise on the Seine is the best way to\n enjoy a Parisian evening while staying in Paris. You\n'll see the city's most iconic landmarks illuminated\n at night, and enjoy a delicious meal\n aboard a top-notch restaurant boat. Plus\n, it's a great way to relax and unwind\n after spending a day wandering the bustling streets of\n the French capital. In short, a dinner cruise\n on the Seine is an absolute must for a\n tourist stay in Paris!\n \n(PRICE   =  26457.19 RUPEES)");
        cb3.setFont(new Font(11));
        VBox vbox3 = new VBox(name3, cb3, checkBox3);

        Label name4 = new Label("The Palace of Versailles");
        name4.setFont(new Font(25));
        Label cb4 = new Label(
                "The Palace of Versailles. If you're\n planning a trip to France, one of the must-\nsee attractions is the Château de Versailles, also known\n as the Palace of Versailles. This magnificent palace has a\n rich and fascinating history, and has been home to French\n monarchs such as Louis XIV, Louis XV\n, and Marie Antoinette.\n \n(PRICE   =   40069.59 RUPEES)");
        cb4.setFont(new Font(11));
        VBox vbox4 = new VBox(name4, cb4, checkBox4);

        Label name5 = new Label("Disneyland Paris");
        name5.setFont(new Font(25));
        Label cb5 = new Label(
                "Disneyland Paris - Daypass. Discover 2 \nDisney® Parks, enchanted by\n over 50 attractions, stunning shows, unforgettable Disney\n Character Encounters and a sparkly celebration*\n that’s been 30 years in the making\n. Eat, drink and dance the night or day\n away in the lively venues of Disney Village®. Tickle your\n taste buds at more than 50 restaurants across\n Disneyland® Paris and add a little more sparkle\n to your stay with our magical extras\n, smart services and top tips.\n \n(PRICE   =  18364.40 RUPEES)");
        cb5.setFont(new Font(11));
        VBox vbox5 = new VBox(name5, cb5, checkBox5);

        ImageView c1 = new ImageView(new Image("icons/eiffel.jpeg"));
        c1.setFitHeight(350);
        c1.setFitWidth(350);
        ImageView c2 = new ImageView(new Image("icons/louvre.jpeg"));
        c2.setFitHeight(350);
        c2.setFitWidth(350);
        ImageView c3 = new ImageView(new Image("icons/diner.jpeg"));
        c3.setFitHeight(350);
        c3.setFitWidth(350);
        ImageView c4 = new ImageView(new Image("icons/versailles.jpeg"));
        c4.setFitHeight(350);
        c4.setFitWidth(350);
        ImageView c5 = new ImageView(new Image("icons/disneyland.jpeg"));
        c5.setFitHeight(350);
        c5.setFitWidth(350);

        HBox ch1 = new HBox(35); // Set the spacing between nodes in the HBox
        ch1.getChildren().addAll(c1, vbox1);
        HBox ch2 = new HBox(35); // Set the spacing between nodes in the HBox
        ch2.getChildren().addAll(vbox2, c2);
        HBox ch3 = new HBox(35); // Set the spacing between nodes in the HBox
        ch3.getChildren().addAll(c3, vbox3);
        HBox ch4 = new HBox(35); // Set the spacing between nodes in the HBox
        ch4.getChildren().addAll(vbox4, c4);
        HBox ch5 = new HBox(35); // Set the spacing between nodes in the HBox
        ch5.getChildren().addAll(c5, vbox5);
        HBox ch6 = new HBox(35);
        ch6.getChildren().addAll(emptyLabel, cb);

        VBox column1 = new VBox(10); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(excursionLabel, ch1, ch2, ch3, ch4, ch5, ch6, new Label(" \n\n\n\n\n\n "));

        paris.setContent(column1);
        borderPane.setCenter(paris);

        cb.setOnAction(e -> {
            if (checkBox1.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name1.getText(), cb1.getText(), 15563.05));
                count++;
            }
            if (checkBox2.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name2.getText(), cb2.getText(), 24229.44));
                count++;
            }
            if (checkBox3.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name3.getText(), cb3.getText(), 26457.19));
                count++;
            }
            if (checkBox4.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name4.getText(), cb4.getText(), 40069.59));
                count++;
            }
            if (checkBox5.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name5.getText(), cb5.getText(), 18364.40));
                count++;
            }
            flightScene(borderPane);
        });

    }

    public void city5(BorderPane borderPane) {

        ScrollPane beijing = new ScrollPane();

        checkBox1.setStyle("-fx-font-size: 25px;");
        checkBox2.setStyle("-fx-font-size: 25px;");
        checkBox3.setStyle("-fx-font-size: 25px;");
        checkBox4.setStyle("-fx-font-size: 25px;");
        checkBox5.setStyle("-fx-font-size: 25px;");

        excursionLabel = new Label("\t\t Excursions in " + tripCity + "\n");
        excursionLabel.setFont(new Font(35));
        excursionLabel.setStyle("-fx-font-weigth: bold;");

        Button cb = new Button("Book Selected Excursions");
        cb.setFont(new Font(20));

        Label name1 = new Label("Great Wall of China");
        name1.setFont(new Font(25));
        Label cb1 = new Label(
                "Great Wall of China. The Great Wall\n begins in the east at Shanhaiguan \nin Hebei province and ends at Jiayuguan in Gansu province to the west. \nIts main body consists of walls, horse tracks, watch towers, and shelters \non the wall, and includes fortresses and passes along the Wall.\n \n(PRICE   =   11081.78 RUPEES)");
        cb1.setFont(new Font(11));
        VBox vbox1 = new VBox(name1, cb1, checkBox1);

        Label name2 = new Label("Tian'anmen Square");
        name2.setFont(new Font(25));
        Label cb2 = new Label(
                "Tiananmen Square or Tian'anmen Square (/ˈtjɛnənmən/[1]) \nis a city square in the city center of Beijing\n, China, named after the eponymous Tiananmen \n(\\\"Gate of Heavenly Peace\\\") located to its\n north, which separates it from the Forbidden City.\n The square contains the Monument to the People's\n Heroes, the Great Hall of the People, the\n National Museum of China, and the Mausoleum of\n Mao Zedong\n \n(PRICE   =   7950.52 RUPEES)");
        cb2.setFont(new Font(11));
        VBox vbox2 = new VBox(name2, cb2, checkBox2);

        Label name3 = new Label("The Summer Palace");
        name3.setFont(new Font(25));
        Label cb3 = new Label(
                "The Summer Palace is a vast ensemble of lakes\n, gardens and palaces in Beijing. It was an imperial\n garden in the Qing dynasty. Inside includes Longevity\n Hill, Kunming Lake and Seventeen Hole Bridge\n\n(PRICE   =  13457.19 RUPEES)");
        cb3.setFont(new Font(11));
        VBox vbox3 = new VBox(name3, cb3, checkBox3);

        Label name4 = new Label("The National Palace");
        name4.setFont(new Font(25));
        Label cb4 = new Label(
                "The National Palace Museum, with locations in\n Taipei and Taibao, houses the world's largest\n collection of priceless Chinese art treasures spanning China's\n nearly 5,000 year history from the Neolithic age to\n today.\n \n(PRICE   =   15381.55 RUPEES)");
        cb4.setFont(new Font(11));
        VBox vbox4 = new VBox(name4, cb4, checkBox4);

        Label name5 = new Label("The Ming and Qing Tombs");
        name5.setFont(new Font(25));
        Label cb5 = new Label(
                "The Ming and Qing Tombs are dazzling illustrations \nof the beliefs, world view, and geomantic\n theories of Fengshui prevalent in feudal China.\n They have served as burial edifices for illustrious personages\n and as the theatre for major events that\n have marked the history of China.\n \n(PRICE   =  6581.43 RUPEES)");
        cb5.setFont(new Font(11));
        VBox vbox5 = new VBox(name5, cb5, checkBox5);

        ImageView c1 = new ImageView(new Image("icons/greatwall.jpeg"));
        c1.setFitHeight(350);
        c1.setFitWidth(350);
        ImageView c2 = new ImageView(new Image("icons/square.jpeg"));
        c2.setFitHeight(350);
        c2.setFitWidth(350);
        ImageView c3 = new ImageView(new Image("icons/summer.jpeg"));
        c3.setFitHeight(350);
        c3.setFitWidth(350);
        ImageView c4 = new ImageView(new Image("icons/themuseum.jpeg"));
        c4.setFitHeight(350);
        c4.setFitWidth(350);
        ImageView c5 = new ImageView(new Image("icons/tombs.jpeg"));
        c5.setFitHeight(350);
        c5.setFitWidth(350);

        HBox ch1 = new HBox(35); // Set the spacing between nodes in the HBox
        ch1.getChildren().addAll(c1, vbox1);
        HBox ch2 = new HBox(35); 
        ch2.getChildren().addAll(vbox2, c2);
        HBox ch3 = new HBox(35); 
        ch3.getChildren().addAll(c3, vbox3);
        HBox ch4 = new HBox(35); 
        ch4.getChildren().addAll(vbox4, c4);
        HBox ch5 = new HBox(35); 
        ch5.getChildren().addAll(c5, vbox5);
        HBox ch6 = new HBox(35);
        ch6.getChildren().addAll(emptyLabel, cb);

        VBox column1 = new VBox(10); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(excursionLabel, ch1, ch2, ch3, ch4, ch5, ch6, new Label(" \n\n\n\n\n\n "));

        beijing.setContent(column1);
        borderPane.setCenter(beijing);

        cb.setOnAction(e -> {
            if (checkBox1.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name1.getText(), cb1.getText(), 11081.78));
                count++;
            }
            if (checkBox2.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name2.getText(), cb2.getText(), 7950.52));
                count++;
            }
            if (checkBox3.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name3.getText(), cb3.getText(), 13457.19));
                count++;
            }
            if (checkBox4.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name4.getText(), cb4.getText(), 15381.55));
                count++;
            }
            if (checkBox5.isSelected()) {
                excursionsList.add(
                        new Excursion(new Location(tripCity, tripCountry), name5.getText(), cb5.getText(), 6581.43));
                count++;
            }
            flightScene(borderPane);

        });

    }






    String deptAirport;

    Flights bookedFlight;

    public void flightScene(BorderPane flightBorderPane) {

        ScrollPane flights = new ScrollPane();
        if (tripCity == null) {
            VBox goToCityVBox = new VBox();
            Label goToCityLabel = new Label("First Select Your Trip City to view Flights.");
            goToCityLabel.setFont(new Font(30));
            Button goToCityButton = new Button("SELECT CITY.");
            goToCityButton.setFont(new Font(30));
            goToCityVBox.getChildren().addAll(new Label("\n\n\n\n"), goToCityLabel, goToCityButton);
            goToCityVBox.setAlignment(Pos.BASELINE_CENTER);
            flightBorderPane.setCenter(goToCityVBox);

            goToCityButton.setOnAction(e -> {
                cities(flightBorderPane);
            });
        } else {
            Label labelselectAirport = new Label(
                    "\t    " + tripCity + " Flights\n  Choose One Airport To Go From");
            labelselectAirport.setStyle("-fx-font-weight: bold;");
            labelselectAirport.setTextFill(Color.DARKBLUE);
            labelselectAirport.setFont(new Font(40));
            labelselectAirport.setAlignment(Pos.TOP_CENTER);

            Button lahoreButton = new Button("    Allama Iqbal International Airport, Lahore     ");
            lahoreButton.setFont(new Font(25));
            lahoreButton.setAlignment(Pos.BASELINE_CENTER);
            Button karachiButton = new Button("          Jinnah International Airport, Karachi         ");
            karachiButton.setFont(new Font(25));
            karachiButton.setAlignment(Pos.BASELINE_CENTER);
            Button islamabadButton = new Button("Benazir Bhutto International Airport, Islamabad");
            islamabadButton.setFont(new Font(25));
            islamabadButton.setAlignment(Pos.BASELINE_CENTER);
            VBox airportVBox = new VBox(labelselectAirport, lahoreButton, karachiButton, islamabadButton);
            airportVBox.setSpacing(20);
            //airportVBox.setAlignment(Pos.BASELINE_CENTER);

            HBox airportHBox = new HBox(10,new Label("\t\t"),airportVBox);
            flights.setContent(airportHBox);
            flightBorderPane.setCenter(flights);

            lahoreButton.setOnAction(e -> {
                Label labellahore = new Label("Flights From Lahore To " + tripCity);
                labellahore.setFont(new Font(35));
                labellahore.setAlignment(Pos.CENTER);
                deptAirport = "Allama Iqbal Airport, Lahore";
                if (airportVBox.getChildren().getLast() instanceof VBox) {
                    airportVBox.getChildren().removeLast();
                    airportVBox.getChildren().add(flights(flightBorderPane));
                } else {
                    airportVBox.getChildren().add(flights(flightBorderPane));
                }
            });

            karachiButton.setOnAction(e -> {
                Label labelKarachi = new Label("Flights From Karachi To " + tripCity);
                labelKarachi.setFont(new Font(35));
                labelKarachi.setAlignment(Pos.TOP_CENTER);
                deptAirport = "Jinnah International Airport, Karachi";

                if(airportVBox.getChildren().getLast() instanceof VBox){
                    airportVBox.getChildren().removeLast();
                    airportVBox.getChildren().add(flights(flightBorderPane));
                }else{
                    airportVBox.getChildren().add(flights(flightBorderPane));
                }

            });

            islamabadButton.setOnAction(e -> {
                Label labelIslamabad = new Label("Flights From Islamabad To " + tripCity);
                labelIslamabad.setFont(new Font(35));
                labelIslamabad.setAlignment(Pos.CENTER);
                deptAirport = "Benazir Bhutto International Airport, Islamabad";

                if(airportVBox.getChildren().getLast() instanceof VBox){
                    airportVBox.getChildren().removeLast();
                    airportVBox.getChildren().add(flights(flightBorderPane));
                }else{
                    airportVBox.getChildren().add(flights(flightBorderPane));
                }
            });
        }

    }




    public VBox flights(BorderPane borderPane) {

        RadioButton radioButton1 = new RadioButton("Book");
        RadioButton radioButton2 = new RadioButton("Book");
        RadioButton radioButton3 = new RadioButton("Book");
        RadioButton radioButton4 = new RadioButton("Book");
        RadioButton radioButton5 = new RadioButton("Book");

        radioButton1.setStyle("-fx-font-size: 25px;");
        radioButton2.setStyle("-fx-font-size: 25px;");
        radioButton3.setStyle("-fx-font-size: 25px;");
        radioButton4.setStyle("-fx-font-size: 25px;");
        radioButton5.setStyle("-fx-font-size: 25px;");

        ToggleGroup toggleGroup = new ToggleGroup();

        Label flightNo1 = new Label("Flight Number 1");
        flightNo1.setStyle("-fx-font-weight: bold;");
        flightNo1.setFont(new Font(25));
        Label flight1 = new Label(
                "\nFlight Number: PQR456\tAirline: AirBlue Airways\tFlight Type: Economy Class\tTake-Off Time: 7:30am");
        flight1.setFont(new Font(15));
        Label flight1Price = new Label("Price = 56000 pkr");
        flight1Price.setFont(new Font(15));
        VBox vbox1 = new VBox(flightNo1, flight1, flight1Price, radioButton1);

        Label flightNo2 = new Label("Flight Number 2");
        flightNo2.setStyle("-fx-font-weight: bold;");
        flightNo2.setFont(new Font(25));
        Label flight2 = new Label(
                "\nFlight Number: LMN789\tAirline: SwiftSky Airways\tFlight Type: Business Class\tTake-Off Time: 1:15pm");
        flight2.setFont(new Font(15));
        Label flight2Price = new Label("Price = 48000 pkr");
        flight2Price.setFont(new Font(15));
        VBox vbox2 = new VBox(flightNo2, flight2, flight2Price, radioButton2);

        Label flightNo3 = new Label("Flight Number 3");
        flightNo3.setStyle("-fx-font-weight: bold;");
        flightNo3.setFont(new Font(25));
        Label flight3 = new Label(
                "\nFlight Number: STU123\tAirline: Emirates Airways\tFlight Type: Premium Economy\tTake-Off Time: 4:00pm");
        flight3.setFont(new Font(15));
        Label flight3Price = new Label("Price = 65000 pkr");
        flight3Price.setFont(new Font(15));
        VBox vbox3 = new VBox(flightNo3, flight3, flight3Price, radioButton3);

        Label flightNo4 = new Label("Flight Number 4");
        flightNo4.setStyle("-fx-font-weight: bold;");
        flightNo4.setFont(new Font(25));
        Label flight4 = new Label(
                "\nFlight Number: XYZ987\tAirline: Etihad Airways\tFlight Type: First Class\tTake-Off Time: 10:45pm");
        flight4.setFont(new Font(15));
        Label flight4Price = new Label("Price = 72000 pkr");
        flight4Price.setFont(new Font(15));
        VBox vbox4 = new VBox(flightNo4, flight4, flight4Price, radioButton4);

        Label flightNo5 = new Label("Flight Number 5");
        flightNo5.setStyle("-fx-font-weight: bold;");
        flightNo5.setFont(new Font(25));
        Label flight5 = new Label(
                "\nFlight Number: ABC321\tAirline: SkyElite Airlines\tFlight Type: Business Class\tTake-Off Time: 3:30am");
        flight5.setFont(new Font(15));
        Label flight5Price = new Label("Price = 51000 pkr");
        flight5Price.setFont(new Font(15));
        VBox vbox5 = new VBox(flightNo5, flight5, flight5Price, radioButton5);

        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        radioButton4.setToggleGroup(toggleGroup);
        radioButton5.setToggleGroup(toggleGroup);

        Label numberOfSeatsLabel = new Label("Enter Number of Passengers: ");
        TextField numberOfSeatsField = new TextField();
        Label errorLabel = new Label();

        HBox numberHBox = new HBox(numberOfSeatsLabel, numberOfSeatsField);
        Button bookflight = new Button("Book Flight");
        bookflight.setFont(new Font(20));
        HBox flightHBox = new HBox(emptyLabel, bookflight);

        VBox vBox6 = new VBox(numberHBox, errorLabel, flightHBox, new Label("\n\n\n\n\n\n\n\n"));

        VBox flightsVbox = new VBox(vbox1, vbox2, vbox3, vbox4, vbox5, vBox6);
        flightsVbox.setSpacing(15);


        bookflight.setOnAction(e -> {
            int numberofSeats = Integer.parseInt(numberOfSeatsField.getText());
            if (radioButton1.isSelected()) {
                bookedFlight = new Flights(2384, numberofSeats, "AirBlue Airways", deptAirport, tripCity + " Airport",
                        56000);
            } else if (radioButton2.isSelected()) {
                bookedFlight = new Flights(7856, numberofSeats, "AirBlue Airways", deptAirport, tripCity + " Airport",
                        48000);
            } else if (radioButton3.isSelected()) {
                bookedFlight = new Flights(9034, numberofSeats, "AirBlue Airways", deptAirport, tripCity + " Airport",
                        65000);
            } else if (radioButton4.isSelected()) {
                bookedFlight = new Flights(6273, numberofSeats, "AirBlue Airways", deptAirport, tripCity + " Airport",
                        72000);
            } else if (radioButton5.isSelected()) {
                bookedFlight = new Flights(5098, numberofSeats, "AirBlue Airways", deptAirport, tripCity + " Airport",
                        51000);
            }
            hotels(borderPane);

        });

        return flightsVbox;

    }









    // HOTELS
    public void hotels(BorderPane borderPane) {
        if (tripCity == null) {
            VBox goToCityVBox = new VBox();
            Label goToCityLabel = new Label("First Select Your Trip City to view Hotels.");
            goToCityLabel.setFont(new Font(30));
            Button goToCityButton = new Button("SELECT CITY.");
            goToCityButton.setFont(new Font(30));
            goToCityVBox.getChildren().addAll(new Label("\n\n\n\n"), goToCityLabel, goToCityButton);
            goToCityVBox.setAlignment(Pos.BASELINE_CENTER);
            borderPane.setCenter(goToCityVBox);

            goToCityButton.setOnAction(e -> {
                cities(borderPane);
            });
        } else {
            if (tripCity.equals("Dubai"))
                hotel1(borderPane);
            else if (tripCity.equals("NewYork"))
                hotel2(borderPane);
            else if (tripCity.equals("London"))
                hotel3(borderPane);
            else if (tripCity.equals("Paris"))
                hotel4(borderPane);
            else if (tripCity.equals("Beijing"))
                hotel5(borderPane);
        }

    }

    RadioButton radioButton1 = new RadioButton("Book");
    RadioButton radioButton2 = new RadioButton("Book");
    RadioButton radioButton3 = new RadioButton("Book");
    RadioButton radioButton4 = new RadioButton("Book");
    RadioButton radioButton5 = new RadioButton("Book");
    ToggleGroup toggleGroup = new ToggleGroup();
    Label hotelLabel = new Label();
    Label selectHotelLabel = new Label();
    Hotels bookedHotel;

    public void hotel1(BorderPane borderPane) {

        ScrollPane dubai = new ScrollPane();

        hotelLabel = new Label("\t\t\t Hotels in " + tripCity + "\n");
        hotelLabel.setFont(new Font(35));

        Button cb = new Button("Book A Hotel");
        cb.setFont(new Font(20));

        Label name1 = new Label("Royal Prince Hotel");
        name1.setFont(new Font(25));
        Label cb1 = new Label(
                "Bunk-bed dorms with free Wi-Fi, plus a sunken\n patio & garden, a cafe bar & organized nights\n out.This Hotel has Three Room Apartments    \n(PRICE PER DAY = 15,000 RUPEES)");
        cb1.setFont(new Font(15));
        VBox vBox1 = new VBox(name1, cb1, radioButton1);

        Label name2 = new Label("Flora Inn Hotel");
        name2.setFont(new Font(25));
        Label cb2 = new Label(
                "Stylish lodging with a restaurant,a rooftop terrace\n & Midtown views, plus a 24/7 gym & free Wi-Fi.\nThis hotel has Two Room Apartments     \n(PRICE PER DAY = 12,000 RUPEES)");
        cb2.setFont(new Font(15));
        VBox vBox2 = new VBox(name2, cb2, radioButton2);

        Label name3 = new Label("TRYP by Wyndham ");
        name3.setFont(new Font(25));
        Label cb3 = new Label(
                "Contemporary, upscale property offering terrace dining\n & a gym, plus a rooftop bar with city views. \nThis hotel has Four Room Apartments       \n(PRICE PER DAY = 22,000 RUPEES)");
        cb3.setFont(new Font(15));
        VBox vBox3 = new VBox(name3, cb3, radioButton3);

        Label name4 = new Label("Holiday Inn Express");
        name4.setFont(new Font(25));
        Label cb4 = new Label(
                "Modern hotel with warm rooms & an all-day\nrestaurant, plus a 24-hour business center& a gym.\nThis hotel has Two Room Apartments  \n(PRICE PER DAY = 19,000 RUPEES)");
        cb4.setFont(new Font(15));
        VBox vBox4 = new VBox(name4, cb4, radioButton4);

        Label name5 = new Label("Millennium Hotel Dubai");
        name5.setFont(new Font(25));
        Label cb5 = new Label(
                "Straightforward lodging with an Italian cafe &\nJapanese restaurant, plus a business center& a \npool.This hotel has Three Room Apartments    \n(PRICE PER DAY = 9,000 RUPEES");
        cb5.setFont(new Font(15));
        VBox vBox5 = new VBox(name5, cb5, radioButton5);

        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        radioButton4.setToggleGroup(toggleGroup);
        radioButton5.setToggleGroup(toggleGroup);

        radioButton1.setStyle("-fx-font-size: 25px;");
        radioButton2.setStyle("-fx-font-size: 25px;");
        radioButton3.setStyle("-fx-font-size: 25px;");
        radioButton4.setStyle("-fx-font-size: 25px;");
        radioButton5.setStyle("-fx-font-size: 25px;");

        ImageView c1 = new ImageView(new Image("icons/hb1.jpeg"));
        c1.setFitHeight(210);
        c1.setFitWidth(210);
        ImageView c2 = new ImageView(new Image("icons/hb2.jpg"));
        c2.setFitHeight(210);
        c2.setFitWidth(210);
        ImageView c3 = new ImageView(new Image("icons/hb3.jpeg"));
        c3.setFitHeight(210);
        c3.setFitWidth(210);
        ImageView c4 = new ImageView(new Image("icons/hb4.jpeg"));
        c4.setFitHeight(210);
        c4.setFitWidth(210);
        ImageView c5 = new ImageView(new Image("icons/hb5.jpg"));
        c5.setFitHeight(210);
        c5.setFitWidth(210);

        HBox ch1 = new HBox(25); // Set the spacing between nodes in the HBox
        ch1.getChildren().addAll(c1, vBox1);
        HBox ch2 = new HBox(25);
        ch2.getChildren().addAll(c2, vBox2);

        HBox ch3 = new HBox(25);
        ch3.getChildren().addAll(c3, vBox3);
        HBox ch4 = new HBox(25);
        ch4.getChildren().addAll(c4, vBox4);
        HBox ch5 = new HBox(25);
        ch5.getChildren().addAll(c5, vBox5);
        HBox ch6 = new HBox(emptyLabel, cb);

        VBox column1 = new VBox(30); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(hotelLabel, ch1, ch2, ch3, ch4, ch5, ch6, selectHotelLabel,
                new Label(" \n\n\n\n\n\n "));

        dubai.setContent(column1);
        borderPane.setCenter(dubai);

        cb.setOnAction(e -> {
            if (radioButton1.isSelected()) {
                bookedHotel = new Hotels(8475, 3, 15000.00, name1.getText());
            } else if (radioButton2.isSelected()) {
                bookedHotel = new Hotels(7834, 2, 12000.00, name2.getText());
            } else if (radioButton3.isSelected()) {
                bookedHotel = new Hotels(8344, 4, 22000.00, name3.getText());
            } else if (radioButton4.isSelected()) {
                bookedHotel = new Hotels(9238, 2, 19000.00, name4.getText());
            } else if (radioButton5.isSelected()) {
                bookedHotel = new Hotels(0342, 3, 9000.00, name5.getText());
            } else {
                selectHotelLabel.setText("Please Select A Hotel");
            }
            personal(borderPane);
        });

    }

    public void hotel2(BorderPane borderPane) {

        ScrollPane newYork = new ScrollPane();

        hotelLabel = new Label("\t\t Hotels in " + tripCity + "\n");
        hotelLabel.setFont(new Font(35));

        Button cb = new Button("Book A Hotel");
        cb.setFont(new Font(20));

        Label name1 = new Label("Trump International Hotel");
        name1.setFont(new Font(25));
        Label cb1 = new Label(
                "Bunk-bed dorms with free Wi-Fi,\n plus a sunken patio & garden,\n a cafe bar & organized nights out.\n This Hotel has Three Room Apartments    \n(PRICE PER DAY = 25,000 RUPEES)");
        cb1.setFont(new Font(15));
        VBox vBox1 = new VBox(name1, cb1, radioButton1);

        Label name2 = new Label("The Hotel Chelsea New York ");
        name2.setFont(new Font(25));
        Label cb2 = new Label(
                "Stylish lodging with a restaurant,\n a rooftop terrace & Midtown views,\n plus a 24/7 gym & free Wi-Fi.\nThis hotel has Two Room Apartments     \n(PRICE PER DAY = 28,670 RUPEES)");
        cb2.setFont(new Font(15));
        VBox vBox2 = new VBox(name2, cb2, radioButton2);

        Label name3 = new Label("The Westin New York");
        name3.setFont(new Font(25));
        Label cb3 = new Label(
                "Contemporary, upscale property offering terrace dining\n & a gym, plus a rooftop bar with city views. \nThis hotel has Four Room Apartments       \n(PRICE PER DAY = 35,380 RUPEES)");
        cb3.setFont(new Font(15));
        VBox vBox3 = new VBox(name3, cb3, radioButton3);

        Label name4 = new Label("The Grand Hotel New York");
        name4.setFont(new Font(25));
        Label cb4 = new Label(
                " Modern hotel with warm rooms & an all-day\n restaurant, plus a 24-hour business center\n & a gym.This hotel has Two Room Apartments  \n(PRICE PER DAY = 29,000 RUPEES)");
        cb4.setFont(new Font(15));
        VBox vBox4 = new VBox(name4, cb4, radioButton4);

        Label name5 = new Label("Park Lane New York");
        name5.setFont(new Font(25));
        Label cb5 = new Label(
                "Straightforward lodging with an Italian cafe &\n Japanese restaurant, plus a business center \n& a pool.This hotel has Three Room Apartments    \n(PRICE PER DAY = 33,550 RUPEES");
        cb5.setFont(new Font(15));
        VBox vBox5 = new VBox(name5, cb5, radioButton5);

        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        radioButton4.setToggleGroup(toggleGroup);
        radioButton5.setToggleGroup(toggleGroup);

        radioButton1.setStyle("-fx-font-size: 25px;");
        radioButton2.setStyle("-fx-font-size: 25px;");
        radioButton3.setStyle("-fx-font-size: 25px;");
        radioButton4.setStyle("-fx-font-size: 25px;");
        radioButton5.setStyle("-fx-font-size: 25px;");

        ImageView c1 = new ImageView(new Image("icons/hn1.jpg"));
        c1.setFitHeight(210);
        c1.setFitWidth(210);
        ImageView c2 = new ImageView(new Image("icons/hn2.jpg"));
        c2.setFitHeight(210);
        c2.setFitWidth(210);
        ImageView c3 = new ImageView(new Image("icons/hn3.jpg"));
        c3.setFitHeight(210);
        c3.setFitWidth(210);
        ImageView c4 = new ImageView(new Image("icons/hn4.jpg"));
        c4.setFitHeight(210);
        c4.setFitWidth(210);
        ImageView c5 = new ImageView(new Image("icons/hn5.jpg"));
        c5.setFitHeight(210);
        c5.setFitWidth(210);

        HBox ch1 = new HBox(25); // Set the spacing between nodes in the HBox
        ch1.getChildren().addAll(c1, vBox1);
        HBox ch2 = new HBox(25); 
        ch2.getChildren().addAll(c2, vBox2);

        HBox ch3 = new HBox(25); 
        ch3.getChildren().addAll(c3, vBox3);
        HBox ch4 = new HBox(25); 
        ch4.getChildren().addAll(c4, vBox4);
        HBox ch5 = new HBox(25); 
        ch5.getChildren().addAll(c5, vBox5);
        HBox ch6 = new HBox(emptyLabel, cb);

        VBox column1 = new VBox(30); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(hotelLabel, ch1, ch2, ch3, ch4, ch5, ch6, selectHotelLabel,
                new Label(" \n\n\n\n\n\n "));

        newYork.setContent(column1);
        borderPane.setCenter(newYork);

        cb.setOnAction(e -> {
            if (radioButton1.isSelected()) {
                bookedHotel = new Hotels(8475, 3, 25000.00, name1.getText());
            } else if (radioButton2.isSelected()) {
                bookedHotel = new Hotels(7834, 2, 28670.00, name2.getText());
            } else if (radioButton3.isSelected()) {
                bookedHotel = new Hotels(4875, 4, 35380.00, name3.getText());
            } else if (radioButton4.isSelected()) {
                bookedHotel = new Hotels(2938, 2, 29000.00, name4.getText());
            } else if (radioButton5.isSelected()) {
                bookedHotel = new Hotels(8233, 3, 33550.00, name5.getText());
            } else {
                selectHotelLabel.setText("Please Select A Hotel");
            }
            personal(borderPane);
        });

    }



    public void hotel3(BorderPane borderPane) {

        ScrollPane london = new ScrollPane();

        hotelLabel = new Label("\t\t Hotels in " + tripCity + "\n");
        hotelLabel.setFont(new Font(35));

        Button cb = new Button("Book A Hotel");
        cb.setFont(new Font(20));

        Label name1 = new Label("Hotel in Westminster Borough");
        name1.setFont(new Font(25));
        Label cb1 = new Label(
                "Bunk-bed dorms with free Wi-Fi,\n plus a sunken patio & garden,\n a cafe bar & organized nights out.\n This Hotel has Three Room Apartments    \n(PRICE PER DAY = 32,456 RUPEES)");
        cb1.setFont(new Font(15));
        VBox vBox1 = new VBox(name1, cb1, radioButton1);

        Label name2 = new Label("Hotel in Lambeth");
        name2.setFont(new Font(25));
        Label cb2 = new Label(
                "Stylish lodging with a restaurant,\n a rooftop terrace & Midtown views,\n plus a 24/7 gym & free Wi-Fi.\nThis hotel has Two Room Apartments     \n(PRICE PER DAY = 44,899 RUPEES)");
        cb2.setFont(new Font(15));
        VBox vBox2 = new VBox(name2, cb2, radioButton2);

        Label name3 = new Label("Millennium Gloucester");
        name3.setFont(new Font(25));
        Label cb3 = new Label(
                "Contemporary, upscale property offering terrace dining\n & a gym, plus a rooftop bar with city views. \nThis hotel has Four Room Apartments       \n(PRICE PER DAY = 55,677 RUPEES)");
        cb3.setFont(new Font(15));
        VBox vBox3 = new VBox(name3, cb3, radioButton3);

        Label name4 = new Label("Central Park Hotel");
        name4.setFont(new Font(25));
        Label cb4 = new Label(
                " Modern hotel with warm rooms & an all-day\n restaurant, plus a 24-hour business center\n & a gym.This hotel has Two Room Apartments  \n(PRICE PER DAY = 45,667 RUPEES)");
        cb4.setFont(new Font(15));
        VBox vBox4 = new VBox(name4, cb4, radioButton4);

        Label name5 = new Label(" Hotel Canary Wharf");
        name5.setFont(new Font(25));
        Label cb5 = new Label(
                " Straightforward lodging with an Italian cafe &\n Japanese restaurant, plus a business center \n& a pool.This hotel has Three Room Apartments    \n(PRICE PER DAY = 43,789 RUPEES");
        cb5.setFont(new Font(15));
        VBox vBox5 = new VBox(name5, cb5, radioButton5);

        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        radioButton4.setToggleGroup(toggleGroup);
        radioButton5.setToggleGroup(toggleGroup);

        radioButton1.setStyle("-fx-font-size: 25px;");
        radioButton2.setStyle("-fx-font-size: 25px;");
        radioButton3.setStyle("-fx-font-size: 25px;");
        radioButton4.setStyle("-fx-font-size: 25px;");
        radioButton5.setStyle("-fx-font-size: 25px;");

        ImageView c1 = new ImageView(new Image("icons/hl1.jpeg"));
        c1.setFitHeight(210);
        c1.setFitWidth(210);
        ImageView c2 = new ImageView(new Image("icons/hl2.jpeg"));
        c2.setFitHeight(210);
        c2.setFitWidth(210);
        ImageView c3 = new ImageView(new Image("icons/hl3.jpg"));
        c3.setFitHeight(210);
        c3.setFitWidth(210);
        ImageView c4 = new ImageView(new Image("icons/hl4.jpg"));
        c4.setFitHeight(210);
        c4.setFitWidth(210);
        ImageView c5 = new ImageView(new Image("icons/hl5.jpg"));
        c5.setFitHeight(210);
        c5.setFitWidth(210);

        HBox ch1 = new HBox(25); // Set the spacing between nodes in the HBox
        ch1.getChildren().addAll(c1, vBox1);
        HBox ch2 = new HBox(25); 
        ch2.getChildren().addAll(c2, vBox2);

        HBox ch3 = new HBox(25); 
        ch3.getChildren().addAll(c3, vBox3);
        HBox ch4 = new HBox(25); 
        ch4.getChildren().addAll(c4, vBox4);
        HBox ch5 = new HBox(25); 
        ch5.getChildren().addAll(c5, vBox5);
        HBox ch6 = new HBox(emptyLabel, cb);

        VBox column1 = new VBox(30); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(hotelLabel, ch1, ch2, ch3, ch4, ch5, ch6, selectHotelLabel,
                new Label(" \n\n\n\n\n\n "));

        cb.setOnAction(e -> {
            if (radioButton1.isSelected()) {
                bookedHotel = new Hotels(8475, 3, 32456.00, name1.getText());
            } else if (radioButton2.isSelected()) {
                bookedHotel = new Hotels(7834, 2, 44899.00, name2.getText());
            } else if (radioButton3.isSelected()) {
                bookedHotel = new Hotels(7834, 4, 55677.00, name3.getText());
            } else if (radioButton4.isSelected()) {
                bookedHotel = new Hotels(7834, 2, 45667.00, name4.getText());
            } else if (radioButton5.isSelected()) {
                bookedHotel = new Hotels(7834, 3, 43789.00, name5.getText());
            } else {
                selectHotelLabel.setText("Please Select A Hotel");
            }
            personal(borderPane);
        });

        london.setContent(column1);
        borderPane.setCenter(london);

    }







    public void hotel4(BorderPane borderPane) {

        ScrollPane paris = new ScrollPane();

        hotelLabel = new Label("\t\t Hotels in " + tripCity + "\n");
        hotelLabel.setFont(new Font(35));

        Button cb = new Button("Book A Hotel");
        cb.setFont(new Font(20));

        Label name1 = new Label("International Youth Hotel");
        name1.setFont(new Font(25));
        Label cb1 = new Label(
                "Bunk-bed dorms with free Wi-Fi,\n plus a sunken patio & garden,\n a cafe bar & organized nights out.\n This Hotel has Three Room Apartments    \n(PRICE PER DAY = 16,099 RUPEES)");
        cb1.setFont(new Font(15));
        VBox vBox1 = new VBox(name1, cb1, radioButton1);

        Label name2 = new Label("Hôtel de Paris Saint Georges");
        name2.setFont(new Font(25));
        Label cb2 = new Label(
                "Stylish lodging with a restaurant,\n a rooftop terrace & Midtown views,\n plus a 24/7 gym & free Wi-Fi.\nThis hotel has Two Room Apartments     \n(PRICE PER DAY = 18,899 RUPEES)");
        cb2.setFont(new Font(15));
        VBox vBox2 = new VBox(name2, cb2, radioButton2);

        Label name3 = new Label("The Playce Hotel by HappyCulture");
        name3.setFont(new Font(25));
        Label cb3 = new Label(
                "Contemporary, upscale property offering terrace dining\n & a gym, plus a rooftop bar with city views. \nThis hotel has Four Room Apartments       \n(PRICE PER DAY = 26,899 RUPEES)");
        cb3.setFont(new Font(15));
        VBox vBox3 = new VBox(name3, cb3, radioButton3);

        Label name4 = new Label("Hôtel Maison Montmartre");
        name4.setFont(new Font(25));
        Label cb4 = new Label(
                " Modern hotel with warm rooms & an all-day\n restaurant, plus a 24-hour business center\n & a gym.This hotel has Two Room Apartments  \n(PRICE PER DAY = 45,667 RUPEES)");
        cb4.setFont(new Font(15));
        VBox vBox4 = new VBox(name4, cb4, radioButton4);

        Label name5 = new Label("Hotel Astoria Astotel");
        name5.setFont(new Font(25));
        Label cb5 = new Label(
                " Straightforward lodging with an Italian cafe &\n Japanese restaurant, plus a business center \n& a pool.This hotel has Three Room Apartments    \n(PRICE PER DAY = 34,000 RUPEES");
        cb5.setFont(new Font(15));
        VBox vBox5 = new VBox(name5, cb5, radioButton5);

        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        radioButton4.setToggleGroup(toggleGroup);
        radioButton5.setToggleGroup(toggleGroup);

        radioButton1.setStyle("-fx-font-size: 25px;");
        radioButton2.setStyle("-fx-font-size: 25px;");
        radioButton3.setStyle("-fx-font-size: 25px;");
        radioButton4.setStyle("-fx-font-size: 25px;");
        radioButton5.setStyle("-fx-font-size: 25px;");

        ImageView c1 = new ImageView(new Image("icons/hp1.jpeg"));
        c1.setFitHeight(210);
        c1.setFitWidth(210);
        ImageView c2 = new ImageView(new Image("icons/hp2.jpeg"));
        c2.setFitHeight(210);
        c2.setFitWidth(210);
        ImageView c3 = new ImageView(new Image("icons/hp3.jpeg"));
        c3.setFitHeight(210);
        c3.setFitWidth(210);
        ImageView c4 = new ImageView(new Image("icons/hp4.jpeg"));
        c4.setFitHeight(210);
        c4.setFitWidth(210);
        ImageView c5 = new ImageView(new Image("icons/hp5.jpeg"));
        c5.setFitHeight(210);
        c5.setFitWidth(210);

        HBox ch1 = new HBox(25); // Set the spacing between nodes in the HBox
        ch1.getChildren().addAll(c1, vBox1);
        HBox ch2 = new HBox(25); 
        ch2.getChildren().addAll(c2, vBox2);

        HBox ch3 = new HBox(25); 
        ch3.getChildren().addAll(c3, vBox3);
        HBox ch4 = new HBox(25); 
        ch4.getChildren().addAll(c4, vBox4);
        HBox ch5 = new HBox(25); 
        ch5.getChildren().addAll(c5, vBox5);
        HBox ch6 = new HBox(emptyLabel, cb);

        VBox column1 = new VBox(30); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(hotelLabel, ch1, ch2, ch3, ch4, ch5, ch6, selectHotelLabel,
                new Label(" \n\n\n\n\n\n "));

        cb.setOnAction(e -> {
            if (radioButton1.isSelected()) {
                bookedHotel = new Hotels(8475, 3, 16099.00, name1.getText());
            } else if (radioButton2.isSelected()) {
                bookedHotel = new Hotels(7834, 2, 18899.00, name2.getText());
            } else if (radioButton3.isSelected()) {
                bookedHotel = new Hotels(7836, 4, 26899.00, name3.getText());
            } else if (radioButton4.isSelected()) {
                bookedHotel = new Hotels(3847, 2, 45667.00, name4.getText());
            } else if (radioButton5.isSelected()) {
                bookedHotel = new Hotels(1092, 3, 34000.00, name5.getText());
            } else {
                selectHotelLabel.setText("Please Select A Hotel");
            }
            personal(borderPane);
        });

        paris.setContent(column1);
        borderPane.setCenter(paris);

    }





    public void hotel5(BorderPane borderPane) {

        ScrollPane beijing = new ScrollPane();

        hotelLabel = new Label("\t\t Hotels in " + tripCity + "\n");
        hotelLabel.setFont(new Font(35));

        Button cb = new Button("Book A Hotel");
        cb.setFont(new Font(20));

        Label name1 = new Label("Holiday Inn Express Beijing");
        name1.setFont(new Font(25));
        Label cb1 = new Label(
                "Bunk-bed dorms with free Wi-Fi,\n plus a sunken patio & garden,\n a cafe bar & organized nights out.\n This Hotel has Three Room Apartments    \n(PRICE PER DAY = 18,799 RUPEES)");
        cb1.setFont(new Font(15));
        VBox vBox1 = new VBox(name1, cb1, radioButton1);

        Label name2 = new Label("Ramada by Wyndham");
        name2.setFont(new Font(25));
        Label cb2 = new Label(
                "Stylish lodging with a restaurant,\n a rooftop terrace & Midtown views,\n plus a 24/7 gym & free Wi-Fi.\nThis hotel has Two Room Apartments     \n(PRICE PER DAY = 16,099 RUPEES)");
        cb2.setFont(new Font(15));
        VBox vBox2 = new VBox(name2, cb2, radioButton2);

        Label name3 = new Label("CITIC HOTEL BEIJING");
        name3.setFont(new Font(25));
        Label cb3 = new Label(
                "Contemporary, upscale property offering terrace dining\n & a gym, plus a rooftop bar with city views. \nThis hotel has Four Room Apartments       \n(PRICE PER DAY = 18,999 RUPEES)");
        cb3.setFont(new Font(15));
        VBox vBox3 = new VBox(name3, cb3, radioButton3);

        Label name4 = new Label("Hilton Beijing Capital ");
        name4.setFont(new Font(25));
        Label cb4 = new Label(
                " Modern hotel with warm rooms & an all-day\n restaurant, plus a 24-hour business center\n & a gym.This hotel has Two Room Apartments  \n(PRICE PER DAY = 36,799 RUPEES)");
        cb4.setFont(new Font(15));
        VBox vBox4 = new VBox(name4, cb4, radioButton4);

        Label name5 = new Label("Cordis Beijing Capital");
        name5.setFont(new Font(25));
        Label cb5 = new Label(
                " Straightforward lodging with an Italian cafe &\n Japanese restaurant, plus a business center \n& a pool.This hotel has Three Room Apartments    \n(PRICE PER DAY = 38,999 RUPEES");
        cb5.setFont(new Font(15));
        VBox vBox5 = new VBox(name5, cb5, radioButton5);

        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        radioButton4.setToggleGroup(toggleGroup);
        radioButton5.setToggleGroup(toggleGroup);

        radioButton1.setStyle("-fx-font-size: 25px;");
        radioButton2.setStyle("-fx-font-size: 25px;");
        radioButton3.setStyle("-fx-font-size: 25px;");
        radioButton4.setStyle("-fx-font-size: 25px;");
        radioButton5.setStyle("-fx-font-size: 25px;");

        ImageView c1 = new ImageView(new Image("icons/hj1.jpg"));
        c1.setFitHeight(210);
        c1.setFitWidth(210);
        ImageView c2 = new ImageView(new Image("icons/hj2.jpg"));
        c2.setFitHeight(210);
        c2.setFitWidth(210);
        ImageView c3 = new ImageView(new Image("icons/hj3.jpg"));
        c3.setFitHeight(210);
        c3.setFitWidth(210);
        ImageView c4 = new ImageView(new Image("icons/hj4.jpg"));
        c4.setFitHeight(210);
        c4.setFitWidth(210);
        ImageView c5 = new ImageView(new Image("icons/hj5.jpg"));
        c5.setFitHeight(210);
        c5.setFitWidth(210);

        HBox ch1 = new HBox(25); // Set the spacing between nodes in the HBox
        ch1.getChildren().addAll(c1, vBox1);
        HBox ch2 = new HBox(25); 
        ch2.getChildren().addAll(c2, vBox2);

        HBox ch3 = new HBox(25); 
        ch3.getChildren().addAll(c3, vBox3);
        HBox ch4 = new HBox(25); 
        ch4.getChildren().addAll(c4, vBox4);
        HBox ch5 = new HBox(25); 
        ch5.getChildren().addAll(c5, vBox5);
        HBox ch6 = new HBox(emptyLabel, cb);

        VBox column1 = new VBox(30); // Set the spacing between nodes in the VBox
        column1.getChildren().addAll(hotelLabel, ch1, ch2, ch3, ch4, ch5, ch6, selectHotelLabel,
                new Label(" \n\n\n\n\n\n "));

        cb.setOnAction(e -> {
            if (radioButton1.isSelected()) {
                bookedHotel = new Hotels(8475, 3, 18799.00, name1.getText());
            } else if (radioButton2.isSelected()) {
                bookedHotel = new Hotels(7834, 2, 16099.00, name2.getText());
            } else if (radioButton3.isSelected()) {
                bookedHotel = new Hotels(7836, 4, 18999.00, name3.getText());
            } else if (radioButton4.isSelected()) {
                bookedHotel = new Hotels(3847, 2, 36799.00, name4.getText());
            } else if (radioButton5.isSelected()) {
                bookedHotel = new Hotels(1092, 3, 38999.00, name5.getText());
            } else {
                selectHotelLabel.setText("Please Select A Hotel");
            }
            personal(borderPane);
        });

        beijing.setContent(column1);
        borderPane.setCenter(beijing);

    }





















    

    ArrayList<Activity> activityList = new ArrayList<>();
    
    ArrayList<Trip> bookedTrip = new ArrayList<>();

    public void personal(BorderPane borderPane) {
        
            ScrollPane perDetailsScrollPane = new ScrollPane();

            Label confirmDetails = new Label("\t  CONFIRM YOUR TRIP DETAILS");
            confirmDetails.setFont(new Font(30));
            confirmDetails.setStyle("-fx-font-weight: bold");
            confirmDetails.setTextFill(Color.DARKBLUE);
            Label bookedCity = new Label("TRIP LOCATION\nCity:\t" + tripCity + "\t\t\t  Country:\t" + tripCountry);
            bookedCity.setFont(new Font(22));
            bookedCity.setTextFill(Color.DARKGREEN);


            Label startDateLabel = new Label("Start Date:\t  "+startDatePicker.getValue());
            startDateLabel.setFont(new Font(22));
            startDateLabel.setTextFill(Color.DARKGREEN);
            Label endDateLabel = new Label("\t   End Date: \t  "+endDatePicker.getValue());
            endDateLabel.setTextFill(Color.DARKGREEN);
            endDateLabel.setFont(new Font(22));

            Label flightLabel = new Label("FLIGHT");
            flightLabel.setFont(new Font(23));
            flightLabel.setStyle("-fx-font-weight: bold;");
            flightLabel.setTextFill(Color.DARKBLUE);
            Label bookedflightLabel = new Label("\n"+bookedFlight.toString());
            bookedflightLabel.setFont(new Font(18));

            Label hotelLabel = new Label("HOTEL");
            hotelLabel.setFont(new Font(23));
            hotelLabel.setStyle("-fx-font-weight: bold;");
            hotelLabel.setTextFill(Color.DARKBLUE);
            Label bookedhotelLabel = new Label("\n"+bookedHotel.toString());
            bookedhotelLabel.setFont(new Font(18));

            Label excursionLabel = new Label("EXCURSIONS");
            excursionLabel.setFont(new Font(25));
            excursionLabel.setStyle("-fx-font-weight: bold;");
            excursionLabel.setTextFill(Color.DARKBLUE);
            Label bookedExcursions[] = new Label[count];

            VBox excursionVBox = new VBox();
            excursionVBox.setSpacing(15);

            for (int j = 0; j < count; j++) {
                bookedExcursions[j] = new Label(excursionsList.get(j).toString()+"\n");
                bookedExcursions[j].setFont(new Font(20));
                
                excursionVBox.getChildren().add(bookedExcursions[j]);
            }

            Button submitButton = new Button("Submit");
            submitButton.setFont(new Font(30));
            Label errorLabel = new Label();
            errorLabel.setFont(new Font(20));

            HBox reviewHbox = new HBox(15,startDateLabel,endDateLabel);


            VBox vBox1 = new VBox(confirmDetails,bookedCity,reviewHbox,flightLabel,bookedflightLabel,hotelLabel,bookedhotelLabel,excursionLabel,excursionVBox,submitButton,errorLabel,new Label("\n\n\n\n\n\n"));
            vBox1.setSpacing(25);


            HBox hbox1 = new HBox(15,new Label("\t\t"),vBox1);

            submitButton.setOnAction(e -> {
                
                    Trip trip = new Trip(startDatePicker.getValue(), endDatePicker.getValue(),
                            new Location(tripCity, tripCountry), activityList);
    
                    
                        
                        HotelBooking bookedHotelBooking = new HotelBooking(new Location(tripCity, tripCountry),
                        bookedHotel, startDatePicker.getValue(), endDatePicker.getValue());
                        FlightBooking bookedFlightBooking = new FlightBooking(new Location(tripCity, tripCountry),
                        bookedFlight);
                        activityList.add(bookedFlightBooking);
                        activityList.add(bookedHotelBooking);
    
    
                        for (int i = 0; i < count; i++) {
                            activityList.add(excursionsList.get(i));
                        }

                        trip.setActivities(activityList);

                        if(travelerData!=null){
                            travelerData.getTrip().add(trip);
                        }else{
                            bookedTrip.add(trip);
                            travelerData = new Traveler(signUpdata.getName(), contactTextField.getText(),
                            nationalityTextField.getText(), cnicTextField.getText(), passportTextField.getText(),
                            personLocation, bookedTrip);

                        }
                        writeToFile();
                        errorLabel.setText("Successfully Added");
            });

            perDetailsScrollPane.setContent(hbox1);
            perDetailsScrollPane.setFitToWidth(true);

            borderPane.setCenter(perDetailsScrollPane);

    }

   












    // ABOUT
    public void about(BorderPane aboutBorderPane) {
        ScrollPane aboutsScrollPane = new ScrollPane();

        Label aboutUsLabel = new Label("ABOUT US");
        aboutUsLabel.setFont(new Font(30));
        aboutUsLabel.setTextFill(Color.DARKGOLDENROD);
        Label informationLabel = new Label(
                "The Travel Itinerary System is a Java application that allows \nusers to create, organize, and manage travel itineraries. Users \ncan plan and schedule trips, add various activities, such as\n flights, hotel bookings, and excursions, and calculate \ntotal trip costs. The system supports user authentication,\n integrates with external data sources, and provides \nnotifications for upcoming activities.\n\n\n Developer:\nMuhammad Zaid Amjad");
        informationLabel.setFont(new Font(20));
        Label emptyLabel = new Label("\t\t\t\t\t\t\t\t\t\t\t");
        Label emptyLabe2 = new Label("\t\t\t\t\t");
        VBox aboutUsVBox = new VBox();

        HBox spaceBox = new HBox(new Label("\n"));
        HBox aboutUsHBox = new HBox(emptyLabel, aboutUsLabel);
        HBox infoHBox = new HBox(emptyLabe2, informationLabel);

        aboutUsVBox.getChildren().addAll(spaceBox, aboutUsHBox, infoHBox);

        aboutsScrollPane.setContent(aboutUsVBox);
        aboutBorderPane.setCenter(aboutsScrollPane);
    }















    public static void main(String[] args) throws ParseException {
        launch(args);
    }
}