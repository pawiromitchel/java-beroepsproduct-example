package sr.unasat.beroeps.product.repositories;

import sr.unasat.beroeps.product.entities.Student;
import sr.unasat.beroeps.product.entities.Vak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnarain on 5/17/2017.
 */
public class VakRepository {
    private Connection connect;

    public VakRepository() {
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

    public List<Vak> selectAll() {

        List<Vak> outputList = new ArrayList();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Construeer een statement voor het uitvoeren van een SQL Query
            statement = connect.createStatement();
            // Voer de SQL statement uit en verzamel de output in de resultset
            resultSet = statement.executeQuery("select * from studenten.vak");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String vakNaam = resultSet.getString("vakNaam");
                boolean tentamenGemaakt = resultSet.getBoolean("tentamenGemaakt");
                String docent = resultSet.getString("docent");
                int studiepunten = resultSet.getInt("studiePunten");

                // Maak een student instantie en print deze instantie
                //maak een constructor van Student
                Vak vak = new Vak(id, vakNaam, tentamenGemaakt, docent, studiepunten);

                System.out.println(vak);
                outputList.add(vak);
            }
        } catch (SQLException e) {
            System.out.println("Er is een SQL fout ontstaan tijdens de select * statement!");
        }

        return outputList;
    }
    public Vak selectRecord(int recordId) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Vak vak = new Vak();

        try {
            // Statements allow to issue SQL queries to the database
            preparedStatement = connect.prepareStatement("select * from studenten.vak where id = ?");
            // Result set get the result of the SQL query
            preparedStatement.setInt(1, recordId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getSTring(2);

                int id = resultSet.getInt("id");
                String vakNaam = resultSet.getString("vakNaam");
                boolean tentamenGemaakt = resultSet.getBoolean("tentamenGemaakt");
                String docent = resultSet.getString("docent");
                int studiepunten = resultSet.getInt("studiePunten");

                // Maak een student instantie en print deze instantie
                vak.setId(id);
                vak.setVakNaam(vakNaam);
                vak.setTentamenGemaakt(tentamenGemaakt);
                vak.setDocent(docent);
                vak.setStudiePunten(studiepunten);

                System.out.println(vak);
            }
        } catch (SQLException e) {
            System.out.println("Er is een SQL fout ontstaan tijdens de select statement!");
        }

        return vak;
    }
    public int updateRecord(Vak vak) {

        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            preparedStatement = connect.prepareStatement("update vak set " +
                    " tentamenGemaakt = ?," +
                    " vakNaam = ?, " +
                    " studiepunten = ?," +
                    " docent = ? " +
                    " where id = ?");
            preparedStatement.setBoolean(1, vak.isTentamenGemaakt());
            preparedStatement.setString(2, vak.getVakNaam());
            preparedStatement.setString(3, vak.getDocent());
            preparedStatement.setInt(4, vak.getStudiePunten());
            preparedStatement.setInt(5, vak.getId());

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
            preparedStatement = connect.prepareStatement("delete from vak where id = ?");
            preparedStatement.setInt(1, recordId);

            // Voer de statement uit en haal het resultaat op
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Er is een SQL fout tijdens deleten van een record!");
            e.printStackTrace();
        }

        return result;
    }

    public int insertRecord(Vak vak) {

        PreparedStatement preparedStatement = null;
        int result = 0;

        try {
            preparedStatement = connect.prepareStatement("insert into vak values (NULL, ?,?,?,?,?)");
            preparedStatement.setString(1, vak.getVakNaam());
            preparedStatement.setBoolean(2, vak.isTentamenGemaakt());
            preparedStatement.setString(3, vak.getDocent());
            preparedStatement.setInt(4,vak.getStudiePunten());
            preparedStatement.setInt(5, vak.getId());

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
