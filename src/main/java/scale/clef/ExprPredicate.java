package scale.clef;

import scale.clef.expr.AbsoluteValueOp;
import scale.clef.expr.AdditionAssignmentOp;
import scale.clef.expr.AdditionOp;
import scale.clef.expr.AddressLiteral;
import scale.clef.expr.AddressOp;
import scale.clef.expr.AggregateOp;
import scale.clef.expr.AggregationElements;
import scale.clef.expr.AllocatePlacementOp;
import scale.clef.expr.AllocateSettingFieldsOp;
import scale.clef.expr.AndConditionalOp;
import scale.clef.expr.AndOp;
import scale.clef.expr.AssignSimpleOp;
import scale.clef.expr.AssignmentOp;
import scale.clef.expr.BitAndAssignmentOp;
import scale.clef.expr.BitAndOp;
import scale.clef.expr.BitComplementOp;
import scale.clef.expr.BitOrAssignmentOp;
import scale.clef.expr.BitOrOp;
import scale.clef.expr.BitShiftAssignmentOp;
import scale.clef.expr.BitShiftOp;
import scale.clef.expr.BitXorAssignmentOp;
import scale.clef.expr.BitXorOp;
import scale.clef.expr.BooleanLiteral;
import scale.clef.expr.CallFunctionOp;
import scale.clef.expr.CallOp;
import scale.clef.expr.CharLiteral;
import scale.clef.expr.ComplexLiteral;
import scale.clef.expr.ComplexOp;
import scale.clef.expr.CompoundAssignmentOp;
import scale.clef.expr.DefOp;
import scale.clef.expr.DeleteArrayOp;
import scale.clef.expr.DeleteOp;
import scale.clef.expr.DereferenceOp;
import scale.clef.expr.DivisionAssignmentOp;
import scale.clef.expr.DivisionOp;
import scale.clef.expr.DyadicOp;
import scale.clef.expr.EqualityOp;
import scale.clef.expr.ExponentiationOp;
import scale.clef.expr.Expression;
import scale.clef.expr.ExpressionIfOp;
import scale.clef.expr.FloatArrayLiteral;
import scale.clef.expr.FloatLiteral;
import scale.clef.expr.GreaterEqualOp;
import scale.clef.expr.GreaterOp;
import scale.clef.expr.HeapOp;
import scale.clef.expr.IdAddressOp;
import scale.clef.expr.IdReferenceOp;
import scale.clef.expr.IdValueOp;
import scale.clef.expr.IncrementOp;
import scale.clef.expr.IntArrayLiteral;
import scale.clef.expr.IntLiteral;
import scale.clef.expr.LessEqualOp;
import scale.clef.expr.LessOp;
import scale.clef.expr.Literal;
import scale.clef.expr.MaximumOp;
import scale.clef.expr.MinimumOp;
import scale.clef.expr.ModulusOp;
import scale.clef.expr.MonadicOp;
import scale.clef.expr.MultiplicationAssignmentOp;
import scale.clef.expr.MultiplicationOp;
import scale.clef.expr.NegativeOp;
import scale.clef.expr.NilOp;
import scale.clef.expr.NotEqualOp;
import scale.clef.expr.NotOp;
import scale.clef.expr.OrConditionalOp;
import scale.clef.expr.OrOp;
import scale.clef.expr.ParenthesesOp;
import scale.clef.expr.PositiveOp;
import scale.clef.expr.PostDecrementOp;
import scale.clef.expr.PostIncrementOp;
import scale.clef.expr.PreDecrementOp;
import scale.clef.expr.PreIncrementOp;
import scale.clef.expr.RemainderAssignmentOp;
import scale.clef.expr.RemainderOp;
import scale.clef.expr.SelectIndirectOp;
import scale.clef.expr.SelectOp;
import scale.clef.expr.SeriesOp;
import scale.clef.expr.SizeofLiteral;
import scale.clef.expr.StatementOp;
import scale.clef.expr.StringLiteral;
import scale.clef.expr.SubscriptAddressOp;
import scale.clef.expr.SubscriptOp;
import scale.clef.expr.SubscriptValueOp;
import scale.clef.expr.SubstringOp;
import scale.clef.expr.SubtractionAssignmentOp;
import scale.clef.expr.SubtractionOp;
import scale.clef.expr.TernaryOp;
import scale.clef.expr.ThisOp;
import scale.clef.expr.Transcendental2Op;
import scale.clef.expr.TranscendentalOp;
import scale.clef.expr.TypeConversionOp;
import scale.clef.expr.VaArgOp;
import scale.clef.expr.VaCopyOp;
import scale.clef.expr.VaEndOp;
import scale.clef.expr.VaStartOp;
import scale.clef.expr.VarArgOp;

/**
 * Predicate class for visit pattern of Clef Expressions.
 * <p/>
 * $Id: ExprPredicate.java,v 1.37 2007-05-10 16:47:59 burrill Exp $
 * <p/>
 * Copyright 2005 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 * <p/>
 *
 * @see scale.clef.Predicate
 */

public interface ExprPredicate
{
    public void visitAbsoluteValueOp(AbsoluteValueOp n);

    public void visitAdditionAssignmentOp(AdditionAssignmentOp n);

    public void visitAdditionOp(AdditionOp n);

    public void visitAddressLiteral(AddressLiteral n);

    public void visitAddressOp(AddressOp n);

    public void visitAggregateOp(AggregateOp n);

    public void visitAggregationElements(AggregationElements n);

    public void visitAllocatePlacementOp(AllocatePlacementOp n);

    public void visitAllocateSettingFieldsOp(AllocateSettingFieldsOp n);

    public void visitAndConditionalOp(AndConditionalOp n);

    public void visitAndOp(AndOp n);

    public void visitMonadicOp(MonadicOp n);

    public void visitAssignSimpleOp(AssignSimpleOp n);

    public void visitAssignmentOp(AssignmentOp n);

    public void visitBitAndAssignmentOp(BitAndAssignmentOp n);

    public void visitBitAndOp(BitAndOp n);

    public void visitBitComplementOp(BitComplementOp n);

    public void visitBitOrAssignmentOp(BitOrAssignmentOp n);

    public void visitBitOrOp(BitOrOp n);

    public void visitBitShiftAssignmentOp(BitShiftAssignmentOp n);

    public void visitBitShiftOp(BitShiftOp n);

    public void visitBitXorAssignmentOp(BitXorAssignmentOp n);

    public void visitBitXorOp(BitXorOp n);

    public void visitBooleanLiteral(BooleanLiteral n);

    public void visitCallFunctionOp(CallFunctionOp n);

    public void visitCallOp(CallOp n);

    public void visitCharLiteral(CharLiteral n);

    public void visitComplexLiteral(ComplexLiteral n);

    public void visitComplexOp(ComplexOp n);

    public void visitCompoundAssignmentOp(CompoundAssignmentOp n);

    public void visitDefOp(DefOp n);

    public void visitDeleteArrayOp(DeleteArrayOp n);

    public void visitDeleteOp(DeleteOp n);

    public void visitDereferenceOp(DereferenceOp n);

    public void visitDivisionAssignmentOp(DivisionAssignmentOp n);

    public void visitDivisionOp(DivisionOp n);

    public void visitDyadicOp(DyadicOp n);

    public void visitEqualityOp(EqualityOp n);

    public void visitExponentiationOp(ExponentiationOp n);

    public void visitExpression(Expression n);

    public void visitExpressionIfOp(ExpressionIfOp n);

    public void visitFloatArrayLiteral(FloatArrayLiteral n);

    public void visitFloatLiteral(FloatLiteral n);

    public void visitGreaterEqualOp(GreaterEqualOp n);

    public void visitGreaterOp(GreaterOp n);

    public void visitHeapOp(HeapOp n);

    public void visitIdAddressOp(IdAddressOp n);

    public void visitIdReferenceOp(IdReferenceOp n);

    public void visitIdValueOp(IdValueOp n);

    public void visitIncrementOp(IncrementOp n);

    public void visitIntArrayLiteral(IntArrayLiteral n);

    public void visitIntLiteral(IntLiteral n);

    public void visitLessEqualOp(LessEqualOp n);

    public void visitLessOp(LessOp n);

    public void visitLiteral(Literal n);

    public void visitMaximumOp(MaximumOp n);

    public void visitMinimumOp(MinimumOp n);

    public void visitModulusOp(ModulusOp n);

    public void visitMultiplicationAssignmentOp(MultiplicationAssignmentOp n);

    public void visitMultiplicationOp(MultiplicationOp n);

    public void visitNegativeOp(NegativeOp n);

    public void visitNilOp(NilOp n);

    public void visitNotEqualOp(NotEqualOp n);

    public void visitNotOp(NotOp n);

    public void visitOrConditionalOp(OrConditionalOp n);

    public void visitOrOp(OrOp n);

    public void visitParenthesesOp(ParenthesesOp n);

    public void visitPositiveOp(PositiveOp n);

    public void visitPostDecrementOp(PostDecrementOp n);

    public void visitPostIncrementOp(PostIncrementOp n);

    public void visitPreDecrementOp(PreDecrementOp n);

    public void visitPreIncrementOp(PreIncrementOp n);

    public void visitRemainderAssignmentOp(RemainderAssignmentOp n);

    public void visitRemainderOp(RemainderOp n);

    public void visitSelectIndirectOp(SelectIndirectOp n);

    public void visitSelectOp(SelectOp n);

    public void visitSeriesOp(SeriesOp n);

    public void visitSizeofLiteral(SizeofLiteral n);

    public void visitStatementOp(StatementOp n);

    public void visitStringLiteral(StringLiteral n);

    public void visitSubscriptAddressOp(SubscriptAddressOp n);

    public void visitSubscriptOp(SubscriptOp n);

    public void visitSubscriptValueOp(SubscriptValueOp n);

    public void visitSubstringOp(SubstringOp n);

    public void visitSubtractionAssignmentOp(SubtractionAssignmentOp n);

    public void visitSubtractionOp(SubtractionOp n);

    public void visitTernaryOp(TernaryOp n);

    public void visitThisOp(ThisOp n);

    public void visitTranscendentalOp(TranscendentalOp n);

    public void visitTranscendental2Op(Transcendental2Op n);

    public void visitTypeConversionOp(TypeConversionOp n);

    public void visitVaArgOp(VaArgOp n);

    public void visitVaCopyOp(VaCopyOp n);

    public void visitVaEndOp(VaEndOp n);

    public void visitVaStartOp(VaStartOp n);

    public void visitVarArgOp(VarArgOp n);
}
