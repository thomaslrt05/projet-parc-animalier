package userInterface;

import controller.*;
import model.*;
import Exceptions.*;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class ModifyPanel extends JPanel implements ActionListener{
    private JComboBox<Animal> comboBox;
    private JPanel panel,formPanel;
    private JLabel animalLabel, nameLabel, sexLabel, weightLabel, nickNameLabel, speciesLabel,isDangerousLabel,arrivalDateLabel;
    private JTextField nameField, weightField, nickNameField;
    private ApplicationController controller;
    private ArrayList<Animal> animalList;
    private JButton button;
    private String currentCode;
    private JCheckBox isDangerousBox;
    private JComboBox<Species> speciesCombo;
    private JRadioButton sexMaleButton, sexFemaleButton;
    private JButton submitButton, cancelButton;
    private JSpinner spinner;
    private SpinnerDateModel model;
    private ArrayList<Species> speciesList;
    private Species currentSpecies,matchingSpecies;

    public ModifyPanel(){

        try {
            controller = new ApplicationController();
            animalList = new ArrayList<>();
            animalList = controller.getAllAnimals();
            setLayout(new BorderLayout());
            panel = new JPanel(new GridLayout(0,4));
            animalLabel = new JLabel("Animal : ");
            panel.add(animalLabel);

            comboBox = new JComboBox<>();
            for (Animal animal: animalList) {
                comboBox.addItem(animal);
            }
            panel.add(comboBox);
            button = new JButton("Valider choix");
            button.addActionListener(this);
            panel.add(button);
            add(panel,BorderLayout.NORTH);
        }catch (GetAllAnimalsException | SingletonConnexionException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }



    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Valider choix")) {
            Animal selectedAnimal = (Animal) comboBox.getSelectedItem();
            panel.removeAll();
            setLayout(new BorderLayout());
            formPanel = new JPanel(new GridLayout(0, 2));
            formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            currentCode = selectedAnimal.getCode();

            nameLabel = new JLabel("Nom * :");
            nameField = new JTextField(20);
            nameField.setText(selectedAnimal.getName());
            formPanel.add(nameLabel);
            formPanel.add(nameField);

            nickNameLabel = new JLabel("Surnom :");
            nickNameField = new JTextField(20);
            nickNameField.setText(selectedAnimal.getNickName());
            formPanel.add(nickNameLabel);
            formPanel.add(nickNameField);

            arrivalDateLabel = new JLabel("Date d'arrivé * : ");
            model = new SpinnerDateModel();
            model.setValue(selectedAnimal.getArrivalDate());
            spinner = new JSpinner(model);
            JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd.MM.yyyy");
            DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
            formatter.setAllowsInvalid(false);
            formatter.setOverwriteMode(true);
            formPanel.add(arrivalDateLabel);
            formPanel.add(spinner);

            sexMaleButton = new JRadioButton("Mâle");
            sexFemaleButton = new JRadioButton("Female");
            ButtonGroup groupSex = new ButtonGroup();
            groupSex.add(sexMaleButton);
            groupSex.add(sexFemaleButton);
            if(selectedAnimal.getSex() == Gender.M) {
                sexMaleButton.setSelected(true);
            }else sexFemaleButton.setSelected(true);
            sexLabel = new JLabel("Sexe * :");

            JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            formPanel.add(sexLabel);
            formPanel.add(sexPanel);
            sexPanel.add(sexMaleButton);
            sexPanel.add(sexFemaleButton);

            weightLabel = new JLabel("Poids * :");
            weightField = new JTextField(10);
            weightField.setText(Double.toString(selectedAnimal.getWeight()));
            formPanel.add(weightLabel);
            formPanel.add(weightField);

            isDangerousLabel = new JLabel("Est-il dangereux ?");
            isDangerousBox = new JCheckBox();
            isDangerousBox.setSelected(selectedAnimal.getDangerous());
            formPanel.add(isDangerousLabel);
            formPanel.add(isDangerousBox);

            speciesLabel = new JLabel("Espèce * : ");
            speciesCombo = new JComboBox<>();
            try {
                speciesList = controller.listSpecies();
                for (Species species: speciesList) {
                    speciesCombo.addItem(species);
                }
                try {
                    currentSpecies = controller.getSpecies(selectedAnimal.getBreed());
                    matchingSpecies = speciesList.stream()
                            .filter(species -> species.getId().equals(currentSpecies.getId()))
                            .findFirst()
                            .orElse(null);

                    if (matchingSpecies != null) {
                        speciesCombo.setSelectedItem(matchingSpecies);
                    }

                    formPanel.add(speciesLabel);
                    formPanel.add(speciesCombo);

                    submitButton = new JButton("Modifier");
                    cancelButton = new JButton("Annuler");
                    submitButton.addActionListener(this);
                    cancelButton.addActionListener(this);

                    formPanel.add(submitButton);
                    formPanel.add(cancelButton);
                    add(formPanel,BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }catch (GetSpeciesException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }catch (ListSpeciesException exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getActionCommand().equals("Modifier")){
            String name = nameField.getText();
            Gender sex = (sexMaleButton.isSelected()) ? Gender.M : Gender.F;
            Species speciesSelected = (Species) speciesCombo.getSelectedItem();
            Breed breedSelected = null;
            try {
                breedSelected = controller.getBreed(speciesSelected.getId());
            }catch (GetBreedException exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            String breed = breedSelected.getId();

            Boolean isDangerous = isDangerousBox.isSelected();
            String weightInformation = weightField.getText();
            Date arrivalDate = model.getDate();
            String nickName = nickNameField.getText();

            Animal animalModified;
            double weight = 0;

            if (name.length() > 20 || name.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Le nom doit contenir entre 1 et 20 caractères.","Erreur",JOptionPane.ERROR_MESSAGE);
            }else {
                if (!name.matches("[\\p{L}]+")) {
                    JOptionPane.showMessageDialog(null, "Le nom doit contenir uniquement des lettres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }else {
                    if(weightInformation.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Le poids ne doit pas être vide","Erreur",JOptionPane.ERROR_MESSAGE);
                    }else {
                        if (!weightInformation.matches("-?\\d+(\\.\\d+)?")) {
                            JOptionPane.showMessageDialog(null, "Le champ poids doit contenir seulement des chiffres.","Erreur",JOptionPane.ERROR_MESSAGE);
                        }else {
                            if(weightInformation.length() > 10){
                            JOptionPane.showMessageDialog(null,"Le poids ne doit pas dépasser 10 chiffres","Erreur",JOptionPane.ERROR_MESSAGE);
                            }else {
                                weight = Double.parseDouble(weightInformation);
                                if (weight < 0) {
                                    JOptionPane.showMessageDialog(null,"Le poids doit être positif","Erreur",JOptionPane.ERROR_MESSAGE);
                                }else {
                                    Date now = new Date();
                                    if (arrivalDate.after(now)) {
                                        JOptionPane.showMessageDialog(null, "La date d'arrivée doit être antérieure ou égale à la date courante.", "Erreur", JOptionPane.ERROR_MESSAGE);
                                    }else {
                                        if (!nickName.isEmpty()){
                                            if (nickName.length() > 20) {
                                                JOptionPane.showMessageDialog(null,"Le nom doit contenir entre 1 et 20 caractères.","Erreur",JOptionPane.ERROR_MESSAGE);
                                            }else {
                                                if (!nickName.matches("[\\p{L}]+")) {
                                                    JOptionPane.showMessageDialog(null, "Le surnom doit contenir uniquement des lettres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                                                }else {
                                                    try {
                                                        animalModified = new Animal(currentCode,name,arrivalDate,sex,isDangerous,weight,breed,nickName);
                                                        controller.modifyAnimal(animalModified);
                                                        JOptionPane.showMessageDialog(null,"La modification de l'animal a été effectué","Réussite",JOptionPane.INFORMATION_MESSAGE);
                                                        removeAll();
                                                        add(panel,BorderLayout.NORTH);
                                                        animalList.clear();
                                                        comboBox = new JComboBox<>();
                                                        try {
                                                            animalList = controller.getAllAnimals();
                                                        }catch (GetAllAnimalsException exception){
                                                            JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                                                        }
                                                        for (Animal animal: animalList) {
                                                            comboBox.addItem(animal);
                                                        }
                                                        panel.add(animalLabel);
                                                        panel.add(comboBox);
                                                        panel.add(button);
                                                        revalidate();
                                                        repaint();
                                                    }
                                                    catch (ModifyAnimalException exception){
                                                        JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                                                    }
                                                }
                                            }

                                        }else {
                                            try {
                                                animalModified = new Animal(currentCode,name,arrivalDate,sex,isDangerous,weight,breed);
                                                controller.modifyAnimal(animalModified);
                                                JOptionPane.showMessageDialog(null,"La modification de l'animal a été effectué","Réussite",JOptionPane.INFORMATION_MESSAGE);
                                                removeAll();
                                                add(panel,BorderLayout.NORTH);
                                                animalList.clear();
                                                comboBox = new JComboBox<>();
                                                try {
                                                    animalList = controller.getAllAnimals();
                                                }catch (GetAllAnimalsException exception){
                                                    JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                                                }
                                                for (Animal animal: animalList) {
                                                    comboBox.addItem(animal);
                                                }
                                                panel.add(animalLabel);
                                                panel.add(comboBox);
                                                panel.add(button);
                                                revalidate();
                                                repaint();
                                            }
                                            catch (ModifyAnimalException exception){
                                                JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (e.getActionCommand().equals("Annuler")) {
            removeAll();
            setLayout(new BorderLayout());
            add(panel,BorderLayout.NORTH);
            panel.add(animalLabel);
            panel.add(comboBox);
            panel.add(button);
            revalidate();
            repaint();
        }


    }
}
