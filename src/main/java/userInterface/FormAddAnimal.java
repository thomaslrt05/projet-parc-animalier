package userInterface;

import model.*;
import controller.*;
import Exceptions.*;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class FormAddAnimal extends JPanel implements ActionListener{
    private JLabel codeLabel, nameLabel, sexLabel, weightLabel, nickNameLabel, speciesLabel,isDangerousLabel,arrivalDate;
    private JTextField codeField, nameField, weightField, nickNameField;
    private JCheckBox isDangerousBox;
    private JComboBox<Species> speciesCombo;
    private JRadioButton sexMaleButton, sexFemaleButton;
    private JButton submitButton, cancelButton;
    private ApplicationController controller;
    private JSpinner spinner;
    private SpinnerDateModel model;
    private ArrayList<Species> speciesList;


    public FormAddAnimal()  {
        controller = new ApplicationController();
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        codeLabel = new JLabel("Code :");
        codeField = new JTextField(20);
        formPanel.add(codeLabel);
        formPanel.add(codeField);

        nameLabel = new JLabel("Nom :");
        nameField = new JTextField(20);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        nickNameLabel = new JLabel("Surnom :");
        nickNameField = new JTextField(20);
        formPanel.add(nickNameLabel);
        formPanel.add(nickNameField);

        arrivalDate = new JLabel("Date d'arrivé : ");
        model = new SpinnerDateModel();
        spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd.MM.yyyy");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        formPanel.add(arrivalDate);
        formPanel.add(spinner);

        sexMaleButton = new JRadioButton("Mâle");
        sexFemaleButton = new JRadioButton("Female");
        ButtonGroup groupSex = new ButtonGroup();
        groupSex.add(sexMaleButton);
        sexMaleButton.setSelected(true);
        groupSex.add(sexFemaleButton);
        sexLabel = new JLabel("Sexe :");

        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formPanel.add(sexLabel);
        formPanel.add(sexPanel);
        sexPanel.add(sexMaleButton);
        sexPanel.add(sexFemaleButton);




        weightLabel = new JLabel("Poids :");
        weightField = new JTextField(10);
        formPanel.add(weightLabel);
        formPanel.add(weightField);


        isDangerousLabel = new JLabel("Est-il dangereux ?");
        isDangerousBox = new JCheckBox();
        formPanel.add(isDangerousLabel);
        formPanel.add(isDangerousBox);

        speciesLabel = new JLabel("Espèce : ");
        speciesCombo = new JComboBox<>();

        try {
            speciesList = controller.listSpecies();
            for (Species species: speciesList) {
                speciesCombo.addItem(species);
            }
        }catch (ListSpeciesException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
        formPanel.add(speciesLabel);
        formPanel.add(speciesCombo);



        submitButton = new JButton("Ajouter");
        cancelButton = new JButton("Annuler");
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);

        formPanel.add(submitButton);
        formPanel.add(cancelButton);
        add(formPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ajouter")){
            String code = codeField.getText();
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
            if(nickNameField.getText().isEmpty()){
                nickName = "";
            }

            Boolean errorDetected = false;
            try {
                // Vérification code
                Boolean animalExist = false;
                try {
                    animalExist = controller.animalExists(code);
                }catch (AnimalExistsException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                }
                if(animalExist){
                    JOptionPane.showMessageDialog(null,"Le code unique est déjà utilisé par un autre animal.","Erreur",JOptionPane.ERROR_MESSAGE);
                    errorDetected = true;
                }

                if (code.length() > 20 || code.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Le nom doit contenir entre 1 et 20 caractères.","Erreur",JOptionPane.ERROR_MESSAGE);
                    errorDetected = true;
                }
                // Vérification nom
                if (name.length() > 20 || name.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Le nom doit contenir entre 1 et 20 caractères.","Erreur",JOptionPane.ERROR_MESSAGE);
                    errorDetected = true;
                }
                if (!name.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(null, "Le nom doit contenir uniquement des lettres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    errorDetected = true;
                }

                // Vérification nickName
                if (!nickName.isEmpty()){
                    if (nickName.length() > 20) {
                        JOptionPane.showMessageDialog(null,"Le nom doit contenir entre 1 et 20 caractères.","Erreur",JOptionPane.ERROR_MESSAGE);
                        errorDetected = true;
                    }
                    if (!nickName.matches("[a-zA-Z]+")) {
                        JOptionPane.showMessageDialog(null, "Le surnom doit contenir uniquement des lettres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        errorDetected = true;
                    }
                }
                // Vérification poids
                double weight = 0;
                if(weightInformation.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Le poids ne doit pas être vide","Erreur",JOptionPane.ERROR_MESSAGE);
                    errorDetected = true;
                } else {
                    weight = Double.parseDouble(weightInformation);
                    if (!weightInformation.matches("\\d+(\\.\\d+)?")) {
                        JOptionPane.showMessageDialog(null, "Le champ poids doit contenir seulement des chiffres.","Erreur",JOptionPane.ERROR_MESSAGE);
                        errorDetected = true;
                    }
                    if (weight < 0) {
                        JOptionPane.showMessageDialog(null,"Le poids doit être positif","Erreur",JOptionPane.ERROR_MESSAGE);
                        errorDetected = true;
                    }
                }
                // Vérification date
                Date now = new Date();
                if (arrivalDate.after(now)) {
                    JOptionPane.showMessageDialog(null, "La date d'arrivée doit être antérieure ou égale à la date courante.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    errorDetected = true;
                }

                if(!errorDetected){
                    try {
                        Animal newAnimal;

                        if(nickName.isEmpty()){
                            newAnimal = new Animal(code,name,arrivalDate,sex,isDangerous,weight,breed);
                        } else {
                            newAnimal = new Animal(code,name,arrivalDate,sex,isDangerous,weight,breed,nickName);
                        }
                        controller.addAnimal(newAnimal);
                        codeField.setText("");
                        nameField.setText("");
                        weightField.setText("");
                        sexFemaleButton.setSelected(false);
                        sexMaleButton.setSelected(true);
                        nickNameField.setText("");
                        JOptionPane.showMessageDialog(null,"L'ajout de l'animal a été effectué","Réussite",JOptionPane.INFORMATION_MESSAGE);
                        revalidate();
                    }
                    catch (AddAnimalException exception){
                        JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getActionCommand().equals("Annuler")){
            codeField.setText("");
            nameField.setText("");
            weightField.setText("");
            sexFemaleButton.setSelected(false);
            sexMaleButton.setSelected(true);
            nickNameField.setText("");
            revalidate();
        }
    }


}

