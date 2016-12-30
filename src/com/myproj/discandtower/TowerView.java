package com.myproj.discandtower;

import java.util.Stack;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TowerView extends View {
	private Disc[] allDiscs;
	public Stack<Integer> discStack = new Stack<Integer>();
	private int puzzleSize = Disc.MaxDiscSize;
	private int margin_horizontal = 4;
	private int margin_vertical = 8;
	private int padding_disc = 8;
	private int board_height = 4;
	private int width = 0, height = 0;
	private final Paint mPaint = new Paint();
	private Bitmap bmpBoard;
	private boolean isMainTower = false;
	public int tower_id = -1;
	private DiscAndTowerActivity gameActivity;
	private boolean isGameOver = false;
	private int my_x = -1, my_y = -1;

	int[] discWidth = new int[Disc.MaxDiscSize];
	int discHeight = 0;
	double scaleFactor = 0.0;
	XY[] slotXY = new XY[Disc.MaxDiscSize];
	public Rect towerRect = new Rect();
	
	public TowerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public TowerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TowerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isMainTower() {
		return isMainTower;
	}

	public void setMainTower(boolean isMainTower) {
		this.isMainTower = isMainTower;
	}
	
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	public void setActivity(DiscAndTowerActivity gameActivity) {
		this.gameActivity = gameActivity;
	}
	
	public void setPuzzleSize(int puzzleSize, boolean refresh) {
		assert(puzzleSize > 0 && puzzleSize <= Disc.MaxDiscSize);
		this.puzzleSize = puzzleSize;
		allDiscs = new Disc[puzzleSize];
		for(int i = 0; i < puzzleSize; i++) {
			allDiscs[i] = new Disc(i);
		}
		if(refresh) {
			computeMetrics(width, height);
		}
	}
	
	public void loadBoardBitmap() {
		bmpBoard = Bitmap.createBitmap(getWidth() - margin_horizontal, board_height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bmpBoard);
		Drawable drawableBoard = getContext().getResources().getDrawable(R.drawable.board);
		drawableBoard.setBounds(0, 0, getWidth() - margin_horizontal, board_height);
		drawableBoard.draw(canvas);
	}
	
	// Initialize the stack with array (first element at top)
	public void initDiscStack(int[] discs) {
		assert(discs != null);
		assert(discs.length > 0 && discs.length == puzzleSize);
		discStack.clear();
		for(int i = discs.length - 1; i >= 0; i--) {
			assert(discs[i] >= 0 && discs[i] < Disc.MaxDiscSize);
			discStack.push(discs[i]);
		}
		Log.d("TowerView", "discStack: " + discStack.toString());
	}
	
	protected void computeMetrics(int w, int h) {
		if(w == 0 || h == 0) return;

		Drawable[] discDrawable = new Drawable[puzzleSize];
		for(int i = puzzleSize - 1; i >= 0; i--) {
			discDrawable[i] = getContext().getResources().getDrawable(Disc.DiscDrawableRes[i]);
			if(i == puzzleSize - 1) {
				discWidth[i] = (w - 2 * margin_horizontal);
				scaleFactor = ((double)(discWidth[i])) / ((double)(discDrawable[i].getIntrinsicWidth()));
				discHeight = (int)((discDrawable[i].getIntrinsicHeight()) * scaleFactor + 0.5);
				if(discHeight < 20) discHeight = 20;
				Log.d("TowerView", "discWidth=" + discWidth[i]);
				Log.d("TowerView", "discHeight=" + discHeight);
				Log.d("TowerView", "scaleFactor=" + scaleFactor);
			}
			else {
				discWidth[i] = (int)((discDrawable[i].getIntrinsicWidth()) * scaleFactor + 0.5);
				if(discHeight == 0) {
					discHeight = (int)((discDrawable[i].getIntrinsicHeight()) * scaleFactor + 0.5);
					if(discHeight < 20) discHeight = 20;
				}
			}
			allDiscs[i].loadBitmap(discWidth[i], discHeight, discDrawable[i]);
			
			int top = getHeight() - margin_vertical - board_height - (padding_disc / 2);
			top -= (discHeight) * (puzzleSize - i);
			top -= (padding_disc) * (puzzleSize - i - 1);
			if(slotXY[i] == null) slotXY[i] = new XY();
			slotXY[i].x = (getWidth() - discWidth[i]) / 2;
			slotXY[i].y = top;
		}
		
		towerRect.left = 0;
		towerRect.top = slotXY[0].y;
		towerRect.right = w;
		towerRect.bottom = h;
		
		// Adjust vertical margin if necessary
		int total_height = puzzleSize * discHeight + (puzzleSize - 1) * padding_disc;
		assert(total_height <= getHeight());
		if((getHeight() - total_height) < margin_vertical * 2) {
			margin_vertical = (getHeight() - total_height) / 2;
		}
		
		loadBoardBitmap();
		
		if(gameActivity != null) {
			gameActivity.towerViewSizeChanged(discWidth[0], discHeight);
		}
	}
	
	public boolean checkOrder(int[] order) {
		if(discStack.size() != order.length) return false;
		//int[] discorder = new int[order.length];
		for(int i = 0; i < order.length; i++) {
			//discorder[i] = discStack.get(order.length - i - 1);
			//Log.d("TowerView", "Tower " + tower_id + ": " + discorder[i] + ", " + order[i]);
			if(discStack.get(order.length - i - 1) != order[i]) return false;
		}
		return true;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		Log.d("TowerView", "onSizeChanged");
		super.onSizeChanged(w, h, oldw, oldh);
		assert(puzzleSize > 0);

		computeMetrics(w, h);
		
		width = w;
		height = h;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.d("TowerView", "onDraw");
		super.onDraw(canvas);
		if(!discStack.empty()) {
			int numDiscs = discStack.size();
			int yorder = puzzleSize - 1;
			//for(int i = puzzleSize - 1; i >= 0; i--) {
			for(int i = numDiscs - 1; i >= 0; i--) {
				int disc = discStack.get(numDiscs - i - 1);
				// Log.d("TowerView", "i=" + i + " disc=" + disc);
				canvas.drawBitmap(allDiscs[disc].getBitmap(), slotXY[disc].x, slotXY[yorder].y, mPaint);
				yorder--;
			}
		}
		canvas.drawBitmap(bmpBoard, margin_horizontal / 2, getHeight() - margin_vertical - board_height, mPaint);
	}
	
	private class XY {
        public int x;
        public int y;

        public XY() {
        	x = y = 0;
        }
        public XY(int newX, int newY) {
            x = newX;
            y = newY;
        }

        public boolean equals(XY other) {
            if (x == other.x && y == other.y) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "XY: [" + x + "," + y + "]";
        }
    }
/*
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
        float y = event.getY();
        
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("TowerView", "Tower" + tower_id + ": start dragging");
                break;
            case MotionEvent.ACTION_MOVE:
            	Log.d("TowerView", "Tower" + tower_id + ": move (" + x + ", " + y + ")");
                break;
            case MotionEvent.ACTION_UP:
            	Log.d("TowerView", "Tower" + tower_id + ": end dragging");
                break;
        }
		return true;
	}
*/
}
