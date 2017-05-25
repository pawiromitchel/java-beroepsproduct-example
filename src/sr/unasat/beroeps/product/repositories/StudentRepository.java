package sr.unasat.beroeps.product.repositories;

import sr.unasat.beroeps.product.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private Connection connect;

    public StudentRepository() {
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

    public List<Student> selectAll() {

        List<Student> outputList = new ArrayList();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Construeer een statement voor het uitvoeren van een SQL Query
            statement = connect.createStatement();
            // Voer de SQL statement uit en verzamel de output in de resultset
            resultSet = statement.executeQuery("select * from studenten.student");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String naam = resultSet.getString("naam");
                String adres = resultSet.getString("adres");
                String studierichting = resultSet.getString("studierichting");
                int leeftijd = resultSet.getInt("leeftijd");
                double cijfergemiddelde = resultSet.getDouble("cijfergemiddelde");

                // Maak een student instantie en print deze instantie
                //maak een constructor van Student
                Student student = new Student(id, naam, adres, studierichting, leeftijd, cijfergemiddelde);

                System.out.println(student);
                outputList.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Er is een SQL fout ontstaan tijdens de select * statement!");
        }

        return outputList;
    }

    public Student selectRecord(int recordId) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = new Student();

        try {
            // Statements allow to issue SQL queries to the database
            preparedStatement = connect.prepareStatement("select * from studenten.student where id = ?");
            // Result set get the result of the SQL query
            preparedStatement.setInt(1, recordId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getSTring(2);

                int id = resultSet.getInt("id");
                String naam = resultSet.getString("naam");
                String adres = resultSet.getString("adres");
                String studierichting = resultSet.getString("studierichting");
                int leeftijd = resultSet.getInt("leeftijd");
                double cijfergemiddelde = resultSet.getDouble("cijfergemiddelde");

                // Maak een student instantie en print deze instantie
                student.setId(id);
                student.setNaam(naam);
                student.setAdres(adres);
                student.setLeeftijd(leeftijd);
                student.setStudierichting(studierichting);
                student.setCijfergemiddelde(cijfergemiddelde);

                System.out.println(student);
            }
        } catch (SQLException e) {
            System.out.println("Er is een SQL fout ontstaan tijdens de select statement!");
        }

        return student;
    }

    public int updateRecord(Student student) {

        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            preparedStatement = connect.prepareStatement("update student set " +
                    " cijfergemiddelde = ?," +
                    " naam = ?, " +
                    " adres = ?, " +
                    " leeftijd = ?, " +
                    " studierichting = ? " +
                    " where id = ?");
            preparedStatement.setDouble(1, student.getCijfergemiddelde());
            preparedStatement.setString(2, student.getNaam());
            preparedStatement.setString(3, student.getAdres());
            preparedStatement.setInt(4, student.getLeeftijd());
            preparedStatement.setString(5, student.getStudierichting());
            preparedStatement.setInt(6, student.getId());

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
            preparedStatement = connect.prepareStatement("delete from student where id = ?");
            preparedStatement.setInt(1, recordId);

            // Voer de statement uit en haal het resultaat op
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Er is een SQL fout tijdens deleten van een record!");
            e.printStackTrace();
        }

        return result;
    }

    public int insertRecord(Student student) {

        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            preparedStatement = connect.prepareStatement("insert into student values (NULL, ?,?,?,?,?)");
            preparedStatement.setString(1, student.getNaam());
            preparedStatement.setString(2, student.getAdres());
            preparedStatement.setString(3, student.getStudierichting());
            preparedStatement.setInt(4, student.getLeeftijd());
            preparedStatement.setDouble(5, student.getCijfergemiddelde());

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
