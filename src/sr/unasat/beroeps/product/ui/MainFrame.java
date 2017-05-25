package sr.unasat.beroeps.product.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mnarain on 5/17/2017.
 */
public class MainFrame extends JFrame {

    //	Create and set up the window.
    public MainFrame() throws HeadlessException {
        super("SwingApp");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create and set menu bar
        MenuBar menuBar = new MenuBar();
        // put the menubar on the frame
        setJMenuBar(menuBar.getMenuBar());

        JTabbedPane tabbedPane=new JTabbedPane();
        tabbedPane.setBounds(50,50,200,200);

        //  Place Student
        tabbedPane.add("Student", new StudentUI());

        //  Place Rooster
        tabbedPane.add("Rooster", new RoosterUI());

        tabbedPane.setOpaque(true);    //	content panes must be op..
        setContentPane(tabbedPane);

        //	Display the window
        // setSize(500, 500);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}