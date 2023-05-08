package userInterface;

import Exceptions.*;
import controller.ApplicationController;
import model.Animal;
import model.CareSheetResearch;
import model.Medicine;
import model.Species;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListingPanel extends JPanel implements ActionListener {
    private JComboBox<Species> speciesJComboBoxBox;
    private JComboBox<Animal> animalJComboBox;
    private JPanel panel,animalPanel;
    private JLabel animalLabel,speciesLabel;
    private ApplicationController controller;
    private ArrayList<Species> speciesList;
    private ArrayList<Animal> animalsList,animalSpecific;
    private JButton buttonSearch;
    private JTable jTable;
    private JScrollPane scrollPanel;
    public ListingPanel(){
        controller = new ApplicationController();
        speciesList = new ArrayList<>();
        try {
            speciesList = controller.listSpecies();
        }catch (ListSpeciesException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
        animalsList = new ArrayList<>();
        setLayout(new BorderLayout());
        panel = new JPanel(new GridLayout(0,4));
        speciesLabel = new JLabel("Espèce : ");
        panel.add(speciesLabel);

        speciesJComboBoxBox = new JComboBox<>();
        for (Species species : speciesList){
            speciesJComboBoxBox.addItem(species);
        }
        panel.add(speciesJComboBoxBox);
        buttonSearch = new JButton("Rechercher");
        buttonSearch.addActionListener(this);
        panel.add(buttonSearch);
        add(panel,BorderLayout.NORTH);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Rechercher")){
            Species selectedSpecies = (Species) speciesJComboBoxBox.getSelectedItem();
            try {
                if(jTable != null){
                    remove(jTable);
                }
                if(scrollPanel != null){
                    remove(scrollPanel);
                }
                animalsList = controller.getAnimalsBySpecies(selectedSpecies.getId());
                ListingAnimalModel model = new ListingAnimalModel(animalsList);
                jTable = new JTable(model);
                jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                scrollPanel = new JScrollPane (jTable);
                animalLabel = new JLabel("Animal choisi : ");
                animalSpecific = new ArrayList<>();
                try {
                    animalSpecific = controller.getAnimalsBySpecies(selectedSpecies.getId());
                }catch (GetAnimalsException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                }
                animalJComboBox = new JComboBox<>();
                for (Animal animal : animalSpecific){
                    animalJComboBox.addItem(animal);
                }
                buttonSearch = new JButton("Afficher ses préparations");
                buttonSearch.addActionListener(this);

                animalPanel = new JPanel();
                animalPanel.add(animalLabel,BorderLayout.SOUTH);
                animalPanel.add(animalJComboBox,BorderLayout.SOUTH);
                animalPanel.add(buttonSearch,BorderLayout.SOUTH);

                add(scrollPanel, BorderLayout.CENTER);
                add(animalPanel,BorderLayout.SOUTH);
                revalidate();
                repaint();
            }catch (GetAnimalsException exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Afficher ses préparations")){
            if(jTable != null){
                this.remove(jTable);
            }
            if(scrollPanel != null){
                this.remove(scrollPanel);
            }
            removeAll();


            revalidate();
            repaint();
        } else if (e.getActionCommand().equals("A été effectué aujourd'hui")){
            if(jTable != null){
                this.remove(jTable);
            }
            if(scrollPanel != null){
                this.remove(scrollPanel);
            }
        } else if (e.getActionCommand().equals("Retour au listing")){
            //
        }

    }
}
