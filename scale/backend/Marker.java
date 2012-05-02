package scale.backend;

import scale.common.*;

/** 
 * Instances of this class are used to mark places in the instruction stream.
 * <p>
 * $Id: Marker.java,v 1.24 2006-02-02 22:37:06 burrill Exp $
 * <p>
 * Copyright 2005 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 * <p>
 * No machine instructions are generated from a Marker instruction.
 * However, assembly language directives may be generated by a Marker.
 */

public abstract class Marker extends Instruction
{
  protected Marker()
  {
    super();
  }

  public void remapRegisters(int[] map)
  {
  }

  /**
   * Map the registers used in the instruction as sources to the specified register.
   * If the register is not used as a source register, no change is made.
   * @param oldReg is the previous source register
   * @param newReg is the new source register
   */
  public void remapSrcRegister(int oldReg, int newReg)
  {
  }

  /**
   * Map the registers defined in the instruction as destinations to the specified register.
   * If the register is not used as a destination register, no change is made.
   * @param oldReg is the previous destination register
   * @param newReg is the new destination register
   */
  public void remapDestRegister(int oldReg, int newReg)
  {
  }

  /**
   * Return the numeric opcode of the instruction.
   * This opcode may be an encoding of the actual instruction opcode.
   */
  public final int getOpcode()
  {
    return -1;
  }

  /**
   * Return true if this is a Marker.
   */
  public final boolean isMarker()
  {
    return true;
  }

  /**
   * Return the number of cycles that this instruction requires.
   */
  public int getExecutionCycles()
  {
    return 0;
  }

  /**
   * Insert the assembler representation of the instruction into the output stream. 
   */
  public void assembler(Assembler asm, Emit emit)
  {
    if (Debug.debug(1))
      asm.assembleComment(toString(), emit);
  }

  public int instructionSize()
  {
    return 0;
  }

  public String toString()
  {
    StringBuffer buf = new StringBuffer("(");
    buf.append(getClass().getName());
    buf.append(')');
    return buf.toString();
  }

  /**
   * Specify the registers used by this instruction.
   * @param rs is the register set in use
   * @param index is an index associated with the instruction
   * @param strength is the importance of the instruction
   * @see scale.backend.RegisterAllocator#useRegister(int,int,int)
   * @see scale.backend.RegisterAllocator#defRegister(int,int)
   */
  public void specifyRegisterUsage(RegisterAllocator rs, int index, int strength)
  {
  }

  /**
   * Mark the instruction as no longer needed.
   */
  public void nullify(RegisterSet rs)
  {
  }

  /**
   * Return true if this instruction is independent of the specified instruction.
   * If instructions are independent, than one instruction can be moved before
   * or after the other instruction without changing the semantics of the program.
   * @param inst is the specified instruction
   */
  public boolean independent(Instruction inst, RegisterSet registers)
  {
    return true;
  }
}
