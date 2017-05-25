package sr.unasat.beroeps.product.ui;

import sr.unasat.beroeps.product.entities.Student;
import sr.unasat.beroeps.product.repositories.StudentRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class StudentUI extends JPanel {

    private JButton selectAllButton;
    private JList outputList;
    private StudentRepository studentRepo;

    private DefaultListModel listModel;

    private DefaultTableModel listTableModel;
    private JTable outputTable;

    public StudentUI() {

        // Maak een instantie van de sr.unasat.beroeps.product.app.JdbcApp class zodat je toegang hebt tot alle methoden
        studentRepo = new StudentRepository();

        // Initialisatie selectAllButton.
        selectAllButton = new JButton("Select All");
        selectAllButton.setBackground(Color.green);
        selectAllButton.setPreferredSize(new Dimension(100, 40));

        //	Creeer buttonPanel met buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        // Voeg selectAllButton aan inputPanel
        buttonPanel.add(selectAllButton);
        add(buttonPanel, BorderLayout.LINE_START);

        //	Creeer outputPanel en voeg toe
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        add(outputPanel, BorderLayout.CENTER);

        listModel = new DefaultListModel();
        outputList = new JList(listModel);
        outputList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        outputList.setSelectedIndex(0);
        outputList.setVisibleRowCount(5);
        JScrollPane listPanel = new JScrollPane(outputList);
        listPanel.setPreferredSize(new Dimension(800, 150));
        outputPanel.add(listPanel);

        listTableModel = new DefaultTableModel();
        String[] colnames = {"id", "naam", "leeftijd", "adres", "studierichting", "cijfergemiddelde"};
        Vector colnamesV = new Vector(Arrays.asList(colnames));
        outputTable = new JTable(null, colnamesV);
        JScrollPane tablePanel = new JScrollPane(outputTable);
        tablePanel.setPreferredSize(new Dimension(800, 150));
        outputPanel.add(tablePanel);

        // Select listener
        selectAllButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg) {
                // Voer SelectAll functie uit.

                // sr.unasat.beroeps.product.entities.Student database instantie is alleen 1 keer nodig
                if (studentRepo == null) {
                    studentRepo = new StudentRepository();
                }

                // Initialiseer de studentRepo alleen als het moet
                if (!studentRepo.isInitialised()) {
                    studentRepo.initialize();
                }

                // Alleen als de studenten database is geinitialiseerd dan verder
                if (studentRepo.isInitialised()) {
                    List<Student> outputList = studentRepo.selectAll();

                    // Maak de lijst leeg als er elementen inzitten
                    listModel.removeAllElements();

                    // Parse de lijst
                    for (Student student : outputList) {
                        Student record = student;
                        listModel.addElement(record.toString());
                    }

                    listTableModel = (DefaultTableModel) outputTable.getModel();
                    listTableModel.setRowCount(0);
                    // Parse de table
                    for (Student student : outputList) {
                        Student record = student;
                        String[] colData = new String[6];
                        colData[0] = Integer.valueOf(record.getId()).toString();
                        colData[1] = record.getNaam();
                        colData[2] = Integer.valueOf(record.getLeeftijd()).toString();
                        colData[3] = record.getAdres();
                        colData[4] = record.getStudierichting();
                        colData[5] = Double.toString(record.getCijfergemiddelde());
                        listTableModel.addRow(colData);
                    }
                    outputTable.setModel(listTableModel);
                }
            }
        });
    }
}
