package sr.unasat.beroeps.product.app;

import sr.unasat.beroeps.product.entities.Rooster;
import sr.unasat.beroeps.product.entities.Student;
import sr.unasat.beroeps.product.entities.Vak;
import sr.unasat.beroeps.product.repositories.RoosterRepository;
import sr.unasat.beroeps.product.repositories.StudentRepository;
import sr.unasat.beroeps.product.repositories.VakRepository;

/**
 * Created by mitchel on 5/18/17.
 */
public class App {
    public static void main(String[] args) {

//		VakRepository vakRepository = new VakRepository();
//		Vak vak = vakRepository.selectRecord(1);
//		vak.setStudiePunten(5);
//		System.out.println(vakRepository.updateRecord(vak));

        StudentRepository studentRepository = new StudentRepository();
        Student student = studentRepository.selectRecord(1);

        VakRepository vakRepository = new VakRepository();
        Vak vak = vakRepository.selectRecord(1);

        Rooster rooster = new Rooster(1, student, vak, "maandag", "11u", "12u");
        RoosterRepository roosterRepository = new RoosterRepository();
        System.out.println(roosterRepository.insertRecord(rooster));

        // login
        // schermen voor nieuwe gebruikers, editen van gebruikers, verwijderen
        //
    }
}
