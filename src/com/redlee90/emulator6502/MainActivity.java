package com.redlee90.emulator6502;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.CompoundButton.*;
import com.redlee90.Emulator.*;
import java.io.*;

import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private Button buttonAssemble;
	private Button buttonRun;
	private Button buttonReset;
	private Button buttonHexdump;
	private Button buttonStep;

	private static TextView textViewInfo;
	private static TextView textViewN;
	private static TextView textViewV;
	private static TextView textViewB;
	private static TextView textViewD;
	private static TextView textViewI;
	private static TextView textViewZ;
	private static TextView textViewC;

	private EditText editTextCode;
	private static TextView textViewA;
	private static TextView textViewX;
	private static TextView textViewY;
	private static TextView textViewSP;
	private static TextView textViewPC;

	private CheckBox checkBoxDebug;

	private Memory memory;
	private Assembler assembler;
	private VM vm;
	private PPU ppu;

	public static Canvas c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		buttonAssemble = (Button) findViewById(R.id.buttonAssemble);
		buttonRun = (Button) findViewById(R.id.buttonRun);
		buttonReset = (Button) findViewById(R.id.buttonReset);
		buttonHexdump = (Button) findViewById(R.id.buttonHexdump);
		buttonStep = (Button) findViewById(R.id.buttonStep);

		textViewInfo = (TextView) findViewById(R.id.TextViewInfo);
		textViewN = (TextView) findViewById(R.id.TextViewN);
		textViewV = (TextView) findViewById(R.id.TextViewV);
		textViewB = (TextView) findViewById(R.id.TextViewB);
		textViewD = (TextView) findViewById(R.id.TextViewD);
		textViewI = (TextView) findViewById(R.id.TextViewI);
		textViewZ = (TextView) findViewById(R.id.TextViewZ);
		textViewC = (TextView) findViewById(R.id.TextViewC);

		editTextCode = (EditText) findViewById(R.id.EditTextCode);
		textViewA = (TextView) findViewById(R.id.TextViewA);
		textViewX = (TextView) findViewById(R.id.TextViewX);
		textViewY = (TextView) findViewById(R.id.TextViewY);
		textViewPC = (TextView) findViewById(R.id.TextViewPC);

		checkBoxDebug = (CheckBox) findViewById(R.id.checkBoxDebug);

		memory = new Memory();

		ppu = new PPU(this, memory);
		RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				480, 480);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.BELOW, buttonHexdump.getId());
		ppu.setLayoutParams(params);
		ppu.setBackgroundColor(Color.BLUE);
		relativeLayout.addView(ppu);
		Bitmap bitmap = Bitmap.createBitmap(480, 480, Bitmap.Config.ARGB_8888);
		c = new Canvas(bitmap);
		ppu.draw(c);

		assembler = new Assembler(memory);
		vm = new VM(memory, ppu);

		buttonAssemble.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				assembler.assembleCode(editTextCode.getText().toString());
				if (assembler.assembleOK) {
					textViewInfo.setText("Assemble successfully");
				}
			}

		});

		buttonRun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				vm.runAll();
			}

		});

		buttonReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				memory.reset();
				assembler.reset();
				vm.reset();
				ppu.invalidate();
				textViewA.setText("");
				textViewX.setText("");
				textViewY.setText("");
				textViewPC.setText("");
				textViewInfo.setText("Reset successfully");
			}

		});

		buttonHexdump.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textViewInfo.setText("the size of code is " + assembler.size
						+ "\n");

				for (int i = 0; i < assembler.size; i++) {
					if (i % 16 == 0) {
						textViewInfo.append("0x"
								+ Integer.toHexString(0x600 + i) + ":  ");
					}

					if (memory.cells[0x600 + i].length() == 1) {
						textViewInfo.append("0" + memory.cells[0x600 + i]
								+ "  ");
					} else {
						textViewInfo.append(memory.cells[0x600 + i] + "  ");
					}

					if (i % 16 == 15) {
						textViewInfo.append("\n");
					}
				}
			}

		});

		buttonStep.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				vm.runStep();
			}

		});

		checkBoxDebug.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					buttonStep.setVisibility(View.VISIBLE);
				} else {
					buttonStep.setVisibility(View.INVISIBLE);
				}

			}

		});

	}

	public static void showTextViewA(String message) {
		textViewA.setText(message);
	}

	public static void showTextViewX(String message) {
		textViewX.setText(message);
	}

	public static void showTextViewY(String message) {
		textViewY.setText(message);
	}

	public static void showTextViewPC(String message) {
		textViewPC.setText(message);
	}

	public static void showTextViewInfo(String message) {
		textViewInfo.setText(message);
	}

	public static void showTextViewN(int regP) {
		textViewN.setText(Integer.toBinaryString(regP).substring(0, 1));
	}

	public static void showTextViewV(int regP) {
		textViewV.setText(Integer.toBinaryString(regP).substring(1, 2));
	}

	public static void showTextViewB(int regP) {
		textViewB.setText(Integer.toBinaryString(regP).substring(3, 4));
	}

	public static void showTextViewD(int regP) {
		textViewD.setText(Integer.toBinaryString(regP).substring(4, 5));
	}

	public static void showTextViewI(int regP) {
		textViewI.setText(Integer.toBinaryString(regP).substring(5, 6));
	}

	public static void showTextViewZ(int regP) {
		textViewZ.setText(Integer.toBinaryString(regP).substring(6, 7));
	}

	public static void showTextViewC(int regP) {
		textViewC.setText(Integer.toBinaryString(regP).substring(7, 8));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_help:
			new AlertDialog.Builder(this)
					.setTitle("FYI")
					.setMessage(
							"There is an awesome article on how to program the 6502 chipset by Nick Morgan, would you like to go to that webpage?")
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									Intent browserIntent = new Intent(
											Intent.ACTION_VIEW,
											Uri.parse("http://skilldrick.github.io/easy6502/"));
									startActivity(browserIntent);
								}
							})
					.setNegativeButton(android.R.string.no,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// do nothing
								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();

			return true;
		case R.id.menu_open:

			return true;
		case R.id.menu_save:

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
