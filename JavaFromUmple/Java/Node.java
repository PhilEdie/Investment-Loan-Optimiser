/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 7 "model.ump"
// line 51 "model.ump"
public class Node
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Node Attributes
  private float interestRate;
  private float value;
  private int month;

  //Node Associations
  private List<Node> children;
  private Program program;
  private Node parentNode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Node(float aInterestRate, float aValue, int aMonth, Program aProgram)
  {
    interestRate = aInterestRate;
    value = aValue;
    month = aMonth;
    children = new ArrayList<Node>();
    if (aProgram == null || aProgram.getRootNode() != null)
    {
      throw new RuntimeException("Unable to create Node due to aProgram. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    program = aProgram;
  }

  public Node(float aInterestRate, float aValue, int aMonth)
  {
    interestRate = aInterestRate;
    value = aValue;
    month = aMonth;
    children = new ArrayList<Node>();
    program = new Program(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setInterestRate(float aInterestRate)
  {
    boolean wasSet = false;
    interestRate = aInterestRate;
    wasSet = true;
    return wasSet;
  }

  public boolean setValue(float aValue)
  {
    boolean wasSet = false;
    value = aValue;
    wasSet = true;
    return wasSet;
  }

  public boolean setMonth(int aMonth)
  {
    boolean wasSet = false;
    month = aMonth;
    wasSet = true;
    return wasSet;
  }

  public float getInterestRate()
  {
    return interestRate;
  }

  public float getValue()
  {
    return value;
  }

  public int getMonth()
  {
    return month;
  }
  /* Code from template association_GetMany */
  public Node getChild(int index)
  {
    Node aChild = children.get(index);
    return aChild;
  }

  public List<Node> getChildren()
  {
    List<Node> newChildren = Collections.unmodifiableList(children);
    return newChildren;
  }

  public int numberOfChildren()
  {
    int number = children.size();
    return number;
  }

  public boolean hasChildren()
  {
    boolean has = children.size() > 0;
    return has;
  }

  public int indexOfChild(Node aChild)
  {
    int index = children.indexOf(aChild);
    return index;
  }
  /* Code from template association_GetOne */
  public Program getProgram()
  {
    return program;
  }
  /* Code from template association_GetOne */
  public Node getParentNode()
  {
    return parentNode;
  }

  public boolean hasParentNode()
  {
    boolean has = parentNode != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfChildren()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addChild(Node aChild)
  {
    boolean wasAdded = false;
    if (children.contains(aChild)) { return false; }
    Node existingParentNode = aChild.getParentNode();
    if (existingParentNode == null)
    {
      aChild.setParentNode(this);
    }
    else if (!this.equals(existingParentNode))
    {
      existingParentNode.removeChild(aChild);
      addChild(aChild);
    }
    else
    {
      children.add(aChild);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeChild(Node aChild)
  {
    boolean wasRemoved = false;
    if (children.contains(aChild))
    {
      children.remove(aChild);
      aChild.setParentNode(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addChildAt(Node aChild, int index)
  {  
    boolean wasAdded = false;
    if(addChild(aChild))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChildren()) { index = numberOfChildren() - 1; }
      children.remove(aChild);
      children.add(index, aChild);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveChildAt(Node aChild, int index)
  {
    boolean wasAdded = false;
    if(children.contains(aChild))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChildren()) { index = numberOfChildren() - 1; }
      children.remove(aChild);
      children.add(index, aChild);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addChildAt(aChild, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setParentNode(Node aParentNode)
  {
    boolean wasSet = false;
    Node existingParentNode = parentNode;
    parentNode = aParentNode;
    if (existingParentNode != null && !existingParentNode.equals(aParentNode))
    {
      existingParentNode.removeChild(this);
    }
    if (aParentNode != null)
    {
      aParentNode.addChild(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !children.isEmpty() )
    {
      children.get(0).setParentNode(null);
    }
    Program existingProgram = program;
    program = null;
    if (existingProgram != null)
    {
      existingProgram.delete();
    }
    if (parentNode != null)
    {
      Node placeholderParentNode = parentNode;
      this.parentNode = null;
      placeholderParentNode.removeChild(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "interestRate" + ":" + getInterestRate()+ "," +
            "value" + ":" + getValue()+ "," +
            "month" + ":" + getMonth()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "program = "+(getProgram()!=null?Integer.toHexString(System.identityHashCode(getProgram())):"null");
  }
}