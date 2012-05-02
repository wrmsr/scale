package scale.jcr;

import java.io.*;

/**
 * This class is used to both represent a Java class file float
 * constant pool entry and to read that class file float constant pool
 * entry.
 * <p>
 * $Id: FloatCPInfo.java,v 1.9 2007-10-04 19:58:16 burrill Exp $
 * <p>
 * Copyright 2007 by the
 * <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 */
public class FloatCPInfo extends CPInfo
{
  private float value;

  public FloatCPInfo(float value)
  {
    super(CPInfo.CONSTANT_Float);
    this.value = value;
  }

  public float getValue()
  {
    return value;
  }

  public static CPInfo read(ClassFile       cf,
                            DataInputStream reader) throws java.io.IOException
  {
    float value = reader.readFloat();
    return new FloatCPInfo(value);
  }
}
