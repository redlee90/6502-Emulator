package com.redlee90.emulator6502;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Assembler {

	private int defaultPC = 0x600;
	public int size = 0;
	public boolean assembleOK = true;

	private Memory memory;

	private HashMap<String, String> LDA;
	private HashMap<String, String> LDX;
	private HashMap<String, String> LDY;
	private HashMap<String, String> INC;
	private HashMap<String, String> STA;

	public Assembler(Memory memory) {
		this.memory = memory;

		INC = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", "e6");
				put("ZPX", "f6");
				put("ABS", "ee");
				put("ABSX", "fe");
			}
		};

		LDA = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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

		LDX = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", "a2");
				put("ZP", "a6");
				put("ZPY", "b6");
				put("ABS", "ae");
				put("ABSY", "be");
			}
		};

		LDY = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", "a0");
				put("ZP", "a4");
				put("ZPX", "b4");
				put("ABS", "ac");
				put("ABSX", "bc");
			}

		};

		STA = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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

	public void reset() {
		this.size = 0;
		this.defaultPC = 0x600;
		this.assembleOK = false;
	}

	public void assembleCode(String code) {
		reset();
		String lines[] = code.split("\\r?\\n");

		for (int i = 0; i < lines.length; i++) {
			assembleLine(lines[i]);
		}
	}

	private void assembleLine(String line) {
		String patternImm = "(\\s*)(\\w{3})(\\s*)(#\\$)([a-f,0-9]{2})(\\s*;?.*)";
		String patternZP = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{2})([$,\\s*;.*])";
		String patternZPX = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{2})(\\s*,\\s*)([X,x])(\\s*;?.*)";
		String patternZPY = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{2})(\\s*,\\s*)([Y,y])(\\s*;?.*)";
		String patternABS = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{4})([$,\\s*;.*])";
		String patternABSX = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{4})(\\s*,\\s*)([X,x])(\\s*;?.*)";
		String patternABSY = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{4})(\\s*,\\s*)([Y,y])(\\s*;?.*)";
		//String patternIND = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{4})(\\s*\\))(\\s*;?.*)";
		String patternINDX = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{2})(\\s*,\\s*)([X,x])(\\s*\\))(\\s*;?.*)";
		String patternINDY = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{2})(\\s*\\))(\\s*,\\s*)([Y,y])(\\s*;?.*)";

		// Imm
		if (line.matches(patternImm)) {
			System.out.println(line + " matches Imm");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternImm, "$2 $5"));
			String command = st.nextToken();
			String ImmNum = st.nextToken();

			switch (command) {
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
				size += 2;
				break;
			case "LDX":
				this.memory.cells[defaultPC++] = LDX.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
				size += 2;
				break;
			case "LDY":
				this.memory.cells[defaultPC++] = LDY.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
				size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}

		// ZP
		else if (line.matches(patternZP)) {
			System.out.println(line + " matches ZP");
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternZP,
					"$2 $5"));
			String command = st.nextToken();
			String ZPAddr = st.nextToken();
			switch (command) {
			case "INC":
				this.memory.cells[defaultPC++] = INC.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "LDX":
				this.memory.cells[defaultPC++] = LDX.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "LDY":
				this.memory.cells[defaultPC++] = LDY.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}
		}

		// ZPX
		else if (line.matches(patternZPX)) {
			System.out.println(line + " matches ZPX");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternZPX, "$2 $5"));
			String command = st.nextToken();
			String ZPXAddr = st.nextToken();
			switch (command) {
			case "INC":
				this.memory.cells[defaultPC++] = INC.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "LDY":
				this.memory.cells[defaultPC++] = LDY.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}

		// ZPY
		else if (line.matches(patternZPY)) {
			System.out.println(line + " matches ZPY");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternZPY, "$2 $5"));
			String command = st.nextToken();
			String ZPYAddr = st.nextToken();
			switch (command) {
			case "LDX":
				this.memory.cells[defaultPC++] = LDX.get("ZPY");
				this.memory.cells[defaultPC++] = ZPYAddr;
				size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}

		// ABS
		else if (line.matches(patternABS)) {
			System.out.println(line + " matches ABS");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternABS, "$2 $5"));
			String command = st.nextToken();
			String ABSAddr = st.nextToken();
			switch (command) {
			case "INC":
				this.memory.cells[defaultPC++] = INC.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0, 2);
				this.size += 3;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0, 2);
				this.size += 3;
				break;
			case "LDX":
				this.memory.cells[defaultPC++] = LDX.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0, 2);
				this.size += 3;
				break;
			case "LDY":
				this.memory.cells[defaultPC++] = LDY.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0, 2);
				this.size += 3;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0, 2);
				this.size += 3;
				break;
			default:
				this.assembleOK = false;
				break;
			}
		}

		// ABSX
		else if (line.matches(patternABSX)) {
			System.out.println(line + " matches ABSX");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternABSX, "$2 $5"));
			String command = st.nextToken();
			String ABSXAddr = st.nextToken();
			switch (command) {
			case "INC":
				this.memory.cells[defaultPC++] = INC.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSXAddr.substring(0, 2);
				this.size += 3;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSXAddr.substring(0, 2);
				this.size += 3;
				break;
			case "LDY":
				this.memory.cells[defaultPC++] = LDY.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSXAddr.substring(0, 2);
				this.size += 3;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSXAddr.substring(0, 2);
				this.size += 3;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}

		// ABSY
		else if (line.matches(patternABSY)) {
			System.out.println(line + " matches ABSY");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternABSY, "$2 $5"));
			String command = st.nextToken();
			String ABSYAddr = st.nextToken();
			switch (command) {
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSYAddr.substring(0, 2);
				this.size += 3;
				break;
			case "LDX":
				this.memory.cells[defaultPC++] = LDX.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSYAddr.substring(0, 2);
				this.size += 3;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr.substring(2);
				this.memory.cells[defaultPC++] = ABSYAddr.substring(0, 2);
				this.size += 3;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}
		// IND
		/*else if (line.matches(patternIND)) {
			System.out.println(line + " matches IND");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternIND, "$2 $6"));
			String command = st.nextToken();
			String INDAddr = st.nextToken();

		}*/

		// INDX
		else if (line.matches(patternINDX)) {
			System.out.println(line + " matches INDX");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternINDX, "$2 $6"));
			String command = st.nextToken();
			String INDXAddr = st.nextToken();
			switch (command) {
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("INDX");
				this.memory.cells[defaultPC++] = INDXAddr;
				this.size += 2;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("INDX");
				this.memory.cells[defaultPC++] = INDXAddr;
				this.size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}
		}

		// INDY
		else if (line.matches(patternINDY)) {
			System.out.println(line + " matches INDY");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternINDY, "$2 $6"));
			String command = st.nextToken();
			String INDYAddr = st.nextToken();
			switch (command) {
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("INDY");
				this.memory.cells[defaultPC++] = INDYAddr;
				this.size += 2;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("INDY");
				this.memory.cells[defaultPC++] = INDYAddr;
				this.size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}
		}

		// Syntax error
		else {
			this.assembleOK = false;
		}
	}
}
