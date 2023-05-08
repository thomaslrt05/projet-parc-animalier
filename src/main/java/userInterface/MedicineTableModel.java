package userInterface;
import model.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class MedicineTableModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<TreatmentByMedicine> contents;

    public MedicineTableModel(ArrayList<TreatmentByMedicine> contents) {
        this.columnNames = new ArrayList<>();
        columnNames.add("Description");
        columnNames.add("Quantité");
        columnNames.add("Date");
        setContents(contents);
    }


    public void setContents(ArrayList<TreatmentByMedicine> contents) {
        this.contents = contents;
    }
    public int getRowCount() {
        return contents.size();
    }
    public int getColumnCount() {
        return columnNames.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        TreatmentByMedicine query = contents.get(rowIndex);
        switch(columnIndex) {
            case 0 : return query.getDescription();
            case 1: return query.getQuantity();
            case 2: return query.getDate();
            default:return null;
        }
    }
    public String getColumnName(int column) { return columnNames.get(column); }

    public Class getColumnClass(int columnIndex) {
        Class c;
        switch (columnIndex) {
            case 0: c= String.class; break;
            case 1: c= Double.class; break;
            case 2: c = Date.class; break;
            default: c = String.class;
        }
        return c;
    }
}
