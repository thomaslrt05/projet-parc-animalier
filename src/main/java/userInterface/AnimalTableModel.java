package userInterface;
import model.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
public class AnimalTableModel extends AbstractTableModel{
    private ArrayList<String> columnNames;
    private ArrayList<Animal> contents;

    public AnimalTableModel(ArrayList<Animal> contents) {
        this.columnNames = new ArrayList<>();
        columnNames.add("Code");
        columnNames.add("Nom");
        columnNames.add("Sex");
        columnNames.add("Dangereux");
        columnNames.add("Poids");
        columnNames.add("Date d'arrivé");
        columnNames.add("Surnom");
        columnNames.add("Race");
        setContents(contents);
    }


    public void setContents(ArrayList<Animal> contents) {
        this.contents = contents;
    }
    public int getRowCount() {
        return contents.size();
    }
    public int getColumnCount() {
        return columnNames.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal query = contents.get(rowIndex);
        switch(columnIndex) {
            case 0 : return query.getCode();
            case 1: return query.getName();
            case 2 : return query.getSex();
            case 3: return query.getDangerous();
            case 4: return query.getWeight();
            case 5: return query.getArrivalDate();
            case 6: return query.getNickName();
            case 7: return query.getBreed();
            default:return null;
        }
    }
    public String getColumnName(int column) { return columnNames.get(column); }

    public Class getColumnClass(int columnIndex) {
        Class c;
        switch (columnIndex) {
            case 0: c= String.class; break;
            case 1: c= String.class; break;
            case 2: c = Gender.class; break;
            case 3: c = Boolean.class; break;
            case 4: c = Double.class; break;
            case 5: c = Date.class; break;
            case 6: c = String.class; break;
            case 7: c = String.class; break;
            default: c = String.class;
        }
        return c;
    }
}
