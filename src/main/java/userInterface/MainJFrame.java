package userInterface;

import model.*;
import controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Exceptions.*;

public class MainJFrame extends JFrame implements ActionListener {
    private JPanel panel;
    private Container container;
    private AntilopeAnimation animation;

    public MainJFrame() {
        panel = new JPanel();
        container = getContentPane();
        container.add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gestion Parc Animalier");
        setSize(600, 400);

        // Création du menu bar
        JMenuBar menuBar = new JMenuBar();

        // Création des menus
        JMenu applicationMenu = new JMenu("Application");
        JMenu animalMenu = new JMenu("Animal");
        JMenu researchMenu = new JMenu("Research");
        JMenu listingMenu = new JMenu("Listing");

        // Ajout des menus à la barre de menu
        menuBar.add(applicationMenu);
        menuBar.add(animalMenu);
        menuBar.add(researchMenu);
        menuBar.add(listingMenu);

        // Création des options de menu
        JMenuItem leaveMenuItem = new JMenuItem("Quitter");
        JMenuItem backToMainFrame = new JMenuItem("Retour à la fenêtre principal");
        JMenuItem inscriptionMenuItem = new JMenuItem("Inscription");
        JMenuItem speciesMenuItem = new JMenuItem("Par espèce");
        JMenuItem fonctionMenuItem = new JMenuItem("Par fonction");
        JMenuItem medicineMenuItem = new JMenuItem("Par médicament");

        // Ajout des options de menu aux menus correspondants
        applicationMenu.add(backToMainFrame);
        applicationMenu.add(leaveMenuItem);
        animalMenu.add(inscriptionMenuItem);
        researchMenu.add(speciesMenuItem);
        researchMenu.add(fonctionMenuItem);
        researchMenu.add(medicineMenuItem);

        // Ajout des evenement
        leaveMenuItem.addActionListener(this);
        backToMainFrame.addActionListener(this);
        inscriptionMenuItem.addActionListener(this);
        speciesMenuItem.addActionListener(this);
        fonctionMenuItem.addActionListener(this);
        medicineMenuItem.addActionListener(this);

        // Ajout de la barre de menu à la fenêtre
        setJMenuBar(menuBar);

        // Ajout d'un message d'accueil dans le panel
        panel.add(new JLabel("Gestion du parc animalier"));

        // Ajout de l'animation AntilopeAnimation dans le panel
        animation = new AntilopeAnimation(this);
        panel.add(animation.getPanel(),BorderLayout.SOUTH);
        animation.start();
        setVisible(true);
    }

    // Implémentation de l'action listener pour les options de menu
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Quitter")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Inscription")) {
                FormAddAnimal formAddAnimal = new FormAddAnimal();
                panel.removeAll();
                panel.setLayout(new BorderLayout());
                panel.add(formAddAnimal,BorderLayout.CENTER);
                panel.revalidate();
        } else if (e.getActionCommand().equals("Par espèce")) {

        } else if (e.getActionCommand().equals("Par fonction")) {
            ///
        } else if (e.getActionCommand().equals("Par médicament")) {
            ///
        } else if (e.getActionCommand().equals("Retour à la fenêtre principal")){
            ////
        }
    }
}
