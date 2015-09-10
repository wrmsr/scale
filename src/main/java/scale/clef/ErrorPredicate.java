package scale.clef;

import scale.clef.decl.CaseLabelDecl;
import scale.clef.decl.Declaration;
import scale.clef.decl.EnumElementDecl;
import scale.clef.decl.EquivalenceDecl;
import scale.clef.decl.ExceptionDecl;
import scale.clef.decl.FieldDecl;
import scale.clef.decl.FileDecl;
import scale.clef.decl.FormalDecl;
import scale.clef.decl.ForwardProcedureDecl;
import scale.clef.decl.LabelDecl;
import scale.clef.decl.ProcedureDecl;
import scale.clef.decl.RenamedVariableDecl;
import scale.clef.decl.RoutineDecl;
import scale.clef.decl.TypeDecl;
import scale.clef.decl.TypeName;
import scale.clef.decl.UnknownFormals;
import scale.clef.decl.ValueDecl;
import scale.clef.decl.VariableDecl;
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
import scale.clef.stmt.AltCase;
import scale.clef.stmt.ArithmeticIfStmt;
import scale.clef.stmt.AssignLabelStmt;
import scale.clef.stmt.AssignedGotoStmt;
import scale.clef.stmt.BlockStmt;
import scale.clef.stmt.BreakStmt;
import scale.clef.stmt.CaseStmt;
import scale.clef.stmt.ComputedGotoStmt;
import scale.clef.stmt.ContinueStmt;
import scale.clef.stmt.DeclStmt;
import scale.clef.stmt.DoLoopStmt;
import scale.clef.stmt.EvalStmt;
import scale.clef.stmt.ExitStmt;
import scale.clef.stmt.ForLoopStmt;
import scale.clef.stmt.GotoStmt;
import scale.clef.stmt.IfStmt;
import scale.clef.stmt.IfThenElseStmt;
import scale.clef.stmt.LabelStmt;
import scale.clef.stmt.LoopStmt;
import scale.clef.stmt.MultiBranchStmt;
import scale.clef.stmt.NullStmt;
import scale.clef.stmt.RepeatUntilLoopStmt;
import scale.clef.stmt.RepeatWhileLoopStmt;
import scale.clef.stmt.ReturnStmt;
import scale.clef.stmt.Statement;
import scale.clef.stmt.SwitchStmt;
import scale.clef.stmt.TestLoopStmt;
import scale.clef.stmt.WhileLoopStmt;
import scale.clef.type.AggregateType;
import scale.clef.type.AllocArrayType;
import scale.clef.type.ArrayType;
import scale.clef.type.AtomicType;
import scale.clef.type.BooleanType;
import scale.clef.type.Bound;
import scale.clef.type.CharacterType;
import scale.clef.type.ComplexType;
import scale.clef.type.CompositeType;
import scale.clef.type.EnumerationType;
import scale.clef.type.FixedArrayType;
import scale.clef.type.FloatType;
import scale.clef.type.FortranCharType;
import scale.clef.type.IncompleteType;
import scale.clef.type.IntegerType;
import scale.clef.type.NumericType;
import scale.clef.type.PointerType;
import scale.clef.type.ProcedureType;
import scale.clef.type.Raise;
import scale.clef.type.RaiseWithObject;
import scale.clef.type.RaiseWithType;
import scale.clef.type.RealType;
import scale.clef.type.RecordType;
import scale.clef.type.RefType;
import scale.clef.type.SignedIntegerType;
import scale.clef.type.Type;
import scale.clef.type.UnionType;
import scale.clef.type.UnsignedIntegerType;
import scale.clef.type.VoidType;

/**
 * This class provides a default implementation of the Predicate visit
 * pattern that generates an error.
 * <p/>
 * $Id: ErrorPredicate.java,v 1.62 2007-10-04 19:58:03 burrill Exp $
 * <p/>
 * Copyright 2007 by the
 * <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 * <p/>
 * All of the visit methods in this class throw an error.  To make
 * a useable predicate from this class, you will need to override the
 * visit methods you expect to use.  You will want to inherit from this
 * class if you know that you will invoke only a subset of the visit
 * methods, and want to detect that you have visited a Clef class instance
 * unexpectedly.
 */

public abstract class ErrorPredicate
        implements Predicate
{
    private void GenErr(Node n)
    {
        throw new scale.common.NotImplementedError(getClass().getName() +
                " does not define behavior for class " +
                n.getClass().getName());
    }

    public void visitAbsoluteValueOp(AbsoluteValueOp n) { GenErr(n); }

    public void visitAdditionAssignmentOp(AdditionAssignmentOp n) { GenErr(n); }

    public void visitAdditionOp(AdditionOp n) { GenErr(n); }

    public void visitAddressLiteral(AddressLiteral n) { GenErr(n); }

    public void visitAddressOp(AddressOp n) { GenErr(n); }

    public void visitAggregateOp(AggregateOp n) { GenErr(n); }

    public void visitAggregateType(AggregateType n) { GenErr(n); }

    public void visitAggregationElements(AggregationElements n) { GenErr(n); }

    public void visitAllocArrayType(AllocArrayType n) { GenErr(n); }

    public void visitAllocatePlacementOp(AllocatePlacementOp n) { GenErr(n); }

    public void visitAllocateSettingFieldsOp(AllocateSettingFieldsOp n) { GenErr(n); }

    public void visitAltCase(AltCase n) { GenErr(n); }

    public void visitAndConditionalOp(AndConditionalOp n) { GenErr(n); }

    public void visitAndOp(AndOp n) { GenErr(n); }

    public void visitArithmeticIfStmt(ArithmeticIfStmt n) { GenErr(n); }

    public void visitMonadicOp(MonadicOp n) { GenErr(n); }

    public void visitArrayType(ArrayType n) { GenErr(n); }

    public void visitAssignLabelStmt(AssignLabelStmt n) { GenErr(n); }

    public void visitAssignSimpleOp(AssignSimpleOp n) { GenErr(n); }

    public void visitAssignedGotoStmt(AssignedGotoStmt n) { GenErr(n); }

    public void visitAssignmentOp(AssignmentOp n) { GenErr(n); }

    public void visitAtomicType(AtomicType n) { GenErr(n); }

    public void visitBitAndAssignmentOp(BitAndAssignmentOp n) { GenErr(n); }

    public void visitBitAndOp(BitAndOp n) { GenErr(n); }

    public void visitBitComplementOp(BitComplementOp n) { GenErr(n); }

    public void visitBitOrAssignmentOp(BitOrAssignmentOp n) { GenErr(n); }

    public void visitBitOrOp(BitOrOp n) { GenErr(n); }

    public void visitBitShiftAssignmentOp(BitShiftAssignmentOp n) { GenErr(n); }

    public void visitBitShiftOp(BitShiftOp n) { GenErr(n); }

    public void visitBitXorAssignmentOp(BitXorAssignmentOp n) { GenErr(n); }

    public void visitBitXorOp(BitXorOp n) { GenErr(n); }

    public void visitBlockStmt(BlockStmt n) { GenErr(n); }

    public void visitBooleanLiteral(BooleanLiteral n) { GenErr(n); }

    public void visitBooleanType(BooleanType n) { GenErr(n); }

    public void visitBound(Bound n) { GenErr(n); }

    public void visitBreakStmt(BreakStmt n) { GenErr(n); }

    public void visitCallFunctionOp(CallFunctionOp n) { GenErr(n); }

    public void visitCallOp(CallOp n) { GenErr(n); }

    public void visitCaseLabelDecl(CaseLabelDecl n) { GenErr(n); }

    public void visitCaseStmt(CaseStmt n) { GenErr(n); }

    public void visitCharLiteral(CharLiteral n) { GenErr(n); }

    public void visitCharacterType(CharacterType n) { GenErr(n); }

    public void visitComplexLiteral(ComplexLiteral n) { GenErr(n); }

    public void visitComplexOp(ComplexOp n) { GenErr(n); }

    public void visitComplexType(ComplexType n) { GenErr(n); }

    public void visitCompositeType(CompositeType n) { GenErr(n); }

    public void visitCompoundAssignmentOp(CompoundAssignmentOp n) { GenErr(n); }

    public void visitComputedGotoStmt(ComputedGotoStmt n) { GenErr(n); }

    public void visitContinueStmt(ContinueStmt n) { GenErr(n); }

    public void visitDeclStmt(DeclStmt n) { GenErr(n); }

    public void visitDeclaration(Declaration n) { GenErr(n); }

    public void visitDefOp(DefOp n) { GenErr(n); }

    public void visitDeleteArrayOp(DeleteArrayOp n) { GenErr(n); }

    public void visitDeleteOp(DeleteOp n) { GenErr(n); }

    public void visitDereferenceOp(DereferenceOp n) { GenErr(n); }

    public void visitDivisionAssignmentOp(DivisionAssignmentOp n) { GenErr(n); }

    public void visitDivisionOp(DivisionOp n) { GenErr(n); }

    public void visitDoLoopStmt(DoLoopStmt n) { GenErr(n); }

    public void visitDyadicOp(DyadicOp n) { GenErr(n); }

    public void visitEnumElementDecl(EnumElementDecl n) { GenErr(n); }

    public void visitEnumerationType(EnumerationType n) { GenErr(n); }

    public void visitEqualityOp(EqualityOp n) { GenErr(n); }

    public void visitEquivalenceDecl(EquivalenceDecl n) { GenErr(n); }

    public void visitEvalStmt(EvalStmt n) { GenErr(n); }

    public void visitExceptionDecl(ExceptionDecl n) { GenErr(n); }

    public void visitExitStmt(ExitStmt n) { GenErr(n); }

    public void visitExponentiationOp(ExponentiationOp n) { GenErr(n); }

    public void visitExpression(Expression n) { GenErr(n); }

    public void visitExpressionIfOp(ExpressionIfOp n) { GenErr(n); }

    public void visitFieldDecl(FieldDecl n) { GenErr(n); }

    public void visitFileDecl(FileDecl n) { GenErr(n); }

    public void visitFixedArrayType(FixedArrayType n) { GenErr(n); }

    public void visitFloatArrayLiteral(FloatArrayLiteral n) { GenErr(n); }

    public void visitFloatLiteral(FloatLiteral n) { GenErr(n); }

    public void visitFloatType(FloatType n) { GenErr(n); }

    public void visitForLoopStmt(ForLoopStmt n) { GenErr(n); }

    public void visitFormalDecl(FormalDecl n) { GenErr(n); }

    public void visitForwardProcedureDecl(ForwardProcedureDecl n) { GenErr(n); }

    public void visitGotoStmt(GotoStmt n) { GenErr(n); }

    public void visitGreaterEqualOp(GreaterEqualOp n) { GenErr(n); }

    public void visitGreaterOp(GreaterOp n) { GenErr(n); }

    public void visitHeapOp(HeapOp n) { GenErr(n); }

    public void visitIdAddressOp(IdAddressOp n) { GenErr(n); }

    public void visitIdReferenceOp(IdReferenceOp n) { GenErr(n); }

    public void visitIdValueOp(IdValueOp n) { GenErr(n); }

    public void visitIfStmt(IfStmt n) { GenErr(n); }

    public void visitIfThenElseStmt(IfThenElseStmt n) { GenErr(n); }

    public void visitIncompleteType(IncompleteType n) { GenErr(n); }

    public void visitIncrementOp(IncrementOp n) { GenErr(n); }

    public void visitIntArrayLiteral(IntArrayLiteral n) { GenErr(n); }

    public void visitIntLiteral(IntLiteral n) { GenErr(n); }

    public void visitIntegerType(IntegerType n) { GenErr(n); }

    public void visitSignedIntegerType(SignedIntegerType n) { GenErr(n); }

    public void visitUnsignedIntegerType(UnsignedIntegerType n) { GenErr(n); }

    public void visitFortranCharType(FortranCharType n) { GenErr(n); }

    public void visitLabelDecl(LabelDecl n) { GenErr(n); }

    public void visitLabelStmt(LabelStmt n) { GenErr(n); }

    public void visitLessEqualOp(LessEqualOp n) { GenErr(n); }

    public void visitLessOp(LessOp n) { GenErr(n); }

    public void visitLiteral(Literal n) { GenErr(n); }

    public void visitLoopStmt(LoopStmt n) { GenErr(n); }

    public void visitMaximumOp(MaximumOp n) { GenErr(n); }

    public void visitMinimumOp(MinimumOp n) { GenErr(n); }

    public void visitModulusOp(ModulusOp n) { GenErr(n); }

    public void visitMultiBranchStmt(MultiBranchStmt n) { GenErr(n); }

    public void visitMultiplicationAssignmentOp(MultiplicationAssignmentOp n) { GenErr(n); }

    public void visitMultiplicationOp(MultiplicationOp n) { GenErr(n); }

    public void visitNegativeOp(NegativeOp n) { GenErr(n); }

    public void visitNilOp(NilOp n) { GenErr(n); }

    public void visitNode(Node n) { GenErr(n); }

    public void visitNotEqualOp(NotEqualOp n) { GenErr(n); }

    public void visitNotOp(NotOp n) { GenErr(n); }

    public void visitNullStmt(NullStmt n) { GenErr(n); }

    public void visitNumericType(NumericType n) { GenErr(n); }

    public void visitOrConditionalOp(OrConditionalOp n) { GenErr(n); }

    public void visitOrOp(OrOp n) { GenErr(n); }

    public void visitParenthesesOp(ParenthesesOp n) { GenErr(n); }

    public void visitPointerType(PointerType n) { GenErr(n); }

    public void visitPositiveOp(PositiveOp n) { GenErr(n); }

    public void visitPostDecrementOp(PostDecrementOp n) { GenErr(n); }

    public void visitPostIncrementOp(PostIncrementOp n) { GenErr(n); }

    public void visitPreDecrementOp(PreDecrementOp n) { GenErr(n); }

    public void visitPreIncrementOp(PreIncrementOp n) { GenErr(n); }

    public void visitProcedureDecl(ProcedureDecl n) { GenErr(n); }

    public void visitProcedureType(ProcedureType n) { GenErr(n); }

    public void visitRaise(Raise n) { GenErr(n); }

    public void visitRaiseWithObject(RaiseWithObject n) { GenErr(n); }

    public void visitRaiseWithType(RaiseWithType n) { GenErr(n); }

    public void visitRealType(RealType n) { GenErr(n); }

    public void visitRecordType(RecordType n) { GenErr(n); }

    public void visitRefType(RefType n) { GenErr(n); }

    public void visitRemainderAssignmentOp(RemainderAssignmentOp n) { GenErr(n); }

    public void visitRemainderOp(RemainderOp n) { GenErr(n); }

    public void visitRenamedVariableDecl(RenamedVariableDecl n) { GenErr(n); }

    public void visitRepeatUntilLoopStmt(RepeatUntilLoopStmt n) { GenErr(n); }

    public void visitRepeatWhileLoopStmt(RepeatWhileLoopStmt n) { GenErr(n); }

    public void visitReturnStmt(ReturnStmt n) { GenErr(n); }

    public void visitRoutineDecl(RoutineDecl n) { GenErr(n); }

    public void visitSelectIndirectOp(SelectIndirectOp n) { GenErr(n); }

    public void visitSelectOp(SelectOp n) { GenErr(n); }

    public void visitSeriesOp(SeriesOp n) { GenErr(n); }

    public void visitSizeofLiteral(SizeofLiteral n) { GenErr(n); }

    public void visitStatement(Statement n) { GenErr(n); }

    public void visitStatementOp(StatementOp n) { GenErr(n); }

    public void visitStringLiteral(StringLiteral n) { GenErr(n); }

    public void visitSubscriptAddressOp(SubscriptAddressOp n) { GenErr(n); }

    public void visitSubscriptOp(SubscriptOp n) { GenErr(n); }

    public void visitSubscriptValueOp(SubscriptValueOp n) { GenErr(n); }

    public void visitSubstringOp(SubstringOp n) { GenErr(n); }

    public void visitSubtractionAssignmentOp(SubtractionAssignmentOp n) { GenErr(n); }

    public void visitSubtractionOp(SubtractionOp n) { GenErr(n); }

    public void visitSwitchStmt(SwitchStmt n) { GenErr(n); }

    public void visitTernaryOp(TernaryOp n) { GenErr(n); }

    public void visitTestLoopStmt(TestLoopStmt n) { GenErr(n); }

    public void visitThisOp(ThisOp n) { GenErr(n); }

    public void visitTranscendentalOp(TranscendentalOp n) { GenErr(n); }

    public void visitTranscendental2Op(Transcendental2Op n) { GenErr(n); }

    public void visitType(Type n) { GenErr(n); }

    public void visitTypeConversionOp(TypeConversionOp n) { GenErr(n); }

    public void visitTypeDecl(TypeDecl n) { GenErr(n); }

    public void visitTypeName(TypeName n) { GenErr(n); }

    public void visitUnionType(UnionType n) { GenErr(n); }

    public void visitUnknownFormals(UnknownFormals n) { GenErr(n); }

    public void visitVaArgOp(VaArgOp n) { GenErr(n); }

    public void visitVaCopyOp(VaCopyOp n) { GenErr(n); }

    public void visitVaEndOp(VaEndOp n) { GenErr(n); }

    public void visitVaStartOp(VaStartOp n) { GenErr(n); }

    public void visitValueDecl(ValueDecl n) { GenErr(n); }

    public void visitVarArgOp(VarArgOp n) { GenErr(n); }

    public void visitVariableDecl(VariableDecl n) { GenErr(n); }

    public void visitVoidType(VoidType n) { GenErr(n); }

    public void visitWhileLoopStmt(WhileLoopStmt n) { GenErr(n); }
}
