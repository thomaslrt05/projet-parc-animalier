package userInterface;
import model.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class PreparationTableModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<PreparationSheet> contents;

    public PreparationTableModel(ArrayList<PreparationSheet> contents) {
        this.columnNames = new ArrayList<>();
        columnNames.add("Numéro");
        columnNames.add("Date");
        columnNames.add("Quantité");
        columnNames.add("Posologie");
        columnNames.add("Crée");
        columnNames.add("Animal ID");
        columnNames.add("Détail");
        columnNames.add("Préparation");
        setContents(contents);
    }


    public void setContents(ArrayList<PreparationSheet> contents) {
        this.contents = contents;
    }
    public int getRowCount() {
        return contents.size();
    }
    public int getColumnCount() {
        return columnNames.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        PreparationSheet query = contents.get(rowIndex);
        switch(columnIndex) {
            case 0 : return query.getNumber();
            case 1: return query.getDate();
            case 2: return query.getQuantity();
            case 3 : return query.getPosology();
            case 4: return query.getCreation();
            case 5: return query.getAttachment();
            case 6 : return query.getDetail();
            case 7: return query.getPreparation();
            default:return null;
        }
    }
    public String getColumnName(int column) { return columnNames.get(column); }

    public Class getColumnClass(int columnIndex) {
        Class c;
        switch (columnIndex) {
            case 0: c= Integer.class; break;
            case 1: c= Date.class; break;
            case 2: c = Double.class; break;
            case 3: c= String.class; break;
            case 4: c= String.class; break;
            case 5: c = String.class; break;
            case 6: c= Integer.class; break;
            case 7: c= String.class; break;
            default: c = String.class;
        }
        return c;
    }
}
