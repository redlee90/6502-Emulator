package com.redlee90.emulator6502;

public class VM {
	private String regA;
	private String regX;
	private String regY;
	// private String regP;
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

			switch (command) {
			case "00": // BRK, SNGL
				break;
			case "61": // ADC, INDX
				ZPAddr = getZPAddr();
				valueX = getValueX();
				INDAddr = ZPAddr + valueX;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr], 16);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1], 16);
				realAddr = INDAddrPart1 + INDAddrPart2 << 8;
				beforeADC = Integer.parseInt(regA,16);
				addend = Integer.parseInt(memory.cells[realAddr],16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "65": // ADC, ZP
				realAddr = getZPAddr();
				beforeADC = Integer.parseInt(regA,16);
				addend = Integer.parseInt(memory.cells[realAddr],16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "69": // ADC, Imm
				beforeADC = Integer.parseInt(regA,16);
				addend = Integer.parseInt(memory.cells[regPC++],16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "6d": // ADC, ABS
				realAddr = getABSAddr();
				beforeADC = Integer.parseInt(regA,16);
				addend = Integer.parseInt(memory.cells[realAddr],16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "71": // ADC, INDY
				ZPAddr = getZPAddr();
				valueY = getValueY();
				INDAddr = ZPAddr;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr]);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1]);
				realAddr = INDAddrPart1 + valueY + INDAddrPart2 << 8;
				beforeADC = Integer.parseInt(regA,16);
				addend = Integer.parseInt(memory.cells[realAddr],16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "75": // ADC, ZPX
				ZPAddr =getZPAddr();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				beforeADC = Integer.parseInt(regA,16);
				addend = Integer.parseInt(memory.cells[realAddr],16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;
			case "79": // ADC, ABSY
				ABSAddr = getABSAddr();
				valueY = getValueY();
				realAddr = ABSAddr + valueY;
				beforeADC = Integer.parseInt(regA,16);
				addend = Integer.parseInt(memory.cells[realAddr],16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;		
			case "7d": // ADC, ABSX
				ABSAddr = getABSAddr();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				beforeADC = Integer.parseInt(regA,16);
				addend = Integer.parseInt(memory.cells[realAddr],16);
				afterADC = beforeADC + addend;
				regA = Integer.toHexString(afterADC);
				MainActivity.showEditTextA(regA);
				break;			
			case "81": // STA, INDX
				ZPAddr = getZPAddr();
				valueX = getValueX();
				INDAddr = ZPAddr + valueX;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr], 16);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1], 16);
				realAddr = INDAddrPart1 + INDAddrPart2 << 8;
				memory.cells[realAddr] = regA;
				break;			
			case "85": // STA, ZP
				realAddr = getZPAddr();
				memory.cells[realAddr] = regA;				
				break;
			case "88": // DEY, SNGL
				regY = Integer.toHexString(Integer.parseInt(regY,16)-1);
				break;
			case "8a": // TXA, SNGL
				regA = regX;
				MainActivity.showEditTextA(regA);
				break;				
			case "8d": //STA, ABS
				realAddr = getABSAddr();
				memory.cells[realAddr] = regA;
				break;
			case "91": //STA, INDY
				ZPAddr = getZPAddr();
				valueY = getValueY();
				INDAddr = ZPAddr;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr]);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1]);
				realAddr = INDAddrPart1 + valueY + INDAddrPart2 << 8;
				memory.cells[realAddr] = regA;
				break;			
			case "95": // STA, ZPX
				ZPAddr = getZPAddr();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				memory.cells[realAddr] = regA;
				break;
			case "98": // TYA, SNGL
				regA = regY;
				MainActivity.showEditTextA(regA);
				break;	
			case "99": // STA, ABSY
				ABSAddr = getABSAddr();
				valueY = getValueY();
				realAddr = ABSAddr + valueY;
				memory.cells[realAddr] = regA;
				break;
			case "9d": // STA, ABSX
				ABSAddr = getABSAddr();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				memory.cells[realAddr] = regA;
				break;
			case "a0": // LDY, Imm
				regY = memory.cells[regPC++];
				MainActivity.showEditTextY(regY);
				break;
			case "a1": // LDA, INDX
				ZPAddr = getZPAddr();
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
				realAddr = getZPAddr();
				regY = getValueFromMem(realAddr);
				MainActivity.showEditTextY(regY);
				break;
			case "a5":
				regA = memory.cells[Integer.parseInt(memory.cells[regPC++], 16)];
				MainActivity.showEditTextA(regA);
				break;
			case "a6": // LDX, ZP
				realAddr = getZPAddr();
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
				realAddr = getABSAddr();
				regY = getValueFromMem(realAddr);
				MainActivity.showEditTextY(regY);
				break;
			case "ad": // LDA, ABS
				realAddr = getABSAddr();
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "ae": // LDX, ABS
				realAddr = getABSAddr();
				regX = getValueFromMem(realAddr);
				MainActivity.showEditTextX(regX);
				break;				
			case "b1": // LDA, INDY
				ZPAddr = getZPAddr();
				valueY = getValueY();
				INDAddr = ZPAddr;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr]);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1]);
				realAddr = INDAddrPart1 + valueY + INDAddrPart2 << 8;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "b4": // LDY, ZPX
				ZPAddr = getZPAddr();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				regY = getValueFromMem(realAddr);
				MainActivity.showEditTextY(regY);
				break;
			case "b5": // LDA, ZPX
				ZPAddr = getZPAddr();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "b6": // LDX, ZPY
				ZPAddr = getZPAddr();
				valueY = getValueY();
				realAddr = ZPAddr + valueY;
				regX = getValueFromMem(realAddr);
				MainActivity.showEditTextX(regX);
				break;
			case "b9": // LDA, ABSY
				ABSAddr = getABSAddr();
				valueY = getValueY();
				realAddr = ABSAddr + valueY;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "bc": // LDY, ABSX
				ABSAddr = getABSAddr();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				regY = getValueFromMem(realAddr);
				MainActivity.showEditTextY(regY);
				break;
			case "bd": // LDA, ABSX
				ABSAddr = getABSAddr();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "be": // LDX, ABSY
				ABSAddr = getABSAddr();
				valueY = getValueY();
				realAddr = ABSAddr + valueY;
				regX = getValueFromMem(realAddr);
				MainActivity.showEditTextX(regX);
				break;
			case "c8": // INY, SNGL
				regY = Integer.toHexString(Integer.parseInt(regY,16)+1);
				break;
			case "ca": // DEX, SNGL
				regX = Integer.toHexString(Integer.parseInt(regX,16)-1);
				break;			
			case "e6": // INC, ZP
				realAddr = getZPAddr();
				beforeINC = Integer.parseInt(memory.cells[realAddr],16);
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = Integer.toHexString(afterINC);
				break;
			case "e8": // INX, SNGL
				regX = Integer.toHexString(Integer.parseInt(regX,16)+1);
				MainActivity.showEditTextX(regX);
				break;
			case "ee": //INC, ABS
				realAddr = getABSAddr();
				beforeINC = Integer.parseInt(memory.cells[realAddr],16);
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = Integer.toHexString(afterINC);
				break;				
			case "f6": // INC, ZPX
				ZPAddr = getZPAddr();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				beforeINC = Integer.parseInt(memory.cells[realAddr],16);
				afterINC = beforeINC + 1;
				memory.cells[realAddr] = Integer.toHexString(afterINC);
				break;	
			case "fe": //INC, ABSX
				ABSAddr = getABSAddr();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				beforeINC = Integer.parseInt(memory.cells[realAddr],16);
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

	private int getABSAddr() {
		return Integer.parseInt(memory.cells[regPC++], 16)
				+ (Integer.parseInt(memory.cells[regPC++], 16) << 8);
	}

	private int getZPAddr() {
		return Integer.parseInt(memory.cells[regPC++], 16);
	}

	private int getValueX() {
		return Integer.parseInt(regX, 16);
	}

	private int getValueY() {
		return Integer.parseInt(regY, 16);
	}

}
