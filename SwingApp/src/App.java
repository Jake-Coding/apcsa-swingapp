import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.io.*;

public class App extends JFrame { 
    //globalizing buttons
    JButton openButton; 
    JButton backButton; 
    JButton saveButton; 
    JButton openFileButton;
    
    //two different scenes
    JFrame frame; //coresponding to default 
    JFrame f; //coresponding to individual

    String filename; 
    
    //default view
    private App(){
        try{
            mainView(); 
        }catch(Exception e){

        }
    }   
    //initializes big list
    public static BigList makeBigList(String filename) throws Exception {
        File currentFile = new File(filename);
        //scanner to get from front end to back end
        Scanner s = new Scanner(currentFile);
        BigList listOne = BigList.parseData("Unknown", s);
        return listOne;
    }

    public void mainView() throws Exception {
          var frame = new JFrame(); 
          frame.setSize(1000,1000); 
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
          frame.setLayout(null);
          
          //open button is case if you don't have a files
          var openButton = new JButton();
          openButton.setBounds(50,100,95,30);  
          openButton.setText("New"); 
          openButton.setActionCommand("new");
          openButton.addActionListener(new Switcher());
          openButton.addActionListener((e)-> {
              frame.dispose();
          });

          frame.add(openButton);     

          JTextField fileInput = new JTextField();
          frame.add(fileInput);
          
          openFileButton = new JButton("Open File");
          openFileButton.addActionListener((e) -> {
            try{
               individualView(fileInput.getText(),false);  
            }catch(Exception error) {
                System.out.println(error);
            }
          });
          frame.add(openFileButton);
          frame.revalidate();
          frame.repaint();
          frame.setVisible(true);
         
    }
    
    //list item view
    public void individualView(String filename, boolean isNew) throws Exception{
        JFrame f = new JFrame(); 
        JPanel btns = new JPanel();
        f.setSize(1000,1000); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        // f.setLayout(null);

        // JButton SaveButton = new JButton();
        // SaveButton.setText("Save");  
        // SaveButton.setBounds(50,100,95,30); 
        // SaveButton.addActionListener(new Switcher());
        // btns.add(SaveButton); 

        //Back button
        var backButton = new JButton(); 
        backButton.setBounds(50,100,95,30); 
        backButton.setText("Back");
        btns.add(backButton); 
        backButton.setActionCommand("back"); 
        backButton.addActionListener(new Switcher());
        backButton.addActionListener((e)-> {
            f.dispose();
        });
        // btns.revalidate();
        // btns.repaint();
        
         BigList listOne;
         if(isNew) {
            listOne = new BigList("Newfile", new ArrayList<ListItem>());
         } else {
             listOne = makeBigList(filename);
         }
         JComponent c = listOne.toComponent();
         btns.add(c);
         f.add(btns);
         JScrollPane pane = new JScrollPane(btns);
         f.add(pane);
         f.revalidate();
         f.repaint();

         
         f.setVisible(true);

    }

    //runs the app, might be unessisary, is potentially temp
    public static void runGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        App app = new App(); 
   }

   //switching pages
   private class Switcher implements ActionListener {       
        public void actionPerformed(ActionEvent event) {
      String eventName = event.getActionCommand();
      
        if(eventName.equals("new")) { //add critera of adding file, make some event trigger: mayb
            try{
                individualView("",true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

       if(eventName.equals("back")){
           try{
            mainView(); 
           }catch(Exception e){

           }
       }
    }

   }
    public static void main(String[] args){
        runGUI();
    }
}


 

