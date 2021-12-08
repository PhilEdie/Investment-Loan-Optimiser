/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 17 "model.ump"
// line 41 "model.ump"
public class Loan extends Node
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Loan Attributes
  private float minimumPayment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Loan(float aInterestRate, float aValue, int aMonth, Program aProgram, float aMinimumPayment)
  {
    super(aInterestRate, aValue, aMonth, aProgram);
    minimumPayment = aMinimumPayment;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMinimumPayment(float aMinimumPayment)
  {
    boolean wasSet = false;
    minimumPayment = aMinimumPayment;
    wasSet = true;
    return wasSet;
  }

  public float getMinimumPayment()
  {
    return minimumPayment;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "minimumPayment" + ":" + getMinimumPayment()+ "]";
  }
}