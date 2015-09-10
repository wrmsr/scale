package scale.jcr;

import java.io.DataInputStream;

/**
 * This class is used to both represent a Java class file UTF8
 * constant pool entry and to read that class file UTF8 constant pool
 * entry.
 * <p/>
 * $Id: Utf8CPInfo.java,v 1.9 2007-10-04 19:58:17 burrill Exp $
 * <p/>
 * Copyright 2007 by the
 * <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 */
public class Utf8CPInfo
        extends CPInfo
{
    private String str;

    public Utf8CPInfo(String str)
    {
        super(CPInfo.CONSTANT_Utf8);
        this.str = str;
    }

    public String getString()
    {
        return str;
    }

    public static CPInfo read(ClassFile cf,
            DataInputStream reader)
            throws java.io.IOException
    {
        String str = reader.readUTF();
        return new Utf8CPInfo(str);
    }
}
