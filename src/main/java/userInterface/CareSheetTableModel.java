package userInterface;

import javax.swing.table.AbstractTableModel;
import model.*;

import java.util.ArrayList;
import java.util.Date;

public class CareSheetTableModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<CareSheetResearch> contents;

    public CareSheetTableModel(ArrayList<CareSheetResearch> contents) {
        this.columnNames = new ArrayList<>();
        columnNames.add("Intitulé fiche de soin");
        columnNames.add("Code");
        columnNames.add("Nom");
        columnNames.add("Race");
        columnNames.add("Date");
        setContents(contents);
    }


    public void setContents(ArrayList<CareSheetResearch> contents) {
        this.contents = contents;
    }
    public int getRowCount() {
        return contents.size();
    }
    public int getColumnCount() {
        return columnNames.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        CareSheetResearch query = contents.get(rowIndex);
        switch(columnIndex) {
            case 0 : return query.getSpeciesLabel();
            case 1: return query.getCode();
            case 2 : return query.getName();
            case 3: return query.getBreedLabel();
            case 4: return query.getDate();
            default:return null;
        }
    }
    public String getColumnName(int column) { return columnNames.get(column); }

    public Class getColumnClass(int columnIndex) {
        Class c;
        switch (columnIndex) {
            case 0: c= String.class; break;
            case 1: c= String.class; break;
            case 2: c = String.class; break;
            case 3: c = String.class; break;
            case 4: c = Date.class; break;
            default: c = String.class;
        }
        return c;
    }

}
