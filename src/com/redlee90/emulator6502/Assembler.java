package com.redlee90.emulator6502;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Assembler {

	private int defaultPC = 0x600;
	public int size = 0;
	public boolean assembleOK = true;

	private Memory memory;

	private HashMap<String, String> ADC;
	private HashMap<String, String> BCC;
	private HashMap<String, String> BCS;
	private HashMap<String, String> BEQ;
	private HashMap<String, String> BMI;
	private HashMap<String, String> BNE;
	private HashMap<String, String> BPL;
	private HashMap<String, String> BVC;
	private HashMap<String, String> BVS;
	private HashMap<String, String> BRK;
	private HashMap<String, String> CLC;
	private HashMap<String, String> CLD;
	private HashMap<String, String> CLI;
	private HashMap<String, String> CLV;
	private HashMap<String, String> SEC;
	private HashMap<String, String> SED;
	private HashMap<String, String> SEI;
	private HashMap<String, String> DEX;
	private HashMap<String, String> DEY;
	private HashMap<String, Integer> LAB;
	private HashMap<String, String> LDA;
	private HashMap<String, String> LDX;
	private HashMap<String, String> LDY;
	private HashMap<String, String> INC;
	private HashMap<String, String> INX;
	private HashMap<String, String> INY;
	private HashMap<String, String> STA;
	private HashMap<String, String> STX;
	private HashMap<String, String> STY;
	private HashMap<String, String> TAX;
	private HashMap<String, String> TAY;
	private HashMap<String, String> TXA;
	private HashMap<String, String> TYA;

	public Assembler(Memory memory) {
		this.memory = memory;

		ADC = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("Imm", "69");
				put("ZP", "65");
				put("ZPX", "75");
				put("ABS", "6d");
				put("ABSX", "7d");
				put("ABSY", "79");
				put("INDX", "61");
				put("INDY", "71");
			}
		};

		BCC = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", "90");
			}
		};

		BCS = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", "b0");
			}
		};

		BEQ = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", "f0");
			}
		};

		BMI = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", "30");
			}
		};

		BNE = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", "d0");
			}
		};

		BPL = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", "10");
			}
		};

		BVC = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", "50");
			}
		};

		BVS = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("BRA", "70");
			}
		};

		BRK = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", "00");
			}
		};

		CLC = new HashMap<String, String>() {
			{
				put("SNGL", "18");
			}
		};

		CLD = new HashMap<String, String>() {
			{
				put("SNGL", "d8");
			}
		};

		CLI = new HashMap<String, String>() {
			{
				put("SNGL", "58");
			}
		};

		CLV = new HashMap<String, String>() {
			{
				put("SNGL", "b8");
			}
		};

		SEC = new HashMap<String, String>() {
			{
				put("SNGL", "38");
			}
		};

		SED = new HashMap<String, String>() {
			{
				put("SNGL", "f8");
			}
		};

		SEI = new HashMap<String, String>() {
			{
				put("SNGL", "78");
			}
		};

		DEX = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", "ca");
			}
		};

		DEY = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", "88");
			}
		};

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

		INX = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", "e8");
			}
		};

		INY = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", "c8");
			}
		};

		LAB = new HashMap<String, Integer>();

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

		STX = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", "86");
				put("ZPY", "96");
				put("ABS", "8e");

			}
		};

		STY = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("ZP", "84");
				put("ZPX", "94");
				put("ABS", "8c");

			}
		};

		TAX = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", "aa");
			}
		};

		TAY = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", "a8");
			}
		};

		TXA = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", "8a");
			}
		};

		TYA = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("SNGL", "98");
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
		String patternZP = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{2})($|\\s*;.*)";
		String patternZPX = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{2})(\\s*,\\s*)([X,x])($|\\s*;.*)";
		String patternZPY = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{2})(\\s*,\\s*)([Y,y])($|\\s*;.*)";
		String patternABS = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f0-9]{3,4})($|\\s*;.*)";
		String patternABSX = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{4})(\\s*,\\s*)([X,x])($|\\s*;.*)";
		String patternABSY = "(\\s*)(\\w{3})(\\s*)(\\$)([a-f,0-9]{4})(\\s*,\\s*)([Y,y])($|\\s*;.*)";
		// String patternIND =
		// "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{4})(\\s*\\))(\\s*;?.*)";
		String patternINDX = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{2})(\\s*,\\s*)([X,x])(\\s*\\))($|\\s*;.*)";
		String patternINDY = "(\\s*)(\\w{3})(\\s*)(\\()(\\s*\\$\\s*)([a-f,0-9]{2})(\\s*\\))(\\s*,\\s*)([Y,y])($|\\s*;.*)";
		String patternLAB = "(\\s*)(\\w+)(\\s*)(:)($|\\s*;.*)";
		String patternSNGL = "(\\s*)(\\w{3})($|\\s*;.*)";
		String patternBRA = "(\\s*)(\\w{3})(\\s+)(\\w+)($|\\s*;.*)";

		// Imm
		if (line.matches(patternImm)) {
			System.out.println(line + " matches Imm");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternImm, "$2 $5"));
			String command = st.nextToken();
			String ImmNum = st.nextToken();

			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("Imm");
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
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("ZP");
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
			String ZPXAddr = st.nextToken();
			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("ZPX");
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
			String ZPYAddr = st.nextToken();
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
			String ABSAddr = st.nextToken();
			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(ABSAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0,
						ABSAddr.length() - 2);
				this.size += 3;
				break;
			case "INC":
				this.memory.cells[defaultPC++] = INC.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(ABSAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0,
						ABSAddr.length() - 2);
				this.size += 3;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(ABSAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0,
						ABSAddr.length() - 2);
				this.size += 3;
				break;
			case "LDX":
				this.memory.cells[defaultPC++] = LDX.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(ABSAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0,
						ABSAddr.length() - 2);
				this.size += 3;
				break;
			case "LDY":
				this.memory.cells[defaultPC++] = LDY.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(ABSAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0,
						ABSAddr.length() - 2);
				this.size += 3;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(ABSAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0,
						ABSAddr.length() - 2);
				this.size += 3;
				break;
			case "STX":
				this.memory.cells[defaultPC++] = STX.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(ABSAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0,
						ABSAddr.length() - 2);
				this.size += 3;
				break;
			case "STY":
				this.memory.cells[defaultPC++] = STY.get("ABS");
				this.memory.cells[defaultPC++] = ABSAddr.substring(ABSAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSAddr.substring(0,
						ABSAddr.length() - 2);
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
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr.substring(ABSXAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSXAddr.substring(0,
						ABSXAddr.length() - 2);
				this.size += 3;
				break;
			case "INC":
				this.memory.cells[defaultPC++] = INC.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr.substring(ABSXAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSXAddr.substring(0,
						ABSXAddr.length() - 2);
				this.size += 3;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr.substring(ABSXAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSXAddr.substring(0,
						ABSXAddr.length() - 2);
				this.size += 3;
				break;
			case "LDY":
				this.memory.cells[defaultPC++] = LDY.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr.substring(ABSXAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSXAddr.substring(0,
						ABSXAddr.length() - 2);
				this.size += 3;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ABSX");
				this.memory.cells[defaultPC++] = ABSXAddr.substring(ABSXAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSXAddr.substring(0,
						ABSXAddr.length() - 2);
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
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr.substring(ABSYAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSYAddr.substring(0,
						ABSYAddr.length() - 2);
				this.size += 3;
				break;
			case "LDA":
				this.memory.cells[defaultPC++] = LDA.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr.substring(ABSYAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSYAddr.substring(0,
						ABSYAddr.length() - 2);
				this.size += 3;
				break;
			case "LDX":
				this.memory.cells[defaultPC++] = LDX.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr.substring(ABSYAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSYAddr.substring(0,
						ABSYAddr.length() - 2);
				this.size += 3;
				break;
			case "STA":
				this.memory.cells[defaultPC++] = STA.get("ABSY");
				this.memory.cells[defaultPC++] = ABSYAddr.substring(ABSYAddr
						.length() - 2);
				this.memory.cells[defaultPC++] = ABSYAddr.substring(0,
						ABSYAddr.length() - 2);
				this.size += 3;
				break;
			default:
				this.assembleOK = false;
				break;
			}

		}
		// IND
		/*
		 * else if (line.matches(patternIND)) { System.out.println(line +
		 * " matches IND"); StringTokenizer st = new
		 * StringTokenizer(line.replaceAll( patternIND, "$2 $6")); String
		 * command = st.nextToken(); String INDAddr = st.nextToken();
		 * 
		 * }
		 */

		// INDX
		else if (line.matches(patternINDX)) {
			System.out.println(line + " matches INDX");
			StringTokenizer st = new StringTokenizer(line.replaceAll(
					patternINDX, "$2 $6"));
			String command = st.nextToken();
			String INDXAddr = st.nextToken();
			switch (command) {
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("INDX");
				this.memory.cells[defaultPC++] = INDXAddr;
				this.size += 2;
				break;
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
			case "ADC":
				this.memory.cells[defaultPC++] = ADC.get("INDY");
				this.memory.cells[defaultPC++] = INDYAddr;
				this.size += 2;
				break;
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

		// LAB
		else if (line.matches(patternLAB)) {
			System.out.println(line + " matches LAB");
			String label = line.replaceAll(patternLAB, "$2");
			if (!LAB.containsKey(label)) {
				LAB.put(label, defaultPC);
			} else {
				int offset = defaultPC - LAB.get(label);
				memory.cells[LAB.get(label)] = Integer.toHexString(offset);
			}

		}

		// SNGL
		else if (line.matches(patternSNGL)) {
			System.out.println(line + " matches SNGL");
			String command = line.replaceAll(patternSNGL, "$2");
			switch (command) {
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
			case "TXA":
				memory.cells[defaultPC++] = TXA.get("SNGL");
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
					memory.cells[defaultPC++] = Integer
							.toHexString(0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = null;
				}
				size += 2;
				break;
			case "BCS":
				memory.cells[defaultPC++] = BCS.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = Integer
							.toHexString(0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = null;
				}
				size += 2;
				break;
			case "BEQ":
				memory.cells[defaultPC++] = BEQ.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = Integer
							.toHexString(0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = null;
				}
				size += 2;
				break;
			case "BMI":
				memory.cells[defaultPC++] = BMI.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = Integer
							.toHexString(0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = null;
				}
				size += 2;
				break;
			case "BNE":
				memory.cells[defaultPC++] = BNE.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = Integer
							.toHexString(0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = null;
				}
				size += 2;
				break;
			case "BPL":
				memory.cells[defaultPC++] = BPL.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = Integer
							.toHexString(0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = null;
				}
				size += 2;
				break;
			case "BVC":
				memory.cells[defaultPC++] = BVC.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = Integer
							.toHexString(0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = null;
				}
				size += 2;
				break;
			case "BVS":
				memory.cells[defaultPC++] = BVS.get("BRA");
				if (LAB.containsKey(label)) {
					int offset = defaultPC - LAB.get(label);
					memory.cells[defaultPC++] = Integer
							.toHexString(0x0100 - offset);
				} else {
					LAB.put(label, defaultPC);
					memory.cells[defaultPC++] = null;
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
