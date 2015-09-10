package scale.backend.sparc;

import scale.backend.Assembler;
import scale.backend.Instruction;
import scale.backend.RegisterAllocator;
import scale.backend.RegisterSet;
import scale.common.Emit;
import scale.common.Statistics;

/**
 * This class represents Sparc integer arithmetic instructions that use an immediate value.
 * <p/>
 * $Id: Inst8Instruction.java,v 1.20 2005-03-24 13:56:53 burrill Exp $
 * <p/>
 * Copyright 2005 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 * <p/>
 * Instance=08, Op=1x
 */

public class Inst8Instruction
        extends SparcInstruction
{
    private static int createdCount = 0; /* A count of all the instances of this class that were created. */

    private static final String[] stats = {"created"};

    static {
        Statistics.register("scale.backend.sparc.Inst8Instruction", stats);
    }

    /**
     * Return the number of instances of this class created.
     */
    public static int created()
    {
        return createdCount;
    }

    /**
     * the literal value
     */
    protected int value;

    /**
     * the rs1 register
     */
    protected int rs1;

    protected Inst8Instruction(int opcode, int rs1, int value)
    {
        super(opcode);
        this.rs1 = rs1;
        this.value = value;

        createdCount++;
    }

    public void remapRegisters(int[] map)
    {
        rs1 = map[rs1];
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
        if (rs1 == oldReg) {
            rs1 = newReg;
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
        rs.useRegister(index, rs1, strength);
    }

    /**
     * Return true if the instruction uses the register.
     */
    public boolean uses(int register, RegisterSet registers)
    {
        int ars1 = registers.actualRegister(rs1);
        int areg = registers.actualRegister(register);
        return (areg == ars1);
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
        if (inst.defs(rs1, registers)) {
            return false;
        }
        if (!(inst instanceof SparcInstruction)) {
            return true;
        }

        return independentCC((SparcInstruction) inst);
    }

    /**
     * Mark the instruction as no longer needed.
     */
    public void nullify(RegisterSet rs)
    {
    }

    /**
     * Insert the assembler representation of the instruction into the output stream.
     */
    public void assembler(Assembler gen, Emit emit)
    {
        emit.emit(Opcodes.getOp(this));
        emit.emit('\t');
        emit.emit(gen.assembleRegister(rs1));
        emit.emit('+');
        emit.emit(value);
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer(Opcodes.getOp(this));
        buf.append("\t%");
        buf.append(rs1);
        buf.append(',');
        buf.append(value);

        return buf.toString();
    }
}


