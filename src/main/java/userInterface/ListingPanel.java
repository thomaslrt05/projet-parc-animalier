package userInterface;

import Exceptions.*;
import controller.ApplicationController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListingPanel extends JPanel {

    private ApplicationController controller;

    private ArrayList<Animal> animalsList;

    private JTable jTable;
    private JScrollPane scrollPanel;
    public ListingPanel(){

        try {
            controller = new ApplicationController();
            setLayout(new BorderLayout());
            animalsList = controller.getAllAnimals();
            AnimalTableModel model = new AnimalTableModel(animalsList);
            jTable = new JTable(model);
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPanel = new JScrollPane (jTable);
            add(scrollPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }catch (GetAllAnimalsException | SingletonConnexionException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }


}
