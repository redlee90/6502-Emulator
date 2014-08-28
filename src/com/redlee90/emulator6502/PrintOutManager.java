package com.redlee90.emulator6502;

import com.redlee90.Emulator.R;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

public class PrintOutManager extends Activity {
	private EditText editTextA;
	private EditText editTextX;
	private EditText editTextY;
	private TextView textViewInfo;
	
	
	public PrintOutManager () {
		this.editTextA = (EditText) findViewById(R.id.EditTextA);
		this.editTextX = (EditText) findViewById(R.id.EditTextX);
		
		
	}
	
	public void showEditTextA (String message) {
		this.editTextA.setText(message);
	}
	
	public void showEditTextX (String message) {
		this.editTextX.setText(message);
	}

}
