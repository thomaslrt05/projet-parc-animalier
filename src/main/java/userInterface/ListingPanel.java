package userInterface;

import Exceptions.*;
import controller.ApplicationController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListingPanel extends JPanel implements ActionListener {
    private JComboBox<Species> speciesJComboBoxBox;
    private JComboBox<Animal> animalJComboBox;
    private JComboBox<PreparationSheet> preparationSheetJComboBox;
    private JPanel panel,animalPanel,preparationPanel;
    private JLabel animalLabel,speciesLabel,preparationLabel;
    private ApplicationController controller;
    private ArrayList<Species> speciesList;
    private ArrayList<Animal> animalsList,animalSpecific;
    private ArrayList<PreparationSheet> preparationSheetsList;
    private JButton buttonSearch,buttonBackToListing;
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
                removeAll();
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
                buttonBackToListing = new JButton("Retour au listing");
                buttonBackToListing.addActionListener(this);
                animalPanel = new JPanel();
                animalPanel.add(animalLabel,BorderLayout.SOUTH);
                animalPanel.add(animalJComboBox,BorderLayout.SOUTH);
                animalPanel.add(buttonSearch,BorderLayout.SOUTH);
                animalPanel.add(buttonBackToListing);

                add(scrollPanel, BorderLayout.CENTER);
                add(animalPanel,BorderLayout.SOUTH);
                revalidate();
                repaint();
            }catch (GetAnimalsException exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Afficher ses préparations")){
            removeAll();

            Animal selectedAnimal = (Animal) animalJComboBox.getSelectedItem();
            preparationSheetsList = new ArrayList<>();

            try {
                preparationSheetsList = controller.getListPreparations(selectedAnimal.getCode());
            }catch (ListPreparationsException exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
            }
            ListingPreparationModel model = new ListingPreparationModel(preparationSheetsList);
            jTable = new JTable(model);
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            scrollPanel = new JScrollPane (jTable);

            preparationSheetJComboBox = new JComboBox<>();
            for (PreparationSheet sheet : preparationSheetsList){
                preparationSheetJComboBox.addItem(sheet);
            }
            preparationLabel = new JLabel("A été effectué aujourd'hui");
            buttonSearch = new JButton("Valider");
            buttonSearch.addActionListener(this);
            buttonBackToListing = new JButton("Retour au listing");
            buttonBackToListing.addActionListener(this);
            preparationPanel = new JPanel();
            preparationPanel.add(preparationLabel);
            preparationPanel.add(preparationSheetJComboBox);
            preparationPanel.add(buttonSearch);
            preparationPanel.add(buttonBackToListing);
            add(scrollPanel, BorderLayout.CENTER);
            add(preparationPanel,BorderLayout.SOUTH);
            revalidate();
            repaint();
        } else if (e.getActionCommand().equals("Valider")){
            PreparationSheet selectedSheet = (PreparationSheet) preparationSheetJComboBox.getSelectedItem();
            try {
                controller.modifyPreparationsheet(selectedSheet.getNumber());
                JOptionPane.showMessageDialog(null,"La fiche a été mise a jour","Réussite",JOptionPane.INFORMATION_MESSAGE);
            }
            catch (ModifyPreparationsheetException  exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
            }
            revalidate();
            repaint();
        } else if (e.getActionCommand().equals("Retour au listing")){
            removeAll();
            setLayout(new BorderLayout());
            panel = new JPanel(new GridLayout(0,4));
            panel.add(speciesLabel);
            panel.add(speciesJComboBoxBox);
            buttonSearch = new JButton("Rechercher");
            buttonSearch.addActionListener(this);
            panel.add(buttonSearch);
            add(panel,BorderLayout.NORTH);
            revalidate();
            repaint();
        }

    }
}
