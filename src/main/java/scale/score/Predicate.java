package scale.score;

import scale.score.chords.BeginChord;
import scale.score.chords.EndChord;
import scale.score.chords.ExitChord;
import scale.score.chords.ExprChord;
import scale.score.chords.GotoChord;
import scale.score.chords.IfThenElseChord;
import scale.score.chords.LoopExitChord;
import scale.score.chords.LoopHeaderChord;
import scale.score.chords.LoopInitChord;
import scale.score.chords.LoopPreHeaderChord;
import scale.score.chords.LoopTailChord;
import scale.score.chords.MarkerChord;
import scale.score.chords.NullChord;
import scale.score.chords.PhiExprChord;
import scale.score.chords.ReturnChord;
import scale.score.chords.SwitchChord;
import scale.score.expr.AbsoluteValueExpr;
import scale.score.expr.AdditionExpr;
import scale.score.expr.AllocateExpr;
import scale.score.expr.AndExpr;
import scale.score.expr.ArrayIndexExpr;
import scale.score.expr.BitAndExpr;
import scale.score.expr.BitComplementExpr;
import scale.score.expr.BitOrExpr;
import scale.score.expr.BitShiftExpr;
import scale.score.expr.BitXorExpr;
import scale.score.expr.CallFunctionExpr;
import scale.score.expr.CallMethodExpr;
import scale.score.expr.CompareExpr;
import scale.score.expr.ComplexValueExpr;
import scale.score.expr.ConditionalExpr;
import scale.score.expr.ConversionExpr;
import scale.score.expr.DivisionExpr;
import scale.score.expr.DualExpr;
import scale.score.expr.EqualityExpr;
import scale.score.expr.ExponentiationExpr;
import scale.score.expr.ExprPhiExpr;
import scale.score.expr.GreaterEqualExpr;
import scale.score.expr.GreaterExpr;
import scale.score.expr.LessEqualExpr;
import scale.score.expr.LessExpr;
import scale.score.expr.LiteralExpr;
import scale.score.expr.LoadDeclAddressExpr;
import scale.score.expr.LoadDeclValueExpr;
import scale.score.expr.LoadFieldAddressExpr;
import scale.score.expr.LoadFieldValueExpr;
import scale.score.expr.LoadValueIndirectExpr;
import scale.score.expr.MaxExpr;
import scale.score.expr.MinExpr;
import scale.score.expr.MultiplicationExpr;
import scale.score.expr.NegativeExpr;
import scale.score.expr.NilExpr;
import scale.score.expr.NotEqualExpr;
import scale.score.expr.NotExpr;
import scale.score.expr.OrExpr;
import scale.score.expr.PhiExpr;
import scale.score.expr.RemainderExpr;
import scale.score.expr.SubscriptExpr;
import scale.score.expr.SubtractionExpr;
import scale.score.expr.Transcendental2Expr;
import scale.score.expr.TranscendentalExpr;
import scale.score.expr.VaArgExpr;
import scale.score.expr.VaEndExpr;
import scale.score.expr.VaStartExpr;
import scale.score.expr.VectorExpr;

/**
 * This interface defines a Scribble CFG traversal predicate.
 * <p/>
 * $Id: Predicate.java,v 1.57 2007-03-21 13:32:07 burrill Exp $
 * <p/>
 * Copyright 2007 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 * <p/>
 * A traversal predicate has a method per instantiable subclass of
 * a Score node.  Each method represents an action to be done when
 * visiting a node of that type during a traversal of a program
 * representation.  Predicates allow logically related code to be
 * grouped together (in an implementation of the predicate).
 *
 * @see Note#visit
 */

public interface Predicate
{
    public void visitAbsoluteValueExpr(AbsoluteValueExpr e);

    public void visitAdditionExpr(AdditionExpr e);

    public void visitAllocateExpr(AllocateExpr e);

    public void visitAndExpr(AndExpr e);

    public void visitBeginChord(BeginChord c);

    public void visitBitAndExpr(BitAndExpr e);

    public void visitBitComplementExpr(BitComplementExpr e);

    public void visitBitOrExpr(BitOrExpr e);

    public void visitBitShiftExpr(BitShiftExpr e);

    public void visitBitXorExpr(BitXorExpr e);

    public void visitCallFunctionExpr(CallFunctionExpr e);

    public void visitCallMethodExpr(CallMethodExpr e);

    public void visitCompareExpr(CompareExpr e);

    public void visitComplexValueExpr(ComplexValueExpr e);

    public void visitConditionalExpr(ConditionalExpr e);

    public void visitConversionExpr(ConversionExpr e);

    public void visitDivisionExpr(DivisionExpr e);

    public void visitDualExpr(DualExpr e);

    public void visitEndChord(EndChord c);

    public void visitEqualityExpr(EqualityExpr e);

    public void visitExitChord(ExitChord c);

    public void visitExponentiationExpr(ExponentiationExpr e);

    public void visitExprChord(ExprChord c);

    public void visitExprPhiExpr(ExprPhiExpr e);

    public void visitGotoChord(GotoChord c);

    public void visitGreaterEqualExpr(GreaterEqualExpr e);

    public void visitGreaterExpr(GreaterExpr e);

    public void visitIfThenElseChord(IfThenElseChord c);

    public void visitLessEqualExpr(LessEqualExpr e);

    public void visitLessExpr(LessExpr e);

    public void visitLiteralExpr(LiteralExpr e);

    public void visitLoadDeclAddressExpr(LoadDeclAddressExpr e);

    public void visitLoadDeclValueExpr(LoadDeclValueExpr e);

    public void visitLoadFieldValueExpr(LoadFieldValueExpr e);

    public void visitLoadFieldAddressExpr(LoadFieldAddressExpr e);

    public void visitLoadValueIndirectExpr(LoadValueIndirectExpr e);

    public void visitLoopExitChord(LoopExitChord c);

    public void visitLoopHeaderChord(LoopHeaderChord c);

    public void visitLoopPreHeaderChord(LoopPreHeaderChord c);

    public void visitLoopTailChord(LoopTailChord c);

    public void visitLoopInitChord(LoopInitChord c);

    public void visitMarkerChord(MarkerChord c);

    public void visitMaxExpr(MaxExpr e);

    public void visitMinExpr(MinExpr e);

    public void visitMultiplicationExpr(MultiplicationExpr e);

    public void visitNegativeExpr(NegativeExpr e);

    public void visitNilExpr(NilExpr e);

    public void visitNotEqualExpr(NotEqualExpr e);

    public void visitNotExpr(NotExpr e);

    public void visitNullChord(NullChord c);

    public void visitOrExpr(OrExpr e);

    public void visitPhiExpr(PhiExpr e);

    public void visitPhiExprChord(PhiExprChord c);

    public void visitRemainderExpr(RemainderExpr e);

    public void visitReturnChord(ReturnChord c);

    public void visitSubscriptExpr(SubscriptExpr e);

    public void visitArrayIndexExpr(ArrayIndexExpr e);

    public void visitSubtractionExpr(SubtractionExpr e);

    public void visitSwitchChord(SwitchChord c);

    public void visitTranscendentalExpr(TranscendentalExpr e);

    public void visitTranscendental2Expr(Transcendental2Expr e);

    public void visitVaArgExpr(VaArgExpr e);

    public void visitVaEndExpr(VaEndExpr e);

    public void visitVaStartExpr(VaStartExpr e);

    public void visitVectorExpr(VectorExpr e);
}
