import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.io.*;

//current task, implement backend
//current problems/bugs: 
//creating two seperate pannels, instead of using frames because yes

public class App extends JFrame { 
    //globalizing buttons
    JButton openButton; 
    JButton backButton; 
    JButton saveButton; 
    
    //two different scenes
    JFrame frame; //coresponding to default 
    JFrame f; //coresponding to individual
    
    //default view
   
    private App(){
        try{
            mainView(); 
        }catch(Exception e){

        }
    }   

    public static BigList makeBigList(String filename) throws Exception {
        File currentFile = new File(filename);
        //scanner to get from front end to back end
        Scanner s = new Scanner(currentFile);
        BigList listOne = BigList.parseData("Unknown", s);
        return listOne;
    }


    //add a throws exception at some point

    public void mainView() throws Exception {
          var frame = new JFrame(); 
          frame.setSize(500,500); 
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
          frame.setLayout(null);
          
          //when imputing the file name
          var openButton = new JButton();
          openButton.setBounds(50,100,95,30);  
          openButton.setText("Open"); 
          openButton.setActionCommand("open");
          openButton.addActionListener(new Switcher());
          frame.add(openButton);     
  
          frame.setVisible(true);
         
    }
    
    //list item view
    public void individualView() throws Exception{
        JFrame f = new JFrame(); 
        JPanel btns = new JPanel();
        f.setSize(500,500); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.setLayout(null);

        JButton SaveButton = new JButton();
        SaveButton.setText("Save");  
        SaveButton.setBounds(50,100,95,30); 
        SaveButton.addActionListener(new Switcher());
        btns.add(SaveButton); 

        //Back button
        var backButton = new JButton(); 
        backButton.setBounds(50,100,95,30); 
        backButton.setText("Back");
        btns.add(backButton); 
        backButton.setActionCommand("back"); 
        btns.revalidate();
        btns.repaint();
        f.add(btns);
        f.revalidate();
         f.repaint();
        System.out.println("hidsf");
        
         //jcomponent
         BigList listOne = makeBigList("./src/Data.txt");
         JComponent c = listOne.toComponent();
         f.add(c); 
         JScrollPane pane = new JScrollPane(c);
         f.add(pane);
         f.revalidate();
         f.repaint();

         
         f.setVisible(true);

    }

    //default
    private void getFileFromInput(){
        
        //String filestring = "ex.txt"; 
        //File currentFile =new File(fileString);   
        
        //Jlabel for every thing or individual event
        //or maybe jtree
    }

    public static void runGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        App app = new App(); 
   }

   //switching pages
   private class Switcher implements ActionListener { 
        //temporary solution - reworking logic afterwards
      
        public void actionPerformed(ActionEvent event) {
      String eventName = event.getActionCommand();
        //if Back button = true = go to back
      
        if(eventName.equals("open")) { //add critera of adding file, make some event trigger
            try{
                individualView();

            }catch(Exception e){

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
        
        // javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //     public void run() {
        //       runGUI();
        //     }
        // });    
    }
}


 

