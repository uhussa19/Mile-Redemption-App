/************************************************************
 *                                                          *
 *  CSCI 470/680     Assignment 5       Summer 2018         *                        
 *                                                          *
 *  Programmer:  Usman Hussain                              *  
 *                                                          *   
 *                                                          *
 *  Date Due:    07/30/2018                                 *
 *                                                          *
 *  Use:         MileRedemption App Class - Implementation  *
 *                                                          *
 ************************************************************/ 
import javax.swing.JFrame;

class MileRedemptionApp
{
  public static void main(String[] args)
  {
    //Open a Window to call the File Opener
    FileFrame fileFrame = new FileFrame(); //instantiate the file frame
    /******SETTING SIZE AND BASICE WINDOW FEATURES************/
    fileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fileFrame.setSize(300, 150);
    fileFrame.setLocationRelativeTo(null);
    fileFrame.setVisible(true); 
  }
}