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
 * This is an abstract class that implements a recursive descent visit
 * of a Clef AST class tree.
 * <p/>
 * $Id: DescendPredicate.java,v 1.68 2007-10-04 19:58:02 burrill Exp $
 * <p/>
 * Copyright 2007 by the
 * <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 * <p/>
 * Sub-classes should implement override methods for the leaf nodes
 * that matter to them.
 * <p/>
 *
 * @see scale.clef
 */
public abstract class DescendPredicate
        implements Predicate
{
    /**
     * Visit all of the Node elements of a Vector.
     */
    public void visitChildren(Node parent)
    {
        int l = parent.numChildren();
        for (int i = 0; i < l; i++) {
            Node child = parent.getChild(i);
            if (child != null) {
                child.visit(this);
            }
        }
    }

    public void visitAbsoluteValueOp(AbsoluteValueOp n) { visitMonadicOp(n); }

    public void visitAdditionAssignmentOp(AdditionAssignmentOp n) { visitCompoundAssignmentOp(n); }

    public void visitAdditionOp(AdditionOp n) { visitDyadicOp(n); }

    public void visitAddressLiteral(AddressLiteral n) { visitLiteral(n); }

    public void visitAddressOp(AddressOp n) { visitMonadicOp(n); }

    public void visitAggregateOp(AggregateOp n) { visitMonadicOp(n); }

    public void visitAggregateType(AggregateType n) { visitCompositeType(n); }

    public void visitAggregationElements(AggregationElements n) { visitLiteral(n); }

    public void visitAllocArrayType(AllocArrayType n) { visitArrayType(n); }

    public void visitAllocatePlacementOp(AllocatePlacementOp n) { visitHeapOp(n); }

    public void visitAllocateSettingFieldsOp(AllocateSettingFieldsOp n) { visitHeapOp(n); }

    public void visitAltCase(AltCase n) { visitNode(n); }

    public void visitAndConditionalOp(AndConditionalOp n) { visitDyadicOp(n); }

    public void visitAndOp(AndOp n) { visitDyadicOp(n); }

    public void visitArithmeticIfStmt(ArithmeticIfStmt n) { visitIfStmt(n); }

    public void visitArrayType(ArrayType n) { visitCompositeType(n); }

    public void visitAssignLabelStmt(AssignLabelStmt n) { visitStatement(n); }

    public void visitAssignSimpleOp(AssignSimpleOp n) { visitAssignmentOp(n); }

    public void visitAssignedGotoStmt(AssignedGotoStmt n) { visitMultiBranchStmt(n); }

    public void visitAssignmentOp(AssignmentOp n) { visitDyadicOp(n); }

    public void visitAtomicType(AtomicType n) { visitType(n); }

    public void visitBitAndAssignmentOp(BitAndAssignmentOp n) { visitCompoundAssignmentOp(n); }

    public void visitBitAndOp(BitAndOp n) { visitDyadicOp(n); }

    public void visitBitComplementOp(BitComplementOp n) { visitMonadicOp(n); }

    public void visitBitOrAssignmentOp(BitOrAssignmentOp n) { visitCompoundAssignmentOp(n); }

    public void visitBitOrOp(BitOrOp n) { visitDyadicOp(n); }

    public void visitBitShiftAssignmentOp(BitShiftAssignmentOp n) { visitCompoundAssignmentOp(n); }

    public void visitBitShiftOp(BitShiftOp n) { visitDyadicOp(n); }

    public void visitBitXorAssignmentOp(BitXorAssignmentOp n) { visitCompoundAssignmentOp(n); }

    public void visitBitXorOp(BitXorOp n) { visitDyadicOp(n); }

    public void visitBlockStmt(BlockStmt n) { visitStatement(n); }

    public void visitBooleanLiteral(BooleanLiteral n) { visitLiteral(n); }

    public void visitBooleanType(BooleanType n) { visitNumericType(n); }

    public void visitBound(Bound n) { visitNode(n); }

    public void visitBreakStmt(BreakStmt n) { visitStatement(n); }

    public void visitCallFunctionOp(CallFunctionOp n) { visitCallOp(n); }

    public void visitCallOp(CallOp n) { visitExpression(n); }

    public void visitCaseLabelDecl(CaseLabelDecl n) { visitLabelDecl(n); }

    public void visitCaseStmt(CaseStmt n) { visitStatement(n); }

    public void visitCharLiteral(CharLiteral n) { visitLiteral(n); }

    public void visitCharacterType(CharacterType n) { visitNumericType(n); }

    public void visitComplexLiteral(ComplexLiteral n) { visitLiteral(n); }

    public void visitComplexOp(ComplexOp n) { visitDyadicOp(n); }

    public void visitComplexType(ComplexType n) { visitNumericType(n); }

    public void visitCompositeType(CompositeType n) { visitType(n); }

    public void visitCompoundAssignmentOp(CompoundAssignmentOp n) { visitAssignmentOp(n); }

    public void visitComputedGotoStmt(ComputedGotoStmt n) { visitMultiBranchStmt(n); }

    public void visitContinueStmt(ContinueStmt n) { visitStatement(n); }

    public void visitDeclStmt(DeclStmt n) { visitStatement(n); }

    public void visitDeclaration(Declaration n) { visitNode(n); }

    public void visitDefOp(DefOp n) { visitMonadicOp(n); }

    public void visitDeleteArrayOp(DeleteArrayOp n) { visitMonadicOp(n); }

    public void visitDeleteOp(DeleteOp n) { visitMonadicOp(n); }

    public void visitDereferenceOp(DereferenceOp n) { visitMonadicOp(n); }

    public void visitDivisionAssignmentOp(DivisionAssignmentOp n) { visitCompoundAssignmentOp(n); }

    public void visitDivisionOp(DivisionOp n) { visitDyadicOp(n); }

    public void visitDoLoopStmt(DoLoopStmt n) { visitLoopStmt(n); }

    public void visitDyadicOp(DyadicOp n) { visitExpression(n); }

    public void visitEnumElementDecl(EnumElementDecl n) { visitDeclaration(n); }

    public void visitEnumerationType(EnumerationType n) { visitNumericType(n); }

    public void visitEqualityOp(EqualityOp n) { visitDyadicOp(n); }

    public void visitEquivalenceDecl(EquivalenceDecl n) { visitVariableDecl(n); }

    public void visitEvalStmt(EvalStmt n) { visitStatement(n); }

    public void visitExceptionDecl(ExceptionDecl n) { visitDeclaration(n); }

    public void visitExitStmt(ExitStmt n) { visitStatement(n); }

    public void visitExponentiationOp(ExponentiationOp n) { visitDyadicOp(n); }

    public void visitExpression(Expression n) { visitNode(n); }

    public void visitExpressionIfOp(ExpressionIfOp n) { visitTernaryOp(n); }

    public void visitFieldDecl(FieldDecl n) { visitValueDecl(n); }

    public void visitFileDecl(FileDecl n) { visitDeclaration(n); }

    public void visitFixedArrayType(FixedArrayType n) { visitArrayType(n); }

    public void visitFloatArrayLiteral(FloatArrayLiteral n) { visitLiteral(n); }

    public void visitFloatLiteral(FloatLiteral n) { visitLiteral(n); }

    public void visitFloatType(FloatType n) { visitRealType(n); }

    public void visitForLoopStmt(ForLoopStmt n) { visitTestLoopStmt(n); }

    public void visitFormalDecl(FormalDecl n) { visitVariableDecl(n); }

    public void visitForwardProcedureDecl(ForwardProcedureDecl n) { visitProcedureDecl(n); }

    public void visitGotoStmt(GotoStmt n) { visitStatement(n); }

    public void visitGreaterEqualOp(GreaterEqualOp n) { visitDyadicOp(n); }

    public void visitGreaterOp(GreaterOp n) { visitDyadicOp(n); }

    public void visitHeapOp(HeapOp n) { visitExpression(n); }

    public void visitIdAddressOp(IdAddressOp n) { visitIdReferenceOp(n); }

    public void visitIdReferenceOp(IdReferenceOp n) { visitExpression(n); }

    public void visitIdValueOp(IdValueOp n) { visitIdReferenceOp(n); }

    public void visitIfStmt(IfStmt n) { visitStatement(n); }

    public void visitIfThenElseStmt(IfThenElseStmt n) { visitIfStmt(n); }

    public void visitIncompleteType(IncompleteType n) { visitType(n); }

    public void visitIncrementOp(IncrementOp n) { visitMonadicOp(n); }

    public void visitIntArrayLiteral(IntArrayLiteral n) { visitLiteral(n); }

    public void visitIntLiteral(IntLiteral n) { visitLiteral(n); }

    public void visitIntegerType(IntegerType n) { visitNumericType(n); }

    public void visitSignedIntegerType(SignedIntegerType n) { visitIntegerType(n); }

    public void visitUnsignedIntegerType(UnsignedIntegerType n) { visitIntegerType(n); }

    public void visitFortranCharType(FortranCharType n) { visitCompositeType(n); }

    public void visitLabelDecl(LabelDecl n) { visitDeclaration(n); }

    public void visitLabelStmt(LabelStmt n) { visitStatement(n); }

    public void visitLessEqualOp(LessEqualOp n) { visitDyadicOp(n); }

    public void visitLessOp(LessOp n) { visitDyadicOp(n); }

    public void visitLiteral(Literal n) { visitExpression(n); }

    public void visitLoopStmt(LoopStmt n) { visitStatement(n); }

    public void visitMaximumOp(MaximumOp n) { visitDyadicOp(n); }

    public void visitMinimumOp(MinimumOp n) { visitDyadicOp(n); }

    public void visitModulusOp(ModulusOp n) { visitDyadicOp(n); }

    public void visitMonadicOp(MonadicOp n) { visitExpression(n); }

    public void visitMultiBranchStmt(MultiBranchStmt n) { visitStatement(n); }

    public void visitMultiplicationAssignmentOp(MultiplicationAssignmentOp n) { visitCompoundAssignmentOp(n); }

    public void visitMultiplicationOp(MultiplicationOp n) { visitDyadicOp(n); }

    public void visitNegativeOp(NegativeOp n) { visitMonadicOp(n); }

    public void visitNilOp(NilOp n) { visitLiteral(n); }

    public void visitNode(Node n) { visitChildren(n); }

    public void visitNotEqualOp(NotEqualOp n) { visitDyadicOp(n); }

    public void visitNotOp(NotOp n) { visitMonadicOp(n); }

    public void visitNullStmt(NullStmt n) { visitStatement(n); }

    public void visitNumericType(NumericType n) { visitAtomicType(n); }

    public void visitOrConditionalOp(OrConditionalOp n) { visitDyadicOp(n); }

    public void visitOrOp(OrOp n) { visitDyadicOp(n); }

    public void visitParenthesesOp(ParenthesesOp n) { visitMonadicOp(n); }

    public void visitPointerType(PointerType n) { visitAtomicType(n); }

    public void visitPositiveOp(PositiveOp n) { visitMonadicOp(n); }

    public void visitPostDecrementOp(PostDecrementOp n) { visitIncrementOp(n); }

    public void visitPostIncrementOp(PostIncrementOp n) { visitIncrementOp(n); }

    public void visitPreDecrementOp(PreDecrementOp n) { visitIncrementOp(n); }

    public void visitPreIncrementOp(PreIncrementOp n) { visitIncrementOp(n); }

    public void visitProcedureDecl(ProcedureDecl n) { visitRoutineDecl(n); }

    public void visitProcedureType(ProcedureType n) { visitType(n); }

    public void visitRaise(Raise n) { visitNode(n); }

    public void visitRaiseWithObject(RaiseWithObject n) { visitRaise(n); }

    public void visitRaiseWithType(RaiseWithType n) { visitRaise(n); }

    public void visitRealType(RealType n) { visitNumericType(n); }

    public void visitRecordType(RecordType n) { visitAggregateType(n); }

    public void visitRefType(RefType n) { visitType(n); }

    public void visitRemainderAssignmentOp(RemainderAssignmentOp n) { visitCompoundAssignmentOp(n); }

    public void visitRemainderOp(RemainderOp n) { visitDyadicOp(n); }

    public void visitRenamedVariableDecl(RenamedVariableDecl n) { visitVariableDecl(n); }

    public void visitRepeatUntilLoopStmt(RepeatUntilLoopStmt n) { visitTestLoopStmt(n); }

    public void visitRepeatWhileLoopStmt(RepeatWhileLoopStmt n) { visitTestLoopStmt(n); }

    public void visitReturnStmt(ReturnStmt n) { visitStatement(n); }

    public void visitRoutineDecl(RoutineDecl n) { visitDeclaration(n); }

    public void visitSelectIndirectOp(SelectIndirectOp n) { visitAggregateOp(n); }

    public void visitSelectOp(SelectOp n) { visitAggregateOp(n); }

    public void visitSeriesOp(SeriesOp n) { visitDyadicOp(n); }

    public void visitSizeofLiteral(SizeofLiteral n) { visitLiteral(n); }

    public void visitStatement(Statement n) { visitNode(n); }

    public void visitStatementOp(StatementOp n) { visitMonadicOp(n); }

    public void visitStringLiteral(StringLiteral n) { visitLiteral(n); }

    public void visitSubscriptAddressOp(SubscriptAddressOp n) { visitSubscriptOp(n); }

    public void visitSubscriptOp(SubscriptOp n) { visitExpression(n); }

    public void visitSubscriptValueOp(SubscriptValueOp n) { visitSubscriptOp(n); }

    public void visitSubstringOp(SubstringOp n) { visitTernaryOp(n); }

    public void visitSubtractionAssignmentOp(SubtractionAssignmentOp n) { visitCompoundAssignmentOp(n); }

    public void visitSubtractionOp(SubtractionOp n) { visitDyadicOp(n); }

    public void visitSwitchStmt(SwitchStmt n) { visitStatement(n); }

    public void visitTernaryOp(TernaryOp n) { visitExpression(n); }

    public void visitTestLoopStmt(TestLoopStmt n) { visitLoopStmt(n); }

    public void visitThisOp(ThisOp n) { visitExpression(n); }

    public void visitTranscendental2Op(Transcendental2Op n) { visitDyadicOp(n); }

    public void visitTranscendentalOp(TranscendentalOp n) { visitMonadicOp(n); }

    public void visitType(Type n) { visitNode(n); }

    public void visitTypeConversionOp(TypeConversionOp n) { visitMonadicOp(n); }

    public void visitTypeDecl(TypeDecl n) { visitDeclaration(n); }

    public void visitTypeName(TypeName n) { visitDeclaration(n); }

    public void visitUnionType(UnionType n) { visitRecordType(n); }

    public void visitUnknownFormals(UnknownFormals n) { visitFormalDecl(n); }

    public void visitVaArgOp(VaArgOp n) { visitVarArgOp(n); }

    public void visitVaCopyOp(VaCopyOp n) { visitAssignmentOp(n); }

    public void visitVaEndOp(VaEndOp n) { visitVarArgOp(n); }

    public void visitVaStartOp(VaStartOp n) { visitVarArgOp(n); }

    public void visitValueDecl(ValueDecl n) { visitDeclaration(n); }

    public void visitVarArgOp(VarArgOp n) { visitExpression(n); }

    public void visitVariableDecl(VariableDecl n) { visitValueDecl(n); }

    public void visitVoidType(VoidType n) { visitType(n); }

    public void visitWhileLoopStmt(WhileLoopStmt n) { visitTestLoopStmt(n); }
}
