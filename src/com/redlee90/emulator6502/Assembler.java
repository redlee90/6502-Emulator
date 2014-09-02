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
			System.out.println("the " + i + "th line is " + "^" + lines[i]
					+ "$");
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

		// Imm
		if (line.matches(patternImm)) {
			System.out.println(line + " matches Imm");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternImm, "$2 $5"));
			String command = st.nextToken();
			int ImmNum = Integer.parseInt(st.nextToken(), 16);

			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
				size += 2;
				break;
			case "AND":
				this.memory.cells[defaultPC++] = AND.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
				size += 2;
				break;
			case "CMP":
				this.memory.cells[defaultPC++] = CMP.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
				size += 2;
				break;
			case "CPX":
				this.memory.cells[defaultPC++] = CPX.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
				size += 2;
				break;
			case "CPY":
				this.memory.cells[defaultPC++] = CPY.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
				size += 2;
				break;
			case "EOR":
				this.memory.cells[defaultPC++] = EOR.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
				size += 2;
				break;
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
			case "ORA":
				this.memory.cells[defaultPC++] = ORA.get("Imm");
				this.memory.cells[defaultPC++] = ImmNum;
				size += 2;
				break;
			case "SBC":
				this.memory.cells[defaultPC++] = SBC.get("Imm");
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
			int ZPAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "AND":
				this.memory.cells[defaultPC++] = AND.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "ASL":
				this.memory.cells[defaultPC++] = ASL.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "CMP":
				this.memory.cells[defaultPC++] = CMP.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "CPX":
				this.memory.cells[defaultPC++] = CPX.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "CPY":
				this.memory.cells[defaultPC++] = CPY.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "DEC":
				this.memory.cells[defaultPC++] = DEC.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "EOR":
				this.memory.cells[defaultPC++] = EOR.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
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
			case "LSR":
				this.memory.cells[defaultPC++] = LSR.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "ORA":
				this.memory.cells[defaultPC++] = ORA.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "ROL":
				this.memory.cells[defaultPC++] = ROL.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "ROR":
				this.memory.cells[defaultPC++] = ROR.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "SBC":
				this.memory.cells[defaultPC++] = SBC.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "STX":
				this.memory.cells[defaultPC++] = STX.get("ZP");
				this.memory.cells[defaultPC++] = ZPAddr;
				size += 2;
				break;
			case "STY":
				this.memory.cells[defaultPC++] = STY.get("ZP");
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
			int ZPXAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "AND":
				this.memory.cells[defaultPC++] = AND.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "ASL":
				this.memory.cells[defaultPC++] = ASL.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "CMP":
				this.memory.cells[defaultPC++] = CMP.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "DEC":
				this.memory.cells[defaultPC++] = DEC.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "EOR":
				this.memory.cells[defaultPC++] = EOR.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
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
			case "LSR":
				this.memory.cells[defaultPC++] = LSR.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "ORA":
				this.memory.cells[defaultPC++] = ORA.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "ROL":
				this.memory.cells[defaultPC++] = ROL.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "ROR":
				this.memory.cells[defaultPC++] = ROR.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "SBC":
				this.memory.cells[defaultPC++] = SBC.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ZPX");
				this.memory.cells[defaultPC++] = ZPXAddr;
				size += 2;
				break;
			case "STY":
				this.memory.cells[defaultPC++] = STY.get("ZPX");
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
			int ZPYAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "LDX":
				this.memory.cells[defaultPC++] = LDX.get("ZPY");
				this.memory.cells[defaultPC++] = ZPYAddr;
				size += 2;
				break;
			case "STX":
				this.memory.cells[defaultPC++] = STX.get("ZPY");
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
			int ABSAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "AND":
				this.memory.cells[defaultPC++] = AND.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "ASL":
				this.memory.cells[defaultPC++] = ASL.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "CMP":
				this.memory.cells[defaultPC++] = CMP.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "CPX":
				this.memory.cells[defaultPC++] = CPX.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "CPY":
				this.memory.cells[defaultPC++] = CPY.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "DEC":
				this.memory.cells[defaultPC++] = DEC.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "EOR":
				this.memory.cells[defaultPC++] = EOR.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "INC":
				this.memory.cells[defaultPC++] = INC.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "JMP":
				this.memory.cells[defaultPC++] = JMP.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "JSR":
				this.memory.cells[defaultPC++] = JSR.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "LDX":
				this.memory.cells[defaultPC++] = LDX.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "LDY":
				this.memory.cells[defaultPC++] = LDY.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "LSR":
				this.memory.cells[defaultPC++] = LSR.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "ROL":
				this.memory.cells[defaultPC++] = ROL.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "ORA":
				this.memory.cells[defaultPC++] = ORA.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "ROR":
				this.memory.cells[defaultPC++] = ROR.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "SBC":
				this.memory.cells[defaultPC++] = SBC.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "STX":
				this.memory.cells[defaultPC++] = STX.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
				this.size += 3;
				break;
			case "STY":
				this.memory.cells[defaultPC++] = STY.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSAddr >> 8;
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
			int ABSXAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "AND":
				this.memory.cells[defaultPC++] = AND.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "ASL":
				this.memory.cells[defaultPC++] = ASL.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "CMP":
				this.memory.cells[defaultPC++] = CMP.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "DEC":
				this.memory.cells[defaultPC++] = DEC.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "EOR":
				this.memory.cells[defaultPC++] = EOR.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "INC":
				this.memory.cells[defaultPC++] = INC.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "LDY":
				this.memory.cells[defaultPC++] = LDY.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "ROL":
				this.memory.cells[defaultPC++] = ROL.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "LSR":
				this.memory.cells[defaultPC++] = LSR.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "ORA":
				this.memory.cells[defaultPC++] = ORA.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "ROR":
				this.memory.cells[defaultPC++] = ROR.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "SBC":
				this.memory.cells[defaultPC++] = SBC.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
				this.size += 3;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSXAddr >> 8;
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
			int ABSYAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSYAddr >> 8;
				this.size += 3;
				break;
			case "AND":
				this.memory.cells[defaultPC++] = AND.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSYAddr >> 8;
				this.size += 3;
				break;
			case "CMP":
				this.memory.cells[defaultPC++] = CMP.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSYAddr >> 8;
				this.size += 3;
				break;
			case "EOR":
				this.memory.cells[defaultPC++] = EOR.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSYAddr >> 8;
				this.size += 3;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSYAddr >> 8;
				this.size += 3;
				break;
			case "LDX":
				this.memory.cells[defaultPC++] = LDX.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSYAddr >> 8;
				this.size += 3;
				break;
			case "ORA":
				this.memory.cells[defaultPC++] = ORA.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSYAddr >> 8;
				this.size += 3;
				break;
			case "SBC":
				this.memory.cells[defaultPC++] = SBC.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSYAddr >> 8;
				this.size += 3;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr & 0xff;
				this.memory.cells[defaultPC++] = ABSYAddr >> 8;
				this.size += 3;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}

		// IND
		else if (line.matches(patternIND)) {
			System.out.println(line + " matches IND");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternIND, "$2 $6"));
			String command = st.nextToken();
			int INDAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "JMP":
				memory.cells[defaultPC++] = JMP.get("IND");
				memory.cells[defaultPC++] = INDAddr & 0xff;
				memory.cells[defaultPC++] = INDAddr >> 8;
				this.size += 3;
				break;
			default:
				break;
			}
		}

		// INDX
		else if (line.matches(patternINDX)) {
			System.out.println(line + " matches INDX");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternINDX, "$2 $6"));
			String command = st.nextToken();
			int INDXAddr = Integer.parseInt(st.nextToken(), 16);
			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("INDX");
				this.memory.cells[defaultPC++] = INDXAddr;
				this.size += 2;
				break;
			case "AND":
				this.memory.cells[defaultPC++] = AND.get("INDX");
				this.memory.cells[defaultPC++] = INDXAddr;
				this.size += 2;
				break;
			case "CMP":
				this.memory.cells[defaultPC++] = CMP.get("INDX");
				this.memory.cells[defaultPC++] = INDXAddr;
				this.size += 2;
				break;
			case "EOR":
				this.memory.cells[defaultPC++] = EOR.get("INDX");
				this.memory.cells[defaultPC++] = INDXAddr;
				this.size += 2;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("INDX");
				this.memory.cells[defaultPC++] = INDXAddr;
				this.size += 2;
				break;
			case "ORA":
				this.memory.cells[defaultPC++] = ORA.get("INDX");
				this.memory.cells[defaultPC++] = INDXAddr;
				this.size += 2;
				break;
			case "SBC":
				this.memory.cells[defaultPC++] = SBC.get("INDX");
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
			int INDYAddr = Integer.parseInt(st.nextToken());
			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("INDY");
				this.memory.cells[defaultPC++] = INDYAddr;
				this.size += 2;
				break;
			case "AND":
				this.memory.cells[defaultPC++] = AND.get("INDY");
				this.memory.cells[defaultPC++] = INDYAddr;
				this.size += 2;
				break;
			case "CMP":
				this.memory.cells[defaultPC++] = CMP.get("INDY");
				this.memory.cells[defaultPC++] = INDYAddr;
				this.size += 2;
				break;
			case "EOR":
				this.memory.cells[defaultPC++] = EOR.get("INDY");
				this.memory.cells[defaultPC++] = INDYAddr;
				this.size += 2;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("INDY");
				this.memory.cells[defaultPC++] = INDYAddr;
				this.size += 2;
				break;
			case "ORA":
				this.memory.cells[defaultPC++] = ORA.get("INDY");
				this.memory.cells[defaultPC++] = INDYAddr;
				this.size += 2;
				break;
			case "SBC":
				this.memory.cells[defaultPC++] = SBC.get("INDY");
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

		// LAB
		else if (line.matches(patternLAB)) {
			System.out.println(line + " matches LAB");
			String label = line.replaceAll(patternLAB, "$2");
			if (!LAB.containsKey(label)) {
				LAB.put(label, defaultPC);
			} else {
				int offset = defaultPC - LAB.get(label);
				memory.cells[LAB.get(label)] = offset;
			}

		}

		// SNGL
		else if (line.matches(patternSNGL)) {
			System.out.println(line + " matches SNGL");
			String command = line.replaceAll(patternSNGL, "$2");
			switch (command) {
			case "ASL":
				memory.cells[defaultPC++] = ASL.get("SNGL");
				size += 1;
				break;
			case "BRK":
				memory.cells[defaultPC++] = BRK.get("SNGL");
				size += 1;
				break;
			case "CLC":
				memory.cells[defaultPC++] = CLC.get("SNGL");
				size += 1;
				break;
			case "CLD":
				memory.cells[defaultPC++] = CLD.get("SNGL");
				size += 1;
				break;
			case "CLI":
				memory.cells[defaultPC++] = CLI.get("SNGL");
				size += 1;
				break;
			case "CLV":
				memory.cells[defaultPC++] = CLV.get("SNGL");
				size += 1;
				break;
			case "DEX":
				memory.cells[defaultPC++] = DEX.get("SNGL");
				size += 1;
				break;
			case "DEY":
				memory.cells[defaultPC++] = DEY.get("SNGL");
				size += 1;
				break;
			case "INX":
				memory.cells[defaultPC++] = INX.get("SNGL");
				size += 1;
				break;
			case "INY":
				memory.cells[defaultPC++] = INY.get("SNGL");
				size += 1;
				break;
			case "LSR":
				memory.cells[defaultPC++] = LSR.get("SNGL");
				size += 1;
				break;
			case "NOP":
				memory.cells[defaultPC++] = NOP.get("SNGL");
				size += 1;
				break;
			case "PLA":
				memory.cells[defaultPC++] = PLA.get("SNGL");
				size += 1;
				break;
			case "PLP":
				memory.cells[defaultPC++] = PLP.get("SNGL");
				size += 1;
				break;
			case "PHA":
				memory.cells[defaultPC++] = PHA.get("SNGL");
				size += 1;
				break;
			case "PHP":
				memory.cells[defaultPC++] = PHP.get("SNGL");
				size += 1;
				break;
			case "ROL":
				memory.cells[defaultPC++] = ROL.get("SNGL");
				size += 1;
				break;
			case "ROR":
				memory.cells[defaultPC++] = ROR.get("SNGL");
				size += 1;
				break;
			case "RTI":
				memory.cells[defaultPC++] = RTI.get("SNGL");
				size += 1;
				break;
			case "RTS":
				memory.cells[defaultPC++] = RTS.get("SNGL");
				size += 1;
				break;
			case "SEC":
				memory.cells[defaultPC++] = SEC.get("SNGL");
				size += 1;
				break;
			case "SED":
				memory.cells[defaultPC++] = SED.get("SNGL");
				size += 1;
				break;
			case "SEI":
				memory.cells[defaultPC++] = SEI.get("SNGL");
				size += 1;
				break;
			case "TAX":
				memory.cells[defaultPC++] = TAX.get("SNGL");
				size += 1;
				break;
			case "TAY":
				memory.cells[defaultPC++] = TAY.get("SNGL");
				size += 1;
				break;
			case "TSX":
				memory.cells[defaultPC++] = TSX.get("SNGL");
				size += 1;
				break;
			case "TXA":
				memory.cells[defaultPC++] = TXA.get("SNGL");
				size += 1;
				break;
			case "TXS":
				memory.cells[defaultPC++] = TXS.get("SNGL");
				size += 1;
				break;
			case "TYA":
				memory.cells[defaultPC++] = TYA.get("SNGL");
				size += 1;
				break;
			default:
				this.assembleOK = false;
				break;

			}
		}

		// BRA
		else if (line.matches(patternBRA)) {
			System.out.println(line + " matches BRA");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternINDY, "$2 $4"));
			String command = st.nextToken();
			String label = st.nextToken();
			switch (command) {
			case "BCC":
				memory.cells[defaultPC++] = BCC.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = 0x0100 - offset;
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = -1;
				}
				size += 2;
				break;
			case "BCS":
				memory.cells[defaultPC++] = BCS.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = 0x0100 - offset;
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = -1;
				}
				size += 2;
				break;
			case "BEQ":
				memory.cells[defaultPC++] = BEQ.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = 0x0100 - offset;
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = -1;
				}
				size += 2;
				break;
			case "BMI":
				memory.cells[defaultPC++] = BMI.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = 0x0100 - offset;
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = -1;
				}
				size += 2;
				break;
			case "BNE":
				memory.cells[defaultPC++] = BNE.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = 0x0100 - offset;
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = -1;
				}
				size += 2;
				break;
			case "BPL":
				memory.cells[defaultPC++] = BPL.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = 0x0100 - offset;
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = -1;
				}
				size += 2;
				break;
			case "BVC":
				memory.cells[defaultPC++] = BVC.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = 0x0100 - offset;
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = -1;
				}
				size += 2;
				break;
			case "BVS":
				memory.cells[defaultPC++] = BVS.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = 0x0100 - offset;
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = -1;
				}
				size += 2;
				break;
			default:
				break;
			}
		}

		// Syntax error
		else {
			this.assembleOK = false;
		}
	}
}
