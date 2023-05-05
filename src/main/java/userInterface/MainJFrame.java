package userInterface;

import model.*;
import controller.*;

import javax.swing.*;
import javax.swing.border.Border;
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

        JMenuBar menuBar = new JMenuBar();

        JMenu applicationMenu = new JMenu("Application");
        JMenu animalMenu = new JMenu("Animal");
        JMenu researchMenu = new JMenu("Research");

        menuBar.add(applicationMenu);
        menuBar.add(animalMenu);
        menuBar.add(researchMenu);

        JMenuItem leaveMenuItem = new JMenuItem("Quitter");
        JMenuItem backToMainFrame = new JMenuItem("Retour à la fenêtre principal");
        JMenuItem inscriptionMenuItem = new JMenuItem("Inscription");
        JMenuItem modifyMenuItem = new JMenuItem("Modifier");
        JMenuItem deleteMenuItem = new JMenuItem("Supprimer");
        JMenuItem listingMenuItem = new JMenuItem("Listing");
        JMenuItem speciesMenuItem = new JMenuItem("Par espèce");
        JMenuItem fonctionMenuItem = new JMenuItem("Par fonction");
        JMenuItem medicineMenuItem = new JMenuItem("Par médicament");

        applicationMenu.add(backToMainFrame);
        applicationMenu.add(leaveMenuItem);
        animalMenu.add(inscriptionMenuItem);
        animalMenu.add(modifyMenuItem);
        animalMenu.add(deleteMenuItem);
        animalMenu.add(listingMenuItem);
        researchMenu.add(speciesMenuItem);
        researchMenu.add(fonctionMenuItem);
        researchMenu.add(medicineMenuItem);

        leaveMenuItem.addActionListener(this);
        backToMainFrame.addActionListener(this);
        inscriptionMenuItem.addActionListener(this);
        modifyMenuItem.addActionListener(this);
        deleteMenuItem.addActionListener(this);
        listingMenuItem.addActionListener(this);
        speciesMenuItem.addActionListener(this);
        fonctionMenuItem.addActionListener(this);
        medicineMenuItem.addActionListener(this);

        setJMenuBar(menuBar);

        panel.add(new JLabel("Gestion du parc animalier"));

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
                animation.interrupt();
                FormAddAnimal formAddAnimal = new FormAddAnimal();
                panel.removeAll();
                panel.setLayout(new BorderLayout());
                panel.add(formAddAnimal,BorderLayout.CENTER);
                panel.revalidate();
        } else if (e.getActionCommand().equals("Modifier")){

        } else if (e.getActionCommand().equals("Supprimer")){

        } else if (e.getActionCommand().equals("Listing")){

        } else if (e.getActionCommand().equals("Par espèce")) {
            FormCareSheetResearch research = new FormCareSheetResearch();
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panel.add(research,BorderLayout.CENTER);
            panel.revalidate();
        } else if (e.getActionCommand().equals("Par fonction")) {
            animation.interrupt();
            RemarkPanel panelRemark = new RemarkPanel();
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panel.add(panelRemark, BorderLayout.CENTER);
            panel.revalidate();
        } else if (e.getActionCommand().equals("Par médicament")) {
            ///
        } else if (e.getActionCommand().equals("Retour à la fenêtre principal")){
            ////
        }
    }
}
