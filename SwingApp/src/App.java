//general import statements for cohesion: might be clarified in the future if nothing is correct

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class App extends JFrame { 
    
    //globalizing buttons
    JButton openButton; 
    JButton backButton; 

    JButton save; 

    //default view
   private App(){

        var frame = new JFrame(); 
        frame.setSize(500,500); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(null);
        
        //when imputing the file name
        var openButton = new JButton();
        openButton.setBounds(50,100,95,30);  
        openButton.setText("Open"); 
        openButton.addActionListener(e -> System.out.println("example"));
        frame.add(openButton);     


        frame.setVisible(true);

    }   

    //list item view
    private void listView(){
        var f = new JFrame(); 
        f.setSize(500,500); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.setLayout(null);

        var SaveButton = new JButton();
        SaveButton.setText("Save");  
        SaveButton.setBounds(50,100,95,30); 
        SaveButton.addActionListener(new NextPage());
        //SaveButton.addActionListener(e -> System.out.println("example"));
        f.add(SaveButton); 

        //Back button
        var backButton = new JButton(); 
        backButton.setBounds(50,100,95,30); 
        backButton.setText("Back");
        f.add(backButton); 
        openButton.addActionListener(e -> System.out.println("example"));
        f.setVisible(true);
    }

    //running/building app
    public static void runGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
      
        App mainView = new App(); 
   }

   //switching pages
   
   //TODO -- finish switching pages 
   private class NextPage implements ActionListener { 

    public void actionPerformed(ActionEvent event) {
      String eventName = event.getActionCommand();
      
      if(eventName.equals("tba")) {
          //firstFrame(); 
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

 

