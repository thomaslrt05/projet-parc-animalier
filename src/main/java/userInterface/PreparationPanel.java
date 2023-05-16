package userInterface;
import Exceptions.*;
import controller.ApplicationController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PreparationPanel extends JPanel implements ActionListener{
    private JComboBox<Species> speciesJComboBoxBox;
    private JComboBox<Animal> animalJComboBox;
    private JComboBox<Employee> employeeJComboBox;
    private JComboBox<PreparationSheet> preparationSheetJComboBox;
    private JPanel panel,animalPanel,preparationPanel;
    private JLabel animalLabel,speciesLabel,preparationLabel,employeeLabel;
    private ApplicationController controller;
    private ArrayList<Species> speciesList;
    private ArrayList<Employee> employeeList;
    private ArrayList<Animal> animalsList,animalSpecific;
    private ArrayList<PreparationSheet> preparationSheetsList;
    private JButton buttonSearch,buttonBackToListing;
    private JTable jTable;
    private JScrollPane scrollPanel;
    public PreparationPanel(){

        try {
            controller = new ApplicationController();
            speciesList = new ArrayList<>();
            speciesList = controller.listSpecies();
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
        }catch (ListSpeciesException | SingletonConnexionException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Rechercher")){
            Species selectedSpecies = (Species) speciesJComboBoxBox.getSelectedItem();
            try {
                removeAll();
                animalsList = controller.getAnimalsBySpecies(selectedSpecies.getId());
                AnimalTableModel model = new AnimalTableModel(animalsList);

                jTable = new JTable(model);
                jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                scrollPanel = new JScrollPane (jTable);
                animalLabel = new JLabel("Animal choisi : ");
                animalSpecific = new ArrayList<>();
                try {
                    animalSpecific = controller.getAnimalsBySpecies(selectedSpecies.getId());
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
            }catch (GetAnimalsException exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Afficher ses préparations")){
            removeAll();

            Animal selectedAnimal = (Animal) animalJComboBox.getSelectedItem();

            preparationSheetsList = new ArrayList<>();

            try {
                preparationSheetsList = controller.getListPreparations(selectedAnimal.getCode());
                PreparationTableModel model = new PreparationTableModel(preparationSheetsList);
                jTable = new JTable(model);
                jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                scrollPanel = new JScrollPane (jTable);

                preparationSheetJComboBox = new JComboBox<>();
                for (PreparationSheet sheet : preparationSheetsList){
                    preparationSheetJComboBox.addItem(sheet);
                }

                employeeList = new ArrayList<>();
                try {
                    employeeList = controller.listEmployees();
                    employeeJComboBox = new JComboBox<>();
                    for (Employee employee : employeeList){
                        employeeJComboBox.addItem(employee);
                    }
                    employeeLabel = new JLabel("Choisir l'employe : ");

                    preparationLabel = new JLabel("A été effectué aujourd'hui");
                    buttonSearch = new JButton("Valider");
                    buttonSearch.addActionListener(this);
                    buttonBackToListing = new JButton("Retour au listing");
                    buttonBackToListing.addActionListener(this);
                    preparationPanel = new JPanel();
                    preparationPanel.add(preparationLabel);
                    preparationPanel.add(preparationSheetJComboBox);
                    preparationPanel.add(employeeLabel);
                    preparationPanel.add(employeeJComboBox);
                    preparationPanel.add(buttonSearch);
                    preparationPanel.add(buttonBackToListing);
                    add(scrollPanel, BorderLayout.CENTER);
                    add(preparationPanel,BorderLayout.SOUTH);
                    revalidate();
                    repaint();
                }catch (ListEmployeesException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }catch (ListPreparationsException exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getActionCommand().equals("Valider")){
            PreparationSheet selectedSheet = (PreparationSheet) preparationSheetJComboBox.getSelectedItem();
            Employee selectedEmployee = (Employee) employeeJComboBox.getSelectedItem();
            if(selectedSheet == null ) {
                JOptionPane.showMessageDialog(null,"Il n'y a aucune préparation pour cet l'animal.","Erreur",JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    controller.modifyPreparationsheet(selectedEmployee.getMatricule(),selectedSheet.getNumber());
                    JOptionPane.showMessageDialog(null,"La fiche a été mise a jour","Réussite",JOptionPane.INFORMATION_MESSAGE);
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
                catch (ModifyPreparationsheetException  exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
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
