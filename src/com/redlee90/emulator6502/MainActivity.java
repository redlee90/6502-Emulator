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
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	private Button buttonAssemble;
	private Button buttonRun;
	private Button buttonReset;
	private Button buttonHexdump;
	private Button buttonDisassemble;
	private Button buttonNotes;
	private EditText codeInput;
	private Memory memory;
	private Assembler assembler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		codeInput = (EditText) findViewById(R.id.codeInput);
		buttonAssemble = (Button) findViewById(R.id.buttonAssemble);
		buttonRun = (Button) findViewById(R.id.buttonRun);
		buttonReset = (Button) findViewById(R.id.buttonReset);
		buttonHexdump = (Button) findViewById(R.id.buttonHexdump);
		buttonDisassemble = (Button) findViewById(R.id.buttonDisassemble);
		buttonNotes = (Button) findViewById(R.id.buttonNotes);

		PPU ppu = new PPU(this);
		RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				480, 480);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.BELOW, buttonNotes.getId());
		ppu.setLayoutParams(params);
		ppu.setBackgroundColor(Color.BLUE);
		relativeLayout.addView(ppu);
		Bitmap bitmap = Bitmap.createBitmap(480, 480, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bitmap);
		ppu.draw(c);
		
		memory = new Memory();
		assembler = new Assembler(memory);

		buttonAssemble.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				assembler.assembleCode(codeInput.getText().toString());
			}

		});

		buttonRun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});

		buttonReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});

		buttonHexdump.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("the size of code is "+assembler.size);
				System.out.println(memory.cells[0x0600]);
				System.out.println(memory.cells[0x0601]);
				System.out.println(memory.cells[0x0602]);
				System.out.println(memory.cells[0x0603]);
				System.out.println(memory.cells[0x0604]);
				//System.out.println(memory.cells[0x0605]);
				//System.out.println(memory.cells[0x0606]);
				//System.out.println(memory.cells[0x0607]);
				//System.out.println(memory.cells[0x0608]);								
			}

		});

		buttonDisassemble.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});

		buttonNotes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
