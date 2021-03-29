import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BigList {
    // public static void main(String[] args) {

    // }
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

    public void sort() {
        Collections.sort(list);
    }

    public void editName(int index, String newName) {
        list.get(index).setName(newName);
    }

    public String toString() {
        String big = "";
        for(ListItem item : list) {
            big += item.isEmpty() ? "" : item.toString()+"\n";
        }
        return big.substring(0, big.length()-1);
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
        BigList this_ = this;
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
        GridBagConstraints gbcInner = new GridBagConstraints();
        GridBagLayout layoutInner = new GridBagLayout();
        JPanel listItems = new JPanel(layoutInner);
        JScrollPane scrollPane = new JScrollPane(listItems);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0,0,200,300);
        comp.add(scrollPane);

        for (ListItem item : list) {
            JComponent itemComp = item.toComponent(this);
            
            gbcInner.gridy++;  
            gbcInner.gridx = 0;  
            layoutInner.addLayoutComponent(itemComp, gbcInner);  
            listItems.add(itemComp);
        }
        comp.add(listItems);

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

        JButton addBtn = new JButton("+");
        addBtn.addActionListener((event)-> {
            ListItem newItem = new ListItem();
            list.add(newItem);
            gbcInner.gridx = 0;
            gbcInner.gridy = list.size();
            JComponent itemC = newItem.toComponent(this_);
            // comp.removeAll();
            listItems.add(itemC);
            layoutInner.addLayoutComponent(itemC, gbcInner);
            // comp.setBackground(new Color(255,0,0));
            listItems.revalidate();
            listItems.repaint();
            
        });
        comp.add(addBtn);
        gbc.gridy = ++yCounter;
        gbc.gridx =0;
        layout.addLayoutComponent(addBtn, gbc);
        // Todo- test this please I need an adult
        return comp;
    }
}
