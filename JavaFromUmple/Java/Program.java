/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 2 "model.ump"
// line 34 "model.ump"
public class Program
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Program Associations
  private Node rootNode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Program(Node aRootNode)
  {
    if (aRootNode == null || aRootNode.getProgram() != null)
    {
      throw new RuntimeException("Unable to create Program due to aRootNode. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    rootNode = aRootNode;
  }

  public Program(float aInterestRateForRootNode, float aValueForRootNode, int aMonthForRootNode)
  {
    rootNode = new Node(aInterestRateForRootNode, aValueForRootNode, aMonthForRootNode, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Node getRootNode()
  {
    return rootNode;
  }

  public void delete()
  {
    Node existingRootNode = rootNode;
    rootNode = null;
    if (existingRootNode != null)
    {
      existingRootNode.delete();
    }
  }

}