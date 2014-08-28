package com.redlee90.emulator6502;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class PPU extends View {

	public static int cellWidth;
	public static int cellHeight;
	private int rowNum = 32;
	private static int colNum = 32;
	Paint myPaint = new Paint();

	public PPU(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		cellWidth = getWidth() / colNum;
		cellHeight = getHeight() / rowNum;

		myPaint.setColor(Color.BLACK);
		canvas.drawRect(0, 0, getWidth(), getHeight(), myPaint);
	}

	/*
	 * Memory locations $200 to $5ff (1032 locations) map to screen pixels.
	 * Different values will draw different colour pixels, The colors are $0:
	 * blcak $1: white $2: Red $3: Cyan $4: Purple $5: Green $6: Blue $7: Yellow
	 * $8: Orange $9: Brown $a: Light red $b: Dark grey $c: Grey $d: Light green
	 * $e: light blue $f: light grey
	 */

	public static void drawDot(Canvas canvas, int location, int color) {
		int x = (location-0x200)/colNum;
		int y = (location-0x200)%colNum;
		Paint paint = new Paint();
		
		switch (color) {
		case 0x0: // black
			paint.setColor(Color.BLACK);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0x1: // white
			System.out.println("I am going to draw white at "+location);
			paint.setColor(Color.WHITE);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, x*cellWidth+cellWidth,
					y*cellHeight+cellHeight), paint);
			break;
		case 0x2: // red
			paint.setColor(Color.RED);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0x3: // cyan
			paint.setColor(Color.CYAN);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0x4: // purple
			paint.setColor(Color.MAGENTA);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0x5: // green
			paint.setColor(Color.GREEN);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0x6: // blue
			paint.setColor(Color.BLUE);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0x7: // yellow
			paint.setColor(Color.YELLOW);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0x8: // orange
			paint.setColor(Color.TRANSPARENT);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0x9: // brow
			paint.setColor(Color.TRANSPARENT);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0xa: // light red
			paint.setColor(Color.TRANSPARENT);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0xb: // dark gray
			paint.setColor(Color.DKGRAY);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0xc: // gray
			paint.setColor(Color.GRAY);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0xd: // light gree
			paint.setColor(Color.TRANSPARENT);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0xe: // light blue
			paint.setColor(Color.TRANSPARENT);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		case 0xf: // light gray
			paint.setColor(Color.TRANSPARENT);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, cellWidth,
					cellHeight), paint);
			break;
		default:
			break;

		}
	}
}
