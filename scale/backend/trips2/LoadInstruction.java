package scale.backend.trips2;

import scale.common.*;
import scale.backend.*;

/**
 * This class represents Trips load instructions.
 * <p>
 * $Id: LoadInstruction.java,v 1.39 2006-11-16 17:49:40 burrill Exp $
 * <p>
 * Copyright 2005 by the <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
 * <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
 * <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
 * Amherst MA. 01003, USA<br>
 * All Rights Reserved.<br>
 * <p>
 */

public class LoadInstruction extends TripsInstruction
{
  private static int createdCount = 0; /* A count of all the instances of this class that were created. */

  private static final String[] stats = {"created"};

  static
  {
    Statistics.register("scale.backend.trips2.LoadInstruction", stats);
  }

  /**
   * Return the number of instances of this class created.
   */
  public static int created()
  {
    return createdCount;
  }

  /**
   * the instruction opcode.
   */
  private int opcode;

  /**
   * the ra register.
   */
  protected int ra;

  /**
   * the rb register.
   */
  protected int rb;

  /**
   * the immediate value.
   */
  protected long imm;

  /**
   * The load/store queue id of the load.
   */
  protected int lsqid;
  
  /**
   * Displacement.
   */
  private Displacement disp;
  
  /**
   * Create a new Load/Store Trips instruction in the L:1 format.
   * <p>
   * @param opcode specifies the pseudo-operation
   * @param ra specifies the destination register
   * @param rb specifies the left argument register (ie: base address)
   * @param imm specifies the immediate signed offset to base address
   * @see scale.backend.trips2.Trips2Machine#minImmediate
   * @see scale.backend.trips2.Trips2Machine#maxImmediate
   * @param rp specifies the register to predicate on (-1 if unused)
   * @param predicatedOnTrue speficies the condition to predicate on (ignored if rp is -1)
   */
  public LoadInstruction(int opcode, int ra, int rb, long imm, int rp, boolean predicatedOnTrue)
  {
    super(rp, predicatedOnTrue);
    
    byte format = Opcodes.getFormat(opcode);
    if ((format != Opcodes.L1) && (format != Opcodes.LPF))
      throw new scale.common.InternalError("Wrong form for L:1 instruction format.");

    this.opcode = opcode;
    this.imm    = imm;
    this.lsqid  = -1;
    this.ra     = ra;
    this.rb     = rb;
    this.disp   = null;

    createdCount++;
  }

  /**
   * Create a new non-predicated Load/Store Trips instruction in the L:1 format.
   * <p>
   * @param opcode specifies the pseudo-operation
   * @param ra specifies the destination register
   * @param rb specifies the left argument register (ie: base address)
   * @param imm specifies the immediate signed offset to base address
   * @see scale.backend.trips2.Trips2Machine#minImmediate
   * @see scale.backend.trips2.Trips2Machine#maxImmediate
   */
  public LoadInstruction(int opcode, int ra, int rb, long imm)
  {
    this(opcode, ra, rb, imm, -1, false);
  }

  /**
   * Create a new non-predicated Load/Store Trips instruction in the L:1 format.
   * <p>
   * @param opcode specifies the pseudo-operation
   * @param ra specifies the destination register
   * @param rb specifies the left argument register (ie: base address)
   * @param disp specifies the immediate signed offset to base address
   * @see scale.backend.trips2.Trips2Machine#minImmediate
   * @see scale.backend.trips2.Trips2Machine#maxImmediate
   */
  public LoadInstruction(int opcode, int ra, int rb, Displacement disp)
  {
    this(opcode, ra, rb, 0, -1, false);
    
    this.disp = disp;
  }
  
  /**
   * Map the registers used in the instruction to the specified registers.
   */
  public void remapRegisters(int[] map)
  {
    super.remapRegisters(map);
    ra = map[ra];
    rb = map[rb];
  }

  /**
   * Map the registers used in the instruction as sources to the specified register.
   * If the register is not used as a source register, no change is made.
   * @param oldReg is the previous source register
   * @param newReg is the new source register
   */
  public void remapSrcRegister(int oldReg, int newReg)
  {
    super.remapSrcRegister(oldReg, newReg);
    if (rb == oldReg)
      rb = newReg;
  }

  /**
   * Map the registers defined in the instruction as destinations to the specified register.
   * If the register is not used as a destination register, no change is made.
   * @param oldReg is the previous destination register
   * @param newReg is the new destination register
   */
  public void remapDestRegister(int oldReg, int newReg)
  {
    if (ra == oldReg)
      ra = newReg;
  }

  /**
   * Return the instruction opcode.
   */
  public int getOpcode()
  {
    return opcode & 0xff;
  }
  
  /**
   * Set the instruction opcode.
   */
  protected void setOpcode(int opcode)
  {
    this.opcode = opcode;
  }

  /**
   * Return the instruction format.
   */
  public byte getFormat()
  {
    return Opcodes.getFormat(opcode);
  }

  /**
   * Return the destination register.
   */
  public int getDestRegister()
  {
    if (opcode == Opcodes._LPF)
      return -1;

    return ra;
  }
  
  /**
   * This routine returns the source registers for an instruction.
   * Null is returned if there are no source registers.
   */
  public int[] getSrcRegisters()
  {
    int[] sups = super.getSrcRegisters();
    int   len  = sups == null ? 0 : sups.length;
    int[] srcs = new int[1 + len];
    
    srcs[0] = rb;
    
    if (len > 0)
      System.arraycopy(sups, 0, srcs, 1, len);
    
    return srcs;
  }
  
  /**
   * Return the ra field.
   */
  public int getRa()
  {
    return ra;
  }

  /**
   * Return the rb field.
   */
  public int getRb()
  {
    return rb;
  }

  /**
   * Set the rb field.
   */
  protected void setRb(int rb)
  {
    this.rb = rb;
  }
  
  /**
   * Return the immediate field.
   */
  public long getImm()
  {
    return imm;
  }
  
  /**
   * Return the displacement field.
   */
  public Displacement getDisp()
  {
    return disp;
  }
  
  /**
   * Set the displacement field.
   */
  protected void setDisp(Displacement disp)
  {
    this.disp = disp;
  }
  
  /**
   * Set the immediate field.
   */
  protected void setImm(long imm)
  {
    this.imm = imm;
  }

  /**
   * Return the load/store queue id.
   */
  public int getLSQid()
  {
    return lsqid;
  }
  
  /**
   * Set the load/store queue id.
   */
  protected void setLSQid(int lsqid)
  {
    if (opcode != Opcodes._LPF)
      this.lsqid = lsqid;
  }
  
  /**
   * Return true if this is a spill.
   */
  public boolean isSpill()
  {
    return opcode == Opcodes._LDSPILL;
  }
  
  /**
   * Return true if this is a prefetch instruction.
   */
  public boolean isPrefetch()
  {
    return opcode == Opcodes._LPF;
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
    super.specifyRegisterUsage(rs, index, strength);
    if (opcode != Opcodes._LPF)
      rs.defRegister(index, ra);
    rs.useRegister(index, rb, strength);
  }

  /**
   * Return true if the instruction uses the register.
   */
  public boolean uses(int register, RegisterSet registers)
  {
    return (rb == register || super.uses(register, registers));
  }

  /**
   * Return true if the instruction sets the register
   */
  public boolean defs(int register, RegisterSet registers)
  {
    if (opcode == Opcodes._LPF)
      return false;

    return (register == ra);
  }

  /**
   * Return true if this instruction is independent of the specified instruction.
   * If instructions are independent, than one instruction can be moved before
   * or after the other instruction without changing the semantics of the program.
   * @param inst is the specified instruction
   */
  public boolean independent(Instruction inst, RegisterSet registers)
  {
    if ((opcode != Opcodes._LPF) && inst.uses(ra, registers))
      return false;

    if (inst.defs(rb, registers))
      return false;

    return super.independent(inst, registers);
  }

  /**
   * Return the number of bytes required for the TripsInstruction.
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
    if (nullified())
      return true;
    return false;
  }

  /**
   * Mark the instruction so that it is not used.
   */
  public void nullify(RegisterSet rs)
  {
  }

  /**
   * Return true if the instruction copies a value from one register
   * to another without modification.  Transfers between integer and
   * floating point registers are not considered to be copy instructions.
   */
  public boolean isCopy()
  {
    return false;
  }

  /**
   * Return true if this is a load-from-memory instruction.
   */
  public final boolean isLoad()
  {    
    if (opcode == Opcodes._LPF)
      return false;
  
    return true;
  }
  
  /**
   * Return the source register of a copy instruction.
   */
  public int getCopySrc()
  {
    return super.getCopySrc();
  }

  /**
   * Return the source register of a copy instruction.
   */
  public int getCopyDest()
  {
    return ra; 
  }

  /**
   * Return a hash code that can be used to determine equivalence.
   * <br>
   * The hash code does not include predicates or the destination register.
   */
  public int ehash()
  {
    if (opcode == Opcodes._LPF)
      return -1;
    
    String       str = Opcodes.getOp(opcode);
    StringBuffer buf = new StringBuffer(str);
    
    if (disp == null)
      buf.append(imm);
    else
      buf.append(disp);
    
    buf.append(rb);
    
    return buf.toString().hashCode();
  }
  
  /**
   * Insert the assembler representation of the instruction into the output stream.
   */
  public void assembler(Assembler asm, Emit emit)
  {
    int op = opcode;
    if (isSpill())
      op = Opcodes.LD;
    
    emit.emit(formatOpcode(asm, op));
    emit.emit('\t');
    if (opcode != Opcodes._LPF) {
      emit.emit(formatRa(asm, ra));
      emit.emit(", ");
    }
    
    if (disp != null) {
      if (disp.isNumeric()) {
        long d = disp.getDisplacement();
        if (d != 0)
          emit.emit(d);
      } else
        emit.emit(disp.assembler(asm));
    } else if (imm != 0)
      emit.emit(imm);

    emit.emit('(');
    emit.emit(asm.assembleRegister(rb));
    emit.emit(')');
    
    if (lsqid > -1)
      emit.emit(" L[" + lsqid + "]");
    
    if (isSpill())
      emit.emit("\t;# spillLoad");
  }

  /**
   * Return a string representation of the instruction.
   */
  public String toString()
  {
    StringBuffer buf = new StringBuffer("");

    buf.append(formatOpcode(opcode));
    buf.append('\t');
    if (opcode != Opcodes._LPF) {
      buf.append(ra);
      buf.append(' ');
    }
    if (disp == null)
      buf.append(imm);
    else
      buf.append(disp);
    buf.append('(');
    buf.append(rb);
    buf.append(')');
    
    if (lsqid > -1)
      buf.append(" L[" + lsqid + "]");
    
    return buf.toString();
  }
}
