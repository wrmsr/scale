package scale.clef.expr;

import scale.clef.Predicate;
import scale.clef.type.Type;

/**
 * This class represents the <tt>++x</t> operator.
 * <p/>
 * $Id: PreIncrementOp.java,v 1.24 2005-02-07 21:28:02 burrill Exp $
 * <p/>
 * Copyright 2005 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 */

public class PreIncrementOp
        extends IncrementOp
{
    public PreIncrementOp(Type type, Expression e)
    {
        super(type, e);
    }

    public void visit(Predicate p)
    {
        p.visitPreIncrementOp(this);
    }
}
