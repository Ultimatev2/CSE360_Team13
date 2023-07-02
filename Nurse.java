package com.example.cse360;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.paint.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Nurse extends Application {

    String nameText;
    String dobText;
    String allergiesText;
    String healthConcernsText;
    String weightText;
    String heightText;
    String bodyTemperatureText;
    String bloodPressureText;
    String patientInfoText;
    String patientVitalsText;

    @Override
    public void start(Stage primaryStage) {
        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setVgap(10);
        mainPane.setHgap(10);
        mainPane.setPadding(new Insets(10));

        // title of stage
        primaryStage.setTitle("Nurse's View");

        // buttons, ageCB, labels
        Button messageButton = new Button("Patient\nPortal\nMessage");
//        messageButton.setOnAction(event -> messagePatient(primaryStage));
        Button logoutButton = new Button("Logout");
//        logoutButton.setOnAction(event -> logout(primaryStage));  //primaryStage.close();
        Button continueButton = new Button("Continue");
//        continueButton.setOnAction(event -> pressedContinue(primaryStage));
        Label helloLabel = new Label("Hello, nurse");

        Label patientInfoLabel = new Label("Patient Information");
        Label patientVitalsLabel = new Label("Patient's Vitals");
        CheckBox ageCB = new CheckBox("Patient is not over 12 years old");

        // textfields, concerns textarea (Patient's Information, Patient's Vitals)
        TextField nameTextField = new TextField("Name: ");
        TextField dobTextField = new TextField("DOB: ");
        TextField allergiesTextField = new TextField("Known allergies: ");
        TextArea concernsTextArea = new TextArea("Any health concerns: ");
        concernsTextArea.setPrefWidth(allergiesTextField.getWidth());
        concernsTextArea.setPrefHeight(allergiesTextField.getHeight());
        TextField weightTextField = new TextField("Weight: ");
        TextField heightTextField = new TextField("Height: ");
        TextField temperatureTextField = new TextField("Temperature: ");
        TextField bloodpressureTextField = new TextField("Blood pressure: ");

        mainPane.add(messageButton, 0, 0);
        mainPane.add(logoutButton, 2, 1);
        mainPane.add(continueButton, 2, 7);
        mainPane.add(helloLabel, 2, 0);

        mainPane.add(patientInfoLabel, 0, 2);
        mainPane.add(nameTextField, 0, 3);
        mainPane.add(dobTextField, 0, 4);
        mainPane.add(allergiesTextField, 0, 5);
        mainPane.add(concernsTextArea, 0, 6);

        mainPane.add(patientVitalsLabel, 1, 2);
        mainPane.add(weightTextField, 1, 3);
        mainPane.add(heightTextField, 1, 4);
        mainPane.add(temperatureTextField, 1, 5);
        mainPane.add(bloodpressureTextField, 1, 6);
        mainPane.add(ageCB, 1, 7);


        continueButton.setOnAction(event -> {
            nameText = nameTextField.getText();
            dobText = dobTextField.getText();
            allergiesText = allergiesTextField.getText();
            healthConcernsText = concernsTextArea.getText();
            patientInfoText = nameText+"\n"+dobText+"\n"+allergiesText+"\n"+healthConcernsText;

            weightText = weightTextField.getText();
            heightText = heightTextField.getText();
            bodyTemperatureText = temperatureTextField.getText();
            bloodPressureText = bloodpressureTextField.getText();
            patientVitalsText = weightText+"\n"+heightText+"\n"+bodyTemperatureText;
            if(!ageCB.isSelected()) {patientVitalsText+="\n"+bloodPressureText;}

            String name = nameText.substring(6);    //"Name: ".length()=6
//            System.out.println(name);
//            System.out.println(patientInfoText);
//            System.out.println("\n"+patientVitalsText);

//            if((bloodPressureText.length()<=15) && !ageCB.isSelected()){
//                System.out.println("patient is over 12 but no blood pressure info");
//            }

            if (nameText.length()<=6 || dobText.length()<=5 || allergiesText.length()<=16 || healthConcernsText.length()<=20 ||
                    weightText.length()<=7 || heightText.length()<=7 || bodyTemperatureText.length()<=12 || (bloodPressureText.length()<=15) && !ageCB.isSelected()) {
//                showErrorMessage("Please fill in all patient information.");
                showErrorMessage("Please fill in information.");
            }
//            if (weightText.length()<=7 || heightText.length()<=7 || bodyTemperatureText.length()<=12 || (bloodPressureText.length()<=15) && !ageCB.isSelected()) {
//                showErrorMessage("Please fill in all vital information.");
//            }
            else {
                pressedContinue(primaryStage);
                createVisitsFile(name+"_visit.txt", patientInfoText, patientVitalsText);
//                stage.close();
            }
        });

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();    // show stage
    }

    private void messagePatient(Stage stage) {
        stage.close();
        //go to patient portal page
    }

    private void logout(Stage stage) {
        stage.close();
        //go to main page
    }

    private void pressedContinue(Stage primaryStage) {
        primaryStage.close();
        Stage secondStage = new Stage();
        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setVgap(10);
        mainPane.setHgap(10);
        mainPane.setPadding(new Insets(10));

        // title of stage
        secondStage.setTitle("Nurse's View: Patient History");

        // buttons, labels
        Button messageButton = new Button("Patient\nPortal\nMessage");
//        messageButton.setOnAction(event -> messagePatient(primaryStage));
        Button logoutButton = new Button("Logout");
//        logoutButton.setOnAction(event -> logout(secondStage));
        Label helloLabel = new Label("Hello, nurse");

        Label previousHealthIssues = new Label("Previous Health Issues");
        Label prescribedMedications = new Label("Previously Prescribed Medications");
        Label historyImmunization = new Label("History of Immunization");
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> pressedAdd(primaryStage, secondStage));

        mainPane.add(messageButton, 0, 0);
        mainPane.add(logoutButton, 3, 1);
        mainPane.add(helloLabel, 3, 0);

        mainPane.add(previousHealthIssues, 0, 2);
        mainPane.add(prescribedMedications, 1, 2);
        mainPane.add(historyImmunization, 2, 2);
        mainPane.add(addButton, 3, 4);

        //lists
        TextArea previousHealthIssuesTextArea = new TextArea();
        previousHealthIssuesTextArea.setText("...\n...\n...\n...\n");
//        ListView<String> previousHealthIssuesListView = new ListView<>();
//        previousHealthIssuesListView.getItems().addAll("... ", "...", "...", "...");
        previousHealthIssuesTextArea.setPrefHeight(120);
        previousHealthIssuesTextArea.setPrefWidth(200);
        mainPane.add(previousHealthIssuesTextArea, 0, 3);

        TextArea prescribedMedicationsTextArea = new TextArea();
        prescribedMedicationsTextArea.setText("...\n...\n...\n...\n");
//        ListView<String> prescribedMedicationsListView = new ListView<>();
//        prescribedMedicationsListView.getItems().addAll("... ", "...", "...", "...");
        prescribedMedicationsTextArea.setPrefHeight(120);
        prescribedMedicationsTextArea.setPrefWidth(200);
        mainPane.add(prescribedMedicationsTextArea, 1, 3);

        TextArea historyImmunizationTextArea = new TextArea();
        historyImmunizationTextArea.setText("...\n...\n...\n...\n");
//        ListView<String> historyImmunizationListView = new ListView<>();
//        historyImmunizationListView.getItems().addAll("... ", "...", "...", "...");
        historyImmunizationTextArea.setPrefHeight(120);
        historyImmunizationTextArea.setPrefWidth(200);
        mainPane.add(historyImmunizationTextArea, 2, 3);

        // create the scene
        Scene scene = new Scene(mainPane);
        secondStage.setScene(scene);
        secondStage.show();    // show stage
    }

    private void pressedAdd(Stage primaryStage, Stage secondStage) {
        secondStage.close();
        primaryStage.show();
        //add patient info to Doctor
    }
    private void createVisitsFile(String fileName, String patientInfo, String patientVitals) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(patientInfo + "\n" + patientVitals);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showErrorMessage(String message) {
        Stage stage = new Stage();
        stage.setTitle("Error");
        Label label = new Label(message);
        label.setTextFill(Color.RED);
        label.setAlignment(Pos.CENTER);
        Scene scene = new Scene(label, 200, 100);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}