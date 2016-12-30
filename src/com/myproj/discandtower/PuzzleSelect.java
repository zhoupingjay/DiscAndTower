package com.myproj.discandtower;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PuzzleSelect extends Activity {
	GridView puzzle_grid;
	Puzzle[] puzzles;
	public static int iconSize = 64;
	public static int iconPadding = 4;

	public PuzzleSelect() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.puzzleselect);
		
		puzzles = Puzzle.loadPuzzles();
		puzzle_grid = (GridView) findViewById(R.id.puzzle_grid);
		puzzle_grid.setAdapter(new ImageAdapter(this));
		puzzle_grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				// Toast.makeText(PuzzleSelect.this, puzzles[position].name, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.putExtra("puzzle_selected", position);
				intent.setClass(PuzzleSelect.this, DiscAndTowerActivity.class);
				startActivity(intent);
			}

		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 1, R.string.btnHelp);
		//menu.add(1, 1, 2, R.string.btnExit);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int gid = item.getGroupId();
		int id = item.getItemId();
		if(id == 1) {
			// Help
			Intent intent = new Intent();
			intent.setClass(PuzzleSelect.this, HelpActivity.class);
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

	public class ImageAdapter extends BaseAdapter {
		Context mContext;
		public ImageAdapter(Context c) {
            mContext = c;
        }
		@Override
		public int getCount() {
			return puzzles.length;
		}
		@Override
		public Object getItem(int pos) {
			return pos;
		}
		@Override
		public long getItemId(int pos) {
			return pos;
		}
		@Override
		public View getView(int pos, View convertView, ViewGroup parent) {
			View v;
			//if(convertView == null){
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.puzzleicon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(puzzles[pos].name);
				ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
				//iv.setLayoutParams(new GridView.LayoutParams(iconSize, iconSize));
				iv.setAdjustViewBounds(true);
				iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
				iv.layout(0, 0, iconSize, iconSize);
				//iv.setPadding(iconPadding, iconPadding, iconPadding, iconPadding);
				iv.setImageResource(puzzles[pos].imageId);
				v.setPadding(iconPadding, iconPadding, iconPadding, iconPadding);
			//}
//			else {
//				v = convertView;
//			}
			return v;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		puzzle_grid.invalidate();
	}

}
