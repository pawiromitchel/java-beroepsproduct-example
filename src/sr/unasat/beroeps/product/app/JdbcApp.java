package sr.unasat.beroeps.product.app;

import sr.unasat.beroeps.product.entities.Student;
import sr.unasat.beroeps.product.entities.Vak;
import sr.unasat.beroeps.product.repositories.StudentRepository;
import sr.unasat.beroeps.product.repositories.VakRepository;

import java.util.Scanner;

public class JdbcApp {

	private Scanner gebruikersInput;
	private StudentRepository studentRepo = null;

	public static void main(String[] args) {

		// Maak een instantie van de sr.unasat.beroeps.product.app.JdbcApp class zodat je toegang hebt tot alle methoden
		JdbcApp databaseApplicatie = new JdbcApp();

		databaseApplicatie.startApp(databaseApplicatie);

	}

	private void startApp(JdbcApp databaseApplicatie) {

		// Er is een variabele nodig om te stoppen met de applicatie
		boolean stoppen = false;
		// Er is een selectie variabele nodig om de selectie van de gebruiker te onthouden
		int selectie = 0;
		// De Scanner instantie is nodig om system input te lezen.
		gebruikersInput = new Scanner(System.in);


//		VakRepository vakRepository = new VakRepository();
//		Vak vak = vakRepository.selectRecord(1);
//		vak.setStudiePunten(5);
//		System.out.println(vakRepository.updateRecord(vak));


		while(stoppen == false){
			// Print het menu
			databaseApplicatie.printMenu();

			// Wacht tot de gebruiker een selectie heeft gemaakt
			System.out.print("Selectie : ");
			selectie = gebruikersInput.nextInt(); // getting a String value

			switch(selectie){
			case 0:
				// Stop de applicatie door de while loop te stoppen
				stoppen = true;
				databaseApplicatie.stoppen();
				break;
			case 1:
				databaseApplicatie.doeFullSelectStatement();
				break;
			case 2:
				databaseApplicatie.doeSelectStatement();
				break;
			case 3:
				databaseApplicatie.doeInsertStatement();
				break;
			case 4:
				databaseApplicatie.doeDeleteStatement();
				break;
			case 5:
				databaseApplicatie.doeUpdateStatement();
				break;
			default:
			}
		}
	}

	private void printMenu() {
		System.out.println("................................");
		System.out.println("Mijn eerste Database Applicatie.");
		System.out.println("Type 0 om te stoppen.");
		System.out.println("Type 1 voor full select statement.");
		System.out.println("Type 2 voor select statement.");
		System.out.println("Type 3 voor insert statement.");
		System.out.println("Type 4 voor delete statement.");
		System.out.println("Type 5 voor update statement.");
		System.out.println("................................");
	}

	private void doeFullSelectStatement() {

		// sr.unasat.beroeps.product.entities.Student database instantie is alleen 1 keer nodig
		if (studentRepo == null) {
			studentRepo = new StudentRepository();
		}

		// Initialiseer de studentRepo alleen als het moet
		if (!studentRepo.isInitialised()) {
			studentRepo.initialize();
		}

		// Alleen als de studenten database is geinitialiseerd dan verder
		if (studentRepo.isInitialised()){
			studentRepo.selectAll();
		}
	}

	private void doeSelectStatement() {

		System.out.print("Tik id in van record : ");
		int record = gebruikersInput.nextInt(); // getting a int value

		// sr.unasat.beroeps.product.entities.Student database instantie is alleen 1 keer nodig
		if (studentRepo == null){
			studentRepo = new StudentRepository();
		}

		// Initialiseer de studentRepo alleen als het moet
		if (!studentRepo.isInitialised()){
			studentRepo.initialize();
		}

		// Alleen als de studenten database is geinitialiseerd dan verder
		if (studentRepo.isInitialised()){
			studentRepo.selectRecord(record);
		}
	}

	private void doeInsertStatement() {

		Student student = new Student();

		System.out.print("Tik de waarde van 'naam' : ");
		String naam = gebruikersInput.next();
		student.setNaam(naam);

		System.out.print("Tik de waarde van 'adres' : ");
		String adres = gebruikersInput.next();
		student.setAdres(adres);

		System.out.print("Tik de waarde van 'studierichting' : ");
		String studierichting = gebruikersInput.next();
		student.setStudierichting(studierichting);

		System.out.print("Tik de waarde van 'leeftijd' : ");
		int leeftijd = gebruikersInput.nextInt();
		student.setLeeftijd(leeftijd);

		System.out.print("Tik de waarde van 'cijfergemiddelde' : ");
		double cijfergemiddelde = gebruikersInput.nextDouble();
		student.setCijfergemiddelde(cijfergemiddelde);

		// sr.unasat.beroeps.product.entities.Student database instantie is alleen 1 keer nodig
		if (studentRepo == null){
			studentRepo = new StudentRepository();
		}

		// Initialiseer de studentRepo alleen als het moet
		if (!studentRepo.isInitialised()){
			studentRepo.initialize();
		}

		// Alleen als de studenten database is geinitialiseerd dan verder
		if (studentRepo.isInitialised()){
			studentRepo.insertRecord(student);
		}

	}

	private void doeDeleteStatement() {

		System.out.print("Tik id in van record : ");
		int record = gebruikersInput.nextInt(); // getting a int value

		// sr.unasat.beroeps.product.entities.Student database instantie is alleen 1 keer nodig
		if (studentRepo == null){
			studentRepo = new StudentRepository();
		}

		// Initialiseer de studentRepo alleen als het moet
		if (!studentRepo.isInitialised()){
			studentRepo.initialize();
		}

		// Alleen als de studenten database is geinitialiseerd dan verder
		if (studentRepo.isInitialised()){
			studentRepo.deleteRecord(record);
		}
	}

	private void doeUpdateStatement() {

		System.out.print("Tik id in van record : ");
		int record = gebruikersInput.nextInt(); // getting a int value
		Student student = null;

		// Haal de sr.unasat.beroeps.product.entities.Student op
		// sr.unasat.beroeps.product.entities.Student database instantie is alleen 1 keer nodig
		if (studentRepo == null){
			studentRepo = new StudentRepository();
		}

		// Initialiseer de studentRepo alleen als het moet
		if (!studentRepo.isInitialised()){
			studentRepo.initialize();
		}

		// Alleen als de studenten database is geinitialiseerd dan verder
		if (studentRepo.isInitialised()){
			student = studentRepo.selectRecord(record);
		}

		if (student != null){
			System.out.println("Type 1 voor naam.");
			System.out.println("Type 2 voor adres.");
			System.out.println("Type 3 voor leeftijd.");
			System.out.println("Type 4 voor studierichting.");
			System.out.println("Type 5 voor cijfergemiddelde.");
			System.out.print("Selectie : ");
			int column = gebruikersInput.nextInt(); // getting a int value

			switch(column){
			case 1:
				System.out.print("Tik de nieuwe waarde van 'naam' : ");
				String naam = gebruikersInput.next();
				student.setNaam(naam);
				break;
			case 2:
				System.out.print("Tik de nieuwe waarde van 'adres' : ");
				String adres = gebruikersInput.next();
				student.setAdres(adres);
				break;
			case 3:
				System.out.print("Tik de nieuwe waarde van 'leeftijd' : ");
				int leeftijd = gebruikersInput.nextInt();
				student.setLeeftijd(leeftijd);
				break;
			case 4:
				System.out.print("Tik de nieuwe waarde van 'studierichting' : ");
				String studierichting = gebruikersInput.next();
				student.setStudierichting(studierichting);
				break;
			case 5:
				System.out.print("Tik de nieuwe waarde van 'cijfergemiddelde' : ");
				double cijfergemiddelde = gebruikersInput.nextDouble();
				student.setCijfergemiddelde(cijfergemiddelde);
				break;
			default:
			}

			// Alleen als de studenten database is geinitialiseerd dan verder
			if (studentRepo.isInitialised()){
				studentRepo.updateRecord(student);
			}
		}
	}

	private void stoppen() {
		if (studentRepo != null){
			if (studentRepo.isInitialised()){
				studentRepo.terminate();
			}
		}
	}
}
