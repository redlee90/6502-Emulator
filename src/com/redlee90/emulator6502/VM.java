package com.redlee90.emulator6502;

public class VM {
	private int regA = 0x0;
	private int regX = 0x0;
	private int regY = 0x0;
	private int regP = 0x30;
	private int regPC = 0x600;
	private int regSP = 0x1ff;
	private boolean codeRunning = true;
	private Memory memory;
	private PPU ppu;

	public VM(Memory memory, PPU ppu) {
		this.memory = memory;
		this.ppu = ppu;
	}

	public void runStep() {
		if (memory.cells[regPC] > -1 && codeRunning) {
			int command = memory.cells[regPC++];
			int realAddr;
			int ZPAddr;
			int ABSAddr;
			int INDAddr;
			int INDAddrPart1;
			int INDAddrPart2;
			int value; // for whatever use
			int beforeINC;
			int afterINC;
			int offset;

			switch (command) {
			case 0x00: // BRK, SNGL
				codeRunning = false;
				break;
			case 0x01: // ORA, INDX
				ZPAddr = popByte();
				realAddr = memory.getWord((ZPAddr + regX) & 0xff);
				value = memory.getByte(realAddr);
				regA |= value;
				setNVFlags(regA);
				break;
			case 0x05: // ORA, ZP
				realAddr = popByte();
				value = memory.getByte(realAddr);
				regA |= value;
				setNVFlags(regA);
				break;
			case 0x06: // ASL, ZP
				ZPAddr = popByte();
				value = memory.getByte(ZPAddr);
				setCarryFlagFromBit7(value);
				value = value << 1;
				memory.cells[ZPAddr] = value;
				setNVFlags(value);
				break;
			case 0x08: // PHP, SNGL
				value = regP | 0x30;
				stackPush(value);
				break;
			case 0x09: // ORA, Imm
				regA |= popByte();
				setNVFlags(regA);
				break;
			case 0x0a: // ASL, SNGL
				setCarryFlagFromBit7(regA);
				regA = (regA << 1) & 0xff;
				setNVFlags(regA);
				break;
			case 0x0d: // ORA, ABS
				realAddr = popWord();
				value = memory.getByte(realAddr);
				regA |= value;
				setNVFlags(regA);
				break;
			case 0x0e: // ASL, ABS
				realAddr = popWord();
				value = memory.getByte(realAddr);
				setCarryFlagFromBit7(value);
				value = value << 1;
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x10: // BPL, BRA
				offset = popByte();
				if (negativeSet()<=0) {
					jumpBranch(offset);
				}
				break;
			case 0x11: // ORA, INDY
				ZPAddr = popByte();
				realAddr = memory.getWord(ZPAddr) + regY;
				value = memory.getByte(realAddr);
				regA |= value;
				setNVFlags(regA);
				break;
			case 0x15: // ORA, ZPX
				realAddr = popByte() + regX;
				value = memory.getByte(realAddr);
				regA |= value;
				setNVFlags(regA);
				break;
			case 0x16: // ASL, ZPX
				realAddr = (popByte() + regX) & 0xff;
				value = memory.getByte(realAddr);
				setCarryFlagFromBit7(value);
				value = value << 1;
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x18: // CLC, SNGL
				regP &= 0xfe;
				break;
			case 0x19: // ORA, ABSY
				ABSAddr = popWord();
				realAddr = ABSAddr + regY;
				value = memory.getByte(realAddr);
				regA |= value;
				setNVFlags(regA);
				break;
			case 0x1d: // ORA, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				value = memory.getByte(realAddr);
				regA |= value;
				setNVFlags(regA);
				break;
			case 0x1e: // ASL, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				value = memory.getByte(realAddr);
				setCarryFlagFromBit7(value);
				value = value << 1;
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x20: // JSR, ABS
				INDAddr = popWord();
				int currentAddr = regPC - 1;
				stackPush(currentAddr >> 8);
				stackPush(currentAddr & 0xff);
				regPC = INDAddr;
				break;
			case 0x21: // AND, INDX
				ZPAddr = popByte();
				realAddr = memory.getWord(ZPAddr + regX);
				value = memory.getByte(realAddr);
				regA &= value;
				setNVFlags(regA);
				break;
			case 0x25: // AND, ZP
				ZPAddr = popByte();
				value = memory.getByte(ZPAddr);
				regA &= value;
				setNVFlags(regA);
				break;
			case 0x28: // PLP, SNGL
				regP = stackPop() | 0x30;
				break;
			case 0x29: // AND, Imm
				regA &= popByte();
				setNVFlags(regA);
				break;
			case 0x2d: // AND, ABS
				realAddr = popWord();
				value = memory.getByte(realAddr);
				regA &= value;
				setNVFlags(regA);
				break;
			case 0x30: // BMI, BRA
				offset = popByte();
				if (negativeSet()>0) {
					jumpBranch(offset);
				}
				break;
			case 0x31: // AND, INDY
				ZPAddr = popByte();
				realAddr = memory.getWord(ZPAddr) + regY;
				value = memory.getByte(realAddr);
				regA &= value;
				setNVFlags(regA);
				break;
			case 0x35: // AND, ZPX
				ZPAddr = popByte();
				realAddr = ZPAddr + regX;
				regA &= memory.getByte(realAddr);
				setNVFlags(regA);
				break;
			case 0x38: // SEC, SNGL
				regP |= 1;
				break;
			case 0x39: // AND, ABSY
				ABSAddr = popWord();
				realAddr = ABSAddr + regY;
				value = memory.getByte(realAddr);
				regA &= value;
				break;
			case 0x3d: // AND, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				value = memory.getByte(realAddr);
				regA &= value;
				break;
			case 0x40: // RTI, SNGL
				regP = stackPop();
				regPC = stackPop() | (stackPop() << 8);
				break;
			case 0x41: // EOR, INDX
				ZPAddr = popByte();
				realAddr = memory.getWord(ZPAddr + regX);
				value = memory.getWord(realAddr);
				regA ^= value;
				setNVFlags(regA);
				break;
			case 0x45: // EOR, ZP
				realAddr = popByte() & 0xff;
				value = memory.getByte(realAddr);
				regA ^= value;
				setNVFlags(regA);
				break;
			case 0x46: // LSR, ZP
				realAddr = popByte() & 0xff;
				value = memory.getByte(realAddr);
				setCarryFlagFromBit0(value);
				value = value >> 1;
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x48: // PHA, SNGL
				stackPush(regA);
				break;
			case 0x49: // EOR, Imm
				regA ^= popByte();
				setNVFlags(regA);
				break;
			case 0x4a: // EOR, SNGL
				setCarryFlagFromBit0(regA);
				regA = regA >> 1;
				setNVFlags(regA);
				break;
			case 0x4c: // JMP, ABS
				regPC = popWord();
				break;
			case 0x4d: // EOR, ABS
				realAddr = popWord();
				value = memory.getByte(realAddr);
				regA ^= value;
				setNVFlags(regA);
				break;
			case 0x4e: // LSR, ABS
				realAddr = popWord();
				value = memory.getByte(realAddr);
				setCarryFlagFromBit0(value);
				value = value >> 1;
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x50: // BVC, BRA
				offset = popByte();
				if (overFlowSet()<=0) {
					jumpBranch(offset);
				}
				break;
			case 0x51: // EOR, INDY
				ZPAddr = popByte();
				realAddr = memory.getWord(ZPAddr) + regY;
				value = memory.getByte(realAddr);
				regA ^= value;
				setNVFlags(regA);
				break;
			case 0x55: // EOR, ZPX
				realAddr = popByte() + regX;
				value = memory.getByte(realAddr);
				regA ^= value;
				setNVFlags(regA);
				break;
			case 0x56: // LSR, ZPX
				realAddr = (popByte() + regX) & 0xff;
				value = memory.getByte(realAddr);
				setCarryFlagFromBit0(value);
				value = value >> 1;
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x58: // CLI, SNGL
				regP &= 0x04;
				break;
			case 0x59: // EOR, ABSY
				realAddr = popWord() + regY;
				value = memory.getByte(realAddr);
				regA ^= value;
				setNVFlags(regA);
				break;
			case 0x5d: // EOR, ABSX
				realAddr = popWord() + regX;
				value = memory.getByte(realAddr);
				regA ^= value;
				setNVFlags(regA);
				break;
			case 0x5e: // LSR, ABSX
				realAddr = popWord() + regX;
				value = memory.getByte(realAddr);
				setCarryFlagFromBit0(value);
				value = value >> 1;
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x60: // RTS, SNGL
				regPC = (stackPop() | (stackPop() << 8)) + 1;
				break;
			case 0x61: // ADC, INDX
				ZPAddr = popByte();
				INDAddr = ZPAddr + regX;
				realAddr = memory.getWord(INDAddr);
				regA += memory.getByte(realAddr);
				break;
			case 0x65: // ADC, ZP
				realAddr = popByte();
				regA += memory.getByte(realAddr);
				break;
			case 0x66: // ROR, ZP
				realAddr = popByte();
				value = memory.getByte(realAddr);
				setCarryFlagFromBit0(value);
				value = value >> 1;
				if (carrySet()>0) {
					value |= 0x80;
				}
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x68: // PLA, SNGL
				int head = stackPop();
				regA = head;
				setNVFlags(head);
				break;
			case 0x69: // ADC, Imm
				regA += popByte();
				break;
			case 0x6a: // ROR, SNGL
				setCarryFlagFromBit0(regA);
				regA = regA >> 1;
				if (carrySet()>0) {
					regA |= 0x80;
				}
				setNVFlags(regA);
				break;
			case 0x6c: // JMP, IND
				INDAddr = popWord();
				System.out.println("indirect addfess is "
						+ Integer.toHexString(INDAddr));
				realAddr = memory.getWord(INDAddr);
				System.out.println("real address is "
						+ Integer.toHexString(realAddr));
				regPC = realAddr;
				break;
			case 0x6d: // ADC, ABS
				realAddr = popWord();
				regA += memory.getByte(realAddr);
				break;
			case 0x6e: // ROR, ABS
				realAddr = popWord();
				value = memory.getByte(realAddr);
				setCarryFlagFromBit0(value);
				value = value >> 1;
				if (carrySet()>0) {
					value |= 0x80;
				}
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x70: // BVS, BRA
				offset = popByte();
				if (overFlowSet()>0) {
					jumpBranch(offset);
				}
				break;
			case 0x71: // ADC, INDY
				ZPAddr = popByte();
				INDAddr = ZPAddr;
				INDAddrPart1 = memory.cells[INDAddr];
				INDAddrPart2 = memory.cells[INDAddr + 1];
				realAddr = INDAddrPart1 + regY + INDAddrPart2 << 8;
				regA += memory.getByte(realAddr);
				break;
			case 0x75: // ADC, ZPX
				ZPAddr = popByte();
				realAddr = ZPAddr + regX;
				regA += memory.getByte(realAddr);
				break;
			case 0x76: // ROR, ZPX
				ZPAddr = popByte();
				realAddr = ZPAddr + regX;
				value = memory.getByte(realAddr);
				setCarryFlagFromBit0(value);
				value = value >> 1;
				if (carrySet()>0) {
					value |= 0x80;
				}
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x79: // ADC, ABSY
				ABSAddr = popWord();
				realAddr = ABSAddr + regY;
				regA += memory.getByte(realAddr);
				break;
			case 0x7d: // ADC, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				regA += memory.getByte(realAddr);
				break;
			case 0x7e: // ROR, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				value = memory.getByte(realAddr);
				setCarryFlagFromBit0(value);
				value = value >> 1;
				if (carrySet()>0) {
					value |= 0x80;
				}
				memory.cells[realAddr] = value;
				setNVFlags(value);
				break;
			case 0x81: // STA, INDX
				ZPAddr = popByte();
				INDAddr = ZPAddr + regX;
				INDAddrPart1 = memory.cells[INDAddr];
				INDAddrPart2 = memory.cells[INDAddr + 1];
				realAddr = INDAddrPart1 + INDAddrPart2 << 8;
				memory.cells[realAddr] = regA;
				break;
			case 0x78: // SEI, SNGL
				regP |= 0x04;
				break;
			case 0x84: // STY, ZP
				realAddr = popByte();
				memory.cells[realAddr] = regY;
				break;
			case 0x85: // STA, ZP
				realAddr = popByte();
				memory.cells[realAddr] = regA;
				break;
			case 0x86: // STX, ZP
				realAddr = popByte();
				memory.cells[realAddr] = regX;
				break;
			case 0x88: // DEY, SNGL
				regY = regY - 1;
				break;
			case 0x8a: // TXA, SNGL
				regA = regX;
				break;
			case 0x8c: // STY, ABS
				realAddr = popWord();
				memory.cells[realAddr] = regY;
				break;
			case 0x8d: // STA, ABS
				realAddr = popWord();
				memory.cells[realAddr] = regA;
				break;
			case 0x8e: // STX, ABS
				realAddr = popWord();
				memory.cells[realAddr] = regX;
				break;
			case 0x90: // BCC, BRA
				offset = popByte();
				if (carrySet()<=0) {
					jumpBranch(offset);
				}
				break;
			case 0x91: // STA, INDY
				ZPAddr = popByte();
				INDAddr = ZPAddr;
				INDAddrPart1 = memory.cells[INDAddr];
				INDAddrPart2 = memory.cells[INDAddr + 1];
				realAddr = INDAddrPart1 + regY + INDAddrPart2 << 8;
				memory.cells[realAddr] = regA;
				break;
			case 0x94: // STY, ZPX
				ZPAddr = popByte();
				realAddr = ZPAddr + regX;
				memory.cells[realAddr] = regY;
				break;
			case 0x95: // STA, ZPX
				ZPAddr = popByte();
				realAddr = ZPAddr + regX;
				memory.cells[realAddr] = regA;
				break;
			case 0x96: // STX, ZPY
				ZPAddr = popByte();
				realAddr = ZPAddr + regY;
				memory.cells[realAddr] = regX;
				break;
			case 0x98: // TYA, SNGL
				regA = regY;
				break;
			case 0x99: // STA, ABSY
				ABSAddr = popWord();
				realAddr = ABSAddr + regY;
				memory.cells[realAddr] = regA;
				break;
			case 0x9a: // TXS, SNGL
				regSP = regX + 0x100;
				break;
			case 0x9d: // STA, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				memory.cells[realAddr] = regA;
				break;
			case 0xa0: // LDY, Imm
				regY = popByte();
				setNVFlags(regY);
				break;
			case 0xa1: // LDA, INDX
				ZPAddr = popByte();
				INDAddr = ZPAddr + regX;
				INDAddrPart1 = memory.cells[INDAddr];
				INDAddrPart2 = memory.cells[INDAddr + 1];
				realAddr = INDAddrPart1 + INDAddrPart2 << 8;
				regA = memory.getByte(realAddr);
				break;
			case 0xa2: // LDX, Imm
				regX = popByte();
				setNVFlags(regX);
				break;
			case 0xa4: // LDY, ZP
				realAddr = popByte();
				regY = memory.getByte(realAddr);
				setNVFlags(regY);
				break;
			case 0xa5: // LDA, ZP
				regA = memory.getByte(popByte());
				break;
			case 0xa6: // LDX, ZP
				realAddr = popByte();
				regX = memory.getByte(realAddr);
				setNVFlags(regX);
				break;
			case 0xa8: // TAY, SNGL
				regY = regA;
				break;
			case 0xa9: // LDA, Imm
				regA = popByte();
				MainActivity.showTextViewA(regA);
				break;
			case 0xaa: // TAX, SNGL
				regX = regA;
				MainActivity.showTextViewX(regX);
				break;
			case 0xac: // LDY, ABS
				realAddr = popWord();
				regY = memory.getByte(realAddr);
				setNVFlags(regY);
				break;
			case 0xad: // LDA, ABS
				realAddr = popWord();
				regA = memory.getByte(realAddr);
				break;
			case 0xae: // LDX, ABS
				realAddr = popWord();
				regX = memory.getByte(realAddr);
				setNVFlags(regX);
				break;
			case 0xb0: // BCS, BRA
				offset = popByte();
				if (carrySet()>0) {
					jumpBranch(offset);
				}
				break;
			case 0xb1: // LDA, INDY
				ZPAddr = popByte();
				INDAddr = ZPAddr;
				INDAddrPart1 = memory.cells[INDAddr];
				INDAddrPart2 = memory.cells[INDAddr + 1];
				realAddr = INDAddrPart1 + regY + INDAddrPart2 << 8;
				regA = memory.getByte(realAddr);
				break;
			case 0xb4: // LDY, ZPX
				ZPAddr = popByte();
				realAddr = (ZPAddr + regX)&0xff;
				regY = memory.getByte(realAddr);
				setNVFlags(regY);
				break;
			case 0xb5: // LDA, ZPX
				ZPAddr = popByte();
				realAddr = ZPAddr + regX;
				regA = memory.getByte(realAddr);
				break;
			case 0xb6: // LDX, ZPY
				ZPAddr = popByte();
				realAddr = ZPAddr + regY;
				regX = memory.getByte(realAddr);
				setNVFlags(regX);
				break;
			case 0xb8: // CLV, SNGL
				regP &= 0xbf;
				break;
			case 0xb9: // LDA, ABSY
				ABSAddr = popWord();
				realAddr = ABSAddr + regY;
				regA = memory.getByte(realAddr);
				break;
			case 0xba: // TSX, SNGL
				regX = regSP & 0xff;
				setNVFlags(regX);
				break;
			case 0xbc: // LDY, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				regY = memory.getByte(realAddr);
				setNVFlags(regY);
				break;
			case 0xbd: // LDA, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				regA = memory.getByte(realAddr);
				break;
			case 0xbe: // LDX, ABSY
				ABSAddr = popWord();
				realAddr = ABSAddr + regY;
				regX = memory.getByte(realAddr);
				setNVFlags(regX);
				break;
			case 0xc0: // CPY, Imm
				value = popByte();
				doCompare(regY, value);
				break;
			case 0xc1: // CMP, INDX
				ZPAddr = popByte();
				INDAddr = ZPAddr + regX;
				INDAddrPart1 = memory.cells[INDAddr];
				INDAddrPart2 = memory.cells[INDAddr + 1];
				realAddr = INDAddrPart1 + INDAddrPart2 << 8;
				value = memory.cells[realAddr];
				doCompare(regA, value);
				break;
			case 0xc4: // CPY, ZP
				realAddr = popByte();
				value = memory.getByte(realAddr);
				doCompare(regY, value);
				break;
			case 0xc5: // CMP, ZP
				realAddr = popByte();
				value = memory.cells[realAddr];
				doCompare(regA, value);
				break;
			case 0xc6: // DEC, ZP
				realAddr = popByte();
				beforeINC = memory.cells[realAddr];
				afterINC = beforeINC - 1;
				memory.cells[realAddr] = afterINC;
				break;
			case 0xc8: // INY, SNGL
				regY++;
				break;
			case 0xc9: // CMP, Imm
				value = popByte();
				doCompare(regA, value);
				break;
			case 0xca: // DEX, SNGL
				regX--;
				break;
			case 0xcd: // CMP, ABS
				realAddr = popWord();
				value = memory.cells[realAddr];
				doCompare(regA, value);
				break;
			case 0xcc: // CPY, ABS
				realAddr = popWord();
				value = memory.getByte(realAddr);
				doCompare(regY, value);
				break;
			case 0xce: // DEC, ABS
				realAddr = popWord();
				beforeINC = memory.cells[realAddr];
				afterINC = beforeINC - 1;
				memory.cells[realAddr] = afterINC;
				break;
			case 0xd0: // BNE, BRA
				offset = popByte();
				if (zeroSet()<=0) {
					jumpBranch(offset);
				}
				break;
			case 0xd1: // CMP, INDY
				ZPAddr = popByte();
				INDAddr = ZPAddr;
				INDAddrPart1 = memory.cells[INDAddr];
				INDAddrPart2 = memory.cells[INDAddr + 1];
				realAddr = INDAddrPart1 + regY + INDAddrPart2 << 8;
				value = memory.cells[realAddr];
				doCompare(regA, value);
				break;
			case 0xd5: // CMP, ZPX
				ZPAddr = popByte();
				realAddr = ZPAddr + regX;
				value = memory.cells[realAddr];
				doCompare(regA, value);
				break;
			case 0xd6: // DEC, ZPX
				ZPAddr = popByte();
				realAddr = ZPAddr + regX;
				beforeINC = memory.cells[realAddr];
				afterINC = beforeINC - 1;
				memory.cells[realAddr] = afterINC;
				break;
			case 0xd8: // CLD, SNGL
				regP &= 0xf7;
				break;
			case 0xd9: // CMP, ABSY
				ABSAddr = popWord();
				realAddr = ABSAddr + regY;
				value = memory.cells[realAddr];
				doCompare(regA, value);
				break;
			case 0xdd: // CMP, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				value = memory.cells[realAddr];
				doCompare(regA, value);
				break;
			case 0xde: // DEC, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				beforeINC = memory.cells[realAddr];
				afterINC = beforeINC - 1;
				memory.cells[realAddr] = afterINC;
				break;
			case 0xe0: // CPX, Imm
				value = popByte();
				doCompare(regX, value);
				break;
			case 0xe1: // SBC, INDX
				ZPAddr = popByte();
				realAddr = memory.getWord((ZPAddr + regX)&0xff);
				value = memory.getByte(realAddr);
				testSBC(value);
				break;			
			case 0xe4: // CPX, ZP
				realAddr = popByte();
				value = memory.getByte(realAddr);
				doCompare(regX, value);
				break;
			case 0xe5: // SBC, ZP
				realAddr = popByte();
				value = memory.getByte(realAddr);
				testSBC(value);
				break;
			case 0xe6: // INC, ZP
				realAddr = popByte();
				beforeINC = memory.cells[realAddr];
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = afterINC;
				break;
			case 0xe8: // INX, SNGL
				regX++;
				break;
			case 0xe9: // SBC, Imm
				value = popByte();
				testSBC(value);
				break;
			case 0xea: // NOP, SNGL
				break;
			case 0xec: // CPX, ABS
				realAddr = popWord();
				value = memory.getByte(realAddr);
				doCompare(regX, value);
				break;
			case 0xed: // SBC, ABS
				realAddr = popWord();
				value = memory.getByte(realAddr);
				testSBC(value);
				break;
			case 0xee: // INC, ABS
				realAddr = popWord();
				beforeINC = memory.cells[realAddr];
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = afterINC;
				break;
			case 0xf0: // BEQ, BRA
				offset = popByte();
				if (zeroSet()>0) {
					jumpBranch(offset);
				}
				break;
			case 0xf1: // SBC, INDY
				ZPAddr = popByte();
				realAddr = memory.getWord(ZPAddr)+regY;
				value = memory.getByte(realAddr);
				testSBC(value);
				break;
			case 0xf5: // SBC, ZPX
				realAddr = (popByte() + regX)&0xff;
				value = memory.getByte(realAddr);
				testSBC(value);
				break;
			case 0xf6: // INC, ZPX
				ZPAddr = popByte();
				realAddr = ZPAddr + regX;
				beforeINC = memory.cells[realAddr];
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = afterINC;
				break;
			case 0xf8: // SED, SNGL
				regP |= 8;
				break;
			case 0xf9: // SBC, ABSY
				realAddr = popWord()+regY;
				value = memory.getByte(realAddr);
				testSBC(value);
				break;
			case 0xfd: // SBC, ABSX
				realAddr = popWord()+regX;
				value = memory.getByte(realAddr);
				testSBC(value);
				break;
			case 0xfe: // INC, ABSX
				ABSAddr = popWord();
				realAddr = ABSAddr + regX;
				beforeINC = memory.cells[realAddr];
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = afterINC;
				break;
			default:
				break;
			}

			MainActivity.showTextViewA(regA);
			MainActivity.showTextViewX(regX);
			MainActivity.showTextViewY(regY);
			MainActivity.showTextViewSP(regSP);
			MainActivity.showTextViewPC(regPC);
			MainActivity.showTextViewP(regP);
		} else {
			MainActivity
					.showTextViewInfo("Debugger has reached the end of the code!");
		}

		ppu.invalidate();

	}

	public void runAll() {
		while (memory.cells[regPC] > 0) {
			runStep();
		}
	}

	public void reset() {
		regA = 0;
		regX = 0;
		regY = 0;
		regPC = 0x600;
	}

	private int popWord() {
		return popByte() + (popByte() << 8);
	}

	private int popByte() {
		return memory.cells[regPC++];
	}

	private int overFlowSet() {
		return regP & 0x40;
	}

	private int zeroSet() {
		return regP & 0x02;
	}

	private int decimalMode() {
		return regP & 8;
	}

	private int carrySet() {
		return regP & 1;
	}

	private int negativeSet() {
		return regP & 0x80;
	}

	private void jumpBranch(int offset) {
		if (offset > 0x7f) {
			regPC = (regPC - 1 - (0x100 - offset));
		} else {
			regPC += offset - 1;
		}
	}

	private void setCarryFlagFromBit0(int value) {
		regP = (regP & 0xfe) | ((value >> 7) & 1);
	}

	private void setCarryFlagFromBit7(int value) {
		regP = (regP & 0xfe) | ((value >> 7) & 1);
	}

	private void setNVFlags(int value) {
		if (value > 0) {
			regP &= 0xfd;
		} else {
			regP |= 0x02;
		}
		if ((value & 0x80) > 0) {
			regP |= 0x80;
		} else {
			regP &= 0x7f;
		}
	}

	private int stackPop() {
		regSP++;
		if (regSP > 0x1ff) {
			regSP = 0x1ff;
			MainActivity.showTextViewInfo("stack empty!");
		}
		return memory.cells[regSP];
	}

	private void stackPush(int value) {
		memory.cells[regSP] = value;
		regSP--;
		if (regSP < 0x100) {
			regSP = 0x100;
			MainActivity.showTextViewInfo("stack overflow!");
		}
	}

	private void doCompare(int reg, int val) {
		if (reg >= val) {
			regP |= 1;
		} else {
			regP &= 0xfe;
		}
		val = reg - val;
		setNVFlags(val);
	}

	private void testSBC(int value) {
		int tmp, w;
		if (((regA ^ value) & 0x80) > 0) {
			regP |= 0x40;
		} else {
			setNVFlags(regA); // clv
		}
		if (decimalMode()>0) {
			tmp = 0xf + (regA & 0xf) - (value & 0xf) + carrySet();
			if (tmp < 0x10) {
				w = 0;
				tmp -= 6;
			} else {
				w = 0x10;
				tmp -= 0x10;
			}
			w += 0xf0 + (regA & 0xf0) - (value & 0xf0);
			if (w < 0x100) {
				regP &= 0xfe;
				if (overFlowSet()>0 && w < 0x80) {
					regP &= 0xbf;
				}
				w -= 0x60;
			} else {
				regP &= 1;
				if (overFlowSet()>0 && w >= 0x180) {
					regP &= 0xbf;
				}
			}
			w += tmp;
		} else {
			w = 0xff + regA - value + carrySet();
			if (w < 0x100) {
				regP &= 0xfe;
				if (overFlowSet()>0 && w < 0x80) {
					regP &= 0xbf;
				}
			} else {
				regP |= 1;
				if (overFlowSet()>0 && w >= 0x180) {
					regP &= 0xbf;
				}
			}
		}
		regA = w & 0xff;
		setNVFlags(regA);

	}

}
