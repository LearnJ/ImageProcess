package com.jia.imageprocess;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends Activity implements OnSeekBarChangeListener {

	private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;
	SeekBar sbR;
	SeekBar sbG;
	SeekBar sbB;
	SeekBar sbA;
	SeekBar sbBH;
	ImageView image;
	float r,g,b,a,bh;
	Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		initEvent();
		
	}
	
	void initView(){
		image=(ImageView) findViewById(R.id.imageView1);
		sbR=(SeekBar) findViewById(R.id.seekBar1);
		sbG=(SeekBar) findViewById(R.id.seekBar2);
		sbB=(SeekBar) findViewById(R.id.seekBar3);
		sbA=(SeekBar) findViewById(R.id.seekBar4);
		sbBH=(SeekBar) findViewById(R.id.seekBar5);
		sbR.setProgress(MID_VALUE);
		sbG.setProgress(MID_VALUE);
		sbB.setProgress(MID_VALUE);
		sbA.setProgress(MID_VALUE);
		sbBH.setProgress(MID_VALUE);
		sbR.setMax(MAX_VALUE);
		sbG.setMax(MAX_VALUE);
		sbB.setMax(MAX_VALUE);
		sbA.setMax(MAX_VALUE);
		sbBH.setMax(MAX_VALUE);
		bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.test);
		image.setImageBitmap(bitmap);
	}
	void initEvent(){
	
		sbR.setOnSeekBarChangeListener(this);
		sbG.setOnSeekBarChangeListener(this);
		sbB.setOnSeekBarChangeListener(this);
		sbA.setOnSeekBarChangeListener(this);
		sbBH.setOnSeekBarChangeListener(this);
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		 
		 ColorMatrix cm=new ColorMatrix();
		   switch(seekBar.getId())
		   {
		   case R.id.seekBar1:
			   r=(progress-MID_VALUE)*1.0F/MID_VALUE*180;
			   break;
          case R.id.seekBar2:
        	  g= (progress-MID_VALUE)*1.0F/MID_VALUE*180;
			   break;
          case R.id.seekBar3:
        	  b=(progress-MID_VALUE)*1.0F/MID_VALUE*180;
	           break;
          case R.id.seekBar4:
        	  a=progress*1.0F/MID_VALUE;
	          break;
          case R.id.seekBar5:
        	  bh=progress*1.0F/MID_VALUE;
	         break;
	         
		   }
		   Log.e("ccc", "onProgressChanged");
		  image.setImageBitmap(handleImage(bitmap,r,g,b,a,bh));
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		Log.e("ccc", "onStartTrackingTouch");
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		Log.e("ccc", "onStopTrackingTouch");
	}
	/**
	 * 
	 * @param bm待处理图片
	 * @param r图片的Red分量
	 * @param g图片的Green分量
	 * @param b图片的Blue分量
	 * @param a图片的亮度设置值
	 * @param bh图片的饱和度
	 * @return处理好的图片
	 */
	public Bitmap handleImage(Bitmap bm,float r,float g,float b, float a,float bh)
     	{
		Bitmap bmp=Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),Bitmap.Config.ARGB_8888);
		Canvas canvas=new Canvas(bmp);
		Paint paint=new Paint();
		ColorMatrix hueMatrix=new ColorMatrix();
		hueMatrix.setRotate(0, r);
		hueMatrix.setRotate(1, g);
		hueMatrix.setRotate(2, b);
		
		ColorMatrix saturationMatrix=new ColorMatrix();
		saturationMatrix.setSaturation(bh);
		
		ColorMatrix  lumMatrix=new ColorMatrix();
		lumMatrix.setScale(a, a, a, 1);
		
		ColorMatrix  imageMatrix=new ColorMatrix();
		imageMatrix.postConcat(hueMatrix);
		imageMatrix.postConcat(saturationMatrix);
		imageMatrix.postConcat(lumMatrix);
		
		paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
		canvas.drawBitmap(bm, 0, 0, paint);
		
		return bmp;
    	}
	
	public void go(View view){
		startActivity(new Intent(this,MatrixActivity.class));
	}
	
	public void go2(View view){
		startActivity(new Intent(this,PixelsEffectActivity.class));
	}
	
	public void go3(View view){
		startActivity(new Intent(this,ViewFlipActivity.class));
	}
}
