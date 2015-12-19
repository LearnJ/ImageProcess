package com.jia.imageprocess;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PixelsEffectActivity extends Activity {

	ImageView imageView;
	Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pixels_effect);
		imageView=(ImageView) findViewById(R.id.imageView);
		bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.test);
		imageView.setImageBitmap(bitmap);
	}
	
	
	public void qufan(View view){
	//	imageView.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
		imageView.setImageBitmap(ImageHelper.handleImageCircle(bitmap));
	}
	
	public void returnOld(View view){
		imageView.setImageBitmap(ImageHelper.handleImagePixelsOldPhoto(bitmap));
	}
	
	public void onSure(View view){
		imageView.setImageBitmap(ImageHelper.handleImagePixelsRelief(bitmap));
	}
}
