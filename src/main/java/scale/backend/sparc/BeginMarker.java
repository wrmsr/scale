package scale.backend.sparc;

import scale.backend.Assembler;
import scale.backend.Marker;
import scale.backend.RegisterAllocator;
import scale.clef.decl.RoutineDecl;
import scale.clef.decl.Visibility;
import scale.common.Emit;
import scale.common.Statistics;
import scale.score.Scribble;

import java.util.Enumeration;

/**
 * This class marks the first position in a routine.
 * <p/>
 * $Id: BeginMarker.java,v 1.22 2006-12-05 21:02:02 burrill Exp $
 * <p/>
 * Copyright 2008 by the
 * <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 * <p/>
 * An instance of this class is used to generate the assembly
 * directives for the entry point to a routine.
 */

public class BeginMarker
        extends Marker
{
    private static int createdCount = 0; /* A count of all the instances of this class that were created. */

    private static final String[] stats = {"created"};

    static {
        Statistics.register("scale.backend.sparc.BeginMarker", stats);
    }

    /**
     * Return the number of instances of this class created.
     */
    public static int created()
    {
        return createdCount;
    }

    private Scribble scribble; /* The associated routine. */

    public BeginMarker(Scribble scribble)
    {
        super();
        this.scribble = scribble;
        createdCount++;
    }

    /**
     * Specify the registers used by this instruction.
     *
     * @param rs is the register set in use
     * @param index is an index associated with the instruction
     * @param strength is the importance of the instruction
     * @see scale.backend.RegisterAllocator#useRegister(int, int, int)
     * @see scale.backend.RegisterAllocator#defRegister(int, int)
     */
    public void specifyRegisterUsage(RegisterAllocator rs, int index, int strength)
    {
        rs.useRegister(index, SparcRegisterSet.FP_REG, strength); // Make sure these two registers are always "live".
        rs.useRegister(index, SparcRegisterSet.SP_REG, strength);
    }

    /**
     * Insert the assembler representation of the instruction into the
     * output stream.
     */
    public void assembler(Assembler asm, Emit emit)
    {
        RoutineDecl rd = scribble.getRoutineDecl();
        int sla = rd.getSourceLineNumber();
        String name = rd.getName();

        emit.endLine();

        Enumeration<String> ew = scribble.getWarnings();
        while (ew.hasMoreElements()) {
            String msg = ew.nextElement();
            emit.emit("\t! ");
            emit.emit(msg);
            emit.endLine();
        }

        emit.emit("\t.align\t8");
        emit.endLine();
        emit.emit("\t.align\t8");
        emit.endLine();
        emit.emit("\t.skip\t16");
        emit.endLine();

        if (sla >= 0) {
            emit.emit("\t!\tline ");
            emit.emit(sla);
            emit.endLine();
        }

        if (rd.isMain() && asm.isFortran()) {
            emit.emit("\t.global\tMAIN__");
            emit.endLine();
            emit.emit("MAIN__:");
        }

        if (rd.visibility() == Visibility.GLOBAL) {
            emit.emit(rd.isWeak() ? "\t.weak\t" : "\t.global\t");
            emit.emit(name);
            emit.endLine();
        }

        emit.emit("\t.type\t");
        emit.emit(name);
        emit.emit(",#function");
        emit.endLine();
        emit.emit(name);
        emit.emit(':');
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer(getClass().getName());
        buf.append(' ');
        buf.append(scribble);
        return buf.toString();
    }
}


