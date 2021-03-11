import java.util.*;
import javax.swing.*;
// import java.awt.*;

public class BigList {
    public static void main(String[] args) {

    }
    private List<ListItem> list;

    public BigList(List<ListItem> list) {
        this.list = list;
    }

    //Setter 
    public void getList(List<ListItem> list) {this.list = list;}

    public void deleteItem(int index) {
        list.remove(index);
    }

    public void editName(int index, String newName) {
        list.get(index).setName(newName);
    }

    public String toString() {
        String big = "";
        for(ListItem item : list) {
            big += item.toString();
            big += "\n";
        }
        return big;
    }

    public static BigList parseData(Scanner fileScanner) { // BigList list = BigList.parseData(scan)
        List<ListItem> arr = new ArrayList<ListItem>();
        while (fileScanner.hasNextLine()) {
            arr.add(ListItem.parseString(fileScanner.nextLine()));
        }
        return new BigList(arr);
    }

    public JComponent toComponent() {
        JComponent comp = new JPanel();
        for (ListItem item : list) {
            comp.add(item.toComponent());
        }
        return comp;
    }
}
