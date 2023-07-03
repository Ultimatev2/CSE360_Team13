package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.*;

public class Main extends Application {
	private BorderPane homeRoot;
	private VBox vb;
	private Text text;
	private Button but1, but2, but3, option1, option2;
	private HBox hbox;
	private Scene homeScreen;
	private Stage window;
	private Nurse nurseView;
	private DoctorView doctorView;
	private PatientMenu patientPortal;

	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			createHomeScreen();
			createNurseView();
			createDoctorView();
			createPatientPortal();

			window.setScene(homeScreen);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createHomeScreen() {
		//Create BorderPane and set Style
		homeRoot = new BorderPane();
		homeRoot.setStyle("-fx-background-color : transparent;");
		
		//VBox for User Selection Buttons
		vb = new VBox(20);
		
		//Create top screen text
		text = new Text("Penguin Pediatrics");
		text.setFont(Font.font("",FontWeight.BOLD,36));
		
		//Create User Selection Buttons
		but1 = new Button("Nurse");
		but1.setPrefWidth(300);
		but1.setPrefHeight(50);
		but1.setFont(Font.font("",FontWeight.MEDIUM,20));
		but1.setOnAction(e -> nurseView.start(window));
		but2 = new Button("Doctor");
		but2.setPrefWidth(300);
		but2.setPrefHeight(50);
		but2.setFont(Font.font("",FontWeight.MEDIUM,20));
		
		but2.setOnAction(e -> {
			try {
				doctorView.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		but3 = new Button("Patient Portal");
		but3.setPrefWidth(300);
		but3.setPrefHeight(50);
		but3.setFont(Font.font("",FontWeight.MEDIUM,20));
		but3.setOnAction(e -> patientPortal.start(window));
		
		//Add Buttons to VBox
		vb.getChildren().addAll(but1,but2,but3);
		
		//Create HBox for options at Bottom of BorderPane
		hbox = new HBox(50);
		
		//Create Buttons for options
		option1 = new Button("Info");
		option1.setPrefWidth(150);
		option1.setPrefHeight(30);
		option2 = new Button("Exit");
		option2.setPrefWidth(150);
		option2.setPrefHeight(30);
		
		//Set "Exit" button to quit program
		option2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0)
			{
				System.out.println("User called exit\n");
				System.exit(1);
			}
		});
		
		//Add option buttons to HBox
		hbox.getChildren().addAll(option1,option2);
	 	hbox.setMargin(option1,new Insets(40));
		hbox.setMargin(option2,new Insets(40));
		
		//Arrange BorderPane
	//	homeRoot.getChildren().addAll(text,vb,hbox);
		homeRoot.setTop(text);
		homeRoot.setAlignment(text,Pos.TOP_CENTER);
		homeRoot.setCenter(vb);
		vb.setAlignment(Pos.CENTER);
		homeRoot.setBottom(hbox);
		hbox.setAlignment(Pos.CENTER);
		
		//Create Scene. Set BorderPane, screen size, and fill color
		homeScreen = new Scene(homeRoot,800,600,Color.BURLYWOOD);
	}
	
	private void createNurseView() 
	{
		nurseView = new Nurse();
	}
	
	private void createDoctorView()
	{
		doctorView = new DoctorView();
	}
	private void createPatientPortal()
	{
		patientPortal = new PatientMenu();
	}
	public void switchScenes(Scene scene) {
		window.setScene(scene);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
