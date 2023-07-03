package com.example.cse360;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

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
    String name;

    @Override
    public void start(Stage primaryStage) {
        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setVgap(10);
        mainPane.setHgap(10);
        mainPane.setPadding(new Insets(10));

        primaryStage.setTitle("Nurse's View");

        // buttons, ageCB, labels
        Button messageButton = new Button("Patient\nPortal\nMessage");
        Button logoutButton = new Button("Logout");
        Button continueButton = new Button("Continue");
        Label helloLabel = new Label("Hello, nurse");
        Label patientInfoLabel = new Label("Patient Information");
        Label patientVitalsLabel = new Label("Patient's Vitals");
        CheckBox ageCB = new CheckBox("Patient is not over 12 years old");

        messageButton.setStyle("-fx-background-color: lavender");
        continueButton.setStyle("-fx-background-color: lightblue;");

        // textfields/area (Patient's Information, Patient's Vitals)
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
        mainPane.add(continueButton, 2, 8);
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


        messageButton.setOnAction(event -> messageWho());
//        logoutButton.setOnAction(event -> logout(primaryStage));  //primaryStage.close();
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

            name = nameText.substring(6);    //"Name: ".length()=6
//            System.out.println(name);
//            System.out.println(patientInfoText);
//            System.out.println("\n"+patientVitalsText);

            if (nameText.length()<=6 || dobText.length()<=5 || allergiesText.length()<=17 || healthConcernsText.length()<=21) {
                showErrorMessage("Please fill in all patient information.");
            } else if (weightText.length()<=8 || heightText.length()<=8 || bodyTemperatureText.length()<=13 || (bloodPressureText.length()<=16 && !ageCB.isSelected())) {
                showErrorMessage("Please fill in all vital information.");
            } else {
                pressedContinue();
                createFiles(name, patientInfoText, patientVitalsText);
//                stage.close();
            }
        });

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();    // show stage
    }
    private void messageWho(){  // popup page asking which patient to message
        Stage whichPatientStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        whichPatientStage.setTitle("Nurse: Patient Portal Message");
        Label patientNameLabel = new Label("Patient:");
        TextField patientNameTextField = new TextField();
        Button okButton = new Button("Ok");
        okButton.setStyle("-fx-background-color: lightblue;");
        gridPane.add(patientNameLabel,0,0);
        gridPane.add(patientNameTextField,1,0);
        gridPane.add(okButton,0,1);
        Scene scene = new Scene(gridPane);
        whichPatientStage.setScene(scene);
        whichPatientStage.show();

        okButton.setOnAction(e ->{
            name = patientNameTextField.getText();
            Path path = Paths.get(name+"_messages.txt");
            if(!Files.exists(path)) { showErrorMessage("Invalid patient.\n"); }
            else {
                messagePatient(name);
                whichPatientStage.close();
            }
        });
    }

    private void messagePatient(String name) {  //go to patient portal message page
        Stage messagingStage = new Stage();
        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setVgap(10);
        mainPane.setHgap(10);
        mainPane.setPadding(new Insets(10));

        messagingStage.setTitle("Nurse: Patient Portal Message");
        Label patientNameLabel = new Label("Patient: "+name);
        TextArea messageTextArea = new TextArea();
        Button sendMessageButton = new Button("Send");
        sendMessageButton.setStyle("-fx-background-color: lightblue;");
        try {
            Scanner scanner = new Scanner(new File(name+"_messages.txt"));
            StringBuilder fileContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContent.append(line).append("\n");
            }
            messageTextArea.setText(fileContent.toString());
            scanner.close();

            mainPane.add(patientNameLabel,0,0);
            mainPane.add(messageTextArea,0,1);
            mainPane.add(sendMessageButton,0,2);

            Scene scene = new Scene(mainPane, 300, 200);
            messagingStage.setScene(scene);
            messagingStage.show();

        } catch (FileNotFoundException e) {
            showErrorMessage("No patient with the \nfollowing name found.");
//            e.printStackTrace();
        }

        sendMessageButton.setOnAction(event -> {
            String message = messageTextArea.getText();
            updateMessageFile(name, message);
//            messagingStage.close();
        });
    }
    private void updateMessageFile(String name, String message){
        try {
            FileWriter fileWriter = new FileWriter(name+"_messages.txt");
            fileWriter.write(message);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pressedContinue() {    // go to second page for patient history
        Stage secondStage = new Stage();
        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setVgap(10);
        mainPane.setHgap(10);
        mainPane.setPadding(new Insets(10));

        secondStage.setTitle("Nurse's View: Patient History");

        // buttons, labels
        Button messageButton = new Button("Patient\nPortal\nMessage");
        Button logoutButton = new Button("Logout");

        messageButton.setStyle("-fx-background-color: lavender;");
        messageButton.setOnAction(event -> messagePatient(name));
//        logoutButton.setOnAction(event -> logout(secondStage));

        Label helloLabel = new Label("Hello, nurse");
        Label patientNameLabel = new Label(name+"'s Medical History");

        Label previousHealthIssues = new Label("Previous Health Issues");
        Label prescribedMedications = new Label("Previously Prescribed Medications");
        Label historyImmunization = new Label("History of Immunization");

        mainPane.add(messageButton, 0, 0);
        mainPane.add(logoutButton, 3, 1);
        mainPane.add(helloLabel, 3, 0);

        mainPane.add(patientNameLabel, 1, 0);
        mainPane.add(previousHealthIssues, 0, 2);
        mainPane.add(prescribedMedications, 1, 2);
        mainPane.add(historyImmunization, 2, 2);

        // textareas
        TextArea previousHealthIssuesTextArea = new TextArea();
        TextArea prescribedMedicationsTextArea = new TextArea();
        TextArea historyImmunizationTextArea = new TextArea();

        previousHealthIssuesTextArea.setText("non");
        prescribedMedicationsTextArea.setText("non");
        historyImmunizationTextArea.setText("non");

        previousHealthIssuesTextArea.setPrefHeight(120);
        previousHealthIssuesTextArea.setPrefWidth(200);
        prescribedMedicationsTextArea.setPrefHeight(120);
        prescribedMedicationsTextArea.setPrefWidth(200);
        historyImmunizationTextArea.setPrefHeight(120);
        historyImmunizationTextArea.setPrefWidth(200);

        mainPane.add(previousHealthIssuesTextArea, 0, 3);
        mainPane.add(prescribedMedicationsTextArea, 1, 3);
        mainPane.add(historyImmunizationTextArea, 2, 3);

//        loadPatientHistory(name+"_visit.txt", previousHealthIssuesTextArea);
//        loadPatientHistory(name+"_visit.txt", prescribedMedicationsTextArea);
//        loadPatientHistory(name+"_visit.txt", historyImmunizationTextArea);


        // create the scene
        Scene scene = new Scene(mainPane);
        secondStage.setScene(scene);
        secondStage.show();    // show stage
    }
    private void loadPatientHistory(String patientFileName, TextArea textArea) {
        try {
            Scanner scanner = new Scanner(new File(patientFileName));
            StringBuilder historyContent = new StringBuilder();
            String patientHistory;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                historyContent.append(line).append("\n");
            }
            textArea.setText(historyContent.toString());
            scanner.close();

//            int newlineIndex = patientInfoText.indexOf('\n\n');
//            patientHistory = historyContent.toString().substring(newlineIndex);

        } catch (FileNotFoundException e) {
            showErrorMessage("_visit.txt file not found.");
//            e.printStackTrace();
        }
    }
    private void createFiles(String name, String patientInfo, String patientVitals) {
        try {
            FileWriter visitFileWriter = new FileWriter(name+"_visit.txt");
            visitFileWriter.write(patientInfo + "\n" + patientVitals);
            FileWriter messagesFileWriter = new FileWriter(name+"_messages.txt");
            messagesFileWriter.write("");
            visitFileWriter.close();
            messagesFileWriter.close();
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
    private void logout(Stage stage) {
        stage.close();
        //go to main page
    }

    public static void main(String[] args) {launch();}
}
