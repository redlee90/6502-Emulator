package com.redlee90.emulator6502;

public class VM {
	private String regA;
	private String regX;
	private String regY;
	private String regP;
	private int regPC = 0x600;
	private int regSP = 0xff;
	private Memory memory;
	private PrintOutManager pm;
	
	public VM (Memory memory, PrintOutManager pm) {
		this.memory = memory; 
		this.pm = pm;
	}
	
	public void run() {
		String command = this.memory.cells[regPC++];
		switch (command) {
		case "85":
			this.memory.cells[Integer.parseInt(this.memory.cells[regPC++],16)] = this.regA;
			break;
		case "a0":
			this.regY = this.memory.cells[regPC++];
			break;
		case "a2":
			this.regX = this.memory.cells[regPC++];
			break;	
		case "a5":
			this.regA = this.memory.cells[Integer.parseInt(this.memory.cells[regPC++],16)];
			pm.showEditTextA(this.regA);
			break;
		case "a9":
			this.regA = this.memory.cells[regPC++];
			break;		
		default:
			break;
		
		}
				
	}
	

}
