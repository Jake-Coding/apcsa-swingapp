import javax.swing.*;
public class ItemTester {
    JFrame f;

    public ItemTester() throws Exception{
        f = new JFrame("Item testing");

        ListItem item = new ListItem("Test", "descTest", true);
        System.out.println(item);
        JComponent c = item.toComponent();
        f.add(c);
        f.setSize(500, 500);
        f.setVisible(true);
        Timer t = new Timer(3000, (event)-> {
            System.out.println("slept");
            f.remove(c);
            item.toggleDone();
            item.setDescription(" a new description! ");
            
            JComponent d = item.toComponent();
            JComponent e = ListItem.parseString("parsedName:parsedDesc:false").toComponent();
            JComponent g = new JPanel();
            g.add(d);
            g.add(e);
            f.add(g);
            f.setVisible(false);
            f.setVisible(true);
            System.out.println("pls");
        });
        t.setRepeats(false);
        t.start();

        

    }
   public static void main(String[] args) throws Exception {
       new ItemTester();
   } 
}
