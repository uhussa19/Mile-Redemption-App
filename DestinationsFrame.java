/************************************************************
 *                                                          *
 *  CSCI 470/680     Assignment 5       Summer 2018         *                        
 *                                                          *
 *  Programmer:  Usman Hussain                              *  
 *                                                          *   
 *                                                          *
 *  Date Due:    07/30/2018                                 *
 *                                                          *
 *  Use:         DestinationsFrame Class - Implementation   *
 *                                                          *
 ************************************************************/ 
/*************IMPORTS USED FOR PROGRAM******************/
import java.util.Scanner;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.TitledBorder;


public class DestinationsFrame extends JFrame
{ 
/***************COMPONENT VARIABLES USED IN PROGRAM ****************/
  private String[] months;
  private MileRedeemer userQuote;
  
  
  private final JList<String> destinationJList; // jlist to display destinations
  private final JScrollPane scrollPane,
    textscrollPane;
  private final JPanel leftPanel, 
    rightPanel;
  private final JTextArea textArea;
  private final JLabel norMiles, 
    supMiles, 
    upCost, 
    supDates,
    enterMilesLabel,
    remainingMilesLabel,
    selectMonthLabel;
  private final JTextField norMileOutput,
    supMilesOutput, 
    upCostOutput, 
    supDatesOutput,
    enterMilesField,
    remainingMilesField;
  private final JSpinner spinner;
  private final JButton redeemButton;
  
  //Constructor
  public DestinationsFrame(Scanner fileName)
  {
    super("Mile Redeemer Application");// name of frame
    //Instantiate Redeember Object
    userQuote = new MileRedeemer();//Instantiate Redeemer Object
    userQuote.readDestination(fileName);
    
    //Layout for Panels
    setResizable(false);
    //setLayout(new GridLayout(1,2)); //set frame layout
    setLayout(new BorderLayout()); //set frame layout
    
    /**********MAIN PANELS USED IN FRAME*************/
    leftPanel = new JPanel();
    rightPanel = new JPanel();
    
    //Create Border for left panel
    TitledBorder leftBorder = new TitledBorder("Destinations");
    leftBorder.setTitleJustification(TitledBorder.CENTER);
    leftBorder.setTitlePosition(TitledBorder.TOP);
    
    //Set background color and border
    leftPanel.setBackground(new Color(45,147,74));
    leftPanel.setBorder(leftBorder);
    leftPanel.setPreferredSize(new Dimension(295,319));//these dont matter
    
    //Create Border for right panel
    TitledBorder rightBorder = new TitledBorder("Redeem Miles");
    rightBorder.setTitleJustification(TitledBorder.CENTER);
    rightBorder.setTitlePosition(TitledBorder.TOP);
    
    //Set background color and border
    rightPanel.setBackground(new Color(83,45,147));
    rightPanel.setBorder(rightBorder);
    rightPanel.setPreferredSize(new Dimension(400,319));//these dont matter
    
    //List Box
    destinationJList = new JList<String>(userQuote.getCityNames());
    destinationJList.setVisibleRowCount(10);
    destinationJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    scrollPane = new JScrollPane(destinationJList);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    destinationJList.addListSelectionListener(new listHandler());
    //Text Area
    textArea = new JTextArea(10,30);
    textArea.setEditable(false);
    textscrollPane = new JScrollPane(textArea);
    textscrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    
    //Button
    redeemButton = new JButton("Redeem miles");
    redeemButton.addActionListener(new actionHandler());
    
    //Spinner
    months = getMonthStrings();
    SpinnerListModel monthModel = new SpinnerListModel(months);
    spinner = new JSpinner(monthModel);
    
    //Labels
    norMiles = new JLabel("Normal Miles", SwingConstants.LEFT);
    supMiles = new JLabel("Super Saver Miles", SwingConstants.LEFT);
    upCost = new JLabel("Upgrade Cost", SwingConstants.LEFT);
    supDates = new JLabel("Super Saver Dates", SwingConstants.LEFT);
    enterMilesLabel = new JLabel("Enter your miles", SwingConstants.LEFT);
    remainingMilesLabel = new JLabel("Your remaining miles", SwingConstants.LEFT);
    selectMonthLabel = new JLabel("Select the month of departure", SwingConstants.LEFT);
    
    //Text Fields
    norMileOutput = new JTextField("0",10);
    norMileOutput.setEditable(false);
    norMileOutput.setHorizontalAlignment(SwingConstants.LEFT);
    
    supMilesOutput = new JTextField("0",10);
    supMilesOutput.setEditable(false);
    supMilesOutput.setHorizontalAlignment(SwingConstants.LEFT);
    
    upCostOutput = new JTextField("0",10);
    upCostOutput.setEditable(false);
    upCostOutput.setHorizontalAlignment(SwingConstants.LEFT);
    
    supDatesOutput = new JTextField("0",10);
    supDatesOutput.setEditable(false);
    supDatesOutput.setHorizontalAlignment(SwingConstants.LEFT);
    
    remainingMilesField = new JTextField("0",10);
    remainingMilesField.setEditable(false);
    remainingMilesField.setHorizontalAlignment(SwingConstants.LEFT);
    
    enterMilesField = new JTextField(10);
    
    //Add in Components
    leftPanel.setLayout(new GridBagLayout());
    rightPanel.setLayout(new GridBagLayout());
    
    GridBagConstraints gc = new GridBagConstraints();
    
    //Left Side Panel
    //First Row    
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.weightx = 0.5;
    gc.gridx = 0;
    gc.gridy = 0;
    gc.gridwidth = 2;
    leftPanel.add(scrollPane,gc);
    
    //Second Row
    //gc.fill = GridBagConstraints.HORIZONTAL;
    gc.gridwidth = 1;
    gc.weightx = 0.5;
    gc.gridx = 0;
    gc.gridy = 1;
    leftPanel.add(norMiles,gc);
    
    //gc.fill = GridBagConstraints.HORIZONTAL;
    gc.weightx = 0.5;
    gc.gridx = 1;
    gc.gridy = 1;
    leftPanel.add(norMileOutput,gc);
    
    //Third Row
    gc.gridx = 0;
    gc.gridy = 2;
    leftPanel.add(supMiles,gc);
    
    gc.gridx = 1;
    gc.gridy = 2;
    leftPanel.add(supMilesOutput,gc);
    
    //Fourth Row
    gc.gridx = 0;
    gc.gridy = 3;
    leftPanel.add(upCost,gc);
    
    gc.gridx = 1;
    gc.gridy = 3;
    leftPanel.add(upCostOutput,gc);
    
    //Fifth Row
    gc.gridx = 0;
    gc.gridy = 4;
    leftPanel.add(supDates,gc);
    
    gc.gridx = 1;
    gc.gridy = 4;
    leftPanel.add(supDatesOutput,gc);
    
    ////Start Right Panel
    //First Row
    gc.gridx = 0;
    gc.gridy = 0;
    rightPanel.add(enterMilesLabel,gc);
    gc.gridx = 1;
    gc.gridy = 0;
    rightPanel.add(enterMilesField,gc);
    
    //Second Row
    gc.gridx = 0;
    gc.gridy = 1;
    rightPanel.add(selectMonthLabel,gc);
    gc.gridx = 1;
    gc.gridy = 1;
    rightPanel.add(spinner,gc);
    
    //Third Row
    gc.gridwidth = 2;
    gc.gridx = 0;
    gc.gridy = 2;
    rightPanel.add(redeemButton,gc);
    
    //Fourth Row
    gc.weighty = 0.5;
    gc.gridx = 0;
    gc.gridy = 3;
    rightPanel.add(textscrollPane,gc);
    
    //Fifth Row
    gc.gridwidth = 1;
    gc.gridx = 0;
    gc.gridy = 4;
    rightPanel.add(remainingMilesLabel,gc);
    
    gc.gridx = 1;
    gc.gridy = 4;
    rightPanel.add(remainingMilesField,gc);
    
    //Add Left and Right side after pieces have been built
    add(leftPanel,BorderLayout.WEST);
    add(rightPanel,BorderLayout.EAST);
  }
  
  //Creates an array of months
  protected String[] getMonthStrings() 
  {
    String[] months = new java.text.DateFormatSymbols().getMonths();
    
    int lastIndex = months.length - 1;
    
    if (months[lastIndex] == null || months[lastIndex].length() <= 0)  
    { 
      String[] monthStrings = new String[lastIndex];
      System.arraycopy(months, 0, monthStrings, 0, lastIndex);
      return monthStrings;
    }
    else 
    { 
      return months;
    }
  }

  public class actionHandler implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == redeemButton)
      {
        if(enterMilesField.getText().isEmpty())
        {
          System.out.println("Value entered in the 'Entered Miles Field' is empty.");
        }
        else
        {
          try
          {
            //Clear the fields
            textArea.setText("");
            remainingMilesField.setText("");
            
            //Grab incoming data and ensure it is in proper format and length
            int selectedMonth = Integer.parseInt(enterMilesField.getText());
            int spinnerValue = Arrays.asList(months).indexOf(spinner.getValue()) + 1;
            
            //Generate a list of possible destinations from data
            String [] tempAr = userQuote.redeemMiles(selectedMonth,spinnerValue);
            
            //Create a Loop and run through list
            for(String value : tempAr)
            {
              textArea.append(value + "\n");
            }
            //ouput remaining miles
            remainingMilesField.setText(Integer.toString(userQuote.getRemainingMiles()));
          } 
          catch (NumberFormatException ex)
          {
            System.out.println("Incorrect Data Type Entered: " + ex);
          }
        }
      }
    }
  }
  
  public class listHandler implements ListSelectionListener
  {
    public void valueChanged(ListSelectionEvent event)
    {
      boolean changed = event.getValueIsAdjusting();
      if (changed)
      {
        //Create a Destination object from the destination found from user selection
        Destination tempDest = userQuote.findDestination(destinationJList.getSelectedValue());
        
        //Display object data in appropriate textFields
        norMileOutput.setText(Integer.toString(tempDest.getNormalMiles()));
        supMilesOutput.setText(Integer.toString(tempDest.getCheapMiles()));
        upCostOutput.setText(Integer.toString(tempDest.getUpgradeMiles()));
        supDatesOutput.setText(months[tempDest.getStartMonth()-1] + " - " + months[tempDest.getEndMonth()-1]);
      }
    }
  }
}