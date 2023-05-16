package userInterface;

import Exceptions.*;
import controller.ApplicationController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CareSheetPanel extends JPanel implements ActionListener {
    private JComboBox<Species> comboBox;
    private JPanel panel;
    private JLabel speciesLabel;
    private ApplicationController controller;
    private ArrayList<Species> speciesList;
    private ArrayList<CareSheetResearch> data;
    private JButton button;
    private JTable jTable;
    private JScrollPane scrollPanel;

    public CareSheetPanel() {

        try {
            controller = new ApplicationController();
            speciesList = new ArrayList<>();
            speciesList = controller.listSpecies();

            data = new ArrayList<>();

            setLayout(new BorderLayout());
            panel = new JPanel(new GridLayout(0,4));
            speciesLabel = new JLabel("Espèces :");
            panel.add(speciesLabel);

            comboBox = new JComboBox<>();
            for (Species species: speciesList) {
                comboBox.addItem(species);
            }
            panel.add(comboBox);
            button = new JButton("Recherche");
            button.addActionListener(this);
            panel.add(button);
            add(panel,BorderLayout.NORTH);
        }catch (ListSpeciesException | SingletonConnexionException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void actionPerformed(ActionEvent e) {
        if(jTable != null){
            this.remove(jTable);
        }
        if(scrollPanel != null){
            this.remove(scrollPanel);
        }

        Species selectedSpecies = (Species) comboBox.getSelectedItem();
        try {
            data = controller.careSheetSearch(selectedSpecies.getId());
            CareSheetTableModel model = new CareSheetTableModel(data);
            jTable = new JTable(model);
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPanel = new JScrollPane (jTable);
            add(scrollPanel, BorderLayout.CENTER);
            revalidate();
        }catch (CareSheetResearchException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }


    }
}


