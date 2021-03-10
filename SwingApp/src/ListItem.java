import javax.swing.*;
import java.awt.*;

public class ListItem implements Comparable<ListItem>{
    private String name;
    private String description;
    private boolean isDone;

    public ListItem(String name, String description, boolean isDone) {
        this.name = name;
        this.description = description;
        this.isDone = isDone;
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

    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setIsDone(boolean isDone) {this.isDone = isDone;}

    public void toggleDone() {isDone = !isDone;}

    public int compareTo(ListItem other) {
        return this.name.compareTo(other.getName());
    }

    public String toString() {
        return name + ":" + description + (isDone ? "finished" : "unfinished");
    }

    public JComponent toComponent() {
        JComponent comp = new JPanel();
        JLabel nameLabel = new JLabel(name);
        JLabel descLabel = new JLabel(description);
        JCheckBox doneBox = new JCheckBox();
        doneBox.setSelected(isDone);
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

    public ListItem parseString(String s) {
        // TODO - implement
        return null;
    }
}