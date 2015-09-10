package scale.clef.expr;

import scale.clef.Predicate;
import scale.clef.type.BooleanType;
import scale.clef.type.Type;

/**
 * This class represnts the test for less than.
 * <p/>
 * $Id: LessOp.java,v 1.29 2005-11-29 13:38:23 burrill Exp $
 * <p/>
 * Copyright 2005 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 */

public class LessOp
        extends DyadicOp
{
    public LessOp(Type type, Expression e1, Expression e2)
    {
        super(type, e1, e2);
    }

    public LessOp(Expression e1, Expression e2)
    {
        this(BooleanType.type, e1, e2);
    }

    public void visit(Predicate p)
    {
        p.visitLessOp(this);
    }

    /**
     * Return the constant value of the expression.
     *
     * @see scale.common.Lattice
     */
    public Literal getConstantValue()
    {
        Expression la = getExpr1();
        Expression ra = getExpr2();

        Literal lac = la.getConstantValue();
        Literal rac = ra.getConstantValue();

        if (la.getType().isSigned()) {
            return scale.common.Lattice.less(BooleanType.type, lac, rac);
        }
        return scale.common.Lattice.lessUnsigned(BooleanType.type, lac, rac);
    }

    /**
     * Return true if the result of the expression is either true (1) or false (0).
     */
    public boolean hasTrueFalseResult()
    {
        return true;
    }
}
