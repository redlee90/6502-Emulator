package com.redlee90.emulator6502;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Assembler {

	private int defaultPC = 0x600;
	public int size = 0;
	public boolean assembleOK = false;

	private Memory memory;

	private HashMap<String, String> LDA;
	private HashMap<String, String> STA;

	public Assembler(Memory memory) {
		this.memory = memory;
		LDA = new HashMap<String, String>() {
			{
				put("Imm", "a9");
				put("ZP", "a5");
				put("ZPX", "b5");
				put("ABS", "ad");
				put("ABSX", "bd");
				put("ABSY", "b9");
				put("INDX", "a1");
				put("INDY", "b1");
			}
		};

		STA = new HashMap<String, String>() {
			{
				put("ZP", "85");
				put("ZPX", "95");
				put("ABS", "8d");
				put("ABSX", "9d");
				put("ABSY", "99");
				put("INDX", "81");
				put("INDY", "91");

			}
		};
	}

	private void restore() {
		for (int i = 0; i < 0xffff; i++) {
			memory.cells[i] = null;
		}
		this.size = 0;
		this.assembleOK = false;
	}

	public void assembleCode(String code) {
		restore();
		String lines[] = code.split("\\r?\\n");

		for (int i = 0; i < lines.length; i++) {
			assembleLine(lines[i]);
		}
	}

	private void assembleLine(String line) {
		String patternImm = "(\\s*)(\\w{3})(\\s*)(#\\$)([a-f,0-9]{2})(\\s*;?.*)";
		String patternABS = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{4})(\\s*;?.*)";
		String patternZP = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{2})(\\s*;?.*)";
		String patternZPX = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{2})(\\s*,\\s*)([X,x])(\\s*;?.*)";
		String patternZPY = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{2})(\\s*,\\s*)([Y,y])(\\s*;?.*)";
		String patternABSX = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{4})(\\s*,\\s*)([X,x])(\\s*;?.*)";
		String patternABSY = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{4})(\\s*,\\s*)([Y,y])(\\s*;?.*)";
		String patternIND = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{4})(\\s*\\))(\\s*;?.*)";
		String patternINDX = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{2})(\\s*,\\s*)([X,x])(\\s*\\))(\\s*;?.*)";
		String patternINDY = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{2})(\\s*\\))(\\s*,\\s*)([Y,y])(\\s*;?.*)";

		// Imm
		if (line.matches(patternImm)) {
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternImm, "$2 $5"));
			String command = st.nextToken();
			String ImmNum = st.nextToken();

			switch (command) {
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
			}

		}
		
		// ABS
		else if (line.matches(patternABS)) {
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternABS, "$2 $5"));
			String command = st.nextToken();
			String ABSAddr = st.nextToken();
			switch(command) {
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0,2);
			}
		}
		// ZP
		else if (line.matches(patternZP)) {
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternZP, "$2 $5"));
			String command = st.nextToken();
			String ZPAddr =st.nextToken();
			switch(command) {
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
			}
		}
		
		// ZPX
		else if (line.matches(patternZPX)) {
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternZPX, "$2 $5"));
			String command = st.nextToken();
			String ZPXAddr = st.nextToken();
			switch(comand) {
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				
			case "STA":
				
			}

		}
		
		// ZPY
		else if (line.matches(patternZPY)) {
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternZPY, "$2 $5"));
			String command  = st.nextToken();
			String ZPYAddr = st.nextToken();
			

		}
		
		// ABSX
		else if (line.matches(patternABSX)) {
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternABSX, "$2 $5"));
			String command = st.nextToken();
			String ABSXAddr = st.nextToken();
			
		}
		
		// ABSY
		else if (line.matches(patternABSY)) {
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternABSY, "$2 $5"));
			String command = st.nextToken();
			String ABSYAddr = st.nextToken();
			
		}
		// IND
		else if (line.matches(patternIND)) {
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternIND, "$2 $6"));
			String command  = st.nextToken();
			String INDAddr = st.nextToken();

		}
		
		// INDX
		else if (line.matches(patternINDX)) {
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternINDX, "$2 $6"));
			String command  = st.nextToken();
			String INDXAddr = st.nextToken();
		}
		
		// INDY
		else if (line.matches(patternINDY)) {
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternINDY, "$2 $6"));
			String command  = st.nextToken();
			String INDYAddr = st.nextToken();
		} 
		
		// Syntax error
		else {
			this.assembleOK = false;
		}
	}
}
