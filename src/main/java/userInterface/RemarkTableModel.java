package userInterface;

import javax.swing.table.AbstractTableModel;
import model.*;
import java.util.ArrayList;

public class RemarkTableModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<RemarkByFonction> contents;

    public RemarkTableModel(ArrayList<RemarkByFonction> contents) {
        this.columnNames = new ArrayList<>();
        columnNames.add("LastName");
        columnNames.add("FirstName");
        columnNames.add("Label");
        columnNames.add("Description");
        setContents(contents);
    }


    public void setContents(ArrayList<RemarkByFonction> contents) {
        this.contents = contents;
    }
    public int getRowCount() {
        return contents.size();
    }
    public int getColumnCount() {
        return columnNames.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        RemarkByFonction query = contents.get(rowIndex);
        switch(columnIndex) {
            case 0 : return query.getLastName();
            case 1 : return query.getFirstName();
            case 2: return query.getLabel();
            case 3: return query.getDescription();
            default:return null;
        }
    }
    public String getColumnName(int column) { return columnNames.get(column); }

    public Class getColumnClass(int columnIndex) {
        Class c;
        switch (columnIndex) {
            default: c = String.class;
        }
        return c;
    }

}
