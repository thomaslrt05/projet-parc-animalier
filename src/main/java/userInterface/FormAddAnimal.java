package userInterface;

import model.*;
import controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class FormAddAnimal extends JPanel {
    private JLabel codeLabel, nameLabel, sexLabel, weightLabel, nickNameLabel, speciesLabel,isDangerousLabel;
    private JTextField codeField, nameField, weightField, nickNameField;
    private JCheckBox isDangerousBox;
    private JComboBox<String> speciesCombo;
    private JRadioButton sexMaleButton, sexFemaleButton;
    private JButton submitButton, cancelButton;


    public FormAddAnimal() {
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

        nickNameLabel = new JLabel("Surnom :");
        nickNameField = new JTextField(20);
        formPanel.add(nickNameLabel);
        formPanel.add(nickNameField);

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
                double weight;
                if(!weightInformation.isEmpty()) weight = Double.parseDouble(weightInformation);
                else weight = 1;
                Date arrivalDate = new Date();
                String nickName = nickNameField.getText();
                if(nickNameField.getText().isEmpty()){
                    nickName = "";
                }
                try{
                    if (name.length() > 20 || code.isEmpty()) {
                        throw new IllegalArgumentException("Le code est vide.");
                    }
                    if (name.length() > 20 || name.isEmpty()) {
                        throw new IllegalArgumentException("Le nom doit contenir entre 1 et 20 caractères.");
                    }
                    if (weight < 0) {
                        throw new IllegalArgumentException("Le poids doit être positif.");
                    }

                    // Vérifier doublon
                    //if (ApplicationController.getInstance().getAnimalByCode(code) != null) {
                      //  throw new IllegalArgumentException("Le code existe déjà.");
                    //}

                    Animal newAnimal;
                    if(nickName.isEmpty()){
                        newAnimal = new Animal(code,name,sex,isDangerous,weight,breed);
                    }else {
                        newAnimal = new Animal(code,name,sex,isDangerous,weight,breed,nickName);
                    }
                    ApplicationController.addAnimal(newAnimal);


                    // Effacer les champs
                    codeField.setText("");
                    nameField.setText("");
                    weightField.setText("");
                    sexFemaleButton.setSelected(false);
                    sexMaleButton.setSelected(true);
                    nickNameField.setText("");
                    revalidate();
                }
                catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(formPanel, ex.getMessage(), "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                }


            }
        });


        add(formPanel, BorderLayout.CENTER);
    }




}

