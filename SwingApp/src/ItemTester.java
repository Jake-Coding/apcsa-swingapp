import javax.swing.*;
import java.util.*;
import java.io.*;
public class ItemTester {
    JFrame f;

    public ItemTester() throws Exception{
        f = new JFrame("Item testing");
        BigList testingList = makeBigList("SwingApp/src/TestData.txt");

        JComponent c = testingList.toComponent();

        f.add(c);
        f.setSize(500, 500);
        f.setVisible(true);
    }

    public static BigList makeBigList(String filename) throws Exception {
        File file1 = new File(filename);
        Scanner s = new Scanner(file1);
        BigList testingList = BigList.parseData("Test", s);
        return testingList;
    }
   public static void main(String[] args) throws Exception {
       new ItemTester();
   } 
}
