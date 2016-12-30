package com.myproj.discandtower;

import java.util.ArrayList;

import com.google.ads.Ad;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DiscAndTowerActivity extends Activity implements View.OnTouchListener {
	public static int NumTowers = 3;
	private static final int LOW_DPI_STATUS_BAR_HEIGHT = 19;
	private static final int MEDIUM_DPI_STATUS_BAR_HEIGHT = 25;
	private static final int HIGH_DPI_STATUS_BAR_HEIGHT = 38;
	
	private TowerView[] towerViews = new TowerView[NumTowers];
	private Button btnExit, btnUndo, btnRestart, btnHelp;
	private int towerFrom = -1, towerTo = -1;
	private ImageView imgDrag;
	private int imgDragWidth = 40;
	private int imgDragHeight = 12;
	private Puzzle[] puzzles;
	private int currentPuzzle = 0;
	private int currentStep = 0;
	private ArrayList<Move> moveHistory = new ArrayList<Move>();
	private TextView txtSteps, txtPuzzle, txtGameOver;
	private boolean isGameOver = false;
	private AdView adView = null;
	private int statusBarHeight = 0;
	private LinearLayout layout_ad;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setFlags(WindowManager.LayoutParams.TYPE_APPLICATION,
//                WindowManager.LayoutParams.TYPE_APPLICATION);
//        getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR,
//                WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.game);

        layout_ad = (LinearLayout)findViewById(R.id.layout_ad);

//        adView = (AdView)this.findViewById(R.id.adView);
//        adView = new AdView(this, AdSize.BANNER, "a14de87ca819bb2");
//        layout_ad.addView(adView);
//        AdRequest adRequest = new AdRequest();
//        adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
//        adView.loadAd(adRequest);

        puzzles = Puzzle.loadPuzzles();
        
        if(savedInstanceState != null) {
        	currentPuzzle = (int)savedInstanceState.getInt("currentPuzzle");
        }
        else {
	        Intent intent = getIntent();
	        currentPuzzle = intent.getIntExtra("puzzle_selected", 0);
        }
        Log.d("DiscAndTowerActivity", "puzzel " + currentPuzzle + " selected");

        towerViews[0] = (TowerView) findViewById(R.id.tower0);
        towerViews[1] = (TowerView) findViewById(R.id.tower1);
        towerViews[2] = (TowerView) findViewById(R.id.tower2);
        //towerViews[0].initDiscStack(new int[]{0,3,1,2});
        towerViews[0].setMainTower(true);
        towerViews[0].setActivity(this);
        
        for(int i = 0; i < NumTowers; i++) {
        	towerViews[i].setPuzzleSize(Disc.MaxDiscSize, true);
        	towerViews[i].tower_id = i;
        }
        
        View layout_towers = findViewById(R.id.layout_towers);
        layout_towers.setOnTouchListener(this);

        txtSteps = (TextView) findViewById(R.id.txtSteps);
        txtPuzzle = (TextView) findViewById(R.id.txtPuzzle);
        txtGameOver = (TextView) findViewById(R.id.txtGameOver);
        
        btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				initGame();
			}
        });
        
        btnUndo = (Button) findViewById(R.id.btnUndo);
        btnUndo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(currentStep <= 0 || moveHistory.isEmpty()) return;
				int histLen = moveHistory.size();
				Move lastMove = moveHistory.get(histLen - 1);
				int discMoved = towerViews[lastMove.to].discStack.pop();
				towerViews[lastMove.from].discStack.push(discMoved);
				moveHistory.remove(histLen - 1);
				currentStep--;
				updateStatus();
				if(moveHistory.isEmpty()) btnUndo.setEnabled(false);
				findViewById(R.id.layout_towers).invalidate();
			}
        });

        imgDrag = (ImageView) findViewById(R.id.img_drag);
        imgDrag.setAlpha(128);
        
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        switch (displayMetrics.densityDpi) {
            case DisplayMetrics.DENSITY_HIGH:
                statusBarHeight = HIGH_DPI_STATUS_BAR_HEIGHT;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                statusBarHeight = MEDIUM_DPI_STATUS_BAR_HEIGHT;
                break;
            case DisplayMetrics.DENSITY_LOW:
                statusBarHeight = LOW_DPI_STATUS_BAR_HEIGHT;
                break;
            default:
                statusBarHeight = MEDIUM_DPI_STATUS_BAR_HEIGHT;
        }
        
        initGame();
        
        //FrameLayout layout_game = (FrameLayout) findViewById(R.id.layout_game);
        //layout_game.requestLayout();
    }
    
    private void retunHomeScreen() {
    	finishActivity(0);
    	finish();
    }
    private void updateStatus() {
    	txtPuzzle.setText(puzzles[currentPuzzle].name);
    	txtSteps.setText(Integer.toString(currentStep));
    }
    
    public void towerViewSizeChanged(int discWidth, int discHeight) {
    	imgDragWidth = discWidth;
    	imgDragHeight = discHeight;
    }
    
    private void initGame() {
    	for(int i = 0; i < NumTowers; i++) {
    		towerViews[i].discStack.clear();
        	towerViews[i].setPuzzleSize(puzzles[currentPuzzle].puzzleSize, true);
        	towerViews[i].tower_id = i;
        	towerViews[i].setGameOver(false);
        	// towerViews[i].invalidate();
        }
    	towerViews[0].initDiscStack(puzzles[currentPuzzle].startOrder);
    	moveHistory.clear();
    	currentStep = 0;
    	isGameOver = false;
    	btnUndo.setEnabled(false);
    	txtGameOver.setVisibility(View.INVISIBLE);
    	updateStatus();
    	findViewById(R.id.layout_towers).invalidate();
    }

    private int getTowerId(int x, int y, View v) {
    	int tower_height = towerViews[0].towerRect.height();
		int w = (v.getWidth());
        int h = (v.getHeight());
        if(y < (h - tower_height) || y >= h) return -1;

        int p1 = (w / 3);
		int p2 = (w * 2 / 3);

		if(x >= 0.0 && x < p1) {
			return 0;
		}
		else if(x >= p1 && x < p2) {
			return 1;
		}
		else if(x >= p2 && x < w) {
			return 2;
		}
		else return -1;
	}
    
    private boolean checkWin() {
    	if(puzzles[currentPuzzle].targetTower < 0) {
    		// Target tower can be anyone
    		for(int i = 0; i < NumTowers; i++) {
    			if(towerViews[i].checkOrder(puzzles[currentPuzzle].targetOrder)) return true;
    		}
    		return false;
    	}
    	else {
    		// Target tower is designated
    		int target_tower = puzzles[currentPuzzle].targetTower;
    		return (towerViews[target_tower].checkOrder(puzzles[currentPuzzle].targetOrder));
    	}
    }
    
    private void moveDisc() {
    	if(towerFrom < 0 || towerTo < 0 || towerFrom == towerTo) return;
    	if(towerViews[towerFrom].discStack.isEmpty()) return;
    	
    	int discToMove = towerViews[towerFrom].discStack.peek();
    	// Cannot put disc on top of a smaller one
    	if(!towerViews[towerTo].discStack.isEmpty() && discToMove > towerViews[towerTo].discStack.peek()) return;

    	// Now it's a valid move, do it
    	towerViews[towerFrom].discStack.pop();
    	towerViews[towerTo].discStack.push(discToMove);
    	// Refresh the views
    	towerViews[towerFrom].invalidate();
    	towerViews[towerTo].invalidate();
    	// Record the move
    	moveHistory.add(new Move(towerFrom, towerTo));
    	currentStep++;
    	if(checkWin()) {
    		// Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
    		isGameOver = true;
    		for(int i = 0; i < NumTowers; i++) towerViews[i].setGameOver(true);
    		btnUndo.setEnabled(false);
    		txtGameOver.setVisibility(View.VISIBLE);
    	}
    	else {
	    	btnUndo.setEnabled(true);
    	}
    	updateStatus();
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(isGameOver) return true;

		int x = (int)event.getX();
        int y = (int)event.getY();
        int tower_id;
        
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            	tower_id = getTowerId(x, y, v);
            	towerFrom = tower_id;
            	Log.d("LayoutTower", "start dragging at tower " + tower_id);
                break;
            case MotionEvent.ACTION_MOVE:
            	int[] loc = new int[2];
            	v.getLocationOnScreen(loc);
            	// v.getLocationInWindow(loc);
            	//imgDrag.setMaxHeight(24);
            	//imgDrag.setMaxWidth(80);
            	int offx = imgDragWidth/2;
            	int offy = imgDragHeight/2;
            	// loc[1] -= statusBarHeight;
            	int ax = loc[0] + x, ay = loc[1] + y;
            	imgDrag.layout(ax - offx, ay - offy, ax + offx, ay + offy);
            	imgDrag.setVisibility(View.VISIBLE);
            	Log.d("LayoutTower", "move (" + x + ", " + y + ") (" + ax + ", " + ay + ") " + loc[0] + ", " + loc[1]);
                break;
            case MotionEvent.ACTION_UP:
            	tower_id = getTowerId(x, y, v);
            	towerTo = tower_id;
            	moveDisc();
            	Log.d("LayoutTower", "end dragging at tower " + tower_id);
            	towerFrom = towerTo = -1;
            	imgDrag.setVisibility(View.INVISIBLE);
                break;
        }
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 1, R.string.btnHelp);
		// menu.add(1, 1, 2, R.string.btnBack);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int gid = item.getGroupId();
		int id = item.getItemId();
		if(id == 1) {
			// Help
			Intent intent = new Intent();
			intent.setClass(DiscAndTowerActivity.this, HelpActivity.class);
			startActivityForResult(intent, 0);
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		finishActivity(requestCode);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d("DiscAndTowerActivity", "onSaveInstanceState");
		super.onSaveInstanceState(outState);
		outState.putInt("currentPuzzle", this.currentPuzzle);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d("DiscAndTowerActivity", "onStart");
        // findViewById(R.id.layout_game).requestLayout();
		
        
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("DiscAndTowerActivity", "onResume");
		if(adView == null) {
			adView = new AdView(this, AdSize.BANNER, "a14de87ca819bb2");
	        layout_ad.addView(adView);
	        AdRequest adRequest = new AdRequest();
	        adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
	        adView.loadAd(adRequest);
		}
//      View layout_game = findViewById(R.id.layout_cursor);
//		int[] loc = new int[2];
//		layout_game.getLocationOnScreen(loc);
//		Log.d("DiscAndTowerActivity", "onResume - (" + loc[0] + ", " + loc[1] + ")");
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
//                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR,
//                WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR);
//		View layout_game = findViewById(R.id.layout_game);
//		int[] loc = new int[2];
//		layout_game.getLocationOnScreen(loc);
//		Log.d("DiscAndTowerActivity", "onResume - in screen (" + loc[0] + ", " + loc[1] + ")");
//		layout_game.getLocationInWindow(loc);
//		Log.d("DiscAndTowerActivity", "onResume - in window (" + loc[0] + ", " + loc[1] + ")");
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Log.d("DiscAndTowerActivity", "onResume: request layout");
        //layout_game.setVisibility(View.INVISIBLE);
//        layout_game.requestLayout();
//		layout_game.invalidate();
	}
	
	private class Move {
		public int from, to;
		public Move(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}

	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
		Log.d("DiscAndTowerActivity", "onPostResume");
		//View layout_game = findViewById(R.id.layout_game);
		//layout_game.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("DiscAndTowerActivity", "onRestart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("DiscAndTowerActivity", "onStop");
		layout_ad.removeView(adView);
		adView.destroy();
		adView = null;
	}

	@Override
	public void onWindowAttributesChanged(LayoutParams params) {
		// TODO Auto-generated method stub
		super.onWindowAttributesChanged(params);
		Log.d("DiscAndTowerActivity", "onWindowAttributesChanged");
	}
}