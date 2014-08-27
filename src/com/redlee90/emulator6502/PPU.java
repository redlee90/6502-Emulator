package com.redlee90.emulator6502;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class PPU extends View{

	public int cellWidth;
	public int cellHeight;
	Paint myPaint = new Paint();
	public PPU(Context context) {
		super(context);	
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		cellWidth = getWidth()/32;
		cellHeight = getHeight()/32;
		
		myPaint.setColor(Color.BLACK);
        canvas.drawRect(0,0,getWidth(),getHeight(),myPaint);
		}
}
