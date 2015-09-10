package scale.clef.expr;

import scale.clef.Predicate;
import scale.clef.type.Type;

/**
 * A class which represents the va_arg(va_list, type) C construct in the Clef AST.
 * <p/>
 * $Id: VaArgOp.java,v 1.19 2005-02-07 21:28:04 burrill Exp $
 * <p/>
 * Copyright 2005 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 */

public class VaArgOp
        extends VarArgOp
{
    public VaArgOp(Type type, Expression va_list)
    {
        super(type, va_list);
    }

    public void visit(Predicate p)
    {
        p.visitVaArgOp(this);
    }
}
