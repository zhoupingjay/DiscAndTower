package com.myproj.discandtower;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Disc {
	public static int[] DiscDrawableRes = {R.drawable.disc1, R.drawable.disc2, R.drawable.disc3, R.drawable.disc4, R.drawable.disc5};
	public static int MaxDiscSize = 5;
	private Bitmap bitmap;
	private int size;
	private int width, height;
	private ImageView imgDisc;
	
	public Disc(int discSize) {
		assert(discSize >= 0 && discSize < MaxDiscSize);
		this.size = discSize;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void loadBitmap(int w, int h, Drawable drawable) {
		bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		width = w;
		height = h;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void attachImageView(ImageView img) {
		imgDisc = img;
	}
	
	public ImageView getImageView() {
		return imgDisc;
	}
}
