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

        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Gestion du parc animalier", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        animation = new AntilopeAnimation(this);
        panel.add(animation.getPanel(),BorderLayout.CENTER);
        animation.start();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Quitter")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Inscription")) {
                FormAddAnimal formAddAnimal = new FormAddAnimal();
                panel.removeAll();
                panel.setLayout(new BorderLayout());
                panel.add(formAddAnimal,BorderLayout.CENTER);
                panel.revalidate();
        } else if (e.getActionCommand().equals("Modifier")){
            ModifyPanel modifyPanel = new ModifyPanel();
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panel.add(modifyPanel,BorderLayout.CENTER);
            panel.revalidate();
        } else if (e.getActionCommand().equals("Supprimer")){
            DeletePanel deletePanel = new DeletePanel();
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panel.add(deletePanel,BorderLayout.CENTER);
            panel.revalidate();
        } else if (e.getActionCommand().equals("Listing")){
            ListingPanel listingPanel = new ListingPanel();
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panel.add(listingPanel,BorderLayout.CENTER);
            panel.revalidate();
        } else if (e.getActionCommand().equals("Par espèce")) {
            CareSheetPanel careSheetPanel = new CareSheetPanel();
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panel.add(careSheetPanel,BorderLayout.CENTER);
            panel.revalidate();
        } else if (e.getActionCommand().equals("Par fonction")) {
            RemarkPanel panelRemark = new RemarkPanel();
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panel.add(panelRemark, BorderLayout.CENTER);
            panel.revalidate();
        } else if (e.getActionCommand().equals("Par médicament")) {
            MedicinePanel medicinePanel = new MedicinePanel();
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panel.add(medicinePanel, BorderLayout.CENTER);
            panel.revalidate();
        } else if (e.getActionCommand().equals("Retour à la fenêtre principal")){
            panel.removeAll();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            panel.add(new JLabel("Gestion du parc animalier"));
            panel.add(animation.getPanel(),BorderLayout.CENTER);
            panel.revalidate();
        }
    }
}
