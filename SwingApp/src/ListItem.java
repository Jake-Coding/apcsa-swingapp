import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ListItem implements Comparable<ListItem>{
    private String name;
    private String description;
    private boolean isDone;

    private String fix(String s) {
        return s.replaceAll(":", "");
    }
    public ListItem(String name, String description, boolean isDone) {
        this.name = fix(name);
        this.description = fix(description);
        this.isDone = (isDone);
    }
    public ListItem(String name, String description) {
        this(name, description, false);
    }
    public ListItem(String name) {
        this(name, "", false);
    }
    public ListItem() {
        this("", "", false);
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
    public boolean getIsDone() {return isDone;}
    public boolean finished() {return getIsDone();}
    public boolean isEmpty() {return name.isBlank() && description.isBlank();}

    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setIsDone(boolean isDone) {this.isDone = isDone;}

    public void toggleDone() {isDone = !isDone;}

    public int compareTo(ListItem other) {
        return this.name.compareTo(other.getName());
    }

    public String toString() {
        return name + ":" + description +":"+ (isDone ? "true" : "false");
    }

    public JComponent toComponent(BigList l) {
        ListItem t = this;
        JComponent comp = new JPanel();
        JTextField nameLabel = new JTextField(name, 10);
        
        JTextField descLabel = new JTextField(description, 10);
        nameLabel.getDocument().addDocumentListener(new DocumentListener(){
           public void changedUpdate(DocumentEvent e) {
               setName(nameLabel.getText());
               if (descLabel.getText().equals("") && nameLabel.getText().equals("")) {
                   l.deleteItem(t);
                   comp.setVisible(false);
               }
           } 
           public void insertUpdate(DocumentEvent e) {
               setName(nameLabel.getText());
               if (descLabel.getText().equals("") && nameLabel.getText().equals("")) {
                   l.deleteItem(t);
                   comp.setVisible(false);
               }
           }           
           public void removeUpdate(DocumentEvent e) {
               setName(nameLabel.getText());
               if (descLabel.getText().equals("") && nameLabel.getText().equals("")) {
                   l.deleteItem(t);
                   comp.setVisible(false);
               }
           }
        });
        descLabel.getDocument().addDocumentListener(new DocumentListener(){
           public void changedUpdate(DocumentEvent e) {
               setDescription(descLabel.getText());
               if (descLabel.getText().equals("") && nameLabel.getText().equals("")) {
                   l.deleteItem(t);
                   comp.setVisible(false);
               }
               
           } 
            public void insertUpdate(DocumentEvent e) {
               setDescription(descLabel.getText());
               if (descLabel.getText().equals("") && nameLabel.getText().equals("")) {
                   l.deleteItem(t);
                   comp.setVisible(false);
               }
           }           
           public void removeUpdate(DocumentEvent e) {
               setDescription(descLabel.getText());
               if (descLabel.getText().equals("") && nameLabel.getText().equals("")) {
                   l.deleteItem(t);
                   comp.setVisible(false);
               }
           } 
        
        });
        JCheckBox doneBox = new JCheckBox();
        doneBox.setSelected(isDone);
        doneBox.addActionListener((event)-> {
            toggleDone();
        });
        comp.add(nameLabel);
        comp.add(descLabel);
        comp.add(doneBox);
        // GroupLayout layout = new GroupLayout(comp);
        // layout.setAutoCreateGaps(true);
        // layout.setAutoCreateContainerGaps(true);
        // System.out.println(comp);
        
        // comp.setLayout(layout);
        // comp.setVisible(true);

        // return new JLabel("hi");
        return comp;
    }

    public static ListItem parseString(String s) {
        String[] elems = s.split(":");

        return elems.length == 3 ? new ListItem(elems[0], elems[1], Boolean.parseBoolean(elems[2])) : new ListItem();
        // return null;
    }
}