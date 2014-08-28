package com.redlee90.emulator6502;

public class Memory {
	public String cells[];

	public Memory() {
		this.cells = new String[0xffff];
	}

	public void reset() {
		for (int i = 0; i < 0xffff; i++) {
			this.cells[i] = null;
		}
	}
}
