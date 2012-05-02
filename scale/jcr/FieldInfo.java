package scale.jcr;

import java.io.*;

/**
 * This class is used to both represent a Java class file field info
 * structure and to read that class file field info structure.
 * <p>
 * $Id: FieldInfo.java,v 1.9 2007-01-04 17:01:16 burrill Exp $
 * <p>
 * Copyright 2006 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 */
public class FieldInfo extends Info
{
  public FieldInfo(int accessFlags, int nameIndex, int descriptorIndex, AttributeInfo[] attributes)
  {
    super(accessFlags, nameIndex, descriptorIndex, attributes);
  }

  public static FieldInfo read(ClassFile cf, DataInputStream reader) throws java.io.IOException
  {
    int accessFlags     = reader.readUnsignedShort();
    int nameIndex       = reader.readUnsignedShort();
    int descriptorIndex = reader.readUnsignedShort();
    int attributeCount  = reader.readUnsignedShort();

    AttributeInfo[] attributes = new AttributeInfo[attributeCount];
    for (int i = 0; i < attributeCount; i++)
      attributes[i] = AttributeInfo.read(cf, reader);

    return new FieldInfo(accessFlags, nameIndex, descriptorIndex, attributes);
  }
}
