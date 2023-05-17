package userInterface;

import model.*;
import controller.*;
import Exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MedicinePanel extends JPanel implements ActionListener {
    private JComboBox<Medicine> comboBox;
    private JPanel panel;
    private JLabel speciesLabel;
    private ApplicationController controller;
    private ArrayList<Medicine> medicinesList;
    private ArrayList<TreatmentByMedicine> data;
    private JButton button;
    private JTable jTable;
    private JScrollPane scrollPanel;

    public MedicinePanel() {

        try {
            controller = new ApplicationController();

            medicinesList = new ArrayList<>();
            medicinesList = controller.listMedicine();
            data = new ArrayList<>();

            setLayout(new BorderLayout());
            panel = new JPanel(new GridLayout(0,4));
            speciesLabel = new JLabel("Médicament :");
            panel.add(speciesLabel);

            comboBox = new JComboBox<>();
            for (Medicine medicine: medicinesList) {
                comboBox.addItem(medicine);
            }
            panel.add(comboBox);
            button = new JButton("Recherche");
            button.addActionListener(this);
            panel.add(button);
            add(panel,BorderLayout.NORTH);
        }catch (ListMedicineException | SingletonConnexionException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(jTable != null){
            this.remove(jTable);
        }
        if(scrollPanel != null){
            this.remove(scrollPanel);
        }
        Medicine selectedMedicine = (Medicine) comboBox.getSelectedItem();
        try {
            data = controller.treatmentByMedicineResearch(selectedMedicine.getId());
            MedicineTableModel model = new MedicineTableModel(data);
            jTable = new JTable(model);
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPanel = new JScrollPane (jTable);
            this.add(scrollPanel, BorderLayout.CENTER);
            this.revalidate();
        }catch (MedecineResearchException | EmptyNameException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
}
