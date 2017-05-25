package sr.unasat.beroeps.product.ui;

import sr.unasat.beroeps.product.entities.Rooster;
import sr.unasat.beroeps.product.entities.Student;
import sr.unasat.beroeps.product.repositories.RoosterRepository;
import sr.unasat.beroeps.product.repositories.StudentRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by mitchel on 5/24/17.
 */
public class RoosterUI extends JPanel{
    private JButton selectAllButton;
    private JList outputList;
    private RoosterRepository roosterRepo;

    private DefaultListModel listModel;

    private DefaultTableModel listTableModel;
    private JTable outputTable;

    public RoosterUI(){
        roosterRepo = new RoosterRepository();

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
        String[] colnames = {"id", "student", "vak", "dag", "starttijd", "endtijd"};
        Vector colnamesV = new Vector(Arrays.asList(colnames));
        outputTable = new JTable(null, colnamesV);
        JScrollPane tablePanel = new JScrollPane(outputTable);
        tablePanel.setPreferredSize(new Dimension(800, 150));
        outputPanel.add(tablePanel);
        // Select listener
        selectAllButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg) {
                // Voer SelectAll functie uit.

                // sr.unasat.beroeps.product.entities.Rooster database instantie is alleen 1 keer nodig
                if (roosterRepo == null) {
                    roosterRepo = new RoosterRepository();
                }

                // Initialiseer de roosterRepo alleen als het moet
                if (!roosterRepo.isInitialised()) {
                    roosterRepo.initialize();
                }

                // Alleen als de rooster database is geinitialiseerd dan verder
                if (roosterRepo.isInitialised()) {
                    java.util.List<Rooster> outputList = roosterRepo.selectAll();

                    // Maak de lijst leeg als er elementen inzitten
                    listModel.removeAllElements();

                    // Parse de lijst
                    for (Rooster rooster : outputList) {
                        Rooster record = rooster;
                        listModel.addElement(record.toString());
                    }

                    listTableModel = (DefaultTableModel) outputTable.getModel();
                    listTableModel.setRowCount(0);
                    // Parse de table
                    for (Rooster rooster : outputList) {
                        Rooster record = rooster;
                        String[] colData = new String[6];
                        colData[0] = Integer.valueOf(record.getId()).toString();
                        colData[1] = record.getStudent().getNaam();
                        colData[2] = record.getVak().getVakNaam();
                        colData[3] = record.getDag();
                        colData[4] = record.getStartTijd();
                        colData[5] = record.getEndTijd();
                        listTableModel.addRow(colData);
                    }
                    outputTable.setModel(listTableModel);
                }
            }
        });
    }
}
