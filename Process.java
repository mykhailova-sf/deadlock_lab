import java.io.*;
//import Resource ;
import java.util.Vector ;

public class Process
{
  public static final int STATE_UNKNOWN = 0 ;
  public static final int STATE_COMPUTABLE = 1 ;
  public static final int STATE_RESOURCE_WAIT = 2 ;
  public static final int STATE_HALT = 3 ;

  protected int id ;
  private String filename = "" ;
  protected CommandParser cp ;
  protected int state = STATE_UNKNOWN ;
  protected int timeToCompute ;
  protected Resource resourceAwaiting = null ;
private Vector<Resource> allocatedResources = new Vector<>();
  public Process( int newId , String newFilename )
  {
    super() ;
    id = newId ;
    filename = newFilename ;
  }

  public int getId()
  {
    return id ;
  }

  public String getFilename()
  {
    return filename ;
  }

  public void setFilename(String newFilename)
  {
    filename = newFilename ;
  }

  public String getState()
  {
    switch( state )
    {
      case STATE_RESOURCE_WAIT:
        return "W" ;
      case STATE_COMPUTABLE:
        return "C" ;
      case STATE_HALT:
        return "H" ;
      default:
        return "U";
    }
  }

  public void setState( String newState )
  {
  if ( newState.equals("W") )
    state = STATE_RESOURCE_WAIT ;
  else if ( newState.equals("C") )
    state = STATE_COMPUTABLE ;
  else if ( newState.equals("H") )
    state = STATE_HALT ;
  else
    state = STATE_UNKNOWN ;
  }

  public Resource getResourceAwaiting( )
  {
    return resourceAwaiting ;
  }

  public void addAllocatedResource( Resource resource )
  {
    allocatedResources.addElement( resource ) ;
  }

  public void removeAllocatedResource( Resource resource )
  {
    allocatedResources.removeElement( resource ) ;
  }

  public void reset() throws IOException 
  {
    cp = new CommandParser( new BufferedInputStream( new FileInputStream( filename ) ) ) ;
    state = STATE_UNKNOWN ;
    timeToCompute = 0 ;
    resourceAwaiting = null ;
    allocatedResources.removeAllElements() ;
  }

}
