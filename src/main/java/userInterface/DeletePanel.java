package userInterface;

import model.*;
import controller.*;
import Exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletePanel extends JPanel implements ActionListener {
    private JComboBox<Animal> comboBox;
    private JPanel panel;
    private JLabel animalLabel;
    private ApplicationController controller;
    private ArrayList<Animal> animalList;
    private JButton button;

    public DeletePanel(){

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
            button = new JButton("Supprimer");
            button.addActionListener(this);
            panel.add(button);
            add(panel,BorderLayout.NORTH);
        }catch (GetAllAnimalsException | SingletonConnexionException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {

        Animal selectedAnimal = (Animal) comboBox.getSelectedItem();
        int dialogResult = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer cet animal ? Cela entraînera la suppression de toutes les données le concernant.", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION){
            try {
                controller.deleteAnimal(selectedAnimal.getCode());
                comboBox.removeItemAt(comboBox.getSelectedIndex());
                JOptionPane.showMessageDialog (null, "L'animal a bien été supprimé", "Réussite", JOptionPane.INFORMATION_MESSAGE);
            }catch (DeleteAnimalException exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
