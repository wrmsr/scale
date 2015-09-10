package scale.clef;

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

/**
 * The class for the visit pattern of Clef Statements.
 * <p/>
 * $Id: StmtPredicate.java,v 1.18 2006-06-28 16:39:01 burrill Exp $
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

public interface StmtPredicate
{
    public void visitStatement(Statement n);

    public void visitBlockStmt(BlockStmt n);

    public void visitLoopStmt(LoopStmt n);

    public void visitTestLoopStmt(TestLoopStmt n);

    public void visitIfStmt(IfStmt n);

    public void visitMultiBranchStmt(MultiBranchStmt n);

    public void visitIfThenElseStmt(IfThenElseStmt n);

    public void visitArithmeticIfStmt(ArithmeticIfStmt n);

    public void visitComputedGotoStmt(ComputedGotoStmt n);

    public void visitAssignLabelStmt(AssignLabelStmt n);

    public void visitAssignedGotoStmt(AssignedGotoStmt n);

    public void visitAltCase(AltCase n);

    public void visitCaseStmt(CaseStmt n);

    public void visitSwitchStmt(SwitchStmt n);

    public void visitWhileLoopStmt(WhileLoopStmt n);

    public void visitRepeatWhileLoopStmt(RepeatWhileLoopStmt n);

    public void visitRepeatUntilLoopStmt(RepeatUntilLoopStmt n);

    public void visitDoLoopStmt(DoLoopStmt n);

    public void visitForLoopStmt(ForLoopStmt n);

    public void visitBreakStmt(BreakStmt n);

    public void visitContinueStmt(ContinueStmt n);

    public void visitGotoStmt(GotoStmt n);

    public void visitReturnStmt(ReturnStmt n);

    public void visitExitStmt(ExitStmt n);

    public void visitEvalStmt(EvalStmt n);

    public void visitDeclStmt(DeclStmt n);

    public void visitNullStmt(NullStmt n);

    public void visitLabelStmt(LabelStmt n);
}
