package scale.backend.alpha;

import scale.backend.Assembler;
import scale.backend.Instruction;
import scale.backend.RegisterAllocator;
import scale.backend.RegisterSet;
import scale.common.Emit;
import scale.common.Statistics;

/**
 * This class represents Alpha integer arithmetic instructions that use a literal (0 <= value < 256).
 * <p/>
 * $Id: IntOpLitInstruction.java,v 1.30 2006-11-09 00:56:09 burrill Exp $
 * <p/>
 * Copyright 2005 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 */

public class IntOpLitInstruction
        extends Instruction
{
    private static int createdCount = 0; /* A count of all the instances of this class that were created. */

    private static final String[] stats = {"created"};

    static {
        Statistics.register("scale.backend.alpha.IntOpLitInstruction", stats);
    }

    /**
     * Return the number of instances of this class created.
     */
    public static int created()
    {
        return createdCount;
    }

    /**
     * the instruction opcode
     */
    private int opcode;

    /**
     * the ra register.
     */
    protected int ra;

    /**
     * the literal value
     */
    protected int value;

    /**
     * the rc register
     */
    protected int rc;

    protected IntOpLitInstruction(int opcode, int ra, int value, int rc)
    {
        super();
        createdCount++;
        this.opcode = opcode;
        this.ra = ra;
        this.value = value;
        this.rc = rc;
    }

    public void remapRegisters(int[] map)
    {
        ra = map[ra];
        rc = map[rc];
    }

    /**
     * Return the destination register or -1 if none.
     */
    public int getDestRegister()
    {
        return rc;
    }

    /**
     * Map the registers used in the instruction as sources to the specified register.
     * If the register is not used as a source register, no change is made.
     *
     * @param oldReg is the previous source register
     * @param newReg is the new source register
     */
    public void remapSrcRegister(int oldReg, int newReg)
    {
        if (ra == oldReg) {
            ra = newReg;
        }
    }

    /**
     * Map the registers defined in the instruction as destinations to the specified register.
     * If the register is not used as a destination register, no change is made.
     *
     * @param oldReg is the previous destination register
     * @param newReg is the new destination register
     */
    public void remapDestRegister(int oldReg, int newReg)
    {
        if (rc == oldReg) {
            rc = newReg;
        }
    }

    public int getOpcode()
    {
        return opcode;
    }

    /**
     * Return the number of cycles that this instruction requires.
     */
    public int getExecutionCycles()
    {
        return Opcodes.getExecutionCycles(opcode);
    }

    /**
     * Return the number of the functional unit required to execute this
     * instruction.
     */
    public int getFunctionalUnit()
    {
        return AlphaMachine.FU_INTALU;
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
        rs.useRegister(index, ra, strength);
        rs.defRegister(index, rc);
        if (((opcode >> 12) == 0x11) && ((opcode & 0x4) != 0)) {
            rs.useRegister(index, rc, strength); // CMOV instruction.
        }
    }

    /**
     * Return true if the instruction uses the register.
     */
    public boolean uses(int register, RegisterSet registers)
    {
        if (register == ra) {
            return true;
        }
        return ((register == rc)
                && ((opcode >> 12) == 0x11)
                && ((opcode & 0x4) != 0));
    }

    /**
     * Return true if the instruction sets the register
     */
    public boolean defs(int register, RegisterSet registers)
    {
        return (register == rc);
    }

    /**
     * Return true if this instruction is independent of the specified instruction.
     * If instructions are independent, than one instruction can be moved before
     * or after the other instruction without changing the semantics of the program.
     *
     * @param inst is the specified instruction
     */
    public boolean independent(Instruction inst, RegisterSet registers)
    {
        if (inst.defs(ra, registers)) {
            return false;
        }
        if (inst.uses(rc, registers)) {
            return false;
        }
        if (((opcode >> 12) == 0x11) && ((opcode & 0x4) != 0) && inst.defs(rc, registers)) {
            return false;
        }
        return true;
    }

    /**
     * Return the number of bytes required for the IntOpLitInstruction.
     */
    public int instructionSize()
    {
        return 4;
    }

    /**
     * Return true if the instruction can be deleted without changing program semantics.
     */
    public boolean canBeDeleted(RegisterSet registers)
    {
        if (nullified()) {
            return true;
        }
        return (rc == AlphaRegisterSet.I0_REG) ||
                ((opcode == Opcodes.BIS) && (ra == rc) && (value == 0));
    }

    /**
     * Return true if the instruction copies a value from one register
     * to another without modification.  Transfers between integer and
     * floating point registers are not considered to be copy instructions.
     */
    public boolean isCopy()
    {
        if (opcode == Opcodes.BIS) {
            return (value == 0);
        }

        if (opcode == Opcodes.ADDQ) {
            return (value == 0);
        }

        return false;
    }

    /**
     * Return the source register of a copy instruction.
     */
    public int getCopySrc()
    {
        if (opcode == Opcodes.BIS) {
            if (value == 0) {
                return ra;
            }
        }
        else if (opcode == Opcodes.ADDQ) {
            if (value == 0) {
                return ra;
            }
        }

        return super.getCopySrc();
    }

    /**
     * Return the source register of a copy instruction.
     */
    public int getCopyDest()
    {
        return rc;
    }

    /**
     * Insert the assembler representation of the instruction into the output stream.
     */
    public void assembler(Assembler gen, Emit emit)
    {
        if (nullified()) {
            emit.emit("nop # ");
        }

        if (opcode == Opcodes.BIS) {
            if (value == 0) {
                if (ra == AlphaRegisterSet.I0_REG) {
                    emit.emit("clr\t");
                    emit.emit(gen.assembleRegister(rc));
                    return;
                }
                else {
                    emit.emit("mov\t");
                    emit.emit(gen.assembleRegister(ra));
                    emit.emit(',');
                    emit.emit(gen.assembleRegister(rc));
                    return;
                }
            }
            else if (ra == AlphaRegisterSet.I0_REG) {
                emit.emit("mov\t");
                emit.emit(value);
                emit.emit(',');
                emit.emit(gen.assembleRegister(rc));
                return;
            }
        }
        else if ((opcode == Opcodes.ADDL) && (value == 0)) {
            emit.emit("sextl\t");
            emit.emit(gen.assembleRegister(ra));
            emit.emit(',');
            emit.emit(gen.assembleRegister(rc));
            return;
        }

        emit.emit(Opcodes.getOp(opcode));
        emit.emit('\t');
        emit.emit(gen.assembleRegister(ra));
        emit.emit(',');
        emit.emit(value);
        emit.emit(',');
        emit.emit(gen.assembleRegister(rc));
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer(Opcodes.getOp(this));
        buf.append("\t$");
        buf.append(ra);
        buf.append(',');
        buf.append(value);
        buf.append(",$");
        buf.append(rc);

        return buf.toString();
    }
}
