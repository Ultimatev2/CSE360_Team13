package application;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.Alert.AlertType;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class DoctorView extends Application {

	private String diagnosis = "";
 	private TextArea diagnosisTextArea;
 	private TextArea treatTextArea;
 	private TextArea messageText;
 	private TextArea subjectText;
 	
 	private CheckBox s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
 	private Button b1,b2,b3,b4,b5,b6;

 	
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
		     b3 = new Button("Send Message");
		     b4 = new Button("Cancel Message");
		     b5 = new Button("Save and Exit");
		     
	         String buttonStyle = "-fx-background-color: lightblue;";
	         b1.setStyle(buttonStyle);
	         b2.setStyle(buttonStyle);
	         b3.setStyle(buttonStyle);
	         b4.setStyle(buttonStyle);
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
		    		diagnosis = "Symptom not found";
		    	} 
		    	else {
		    		diagnosis = String.join("\n", sympdiag);
		    	}
		    	 } 
		    	 else {
		    		 diagnosis = "No symptoms selected";
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
		     

		     
		     //SEND MESSAGE SECTION
		     VBox messageBox = new VBox();
		     messageBox.setSpacing(10);
		     messageBox.setPadding(new Insets(10));
		     messageBox.setStyle("-fx-border-color: lightblue; -fx-border-width: 1px;");
		     messageBox.setMaxWidth(400);
		     messageBox.setMaxHeight(300);
		     
		     //Send Message Header
		     Label messageHeader = new Label("Send Message");
		     messageHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-underline: true;");
		     HBox messageLay = new HBox(messageHeader);
		     messageLay.setAlignment(Pos.TOP_CENTER);
		     
		     
		     //Recipient Area
		     Label recepientLabel = new Label("To:            ");
		     TextArea recepientText = new TextArea();
		     recepientText.setPrefRowCount(1);
		     recepientText.setMaxWidth(300);
		     HBox recepientBox = new HBox(recepientLabel, recepientText);
		     recepientText.setPrefRowCount(1);
		     
		     //Subject Area
		     Label subjectLabel = new Label("Subject:    ");
		     subjectText = new TextArea();
		     subjectText.setPrefRowCount(1);
		     subjectText.setMaxWidth(300);
		     HBox subjectBox = new HBox(subjectLabel,subjectText);
		     
		     //Message Area
		     Label messageLabel = new Label("Message: ");
		     messageText = new TextArea();
		     messageText.setPrefColumnCount(50);
		     messageText.setPrefRowCount(5);
		     messageText.setWrapText(true);
		     messageText.setMaxWidth(300);
		     HBox messageTextBox = new HBox(messageLabel,messageText);
		     
		     messageTextBox.setSpacing(5);

		     
		     
		     //Buttons
		     HBox buttonBox = new HBox(b4,b3);
		     buttonBox.setSpacing(150);
		     
		     
		     //Cancel Message
		     b4.setOnAction(e-> {
		    	 recepientText.clear();
		    	 subjectText.clear();
		    	 messageText.clear();
		     });
		     
		     //Send Message
		     b3.setOnAction(e-> {
		    	 Alert alert = new Alert(AlertType.INFORMATION);
		    	 alert.setTitle("Message Sent");
		     	alert.setHeaderText(null);
		     	alert.setContentText("Message Sent!");
		     	alert.showAndWait();
		     	
		     	recepientText.clear();
		     	subjectText.clear();
		     	messageText.clear();
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
		     b6 = new Button("Save Name");
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
		    	 
		    	 saveInfo(patientsName,diagnosisTextArea.getText(), diagnosis, subjectText.getText(), messageText.getText(), treatTextArea.getText());
		    	 primaryStage.close();
		     });
		     
		     messageBox.getChildren().addAll(messageLay,recepientBox,subjectBox,messageTextBox,buttonBox);
		     //Save and Exit Button
		     VBox saveExitBox = new VBox();
		     saveExitBox.setAlignment(Pos.BOTTOM_RIGHT);
		     saveExitBox.setSpacing(10);
		     saveExitBox.setPadding(new Insets(30));
		     saveExitBox.getChildren().add(b5);
		     
		     
		      //Main Layout  
		      GridPane mainLayout = new GridPane();
		      mainLayout.setPadding(new Insets(10));			      
		      //mainLayout.setAlignment(Pos.TOP_CENTER);
		      mainLayout.setHgap(5);
		      mainLayout.setVgap(5);
		      
		      
		      mainLayout.add(messageBox,10,10);
		      mainLayout.add(symptomBox, 0, 10);
		      mainLayout.add(diagnosisBox,18,10);			      
		      mainLayout.add(treatBox, 18, 8);
		      mainLayout.add(patientBox, 10, 8);
		      mainLayout.add(saveExitBox, 18, 20);
		      mainLayout.add(helloLay, 0, 5);
		      mainLayout.add(nameBox, 0, 8);
		      Scene scene = new Scene(mainLayout, 1000, 1000);

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
	        treatmentPlan.add("Viral Infection:\n"
	                + "- Rest and Hydration\n"
	                + "- Antiviral Medications\n");
	    }
	    if (diagnosis.contains("Common Cold")) {
	        treatmentPlan.add("Common Cold:\n"
	                + "- Rest and Hydration\n"
	                + "- Over-The-Counter Medications\n");
	    }
	    if (diagnosis.contains("Food Poisoning")) {
	        treatmentPlan.add("Food Poisoning:\n"
	                + "- Hydration and Electrolyte Replacement\n"
	                + "- Light and Bland Diet\n");
	    }
	    if (diagnosis.contains("Sprain")) {
	        treatmentPlan.add("Sprain:\n"
	                + "- R.I.C.E Method\n"
	                + "- Pain Reliever\n");
	    }
	    if (diagnosis.contains("Gastrointestinal Issues")) {
	        treatmentPlan.add("Gastrointestinal Issues:\n"
	                + "- Low-FODMAP Diet\n"
	                + "- Proton Pump Inhibitors\n");
	    }
    
	return treatmentPlan;
}

private static void saveInfo(String patientName, String symptoms, String diagnosis,String subject, String message, String treatmentPlan){
	String [] name = patientName.split(" ");
	String firstName = name[0];
	String lastName = name[1];
	String fileName = patientName + "_visit.txt";
	
	FileWriter fw = null;
	try {
		fw = new FileWriter(fileName,true);
        fw.write("\nSymptoms: " + symptom + "\n");
        fw.write("Diagnosis: " + diagnosis + "\n");
        fw.write("Treatment Plan: " + treatmentPlan + "\n");
        fw.write("Subject: " + subject + "\n" + message + "\n");
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
private void ErrorMessage(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
}

