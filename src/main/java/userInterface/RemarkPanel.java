package userInterface;

import Exceptions.*;
import controller.*;
import javax.swing.*;
import model.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class RemarkPanel extends JPanel implements ActionListener {
    private JComboBox<Fonction> comboBox;
    private JPanel panel;
    private JLabel fonctionLabel;
    private ApplicationController controller;
    private ArrayList<Fonction> fonctions;
    private ArrayList<RemarkByFonction> data;
    private JButton button;
    private JTable jTable;
    private JScrollPane scrollPanel;

    public RemarkPanel() {


       try {
           controller = new ApplicationController();

           fonctions = new ArrayList<>();
           fonctions = controller.listFonctions();
           data = new ArrayList<>();

           setLayout(new BorderLayout());
           panel = new JPanel(new GridLayout(0,4));
           fonctionLabel = new JLabel("Fonctions:");
           panel.add(fonctionLabel);

           comboBox = new JComboBox<>();
           for (Fonction fonction: fonctions) {
               comboBox.addItem(fonction);
           }

           panel.add(comboBox);
           button = new JButton("Recherche");
           button.addActionListener(this);
           panel.add(button);
           add(panel,BorderLayout.NORTH);
       }catch (ListFonctionsException | SingletonConnexionException exception){
           JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
       }



    }
    public void actionPerformed(ActionEvent e) {

        if(jTable != null){
            this.remove(jTable);
        }
        if(scrollPanel != null){
            this.remove(scrollPanel);
        }

        Fonction selectedFonction = (Fonction) comboBox.getSelectedItem();

       try {
           data = controller.remarkByFonctions(selectedFonction.getId());
           RemarkTableModel model = new RemarkTableModel(data);
           jTable = new JTable(model);
           jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
           jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           scrollPanel = new JScrollPane (jTable);
           this.add(scrollPanel, BorderLayout.CENTER);
           this.revalidate();
       }catch (RemarkByFonctionsException exception){
           JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
       }
    }
}
