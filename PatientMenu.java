package application;

import java.io.*;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientMenu extends Application
{
	public Patient[] patients = new Patient[5];
	private int usercount = 0;
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		Stage stage = new Stage();
		StackPane pane = new StackPane();
		stage.setTitle("Patient Portal");
		Text title = new Text("Patient Portal Log In");
		pane.getChildren().add(title);
		StackPane.setAlignment(title, Pos.TOP_CENTER);

		Text username = new Text("First Name:");
		pane.getChildren().add(username);
		StackPane.setAlignment(username, Pos.TOP_LEFT);
		username.setTranslateX(185);
		username.setTranslateY(50);

		Text password = new Text("Last Name:");
		pane.getChildren().add(password);
		StackPane.setAlignment(password,Pos.TOP_LEFT);
		password.setTranslateX(187);
		password.setTranslateY(75);

		TextField userinput = new TextField();
		pane.getChildren().add(userinput);
		StackPane.setAlignment(userinput, Pos.TOP_LEFT);
		userinput.setTranslateX(250);
		userinput.setTranslateY(50);
		userinput.setMinWidth(100);
		userinput.setMaxWidth(100);

		TextField passinput = new TextField();
		pane.getChildren().add(passinput);
		StackPane.setAlignment(passinput, Pos.TOP_LEFT);
		passinput.setTranslateX(250);
		passinput.setTranslateY(75);
		passinput.setMaxWidth(100);
		passinput.setMinWidth(100);

		Button logIn = new Button("Log In");
		logIn.setOnAction(event -> {
			try {
				loginAttempt(userinput.getText(), passinput.getText(), stage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		pane.getChildren().add(logIn);
		StackPane.setAlignment(logIn, Pos.TOP_LEFT);
		logIn.setTranslateX(270);
		logIn.setTranslateY(105);

		Button newUs = new Button("New User");
		newUs.setOnAction(event -> newUser(stage));
		pane.getChildren().add(newUs);
		StackPane.setAlignment(newUs, Pos.TOP_LEFT);
		newUs.setTranslateX(262);
		newUs.setTranslateY(135);
		Scene scene = new Scene(pane,600,400);
		stage.setScene(scene);
		stage.show();
	}
	public static void patientMenu(Patient patient, Stage stage) throws IOException
	{
		stage.close();
		Stage newStage = new Stage();
		String username = patient.firstName;
		String password = patient.lastName;
		stage.setTitle("Patient Portal");
		TabPane tabs = new TabPane();
		tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		ScrollPane pastVisits = new ScrollPane();
		ScrollPane pHistory = new ScrollPane();
		StackPane pHInfo = new StackPane();
		StackPane mDorM = new StackPane();
		StackPane accInf = new StackPane();
		Tab tab1 = new Tab();
		tab1.setText("Past Visits");
		Tab tab2 = new Tab();
		tab2.setText("Prescriptions \nand Immunizations");
//		Tab tab3 = new Tab();
//		tab3.setText("Pharmacy \nInformation");
		Tab tab4 = new Tab();
		tab4.setText("Message a \nDoctor or Nurse");
		Tab tab5 = new Tab();
		tab5.setText("Account \nInformation");


		//text for past visits (tab 1)
		StackPane hold = new StackPane();
		String fVisits = readVisits(username + " " + password);
		Text visits = new Text(fVisits);
		StackPane.setAlignment(visits,Pos.TOP_LEFT);
		hold.getChildren().add(visits);
		pastVisits.setContent(hold);
		tab1.setContent(pastVisits);

//		//Example text for prescription and immunization history (tab 2)
//
//		StackPane hold2 = new StackPane();
//		String[] fPrecs = readPrescriptions(username + " " + password);
//		Text[] prescriptions = new Text[fVisits.length];
//		for(int i = 0; i < fVisits.length; i++)
//		{
//			prescriptions[i] = new Text(fPrecs[i]);
//			prescriptions[i].setWrappingWidth(400);
//			hold2.getChildren().add(prescriptions[i]);
//			StackPane.setAlignment(prescriptions[i], Pos.TOP_LEFT);
//			prescriptions[i].setTranslateY(150*i);
//		}
//		pHistory.setContent(hold2);
//		tab2.setContent(pHistory);
//
//		//Pharmacy Information and edit function
//
//		Text pha1 = new Text(patient.pharma1);
//
//		Text pha2 = new Text(patient.pharma2);
//		pha1.setWrappingWidth(200);
//		pha2.setWrappingWidth(200);
//
//
//		Button edit1 = new Button("Edit");
//		edit1.setOnAction(event -> setPha1(pha1, patient));
//
//		Button edit2 = new Button("Edit");
//		edit1.setOnAction(event -> setPha2(pha2, patient));
//
//		pHInfo.getChildren().addAll(pha1,pha2,edit1,edit2);
//		StackPane.setAlignment(pha1, Pos.TOP_LEFT);
//		StackPane.setAlignment(pha2, Pos.TOP_LEFT);
//		StackPane.setAlignment(edit1, Pos.TOP_CENTER);
//		StackPane.setAlignment(edit2, Pos.TOP_CENTER);
//		pha2.setTranslateY(150);
//		edit2.setTranslateY(150);
//		tab3.setContent(pHInfo);

		//Messages for doctors or nurses

		String fMess = readMessages(username + " " + password);
		Text messages = new Text(fMess);
		visits.setWrappingWidth(200);
		StackPane.setAlignment(messages,Pos.TOP_LEFT);
		mDorM.getChildren().add(messages);
		

		Text to = new Text("To:");
		StackPane.setAlignment(to, Pos.TOP_LEFT);
		to.setTranslateX(250);

		Text subject = new Text("Subject:");
		StackPane.setAlignment(subject, Pos.TOP_LEFT);
		subject.setTranslateX(250);
		subject.setTranslateY(25);

		TextField tor = new TextField();
		StackPane.setAlignment(tor, Pos.TOP_LEFT);
		tor.setTranslateX(325);
		tor.setMaxWidth(100);
		tor.setMinWidth(100);

		TextField subr = new TextField();
		StackPane.setAlignment(subr, Pos.TOP_LEFT);
		subr.setTranslateX(325);
		subr.setTranslateY(25);
		subr.setMaxWidth(100);
		subr.setMinWidth(100);

		TextArea message = new TextArea();
		StackPane.setAlignment(message, Pos.TOP_LEFT);
		message.setTranslateX(325);
		message.setTranslateY(50);
		message.setMinWidth(200);
		message.setMaxWidth(200);
		message.setMinHeight(200);
		message.setMaxHeight(200);

		Button send = new Button("SEND");
		send.setOnAction(event -> {
			try {
				writeMessage(subject.getText(), message.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		StackPane.setAlignment(send, Pos.TOP_LEFT);
		send.setTranslateX(400);
		send.setTranslateY(300);

		mDorM.getChildren().addAll(to,tor,subject,subr,message,send);

		tab4.setContent(mDorM);

		//login information and edit button

		Text uname = new Text("First Name: " + username);
		accInf.getChildren().add(uname);
		StackPane.setAlignment(uname, Pos.TOP_LEFT);
		Text pword = new Text("Last Name: " + password);
		accInf.getChildren().add(pword);
		StackPane.setAlignment(pword, Pos.TOP_LEFT);
		pword.setTranslateY(25);

		Button uedit = new Button("Edit");
		accInf.getChildren().add(uedit);
		StackPane.setAlignment(uedit, Pos.TOP_LEFT);
		uedit.setTranslateX(150);
		Button pedit = new Button("Edit");
		accInf.getChildren().add(pedit);
		StackPane.setAlignment(pedit, Pos.TOP_LEFT);
		pedit.setTranslateX(150);
		pedit.setTranslateY(25);

		Button signout = new Button("Sign Out");
		signout.setOnAction(e -> newStage.close());
		accInf.getChildren().add(signout);
		StackPane.setAlignment(signout, Pos.TOP_LEFT);
		signout.setTranslateX(50);
		signout.setTranslateY(70);

		tab5.setContent(accInf);


		//Scene creation

		tabs.getTabs().addAll(tab1,tab2,tab4,tab5);
		tabs.setTabMinWidth(104);
		tabs.setTabMaxWidth(104);
		tabs.setTabMinHeight(30);
		tabs.setTabMaxHeight(30);
		Scene scene = new Scene(tabs,600,400);
		newStage.setScene(scene);
		newStage.show();

	}
	private static void setPha2(Text pha2, Patient patient) {
		StackPane pane = new StackPane();
		Text text = new Text("Enter new Pharmacy");
		StackPane.setAlignment(text, Pos.TOP_CENTER);
		TextField answer = new TextField();
		Button save = new Button("Save");
		save.setOnAction(event -> patient.setPharma1("Primary: " + text.getText()));
		StackPane.setAlignment(save, Pos.BASELINE_CENTER);
		StackPane.setAlignment(answer, Pos.TOP_CENTER);
		answer.setMinWidth(200);
		answer.setMaxWidth(200);
		pane.getChildren().addAll(text, answer, save);
		Scene scene = new Scene(pane, 600, 400);
		Stage temp = new Stage();
		temp.setScene(scene);
		temp.show();

	}

	private static void setPha1(Text pha1, Patient patient) {
		StackPane pane = new StackPane();
		Text text = new Text("Enter new Pharmacy");
		StackPane.setAlignment(text, Pos.TOP_CENTER);
		TextField answer = new TextField();
		Button save = new Button("Save");
		save.setOnAction(event -> patient.setPharma1("Secondary: " + text.getText()));
		StackPane.setAlignment(save, Pos.BASELINE_CENTER);
		StackPane.setAlignment(answer, Pos.TOP_CENTER);
		answer.setMinWidth(200);
		answer.setMaxWidth(200);
		pane.getChildren().addAll(text, answer, save);
		Scene scene = new Scene(pane, 600, 400);
		Stage temp = new Stage();
		temp.setScene(scene);
		temp.show();

	}

	private void loginAttempt(String first, String last, Stage primStage) throws IOException
	{
		Stage stage = new Stage();
		StackPane pane = new StackPane();
		for(int i = 0; i < patients.length; i++)
		{
			if(patients[i] != null && patients[i].firstName.equals(first))
			{
				if(patients[i] != null && patients[i].lastName.equals(last))
				{
					primStage.close();
					patientMenu(patients[i], stage);
					return;
				}
				else
				{
					Text message = new Text("Incorrect First/Last Name");
					pane.getChildren().add(message);
					StackPane.setAlignment(message, Pos.CENTER);
					Scene scene = new Scene(pane,300,200);
					stage.setScene(scene);
					stage.show();
					return;
				}
			}
		}

		Text message = new Text("Incorrect First/Last Name");
		pane.getChildren().add(message);
		StackPane.setAlignment(message, Pos.CENTER);
		Scene scene = new Scene(pane,300,200);
		stage.setScene(scene);
		stage.show();
		return;
	}
	private void newUser(Stage primaryStage)
	{
		primaryStage.close();
		Stage stage = new Stage();
		StackPane pane = new StackPane();
		stage.setTitle("Patient Portal");
		Text title = new Text("New User");
		pane.getChildren().add(title);
		StackPane.setAlignment(title, Pos.TOP_CENTER);

		Text username = new Text("First Name:");
		pane.getChildren().add(username);
		StackPane.setAlignment(username, Pos.TOP_LEFT);
		username.setTranslateX(185);
		username.setTranslateY(50);

		Text password = new Text("Last Name:");
		pane.getChildren().add(password);
		StackPane.setAlignment(password,Pos.TOP_LEFT);
		password.setTranslateX(187);
		password.setTranslateY(75);

		TextField userinput = new TextField();
		pane.getChildren().add(userinput);
		StackPane.setAlignment(userinput, Pos.TOP_LEFT);
		userinput.setTranslateX(250);
		userinput.setTranslateY(50);
		userinput.setMinWidth(100);
		userinput.setMaxWidth(100);

		TextField passinput = new TextField();
		pane.getChildren().add(passinput);
		StackPane.setAlignment(passinput, Pos.TOP_LEFT);
		passinput.setTranslateX(250);
		passinput.setTranslateY(75);
		passinput.setMaxWidth(100);
		passinput.setMinWidth(100);

		Button logIn = new Button("Create Account");
		logIn.setOnAction(event -> newUser(userinput.getText(), passinput.getText(), stage));
		pane.getChildren().add(logIn);
		StackPane.setAlignment(logIn, Pos.TOP_LEFT);
		logIn.setTranslateX(270);
		logIn.setTranslateY(105);

		Scene scene = new Scene(pane,600,400);
		stage.setScene(scene);
		stage.show();
	}
	private void newUser(String user, String pass, Stage primaryStage)
	{
		primaryStage.close();
		Stage stage = new Stage();
		StackPane pane = new StackPane();
		String firstName = user;
		String lastName = pass;
		for(int i = 0; i < patients.length; i++)
		{
			if(patients[i] != null && patients[i].firstName.equals(firstName))
			{
				Text message = new Text("Username in use");
				pane.getChildren().add(message);
				StackPane.setAlignment(message, Pos.CENTER);
				Scene scene = new Scene(pane,300,200);
				stage.setScene(scene);
				stage.show();
				return;
			}
			if(patients[i] != null && patients[i].lastName.equals(lastName))
			{
				Text message = new Text("Password in use");
				pane.getChildren().add(message);
				StackPane.setAlignment(message, Pos.CENTER);
				Scene scene = new Scene(pane,300,200);
				stage.setScene(scene);
				stage.show();
				return;
			}
			else
			{
				if(patients.length == usercount)
				{
					Patient[] tempPat = new Patient[patients.length*2];
					for(int j = 0; j < usercount*2; j++)
					{
						tempPat[i] = patients[i];
					}
					patients = tempPat;
				}
				patients[usercount]= new Patient(firstName, lastName, null);
//				patients[usercount].firstName = firstName;
//				patients[usercount].lastName = lastName;
//				patients[usercount].dateOfBirth = null;
				usercount++;
				start(stage);
				return;
			}
		}
	}
	private static String readMessages(String name) throws IOException
	{
 		try {
            Scanner scanner = new Scanner(new File(name+"_messages.txt"));
            StringBuilder historyContent = new StringBuilder();
            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                historyContent.append(line).append("\n");
            
                String end = historyContent.toString();
                scanner.close();

                return end;
            } 
 		}
 		catch (FileNotFoundException e) 
 		{
        //    showErrorMessage("_visit.txt file not found.");
//            e.printStackTrace();
       }
		return null;
	}
	private static String readVisits(String name) throws IOException
	{
		 try {
            Scanner scanner = new Scanner(new File(name+"_visit.txt"));
            StringBuilder historyContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                historyContent.append(line).append("\n");
            }
            String end = historyContent.toString();
            scanner.close();
	    return end;
		 }

       catch (FileNotFoundException e) 
		 {
           // showErrorMessage("_visit.txt file not found.");
//            e.printStackTrace();
        }
		 return null;
	}
	//		private static String[] readPrescriptions(String name) throws IOException
//		{
//			String[] visits = new String[1];
//			String visitsFile = name + "_prescriptions.txt";
//			String temp;
//				BufferedReader fRead = new BufferedReader(new FileReader(visitsFile));
//				int i = 0;
//				int count = 0;
//				while(i != 2)
//				{
//					temp = fRead.readLine();
//					if(temp.equals(""))
//					{
//						i++;
//						if(i == 1)
//						{
//							count++;
//							String[] holder = new String[count+1];
//							for(int j = 0; j < count; j++)
//							{
//								holder[i] = visits[i];
//							}
//							visits = holder;
//						}
//					}
//					else
//					{
//						i = 0;
//						visits[count] = visits[count] + temp + "\n";
//					}
//				}
//				fRead.close();
//			return visits;
//	}
	private static void writeMessage(String recipient, String message) throws IOException
	{
		String fileName = recipient + "_messages.txt";

		BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
		br.write(message + "\n");

		br.close();
	}
}