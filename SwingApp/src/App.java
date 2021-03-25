//general import statements for cohesion: might be clarified in the future if nothing is correct

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

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
        mainView(); 
    }   

    private void mainView(){
          //TODO add feature to implement file names
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
    private void individualView(){
        var f = new JFrame(); 
        f.setSize(500,500); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.setLayout(null);

        var SaveButton = new JButton();
        SaveButton.setText("Save");  
        SaveButton.setBounds(50,100,95,30); 
        SaveButton.addActionListener(new Switcher());
        //SaveButton.addActionListener(e -> System.out.println("example"));
        f.add(SaveButton); 

        //Back button
        var backButton = new JButton(); 
        backButton.setBounds(50,100,95,30); 
        backButton.setText("Back");
        f.add(backButton); 
        backButton.setActionCommand("back"); 
        f.setVisible(true);
    }

    private void getFileFromInput(){
        //Jlabel for every thing or individual event
        //or maybe jtree
    }

    //running/building app - might remove depending on future functionality
    public static void runGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
      
        App app = new App(); 
   }

   //switching pages
   private class Switcher implements ActionListener { 
        //TODO - rework to make it so that there are not two different pannels
        //might have to rework logic
    public void actionPerformed(ActionEvent event) {
      String eventName = event.getActionCommand();
        //if Back button = true = go to back
      if(eventName.equals("open")) { //add critera of adding file, make some event trigger
            individualView();
       }

       if(eventName.equals("back")){
            mainView(); 
       }
    }

   }
    public static void main(String[] args){
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              runGUI();
            }
        });    
    }
}

//base necessities

// - App
//   - default view toggle (back button)
//   - main method (build app)
//   - On default view
//     - getFileFromInput
//   - On list view
//     - saveCurrentList
//     - editListItem

//-add scanner file - will have to look at backend code

 

