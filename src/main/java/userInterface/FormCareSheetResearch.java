package userInterface;

import Exceptions.CareSheetResearchException;
import controller.ApplicationController;
import model.CareSheetResearch;
import model.Species;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormCareSheetResearch extends JPanel{
    private JComboBox<String> speciesCombo;
    private ApplicationController controller;
    private JLabel speciesLabel;
    private JButton submitButton;
    public FormCareSheetResearch() {
        controller = new ApplicationController();
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        speciesLabel = new JLabel("Espèce : ");
        speciesCombo = new JComboBox<>(Species.getSpeciesList());
        formPanel.add(speciesLabel);
        formPanel.add(speciesCombo);

        submitButton = new JButton("Rechercher");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Traiter le formulaire soumis
                String species = (String) speciesCombo.getSelectedItem();
                String code = switch (species) {
                    case "crocodile" -> "s1";
                    case "lion" -> "s2";
                    case "girafe" -> "s3";
                    case "zèbre" -> "s4";
                    case "éléphant" -> "s5";
                    case "singe" -> "s6";
                    case "hippopotame" -> "s7";
                    case "puma" -> "s8";
                    case "rhinocéros" -> "s9";
                    default -> "s10";
                };
                ArrayList<CareSheetResearch> allData = new ArrayList<CareSheetResearch>();
                try {
                    allData = controller.careSheetSearch(code);
                    if(allData.isEmpty()){
                        // TODO que fair si c'est vide
                    } else {
                        for (CareSheetResearch c :allData){
                            // TODO Affichage des données
                        }
                    }

                }
                catch (CareSheetResearchException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
                };
            }
        });
        formPanel.add(submitButton);
        add(formPanel, BorderLayout.CENTER);
    }
}
