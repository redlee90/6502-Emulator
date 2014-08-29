package com.redlee90.emulator6502;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import java.util.logging.*;

public class PPU extends View {

	private int cellWidth;
	private int cellHeight;
	private int numRows = 32;
	private int numCols = 32;
	Paint paint = new Paint();
	private Memory memory;

	public PPU(Context context, Memory memory) {
		super(context);
		this.memory = memory;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		cellWidth = getWidth() / numCols;
		cellHeight = getHeight() / numRows;

		paint.setColor(Color.BLACK);
		canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
		
		for (int i=0;i<=0x3ff;i++) {
			int location = 0x200+i;
			if (memory.cells[location]!=null) {
				int color = Integer.parseInt(memory.cells[location],16);
				drawDot(canvas,location,color,paint);
			}
		}
	}

	/*
	 * Memory locations $200 to $5ff (1032 locations) map to screen pixels.
	 * Different values will draw different colour pixels, The colors are $0:
	 * blcak $1: white $2: Red $3: Cyan $4: Purple $5: Green $6: Blue $7: Yellow
	 * $8: Orange $9: Brown $a: Light red $b: Dark grey $c: Grey $d: Light green
	 * $e: light blue $f: light grey
	 */

	private void drawDot(Canvas canvas, int location, int color, Paint paint) {
		int x = (location-0x200)%numCols;
		int y = (location-0x200)/numRows;
		
		switch (color) {
		case 0x0: // black
			paint.setColor(Color.BLACK);
			canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
					(y+1)*cellHeight), paint);
			break;
		case 0x1: // white
			paint.setColor(Color.WHITE);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0x2: // red
			paint.setColor(Color.RED);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0x3: // cyan
			paint.setColor(Color.CYAN);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0x4: // purple
			paint.setColor(Color.MAGENTA);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0x5: // green
			paint.setColor(Color.GREEN);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0x6: // blue
			paint.setColor(Color.BLUE);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0x7: // yellow
			paint.setColor(Color.YELLOW);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0x8: // orange
			paint.setColor(Color.TRANSPARENT);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0x9: // brow
			paint.setColor(Color.TRANSPARENT);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0xa: // light red
			paint.setColor(Color.TRANSPARENT);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0xb: // dark gray
			paint.setColor(Color.DKGRAY);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0xc: // gray
			paint.setColor(Color.GRAY);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0xd: // light gree
			paint.setColor(Color.TRANSPARENT);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0xe: // light blue
			paint.setColor(Color.TRANSPARENT);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		case 0xf: // light gray
			paint.setColor(Color.TRANSPARENT);
				canvas.drawRect(new Rect(x * cellWidth, y * cellHeight, (x+1)*cellWidth,
										 (y+1)*cellHeight), paint);
			break;
		default:
			break;

		}
	}
}
