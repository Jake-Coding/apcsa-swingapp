import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout; 
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Component;
// import java.awt.*;

public class BigList {
    public static void main(String[] args) {

    }
    private List<ListItem> list;
    private String title;

    public BigList(String title, List<ListItem> list) {
        this.list = list;
        this.title = title;
    }

    //Setter 
    public void getList(List<ListItem> list) {this.list = list;}

    public void deleteItem(int index) {
        list.remove(index);
    }

    public void deleteItem(ListItem item) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == item) {
                deleteItem(i);
                break;
            }
        }
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

    public static BigList parseData(String title, Scanner fileScanner) { // BigList list = BigList.parseData(scan)
        List<ListItem> arr = new ArrayList<ListItem>();
        while (fileScanner.hasNextLine()) {
            arr.add(ListItem.parseString(fileScanner.nextLine()));
        }
        return new BigList(title, arr);
    }
    public void writeFile(String filename) {
        try {
            File file = new File("SwingApp/src/"+filename);
            PrintWriter writer = new PrintWriter(file);
            writer.print(this);
            writer.close();
        } catch (Exception e) {System.out.println(e);}
    }

    public JComponent toComponent() {
        int yCounter = 0;
        JComponent comp = new JPanel();
        
        GridBagConstraints gbc = new GridBagConstraints();  
        GridBagLayout layout = new GridBagLayout();  
        comp.setLayout(layout);  
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        
        JLabel titleComp = new JLabel(this.title);
        comp.add(titleComp);
        gbc.gridy = ++yCounter;  
        gbc.gridx = 0;  
        layout.addLayoutComponent(titleComp, gbc); 
        for (ListItem item : list) {
            JComponent itemComp = item.toComponent(this);
            
           // itemComp.addsomeListener() { when(all fields blank), deleteItem(item)} 
            gbc.gridy = ++yCounter;  
            gbc.gridx = 0;  
            layout.addLayoutComponent(itemComp, gbc);  
            comp.add(itemComp);
        }

        JTextField input = new JTextField(title);
        JButton saveBtn = new JButton("SAVE");
        saveBtn.addActionListener((event)-> {
            String filename = input.getText() + ".txt";
            writeFile(filename);
        });

        comp.add(input);
        comp.add(saveBtn);
        gbc.gridy = ++yCounter;  
        gbc.gridx = 0;  
        layout.addLayoutComponent(input, gbc); 
        gbc.gridy = ++yCounter;  
        gbc.gridx = 0;  
        layout.addLayoutComponent(saveBtn, gbc); 
        // Todo- test this please I need an adult
        return comp;
    }
}
