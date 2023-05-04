package userInterface;

import model.*;
import controller.*;
import Exceptions.*;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class FormAddAnimal extends JPanel {
    private JLabel codeLabel, nameLabel, sexLabel, weightLabel, nickNameLabel, speciesLabel,isDangerousLabel,arrivalDate;
    private JTextField codeField, nameField, weightField, nickNameField;
    private JCheckBox isDangerousBox;
    private JComboBox<String> speciesCombo;
    private JRadioButton sexMaleButton, sexFemaleButton;
    private JButton submitButton, cancelButton;
    private ApplicationController controller;
    private JSpinner spinner;
    private SpinnerDateModel model;


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
        weightLabel.setLabelFor(weightField);
        formPanel.add(weightLabel);
        formPanel.add(weightField);


        isDangerousLabel = new JLabel("Est-il dangereux ?");
        isDangerousBox = new JCheckBox();
        formPanel.add(isDangerousLabel);
        formPanel.add(isDangerousBox);

        speciesLabel = new JLabel("Espèce : ");
        speciesCombo = new JComboBox<>(Species.getSpeciesList());
        formPanel.add(speciesLabel);
        formPanel.add(speciesCombo);



        submitButton = new JButton("Ajouter");
        cancelButton = new JButton("Annuler");

        formPanel.add(submitButton);
        formPanel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Effacer les champs
                codeField.setText("");
                nameField.setText("");
                weightField.setText("");
                sexFemaleButton.setSelected(false);
                sexMaleButton.setSelected(true);
                nickNameField.setText("");
                revalidate();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Traiter le formulaire soumis
                String code = codeField.getText();
                String name = nameField.getText();
                Gender sex = (sexMaleButton.isSelected()) ? Gender.M : Gender.F;
                String species = (String) speciesCombo.getSelectedItem();
                String breed = "";
                switch (species) {
                    case "crocodile":
                        breed = "b1";
                        break;
                    case "lion":
                        breed = "b2";
                        break;
                    case "girafe":
                        breed = "b3";
                        break;
                    case "zèbre":
                        breed = "b4";
                        break;
                    case "éléphant":
                        breed = "b5";
                        break;
                    case "singe":
                        breed = "b6";
                        break;
                    case "hippopotame":
                        breed = "b7";
                        break;
                    case "puma":
                        breed = "b8";
                        break;
                    case "rhinocéros":
                        breed = "b9";
                        break;
                    case "tigre":
                        breed = "b10";
                        break;
                }
                Boolean isDangerous = isDangerousBox.isSelected();
                String weightInformation = weightField.getText();
                Date arrivalDate = model.getDate();
                String nickName = nickNameField.getText();
                if(nickNameField.getText().isEmpty()){
                    nickName = "";
                }

                Boolean errorDetected = false;
                try {
                    // Vérifier doublon
                    //if (ApplicationController.getInstance().getAnimalByCode(code) != null) {
                    //    throw new IllegalArgumentException("Le code existe déjà.");
                    //errorDetected = true;
                    //}

                    if (code.length() > 20 || code.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Le nom doit contenir entre 1 et 20 caractères.","Erreur",JOptionPane.ERROR_MESSAGE);
                        errorDetected = true;
                    }

                    if (name.length() > 20 || name.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Le nom doit contenir entre 1 et 20 caractères.","Erreur",JOptionPane.ERROR_MESSAGE);
                        errorDetected = true;
                    }

                    if (!nickName.isEmpty()){
                        if (nickName.length() > 20) {
                            JOptionPane.showMessageDialog(null,"Le nom doit contenir entre 1 et 20 caractères.","Erreur",JOptionPane.ERROR_MESSAGE);
                            errorDetected = true;
                        }
                    }

                    if (!weightInformation.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "Le champ poids doit contenir seulement des chiffres.","Erreur",JOptionPane.ERROR_MESSAGE);
                        errorDetected = true;
                    }
                    double weight = 0;

                    if(weightInformation.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Le poids ne doit pas être vide","Erreur",JOptionPane.ERROR_MESSAGE);
                        errorDetected = true;
                    } else {
                        weight = Double.parseDouble(weightInformation);
                    }

                    if (weight < 0) {
                        JOptionPane.showMessageDialog(null,"Le poids doit être positif","Erreur",JOptionPane.ERROR_MESSAGE);
                        errorDetected = true;
                    }

                    Animal newAnimal;

                    if(nickName.isEmpty()){
                        newAnimal = new Animal(code,name,arrivalDate,sex,isDangerous,weight,breed);
                    } else {
                        newAnimal = new Animal(code,name,arrivalDate,sex,isDangerous,weight,breed,nickName);
                    }

                    if(!errorDetected){
                        try {
                            controller.addAnimal(newAnimal);
                            codeField.setText("");
                            nameField.setText("");
                            weightField.setText("");
                            sexFemaleButton.setSelected(false);
                            sexMaleButton.setSelected(true);
                            nickNameField.setText("");
                            revalidate();
                        }
                        catch (AddAnimalException exception){
                            JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(formPanel, ex.getMessage(), "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(formPanel, BorderLayout.CENTER);
    }




}

