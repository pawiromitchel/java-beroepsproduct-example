package sr.unasat.beroeps.product.repositories;

import sr.unasat.beroeps.product.entities.Rooster;
import sr.unasat.beroeps.product.entities.Student;
import sr.unasat.beroeps.product.entities.Vak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitchel on 5/18/17.
 */
public class RoosterRepository {

    private Connection connect;

    public RoosterRepository() {
        initialize();
    }

    public void initialize() {

        try {

            // Onderstaande zoekt de juist class op uit de library en laad het in JVM
            Class.forName("com.mysql.jdbc.Driver");
            // De connectie wordt vervolgens gemaakt naar de database middels de juiste authenticatie
            connect = DriverManager.getConnection("jdbc:mysql://localhost/studenten?user=root&password=PAWIR0MITCHEL");

        } catch (ClassNotFoundException e) {
            System.out.println("De class is niet gevonden!");
        } catch (SQLException e) {
            System.out.println("Er is een SQL fout ontstaan!");
            e.printStackTrace();
        }
    }

    public boolean isInitialised() {
        if (connect != null) {
            return true;
        }
        return false;
    }

    public List<Rooster> selectAll() {

        List<Rooster> outputList = new ArrayList();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Construeer een statement voor het uitvoeren van een SQL Query
            statement = connect.createStatement();
            // Voer de SQL statement uit en verzamel de output in de resultset
            resultSet = statement.executeQuery("select * from studenten.rooster");

            while (resultSet.next()) {
                Rooster rooster = new Rooster();

                int id = resultSet.getInt("id");
                int student_id = resultSet.getInt("student_id");
                int vak_id = resultSet.getInt("vak_id");
                String dag = resultSet.getString("dag");
                String startTijd = resultSet.getString("starttijd");
                String endTijd = resultSet.getString("endtijd");

                // Maak een rooster instantie en print deze instantie
                rooster.setId(id);
                if (student_id > 0) {
                    rooster.setStudent(new StudentRepository().selectRecord(student_id));
                }
                if (vak_id > 0) {
                    rooster.setVak(new VakRepository().selectRecord(vak_id));
                }
                rooster.setDag(dag);
                rooster.setStartTijd(startTijd);
                rooster.setEndTijd(endTijd);

                System.out.println(rooster);
                outputList.add(rooster);
            }
        } catch (SQLException e) {
            System.out.println("Er is een SQL fout ontstaan tijdens de select * statement!");
        }

        return outputList;
    }

    public Rooster selectRecord(int recordId) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Rooster rooster = new Rooster();

        try {
            // Statements allow to issue SQL queries to the database
            preparedStatement = connect.prepareStatement("select * from studenten.rooster where id = ?");
            // Result set get the result of the SQL query
            preparedStatement.setInt(1, recordId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getSTring(2);

                int id = resultSet.getInt("id");
                int student_id = resultSet.getInt("student_id");
                int vak_id = resultSet.getInt("vak_id");
                String dag = resultSet.getString("dag");
                String startTijd = resultSet.getString("starttijd");
                String endTijd = resultSet.getString("endtijd");

                // Maak een rooster instantie en print deze instantie
                rooster.setId(id);
                if (student_id > 0) {
                    rooster.setStudent(new StudentRepository().selectRecord(student_id));
                }
                if (vak_id > 0) {
                    rooster.setVak(new VakRepository().selectRecord(vak_id));
                }
                rooster.setDag(dag);
                rooster.setStartTijd(startTijd);
                rooster.setEndTijd(endTijd);

                System.out.println(rooster);
            }
        } catch (SQLException e) {
            System.out.println("Er is een SQL fout ontstaan tijdens de select statement!");
        }

        return rooster;
    }

    public int updateRecord(Rooster rooster) {

        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            preparedStatement = connect.prepareStatement("update rooster set " +
                    " student_id = ?," +
                    " vak_id = ?, " +
                    " dag = ?, " +
                    " starttijd = ?, " +
                    " endtijd = ? " +
                    " where id = ?");
            if (rooster.getStudent() != null) {
                preparedStatement.setInt(1, rooster.getStudent().getId());
            } else {
                preparedStatement.setInt(1, 0);
            }

            if (rooster.getVak() != null) {
                preparedStatement.setInt(2, rooster.getVak().getId());
            } else {
                preparedStatement.setInt(2, 0);
            }

            preparedStatement.setString(3, rooster.getDag());
            preparedStatement.setString(4, rooster.getStartTijd());
            preparedStatement.setString(5, rooster.getEndTijd());

            // Voer de statement uit en haal het resultaat op
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Er is een SQL fout tijdens het updaten van een record!");
            e.printStackTrace();
        }

        return result;
    }

    public int deleteRecord(int recordId) {

        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            preparedStatement = connect.prepareStatement("delete from rooster where id = ?");
            preparedStatement.setInt(1, recordId);

            // Voer de statement uit en haal het resultaat op
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Er is een SQL fout tijdens deleten van een record!");
            e.printStackTrace();
        }

        return result;
    }

    public int insertRecord(Rooster rooster) {

        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            preparedStatement = connect.prepareStatement("insert into rooster values (NULL,?,?,?,?,?)");
            if(rooster.getStudent() != null){
                preparedStatement.setInt(1, rooster.getStudent().getId());
            } else {
                preparedStatement.setInt(1, 0);
            }

            if(rooster.getVak() != null){
                preparedStatement.setInt(2, rooster.getVak().getId());
            } else {
                preparedStatement.setInt(2, 0);
            }
            preparedStatement.setString(3, rooster.getDag());
            preparedStatement.setString(4, rooster.getStartTijd());
            preparedStatement.setString(5, rooster.getEndTijd());

            // Voer de statement uit en haal het resultaat op
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Er is een SQL fout tijdens het inserten van een nieuwe record!");
            e.printStackTrace();
        }

        return result;
    }

    public void terminate() {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            System.out.println("Er is een SQL fout tijdens sluiten van de connectie!");
            e.printStackTrace();
        }
    }
}
