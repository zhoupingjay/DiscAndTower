package com.myproj.discandtower;

import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpActivity extends Activity {
	private ImageView imgHelp;
	private TextView txtHelp, txtAuthor;

	public HelpActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.help);
		
		Display display = getWindowManager().getDefaultDisplay();
		
		txtHelp = (TextView) findViewById(R.id.txtHelp);
		txtAuthor = (TextView) findViewById(R.id.txtAuthor);
		imgHelp = (ImageView) findViewById(R.id.imgHelp);
		imgHelp.setImageResource(R.drawable.help);
		imgHelp.setMaxWidth(display.getWidth());
		imgHelp.setMaxHeight(display.getHeight()/3);
		imgHelp.setAdjustViewBounds(true);
		
		Pattern pattern = Pattern.compile("http://sites.google.com/site/myandroidprojects");
		Linkify.addLinks(txtAuthor, pattern, "http://");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("HelpActivity", "onStop");
	}
}
