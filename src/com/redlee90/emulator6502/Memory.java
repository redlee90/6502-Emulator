package com.redlee90.emulator6502;

import java.util.Arrays;

public class Memory {
	private int cells[];

	public Memory() {
		this.cells = new int[0xffff];
		Arrays.fill(cells, -1);
	}

	public void reset() {
		Arrays.fill(cells, -1);
	}
	
	public int getByte(int addr) {
		return this.cells[addr];
	}
	
	public int getWord(int addr) {
		return this.cells[addr] + (this.cells[addr+1]<<8);
	}
	
	public void setByte(int addr, int value) {
		this.cells[addr] = value;
	}
	
}
