package com.redlee90.emulator6502;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Assembler {

	private int defaultPC = 0x600;
	public int size = 0;
	public boolean assembleOK=false;
	
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
		this.size=0;
		this.assembleOK=false;
	}

	public void assembleCode(String code) {
		restore();
		String lines[] = code.split("\\r?\\n");

		for (int i = 0; i < lines.length; i++) {
			assembleLine(lines[i]);
		}
	}

	private void assembleLine(String line) {
		String newLine = line.replaceAll("(\\s*)([^;]*)(;\\s*)", "$2");
		newLine = newLine.replace(",", " ");
		StringTokenizer st = new StringTokenizer(newLine);
		int numParam = st.countTokens() - 1;

		String command = st.nextToken();
		System.out.println("command is " + command);
		System.out.println("number of parameters is " + numParam);

		// LDA, STA
		if (command.equals("LDA") || command.equals("STA")) {

			// Imm, ZP, ABS
			if (numParam == 1) {
				String param = st.nextToken();

				// Imm, for LDA only
				if (param.substring(0, 1).equals("#")) {
					memory.cells[defaultPC++] = LDA.get("Imm");
					memory.cells[defaultPC++] = param.substring(2);
					size+=2;
				}

				// ZP or ABS
				else {
					String addr = param.substring(1);
					// if for ZP; else for ABS
					if (addr.length() == 2) {
						if (command.equals("LDA")) {
							memory.cells[defaultPC++] = LDA.get("ZP");
						} else if (command.equals("STA")) {
							memory.cells[defaultPC++] = STA.get("ZP");
						}
						memory.cells[defaultPC++] = addr;
						size+=2;
					} else {
						if (command.equals("LDA")) {
							memory.cells[defaultPC++] = LDA.get("ABS");
						} else if (command.equals("STA")) {
							memory.cells[defaultPC++] = STA.get("ABS");
						}
						memory.cells[defaultPC++] = addr.substring(2);
						memory.cells[defaultPC++] = addr.substring(0, 2);
						size+=3;
					}

				}

			}

			// ZPX, ABSX, ABSY, INDX, INDY
			else if (numParam == 2) {

			}
		}
		assembleOK=true;
	}
}
