
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package util;

import java.io.*;

public class deepCloneUtil {

  public deepCloneUtil() {
  }
  
  public static Object deepClone( Object objToClone )
  {
  try
  {
  ByteArrayOutputStream baos = new ByteArrayOutputStream();
  ObjectOutputStream oos = new ObjectOutputStream(baos);
  oos.writeObject(objToClone);
  ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
  ObjectInputStream ois = new ObjectInputStream(bais);
  Object deepCopy = ois.readObject();

  return deepCopy;
  }
  catch( Exception e )
  { 
  System.out.println( "deep clone failed" );
  e.printStackTrace();
  return null;
  }
  
  }
}