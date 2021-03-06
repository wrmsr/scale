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

/**
 * This is the predicate class for the visit pattern of Clef Declarations.
 * <p/>
 * $Id: DeclPredicate.java,v 1.20 2007-05-10 16:47:58 burrill Exp $
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

public interface DeclPredicate
{
    public void visitDeclaration(Declaration n);

    public void visitTypeDecl(TypeDecl n);

    public void visitTypeName(TypeName n);

    public void visitValueDecl(ValueDecl n);

    public void visitRoutineDecl(RoutineDecl n);

    public void visitLabelDecl(LabelDecl n);

    public void visitCaseLabelDecl(CaseLabelDecl n);

    public void visitExceptionDecl(ExceptionDecl n);

    public void visitVariableDecl(VariableDecl n);

    public void visitRenamedVariableDecl(RenamedVariableDecl n);

    public void visitEquivalenceDecl(EquivalenceDecl n);

    public void visitFormalDecl(FormalDecl n);

    public void visitUnknownFormals(UnknownFormals n);

    public void visitFieldDecl(FieldDecl n);

    public void visitProcedureDecl(ProcedureDecl n);

    public void visitForwardProcedureDecl(ForwardProcedureDecl n);

    public void visitFileDecl(FileDecl n);

    public void visitEnumElementDecl(EnumElementDecl n);
}
