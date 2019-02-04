/************************************************************
 *                                                          *
 *  CSCI 470/680     Assignment 5       Summer 2018         *                        
 *                                                          *
 *  Programmer:  Usman Hussain                              *  
 *               Nicholas Swanson                           *   
 *                                                          *
 *  Date Due:    07/30/2018                                 *
 *                                                          *
 *  Use:         MileRedeemer Class - Implementation        *
 *                                                          *
 ************************************************************/
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class MileRedeemer
{
  //Private Variables
  private ArrayList<Destination> destinations;
  private int remainingMiles = 0;
  
  //Constructor
  public MileRedeemer()
  {
    this.destinations = new ArrayList<Destination>();
  }
  
  //Public Methods
  public void readDestination(Scanner fileScanner)
  {
    //We grabbed info from the file now we run through it line by line and add it to the destinations
    while(fileScanner.hasNextLine())
    {
      //Parse the incoming line of data
      String[] splitData = fileScanner.nextLine().split(";");
      String[] splitMonth = splitData[4].split("-");
      //Add the Destination to the destinations instantiation
      this.destinations.add(new Destination(splitData[0],
                                            Integer.parseInt(splitData[1]),
                                            Integer.parseInt(splitData[2]),
                                            Integer.parseInt(splitData[3]),
                                            Integer.parseInt(splitMonth[0]),
                                            Integer.parseInt(splitMonth[1])));
    }
    //Sort - Collections must be imported to use the sort - Arrays.sort() will not work here
    //This sorts by highest normal mileage
    Collections.sort(this.destinations, new MileageComparator());
  }
  
  public String[] getCityNames()
  {
    String[] listedCities = new String[this.destinations.size()];
    for(int i = 0; i < this.destinations.size(); i++)
    {
      listedCities[i] = this.destinations.get(i).getCity();
    }
    //Sort - Arrays.sort() will work here
    Arrays.sort(listedCities);
    return listedCities;
  }
  
  public String[] redeemMiles(int miles, int month)
  {
    //Output to User
    ArrayList<String> infoOutput = new ArrayList<String>();    
    //temp list
    ArrayList<Destination> temp = new ArrayList<Destination>();
    
    //Get ticket combinations from farthest
    for(int i = 0; i < this.destinations.size();i++)
    {
      //Get a single destination from the Array of destinations
      Destination tempDest = this.destinations.get(i);
      //Get the cost of the current destination
      int mileageCost = tempDest.getNormalMiles();
      //Compare inputted month with start and end months
      if(month >= tempDest.getStartMonth() && month <= tempDest.getEndMonth())
      {
        mileageCost = tempDest.getCheapMiles();
      }
      //Check the milage of the destination to inputted miles
      if(miles > mileageCost)
      {
        //if there are miles left from the cost of the flight
        miles -= mileageCost;
        temp.add(tempDest);
      }
    }
    //We want to check for upgrades at this point wiht the temp list
    for(int i = 0; i < temp.size();i++)
    {
      //if the miles remaining allows for upgrade
      if(miles > temp.get(i).getUpgradeMiles())
      {
        miles -= temp.get(i).getUpgradeMiles();
        
        infoOutput.add("* A trip to " + temp.get(i).getCity() + ", first class");
      }
      else
      {
        infoOutput.add("* A trip to " + temp.get(i).getCity() + ", economy class");
      }
    }
    //Minus the mileage
    this.remainingMiles = miles;
    
    //Convert the ArrayList of String to an array of strings once we know the size of infoOutput
    String[] output = new String[infoOutput.size()];
    output = infoOutput.toArray(output);
    
    return output;
  }
  
  public int getRemainingMiles()
  {
    return remainingMiles;
  }
  
  public Destination findDestination(String cityName)
  {
    //Run a Loop to check for city
    Destination value = null;
    for(int i = 0; i < this.destinations.size();i++)
    {
      if(this.destinations.get(i).getCity() == cityName)
      {
        value = destinations.get(i);
      }
    }
    return value;
  }
}