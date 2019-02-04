/************************************************************
 *                                                          *
 *  CSCI 470/680     Assignment 5       Summer 2018         *                        
 *                                                          *
 *  Programmer:  Usman Hussain                              *  
 *               Nicholas Swanson                           *   
 *                                                          *
 *  Date Due:    07/30/2018                                 *
 *                                                          *
 *  Use:         FileFrame Class - Implementation           *
 *                                                          *
 ************************************************************/
/********IMPORTS USED FOR PROGRAM***************/
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JOptionPane; 


public class FileFrame extends JFrame
{
  private final JTextField inputFileField;
  private final JButton inputButton;  
  
  public FileFrame()
  {
    super("File Selection");//setting name of frame
    setResizable(false);
    
    //Components
    JPanel inputPanel = new JPanel();//Instantiate 
    
    JLabel inputText = new JLabel("Enter Filename",SwingConstants.CENTER);//Instantiate
    inputText.setFont(new Font("Arial", Font.BOLD, 15));
    
    inputFileField = new JTextField(20);//Instantiate
    
    inputButton = new JButton("OpenFile");//Instantiate
    inputButton.addActionListener(new requestHandler());
    
    /***********SET LAYOUT VIA GRIDBAG*******************/
    inputPanel.setLayout(new GridBagLayout());
    inputPanel.setPreferredSize(new Dimension(280,100));
    GridBagConstraints gc = new GridBagConstraints();//Instantiate GridBag Constraints
    
    /*****************GRID BAG CONSTRAINTS******************/
    gc.gridwidth = 3;
    gc.anchor = GridBagConstraints.PAGE_START; //top of space
    gc.gridx = 0;
    gc.gridy = 0;
    inputPanel.add(inputText,gc);

    gc.gridwidth = 3;
    gc.anchor = GridBagConstraints.CENTER; //center of space
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridx = 0;
    gc.gridy = 1;//Second Row
    inputPanel.add(inputFileField,gc);
    
    gc.anchor = GridBagConstraints.PAGE_END; //bottom of space
    gc.insets = new Insets(10,0,0,0);  //top padding
    gc.gridx = 2;
    gc.gridwidth = 1;
    gc.gridy = 2;//Third Row
    inputPanel.add(inputButton,gc);
    
    add(inputPanel); //adding panel to frame
  }
  
  public class requestHandler implements ActionListener //request handler which listens for defined action
  {
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == inputButton) //if button is clicked
      {
        if(inputFileField.getText().isEmpty())//check if button is empty
        {
          System.out.println("Field is empty: No filename Entered");//print error if empty
        	
        }
        else
        {
          try // start of try catch block
          { 
            //Open file and check for errors
            File file = new File(inputFileField.getText());//getting text from input field for file
            Scanner fileName;
            fileName = new Scanner(file); //Instantiate file scanner and scan contents of file.
            
            //close window
            dispose();//disposale of first window.
            
            //Open the Destinations Window
            DestinationsFrame destinationFrame = new DestinationsFrame(fileName); //Instantiate destinations frame
            /******SETTING SIZE AND BASICE WINDOW FEATURES************/
            destinationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            destinationFrame.setSize(700, 319);
            destinationFrame.setLocationRelativeTo(null);
            destinationFrame.setVisible(true); 
            
          } 
          catch (FileNotFoundException e) //catch part of try catch block
          {
            System.out.println("File not found: " + e);//error print line if catch file not found.
          }
        }
      }
    }
  }
  
}
