package com.example.cse360project;;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.Alert.AlertType;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.control.Button;
import javafx.scene.Scene;



public class Doctor extends Application {

    private String symptoms = "";
    private String diagnosis = "";
    private TextArea diagnosisTextArea;
    private TextArea treatTextArea;
    private TextArea messageText;
    private TextArea subjectText;

    private CheckBox s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
    private Button b1,b2,b5,b6;


    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) throws Exception {
        try {
            //Labels
            Label titleLabel = new Label("Doctor's View");
            titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
            Label helloLabel = new Label("Hello Dr. Jane Doe,\nPenguin Pediatrics");
            helloLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            HBox helloLay = new HBox(helloLabel);
            helloLay.setAlignment(Pos.TOP_LEFT);

            //Buttons
            b1 = new Button("Diagnose");
            b2 = new Button("Create Treatment Plan");
            b5 = new Button("Save and Exit");

            String buttonStyle = "-fx-background-color: lightblue;";
            b1.setStyle(buttonStyle);
            b2.setStyle(buttonStyle);
            b5.setStyle(buttonStyle);

            //PATIENT INFORMATION SECTION
            VBox patientBox = new VBox();
            patientBox.setSpacing(10);
            patientBox.setPadding(new Insets(30));
            patientBox.setStyle("-fx-border-color: lightblue; -fx-border-width: 1px;");
            patientBox.setMaxWidth(275);

            //Patient Header
            Label patientHeader = new Label("Patient Information");
            patientHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-underline: true;");
            HBox patLay = new HBox(patientHeader);
            patLay.setAlignment(Pos.TOP_CENTER);
            //Patient TextArea
            TextArea patientText = new TextArea();
            patientText.setEditable(false);
            patientBox.getChildren().addAll(patLay,patientText);


            //SYMPTOMS SECTION
            s1 = new CheckBox("Fever");
            s2 = new CheckBox("Nausea");
            s3 = new CheckBox("Joint Pain");
            s4 = new CheckBox("Abdominal Pain");
            s5 = new CheckBox("Cough");
            s6 = new CheckBox("Sore Throat");
            s7 = new CheckBox("Diarrhea");
            s8 = new CheckBox("Rash");
            s9 = new CheckBox("Swelling");
            s10 = new CheckBox("Bruising");

            //Symptom Box

            VBox symptomBox = new VBox();
            symptomBox.setSpacing(15);
            symptomBox.setPadding(new Insets(10));
            symptomBox.setStyle("-fx-border-color: lightblue; -fx-border-width: 1px;");
            symptomBox.setMaxWidth(200);

            //Symptom Header
            Label symptomHeader = new Label("Symptoms");
            symptomHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            HBox sympLay = new HBox(symptomHeader);
            sympLay.setAlignment(Pos.TOP_CENTER);
            symptomBox.getChildren().addAll(sympLay,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,b1);


            //DIAGNOSIS Section
            //diagnosis Box
            VBox diagnosisBox = new VBox();
            diagnosisBox.setSpacing(10);
            diagnosisBox.setPadding(new Insets(20));
            diagnosisBox.setStyle("-fx-border-color: lightblue; -fx-border-width: 1px;");
            diagnosisBox.setMaxWidth(300);
            diagnosisBox.setMaxHeight(250);



            //Diagnose Button schematics
            b1.setPrefWidth(150);
            b1.setOnAction(e->{
                StringBuilder symptom = new StringBuilder();

                if (s1.isSelected()) {
                    symptom.append("Fever, ");
                }
                if (s2.isSelected()) {
                    symptom.append("Nausea, ");
                }
                if (s3.isSelected()) {
                    symptom.append("Joint Pain, ");
                }
                if (s4.isSelected()) {
                    symptom.append("Abdominal Pain, ");
                }
                if (s5.isSelected()) {
                    symptom.append("Cough, ");
                }
                if (s6.isSelected()) {
                    symptom.append("Sore Throat, ");
                }
                if (s7.isSelected()) {
                    symptom.append("Diarrhea, ");
                }
                if (s8.isSelected()) {
                    symptom.append("Rash, ");
                }
                if (s9.isSelected()) {
                    symptom.append("Swelling, ");
                }
                if (s10.isSelected()) {
                    symptom.append("Bruising, ");
                }
                symptoms = symptom.toString();

                //Removes trailing comma and space
                if (symptom.length() > 0) {
                    symptom.delete(symptom.length() - 2, symptom.length());

                    //HashSet to store unique diagnoses
                    HashSet<String> sympdiag = new HashSet<>();
                    String[] selectSymptom = symptom.toString().split(",");
                    for (String selectSymptoms : selectSymptom) {
                        String diagnosis = createDiagnosis(selectSymptoms.trim());
                        sympdiag.add(diagnosis);
                    }

                    if (sympdiag.isEmpty()) {
                        this.diagnosis = "Symptom not found";
                    }
                    else {
                        this.diagnosis = String.join("\n", sympdiag);
                    }
                }
                else {
                    this.diagnosis = "No symptoms selected";
                }

                //Clear symptom choices
                s1.setSelected(false);
                s2.setSelected(false);
                s3.setSelected(false);
                s4.setSelected(false);
                s5.setSelected(false);
                s6.setSelected(false);
                s7.setSelected(false);
                s8.setSelected(false);
                s9.setSelected(false);

                diagnosisTextArea.setText(diagnosis);

                System.out.println(diagnosis);
            });
            //Diagnosis Header
            Label diagnosisHeader = new Label("Diagnosis");
            diagnosisHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-underline: true;");
            HBox diagnosisLay = new HBox(diagnosisHeader);
            diagnosisLay.setAlignment(Pos.TOP_CENTER);

            //diagnosis TextArea
            diagnosisTextArea = new TextArea();
            diagnosisTextArea.setEditable(false);
            diagnosisTextArea.setWrapText(true);

            VBox B2lay = new VBox(b2);
            B2lay.setAlignment(Pos.BOTTOM_CENTER);

            diagnosisBox.getChildren().addAll(diagnosisLay, diagnosisTextArea,B2lay);

            //TREATMENT PLAN Section
            VBox treatBox = new VBox();
            treatBox.setSpacing(15);
            treatBox.setPadding(new Insets(10));
            treatBox.setStyle("-fx-border-color: lightblue; -fx-border-width: 1px;");
            treatBox.setMaxWidth(200);
            treatBox.setMaxHeight(250);

            //Treatment Plan Header
            Label treatHeader = new Label("TreatmentPlan");
            VBox treatLayout = new VBox(treatHeader);
            treatLayout.setAlignment(Pos.TOP_CENTER);

            treatHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

            //Treatment Plan TextArea
            treatTextArea = new TextArea();
            treatTextArea.setEditable(false);
            treatTextArea.setWrapText(true);
            treatTextArea.setMaxWidth(250);
            treatTextArea.setMaxHeight(200);

            treatBox.getChildren().addAll(treatLayout, treatTextArea);

            b2.setPrefWidth(150);
            b2.setOnAction(e-> {
                String diagnosisText = diagnosisTextArea.getText();
                if (!diagnosisText.isEmpty()) {
                    List<String> treatmentPlan  = createTreatmentPlan(diagnosisText);
                    String formatTreatmentPlan = String.join("",treatmentPlan);
                    treatTextArea.setText(formatTreatmentPlan);
                }
                else {
                    treatTextArea.setText("");
                }
                diagnosisTextArea.setText("");
            });

            //Patient Data Entry

            //Labels
            Label firstName = new Label("First Name: ");
            Label lastName = new Label("Last Name: ");
            VBox nameBox = new VBox();

            //First Name Area
            TextArea FnameTextArea = new TextArea();
            FnameTextArea.setPrefColumnCount(5);
            FnameTextArea.setPrefRowCount(1);
            HBox firstNameBox = new HBox(firstName,FnameTextArea);
            firstNameBox.setAlignment(Pos.CENTER_LEFT);

            //Last Name Area
            TextArea LnameTextArea = new TextArea();
            LnameTextArea.setPrefColumnCount(5);
            LnameTextArea.setPrefRowCount(1);
            HBox lastNameBox = new HBox(lastName,LnameTextArea);
            lastNameBox.setAlignment(Pos.CENTER_LEFT);
            
            //Patient Name Button
            b6 = new Button("Enter Name");
            b6.setStyle(buttonStyle);
            b6.setOnAction(e-> {
                String first = FnameTextArea.getText().trim();
                String last = LnameTextArea.getText().trim();
                String patName = first + " " + last;
                displayPatientHistory(patName,patientText);
            });

            VBox saveNameBox = new VBox(b6);
            nameBox.getChildren().addAll(firstNameBox,lastNameBox,saveNameBox);
            nameBox.setMaxWidth(150);
            nameBox.setMaxHeight(150);
            nameBox.setSpacing(3);



            //Save and Exit Button
            b5.setOnAction(e-> {
                String firstN = FnameTextArea.getText().trim();
                String lastN = LnameTextArea.getText().trim();
                String patientsName = firstN + " " + lastN;

                saveInfo(patientsName, symptoms, diagnosis, subjectText.getText(), messageText.getText(), treatTextArea.getText());
                primaryStage.close();
            });
           
            //Save and Exit Button
            VBox saveExitBox = new VBox();
            saveExitBox.setAlignment(Pos.BOTTOM_RIGHT);
            saveExitBox.setSpacing(10);
            saveExitBox.setPadding(new Insets(30));
            saveExitBox.getChildren().add(b5);

            //Patient Portal Messages
            Button patientMessageButton = new Button("Patient Portal Messages");
            patientMessageButton.setStyle("-fx-font-size: 14px;-fx-background-color: lightblue;");
            patientMessageButton.setOnAction(e-> {
            	messageWho();
            });
            
            VBox patientMessageBox = new VBox(patientMessageButton);
            
            

            //Main Layout
            GridPane mainLayout = new GridPane();
            mainLayout.setPadding(new Insets(10));
            //mainLayout.setAlignment(Pos.TOP_CENTER);
            mainLayout.setHgap(5);
            mainLayout.setVgap(5);


            mainLayout.add(patientMessageBox,18,13);
            mainLayout.add(symptomBox, 0, 10);
            mainLayout.add(diagnosisBox,10,10);
            mainLayout.add(treatBox, 18, 8);
            mainLayout.add(patientBox, 10, 8);
            mainLayout.add(saveExitBox, 18, 20);
            mainLayout.add(helloLay, 0, 5);
            mainLayout.add(nameBox, 0, 8);
            Scene scene = new Scene(mainLayout, 800, 900);

            // Set the stage
            primaryStage.setTitle("Doctor's View");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //Creates Diagnosis based on symptoms chosen
    private String createDiagnosis(String symptom) {
        String diagnosis = "";

        Set<String> d1 = new HashSet<>();
        d1.add("Viral Infection");

        Set<String> d2 = new HashSet<>();
        d2.add("Common Cold");

        Set<String> d3 = new HashSet<>();
        d3.add("Food Poisioning");

        Set<String> d4 = new HashSet<>();
        d4.add("Sprain");

        Set<String> d5 = new HashSet<>();
        d5.add("Gastrointestinal Issues");

        if (symptom.contains("Fever") || symptom.contains("Nausea")) {
            diagnosis = d1.iterator().next();
        } else if (symptom.contains("Cough") || symptom.contains("Sore Throat") || symptom.contains("Rash")) {
            diagnosis = d2.iterator().next();
        } else if (symptom.contains("Diarrhea")) {
            diagnosis = d3.iterator().next();
        } else if (symptom.contains("Swelling") || symptom.contains("Joint Pain")|| symptom.contains("Bruising")) {
            diagnosis = d4.iterator().next();
        } else if (symptom.contains("Abdominal Pain")) {
            diagnosis = d5.iterator().next();
        }

        return diagnosis;
    }
    //Creates Treatment Plan based on diagnosis
    private List<String> createTreatmentPlan(String diagnosis) {
        List<String> treatmentPlan = new ArrayList<>();

        if (diagnosis.contains("Viral Infection")) {
            treatmentPlan.add("\n- Rest and Hydration\n- Antiviral Medications\n");
        }
        if (diagnosis.contains("Common Cold")) {
            treatmentPlan.add("\n- Rest and Hydration\n- Over-The-Counter Medications\n");
        }
        if (diagnosis.contains("Food Poisoning")) {
            treatmentPlan.add("\n- Hydration and Electrolyte Replacement\n- Light and Bland Diet\n");
        }
        if (diagnosis.contains("Sprain")) {
            treatmentPlan.add("\n- R.I.C.E Method\n- Pain Reliever\n");
        }
        if (diagnosis.contains("Gastrointestinal Issues")) {
            treatmentPlan.add("\n- Low-FODMAP Diet\n- Proton Pump Inhibitors\n");
        }

        return treatmentPlan;
    }

    private static void saveInfo(String patientName, String symptoms, String diagnosis,String subject, String message, String treatmentPlan){
//        String [] name = patientName.split(" ");
//        String firstName = name[0];
//        String lastName = name[1];
        String fileName = patientName + "_visit.txt";

        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName,true);
//            fw.write("Patient Name: " + patientName + "\n");
            fw.write("\nSymptoms: " + symptoms + "\n");
            fw.write("Diagnosis: " + diagnosis + "\n");
            fw.write("Treatment Plan: " + treatmentPlan + "\n");
//            fw.write("Subject: " + subject + "\n" + message + "\n");
            fw.flush();
            System.out.println("Information saved successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while saving the information: " + e.getMessage());
        }
        finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.err.println("An error occurred while closing the file: " + e.getMessage());
                }
            }
        }
    }
    private void displayPatientHistory(String name, TextArea patientTextBox) {
        try {
            String fileName = name + "_visit.txt";
            File patientHistory = new File(fileName);

            if (patientHistory.exists()) {
                Scanner scnr = new Scanner(patientHistory);
                StringBuilder ph = new StringBuilder();

                while (scnr.hasNextLine()) {
                    String line = scnr.nextLine();
                    ph.append(line).append("\n");
                }
                patientTextBox.setText(ph.toString());
                scnr.close();
            }
            else {
                ErrorMessage("Patient history not found");
            }
        } catch (FileNotFoundException e) {
            ErrorMessage("An error has occured while loading the patient history: " + e.getMessage());
        }
    }
    private void messageWho() {
        // Popup page asking which patient to message
        Stage whichPatientStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20));

        whichPatientStage.setTitle("Doctor: Patient Portal Message");
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
            String name = patientNameTextField.getText();
            Path path = Paths.get(name+"_messages.txt");
            if(!Files.exists(path)) {
                ErrorMessage("Invalid patient.\n");
            } else {
                messagePatient(name);
                whichPatientStage.close();
            }
        });
    }    
    private void messagePatient(String name) {
        // go to patient portal message page
        Stage messagingStage = new Stage();
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(20));
        
        
        messagingStage.setTitle("Doctor: Patient Portal Message");
        Label patientNameLabel = new Label("Patient: " + name);
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

            Scene scene = new Scene(mainPane, 300, 200);
            messagingStage.setScene(scene);
            messagingStage.show();

        } catch (FileNotFoundException e) {
            ErrorMessage("No patient with the \nfollowing name found.");
//            e.printStackTrace();
        }
        
        mainPane.setTop(patientNameLabel);
        mainPane.setCenter(messageTextArea);
        mainPane.setBottom(sendMessageButton);

        Scene scene = new Scene(mainPane, 300, 200);
        messagingStage.setScene(scene);
        messagingStage.show();

        sendMessageButton.setOnAction(event-> {
            String message = messageTextArea.getText();
            updateMessageFile(name, message);
            messagingStage.close();
        });
    }
    
    private void updateMessageFile(String name, String message) {
    	try {
            FileWriter fw = new FileWriter(name + "_messages.txt", true);
            fw.write(message + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
