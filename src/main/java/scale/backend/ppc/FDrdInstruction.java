package scale.backend.ppc;

import scale.backend.Assembler;
import scale.backend.Displacement;
import scale.common.Emit;

/**
 * This is the base class for all PPC instructions that have a destination register,
 * a source register, and a displacement.
 * <p/>
 * $Id: FDrdInstruction.java,v 1.7 2006-11-09 00:56:04 burrill Exp $
 * <p/>
 * Copyright 2005 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 * <p/>
 */

public class FDrdInstruction
        extends FDrInstruction
{
    /**
     * The displacement.
     */
    protected Displacement disp;

    /**
     *
     */
    private int dftn;

    /**
     *
     */
    private boolean macosx;

    /**
     * @param opcode is the instruction's opcode
     * @param rd is the destination register
     * @param ra is the source register
     * @param disp is the displacement
     * @param dftn specifies the loader operation on the displacement
     * @param macosx boolean specifies macosx when true and linux when false
     */
    public FDrdInstruction(int opcode, int rd, int ra, Displacement disp, int dftn, boolean macosx)
    {
        super(opcode, rd, ra);
        this.disp = disp;
        this.dftn = dftn;
        this.macosx = macosx;
    }

    /**
     * Insert the assembler representation of the instruction into the output stream.
     */
    public void assembler(Assembler asm, Emit emit)
    {
        if (nullified()) {
            emit.emit("nop ! ");
        }

        emit.emit(Opcodes.getOp(opcode));
        emit.emit('\t');
        emit.emit(asm.assembleRegister(rd));
        emit.emit(',');
        if ((ra == 0) && (macosx)) {
            emit.emit('0');
        }
        else {
            emit.emit(asm.assembleRegister(ra));
        }
        emit.emit(',');
        emit.emit(assembleDisp(asm, disp, dftn, macosx));
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer(Opcodes.getOp(this));
        buf.append('\t');
        buf.append(rd);
        buf.append(',');
        buf.append(ra);
        buf.append(',');
        buf.append(PPCGenerator.displayDisp(disp, dftn, macosx));
        buf.append(',');
        return buf.toString();
    }
}
