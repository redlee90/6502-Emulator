package com.redlee90.emulator6502;

import com.redlee90.Emulator.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button buttonAssemble;
	private Button buttonRun;
	private Button buttonReset;
	private Button buttonHexdump;
	private Button buttonStep;

	private static TextView textViewInfo;

	private EditText editTextCode;
	private static EditText editTextA;
	private static EditText editTextX;
	private static EditText editTextY;
	private static EditText editTextPC;

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

		editTextCode = (EditText) findViewById(R.id.EditTextCode);
		editTextA = (EditText) findViewById(R.id.EditTextA);
		editTextX = (EditText) findViewById(R.id.EditTextX);
		editTextY = (EditText) findViewById(R.id.EditTextY);
		editTextPC = (EditText) findViewById(R.id.EditTextPC);

		checkBoxDebug = (CheckBox) findViewById(R.id.checkBoxDebug);
		
		memory = new Memory();

		ppu = new PPU(this,memory);
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
		vm = new VM(memory,ppu);

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
				editTextA.setText("");
				editTextX.setText("");
				editTextY.setText("");
				editTextPC.setText("");
				textViewInfo.setText("Reset successfully");
			}

		});

		buttonHexdump.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textViewInfo.setText("the size of code is " + assembler.size
						+ "\n");
				for (int i = 0; i < assembler.size; i++) {
					textViewInfo.append(memory.cells[0x600 + i] + "\n");
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

	public static void showEditTextA(String message) {
		editTextA.setText(message);
	}

	public static void showEditTextX(String message) {
		editTextX.setText(message);
	}

	public static void showEditTextY(String message) {
		editTextY.setText(message);
	}

	public static void showEditTextPC(String message) {
		editTextPC.setText(message);
	}

	public static void showTextViewInfo(String message) {
		textViewInfo.setText(message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
