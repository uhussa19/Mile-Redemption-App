/************************************************************
 *                                                          *
 *  CSCI 470/680     Assignment 5       Summer 2018         *
 *                                                          *
 *  Programmer:  Usman Hussain                              *
 *                                                          *
 *                                                          *
 *  Date Due:    07/30/2018                                 *
 *                                                          *
 *  Use:         Destiantions Class - Implementation        *
 *                                                          *
 ************************************************************/

class Destination
{
  //Private Variables
  private String city;
  private int normalMiles;
  private int cheapMiles;
  private int upgradeMiles;
  private int startMonth;
  private int endMonth; 
  
  //Constructor
  public Destination(String city, int miles, int cheapMiles, int upMiles, int startMonth, int endMonth)
  {
    this.city = city;
    this.normalMiles = miles;
    this.cheapMiles = cheapMiles;
    this.upgradeMiles = upMiles;
    this.startMonth = startMonth;
    this.endMonth = endMonth;
  }
  
  //Making a Guess on this method from notes
  public int compareTo(Object a)
  {
    Destination i = (Destination)a;
    if(this.getNormalMiles() > i.getNormalMiles())
    {
      return -1;
    }
    else
    {
      return 0;
    }
  }
  //Get Methods
  public String getCity()
  {
    return this.city;
  }
  public int getNormalMiles()
  {
    return this.normalMiles;
  }
  public int getCheapMiles()
  {
    return this.cheapMiles;
  }
  public int getUpgradeMiles()
  {
    return this.upgradeMiles;
  }
  public int getStartMonth()
  {
    return this.startMonth;
  }
  public int getEndMonth()
  {
    return this.endMonth;
  }
  
  //Set Methods
  public void setCity(String city)
  {
    this.city = city;
  }
  public void setNormalMiles(int normalMiles)
  {
    this.normalMiles = normalMiles;
  }
  public void setCheapMiles(int cheapMiles)
  {
    this.cheapMiles = cheapMiles;
  }
  public void setUpgradeMiles(int upgradeMiles)
  {
    this.upgradeMiles = upgradeMiles;
  }
  public void setStartMonth(int startMonth)
  {
    this.startMonth = startMonth;
  }
  public void setEndMonth(int endMonth)
  {
    this.endMonth = endMonth;
  }
  
  
}