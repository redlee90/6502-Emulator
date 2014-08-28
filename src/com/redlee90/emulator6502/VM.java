package com.redlee90.emulator6502;

public class VM {
	private String regA;
	private String regX;
	private String regY;
	// private String regP;
	private int regPC = 0x600;
	// private int regSP = 0x00ff;
	private Memory memory;

	public VM(Memory memory) {
		this.memory = memory;
	}

	public void runStep() {
		if (memory.cells[regPC] != null) {
			String command = this.memory.cells[regPC++];
			System.out.println("command is " + command);
			int realAddr;
			int ZPAddr;
			int ABSAddr;
			int INDAddr;
			int INDAddrPart1;
			int INDAddrPart2;
			int valueX;
			int valueY;

			switch (command) {
			case "85":
				memory.cells[Integer.parseInt(memory.cells[regPC++], 16)] = regA;
				break;
			case "8d": //STA, ABS
				realAddr = getABSAddr();
				memory.cells[realAddr] = regA;
				break;
			case "a0":
				regY = memory.cells[regPC++];
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

			case "a2":
				regX = memory.cells[regPC++];
				MainActivity.showEditTextA(regA);

				break;
			case "a5":
				regA = memory.cells[Integer.parseInt(memory.cells[regPC++], 16)];
				MainActivity.showEditTextA(regA);
				break;
			case "a9": // LDA, Imm
				regA = memory.cells[regPC++];
				MainActivity.showEditTextA(regA);
				break;
			case "ad": // LDA, ABS
				ABSAddr = getABSAddr();
				realAddr = ABSAddr;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "b1": // LDA, INDY
				ZPAddr = getZPAddr();
				valueY = getValueY();
				INDAddr = ZPAddr;
				INDAddrPart1 = Integer.parseInt(memory.cells[INDAddr]);
				INDAddrPart2 = Integer.parseInt(memory.cells[INDAddr + 1]);
				realAddr = INDAddrPart1 + 1 + INDAddrPart2 << 8;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);

			case "b5": // LDA, ZPX
				ZPAddr = getZPAddr();
				valueX = getValueX();
				realAddr = ZPAddr + valueX;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "b9": // LDA, ABSY
				ABSAddr = getABSAddr();
				valueY = getValueY();
				realAddr = ABSAddr + valueY;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			case "bd": // LDA, ABSX
				ABSAddr = getABSAddr();
				valueX = getValueX();
				realAddr = ABSAddr + valueX;
				regA = getValueFromMem(realAddr);
				MainActivity.showEditTextA(regA);
				break;
			default:
				break;
			}

			MainActivity.showEditTextPC("0x" + Integer.toHexString(regPC));
		} else {
			MainActivity
					.showTextViewInfo("Debugger has reached the end of the code!");
		}
		
		for (int i=0;i<=0x3ff;i++) {
			int location = 0x200+i;
			if (memory.cells[location]!=null) {
				int color = Integer.parseInt(memory.cells[location],16);
				PPU.drawDot(MainActivity.c, location, color);
			}
		}

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
