package scale.clef.decl;

import scale.clef.Predicate;
import scale.clef.type.ProcedureType;

/**
 * Un-used.
 * <p/>
 * $Id: ForwardProcedureDecl.java,v 1.26 2007-03-21 13:31:51 burrill Exp $
 * <p/>
 * Copyright 2007 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 */

public class ForwardProcedureDecl
        extends ProcedureDecl
{
    public ForwardProcedureDecl(String name, ProcedureType type)
    {
        super(name, type);
    }

    public void visit(Predicate p)
    {
        p.visitForwardProcedureDecl(this);
    }

    /**
     * Return a copy of this Declaration but with a different name.
     */
    public Declaration copy(String name)
    {
        return new ForwardProcedureDecl(name, getSignature());
    }

    public final boolean isForwardProcedureDecl()
    {
        return true;
    }

    public final ForwardProcedureDecl returnForwardProcedureDecl()
    {
        return this;
    }
}
