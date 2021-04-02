import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout; 
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
// import java.awt.*;

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
            File file = new File(filename);
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
        

        GridBagConstraints gbcInner = new GridBagConstraints();
        GridBagLayout layoutInner = new GridBagLayout();
        GridBagConstraints gbcMenu = new GridBagConstraints();
        GridBagLayout layoutMenu = new GridBagLayout();
        gbcMenu.fill = GridBagConstraints.HORIZONTAL; 

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


        JLabel titleComp = new JLabel(this.title);
        comp.add(titleComp);
        gbcMenu.gridy = 0;  
        gbcMenu.gridx = 0;  
        layoutMenu.addLayoutComponent(titleComp, gbcMenu); 

        JTextField input = new JTextField(title);
        JButton saveBtn = new JButton("SAVE");
        saveBtn.addActionListener((event)-> {
            String filename = input.getText() + ".txt";
            writeFile(filename);
        });

        comp.add(input);
        comp.add(saveBtn);
        gbcMenu.gridy = 0;  
        gbcMenu.gridx++;  
        layoutMenu.addLayoutComponent(input, gbcMenu); 
        gbcMenu.gridy = 0;  
        gbcMenu.gridx++;  
        layout.addLayoutComponent(saveBtn, gbcMenu); 

        JButton addBtn = new JButton("+");
        addBtn.addActionListener((event)-> {
            ListItem newItem = new ListItem();
            list.add(newItem);
            gbcInner.gridx = 0;
            gbcInner.gridy++;
            JComponent itemC = newItem.toComponent(this_);
            // comp.removeAll();
            listItems.add(itemC);
            layoutInner.addLayoutComponent(itemC, gbcInner);
            // comp.setBackground(new Color(255,0,0));
            listItems.revalidate();
            listItems.repaint();
            
        });
        comp.add(addBtn);
        gbcMenu.gridy++;
        gbcMenu.gridx =0;
        layoutMenu.addLayoutComponent(addBtn, gbcMenu);

        JPanel menu = new JPanel();
        JPanel list = new JPanel();
        menu.setLayout(layoutMenu);
        list.setLayout(layoutInner);
        gbc.gridy = 0;
        gbc.gridx = 0;
        layout.addLayoutComponent(menu, gbc);
        gbc.gridy = 0;
        gbc.gridx = 1;
        layout.addLayoutComponent(list, gbc);
        // comp.add(listItems);
        // gbc.gridy = 1;
        // gbc.gridx =1;
        // layout.addLayoutComponent(addBtn, gbc);
        // Todo- test this please I need an adult
        return comp;
    }
}
