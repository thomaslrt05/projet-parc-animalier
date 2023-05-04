package userInterface;
import controller.*;
import javax.swing.*;
import model.EnumRank;
import model.Fonction;
import model.RemarkByFonction;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class RemarkPanel extends JPanel implements ActionListener {
    // une combo box avec toutes fonctions
    private JComboBox<Fonction> comboBox;
    private JPanel panel;
    private JLabel fonctionLabel;
    private ApplicationController controller;
    private ArrayList<Fonction> fonctions;
    private ArrayList<RemarkByFonction> data;
    private JButton button;
    private JTable jTable;

    public RemarkPanel() {
        controller = new ApplicationController();

        fonctions = new ArrayList<>(); // SQL

        fonctions.add(new Fonction("1", EnumRank.CHIEF,"fonction1"));
        fonctions.add(new Fonction("2", EnumRank.CHIEF,"fonction2"));

        data = new ArrayList<>(); // SQL



        setLayout(new BorderLayout());
        panel = new JPanel(new GridLayout(0,4));
        //border
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

    }
    public void actionPerformed(ActionEvent e) {

        Fonction selectedFonction = (Fonction) comboBox.getSelectedItem(); // info récup avec la compo

        //faire la recherce sql pour obtenir les infos

        //ici je prend l'array list bidon qu'on a cree
        RemarkTableModel model = new RemarkTableModel(data);
        jTable = new JTable(model);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPanel = new JScrollPane (jTable);
        this.add(scrollPanel, BorderLayout.CENTER);
        this.revalidate();
    }
}
