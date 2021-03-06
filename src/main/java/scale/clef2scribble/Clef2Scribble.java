package scale.clef2scribble;

import scale.annot.CreatorSource;
import scale.callGraph.CallGraph;
import scale.clef.LiteralMap;
import scale.clef.Node;
import scale.clef.decl.Assigned;
import scale.clef.decl.CaseLabelDecl;
import scale.clef.decl.Declaration;
import scale.clef.decl.EnumElementDecl;
import scale.clef.decl.FieldDecl;
import scale.clef.decl.FormalDecl;
import scale.clef.decl.LabelDecl;
import scale.clef.decl.ParameterMode;
import scale.clef.decl.ProcedureDecl;
import scale.clef.decl.Residency;
import scale.clef.decl.RoutineDecl;
import scale.clef.decl.ValueDecl;
import scale.clef.decl.VariableDecl;
import scale.clef.expr.AbsoluteValueOp;
import scale.clef.expr.AdditionAssignmentOp;
import scale.clef.expr.AdditionOp;
import scale.clef.expr.AddressLiteral;
import scale.clef.expr.AddressOp;
import scale.clef.expr.AggregationElements;
import scale.clef.expr.AndConditionalOp;
import scale.clef.expr.AndOp;
import scale.clef.expr.AssignSimpleOp;
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
import scale.clef.expr.CastMode;
import scale.clef.expr.CharLiteral;
import scale.clef.expr.ComplexLiteral;
import scale.clef.expr.ComplexOp;
import scale.clef.expr.CompoundAssignmentOp;
import scale.clef.expr.DefOp;
import scale.clef.expr.DereferenceOp;
import scale.clef.expr.DivisionAssignmentOp;
import scale.clef.expr.DivisionOp;
import scale.clef.expr.EqualityOp;
import scale.clef.expr.ExponentiationOp;
import scale.clef.expr.Expression;
import scale.clef.expr.ExpressionIfOp;
import scale.clef.expr.FloatArrayLiteral;
import scale.clef.expr.FloatLiteral;
import scale.clef.expr.GreaterEqualOp;
import scale.clef.expr.GreaterOp;
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
import scale.clef.expr.MultiplicationAssignmentOp;
import scale.clef.expr.MultiplicationOp;
import scale.clef.expr.NegativeOp;
import scale.clef.expr.NilOp;
import scale.clef.expr.NotEqualOp;
import scale.clef.expr.NotOp;
import scale.clef.expr.OrConditionalOp;
import scale.clef.expr.OrOp;
import scale.clef.expr.ParenthesesOp;
import scale.clef.expr.PositionFieldOp;
import scale.clef.expr.PositionIndexOp;
import scale.clef.expr.PositionOffsetOp;
import scale.clef.expr.PositionRepeatOp;
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
import scale.clef.expr.SubtractionAssignmentOp;
import scale.clef.expr.SubtractionOp;
import scale.clef.expr.TransFtn;
import scale.clef.expr.Transcendental2Op;
import scale.clef.expr.TranscendentalOp;
import scale.clef.expr.TypeConversionOp;
import scale.clef.expr.VaArgOp;
import scale.clef.expr.VaCopyOp;
import scale.clef.expr.VaEndOp;
import scale.clef.expr.VaStartOp;
import scale.clef.stmt.ArithmeticIfStmt;
import scale.clef.stmt.BlockStmt;
import scale.clef.stmt.BreakStmt;
import scale.clef.stmt.ComputedGotoStmt;
import scale.clef.stmt.ContinueStmt;
import scale.clef.stmt.DeclStmt;
import scale.clef.stmt.DoLoopStmt;
import scale.clef.stmt.EvalStmt;
import scale.clef.stmt.ExitStmt;
import scale.clef.stmt.ForLoopStmt;
import scale.clef.stmt.GotoStmt;
import scale.clef.stmt.IfThenElseStmt;
import scale.clef.stmt.LabelStmt;
import scale.clef.stmt.NullStmt;
import scale.clef.stmt.RepeatUntilLoopStmt;
import scale.clef.stmt.RepeatWhileLoopStmt;
import scale.clef.stmt.ReturnStmt;
import scale.clef.stmt.Statement;
import scale.clef.stmt.SwitchStmt;
import scale.clef.stmt.WhileLoopStmt;
import scale.clef.type.AggregateType;
import scale.clef.type.ArrayType;
import scale.clef.type.BooleanType;
import scale.clef.type.Bound;
import scale.clef.type.FixedArrayType;
import scale.clef.type.IntegerType;
import scale.clef.type.PointerType;
import scale.clef.type.ProcedureType;
import scale.clef.type.RefAttr;
import scale.clef.type.RefType;
import scale.clef.type.Type;
import scale.clef.type.VoidType;
import scale.common.HashMap;
import scale.common.IntMap;
import scale.common.InvalidException;
import scale.common.Lattice;
import scale.common.Machine;
import scale.common.PragmaStk;
import scale.common.Stack;
import scale.common.Statistics;
import scale.common.Table;
import scale.common.UniqueName;
import scale.common.Vector;
import scale.frontend.SourceLanguage;
import scale.score.InductionVar;
import scale.score.Note;
import scale.score.Scribble;
import scale.score.chords.BeginChord;
import scale.score.chords.BranchChord;
import scale.score.chords.Chord;
import scale.score.chords.EndChord;
import scale.score.chords.ExitChord;
import scale.score.chords.ExprChord;
import scale.score.chords.GotoChord;
import scale.score.chords.IfThenElseChord;
import scale.score.chords.LoopHeaderChord;
import scale.score.chords.LoopInitChord;
import scale.score.chords.LoopPreHeaderChord;
import scale.score.chords.LoopTailChord;
import scale.score.chords.NullChord;
import scale.score.chords.ReturnChord;
import scale.score.chords.SwitchChord;
import scale.score.expr.AbsoluteValueExpr;
import scale.score.expr.AdditionExpr;
import scale.score.expr.AndExpr;
import scale.score.expr.ArrayIndexExpr;
import scale.score.expr.BinaryExpr;
import scale.score.expr.BitAndExpr;
import scale.score.expr.BitComplementExpr;
import scale.score.expr.BitOrExpr;
import scale.score.expr.BitShiftExpr;
import scale.score.expr.BitXorExpr;
import scale.score.expr.CallExpr;
import scale.score.expr.CallFunctionExpr;
import scale.score.expr.ComplexValueExpr;
import scale.score.expr.ConditionalExpr;
import scale.score.expr.ConversionExpr;
import scale.score.expr.DivisionExpr;
import scale.score.expr.DualExpr;
import scale.score.expr.EqualityExpr;
import scale.score.expr.ExponentiationExpr;
import scale.score.expr.Expr;
import scale.score.expr.GreaterEqualExpr;
import scale.score.expr.GreaterExpr;
import scale.score.expr.LessEqualExpr;
import scale.score.expr.LessExpr;
import scale.score.expr.LiteralExpr;
import scale.score.expr.LoadDeclAddressExpr;
import scale.score.expr.LoadDeclValueExpr;
import scale.score.expr.LoadExpr;
import scale.score.expr.LoadFieldAddressExpr;
import scale.score.expr.LoadFieldValueExpr;
import scale.score.expr.LoadValueIndirectExpr;
import scale.score.expr.MatchExpr;
import scale.score.expr.MaxExpr;
import scale.score.expr.MinExpr;
import scale.score.expr.MultiplicationExpr;
import scale.score.expr.NegativeExpr;
import scale.score.expr.NilExpr;
import scale.score.expr.NotEqualExpr;
import scale.score.expr.NotExpr;
import scale.score.expr.OrExpr;
import scale.score.expr.RemainderExpr;
import scale.score.expr.SubscriptExpr;
import scale.score.expr.SubtractionExpr;
import scale.score.expr.Transcendental2Expr;
import scale.score.expr.TranscendentalExpr;
import scale.score.expr.VaArgExpr;
import scale.score.expr.VaEndExpr;
import scale.score.expr.VaStartExpr;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * This class builds a Scribble graph (CFG) out of a Clef tree (AST).
 * <p/>
 * $Id: Clef2Scribble.java,v 1.374 2007-10-04 19:58:09 burrill Exp $
 * <p/>
 * Copyright 2008 by the
 * <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 * <p/>
 * This class builds a Scribble graph out of a Clef graph.
 * <p/>
 * The functionality of building the Scribble graph has been
 * separated from the actual Scribble class to keep each class small.
 * <p/>
 * Methods that convert Clef statements return a range of Scribble
 * statements (or basic blocks) that are equivalent to the Clef
 * statement.  Methods that convert Clef expressions return a range of
 * Score statements as well, but also an indication of which variable
 * holds the final value of that expression.
 */

public class Clef2Scribble
        extends scale.clef.ErrorPredicate
{
    private static int variableCount = 0;
    private static int regVariableCount = 0;
    private static int equVariableCount = 0;
    private static int formalVariableCount = 0;
    private static int globalVariableCount = 0;
    private static int tempVariableCount = 0;
    private static int newCFGNodeCount = 0; // A count of nodes created.
    private static int redundantTestCount = 0; // A count of the number of redundant tests removed.

    private static final String[] stats = {"newCFGNodes",
                                           "redundantTests",
                                           "variables",
                                           "regVariables",
                                           "equVariables",
                                           "formalVariables",
                                           "globalVariables",
                                           "tempVariables"};

    static {
        Statistics.register("scale.clef2scribble.Clef2Scribble", stats);
    }

    /**
     * Return the number of variables declared.
     */
    public static int variables()
    {
        return variableCount;
    }

    /**
     * Return the number of regular variables declared.
     */
    public static int regVariables()
    {
        return regVariableCount;
    }

    /**
     * Return the number of equivalence variables declared.
     */
    public static int equVariables()
    {
        return equVariableCount;
    }

    /**
     * Return the number of formal variables declared.
     */
    public static int formalVariables()
    {
        return formalVariableCount;
    }

    /**
     * Return the number of global variables declared.
     */
    public static int globalVariables()
    {
        return globalVariableCount;
    }

    /**
     * Return the number of temporary variables declared.
     */
    public static int tempVariables()
    {
        return tempVariableCount;
    }

    /**
     * Return the current number of dead nodes removed.
     */
    public static int newCFGNodes()
    {
        return newCFGNodeCount;
    }

    /**
     * Return the current number of redundant tests removed.
     */
    public static int redundantTests()
    {
        return redundantTestCount;
    }

    private static final int MULT = 2;
    private static final int DIV = 3;
    private static final int REM = 4;
    private static final int ADD = 5;
    private static final int SUBT = 6;
    private static final int AND = 7;
    private static final int OR = 8;
    private static final int XOR = 9;

    /**
     * Set true if certain standard C library functions should not be inlined.
     */
    public static boolean noBuiltins = false;
    /**
     * True if there may be aliases between address arguments to
     * subroutines.
     */
    public static boolean hasDummyAliases = false;

    private static final CreatorSource creator = new CreatorSource("Clef2Scribble");

    private CallGraph cg;
    private Scribble scribble;
    private ExprTuple exp = null; // returns ExprTuple generated by a visit
    private ReturnChord exitS = null; // where to exit to (an EndChord)
    private Chord breakS = null; // where to break to
    private Chord continueS = null; // where to continue to
    private LoopHeaderChord parentLoop = null; // current loop
    private PragmaStk.Pragma pragma = null;

    private HashMap<StringLiteral, VariableDecl> stringMap = new HashMap<StringLiteral, VariableDecl>(23); // map from StringLiteral to VariableDecl
    private HashMap<Expr, Literal> cvMap = new HashMap<Expr, Literal>(23); // Map from expression to constant value.
    private Table<VariableDecl, Expr> byRefTable;                    // Map from by-reference variables to their uses.
    private IntMap<VariableDecl> addrLitMap = new IntMap<VariableDecl>(11);  // Map from argument position to variable.
    private Vector<Object> caseLabels = new Vector<Object>(5);   // Cases for a switch statement
    private Vector<Declaration> decls = new Vector<Declaration>(20);  // Record list of declarations
    private Stack<Chord> chords = new Stack<Chord>();     // Record list of created statements

    private UniqueName un = null;            // The name generator to use for any variables created.
    private GotoFix gotos = new GotoFix();   // List of forward gotos
    private int[] slaStack = new int[20];     // Push down stack for line numbers.
    private int slastkptr = 0;
    private SourceLanguage lang = null;            // The source language of the Clef AST.
    private VariableDecl returnDecl = null;            // Temp. declaration for return variable.
    private RoutineDecl rd = null;            // Routine being processed.
    private int currentSla = -1;              // The annotation that records the current source program line number.
    private int maxLineNumber = 0;               // The maximum source line number encountered so far.
    private boolean containedGoto = false;           // Set true if program contains a goto (i.e., maybe unstructured)
    private boolean doByRef = false;           // True if this is Fortran and by-reference parameters can be replaced.
    /**
     * The types needed by the lowering logic.
     */
    private IntegerType size_t_type = Machine.currentMachine.getSizetType();
    private IntegerType ptrdiff_t_type = Machine.currentMachine.getPtrdifftType();
    private IntegerType longType = Machine.currentMachine.getIntegerCalcType();
    private BooleanType boolType = BooleanType.type;

    /**
     * Construct a Scribble graph for a single routine.
     * We maintain a pointer some of the clef information as well.  Specifically,
     * the symbol and type tables and the source language that generated the Clef tree.
     *
     * @param rd the node in the call graph that represents the
     * @param lang specifies the source language
     */
    public Clef2Scribble(RoutineDecl rd, SourceLanguage lang, CallGraph cg)
    {
        this.rd = rd;
        this.lang = lang;
        this.cg = cg;
        this.scribble = new Scribble(rd, lang, cg);
        this.un = new UniqueName("_cs");

        this.doByRef = !hasDummyAliases && lang.isFortran();
        if (doByRef) {
            byRefTable = new Table<VariableDecl, Expr>();
        }

        rd.attachScribbleCFG(scribble);

        rd.visit(this);

        gotos.fixupGotos();

        BeginChord begin = (BeginChord) exp.getBegin();

        if (doByRef) {
            improveRefParamUse(begin);
        }

        Chord.removeDeadCode(chords);

        scribble.instantiate(begin, (EndChord) exp.getEnd(), decls, containedGoto);
        rd.clearAST();
    }

    /**
     * Try to replace uses of a by-reference parameter with a local
     * variable.  This can be done if the only use of the parameter is
     * to use the referenced value.  But, if it is used as an address
     * then it can not validly be replaced.  An example of the address
     * being used is if it is passed to a subroutine.  By reference
     * parameters only occur in Fortran.
     */
    private void improveRefParamUse(Chord start)
    {
        Chord insert = start.getNextChord();
        boolean skipFP = !Machine.currentMachine.hasCapability(Machine.HAS_NON_VOLATILE_FP_REGS);

        Enumeration<VariableDecl> ek = byRefTable.keys();
        while (ek.hasMoreElements()) {
            VariableDecl d = ek.nextElement();
            if (!d.isFormalDecl() || (skipFP && d.getCoreType().isRealType())) {
                continue;
            }

            int num = byRefTable.rowSize(d);
            if (num <= 2) {
                continue;
            }

            boolean flg = true;
            Iterator<Expr> it = byRefTable.getRowEnumeration(d);
            int cnt = 0;
            while (it.hasNext()) {
                Expr exp = it.next();
                if (!(exp instanceof LoadDeclValueExpr)) {
                    flg = false;
                    break;
                }
                Note out = exp.getOutDataEdge();
                if (out instanceof LoadValueIndirectExpr) {
                    cnt++;
                    continue;
                }
                if (out instanceof CallExpr) {
                    continue;
                }
                flg = false;
                break;
            }

            if (!flg || (cnt <= 0)) {
                continue;
            }

            // We have a by-reference parameter that we can replace by a local variable.

            Type type = d.getCoreType().getPointedTo();
            VariableDecl vd = genTemp(type);
            LoadDeclAddressExpr ldae = new LoadDeclAddressExpr(vd);
            Expr rhs = new LoadValueIndirectExpr(new LoadDeclValueExpr(d));
            ExprChord se = new ExprChord(ldae, rhs);

            // Insert store into replacement variable.

            insert.insertBeforeInCfg(se);

            // Replace all the indirect loads of the by-reference variable
            // with load directs of the local variable.

            Iterator<Expr> it2 = byRefTable.getRowEnumeration(d);
            while (it2.hasNext()) {
                LoadDeclValueExpr ldve = (LoadDeclValueExpr) it2.next();
                Note use = ldve.getOutDataEdge();
                if (use instanceof LoadValueIndirectExpr) {
                    LoadValueIndirectExpr lvie = (LoadValueIndirectExpr) use;
                    Note out = lvie.getOutDataEdge();
                    out.changeInDataEdge(lvie, new LoadDeclValueExpr(vd));
                }
                else {
                    Chord call = use.getChord();
                    LoadDeclAddressExpr ldae2 = new LoadDeclAddressExpr(vd);
                    Expr rhs2 = new LoadValueIndirectExpr(new LoadDeclValueExpr(d));
                    ExprChord se2 = new ExprChord(ldae2, rhs2);
                    Chord insrt = call.getNextChord();

                    // Insert store into replacement variable.

                    insrt.insertBeforeInCfg(se2);
                }
            }
        }
    }

    /**
     * Return the expression type and remove any type const attributes
     * from its type.
     */
    private Type getNonConstType(Expression exp)
    {
        return exp.getType().getNonConstType();
        // return exp.getType().getNonAttributeType();
    }

    private void addDeclaration(Declaration decl)
    {
        decls.addElement(decl);

        if (!decl.isVariableDecl()) {
            return;
        }

        variableCount++;
        if (decl.isTemporary()) {
            tempVariableCount++;
        }
        else if (decl.isGlobal()) {
            globalVariableCount++;
        }
        else if (decl.isEquivalenceDecl()) {
            equVariableCount++;
        }
        else if (decl instanceof FormalDecl) {
            formalVariableCount++;
        }
        else {
            regVariableCount++;
        }
    }

    /**
     * Generate a temporary.  The name of the temporary is computed by
     * this routine.
     *
     * @param t the type of the temporary.
     * @return the declaration for the temporary
     */
    private VariableDecl genTemp(Type t)
    {
        VariableDecl vd = new VariableDecl(un.genName(), t.getNonConstType());
        vd.setTemporary();
        addDeclaration(vd);
        return vd;
    }

    /**
     * Find an existing temporary that holds the same expression value.
     * If none, create a new one.
     *
     * @param exp the expression whose value will be stored in the temproary.
     * @return the temporary variable
     */
    private VariableDecl getTemp(Expr exp)
    {
        Type t = exp.getType();
        return genTemp(t);
    }

    private Expr makeTemp(Expr se)
    {
        Declaration d = getTemp(se);
        Expr tra = new LoadDeclAddressExpr(d);
        Expr trv = new LoadDeclValueExpr(d);
        ExprChord ta = new ExprChord(tra, se.conditionalCopy());
        recordNewChord(ta);
        exp = new ExprTuple(trv, ta, ta);
        return trv;
    }

    /**
     * Generate Scribble for the Clef Expression and place result in a
     * temporary if it is not a leaf expression.  No temporary is
     * generated if the expression is a "leaf" expression or if the type
     * of the expression is ProcedureType (since procedures can't be
     * treated like variables).
     *
     * @param e the Clef expression assigned to the temporary.
     */
    private void convertToTemp(Expression e)
    {
        e.visit(this);

        Expr se = exp.getRef();

        int ne = se.numInDataEdges();
        if (ne == 0) // If leaf, no temporary needed.
        {
            return;
        }

        if (se.getCoreType().isProcedureType()) {
            return;
        }

        if (ne == 1) {
            if (se instanceof ConversionExpr) {
                ConversionExpr ce = (ConversionExpr) se;
                if (ce.getConversion() == CastMode.CAST) {
                    Expr arg = ce.getArg();
                    if (arg.numInDataEdges() == 0) {
                        return;
                    }
                }
            }
        }

        ExprTuple sr = exp;
        Expr ref = makeTemp(se);

        sr.concat(exp);
        sr.setRef(ref);
        exp = sr;
    }

    /**
     * Turn the <i>simple</i> Score expression into an ExprChord
     * representing an assignment.  This is useful when you want a value
     * copied to a temporary variable.  simple expression in this
     * context consists of either variable references or a single
     * indirection from a simple reference.
     *
     * @param se the score expression
     * @param d the declaration representing the temporary.
     * @return the lhs of the ExprChord created
     */
    private LoadDeclAddressExpr makeTempAssignChord(Expr se, Declaration d)
    {
        LoadDeclAddressExpr tra = new LoadDeclAddressExpr(d);
        LoadExpr trv = new LoadDeclValueExpr(d);
        ExprChord ta = new ExprChord(tra, se);

        recordNewChord(ta);
        exp = new ExprTuple(trv, ta, ta);
        return tra;
    }

    /**
     * Turn the <i>simple</i> Score expression into an ExprChord
     * representing an assignment.  This is useful when you want a value
     * copied to a temporary variable.  simple expression in this
     * context consists of either variable references or a single
     * indirection from a simple reference.
     *
     * @param se the score expression
     * @param d the declaration representing the temporary.
     */
    private LoadDeclAddressExpr makeTempAssignChord(Expr se)
    {
        return makeTempAssignChord(se, getTemp(se));
    }

    /**
     * This method converts an r-value to an l-value.
     * The rvalue argument may be returned as the result so it is important
     * to make sure it is an unlinked expression (i.e., has no out data edge).
     *
     * @param rvalue is checked and converted if necessary
     * @param sr is the ExprTuple any new CFG nodes are appended to
     * @return the l-value expression
     */
    private Expr makeLValue(Expr rvalue, ExprTuple sr)
    {
        if (rvalue.isMemRefExpr()) {
            if (rvalue instanceof LoadDeclValueExpr) {
                return new LoadDeclAddressExpr(((LoadDeclValueExpr) rvalue).getDecl());
            }
            if (rvalue instanceof LoadValueIndirectExpr) {
                return rvalue.getOperand(0).copy();
            }
            if (rvalue instanceof LoadFieldValueExpr) {
                LoadFieldValueExpr lfve = (LoadFieldValueExpr) rvalue;
                return new LoadFieldAddressExpr(lfve.getStructure().copy(), lfve.getField());
            }
        }

//      if (rvalue instanceof DualExpr)
//        return makeLValue(((DualExpr) rvalue).getLow().copy(), sr);

        if (rvalue instanceof ConversionExpr) {
            ConversionExpr ce = (ConversionExpr) rvalue;
            if (ce.getArg() instanceof LoadDeclValueExpr) {
                if (ce.getConversion() == CastMode.CAST) {
                    return new LoadDeclAddressExpr(((LoadDeclValueExpr) rvalue.getOperand(0)).getDecl());
                }
            }
        }

        Expr lvalue = makeTempAssignChord(rvalue);
        sr.concat(exp);
        return lvalue.copy();
    }

    /**
     * This method convert an l-value to an r-value.
     * Always returns a copy.
     */
    private Expr makeRValue(Expr lvalue)
    {
        return LoadValueIndirectExpr.create(lvalue.conditionalCopy());
    }

    /**
     * Turn the <i>simple</i> Score expression into an ExprChord
     * representing an assignment.  A simple expression in this context
     * consists of either variable references or a single indirection
     * from a simple reference.
     *
     * @param lhsa is a valid l-value
     * @param rhs is a valid r-value
     */
    private void makeAssignChord(Expr lhsa, Expr rhs)
    {
        if (lhsa.validLValue()) {
            ExprChord ta = new ExprChord(lhsa.conditionalCopy(), rhs);
            recordNewChord(ta);
            exp = new ExprTuple(makeRValue(lhsa), ta, ta);
        }
        else {
            // Alias analysis doesn't like LoadValueIndirect or other
            // expressions on the left hand side.  We also want to keep the
            // left hand side as simple as possible.

            lhsa = makeTemp(lhsa);

            ExprChord ta = new ExprChord(lhsa, rhs);
            recordNewChord(ta);
            exp.append(ta);
        }
    }

    private void makeAssignChordT(Expr lhsa, Expr rhs, ExprTuple sr)
    {
        makeAssignChord(makeLValue(lhsa, sr), rhs);
        sr.concat(exp);
    }

    private void doRoutineDecl(RoutineDecl rd)
    {
        ProcedureType s = rd.getSignature();
        Type rt = s.getReturnType().getNonConstType();
        BeginChord beg = new BeginChord(scribble);
        ExprTuple sr = new ExprTuple(beg, beg);
        ReturnChord saveExit = exitS;
        int sla = rd.getSourceLineNumber();

        if (sla >= 0) {
            currentSla = sla;
            beg.setSourceLineNumber(currentSla);
        }

        parentLoop = beg;
        returnDecl = null;

        Expr retExpr = null;
        if (!rt.isVoidType()) {
            returnDecl = rd.getFtnResultVar();
            if (returnDecl == null) {
                returnDecl = genTemp(rt);
            }
            returnDecl.declareFtnResultVar();
            retExpr = new LoadDeclValueExpr(returnDecl);
        }
        exitS = new ReturnChord(retExpr);

        Node body = rd.getBody();
        if (body != null) {
            body.visit(this);
            sr.concat(exp);
            formalVariableCount += s.numFormals();
        }

        exitS.setSourceLineNumber(sla);

        sr.append(exitS);
        exitS = saveExit;
        exp = sr;
    }

    public void visitProcedureDecl(ProcedureDecl n)
    {
        doRoutineDecl(n);
    }

    /**
     * Record a new Chord for later processing by removeDeadCode().
     * Add source line info.
     */
    private void recordNewChord(Chord s)
    {
        chords.push(s);
        newCFGNodeCount++;
        s.setSourceLineNumber(currentSla);
    }

    /**
     * Called at the start of processing for each Clef Statement.
     */
    private void startNewStmt(Statement s)
    {
        if (slastkptr >= slaStack.length) {
            int[] ns = new int[slastkptr + 20];
            System.arraycopy(slaStack, 0, ns, 0, slastkptr);
            slaStack = ns;
        }

        slaStack[slastkptr++] = currentSla;
        currentSla = s.getSourceLineNumber();
        if (currentSla > maxLineNumber) {
            maxLineNumber = currentSla;
        }

        pragma = s.getPragma();
    }

    /**
     * Called at the end of processing for each Clef Statement
     */
    private void endNewStmt()
    {
        currentSla = slaStack[--slastkptr];
    }

    public void visitBlockStmt(BlockStmt cs)
    {
        ExprTuple sr = new ExprTuple(null, null);

        startNewStmt(cs);

        int l = cs.numStmts();
        for (int i = 0; i < l; i++) {
            Statement s = cs.getStmt(i);
            s.visit(this);
            sr.concat(exp);
        }

        exp = sr;
        endNewStmt();
    }

    /**
     * Build the graph for a conditional predicate.
     *
     * @param predicate is the predicate of the conditional
     * @param trueEdge is the true edge
     * @param falseEdge is the false edge
     */
    private IfThenElseChord genConditionalBranch(Expression predicate,
            Chord trueEdge,
            Chord falseEdge)
    {
        while (true) {
            if (predicate instanceof NotOp) {
                Chord t = trueEdge;
                trueEdge = falseEdge;
                falseEdge = t;
                predicate = ((NotOp) predicate).getExpr();
                continue;
            }

            if (predicate instanceof NotEqualOp) {
                NotEqualOp neo = (NotEqualOp) predicate;
                Expression la = neo.getExpr1();
                Expression ra = neo.getExpr2();
                if (la.hasTrueFalseResult() &&
                        (ra instanceof Literal) &&
                        ((Literal) ra).getConstantValue().isZero()) {
                    predicate = la;
                    continue;
                }
                else if (ra.hasTrueFalseResult() &&
                        (la instanceof Literal) &&
                        ((Literal) la).getConstantValue().isZero()) {
                    predicate = ra;
                    continue;
                }
            }
            else if (predicate instanceof ExpressionIfOp) {
                ExpressionIfOp cs = (ExpressionIfOp) predicate;
                Expression cexp = cs.getExpr1();
                Expression texp = cs.getExpr2();
                Expression fexp = cs.getExpr3();
                Type ct = cs.getCoreType();

                if (ct.isIntegerType() &&
                        (texp instanceof Literal) &&
                        (fexp instanceof Literal)) {
                    Literal tl = ((Literal) texp).getConstantValue();
                    Literal fl = ((Literal) fexp).getConstantValue();
                    if (tl.isOne() && fl.isZero()) {
                        predicate = cexp;
                        continue;
                    }
                }
            }
            break;
        }

        if (predicate instanceof AndConditionalOp) {
            AndConditionalOp ao = (AndConditionalOp) predicate;
            Expression arg1 = ao.getExpr1();
            Expression arg2 = ao.getExpr2();
            NullChord nxt = new NullChord();

            recordNewChord(nxt);

            genConditionalBranch(arg1, nxt, falseEdge);
            ExprTuple sr = exp;
            sr.append(nxt);

            IfThenElseChord ifChord = genConditionalBranch(arg2, trueEdge, falseEdge);
            sr.concat(exp);
            exp = sr;
            return ifChord;
        }

        if (predicate instanceof OrConditionalOp) {
            OrConditionalOp ao = (OrConditionalOp) predicate;
            Expression arg1 = ao.getExpr1();
            Expression arg2 = ao.getExpr2();
            NullChord nxt = new NullChord();

            recordNewChord(nxt);

            genConditionalBranch(arg1, trueEdge, nxt);
            ExprTuple sr = exp;
            sr.append(nxt);

            IfThenElseChord ifChord = genConditionalBranch(arg2, trueEdge, falseEdge);
            sr.concat(exp);
            exp = sr;
            return ifChord;
        }

        predicate.visit(this);

        ExprTuple sr = exp;
        IfThenElseChord ifChord = new IfThenElseChord(sr.getRef());

        recordNewChord(ifChord);
        sr.append(ifChord);
        ifChord.setTrueEdge(trueEdge);
        ifChord.setFalseEdge(falseEdge);
        exp = sr;
        return ifChord;
    }

    public void visitIfThenElseStmt(IfThenElseStmt cs)
    {
        Statement te = cs.getThenStmt();
        Statement fe = cs.getElseStmt();

        startNewStmt(cs);

        // Allocate the statement node.

        Chord bottom = new NullChord();
        recordNewChord(bottom);

        // Build true clause and hook up to predicate.

        Chord trueEdge = bottom;
        if (te != null) {
            te.visit(this);
            ExprTuple srt = exp;
            Chord tb = srt.getBegin();
            if (tb != null) {
                trueEdge = tb;
                Chord trueEnd = srt.getEnd();
                trueEnd.linkTo(bottom);
            }
        }

        // Build false clause and hook up to predicate.

        Chord falseEdge = bottom;
        if (fe != null) {
            fe.visit(this);
            ExprTuple srf = exp;
            Chord fb = srf.getBegin();
            if (fb != null) {
                falseEdge = fb;
                Chord falseEnd = srf.getEnd();
                falseEnd.linkTo(bottom);
            }
        }

        // Build the expression graph for the condition predicate.

        genConditionalBranch(cs.getExpr(), trueEdge, falseEdge);

        exp = new ExprTuple(exp.getBegin(), bottom);

        endNewStmt();
    }

    private void setTarget(BranchChord gs, LabelDecl l)
    {
        Chord target = gotos.getChord(l);
        if (target == null) {
            gotos.add(gs, l, null);
        }
        else {
            gs.setTarget(target);
        }
    }

    private void setTarget(SwitchChord gs, LabelDecl l, Object index)
    {
        Chord target = gotos.getChord(l);
        if (target == null) {
            gotos.add(gs, l, index);
        }
        else {
            gs.addBranchEdge(index, target);
        }
    }

    private void setTarget(IfThenElseChord ifChord, LabelDecl l, boolean edge)
    {
        Chord target = gotos.getChord(l);
        if (target == null) {
            gotos.add(ifChord, l, Boolean.valueOf(edge));
        }
        else {
            if (edge) {
                ifChord.setTrueEdge(target);
            }
            else {
                ifChord.setFalseEdge(target);
            }
        }
    }

    public void visitArithmeticIfStmt(ArithmeticIfStmt s)
    {
        Expression pred = s.getExpr();
        Type t = pred.getCoreType();
        LabelDecl less = s.getLessLabel();
        LabelDecl equ = s.getEqualLabel();
        LabelDecl more = s.getMoreLabel();
        Literal lit;

        if (t.isRealType()) {
            lit = LiteralMap.put(0.0, t);
        }
        else {
            lit = LiteralMap.put(0, t);
        }

        Expr le = new LiteralExpr(lit);

        startNewStmt(s);

        containedGoto = true;

        if (less == equ) {
            pred.visit(this); // Convert the predicate
            ExprTuple sr = exp;
            Expr test = new LessEqualExpr(boolType, exp.getRef(), le);
            IfThenElseChord if1 = new IfThenElseChord(test);
            recordNewChord(if1);
            sr.append(if1);
            setTarget(if1, less, true);
            setTarget(if1, more, false);
            exp = sr;
        }
        else if (less == more) {
            pred.visit(this); // Convert the predicate
            ExprTuple sr = exp;
            Expr test = new NotEqualExpr(boolType, exp.getRef(), le);
            IfThenElseChord if1 = new IfThenElseChord(test);
            recordNewChord(if1);
            sr.append(if1);
            setTarget(if1, less, true);
            setTarget(if1, equ, false);
            exp = sr;
        }
        else if (more == equ) {
            pred.visit(this); // Convert the predicate
            ExprTuple sr = exp;
            Expr test = new GreaterEqualExpr(boolType, exp.getRef(), le);
            IfThenElseChord if1 = new IfThenElseChord(test);
            recordNewChord(if1);
            sr.append(if1);
            setTarget(if1, more, true);
            setTarget(if1, less, false);
            exp = sr;
        }
        else {
            convertToTemp(pred); // Convert the predicate
            ExprTuple sr = exp;
            Expr tr = exp.getRef();
            Expr pred1 = new LessExpr(boolType, tr, le);
            Expr pred2 = new GreaterExpr(boolType, tr.copy(), le.copy());
            IfThenElseChord if1 = new IfThenElseChord(pred1);
            IfThenElseChord if2 = new IfThenElseChord(pred2);
            recordNewChord(if1);
            sr.append(if1);
            recordNewChord(if2);
            sr.append(if2);
            setTarget(if1, less, true);
            if1.setFalseEdge(if2);
            setTarget(if2, more, true);
            setTarget(if2, equ, false);
            exp = sr;
        }

        endNewStmt();
    }

    public void visitComputedGotoStmt(ComputedGotoStmt s)
    {
        startNewStmt(s);

        containedGoto = true;

        Expression pred = s.getExpr();
        pred.visit(this); // Convert the predicate
        ExprTuple sr = exp;
        // Allocate the statement node.
        SwitchChord swChord = new SwitchChord(exp.getRef());
        NullChord exit = new NullChord();
        recordNewChord(exit);
        recordNewChord(swChord);
        sr.append(swChord);
        sr.append(exit);

        int l = s.numLabels();
        for (int i = 0; i < l; i++) {
            LabelDecl lab = s.getLabel(i);
            Type type = Machine.currentMachine.getIntegerCalcType();
            setTarget(swChord, lab, LiteralMap.put(i + 1, type));
        }

        swChord.addBranchEdge("", exit);
        gotos.defineLabel(exit, exit);
        gotos.add(swChord, exit, "");

        exp = sr;

        endNewStmt();
    }

    public void visitSwitchStmt(SwitchStmt s)
    {
        Vector<Object> saveCaseLabels = caseLabels;  // For nesting of switch statements

        startNewStmt(s);

        caseLabels = new Vector<Object>(3);

        s.getExpr().visit(this); // Convert the predicate
        ExprTuple sr = exp;

        // Allocate the statement node.

        SwitchChord swChord = new SwitchChord(exp.getRef());
        recordNewChord(swChord);
        sr.append(swChord);

        // To simplify cfg construction, we insert a null statement on the
        // exit path from the switch.

        NullChord exit = new NullChord();
        recordNewChord(exit);

        Chord saveBreak = breakS;
        breakS = exit;

        s.getStmt().visit(this);
        sr.concat(exp);
        sr.append(exit);

        breakS = saveBreak;

        boolean hasDefault = false;
        int l = caseLabels.size();
        for (int i = 0; i < l; i += 2) {
            LabelDecl ld = (LabelDecl) caseLabels.elementAt(i);
            Object index = caseLabels.elementAt(i + 1);
            hasDefault = index.equals("");
            gotos.add(swChord, ld, index);
        }

        // Add a default if none was defined.

        if (!hasDefault) {
            swChord.addBranchEdge("", exit);
            gotos.defineLabel(exit, exit);
            gotos.add(swChord, exit, "");
        }

        caseLabels = saveCaseLabels;

        exp = sr;

        endNewStmt();
    }

    public void visitWhileLoopStmt(WhileLoopStmt cs)
    {
        Expression ce = cs.getExpr();

        startNewStmt(cs);

        boolean lte = (pragma != null) && pragma.isSet(PragmaStk.LOOP_TEST_AT_END);

        // Allocate the statement node for the loop test.

        LoopInitChord lin = new LoopInitChord();
        LoopPreHeaderChord lph = new LoopPreHeaderChord();
        LoopHeaderChord lh = new LoopHeaderChord(scribble, parentLoop);
        NullChord exit = new NullChord();
        LoopTailChord tail = new LoopTailChord();
        ExprTuple sr = new ExprTuple(lin, lin);

        lh.setLoopTail(tail);
        lh.setLoopInit(lin);
        lh.usePragma(pragma);

        recordNewChord(lin);
        recordNewChord(lh);
        recordNewChord(lph);
        recordNewChord(exit);
        recordNewChord(tail);

        // To simplify cfg construction, we insert a null statement on the
        // exit path from the loop.

        Chord saveBreak = breakS;
        Chord saveContinue = continueS;
        LoopHeaderChord saveParentLoop = parentLoop;

        breakS = exit;
        continueS = tail;
        parentLoop = lh;

        // Generate the loop body

        cs.getStmt().visit(this);
        ExprTuple body_r = exp;
        body_r.append(tail);

        breakS = saveBreak;
        continueS = saveContinue;
        parentLoop = saveParentLoop;

        if (false && lte) { // Put tests at the end.
            NullChord after = new NullChord();
            IfThenElseChord preIf = genConditionalBranch(ce, lph, after);
            sr.concat(exp);
            ExprTuple sr2 = new ExprTuple(lph, lph);
            recordNewChord(after);
            sr2.append(lh);
            sr2.concat(body_r);

            IfThenElseChord ifChord = genConditionalBranch(ce, lh, exit);
            lh.setLoopTest(ifChord);

            sr2.concat(exp);
            exit.setTarget(after);
            exp = new ExprTuple(sr.getBegin(), after);
        }
        else {
            sr.append(lph);
            sr.append(lh);
            // The test always flows into the body on true;
            // The test always exits on false.
            IfThenElseChord ifChord = genConditionalBranch(ce, body_r.getBegin(), exit);
            lh.setLoopTest(ifChord);

            sr.concat(exp);
            tail.setTarget(lh);
            exp = new ExprTuple(sr.getBegin(), exit);
        }

        endNewStmt();
    }

    public void visitRepeatWhileLoopStmt(RepeatWhileLoopStmt cs)
    {
        Expression ce = cs.getExpr();
        LoopInitChord lin = new LoopInitChord();
        LoopPreHeaderChord lph = new LoopPreHeaderChord();
        LoopHeaderChord lh = new LoopHeaderChord(scribble, parentLoop);
        ExprTuple sr = new ExprTuple(lin, lin);

        startNewStmt(cs);

        recordNewChord(lph);
        recordNewChord(lh);
        recordNewChord(lin);

        lh.setLoopInit(lin);
        lh.usePragma(pragma);

        // To simplify cfg construction, we insert a null statement on the
        // exit path from the loop.

        NullChord exit = new NullChord();
        recordNewChord(exit);

        LoopTailChord tail = new LoopTailChord();
        recordNewChord(tail);
        lh.setLoopTail(tail);
        tail.setTarget(lh);

        Chord saveBreak = breakS;
        Chord saveContinue = continueS;
        LoopHeaderChord saveParentLoop = parentLoop;

        IfThenElseChord ifChord = genConditionalBranch(ce, tail, exit);
        ExprTuple test_r = exp;

        lh.setLoopTest(ifChord);

        breakS = exit;
        continueS = test_r.getBegin();
        parentLoop = lh;

        // Generate the loop body

        cs.getStmt().visit(this);

        sr.append(lph);
        sr.append(lh);
        sr.concat(exp);
        sr.concat(test_r);

        exp = new ExprTuple(sr.getBegin(), exit);

        breakS = saveBreak;
        continueS = saveContinue;
        parentLoop = saveParentLoop;

        endNewStmt();
    }

    public void visitRepeatUntilLoopStmt(RepeatUntilLoopStmt cs)
    {
        Expression ce = cs.getExpr();
        LoopInitChord lin = new LoopInitChord();
        LoopPreHeaderChord lph = new LoopPreHeaderChord();
        LoopHeaderChord lh = new LoopHeaderChord(scribble, parentLoop);
        ExprTuple sr = new ExprTuple(lin, lin);

        startNewStmt(cs);

        recordNewChord(lph);
        recordNewChord(lh);
        recordNewChord(lin);

        lh.setLoopInit(lin);
        lh.usePragma(pragma);

        // To simplify cfg construction, we insert a null statement on the
        // exit path from the loop.

        NullChord exit = new NullChord();
        recordNewChord(exit);

        LoopTailChord tail = new LoopTailChord();
        recordNewChord(tail);
        lh.setLoopTail(tail);
        tail.setTarget(lh);

        Chord saveBreak = breakS;
        Chord saveContinue = continueS;
        LoopHeaderChord saveParentLoop = parentLoop;

        IfThenElseChord ifChord = genConditionalBranch(ce, exit, tail);
        ExprTuple test_r = exp;

        lh.setLoopTest(ifChord);

        breakS = exit;
        continueS = test_r.getBegin();
        parentLoop = lh;

        // Generate the loop body

        cs.getStmt().visit(this);

        sr.append(lph);
        sr.append(lh);
        sr.concat(exp);
        sr.concat(test_r);

        breakS = saveBreak;
        continueS = saveContinue;
        parentLoop = saveParentLoop;

        exp = new ExprTuple(sr.getBegin(), exit);

        endNewStmt();
    }

    private MatchExpr makeTermTest(int increasing, Expr index, Expr initial, Expr limit)
    {
        if (increasing > 0) {
            MatchExpr termTest = new LessEqualExpr(boolType, index, limit);
            exp = new ExprTuple(termTest, null, null);
            return termTest;
        }

        if (increasing < 0) {
            MatchExpr termTest = new GreaterEqualExpr(boolType, index, limit);
            exp = new ExprTuple(termTest, null, null);
            return termTest;
        }

        // Loop direction is unknown

        makeTempAssignChord(new LessEqualExpr(boolType, initial, limit));
        ExprTuple tr = exp;
        Expr termTest = exp.getRef();

        IfThenElseChord ifChord = new IfThenElseChord(termTest.copy());
        recordNewChord(ifChord);
        tr.append(ifChord);

        Chord bottom = new NullChord();
        recordNewChord(bottom);

        // Build true clause

        makeTempAssignChord(new LessEqualExpr(boolType, index, limit.copy()));
        ExprTuple srt = exp;
        makeAssignChordT(termTest.copy(), exp.getRef(), srt);

        // Build false clause

        makeTempAssignChord(new GreaterEqualExpr(boolType, index.copy(), limit.copy()));
        ExprTuple srf = exp;
        makeAssignChordT(termTest.copy(), exp.getRef(), srf);

        ifChord.setTrueEdge(srt.getBegin());
        ifChord.setFalseEdge(srf.getBegin());
        srt.getEnd().linkTo(bottom);
        srf.getEnd().linkTo(bottom);

        exp = new ExprTuple(tr.getBegin(), bottom);

        return new NotEqualExpr(boolType, termTest, new LiteralExpr(LiteralMap.put(0, boolType)));
    }

    /**
     * Process the limit or step expression.
     */
    private Expr processDoExpression(Expression expr, ExprTuple sr, int numStmts)
    {
        Expression ct = expr.getConstantValue();
        if ((ct != Lattice.Bot) && (ct != Lattice.Top)) {
            expr = ct;
        }

        expr.visit(this);
        sr.concat(exp);
        Expr ref = exp.getRef();
        if (!ref.isLiteralExpr()) {
            ref = makeTemp(ref);
            if (numStmts > 8) {
                // For large loops, it is faster to reload the limit/step from
                // the stack if it means fewer spills.
                LoadDeclValueExpr ldve = (LoadDeclValueExpr) ref;
                VariableDecl vd = (VariableDecl) ldve.getDecl();
                vd.setStorageLoc(Assigned.ON_STACK);
            }
        }
        sr.concat(exp);
        return ref;
    }

    public void visitDoLoopStmt(DoLoopStmt cs)
    {
        LoopInitChord lin = new LoopInitChord();
        ExprTuple sr = new ExprTuple(lin, lin);
        Expression ind = cs.getIndex();

        startNewStmt(cs);

        boolean lte = (pragma != null) && pragma.isSet(PragmaStk.LOOP_TEST_AT_END);

        ind.visit(this);
        Expr index = exp.getRef();
        sr.concat(exp);

        VariableDecl iv = null;
        Declaration decl = null;
        if (index instanceof LoadDeclValueExpr) {
            decl = ((LoadDeclValueExpr) index).getDecl();
            VariableDecl vd = decl.returnVariableDecl();
            if ((vd != null) &&
                    !vd.getCoreType().isPointerType() &&
                    vd.optimizationCandidate()) {
                iv = vd;
            }
        }

        // Determine the type used by the induction variable.  If the DO
        // index variable is an argument to the function, then it will be
        // a pointer to the actual variable and is already an lvalue.

        Type t = index.getType();
        if (t.isPointerType()) {
            t = t.getCoreType().getPointedTo();
        }
        else {
            index = makeLValue(index, sr);
        }

        t = t.getNonConstType();

        if (iv == null) {
            iv = genTemp(t);
        }

        cs.getExprInit().visit(this);
        Expr initial = exp.getRef();
        sr.concat(exp);

        makeTempAssignChord(initial, iv);
        sr.concat(exp);

        if (decl != iv) {
            makeAssignChord(index, new LoadDeclValueExpr(iv));
            sr.concat(exp);
        }

        Statement block = cs.getStmt();
        int numStmts = block.numTotalStmts();

        // Convert the loop limit and step expressions.

        Expr limit = processDoExpression(cs.getExprTerm(), sr, numStmts);
        Expr inc = processDoExpression(cs.getExprInc(), sr, numStmts);

        LoopPreHeaderChord lph = new LoopPreHeaderChord();
        LoopHeaderChord lh = new LoopHeaderChord(scribble, parentLoop);
        NullChord exit = new NullChord();
        LoopTailChord tail = new LoopTailChord();
        InductionVar ivar = new InductionVar(lh, iv);

        lh.setLoopTail(tail);
        lh.setLoopInit(lin);
        lh.defPrimaryInductionVariable(ivar);
        lh.usePragma(pragma);

        ivar.setInitExpr(initial);
        ivar.setStepExpr(inc);

        recordNewChord(lin);
        recordNewChord(lh);
        recordNewChord(lph);
        recordNewChord(exit);
        recordNewChord(tail);

        Expr add = new AdditionExpr(t, new LoadDeclValueExpr(iv), inc);
        makeAssignChord(new LoadDeclAddressExpr(iv), add);
        ExprTuple stp = exp;

        // Allocate the test statement node.

        int increasing = 0;
        Literal incv = inc.getConstantValue(cvMap);

        if (incv != null) {
            try {
                increasing = (int) Lattice.convertToLongValue(incv);
            }
            catch (InvalidException ex) {
            }
        }

        MatchExpr termTest = makeTermTest(increasing,
                new LoadDeclValueExpr(iv),
                initial.copy(),
                limit);
        ExprTuple tt = exp;
        IfThenElseChord ifChord = new IfThenElseChord(termTest);
        ivar.setTermExpr(termTest, false);

        recordNewChord(ifChord);
        lh.setLoopTest(ifChord);

        Chord saveBreak = breakS;
        Chord saveContinue = continueS;
        LoopHeaderChord saveParentLoop = parentLoop;

        breakS = exit;
        continueS = stp.getBegin();
        parentLoop = lh;

        ExprTuple body = new ExprTuple(null, null);

        // Insert a def of the user index variable, from the induction
        // variable created by the compiler, at the start of the loop
        // body.  This may seem like an unnecessary thing to do as the
        // index variable is set prior to the loop (in case of 0-trip
        // loops).  But, it is extremely important if the loop is unrolled
        // and copy propagation is performed.  Otherwise, aliases are
        // created for array indexes resulting in more temporary variables
        // (i.e., registers) than necessary.

        if (decl != iv) {
            makeAssignChord(index.copy(), new LoadDeclValueExpr(iv));
            body.concat(exp);
        }

        // Generate the loop body.

        block.visit(this);
        body.concat(exp);
        body.concat(stp);

        if (decl != iv) {
            makeAssignChord(index.copy(), new LoadDeclValueExpr(iv));
            body.concat(exp);
        }

        if (lte) {
            LoadDeclValueExpr ldiv = new LoadDeclValueExpr(iv);
            Expr termTestp = makeTermTest(increasing, ldiv, initial.copy(), limit.copy());
            sr.concat(exp);
            IfThenElseChord preIf = new IfThenElseChord(termTestp);
            recordNewChord(preIf);
            sr.append(preIf);
            NullChord after = new NullChord();
            recordNewChord(after);
            ExprTuple sr2 = new ExprTuple(lph, lph);
            sr2.append(lph);
            sr2.append(lh);
            sr2.concat(body);
            sr2.concat(tt);
            sr2.append(ifChord);
            sr2.append(tail);
            preIf.setTrueEdge(sr2.getBegin());
            preIf.setFalseEdge(after);
            ifChord.setTrueEdge(tail);
            ifChord.setFalseEdge(exit);
            tail.setTarget(lh);
            exit.setTarget(after);
            exp = new ExprTuple(sr.getBegin(), after);
        }
        else {
            body.append(tail);
            sr.append(lph);
            sr.append(lh);
            sr.concat(tt);
            sr.append(ifChord);
            ifChord.setTrueEdge(body.getBegin());
            ifChord.setFalseEdge(exit);
            tail.setTarget(lh);
            exp = new ExprTuple(sr.getBegin(), exit);
        }

        breakS = saveBreak;
        continueS = saveContinue;
        parentLoop = saveParentLoop;

        endNewStmt();
    }

    private ExprTuple visitExpr(Expression expr)
    {
        if (expr == null) {
            return new ExprTuple(null, null);
        }

        if (expr instanceof AssignSimpleOp) {
            // If the value of the assign expression is not used then we do not
            // have to worry about re-evaluating it.  For example, for
            //   if (a = a->next) {}
            // we must save the value of the right hand side.
            doAssignSimpleOp((AssignSimpleOp) expr, false);
            return exp;
        }

        if (expr instanceof PostIncrementOp) {
            doPostOp(ADD, (PostIncrementOp) expr, false);
            return exp;
        }

        if (expr instanceof PostDecrementOp) {
            doPostOp(SUBT, (PostDecrementOp) expr, false);
            return exp;
        }

        if (expr instanceof CompoundAssignmentOp) {
            if (expr instanceof AdditionAssignmentOp) {
                doAdditionAssignmentOp((AdditionAssignmentOp) expr, false);
                return exp;
            }

            if (expr instanceof SubtractionAssignmentOp) {
                doSubtractionAssignmentOp((SubtractionAssignmentOp) expr, false);
                return exp;
            }

            if (expr instanceof BitShiftAssignmentOp) {
                doBitShiftAssignmentOp((BitShiftAssignmentOp) expr, false);
                return exp;
            }

            int op = 0;
            if (expr instanceof MultiplicationAssignmentOp) {
                op = MULT;
            }
            else if (expr instanceof DivisionAssignmentOp) {
                op = DIV;
            }
            else if (expr instanceof RemainderAssignmentOp) {
                op = REM;
            }
            else if (expr instanceof BitAndAssignmentOp) {
                op = AND;
            }
            else if (expr instanceof BitXorAssignmentOp) {
                op = XOR;
            }
            else if (expr instanceof BitOrAssignmentOp) {
                op = OR;
            }

            doCompoundAssignment(op, (CompoundAssignmentOp) expr, false);
            return exp;
        }

        if (expr instanceof CallFunctionOp) {
            doCallFunctionOp((CallFunctionOp) expr, false);
            exp.append(new ExprChord(exp.getRef()));
            return exp;
        }

        if (expr instanceof SeriesOp) {
            doSeriesOp((SeriesOp) expr, false);
            return exp;
        }

        expr.visit(this);

        return exp;
    }

    public void visitForLoopStmt(ForLoopStmt cs)
    {
        startNewStmt(cs);

        boolean lte = (pragma != null) && pragma.isSet(PragmaStk.LOOP_TEST_AT_END);

        // Set up the loop header with variable initialization.

        LoopInitChord lin = new LoopInitChord();
        ExprTuple sr = new ExprTuple(lin, lin);
        Expression init = cs.getExprInit();
        Expression step = cs.getExprInc();
        Expression ce = cs.getExprTest();
        ExprTuple sri = visitExpr(init);

        sr.concat(sri);

        LoopPreHeaderChord lph = new LoopPreHeaderChord();
        LoopHeaderChord lh = new LoopHeaderChord(scribble, parentLoop);

        lh.setLoopInit(lin);
        lh.usePragma(pragma);

        recordNewChord(lin);
        recordNewChord(lph);
        recordNewChord(lh);

        VariableDecl iv = null;
        if (init instanceof AssignSimpleOp) {
            AssignSimpleOp ass = (AssignSimpleOp) init;
            Expression lhs = ass.getLhs();
            if (lhs instanceof IdAddressOp) {
                iv = (VariableDecl) ((IdAddressOp) lhs).getDecl();
                if ((ce == null) ||
                        (step == null) ||
                        !ce.containsDeclaration(iv) ||
                        !step.containsDeclaration(iv)) {
                    iv = null;
                }
            }
        }

        // To simplify cfg construction, we insert a null statement on the
        // exit path from the loop.

        NullChord exit = new NullChord();
        recordNewChord(exit);

        LoopTailChord tail = new LoopTailChord();
        recordNewChord(tail);
        lh.setLoopTail(tail);

        Chord saveBreak = breakS;
        Chord saveContinue = continueS;
        LoopHeaderChord saveParentLoop = parentLoop;

        breakS = exit;
        parentLoop = lh;

        // Set up the loop increment

        ExprTuple inc_r = visitExpr(step);
        Expr inc = extractStep(inc_r, iv);

        if (inc_r.getBegin() == null) {
            Chord s = new NullChord();
            inc_r = new ExprTuple(inc_r.getRef(), s, s);
        }

        continueS = inc_r.getBegin();

        // Generate the loop body

        Statement block = cs.getStmt();
        block.visit(this);
        ExprTuple body_r = exp;

        // If a for loop has no exit test (e.g., for(;;)), add one so that
        // the CFG still reaches the end node.  The exit test will be
        // eliminated later.

        if (ce == null) // No loop exit test - create one.
        {
            ce = LiteralMap.put(1, boolType);
        }

        // Generate the loop exit test and link up the loop nodes.

        IfThenElseChord ifChord = null;
        if (ce.isSimpleOp() && lte) { // Put loop exit test at the end.
            NullChord after = new NullChord();
            IfThenElseChord preIf = genConditionalBranch(ce, lph, after);
            ExprTuple sr2 = new ExprTuple(lph, lph);
            recordNewChord(after);
            sr.concat(exp);
            sr2.append(lh);
            sr2.concat(body_r);
            sr2.concat(inc_r);

            ifChord = genConditionalBranch(ce, tail, exit);
            lh.setLoopTest(ifChord);
            sr2.concat(exp);
            exit.setTarget(after);
            tail.setTarget(lh);
            exp = new ExprTuple(sr.getBegin(), after);
        }
        else { // Put loop exit test at the start.
            inc_r.append(tail);
            sr.append(lph);
            sr.append(lh);
            body_r.concat(inc_r);
            ifChord = genConditionalBranch(ce, body_r.getBegin(), exit);
            lh.setLoopTest(ifChord);
            sr.concat(exp);

            tail.setTarget(lh);
            exp = new ExprTuple(sr.getBegin(), exit);
        }

        if ((iv != null) && (inc != null)) {
            Expr match = ifChord.getPredicateExpr();
            if (match.isMatchExpr() && match.containsDeclaration(iv)) {
                InductionVar ivar = new InductionVar(lh, iv);
                lh.defPrimaryInductionVariable(ivar);
                ivar.setInitExpr(sri.getRef());
                ivar.setStepExpr(inc.copy());
                ivar.setTermExpr((MatchExpr) match, false);
            }
        }

        breakS = saveBreak;
        continueS = saveContinue;
        parentLoop = saveParentLoop;

        endNewStmt();
    }

    private Expr extractStep(ExprTuple inc_r, VariableDecl iv)
    {
        if (iv == null) {
            return null;
        }

        Chord bc = inc_r.getBegin();
        if (bc == null) {
            Expr inc = inc_r.getRef();
            if ((inc instanceof BinaryExpr) && inc.containsDeclaration(iv)) {
                return inc;
            }
            return null;
        }

        if (bc.isAssignChord()) {
            ExprChord c = (ExprChord) bc;
            Expr inc = c.getRValue();
            if (inc.containsDeclaration(iv) &&
                    (inc instanceof BinaryExpr) &&
                    c.getLValue().containsDeclaration(iv)) {
                return inc;
            }
        }

        Chord ec = inc_r.getEnd();
        if ((ec != bc) && ec.isAssignChord()) {
            ExprChord c = (ExprChord) ec;
            Expr inc = c.getRValue();
            if (inc.containsDeclaration(iv) &&
                    (inc instanceof BinaryExpr) &&
                    c.getLValue().containsDeclaration(iv)) {
                return inc;
            }
        }

        return null;
    }

    public void visitBreakStmt(BreakStmt cs)
    {
        startNewStmt(cs);

        GotoChord gs = new GotoChord(breakS);
        recordNewChord(gs);
        exp = new ExprTuple(gs, gs);

        endNewStmt();
    }

    public void visitContinueStmt(ContinueStmt cs)
    {
        startNewStmt(cs);

        GotoChord gs = new GotoChord(continueS);
        recordNewChord(gs);
        exp = new ExprTuple(gs, gs);

        endNewStmt();
    }

    public void visitGotoStmt(GotoStmt cs)
    {
        startNewStmt(cs);

        containedGoto = true;

        LabelDecl l = cs.getLabel();
        GotoChord gs = new GotoChord(null);

        recordNewChord(gs);
        setTarget(gs, l);
        exp = new ExprTuple(gs, gs);

        endNewStmt();
    }

    /**
     * Create a scribble return statement. Note that we copy the return
     * expression into a special variable in order to make later
     * analysis easier (e.g., alias analysis).
     */
    public void visitReturnStmt(ReturnStmt cs)
    {
        Expression ce = cs.getExpr();
        ExprTuple sr;

        startNewStmt(cs);

        if ((ce == null) ||
                ((ce instanceof IdValueOp) && (((IdValueOp) ce).getDecl() == returnDecl))) {
            sr = new ExprTuple(exitS, exitS);
        }
        else {
            ce.visit(this);
            sr = exp;
            if (returnDecl != null) {
                makeTempAssignChord(exp.getRef(), returnDecl);
                sr.concat(exp);
            }
            sr.append(exitS);
        }
        exp = sr;

        endNewStmt();
    }

    public void visitExitStmt(ExitStmt cs)
    {
        Expression ce = cs.getExpr();
        ExitChord ss;
        ExprTuple sr;

        startNewStmt(cs);

        if (ce == null) {
            ss = new ExitChord(exitS);
            sr = new ExprTuple(ss, ss);
        }
        else {
            ce.visit(this);
            sr = exp;
            ss = new ExitChord(exitS, exp.getRef());
            sr.append(ss);
        }
        recordNewChord(ss);
        exp = sr;

        endNewStmt();
    }

    public void visitEvalStmt(EvalStmt cs)
    {
        startNewStmt(cs);

        visitExpr(cs.getExpr());

        if (exp.getBegin() == null) {
            Expr x = exp.getRef();
            ExprChord ec = new ExprChord(x);
            exp = new ExprTuple(x, ec, ec);
        }

        endNewStmt();
    }

    public void visitDeclStmt(DeclStmt cs)
    {
        ExprTuple sr = new ExprTuple(null, null);
        Declaration cd = cs.getDecl();

        startNewStmt(cs);

        if (!(cd.isRoutineDecl())) {
            addDeclaration(cd);
        }

        if (cd instanceof ValueDecl) { // For now don't do anything with other kinds of declarations.
            ValueDecl cvd = (ValueDecl) cd;
            Expression ce = cvd.getValue();

            if (ce != null) {
                Literal cec = ce.getConstantValue();
                if ((cec != Lattice.Bot) && (cec != Lattice.Top)) {
                    ce = cec;
                }
            }

            if ((ce != null) &&
                    (cvd.residency() != Residency.MEMORY) &&
                    !cvd.getType().isConst()) {
                ArrayType at1 = cd.getCoreType().returnArrayType();
                ArrayType at2 = ce.getCoreType().returnArrayType();
                if ((at1 != null) && (at2 != null)) {
                    if (at1.numberOfElements() > at2.numberOfElements()) {
                        // This is a kludge and only works for strings.  It's a
                        // kludge because it may result in a very large sequence
                        // of zero-bytes, allocated in memory, that could be better
                        // represented by a loop storing a zero byte.
                        if (ce instanceof StringLiteral) {
                            ce = new StringLiteral(at1, ((StringLiteral) ce).getString());
                        }
                    }
                }

                Expr ref = new LoadDeclAddressExpr(cvd);

                // Remove the initializer from the decl (otherwise it gets
                // generated twice).

                cvd.setValue(null);

                ce.visit(this);
                sr.concat(exp);
                Expr l = exp.getRef();

                makeAssignChord(ref, l);
                sr.concat(exp);
            }
        }

        exp = sr;

        endNewStmt();
    }

    public void visitNullStmt(NullStmt cs)
    {
        Chord ss = new NullChord();

        startNewStmt(cs);

        recordNewChord(ss);
        exp = new ExprTuple(ss, ss);

        endNewStmt();
    }

    public void visitLabelStmt(LabelStmt cs)
    {
        LabelDecl l = cs.getLabel();

        startNewStmt(cs);

        if (l instanceof CaseLabelDecl) {
            CaseLabelDecl d = (CaseLabelDecl) l;
            Expression e = d.getExpr();
            caseLabels.addElement(l);
            if (e == null) {
                caseLabels.addElement("");
            }
            else {
                Literal o = e.getConstantValue();
                assert (o != null) : "CaseLabel not understood " + e;
                caseLabels.addElement(o);
            }
        }

        cs.getStmt().visit(this);
        gotos.defineLabel(l, exp.getBegin());

        endNewStmt();
    }

    public void visitLiteral(Literal li)
    {
        Expr se = new LiteralExpr(li);

        exp = new ExprTuple(se, null, null);
    }

    public void visitAddressLiteral(AddressLiteral al)
    {
        Declaration d = al.getDecl();
        long of = al.getOffset();

        if (d != null) {
            Expr ldae = new LoadDeclAddressExpr(d);
            Type type = al.getType();
            if (of != 0) {
                LiteralExpr off = new LiteralExpr(LiteralMap.put(of, ptrdiff_t_type));
                ldae = AdditionExpr.create(ldae.getType(), ldae, off);
            }
            Expr ce = ConversionExpr.create(type, ldae, CastMode.CAST);
            exp = new ExprTuple(ce, null, null);
            return;
        }

        Literal lit = al.getValue();
        VariableDecl vd = genTemp(lit.getType());
        vd.setValue(lit);

        if (of == 0) {
            Expr ldae = ConversionExpr.create(al.getType(),
                    new LoadDeclAddressExpr(vd),
                    CastMode.CAST);
            exp = new ExprTuple(ldae, null, null);
            return;
        }

        visitLiteral(new AddressLiteral(al.getType(), vd, of));
    }

    public void visitIntLiteral(IntLiteral ce)
    {
        visitLiteral(ce);
    }

    public void visitFloatLiteral(FloatLiteral ce)
    {
        visitLiteral(ce);
    }

    public void visitComplexLiteral(ComplexLiteral ce)
    {
        visitLiteral(ce);
    }

    public void visitSizeofLiteral(SizeofLiteral ce)
    {
        visitLiteral(ce);
    }

    public void visitStringLiteral(StringLiteral sl)
    {
        VariableDecl sv = stringMap.get(sl);
        if (sv == null) { // Always use the same variable for the same string.
            sv = genTemp(sl.getType());
            sv.setInitialValue(sl);
            sv.setResidency(Residency.MEMORY);
            stringMap.put(sl, sv);
        }

        Expr ld = new LoadDeclValueExpr(sv);
        exp = new ExprTuple(ld, null, null);
    }

    public void visitFloatArrayLiteral(FloatArrayLiteral fal)
    {
        Type type = RefType.create(fal.getType(), RefAttr.Const);
        VariableDecl sv = genTemp(type);
        sv.setInitialValue(fal);
        sv.setResidency(Residency.MEMORY);
        Expr ld = new LoadDeclValueExpr(sv);
        exp = new ExprTuple(ld, null, null);
    }

    public void visitIntArrayLiteral(IntArrayLiteral fal)
    {
        Type type = RefType.create(fal.getType(), RefAttr.Const);
        VariableDecl sv = genTemp(type);
        sv.setInitialValue(fal); // set its initial value to the aggregation
        sv.setResidency(Residency.MEMORY);
        Expr ld = new LoadDeclValueExpr(sv);
        exp = new ExprTuple(ld, null, null);
    }

    public void visitAggregationElements(AggregationElements ag)
    {
        Type type = ag.getType();
        Vector<Object> ea = ag.getElementVector();
        int l = ea.size();

        if (!type.isCompositeType()) {
            assert (l == 1) : "Initializers (" + l + ") " + type;
            Expression exp = (Expression) ea.elementAt(0);
            exp.visit(this);
            return;
        }

        if (ag.containsAllLiterals()) { // All initializers are constants.
            if (!type.isConst()) {
                type = RefType.create(type, RefAttr.Const);
            }
            VariableDecl sv = genTemp(type);
            sv.setInitialValue(ag); // set its initial value to the aggregation
            sv.setResidency(Residency.MEMORY);
            Expr ld = new LoadDeclValueExpr(sv);
            exp = new ExprTuple(ld, null, null);
            return;
        }

        // Some of the initializers are expressions that must be evaluated
        // at run time.

        ExprTuple sr = new ExprTuple(null, null);
        VariableDecl sv = genTemp(type);
        addDeclaration(sv);

        ArrayType art = type.getCoreType().returnArrayType();
        if (art != null) {
            Type pet = PointerType.create(art.getElementType());
            Literal lit0 = LiteralMap.put(0, longType);
            long index = 0;

            for (int i = 0; i < l; i++) {
                Object x = ea.elementAt(i);
                Expression expr = null;
                if (x instanceof PositionIndexOp) {
                    index = ((PositionIndexOp) x).getIndex();
                    i++;
                    expr = (Literal) ea.elementAt(i);
                }
                else if (x instanceof Expression) {
                    expr = (Expression) x;
                }
                else {
                    throw new scale.common.InternalError("What's this " + x);
                }

                Expr ldae = new LoadDeclAddressExpr(sv);
                Expr op = new ArrayIndexExpr(pet,
                        ldae,
                        new LiteralExpr(lit0),
                        new LiteralExpr(LiteralMap.put(index, longType)));
                index++;
                expr.visit(this);
                sr.concat(exp);

                makeAssignChord(op, exp.getRef());
                sr.concat(exp);
            }
        }
        else {
            AggregateType agt = (AggregateType) type.getCoreType();
            int fi = 0;
            Vector<FieldDecl> fields = agt.getAgFields();

            for (int i = 0; i < l; i++) {
                Object x = ea.elementAt(i);

                if (x instanceof PositionFieldOp) {
                    FieldDecl fd = ((PositionFieldOp) x).getField();
                    fi = fields.indexOf(fd);
                    assert (fi >= 0) : "Field not found " + fd;
                    continue;
                }

                if (x instanceof PositionOffsetOp) {
                    long pos = ((PositionOffsetOp) x).getOffset();
                    FieldDecl fd = agt.getFieldFromOffset(pos);
                    fi = fields.indexOf(fd);
                    assert (fi >= 0) : "Field not found " + fd;
                    continue;
                }

                int reps = 1;
                if (x instanceof PositionRepeatOp) {
                    reps = ((PositionRepeatOp) x).getCount();
                    i++;
                    x = ea.elementAt(i);
                }

                assert (x instanceof Expression) : "What's this " + x;

                Expression expr = (Expression) x;
                for (int r = 0; r < reps; r++) {
                    FieldDecl fd = fields.get(fi++);
                    Expr ldae = new LoadDeclAddressExpr(sv);
                    Expr op = new LoadFieldAddressExpr(ldae, fd);

                    expr.visit(this);
                    sr.concat(exp);

                    makeAssignChord(op, exp.getRef());
                    sr.concat(exp);
                }
            }
        }

        sr.setRef(new LoadDeclValueExpr(sv));
        exp = sr;
    }

    public void visitBooleanLiteral(BooleanLiteral ce)
    {
        visitLiteral(ce);
    }

    public void visitCharLiteral(CharLiteral ce)
    {
        visitLiteral(ce);
    }

    public void visitVaArgOp(VaArgOp va)
    {
        va.getVaList().visit(this);
        Expr se = new VaArgExpr(getNonConstType(va), exp.getRef());
        exp.setRef(se);
    }

    public void visitVaCopyOp(VaCopyOp p)
    {
        convertToTemp(p.getRhs());
        ExprTuple sr = exp;
        Expr rhs = exp.getRef();

        p.getLhs().visit(this);
        Expr lhs = exp.getRef();
        sr.concat(exp);

        ExprChord ta = new ExprChord(lhs, rhs);
        ta.setVaCopy();

        recordNewChord(ta);
        sr.append(ta);
        sr.setRef(rhs);
        exp = sr;
    }

    public void visitVaEndOp(VaEndOp va)
    {
        va.getVaList().visit(this);

        ExprTuple sr = exp;
        Expr se = new VaEndExpr(exp.getRef());
        ExprChord ec = new ExprChord(se);

        recordNewChord(ec);

        sr.append(ec);
        sr.setRef(se);
        exp = sr;
    }

    public void visitVaStartOp(VaStartOp va)
    {
        rd.setUsesVaStart();

        va.getVaList().visit(this);

        ExprTuple sr = exp;
        Expr se = new VaStartExpr(exp.getRef(), va.getParmN());

        ExprChord ec = new ExprChord(se);
        recordNewChord(ec);

        sr.append(ec);
        sr.setRef(se);
        exp = sr;
    }

    public void visitIdAddressOp(IdAddressOp ce)
    {
        Declaration d = ce.getDecl();

        if (d instanceof EnumElementDecl) {
            EnumElementDecl ed = (EnumElementDecl) d;
            ed.getValue().visit(this);
            return;
        }

        Expr se = new LoadDeclAddressExpr(d);
        if (doByRef && (d.getMode() == ParameterMode.REFERENCE)) {
            byRefTable.add((VariableDecl) d, se);
        }

        exp = new ExprTuple(se, null, null);
    }

    public void visitIdValueOp(IdValueOp ce)
    {
        Declaration d = ce.getDecl();

        if (d instanceof EnumElementDecl) {
            EnumElementDecl ed = (EnumElementDecl) d;
            ed.getValue().visit(this);
            return;
        }

        Expr se = new LoadDeclValueExpr(d);
        if (doByRef && (d.getMode() == ParameterMode.REFERENCE)) {
            byRefTable.add((VariableDecl) d, se);
        }

        exp = new ExprTuple(se, null, null);
    }

    public void visitStatementOp(StatementOp so)
    {
        Statement s = so.getStatement();
        ExprTuple sr = null;

        if (s != null) {
            s.visit(this);
            sr = exp;
        }
        else {
            sr = new ExprTuple(null, null);
        }

        so.getExpr().visit(this);
        sr.concat(exp);
        sr.setRef(exp.getRef());
        exp = sr;
    }

    public void visitSeriesOp(SeriesOp op)
    {
        doSeriesOp(op, true);
    }

    private void doSeriesOp(SeriesOp op, boolean makeTemp)
    {
        visitExpr(op.getExpr1());
        ExprTuple sr = exp;

        if (makeTemp) {
            op.getExpr2().visit(this);
            sr.concat(exp);
            sr.setRef(exp.getRef());
            exp = sr;
            return;
        }

        ExprTuple exp2 = visitExpr(op.getExpr2());
        sr.concat(exp2);
        sr.setRef(exp2.getRef());
        exp = sr;
    }

    public void visitParenthesesOp(ParenthesesOp p)
    {
        p.getExpr().visit(this);
    }

    public void visitAssignSimpleOp(AssignSimpleOp p)
    {
        doAssignSimpleOp(p, true);
    }

    private void doAssignSimpleOp(AssignSimpleOp p, boolean makeTemp)
    {
        Expression rval = p.getRhs();

        if (rval instanceof CallFunctionOp) {
            // Avoid creating a temporary variable for the function result.
            // Throw away the unneeded ExprChord and just use the function
            // call.
            doCallFunctionOp((CallFunctionOp) rval, false);
        }
        else {
            rval.visit(this);
        }
        ExprTuple sr = exp;
        Expr rhs = exp.getRef();

        p.getLhs().visit(this);
        Expr lhs = exp.getRef();
        sr.concat(exp);

        Type latype = lhs.getCoreType().getPointedTo();
        Expr val = addConversion(rhs, latype, latype);

        if (makeTemp) {
            // Store the right hand side in a temporary so that the store can not
            // affect the value of the expression.  For example, consider
            //    if (a = a->next) {}
            // Also, in many cases we do not want to evaluate the right hand side
            // more than once as in
            //    a + (b = c(x));
            // Of course, people, who write code like the above two examples,
            // should be lined up and shot.

            val = makeTemp(val);
            sr.concat(exp);
        }

        makeAssignChord(lhs, val);
        sr.concat(exp);
        sr.setRef(val);
        exp = sr;
    }

    public void visitDefOp(DefOp p)
    {
        Expression exp = p.getExpr();
        Declaration decl = p.getDecl();

        addDeclaration(decl);

        exp.visit(this);
    }

    public void visitPositiveOp(PositiveOp p)
    {
        p.getExpr().visit(this);
    }

    public void visitNegativeOp(NegativeOp no)
    {
        no.getExpr().visit(this);

        ExprTuple sr = exp;
        Expr se = NegativeExpr.create(getNonConstType(no), exp.getRef());

        sr.setRef(se);
        exp = sr;
    }

    public void visitTranscendentalOp(TranscendentalOp no)
    {
        TransFtn ftn = no.getFtn();

        no.getExpr().visit(this);

        ExprTuple sr = exp;
        Expr se = new TranscendentalExpr(getNonConstType(no), exp.getRef(), ftn);

        sr.setRef(se);
        exp = sr;

        if (ftn == TransFtn.Alloca) {
            rd.setUsesAlloca();
        }
    }

    public void visitTranscendental2Op(Transcendental2Op no)
    {
        no.getExpr1().visit(this);
        Expr la = exp.getRef();
        ExprTuple sr = exp;

        no.getExpr2().visit(this);
        Expr ra = exp.getRef();
        sr.concat(exp);

        Expr se = new Transcendental2Expr(getNonConstType(no), la, ra, no.getFtn());

        sr.setRef(se);
        exp = sr;
    }

    public void visitAbsoluteValueOp(AbsoluteValueOp no)
    {
        no.getExpr().visit(this);

        ExprTuple sr = exp;
        Expr se = new AbsoluteValueExpr(getNonConstType(no), exp.getRef());

        sr.setRef(se);
        exp = sr;
    }

    private void doMinMaxOp(Expr check, Expr v1, Expr v2, Expr high)
    {
        ExprTuple sr = exp;

        // Allocate the if statement node.
        IfThenElseChord ifChord = new IfThenElseChord(check);
        recordNewChord(ifChord);
        sr.append(ifChord);

        Chord bottom = new NullChord();
        recordNewChord(bottom);

        // build the true clause
        makeTempAssignChord(v1.copy());
        Expr t = exp.getRef(); // result value

        ifChord.setTrueEdge(exp.getBegin());
        exp.getEnd().linkTo(bottom);

        // build the false clause
        ExprTuple srf = new ExprTuple(null, null);
        makeAssignChordT(t, v2.copy(), srf);

        ifChord.setFalseEdge(srf.getBegin());
        srf.getEnd().linkTo(bottom);

        DualExpr dual = new DualExpr(high, t);
        exp = new ExprTuple(dual, sr.getBegin(), bottom);
    }

    /**
     * Converts an expression of the form
     * <code>min(a,b)</code>
     * to the series of CFG nodes.
     * Converted to
     * <pre>
     *   check = a < b
     *   if (check) then t = a else t = b
     *   t
     * </pre>
     * where <code>t</code> is the result value.
     */
    public void visitMinimumOp(MinimumOp deo)
    {
        Expression lexp = deo.getExpr1();
        Expression rexp = deo.getExpr2();

        if (lexp.isSimpleOp() && rexp.isSimpleOp()) {

            // Maybe the target architecture has a conditional move instruction.

            convertToTemp(lexp);

            ExprTuple sr = exp;
            Expr la = exp.getRef();

            convertToTemp(rexp);

            Expr ra = exp.getRef();
            sr.concat(exp);

            Expr ca = new LessExpr(boolType, la.copy(), ra.copy());
            Expr low = new ConditionalExpr(getNonConstType(deo), ca, la, ra);
            Expr high = new MinExpr(getNonConstType(deo), la.copy(), ra.copy());
            DualExpr dual = new DualExpr(high, low);
            sr.setRef(dual);
            exp = sr;
            return;
        }

        convertToTemp(lexp);

        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        convertToTemp(rexp);

        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new LessExpr(boolType, v1, v2);

        sr.setRef(se);
        exp = sr;
        doMinMaxOp(se, v1, v2, new MinExpr(getNonConstType(deo), v1.copy(), v2.copy()));
    }

    /**
     * Converts an expression of the form
     * <code>max(a,b)</code>
     * to the series of CFG nodes.
     * Converted to
     * <pre>
     *   check = a > b
     *   if (check) then t = a else t = b
     *   t
     * </pre>
     * where <code>t</code> is the result value.
     */
    public void visitMaximumOp(MaximumOp deo)
    {
        Expression lexp = deo.getExpr1();
        Expression rexp = deo.getExpr2();

        if (lexp.isSimpleOp() && rexp.isSimpleOp()) {

            // Maybe the target architecture has a conditional move instruction.

            convertToTemp(lexp);

            ExprTuple sr = exp;
            Expr la = exp.getRef();

            convertToTemp(rexp);

            Expr ra = exp.getRef();
            sr.concat(exp);

            Expr ca = new GreaterExpr(boolType, la.copy(), ra.copy());
            Expr low = new ConditionalExpr(getNonConstType(deo), ca, la, ra);
            Expr high = new MaxExpr(getNonConstType(deo), la.copy(), ra.copy());
            DualExpr dual = new DualExpr(high, low);
            sr.setRef(dual);
            exp = sr;
            return;
        }

        convertToTemp(lexp);

        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        convertToTemp(rexp);

        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new GreaterExpr(boolType, v1, v2);

        sr.setRef(se);
        exp = sr;
        doMinMaxOp(se, v1, v2, new MaxExpr(getNonConstType(deo), v1.copy(), v2.copy()));
    }

    /**
     * Generate a sizeof() value.  We could actually generate a
     * SizeofLiteral here but it would have to be of type
     * <code>size_t</code>.  Then we would have to convert it to the
     * type actually required which is usually signed.  This only really
     * matters when we are generating C code; if we generate a sizeof()
     * and give it a signed type, the native C compiler will still treat
     * it as unsigned.  So, we just generate a machine dependent value
     * here.  Afterall, it's not as if there weren't any other machine
     * dependencies by this point.  This routine can always be changed
     * to create a ConversionExpr of a LiteralExpr of a SizeofLiteral
     * if machine independency is desired.
     *
     * @param ty - is the type for <code>sizeof(ty)</code>
     */
    private LiteralExpr genSizeofValue(Type ty)
    {
        return new LiteralExpr(LiteralMap.put(ty.memorySize(Machine.currentMachine), ptrdiff_t_type));
    }

    public void visitAdditionOp(AdditionOp deo)
    {
        Type dt = deo.getCoreType();
        Expression la = deo.getExpr1();
        Expression ra = deo.getExpr2();
        Type vt = getNonConstType(deo);
        Expr se = null;

        la.visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        ra.visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        if (dt.isPointerType()) {
            Type pt = vt.getCoreType().getPointedTo();
            LiteralExpr sl = genSizeofValue(pt);
            if (la.getCoreType().isPointerType()) {
                if (v2.getCoreType() != ptrdiff_t_type) {
                    v2 = ConversionExpr.create(ptrdiff_t_type, v2, CastMode.TRUNCATE);
                }
                if (!sl.isOne()) {
                    v2 = MultiplicationExpr.create(ptrdiff_t_type, v2, sl);
                }
                se = AdditionExpr.create(vt, v1, v2);
            }
            else {
                if (v1.getCoreType() != ptrdiff_t_type) {
                    v1 = ConversionExpr.create(ptrdiff_t_type, v1, CastMode.TRUNCATE);
                }
                if (!sl.isOne()) {
                    v1 = MultiplicationExpr.create(ptrdiff_t_type, v1, sl);
                }
                se = AdditionExpr.create(vt, v2, v1);
            }
        }
        else {
            se = AdditionExpr.create(vt, v1, v2);
        }

        sr.setRef(se);
        exp = sr;
    }

    public void visitSubtractionOp(SubtractionOp deo)
    {
        Expression la = deo.getExpr1();
        Expression ra = deo.getExpr2();
        Type rt = ra.getCoreType();
        Type vt = getNonConstType(deo);
        Expr se = null;

        la.visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        ra.visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Type lat = la.getCoreType();
        Type rat = ra.getCoreType();

        if (lat.isPointerType()) {
            Type pt = lat.getCoreType().getPointedTo();
            LiteralExpr sl = genSizeofValue(pt);
            if (rat.isPointerType()) {
                Type dt = deo.getCoreType();
                se = SubtractionExpr.create(dt, v1, v2);
                // The type of sizeof(x) is unsigned.
                if (!sl.isOne()) {
                    se = new DivisionExpr(dt, se, sl);
                }
            }
            else {
                if (v2.getCoreType() != ptrdiff_t_type) {
                    v2 = ConversionExpr.create(ptrdiff_t_type, v2, CastMode.TRUNCATE);
                }
                Expr mult = MultiplicationExpr.create(ptrdiff_t_type, v2, sl);
                se = SubtractionExpr.create(vt, v1, mult);
            }
        }
        else {
            se = SubtractionExpr.create(vt, v1, v2);
        }

        sr.setRef(se);
        exp = sr;
    }

    public void visitMultiplicationOp(MultiplicationOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = MultiplicationExpr.create(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitDivisionOp(DivisionOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = DivisionExpr.create(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitRemainderOp(RemainderOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new RemainderExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitExponentiationOp(ExponentiationOp deo)
    {
        Expression e1 = deo.getExpr1();
        Expression e2 = deo.getExpr2();
        Literal value = e2.getConstantValue();

        if (value != null) {
            try {
                double v = Lattice.convertToDoubleValue(value);
                if (v == 2.0) {
                    convertToTemp(e1);
                    Expr v1 = exp.getRef();
                    Expr se = MultiplicationExpr.create(getNonConstType(deo), v1, v1.copy());
                    exp.setRef(se);
                    return;
                }
            }
            catch (InvalidException ex) {
            }
        }

        e1.visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        e2.visit(this);
        sr.concat(exp);
        Expr se = new ExponentiationExpr(getNonConstType(deo), v1, exp.getRef());

        sr.setRef(se);
        exp = sr;
    }

    private LiteralExpr getIncrement(Type t)
    {
        return new LiteralExpr(t.isRealType() ?
                (Literal) LiteralMap.put(1.0, t) :
                (Literal) LiteralMap.put(1, t));
    }

    public void visitPreDecrementOp(PreDecrementOp io)
    {
        Type iot = io.getCoreType();
        Expression arg = io.getExpr();
        Type vt = getNonConstType(io);

        arg.visit(this); // Convert the expression being decremented.

        ExprTuple sr = exp;
        Expr var = exp.getRef();
        Expr res = makeRValue(var);
        Expr ret = res.copy();

        if (iot.isPointerType()) {
            Type pt = iot.getPointedTo();
            Expr dec = genSizeofValue(pt);
            Expr sub = SubtractionExpr.create(vt, res, dec);
            makeAssignChord(var.copy(), sub);
        }
        else {
            makeAssignChord(var.copy(), SubtractionExpr.create(vt, res, getIncrement(iot)));
        }

        sr.concat(exp);

        exp = new ExprTuple(ret, sr.getBegin(), sr.getEnd());
    }

    public void visitPreIncrementOp(PreIncrementOp io)
    {
        Type iot = io.getCoreType();
        Expression arg = io.getExpr();
        Type vt = getNonConstType(io);

        arg.visit(this); // Convert the expression being incremented.

        ExprTuple sr = exp;
        Expr var = exp.getRef();
        Expr res = makeRValue(var);
        Expr ret = res.copy();

        if (iot.isPointerType()) {
            Type pt = iot.getPointedTo();
            Expr inc = genSizeofValue(pt);
            makeAssignChord(var.copy(), AdditionExpr.create(vt, res, inc));
        }
        else {
            makeAssignChord(var.copy(), AdditionExpr.create(vt, res, getIncrement(iot)));
        }

        sr.concat(exp);

        exp = new ExprTuple(ret, sr.getBegin(), sr.getEnd());
    }

    public void visitPostDecrementOp(PostDecrementOp io)
    {
        doPostOp(SUBT, io, true);
    }

    public void visitPostIncrementOp(PostIncrementOp io)
    {
        doPostOp(ADD, io, true);
    }

    private void doPostOp(int op, IncrementOp io, boolean makeTemp)
    {
        Type iot = io.getCoreType();
        Expression arg = io.getExpr();
        Type vt = getNonConstType(io);

        // Convert the expression being incremented.

        arg.visit(this);

        ExprTuple sr = exp;
        Expr var = exp.getRef();
        Expr res = makeRValue(var);

        if (makeTemp) {
            // Copy variable to temporary (so we can return temporary).

            makeTempAssignChord(res);
            sr.concat(exp);
            res = exp.getRef(); // temporary reference
        }

        Expr inc = (iot.isPointerType() ?
                genSizeofValue(iot.getPointedTo()) :
                getIncrement(iot));
        Expr add = ((op == ADD) ?
                AdditionExpr.create(vt, res, inc) :
                SubtractionExpr.create(vt, res, inc));

        makeAssignChord(var.conditionalCopy(), add);

        sr.concat(exp);

        exp = new ExprTuple(res, sr.getBegin(), sr.getEnd());
    }

    public void visitEqualityOp(EqualityOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new EqualityExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitGreaterEqualOp(GreaterEqualOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new GreaterEqualExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitGreaterOp(GreaterOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new GreaterExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitLessOp(LessOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new LessExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitLessEqualOp(LessEqualOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new LessEqualExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitNotEqualOp(NotEqualOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new NotEqualExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitBitComplementOp(BitComplementOp deo)
    {
        deo.getExpr().visit(this);
        ExprTuple sr = exp;
        Expr se = new BitComplementExpr(getNonConstType(deo), exp.getRef());
        sr.setRef(se);
        exp = sr;
    }

    public void visitBitAndOp(BitAndOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new BitAndExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitBitXorOp(BitXorOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new BitXorExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitBitOrOp(BitOrOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new BitOrExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitBitShiftOp(BitShiftOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        BitShiftExpr se = new BitShiftExpr(getNonConstType(deo), v1, v2, deo.getShiftMode());

        sr.setRef(se);
        exp = sr;
    }

    private Expr addConversion(Expr arg, Type checkType, Type castType)
    {
        Type t = arg.getCoreType();

        if (t == checkType) {
            return arg;
        }

        if (arg.getType().isArrayType()) {
            return arg;
        }

        CastMode cr = CastMode.CAST;

        if (checkType.isIntegerType()) {
            cr = CastMode.TRUNCATE;
        }
        else if (checkType.isRealType()) {
            cr = CastMode.REAL;
        }

        return ConversionExpr.create(castType, arg, cr);
    }

    private void doCompoundAssignment(int which, CompoundAssignmentOp deo, boolean makeTemp)
    {
        Expression lhs = deo.getLhs();
        Expression rap = deo.getRhs();
        Type ct = deo.getCalcType(); // Computation type for the right-hand-side.
        Type vt = ct.getNonConstType();
        Type dt = ct.getCoreType();
        Type st = getNonConstType(deo); // Type of the expression.

        lhs.visit(this);

        ExprTuple sr = exp;
        Expr slhs = exp.getRef();

        rap.visit(this);

        Expr sra = exp.getRef();
        sr.concat(exp);

        Expr sv = makeRValue(slhs);
        Expr la = addConversion(sv, dt, vt);
        Expr ra = addConversion(sra, dt, vt);

        Expr rhs;
        switch (which) {
            case MULT:
                rhs = MultiplicationExpr.create(vt, la, ra);
                break;
            case DIV:
                rhs = new DivisionExpr(vt, la, ra);
                break;
            case REM:
                rhs = new RemainderExpr(vt, la, ra);
                break;
            case AND:
                rhs = new BitAndExpr(vt, la, ra);
                break;
            case OR:
                rhs = new BitOrExpr(vt, la, ra);
                break;
            case XOR:
                rhs = new BitXorExpr(vt, la, ra);
                break;
            default:
                throw new scale.common.InternalError("op " + which);
        }

        Expr se = addConversion(rhs, st.getCoreType(), st);
        if (makeTemp) {
            se = makeTemp(se);
            sr.concat(exp);
        }

        makeAssignChord(slhs.copy(), se);
        sr.concat(exp);
        sr.setRef(se);
        exp = sr;
    }

    public void visitMultiplicationAssignmentOp(MultiplicationAssignmentOp deo)
    {
        doCompoundAssignment(MULT, deo, true);
    }

    public void visitDivisionAssignmentOp(DivisionAssignmentOp deo)
    {
        doCompoundAssignment(DIV, deo, true);
    }

    public void visitRemainderAssignmentOp(RemainderAssignmentOp deo)
    {
        doCompoundAssignment(REM, deo, true);
    }

    public void visitAdditionAssignmentOp(AdditionAssignmentOp deo)
    {
        doAdditionAssignmentOp(deo, true);
    }

    private void doAdditionAssignmentOp(AdditionAssignmentOp deo, boolean makeTemp)
    {
        Expression lhs = deo.getLhs();
        Expression rap = deo.getRhs();
        Type ct = deo.getCalcType(); // Computation type for the right-hand-side.
        Type vt = ct.getNonConstType();
        Type dt = ct.getCoreType();
        Type st = getNonConstType(deo); // Type of the expression.

        lhs.visit(this);

        ExprTuple sr = exp;
        Expr slhs = exp.getRef();
        Expr sv = makeRValue(slhs);

        rap.visit(this);

        Expr sra = exp.getRef();
        sr.concat(exp);

        Expr rhs;
        if (st.isPointerType()) {
            Type pt = st.getCoreType().getPointedTo();
            Expr sl = genSizeofValue(pt);
            Expr mult = MultiplicationExpr.create(ptrdiff_t_type, sra, sl);
            rhs = AdditionExpr.create(vt, sv, mult);
        }
        else {
            Expr la = addConversion(sv, dt, vt);
            Expr ra = addConversion(sra, dt, vt);
            rhs = AdditionExpr.create(vt, la, ra);
        }

        Expr se = addConversion(rhs, st.getCoreType(), st);
        if (makeTemp) {
            se = makeTemp(se);
            sr.concat(exp);
        }

        makeAssignChord(slhs, se);
        sr.concat(exp);
        sr.setRef(se);
        exp = sr;
    }

    public void visitSubtractionAssignmentOp(SubtractionAssignmentOp deo)
    {
        doSubtractionAssignmentOp(deo, true);
    }

    private void doSubtractionAssignmentOp(SubtractionAssignmentOp deo, boolean makeTemp)
    {
        Expression lhs = deo.getLhs();
        Expression rap = deo.getRhs();
        Type ct = deo.getCalcType(); // Computation type for the right-hand-side.
        Type vt = ct.getNonConstType();
        Type dt = ct.getCoreType();
        Type st = getNonConstType(deo); // Type of the expression.

        lhs.visit(this);

        ExprTuple sr = exp;
        Expr slhs = exp.getRef();
        Expr sv = makeRValue(slhs);

        rap.visit(this);

        Expr sra = exp.getRef();
        sr.concat(exp);

        Expr rhs;
        if (st.isPointerType()) {
            Type pt = st.getCoreType().getPointedTo();
            Expr sl = genSizeofValue(pt);
            Expr mult = MultiplicationExpr.create(ptrdiff_t_type, sra, sl);
            rhs = SubtractionExpr.create(vt, sv, mult);
        }
        else {
            Expr la = addConversion(sv, dt, vt);
            Expr ra = addConversion(sra, dt, vt);
            rhs = SubtractionExpr.create(vt, la, ra);
        }

        Expr se = addConversion(rhs, st.getCoreType(), st);

        if (makeTemp) {
            se = makeTemp(se);
            sr.concat(exp);
        }

        makeAssignChord(slhs, se);
        sr.concat(exp);
        sr.setRef(se);
        exp = sr;
    }

    public void visitBitShiftAssignmentOp(BitShiftAssignmentOp deo)
    {
        doBitShiftAssignmentOp(deo, true);
    }

    private void doBitShiftAssignmentOp(BitShiftAssignmentOp deo, boolean makeTemp)
    {
        Expression lhs = deo.getLhs();
        Expression rap = deo.getRhs();
        Type ct = deo.getCalcType(); // Computation type for the right-hand-side.
        Type vt = ct.getNonConstType();
        Type dt = ct.getCoreType();
        Type st = getNonConstType(deo); // Type of the expression.

        lhs.visit(this);

        ExprTuple sr = exp;
        Expr slhs = exp.getRef();

        rap.visit(this);

        Expr sra = exp.getRef();
        sr.concat(exp);

        Expr sv = makeRValue(slhs);
        Expr v = new BitShiftExpr(vt, sv, sra, deo.getShiftMode());
        Expr se = addConversion(v, st.getCoreType(), st);

        if (makeTemp) {
            se = makeTemp(se);
            sr.concat(exp);
        }

        makeAssignChord(slhs.copy(), se);
        sr.concat(exp);
        sr.setRef(se);
        exp = sr;
    }

    public void visitBitAndAssignmentOp(BitAndAssignmentOp deo)
    {
        doCompoundAssignment(AND, deo, true);
    }

    public void visitBitXorAssignmentOp(BitXorAssignmentOp deo)
    {
        doCompoundAssignment(XOR, deo, true);
    }

    public void visitBitOrAssignmentOp(BitOrAssignmentOp deo)
    {
        doCompoundAssignment(OR, deo, true);
    }

    public void visitNotOp(NotOp deo)
    {
        deo.getExpr().visit(this);
        ExprTuple sr = exp;
        Expr se = new NotExpr(getNonConstType(deo), exp.getRef());
        sr.setRef(se);
        exp = sr;
    }

    public void visitAndOp(AndOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new AndExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    public void visitOrOp(OrOp deo)
    {
        deo.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        deo.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        Expr se = new OrExpr(getNonConstType(deo), v1, v2);

        sr.setRef(se);
        exp = sr;
    }

    /**
     * Converts an expression of the form
     * <code>(a &amp;&amp; b)</code>
     * to the series of CFG nodes.
     * Converted to
     * <pre>
     *   t = 0
     *   if (a) then if (b) then t = 1
     *   t
     * </pre>
     * where <code>t</code> is the result value.
     */
    public void visitAndConditionalOp(AndConditionalOp cs)
    {
        NullChord exit = new NullChord();

        recordNewChord(exit);

        Type type = cs.getCoreType();
        IntLiteral step0 = LiteralMap.put(0, type);
        IntLiteral step1 = LiteralMap.put(1, type);
        LoadDeclAddressExpr ltemp = makeTempAssignChord(new LiteralExpr(step0));
        ExprTuple srf = exp;
        Declaration decl = ltemp.getDecl();

        makeTempAssignChord(new LiteralExpr(step1), decl);
        ExprTuple srt = exp;
        srt.getEnd().linkTo(exit);

        genConditionalBranch(cs, srt.getBegin(), exit);
        srf.concat(exp);
        exp = new ExprTuple(new LoadDeclValueExpr(decl), srf.getBegin(), exit);
    }

    /**
     * Converts an expression of the form
     * <code>(a || b)</code>
     * to the series of CFG nodes.
     * Converted to
     * <pre>
     *   t = 0
     *   if (a) then t = 1
     *   else if (b) then t = 1
     *   t
     * </pre>
     * where <code>t</code> is the result value.
     */
    public void visitOrConditionalOp(OrConditionalOp cs)
    {
        NullChord exit = new NullChord();

        recordNewChord(exit);

        Type type = cs.getCoreType();
        IntLiteral step0 = LiteralMap.put(0, type);
        IntLiteral step1 = LiteralMap.put(1, type);
        LoadDeclAddressExpr ltemp = makeTempAssignChord(new LiteralExpr(step0));
        ExprTuple srf = exp;
        Declaration decl = ltemp.getDecl();

        makeTempAssignChord(new LiteralExpr(step1), decl);
        ExprTuple srt = exp;
        srt.getEnd().linkTo(exit);

        genConditionalBranch(cs, srt.getBegin(), exit);
        srf.concat(exp);
        exp = new ExprTuple(new LoadDeclValueExpr(decl), srf.getBegin(), exit);
    }

    private boolean isSimple(Expression exp)
    {
        if (exp instanceof Literal) {
            return true;
        }

        if (exp instanceof IdReferenceOp) {
            return true;
        }

        if (exp instanceof SubscriptAddressOp) {
            SubscriptAddressOp sao = (SubscriptAddressOp) exp;
            Expression array = sao.getArray();
            if (sao.numSubscripts() != 1) {
                return false;
            }
            if (!(sao.getSubscript(0) instanceof IntLiteral)) {
                return false;
            }
            return isSimple(array);
        }

        if (exp instanceof TypeConversionOp) {
            TypeConversionOp tco = (TypeConversionOp) exp;

            if (tco.getConversion() == CastMode.CAST) {
                return isSimple(tco.getExpr());
            }
        }

        return false;
    }

    public void visitExpressionIfOp(ExpressionIfOp cs)
    {
        Expression cexp = cs.getExpr1();
        Expression texp = cs.getExpr2();
        Expression fexp = cs.getExpr3();
        Type ct = cs.getCoreType();

        if (ct.isAtomicType() && isSimple(texp) && isSimple(fexp)) {
            if (ct.isIntegerType() && (texp instanceof Literal) && (fexp instanceof Literal)) {
                Literal tl = ((Literal) texp).getConstantValue();
                Literal fl = ((Literal) fexp).getConstantValue();
                if (tl.isOne() && fl.isZero()) {
                    // Special case for
                    //    predicate ? 1 : 0
                    cexp.visit(this);
                    return;
                }
            }

            // Maybe the target architecture has a conditional move instruction.

            cexp.visit(this);
            Expr ca = exp.getRef();
            ExprTuple sr = exp;

            texp.visit(this);
            Expr ta = exp.getRef();
            sr.concat(exp);

            fexp.visit(this);
            Expr fa = exp.getRef();
            sr.concat(exp);

            Expr se = new ConditionalExpr(getNonConstType(cs), ca, ta, fa);

            sr.setRef(se);
            exp = sr;
            return;
        }

        // Do it the hard way - with branches.

        NullChord bottom = new NullChord();

        recordNewChord(bottom);

        // Build the true clause.

        texp.visit(this);

        ExprTuple srt = exp;
        Expr t2 = exp.getRef(); // result value
        if ((t2 != null) && (t2.getCoreType() != VoidType.type)) {
            makeTempAssignChord(t2, getTemp(t2));
            t2 = exp.getRef();
            srt.concat(exp);
        }

        Chord te = srt.getBegin();
        if (te == null) {
            te = bottom;
        }
        else {
            srt.getEnd().linkTo(bottom);
        }

        // Build the false clause.

        fexp.visit(this);

        ExprTuple srf = exp;
        Expr t3 = exp.getRef();
        if ((t3 != null) && (t3.getCoreType() != VoidType.type)) {
            makeAssignChordT(t2, t3, srf);
        }

        Chord fe = srf.getBegin();
        if (fe == null) {
            fe = bottom;
        }
        else {
            srf.getEnd().linkTo(bottom);
        }

        genConditionalBranch(cexp, te, fe);

        exp = new ExprTuple(t2, exp.getBegin(), bottom);
    }

    public void visitAddressOp(AddressOp aa)
    {
        Type at = aa.getType().getNonConstType();
        Expression ae = aa.getExpr();

        if (ae instanceof IdValueOp) {
            Declaration d = ((IdValueOp) ae).getDecl();

            EnumElementDecl ed = d.returnEnumElementDecl();
            if (ed != null) {
                ed.getValue().visit(this);
                return;
            }

            VariableDecl vd = d.returnVariableDecl();
            if (vd != null) {
                vd.setAddressTaken();
            }

            Expr se = new LoadDeclAddressExpr(d);
            if (doByRef && (d.getMode() == ParameterMode.REFERENCE)) {
                byRefTable.add((VariableDecl) d, se);
                se = LoadValueIndirectExpr.create(se);
            }

            exp = new ExprTuple(se, null, null);
            return;
        }

        if (ae instanceof DereferenceOp) { // &* - ignore
            DereferenceOp dr = (DereferenceOp) ae;
            ae = dr.getExpr();
            ae.visit(this);
            return;
        }

        if (ae instanceof SubscriptValueOp) {
            SubscriptOp sub = (SubscriptOp) ae;
            Expr load = doSubscript(sub);

            exp.setRef(load);
            return;
        }

        if (ae instanceof SelectOp) {
            SelectOp s = (SelectOp) ae;
            Expression structure = s.getStruct();
            FieldDecl fd = s.getField();

            structure.visit(this);
            Expr op = new LoadFieldAddressExpr(exp.getRef(), fd);
            exp.setRef(op);

            return;
        }

        // Normal case.

        ae.visit(this);

        ExprTuple sr = exp;
        Expr v = makeLValue(exp.getRef(), sr);

        // If a temporary must be created to hold the value so its address
        // can be computed, the temporary variable must be marked as
        // having its address taken.

        if (v instanceof LoadDeclAddressExpr) {
            ((LoadDeclAddressExpr) v).getDecl().setAddressTaken();
        }

        sr.setRef(v);
        exp = sr;
    }

    public void visitDereferenceOp(DereferenceOp dr)
    {
        Expression e1 = dr.getExpr();

        if (e1 instanceof AddressOp) { // *& - ignore
            AddressOp la = (AddressOp) e1;
            e1 = la.getExpr();
            e1.visit(this);
            return;
        }

        if (e1 instanceof IdAddressOp) {
            IdAddressOp ao = (IdAddressOp) e1;
            Declaration d = ao.getDecl();
            Expr se = new LoadDeclValueExpr(d);

            exp = new ExprTuple(se, null, null);
            return;
        }

        if (e1 instanceof SelectIndirectOp) {
            SelectIndirectOp si = (SelectIndirectOp) e1;
            FieldDecl fd = si.getField();
            e1 = si.getStruct();
            e1.visit(this);
            exp.setRef(new LoadFieldValueExpr(exp.getRef(), fd));
            return;
        }

        if (e1 instanceof AddressLiteral) {
            AddressLiteral al = (AddressLiteral) e1;
            Declaration ad = al.getDecl();
            if (ad != null) {
                long offset = al.getOffset();
                if ((offset == 0) && ad.getCoreType().isAtomicType()) {
                    exp = new ExprTuple(new LoadDeclValueExpr(ad), null, null);
                    return;
                }
                LoadDeclAddressExpr ldae = new LoadDeclAddressExpr(ad);
                Expr sl = new LiteralExpr(LiteralMap.put(offset, longType));
                Expr add = AdditionExpr.create(e1.getType(), ldae, sl);
                ad.setAddressTaken();
                exp = new ExprTuple(LoadValueIndirectExpr.create(add), null, null);
                return;
            }

            exp = new ExprTuple(new LiteralExpr(al.getValue()), null, null);
            return;
        }

        if (e1 instanceof TypeConversionOp) {
            TypeConversionOp tc = (TypeConversionOp) e1;
            Expression arg = tc.getExpr();
            if (tc.getConversion() == CastMode.CAST) {
                if (arg instanceof IdAddressOp) { // Special case for: *((int *) &v)
                    IdAddressOp iao = (IdAddressOp) arg;
                    Declaration decl = iao.getDecl();
                    if (tc.getPointedToCore().equivalent(decl.getPointedToCore())) {
                        exp = new ExprTuple(new LoadDeclValueExpr(decl), null, null);
                        return;
                    }
                    decl.setAddressTaken();
                }
                else if (arg instanceof SubscriptAddressOp) { // Special case for *((unsigned int *) &array[i])
                    SubscriptAddressOp sadd = (SubscriptAddressOp) arg;
                    Expression array = sadd.getArray();
                    Type atype = array.getCoreType().getPointedTo();

                    FixedArrayType at = atype.getCoreType().returnFixedArrayType();
                    if (at != null) {
                        int ll = at.getRank();
                        Vector<Bound> ind = new Vector<Bound>(ll);
                        for (int i = 0; i < ll; i++) {
                            ind.addElement(at.getIndex(i));
                        }

                        at = FixedArrayType.create(ind, dr.getType());
                        array = new TypeConversionOp(PointerType.create(at), array, CastMode.CAST);

                        int l = sadd.numSubscripts();
                        Vector<Expression> subs = new Vector<Expression>(l);
                        for (int i = 0; i < l; i++) {
                            subs.addElement(sadd.getSubscript(i));
                        }

                        SubscriptValueOp sub = new SubscriptValueOp(dr.getType(), array, subs);
                        sub.visit(this);
                        return;
                    }
                }
            }
        }

        e1.visit(this);

        Expr v = exp.getRef();
        assert dr.getCoreType().equivalent(v.getPointedToCore());
        Expr lvi = LoadValueIndirectExpr.create(v);
        exp.setRef(lvi);
    }

    public void visitNilOp(NilOp deo)
    {
        // Literal values do not require an assignment statement.
        exp = new ExprTuple(new NilExpr(getNonConstType(deo)), null, null);
    }

    public void visitSelectOp(SelectOp s)
    {
        Expression structure = s.getStruct();
        FieldDecl fd = s.getField();

        structure.visit(this);

        Expr op = new LoadFieldValueExpr(exp.getRef(), fd);

        exp.setRef(op);
    }

    public void visitSelectIndirectOp(SelectIndirectOp s)
    {
        Expression structure = s.getStruct();
        FieldDecl fd = s.getField();

        structure.visit(this);
        Expr op = new LoadFieldAddressExpr(exp.getRef(), fd);

        exp.setRef(op);
    }

    private DualExpr doSubscript(SubscriptOp subscript)
    {
        ExprTuple sr = new ExprTuple(null, null);
        int nsub = subscript.numSubscripts();
        Expr[] indicies = new Expr[nsub];

        for (int i = 0; i < nsub; i++) {
            Expression ind = subscript.getSubscript(i);
            ind.visit(this);
            sr.concat(exp);
            indicies[i] = exp.getRef();
        }

        Expression array = subscript.getArray();
        if ((nsub == 1) && (array instanceof AdditionOp)) {
            // Simplify the array address expression so that
            // scalar replacement works better.
            AdditionOp ao = (AdditionOp) array;
            Expression la = ao.getExpr1();
            Expression ra = ao.getExpr2();
            if (la.getCoreType().isIntegerType()) {
                Expression t = la;
                la = ra;
                ra = t;
            }
            if (ra.getCoreType().isIntegerType()) {
                Expression oa = array;
                array = la;
                ra.visit(this);
                sr.concat(exp);

                Expr rae = exp.getRef();
                Expr lae = indicies[0];

                indicies[0] = AdditionExpr.create(longType, lae, rae);
            }
        }

        array.visit(this);
        sr.concat(exp);

        IntLiteral step0 = LiteralMap.put(0, longType);
        IntLiteral step1 = LiteralMap.put(1, longType);
        Expr add = exp.getRef();
        PointerType pt = array.getCoreType().returnPointerType(); // It's important to use the Clef node here.
        Type addt = pt.getPointedTo().getCoreType();
        int rank = addt.getRank();
        ArrayType at = addt.returnArrayType();

        boolean fortranArray = subscript.isFortranArray();
        Bound[] dims = null;
        if (fortranArray) {
            if (rank != nsub) {
                throw new scale.common.InternalError("What subscripting is this? " +
                        subscript +
                        " " +
                        rank +
                        " " +
                        nsub +
                        " " +
                        currentSla);
            }
            dims = new Bound[nsub];
            for (int r = 0; r < rank; r++) {
                dims[r] = at.getIndex(r);
            }
        }
        else {
            if (rank >= nsub) {
                int x = rank + 1;
                Expr[] ni = new Expr[x];
                System.arraycopy(indicies, 0, ni, 0, nsub);
                indicies = ni;
                while (nsub < x) {
                    indicies[nsub++] = new LiteralExpr(step0);
                }
            }
            else if (rank != (nsub - 1)) {
                throw new scale.common.InternalError("What subscripting is this? " +
                        subscript +
                        " " +
                        rank +
                        " " +
                        nsub +
                        " " +
                        currentSla);
            }

            dims = new Bound[nsub];
            dims[0] = Bound.noBound;

            for (int r = 0; r < rank; r++) {
                dims[r + 1] = at.getIndex(r);
            }
        }

        Expr[] mins = new Expr[nsub];
        Expr[] sizes = new Expr[nsub];

        for (int k = 0; k < nsub; k++) {
            Bound bd = dims[k];
            if (bd == Bound.noBound) {
                mins[k] = new LiteralExpr(step0);
                sizes[k] = new LiteralExpr(step0);
                continue;
            }

            Expr mine = null;
            try {
                mine = new LiteralExpr(LiteralMap.put(bd.getConstMin(), longType));
            }
            catch (scale.common.InvalidException ex) {
                Expression min = bd.getMin();
                if (min == null) {
                    mine = new LiteralExpr(step0);
                }
                else {
                    min.visit(this);
                    sr.concat(exp);
                    mine = exp.getRef();
                }
            }

            mins[k] = mine;

            try {
                // The dimension is known at compile time.
                sizes[k] = new LiteralExpr(LiteralMap.put(bd.numberOfElements(), longType));
            }
            catch (scale.common.InvalidException ex1) { // The dimension must be calculated at run-time.
                Expr maxe = null;
                try {
                    maxe = new LiteralExpr(LiteralMap.put(bd.getConstMax(), longType));
                }
                catch (scale.common.InvalidException ex2) {
                    Expression max = bd.getMax();
                    if (max == null) {
                        maxe = new LiteralExpr(step0);
                    }
                    else {
                        max.visit(this);
                        sr.concat(exp);
                        maxe = exp.getRef().conditionalCopy();
                    }
                }

                sizes[k] = AdditionExpr.create(longType,
                        SubtractionExpr.create(longType, maxe, mine.copy()),
                        new LiteralExpr(step1));
            }
        }

        // The result is an address expression.

        Type pet = subscript.getType();
        if (subscript instanceof SubscriptValueOp) {
            pet = PointerType.create(pet);
        }

        SubscriptExpr high = new SubscriptExpr(pet, add, indicies, mins, sizes);
        Expr low = high.lower();

        exp = sr;

        return new DualExpr(high, low);
    }

    public void visitSubscriptValueOp(SubscriptValueOp sub)
    {
        Expr load = doSubscript(sub);
        ExprTuple sr = exp;
        Expr ref = LoadValueIndirectExpr.create(load);
        sr.setRef(ref);
        exp = sr;
    }

    public void visitSubscriptAddressOp(SubscriptAddressOp sub)
    {
        Expr load = doSubscript(sub);
        exp.setRef(load);
    }

    /**
     * Collect the arguments to the function.
     *
     * @param co is the CallOp
     * @param args is the vector to receive the converted arguments
     * @param routine is appended with the CFG nodes
     */
    private void processCallArgs(CallOp co, Vector<Expr> args, ExprTuple routine)
    {
        int l = co.getNumArgs(); // Process arguments to procedure call
        for (int i = 0; i < l; i++) {
            Expression arg = co.getArg(i);

            if (arg instanceof AddressOp) {
                AddressOp ao = (AddressOp) arg;
                Expression e = ao.getExpr();
                if (e instanceof IntLiteral) {
                    // Cut down on the number of variables defined for Fortran programs.
                    // In Fortran,
                    //    call sub(1)
                    // causes a temporary variable to be created to hold the value of 1 so that
                    // the address of the value can be passed into the subroutine.  This change
                    // reuses the temporary variable instead of creating a new one for each call.
                    // The temporary variable used is unique to the caller and to the argumment
                    // position in the call.
                    Literal lit = (Literal) e;
                    VariableDecl avd = addrLitMap.get(i);
                    if (avd == null) {
                        avd = genTemp(lit.getType());
                        addrLitMap.put(i, avd);
                        avd.setAddressTaken();
                    }
                    ExprChord ec = new ExprChord(new LoadDeclAddressExpr(avd), new LiteralExpr(lit));
                    recordNewChord(ec);
                    routine.append(ec);
                    args.addElement(new LoadDeclAddressExpr(avd)); // Add the argument.
                    continue;
                }
            }

            // Make sure that the address taken flag is set on variables whose address
            // is passed to the routine.

            Expression a = arg;
            if (a instanceof TypeConversionOp) {
                TypeConversionOp tcop = (TypeConversionOp) a;
                if (tcop.getConversion() == CastMode.CAST) {
                    a = tcop.getExpr();
                }
            }

            if (a instanceof IdAddressOp) {
                IdAddressOp idaop = (IdAddressOp) a;
                idaop.getDecl().setAddressTaken();
            }

            boolean simple = isSimple(arg);
            arg.visit(this); // Convert the argument.

            // Arguments that are array values are placed into a temporary.

            routine.concat(exp);
            Expr ae = exp.getRef();
            if (!simple && (ae.findSubscriptExpr() != null)) {
                ae = makeTemp(ae); // Avoid ambiquity in data dependence checking.
                routine.concat(exp);
            }

            args.addElement(ae); // Add the argument.
        }
    }

    /**
     * Table of names of built-in functions of one argument.
     */
    private static final String[] builtins1 = {"abs", "fabs", "imaxabs", "labs", "llabs"};

    /**
     * Convert function call to in-line expressions.
     *
     * @return true if the conversion was performed.
     */
    private boolean convertBuiltin(CallFunctionOp call)
    {
        int numArgs = call.getNumArgs();
        if (numArgs != 1) {
            return false;
        }

        Expression rt = call.getRoutine();

        if (!(rt instanceof IdAddressOp)) {
            return false;
        }

        IdAddressOp op = (IdAddressOp) rt;
        Declaration decl = op.getDecl();
        String name = decl.getName();
        Expression arg = call.getArg(0);
        Type argt = arg.getCoreType();

        int bi = 0;
        for (; bi < builtins1.length; bi++) {
            if (builtins1[bi].equals(name)) {
                break;
            }
        }

        boolean ok = true;
        switch (bi) {
            case 1:
                if (!call.getArg(0).getType().isRealType()) {
                    ok = false;
                }
                break;
            case 0:
            case 2:
            case 3:
            case 4:
                if (!call.getArg(0).getType().isIntegerType()) {
                    ok = false;
                }
                break;
            default:
                return false;
        }

        if (ok) {
            arg.visit(this);
            exp.setRef(new AbsoluteValueExpr(arg.getType(), exp.getRef()));
            return true;
        }

        return false;
    }

    public void visitCallFunctionOp(CallFunctionOp co)
    {
        // We know that the function call is part of an expression and
        // that its value will be used.  Create a temporary variable to
        // hold the result so that the function is not called multiple
        // times.
        doCallFunctionOp(co, true);
    }

    /**
     * Translate a function call.
     *
     * @param makeTemp true if the function result should be placed in a
     * temporary varible
     */
    private void doCallFunctionOp(CallFunctionOp co, boolean makeTemp)
    {
        if (!noBuiltins && convertBuiltin(co)) {
            return;
        }

        Expression rt = co.getRoutine();

        rt.visit(this);

        Expr ftn = exp.getRef();
        ExprTuple routine = exp;
        ProcedureType sig = co.getProcedureInfo();
        Vector<Expr> args = new Vector<Expr>(co.getNumArgs());

        processCallArgs(co, args, routine);

        Type type = co.getType();
        Expr se = new CallFunctionExpr(type.getNonConstType(), ftn, args);
        if (!makeTemp) {
            exp = new ExprTuple(se, routine.getBegin(), routine.getEnd());
        }
        else if (type.getCoreType().isVoidType()) {
            ExprChord s = new ExprChord(se);
            recordNewChord(s);
            routine.append(s);
            exp = new ExprTuple(routine.getBegin(), routine.getEnd());
        }
        else {
            // Place result in temporary variable so that the function,
            // which may have a side effect, does not get called extra
            // times.
            makeTempAssignChord(se);
            routine.concat(exp);
            exp = new ExprTuple(exp.getRef(), routine.getBegin(), routine.getEnd());
        }

        if (ftn instanceof LoadDeclAddressExpr) {
            Declaration decl = ((LoadDeclAddressExpr) ftn).getDecl();
            String name = decl.getName();
            if (name.equals("_scale_alloca") || name.equals("__builtin_alloca")) {
                rd.setUsesAlloca();
            }
            else if (name.equals("_scale_setjmp") ||
                    name.equals("__builtin_setjmp") ||
                    name.equals("setjmp")) {
                rd.setUsesSetjmp();
            }
        }
    }

    public void visitTypeConversionOp(TypeConversionOp op)
    {
        Expression arg = op.getExpr();
        Type ty = op.getType();
        Type t = ty.getNonConstType();
        CastMode cr = op.getConversion();

        if (arg instanceof TypeConversionOp) {
            TypeConversionOp opa = (TypeConversionOp) arg;
            CastMode cra = opa.getConversion();
            if ((cra == cr) &&
                    ((cra == CastMode.CAST) || (opa.getCoreType() == t.getCoreType()))) {
                arg = opa.getExpr(); // Remove level of cast.
            }
        }

        if ((cr == CastMode.TRUNCATE) &&
                (arg.getCoreType() == t) &&
                t.isIntegerType()) {
            arg.visit(this);
            return;
        }

        IntegerType tyi = ty.getCoreType().returnIntegerType();
        if ((tyi != null) &&
                (arg instanceof DereferenceOp) &&
                (cr == CastMode.TRUNCATE)) {
            DereferenceOp opa = (DereferenceOp) arg;
            IntegerType tyai = opa.getCoreType().returnIntegerType();
            if (tyai != null) {
                if (tyi.bitSize() == tyai.bitSize()) {
                    Expression addr = new TypeConversionOp(PointerType.create(ty),
                            opa.getExpr(),
                            CastMode.CAST);
                    arg = new DereferenceOp(addr);
                    arg.visit(this);
                    return;
                }
            }
        }

        // Casts are not lowered at this point because there is often a
        // simple machine instruction that will accomplish the task.
        // There is no need to create multiple different unary Expr
        // classes for this.

        arg.visit(this);

        Expr v = exp.getRef();
        Expr conv = ConversionExpr.create(ty, v, cr);

        exp.setRef(conv);
    }

    public void visitComplexOp(ComplexOp op)
    {
        op.getExpr1().visit(this);
        ExprTuple sr = exp;
        Expr v1 = exp.getRef();

        op.getExpr2().visit(this);
        Expr v2 = exp.getRef();
        sr.concat(exp);

        sr.setRef(new ComplexValueExpr(getNonConstType(op), v1, v2));
        exp = sr;
    }
}
