/************************************************************
 *                                                          *
 *  CSCI 470/680     Assignment 5       Summer 2018         *                        
 *                                                          *
 *  Programmer:  Usman Hussain                              *  
 *               				                            *   
 *                                                          *
 *  Date Due:    07/30/2018                                 *
 *                                                          *
 *  Use:         Comparator Class - Implementation          *
 *                                                          *
 ************************************************************/
import java.util.Comparator;

public class MileageComparator implements Comparator<Destination> 
{
  public int compare(Destination d1, Destination d2) 
  {
    return (d2.getNormalMiles() - d1.getNormalMiles());
  }
}