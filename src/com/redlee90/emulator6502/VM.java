package com.redlee90.emulator6502;

public class VM {
	private String regA;
	private String regX;
	private String regY;
	private int regP = 0;
	private int regPC = 0x600;
	// private int regSP = 0x00ff;
	private Memory memory;
	private PPU ppu;

	public VM(Memory memory, PPU ppu) {
		this.memory = memory;
		this.ppu = ppu;
	}

	public void runStep() {
		if (memory.cells[regPC] != null) {
			String command = this.memory.cells[regPC++];
			int realAddr;
			int ZPAddr;
			int ABSAddr;
			int INDAddr;
			int INDAddrPart1;
			int INDAddrPart2;
			int valueX;
			int valueY;
			int beforeINC;
			int afterINC;
			int beforeADC;
			int addend;
			int afterADC;
			int offset;

			switch (command) {
			case "00": // BRK, SNGL
				break;
			case "10": // BPL, BRA
				offset = popByte();
				if (!negativeSet()) {
					jumpBranch(offset);
				}
				break;
			case "18": // CLC, SNGL
				regP &= 0xfe;
				MainActivity.showTextViewC(regP);
				break;
			case "30": // BMI, BRA
				offset = popByte();
				if (negativeSet()) {
					jumpBranch(offset);
				}
				break;
			case "38": // SEC, SNGL
				regP |= 1;
				MainActivity.showTextViewC(regP);
				break;
			case "50": // BVC, BRA
				offset = popByte();
				if (!overFlowSet()) {
					jumpBranch(offset);
				}
				break;
			case "58": // CLI, SNGL
				regP &= 0x04;
				MainActivity.showTextViewI(regP);
				break;
			case "61": // ADC, INDX
				ZPAddr = popByte();
				valueX = getValueX();
				INDAddr = ZPAddr + valueX;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr], 16);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1], 16);
				realAddr = INDAddrPart1 + INDAddrPart2 << 8;
				beforeADC = Integer.parseInt(regA, 16);
				addend = Integer.parseInt(memory.cells[realAddr], 16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "65": // ADC, ZP
				realAddr = popByte();
				beforeADC = Integer.parseInt(regA, 16);
				addend = Integer.parseInt(memory.cells[realAddr], 16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "69": // ADC, Imm
				beforeADC = Integer.parseInt(regA, 16);
				addend = Integer.parseInt(memory.cells[regPC++], 16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "6d": // ADC, ABS
				realAddr = popWord();
				beforeADC = Integer.parseInt(regA, 16);
				addend = Integer.parseInt(memory.cells[realAddr], 16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "70": // BVS, BRA
				offset = popByte();
				if (overFlowSet()) {
					jumpBranch(offset);
				}
				break;
			case "71": // ADC, INDY
				ZPAddr = popByte();
				valueY = getValueY();
				INDAddr = ZPAddr;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr]);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1]);
				realAddr = INDAddrPart1 + valueY + INDAddrPart2 << 8;
				beforeADC = Integer.parseInt(regA, 16);
				addend = Integer.parseInt(memory.cells[realAddr], 16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "75": // ADC, ZPX
				ZPAddr = popByte();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				beforeADC = Integer.parseInt(regA, 16);
				addend = Integer.parseInt(memory.cells[realAddr], 16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "79": // ADC, ABSY
				ABSAddr = popWord();
				valueY = getValueY();
				realAddr = ABSAddr + valueY;
				beforeADC = Integer.parseInt(regA, 16);
				addend = Integer.parseInt(memory.cells[realAddr], 16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "7d": // ADC, ABSX
				ABSAddr = popWord();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				beforeADC = Integer.parseInt(regA, 16);
				addend = Integer.parseInt(memory.cells[realAddr], 16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "81": // STA, INDX
				ZPAddr = popByte();
				valueX = getValueX();
				INDAddr = ZPAddr + valueX;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr], 16);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1], 16);
				realAddr = INDAddrPart1 + INDAddrPart2 << 8;
				memory.cells[realAddr] = regA;
				break;
			case "78": // SEI, SNGL
				regP |= 0x04;
				MainActivity.showTextViewI(regP);
				break;
			case "84": // STY, ZP
				realAddr = popByte();
				memory.cells[realAddr] = regY;
				break;
			case "85": // STA, ZP
				realAddr = popByte();
				memory.cells[realAddr] = regA;
				break;
			case "86": // STX, ZP
				realAddr = popByte();
				memory.cells[realAddr] = regX;
				break;
			case "88": // DEY, SNGL
				regY = Integer.toHexString(Integer.parseInt(regY, 16) - 1);
				break;
			case "8a": // TXA, SNGL
				regA = regX;
				MainActivity.showEditTextA(regA);
				break;
			case "8c": // STY, ABS
				realAddr = popWord();
				memory.cells[realAddr] = regY;
				break;
			case "8d": // STA, ABS
				realAddr = popWord();
				memory.cells[realAddr] = regA;
				break;
			case "8e": // STX, ABS
				realAddr = popWord();
				memory.cells[realAddr] = regX;
				break;
			case "90": // BCC, BRA
				offset = popByte();
				if (!carrySet()) {
					jumpBranch(offset);
				}
				break;
			case "91": // STA, INDY
				ZPAddr = popByte();
				valueY = getValueY();
				INDAddr = ZPAddr;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr]);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1]);
				realAddr = INDAddrPart1 + valueY + INDAddrPart2 << 8;
				memory.cells[realAddr] = regA;
				break;
			case "94": // STY, ZPX
				ZPAddr = popByte();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				memory.cells[realAddr] = regY;
				break;
			case "95": // STA, ZPX
				ZPAddr = popByte();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				memory.cells[realAddr] = regA;
				break;
			case "96": // STX, ZPY
				ZPAddr = popByte();
				valueY = getValueY();
				realAddr = ZPAddr + valueY;
				memory.cells[realAddr] = regX;
				break;
			case "98": // TYA, SNGL
				regA = regY;
				MainActivity.showEditTextA(regA);
				break;
			case "99": // STA, ABSY
				ABSAddr = popWord();
				valueY = getValueY();
				realAddr = ABSAddr + valueY;
				memory.cells[realAddr] = regA;
				break;
			case "9d": // STA, ABSX
				ABSAddr = popWord();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				memory.cells[realAddr] = regA;
				break;
			case "a0": // LDY, Imm
				regY = memory.cells[regPC++];
				MainActivity.showEditTextY(regY);
				break;
			case "a1": // LDA, INDX
				ZPAddr = popByte();
				valueX = getValueX();
				INDAddr = ZPAddr + valueX;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr], 16);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1], 16);
				realAddr = INDAddrPart1 + INDAddrPart2 << 8;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "a2": // LDX, Imm
				regX = memory.cells[regPC++];
				MainActivity.showEditTextX(regX);
				break;
			case "a4": // LDY, ZP
				realAddr = popByte();
				regY = getValueFromMem(realAddr);
				MainActivity.showEditTextY(regY);
				break;
			case "a5":
				regA = memory.cells[Integer.parseInt(memory.cells[regPC++], 16)];
				MainActivity.showEditTextA(regA);
				break;
			case "a6": // LDX, ZP
				realAddr = popByte();
				regX = getValueFromMem(realAddr);
				MainActivity.showEditTextX(regX);
				break;
			case "a8": // TAY, SNGL
				regY = regA;
				MainActivity.showEditTextY(regY);
				break;
			case "a9": // LDA, Imm
				regA = memory.cells[regPC++];
				MainActivity.showEditTextA(regA);
				break;
			case "aa": // TAX, SNGL
				regX = regA;
				MainActivity.showEditTextX(regX);
				break;
			case "ac": // LDY, ABS
				realAddr = popWord();
				regY = getValueFromMem(realAddr);
				MainActivity.showEditTextY(regY);
				break;
			case "ad": // LDA, ABS
				realAddr = popWord();
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "ae": // LDX, ABS
				realAddr = popWord();
				regX = getValueFromMem(realAddr);
				MainActivity.showEditTextX(regX);
				break;
			case "b0": // BCS, BRA
				offset = popByte();
				if (carrySet()) {
					jumpBranch(offset);
				}
				break;
			case "b1": // LDA, INDY
				ZPAddr = popByte();
				valueY = getValueY();
				INDAddr = ZPAddr;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr]);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1]);
				realAddr = INDAddrPart1 + valueY + INDAddrPart2 << 8;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "b4": // LDY, ZPX
				ZPAddr = popByte();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				regY = getValueFromMem(realAddr);
				MainActivity.showEditTextY(regY);
				break;
			case "b5": // LDA, ZPX
				ZPAddr = popByte();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "b6": // LDX, ZPY
				ZPAddr = popByte();
				valueY = getValueY();
				realAddr = ZPAddr + valueY;
				regX = getValueFromMem(realAddr);
				MainActivity.showEditTextX(regX);
				break;
			case "b8": // CLV, SNGL
				regP &= 0xbf;
				MainActivity.showTextViewV(regP);
				break;
			case "b9": // LDA, ABSY
				ABSAddr = popWord();
				valueY = getValueY();
				realAddr = ABSAddr + valueY;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "bc": // LDY, ABSX
				ABSAddr = popWord();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				regY = getValueFromMem(realAddr);
				MainActivity.showEditTextY(regY);
				break;
			case "bd": // LDA, ABSX
				ABSAddr = popWord();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "be": // LDX, ABSY
				ABSAddr = popWord();
				valueY = getValueY();
				realAddr = ABSAddr + valueY;
				regX = getValueFromMem(realAddr);
				MainActivity.showEditTextX(regX);
				break;
			case "c8": // INY, SNGL
				regY = Integer.toHexString(Integer.parseInt(regY, 16) + 1);
				MainActivity.showEditTextY(regY);
				break;
			case "ca": // DEX, SNGL
				regX = Integer.toHexString(Integer.parseInt(regX, 16) - 1);
				break;
			case "d0": // BNE, BRA
				offset = popByte();
				if (!zeroSet()) {
					jumpBranch(offset);
				}
				break;
			case "d8": // CLD, SNGL
				regP &= 0xf7;
				MainActivity.showTextViewD(regP);
				break;
			case "e6": // INC, ZP
				realAddr = popByte();
				beforeINC = Integer.parseInt(memory.cells[realAddr], 16);
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = Integer.toHexString(afterINC);
				break;
			case "e8": // INX, SNGL
				regX = Integer.toHexString(Integer.parseInt(regX, 16) + 1);
				MainActivity.showEditTextX(regX);
				break;
			case "ee": // INC, ABS
				realAddr = popWord();
				beforeINC = Integer.parseInt(memory.cells[realAddr], 16);
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = Integer.toHexString(afterINC);
				break;
			case "f0": // BEQ, BRA
				offset = popByte();
				if (zeroSet()) {
					jumpBranch(offset);
				}
				break;
			case "f6": // INC, ZPX
				ZPAddr = popByte();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				beforeINC = Integer.parseInt(memory.cells[realAddr], 16);
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = Integer.toHexString(afterINC);
				break;
			case "f8": // SED, SNGL
				regP |= 8;
				MainActivity.showTextViewD(regP);
				break;
			case "fe": // INC, ABSX
				ABSAddr = popWord();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				beforeINC = Integer.parseInt(memory.cells[realAddr], 16);
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = Integer.toHexString(afterINC);
				break;
			default:
				break;
			}

			MainActivity.showEditTextPC("0x" + Integer.toHexString(regPC));
		} else {
			MainActivity
					.showTextViewInfo("Debugger has reached the end of the code!");
		}

		ppu.invalidate();

	}

	public void runAll() {
		while (memory.cells[regPC] != null) {
			runStep();
		}
	}

	public void reset() {
		regA = null;
		regX = null;
		regY = null;
		regPC = 0x600;
	}

	private String getValueFromMem(int realAddr) {
		return Integer
				.toHexString(Integer.parseInt(memory.cells[realAddr], 16));
	}

	private int popWord() {
		return Integer.parseInt(memory.cells[regPC++], 16)
				+ (Integer.parseInt(memory.cells[regPC++], 16) << 8);
	}

	private int popByte() {
		return Integer.parseInt(memory.cells[regPC++], 16);
	}

	private int getValueX() {
		return Integer.parseInt(regX, 16);
	}

	private int getValueY() {
		return Integer.parseInt(regY, 16);
	}

	private boolean overFlowSet() {
		return (regP & 0x40) > 0;
	}

	private boolean zeroSet() {
		return (regP & 0x02) > 0;
	}

	private boolean decimalMode() {
		return (regP & 8) > 0;
	}

	private boolean carrySet() {
		return (regP & 1) > 0;
	}

	private boolean negativeSet() {
		return (regP & 0x80) > 0;
	}

	private void jumpBranch(int offset) {
		if (offset > 0x7f) {
			regPC = (regPC - 1 - (0x100 - offset));
		} else {
			regPC += offset -1 ;
		}
	}

}
