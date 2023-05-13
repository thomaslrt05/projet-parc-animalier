package userInterface;

import Exceptions.*;
import controller.ApplicationController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListingPanel extends JPanel {

    private ApplicationController controller;

    private ArrayList<Animal> animalsList;

    private JTable jTable;
    private JScrollPane scrollPanel;
    public ListingPanel(){
        controller = new ApplicationController();
        setLayout(new BorderLayout());
        //panel = new JPanel(new GridLayout(0,4));
        try {
            animalsList = controller.getAllAnimals();
            AnimalTableModel model = new AnimalTableModel(animalsList);
            jTable = new JTable(model);
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPanel = new JScrollPane (jTable);
            add(scrollPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }catch (GetAllAnimalsException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }


}
