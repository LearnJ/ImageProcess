package com.jia.imageprocess;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ViewFlipActivity extends Activity   {


	ViewFlipper viewFlip;
	int [] resids={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_flip);
		
		viewFlip=(ViewFlipper) findViewById(R.id.flip);
		for(int i=0;i<resids.length;i++){
			viewFlip.addView(createView(resids[i]));
		}
			
		viewFlip.setInAnimation(this,R.anim.left_in);
		viewFlip.setOutAnimation(this,R.anim.left_out);
		viewFlip.setFlipInterval(3000);
		viewFlip.startFlipping();	
		viewFlip.getDisplayedChild();
	
	}
	
	private ImageView  createView( int resid){
		ImageView image=new ImageView(this);
		image.setBackgroundResource(resid);
		return image;
	}


	
	
}
