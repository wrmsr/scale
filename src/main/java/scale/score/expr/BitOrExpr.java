package scale.score.expr;

import scale.clef.LiteralMap;
import scale.clef.expr.IntLiteral;
import scale.clef.expr.Literal;
import scale.clef.type.Type;
import scale.common.HashMap;
import scale.common.Lattice;
import scale.score.Predicate;

/**
 * This class represents the bit or operation.
 * <p/>
 * $Id: BitOrExpr.java,v 1.26 2007-04-27 18:04:35 burrill Exp $
 * <p/>
 * Copyright 2008 by the
 * <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 */
public class BitOrExpr
        extends BinaryExpr
{
    public BitOrExpr(Type t, Expr e1, Expr e2)
    {
        super(t, e1, e2);
    }

    /**
     * The expression type is the same as the type of expression e1.
     */
    public BitOrExpr(Expr e1, Expr e2)
    {
        this(e1.getType(), e1, e2);
    }

    /**
     * This method of creating a BitOrExpr instance will return a
     * simpler expression if possible.
     */
    public static Expr create(Type type, Expr la, Expr ra)
    {
        if (la.isLiteralExpr() && ra.isLiteralExpr()) {
            Literal lat = ((LiteralExpr) la).getLiteral();
            Literal rat = ((LiteralExpr) ra).getLiteral();
            if ((lat instanceof IntLiteral) &&
                    (rat instanceof IntLiteral)) {
                long vl = ((IntLiteral) lat).getLongValue();
                long vr = ((IntLiteral) rat).getLongValue();
                return new LiteralExpr(LiteralMap.put(vl | vr, type));
            }
        }

        return new BitOrExpr(type, la, ra);
    }

    public Expr copy()
    {
        return new BitOrExpr(getType(), getLeftArg().copy(), getRightArg().copy());
    }

    public void visit(Predicate p)
    {
        p.visitBitOrExpr(this);
    }

    public String getDisplayLabel()
    {
        return "|";
    }

    public boolean isCommutative()
    {
        return true;
    }

    public boolean isAssociative()
    {
        return true;
    }

    /**
     * Return the constant value of the expression.
     * Follow use-def links.
     *
     * @see scale.common.Lattice
     */
    public Literal getConstantValue(HashMap<Expr, Literal> cvMap)
    {
        Literal r = cvMap.get(this);
        if (r != null) {
            return r;
        }

        associativeSwapOperands();

        Literal la = getLeftArg().getConstantValue(cvMap);
        Literal ra = getRightArg().getConstantValue(cvMap);

        r = Lattice.bitOr(getCoreType(), la, ra);

        cvMap.put(this, r);
        return r;
    }

    /**
     * Return the constant value of the expression.
     * Do not follow use-def links.
     *
     * @see scale.common.Lattice
     */
    public Literal getConstantValue()
    {
        associativeSwapOperands();

        Literal la = getLeftArg().getConstantValue();
        Literal ra = getRightArg().getConstantValue();

        return Lattice.bitOr(getCoreType(), la, ra);
    }
}
