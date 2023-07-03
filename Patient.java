package application;

public class Patient {
public String firstName;
public String lastName;
public String dateOfBirth;
public String pharma1;
public String pharma2;
public Patient(String fName, String lName, String dob)
{
	firstName = fName;
	lastName = lName;
	dateOfBirth = dob;
}
public void setPharma1(String newP1)
{
	pharma1 = newP1;
}
public void setPharma2(String newP2)
{
	pharma2 = newP2;
}
}