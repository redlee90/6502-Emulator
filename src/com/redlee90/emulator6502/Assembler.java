package com.redlee90.emulator6502;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Assembler {

	private int defaultPC = 0x600;
	public int size = 0;
	public boolean assembleOK = true;

	private Memory memory;

	private HashMap<String, Integer> ADC;
	private HashMap<String, Integer> AND;
	private HashMap<String, Integer> ASL;
	private HashMap<String, Integer> BCC;
	private HashMap<String, Integer> BCS;
	private HashMap<String, Integer> BEQ;
	private HashMap<String, Integer> BMI;
	private HashMap<String, Integer> BNE;
	private HashMap<String, Integer> BPL;
	private HashMap<String, Integer> BVC;
	private HashMap<String, Integer> BVS;
	private HashMap<String, Integer> BRK;
	private HashMap<String, Integer> CLC;
	private HashMap<String, Integer> CLD;
	private HashMap<String, Integer> CLI;
	private HashMap<String, Integer> CLV;
	private HashMap<String, Integer> CMP;
	private HashMap<String, Integer> CPX;
	private HashMap<String, Integer> CPY;
	private HashMap<String, Integer> DEC;
	private HashMap<String, Integer> DEX;
	private HashMap<String, Integer> DEY;
	private HashMap<String, Integer> EOR;
	private HashMap<String, Integer> INC;
	private HashMap<String, Integer> INX;
	private HashMap<String, Integer> INY;
	private HashMap<String, Integer> JSR;
	private HashMap<String, Integer> JMP;
	private HashMap<String, Integer> LAB;
	private HashMap<String, Integer> LDA;
	private HashMap<String, Integer> LDX;
	private HashMap<String, Integer> LDY;
	private HashMap<String, Integer> LSR;
	private HashMap<String, Integer> NOP;
	private HashMap<String, Integer> ORA;
	private HashMap<String, Integer> PHA;
	private HashMap<String, Integer> PHP;
	private HashMap<String, Integer> PLA;
	private HashMap<String, Integer> PLP;
	private HashMap<String, Integer> ROL;
	private HashMap<String, Integer> ROR;
	private HashMap<String, Integer> RTI;
	private HashMap<String, Integer> RTS;
	private HashMap<String, Integer> SBC;
	private HashMap<String, Integer> SEC;
	private HashMap<String, Integer> SED;
	private HashMap<String, Integer> SEI;
	private HashMap<String, Integer> STA;
	private HashMap<String, Integer> STX;
	private HashMap<String, Integer> STY;
	private HashMap<String, Integer> TAX;
	private HashMap<String, Integer> TAY;
	private HashMap<String, Integer> TSX;
	private HashMap<String, Integer> TXA;
	private HashMap<String, Integer> TXS;
	private HashMap<String, Integer> TYA;

	public Assembler(Memory memory) {
		this.memory = memory;

		ADC = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0x69);
				put("ZP", 0x65);
				put("ZPX", 0x75);
				put("ABS", 0x6d);
				put("ABSX", 0x7d);
				put("ABSY", 0x79);
				put("INDX", 0x61);
				put("INDY", 0x71);
			}
		};

		AND = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0x29);
				put("ZP", 0x25);
				put("ZPX", 0x35);
				put("ABS", 0x2d);
				put("ABSX", 0x3d);
				put("ABSY", 0x39);
				put("INDX", 0x21);
				put("INDY", 0x31);

			}
		};

		ASL = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", 0x06);
				put("ZPX", 0x16);
				put("ABS", 0x0e);
				put("ABSX", 0x1e);
				put("SNGL", 0x0a);

			}
		};

		BCC = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", 0x90);
			}
		};

		BCS = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", 0xb0);
			}
		};

		BEQ = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", 0xf0);
			}
		};

		BMI = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", 0x30);
			}
		};

		BNE = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", 0xd0);
			}
		};

		BPL = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", 0x10);
			}
		};

		BVC = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", 0x50);
			}
		};

		BVS = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", 0x70);
			}
		};

		BRK = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x00);
			}
		};

		CLC = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x18);
			}
		};

		CLD = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0xd8);
			}
		};

		CLI = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x58);
			}
		};

		CLV = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0xb8);
			}
		};

		CMP = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0xc9);
				put("ZP", 0xc5);
				put("ZPX", 0xd5);
				put("ABS", 0xcd);
				put("ABSX", 0xdd);
				put("ABSY", 0xd9);
				put("INDX", 0xc1);
				put("INDY", 0xd1);
			}
		};

		CPX = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0xe0);
				put("ZP", 0xe4);
				put("ABS", 0xec);
			}
		};

		CPY = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0xc0);
				put("ZP", 0xc4);
				put("ABS", 0xcc);
			}
		};

		DEC = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", 0xc6);
				put("ZPX", 0xd6);
				put("ABS", 0xce);
				put("ABSX", 0xde);
			}
		};

		SEC = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x38);
			}
		};

		SED = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0xf8);
			}
		};

		SEI = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x78);
			}
		};

		DEX = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0xca);
			}
		};

		DEY = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x88);
			}
		};

		EOR = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0x49);
				put("ZP", 0x45);
				put("ZPX", 0x55);
				put("ABS", 0x4d);
				put("ABSX", 0x5d);
				put("ABSY", 0x59);
				put("INDX", 0x41);
				put("INDY", 0x51);
			}
		};

		INC = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", 0xe6);
				put("ZPX", 0xf6);
				put("ABS", 0xee);
				put("ABSX", 0xfe);
			}
		};

		INX = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0xe8);
			}
		};

		INY = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0xc8);
			}
		};

		LAB = new HashMap<String, Integer>();

		LDA = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0xa9);
				put("ZP", 0xa5);
				put("ZPX", 0xb5);
				put("ABS", 0xad);
				put("ABSX", 0xbd);
				put("ABSY", 0xb9);
				put("INDX", 0xa1);
				put("INDY", 0xb1);
			}
		};

		LDX = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0xa2);
				put("ZP", 0xa6);
				put("ZPY", 0xb6);
				put("ABS", 0xae);
				put("ABSY", 0xbe);
			}
		};

		LSR = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", 0x46);
				put("ZPX", 0x56);
				put("ABS", 0x4e);
				put("ABSX", 0x5e);
				put("SNGL", 0x4a);
			}
		};

		JSR = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ABS", 0x20);
			}
		};

		JMP = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ABS", 0x4c);
				put("IND", 0x6c);
			}
		};

		LDY = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0xa0);
				put("ZP", 0xa4);
				put("ZPX", 0xb4);
				put("ABS", 0xac);
				put("ABSX", 0xbc);
			}

		};

		NOP = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0xea);
			}
		};

		ORA = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0x09);
				put("ZP", 0x05);
				put("ZPX", 0x15);
				put("ABS", 0x0d);
				put("ABSX", 0x1d);
				put("ABSY", 0x19);
				put("INDX", 0x01);
				put("INDY", 0x11);
			}
		};

		PLA = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x68);
			}
		};

		PLP = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x28);
			}
		};

		PHA = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x48);
			}
		};

		PHP = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x08);
			}
		};

		ROL = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", 0x26);
				put("ZPX", 0x36);
				put("ABS", 0x2e);
				put("ABSX", 0x3e);
				put("SNGL", 0x2a);
			}
		};

		ROR = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", 0x66);
				put("ZPX", 0x76);
				put("ABS", 0x6e);
				put("ABSX", 0x7e);
				put("SNGL", 0x6a);
			}
		};

		RTI = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x40);
			}
		};

		RTS = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x60);
			}
		};

		SBC = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", 0xe9);
				put("ZP", 0xe5);
				put("ZPX", 0xf5);
				put("ABS", 0xed);
				put("ABSX", 0xfd);
				put("ABSY", 0xf9);
				put("INDX", 0x81);
				put("INDY", 0x91);
			}
		};

		STA = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", 0x85);
				put("ZPX", 0x95);
				put("ABS", 0x8d);
				put("ABSX", 0x9d);
				put("ABSY", 0x99);
				put("INDX", 0x81);
				put("INDY", 0x91);

			}
		};

		STX = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", 0x86);
				put("ZPY", 0x96);
				put("ABS", 0x8e);

			}
		};

		STY = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", 0x84);
				put("ZPX", 0x94);
				put("ABS", 0x8c);

			}
		};

		TAX = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0xaa);
			}
		};

		TAY = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0xa8);
			}
		};

		TSX = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0xba);
			}
		};

		TXA = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x8a);
			}
		};

		TXS = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x9a);
			}
		};

		TYA = new HashMap<String, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", 0x98);
			}
		};

	}

	public void reset() {
		this.size = 0;
		this.defaultPC = 0x600;
		this.assembleOK = true;
		LAB.clear();
	}

	public void assembleCode(String code) {
		reset();
		String lines[] = code.split("\\r?\\n");

		for (int i = 0; i < lines.length; i++) {
			// // System.out.println("the " + i + "th line is " + "^" + lines[i]
					//+ "$");
			assembleLine(lines[i]);
		}
	}

	private void assembleLine(String line) {
		String patternImm = "(\\s*)(\\w{3})(\\s*)(#\\$)([a-f,0-9]{1,2})($|\\s*;.*)";
		String patternZP = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{1,2})($|\\s*;.*)";
		String patternZPX = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{1,2})(\\s*,\\s*)([X,x])($|\\s*;.*)";
		String patternZPY = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{1,2})(\\s*,\\s*)([Y,y])($|\\s*;.*)";
		String patternABS = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f0-9]{3,4})($|\\s*;.*)";
		String patternABSX = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{3,4})(\\s*,\\s*)([X,x])($|\\s*;.*)";
		String patternABSY = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{3,4})(\\s*,\\s*)([Y,y])($|\\s*;.*)";
		String patternIND = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{3,4})(\\s*\\))($|\\s*;.*)";
		String patternINDX = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{1,2})(\\s*,\\s*)([X,x])(\\s*\\))($|\\s*;.*)";
		String patternINDY = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{1,2})(\\s*\\))(\\s*,\\s*)([Y,y])($|\\s*;.*)";
		String patternLAB = "(\\s*)(\\w+)(\\s*)(:)($|\\s*;.*)";
		String patternSNGL = "(\\s*)(\\w{3})($|\\s*;.*)";
		String patternBRA = "(\\s*)(\\w{3})(\\s+)(\\w+)($|\\s*;.*)";
		String patternEmpty = "\\s*$";

		// Imm
		if (line.matches(patternImm)) {
			// // System.out.println(line + " matches Imm");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternImm, "$2 $5"));
			String command = st.nextToken();
			int ImmNum = Integer.parseInt(st.nextToken(), 16);

			switch (command) {
			case "ADC":
				memory.setByte(defaultPC++, ADC.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			case "AND":
				memory.setByte(defaultPC++, AND.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			case "CMP":
				memory.setByte(defaultPC++, CMP.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			case "CPX":
				memory.setByte(defaultPC++, CPX.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			case "CPY":
				memory.setByte(defaultPC++, CPY.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			case "EOR":
				memory.setByte(defaultPC++, EOR.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			case "LDA":
				memory.setByte(defaultPC++, LDA.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			case "LDX":
				memory.setByte(defaultPC++, LDX.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			case "LDY":
				memory.setByte(defaultPC++, LDY.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			case "ORA":
				memory.setByte(defaultPC++, ORA.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			case "SBC":
				memory.setByte(defaultPC++, SBC.get("Imm"));
				memory.setByte(defaultPC++, ImmNum);
				size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}

		// ZP
		else if (line.matches(patternZP)) {
			// // System.out.println(line + " matches ZP");
			StringTokenizer st = new StringTokenizer(line.replaceAll(patternZP,
					"$2 $5"));
			String command = st.nextToken();
			int ZPAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				memory.setByte(defaultPC++, ADC.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "AND":
				memory.setByte(defaultPC++, AND.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "ASL":
				memory.setByte(defaultPC++, ASL.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "CMP":
				memory.setByte(defaultPC++, CMP.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "CPX":
				memory.setByte(defaultPC++, CPX.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "CPY":
				memory.setByte(defaultPC++, CPY.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "DEC":
				memory.setByte(defaultPC++, DEC.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "EOR":
				memory.setByte(defaultPC++, EOR.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "INC":
				memory.setByte(defaultPC++, INC.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "LDA":
				memory.setByte(defaultPC++, LDA.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "LDX":
				memory.setByte(defaultPC++, LDX.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "LDY":
				memory.setByte(defaultPC++, LDY.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "LSR":
				memory.setByte(defaultPC++, LSR.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "ORA":
				memory.setByte(defaultPC++, ORA.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "ROL":
				memory.setByte(defaultPC++, ROL.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "ROR":
				memory.setByte(defaultPC++, ROR.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "SBC":
				memory.setByte(defaultPC++, SBC.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "STA":
				memory.setByte(defaultPC++, STA.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "STX":
				memory.setByte(defaultPC++, STX.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			case "STY":
				memory.setByte(defaultPC++, STY.get("ZP"));
				memory.setByte(defaultPC++, ZPAddr);
				size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}
		}

		// ZPX
		else if (line.matches(patternZPX)) {
			// // System.out.println(line + " matches ZPX");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternZPX, "$2 $5"));
			String command = st.nextToken();
			int ZPXAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				memory.setByte(defaultPC++, ADC.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "AND":
				memory.setByte(defaultPC++, AND.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "ASL":
				memory.setByte(defaultPC++, ASL.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "CMP":
				memory.setByte(defaultPC++, CMP.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "DEC":
				memory.setByte(defaultPC++, DEC.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "EOR":
				memory.setByte(defaultPC++, EOR.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "INC":
				memory.setByte(defaultPC++, INC.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "LDA":
				memory.setByte(defaultPC++, LDA.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "LDY":
				memory.setByte(defaultPC++, LDY.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "LSR":
				memory.setByte(defaultPC++, LSR.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "ORA":
				memory.setByte(defaultPC++, ORA.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "ROL":
				memory.setByte(defaultPC++, ROL.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "ROR":
				memory.setByte(defaultPC++, ROR.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "SBC":
				memory.setByte(defaultPC++, SBC.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "STA":
				memory.setByte(defaultPC++, STA.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			case "STY":
				memory.setByte(defaultPC++, STY.get("ZPX"));
				memory.setByte(defaultPC++, ZPXAddr);
				size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}

		// ZPY
		else if (line.matches(patternZPY)) {
			// // System.out.println(line + " matches ZPY");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternZPY, "$2 $5"));
			String command = st.nextToken();
			int ZPYAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "LDX":
				memory.setByte(defaultPC++, LDX.get("ZPY"));
				memory.setByte(defaultPC++, ZPYAddr);
				size += 2;
				break;
			case "STX":
				memory.setByte(defaultPC++, STX.get("ZPY"));
				memory.setByte(defaultPC++, ZPYAddr);
				size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}

		// ABS
		else if (line.matches(patternABS)) {
			// System.out.println(line + " matches ABS");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternABS, "$2 $5"));
			String command = st.nextToken();
			int ABSAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				memory.setByte(defaultPC++, ADC.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "AND":
				memory.setByte(defaultPC++, AND.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "ASL":
				memory.setByte(defaultPC++, ASL.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "CMP":
				memory.setByte(defaultPC++, CMP.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "CPX":
				memory.setByte(defaultPC++, CPX.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "CPY":
				memory.setByte(defaultPC++, CPY.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "DEC":
				memory.setByte(defaultPC++, DEC.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "EOR":
				memory.setByte(defaultPC++, EOR.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "INC":
				memory.setByte(defaultPC++, INC.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "JMP":
				memory.setByte(defaultPC++, JMP.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "JSR":
				memory.setByte(defaultPC++, JSR.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "LDA":
				memory.setByte(defaultPC++, LDA.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "LDX":
				memory.setByte(defaultPC++, LDX.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "LDY":
				memory.setByte(defaultPC++, LDY.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "LSR":
				memory.setByte(defaultPC++, LSR.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "ROL":
				memory.setByte(defaultPC++, ROL.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "ORA":
				memory.setByte(defaultPC++, ORA.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "ROR":
				memory.setByte(defaultPC++, ROR.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "SBC":
				memory.setByte(defaultPC++, SBC.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "STA":
				memory.setByte(defaultPC++, STA.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "STX":
				memory.setByte(defaultPC++, STX.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			case "STY":
				memory.setByte(defaultPC++, STY.get("ABS"));
				memory.setByte(defaultPC++, ABSAddr & 0xff);
				memory.setByte(defaultPC++, ABSAddr >> 8);
				this.size += 3;
				break;
			default:
				this.assembleOK = false;
				break;
			}
		}

		// ABSX
		else if (line.matches(patternABSX)) {
			// System.out.println(line + " matches ABSX");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternABSX, "$2 $5"));
			String command = st.nextToken();
			int ABSXAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				memory.setByte(defaultPC++, ADC.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "AND":
				memory.setByte(defaultPC++, AND.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "ASL":
				memory.setByte(defaultPC++, ASL.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "CMP":
				memory.setByte(defaultPC++, CMP.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "DEC":
				memory.setByte(defaultPC++, DEC.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "EOR":
				memory.setByte(defaultPC++, EOR.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "INC":
				memory.setByte(defaultPC++, INC.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "LDA":
				memory.setByte(defaultPC++, LDA.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "LDY":
				memory.setByte(defaultPC++, LDY.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "ROL":
				memory.setByte(defaultPC++, ROL.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "LSR":
				memory.setByte(defaultPC++, LSR.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "ORA":
				memory.setByte(defaultPC++, ORA.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "ROR":
				memory.setByte(defaultPC++, ROR.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "SBC":
				memory.setByte(defaultPC++, SBC.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			case "STA":
				memory.setByte(defaultPC++, STA.get("ABSX"));
				memory.setByte(defaultPC++, ABSXAddr & 0xff);
				memory.setByte(defaultPC++, ABSXAddr >> 8);
				this.size += 3;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}

		// ABSY
		else if (line.matches(patternABSY)) {
			// System.out.println(line + " matches ABSY");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternABSY, "$2 $5"));
			String command = st.nextToken();
			int ABSYAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				memory.setByte(defaultPC++, ADC.get("ABSY"));
				memory.setByte(defaultPC++, ABSYAddr & 0xff);
				memory.setByte(defaultPC++, ABSYAddr >> 8);
				this.size += 3;
				break;
			case "AND":
				memory.setByte(defaultPC++, AND.get("ABSY"));
				memory.setByte(defaultPC++, ABSYAddr & 0xff);
				memory.setByte(defaultPC++, ABSYAddr >> 8);
				this.size += 3;
				break;
			case "CMP":
				memory.setByte(defaultPC++, CMP.get("ABSY"));
				memory.setByte(defaultPC++, ABSYAddr & 0xff);
				memory.setByte(defaultPC++, ABSYAddr >> 8);
				this.size += 3;
				break;
			case "EOR":
				memory.setByte(defaultPC++, EOR.get("ABSY"));
				memory.setByte(defaultPC++, ABSYAddr & 0xff);
				memory.setByte(defaultPC++, ABSYAddr >> 8);
				this.size += 3;
				break;
			case "LDA":
				memory.setByte(defaultPC++, LDA.get("ABSY"));
				memory.setByte(defaultPC++, ABSYAddr & 0xff);
				memory.setByte(defaultPC++, ABSYAddr >> 8);
				this.size += 3;
				break;
			case "LDX":
				memory.setByte(defaultPC++, LDX.get("ABSY"));
				memory.setByte(defaultPC++, ABSYAddr & 0xff);
				memory.setByte(defaultPC++, ABSYAddr >> 8);
				this.size += 3;
				break;
			case "ORA":
				memory.setByte(defaultPC++, ORA.get("ABSY"));
				memory.setByte(defaultPC++, ABSYAddr & 0xff);
				memory.setByte(defaultPC++, ABSYAddr >> 8);
				this.size += 3;
				break;
			case "SBC":
				memory.setByte(defaultPC++, SBC.get("ABSY"));
				memory.setByte(defaultPC++, ABSYAddr & 0xff);
				memory.setByte(defaultPC++, ABSYAddr >> 8);
				this.size += 3;
				break;
			case "STA":
				memory.setByte(defaultPC++, STA.get("ABSY"));
				memory.setByte(defaultPC++, ABSYAddr & 0xff);
				memory.setByte(defaultPC++, ABSYAddr >> 8);
				this.size += 3;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}

		// IND
		else if (line.matches(patternIND)) {
			// System.out.println(line + " matches IND");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternIND, "$2 $6"));
			String command = st.nextToken();
			int INDAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "JMP":
				memory.setByte(defaultPC++, JMP.get("IND"));
				memory.setByte(defaultPC++, INDAddr & 0xff);
				memory.setByte(defaultPC++, INDAddr >> 8);
				this.size += 3;
				break;
			default:
				break;
			}
		}

		// INDX
		else if (line.matches(patternINDX)) {
			// System.out.println(line + " matches INDX");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternINDX, "$2 $6"));
			String command = st.nextToken();
			int INDXAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				memory.setByte(defaultPC++, ADC.get("INDX"));
				memory.setByte(defaultPC++, INDXAddr);
				this.size += 2;
				break;
			case "AND":
				memory.setByte(defaultPC++, AND.get("INDX"));
				memory.setByte(defaultPC++, INDXAddr);
				this.size += 2;
				break;
			case "CMP":
				memory.setByte(defaultPC++, CMP.get("INDX"));
				memory.setByte(defaultPC++, INDXAddr);
				this.size += 2;
				break;
			case "EOR":
				memory.setByte(defaultPC++, EOR.get("INDX"));
				memory.setByte(defaultPC++, INDXAddr);
				this.size += 2;
				break;
			case "LDA":
				memory.setByte(defaultPC++, LDA.get("INDX"));
				memory.setByte(defaultPC++, INDXAddr);
				this.size += 2;
				break;
			case "ORA":
				memory.setByte(defaultPC++, ORA.get("INDX"));
				memory.setByte(defaultPC++, INDXAddr);
				this.size += 2;
				break;
			case "SBC":
				memory.setByte(defaultPC++, SBC.get("INDX"));
				memory.setByte(defaultPC++, INDXAddr);
				this.size += 2;
				break;
			case "STA":
				memory.setByte(defaultPC++, STA.get("INDX"));
				memory.setByte(defaultPC++, INDXAddr);
				this.size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}
		}

		// INDY
		else if (line.matches(patternINDY)) {
			// System.out.println(line + " matches INDY");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternINDY, "$2 $6"));
			String command = st.nextToken();
			int INDYAddr = Integer.parseInt(st.nextToken());
			switch (command) {
			case "ADC":
				memory.setByte(defaultPC++, ADC.get("INDY"));
				memory.setByte(defaultPC++, INDYAddr);
				this.size += 2;
				break;
			case "AND":
				memory.setByte(defaultPC++, AND.get("INDY"));
				memory.setByte(defaultPC++, INDYAddr);
				this.size += 2;
				break;
			case "CMP":
				memory.setByte(defaultPC++, CMP.get("INDY"));
				memory.setByte(defaultPC++, INDYAddr);
				this.size += 2;
				break;
			case "EOR":
				memory.setByte(defaultPC++, EOR.get("INDY"));
				memory.setByte(defaultPC++, INDYAddr);
				this.size += 2;
				break;
			case "LDA":
				memory.setByte(defaultPC++, LDA.get("INDY"));
				memory.setByte(defaultPC++, INDYAddr);
				this.size += 2;
				break;
			case "ORA":
				memory.setByte(defaultPC++, ORA.get("INDY"));
				memory.setByte(defaultPC++, INDYAddr);
				this.size += 2;
				break;
			case "SBC":
				memory.setByte(defaultPC++, SBC.get("INDY"));
				memory.setByte(defaultPC++, INDYAddr);
				this.size += 2;
				break;
			case "STA":
				memory.setByte(defaultPC++, STA.get("INDY"));
				memory.setByte(defaultPC++, INDYAddr);
				this.size += 2;
				break;
			default:
				this.assembleOK = false;
				break;
			}
		}

		// LAB
		else if (line.matches(patternLAB)) {
			// System.out.println(line + " matches LAB");
			String label = line.replaceAll(patternLAB, "$2");
			if (!LAB.containsKey(label)) {
				LAB.put(label, defaultPC);
			} else {
				int offset = defaultPC - LAB.get(label);
				memory.setByte(LAB.get(label), offset);
			}

		}

		// SNGL
		else if (line.matches(patternSNGL)) {
			// System.out.println(line + " matches SNGL");
			String command = line.replaceAll(patternSNGL, "$2");
			switch (command) {
			case "ASL":
				memory.setByte(defaultPC++, ASL.get("SNGL"));
				size += 1;
				break;
			case "BRK":
				memory.setByte(defaultPC++, BRK.get("SNGL"));
				size += 1;
				break;
			case "CLC":
				memory.setByte(defaultPC++, CLC.get("SNGL"));
				size += 1;
				break;
			case "CLD":
				memory.setByte(defaultPC++, CLD.get("SNGL"));
				size += 1;
				break;
			case "CLI":
				memory.setByte(defaultPC++, CLI.get("SNGL"));
				size += 1;
				break;
			case "CLV":
				memory.setByte(defaultPC++, CLV.get("SNGL"));
				size += 1;
				break;
			case "DEX":
				memory.setByte(defaultPC++, DEX.get("SNGL"));
				size += 1;
				break;
			case "DEY":
				memory.setByte(defaultPC++, DEY.get("SNGL"));
				size += 1;
				break;
			case "INX":
				memory.setByte(defaultPC++, INX.get("SNGL"));
				size += 1;
				break;
			case "INY":
				memory.setByte(defaultPC++, INY.get("SNGL"));
				size += 1;
				break;
			case "LSR":
				memory.setByte(defaultPC++, LSR.get("SNGL"));
				size += 1;
				break;
			case "NOP":
				memory.setByte(defaultPC++, NOP.get("SNGL"));
				size += 1;
				break;
			case "PLA":
				memory.setByte(defaultPC++, PLA.get("SNGL"));
				size += 1;
				break;
			case "PLP":
				memory.setByte(defaultPC++, PLP.get("SNGL"));
				size += 1;
				break;
			case "PHA":
				memory.setByte(defaultPC++, PHA.get("SNGL"));
				size += 1;
				break;
			case "PHP":
				memory.setByte(defaultPC++, PHP.get("SNGL"));
				size += 1;
				break;
			case "ROL":
				memory.setByte(defaultPC++, ROL.get("SNGL"));
				size += 1;
				break;
			case "ROR":
				memory.setByte(defaultPC++, ROR.get("SNGL"));
				size += 1;
				break;
			case "RTI":
				memory.setByte(defaultPC++, RTI.get("SNGL"));
				size += 1;
				break;
			case "RTS":
				memory.setByte(defaultPC++, RTS.get("SNGL"));
				size += 1;
				break;
			case "SEC":
				memory.setByte(defaultPC++, SEC.get("SNGL"));
				size += 1;
				break;
			case "SED":
				memory.setByte(defaultPC++, SED.get("SNGL"));
				size += 1;
				break;
			case "SEI":
				memory.setByte(defaultPC++, SEI.get("SNGL"));
				size += 1;
				break;
			case "TAX":
				memory.setByte(defaultPC++, TAX.get("SNGL"));
				size += 1;
				break;
			case "TAY":
				memory.setByte(defaultPC++, TAY.get("SNGL"));
				size += 1;
				break;
			case "TSX":
				memory.setByte(defaultPC++, TSX.get("SNGL"));
				size += 1;
				break;
			case "TXA":
				memory.setByte(defaultPC++, TXA.get("SNGL"));
				size += 1;
				break;
			case "TXS":
				memory.setByte(defaultPC++, TXS.get("SNGL"));
				size += 1;
				break;
			case "TYA":
				memory.setByte(defaultPC++, TYA.get("SNGL"));
				size += 1;
				break;
			default:
				this.assembleOK = false;
				break;

			}
		}

		// BRA
		else if (line.matches(patternBRA)) {
			// System.out.println(line + " matches BRA");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternINDY, "$2 $4"));
			String command = st.nextToken();
			String label = st.nextToken();
			switch (command) {
			case "BCC":
				memory.setByte(defaultPC++, BCC.get("BRA"));
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.setByte(defaultPC++, 0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.setByte(defaultPC++, -1);
				}
				size += 2;
				break;
			case "BCS":
				memory.setByte(defaultPC++, BCS.get("BRA"));
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.setByte(defaultPC++, 0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.setByte(defaultPC++, -1);
				}
				size += 2;
				break;
			case "BEQ":
				memory.setByte(defaultPC++, BEQ.get("BRA"));
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.setByte(defaultPC++, 0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.setByte(defaultPC++, -1);
				}
				size += 2;
				break;
			case "BMI":
				memory.setByte(defaultPC++, BMI.get("BRA"));
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.setByte(defaultPC++, 0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.setByte(defaultPC++, -1);
				}
				size += 2;
				break;
			case "BNE":
				memory.setByte(defaultPC++, BNE.get("BRA"));
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.setByte(defaultPC++, 0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.setByte(defaultPC++, -1);
				}
				size += 2;
				break;
			case "BPL":
				memory.setByte(defaultPC++, BPL.get("BRA"));
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.setByte(defaultPC++, 0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.setByte(defaultPC++, -1);
				}
				size += 2;
				break;
			case "BVC":
				memory.setByte(defaultPC++, BVC.get("BRA"));
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.setByte(defaultPC++, 0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.setByte(defaultPC++, -1);
				}
				size += 2;
				break;
			case "BVS":
				memory.setByte(defaultPC++, BVS.get("BRA"));
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.setByte(defaultPC++, 0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.setByte(defaultPC++, -1);
				}
				size += 2;
				break;
			default:
				break;
			}
		}

		// Syntax error
		else if (line.matches(patternEmpty)){
			// do nothing	
		}
		else {
			this.assembleOK = false;
			MainActivity.showTextViewInfo("Assemble failed");
		}
	}
}
